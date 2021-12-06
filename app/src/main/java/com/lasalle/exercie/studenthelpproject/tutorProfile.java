package com.lasalle.exercie.studenthelpproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lasalle.exercie.studenthelpproject.model.Tutor;
import com.lasalle.exercie.studenthelpproject.model.TutorialAssignment;

import java.util.ArrayList;

public class tutorProfile extends AppCompatActivity implements View.OnClickListener, ValueEventListener, ChildEventListener {

    Button btnLogout;
    TextView tvWelcome,tvDetails;
    DatabaseReference tutorDB,tutorChild;
    Tutor aTutor;
    String tutorId;

    ListView LvAppointment;
    ArrayList<TutorialAssignment> ListAppointment;
    ArrayAdapter<TutorialAssignment> lvAdapter;
    DatabaseReference TADatabase,ChildDatabase;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_profile);
        initialize();
    }

    private void initialize() {
        btnLogout = findViewById(R.id.btnLogout);
        tvWelcome = findViewById(R.id.tvWelcome);
        tvDetails = findViewById(R.id.tvDetails);

        btnLogout.setOnClickListener(this);


        Intent i = getIntent();
        tutorId = i.getStringExtra("tutorid");

        aTutor = new Tutor();
        tutorDB = FirebaseDatabase.getInstance().getReference("Tutor");

        getTutor();

        TADatabase = FirebaseDatabase.getInstance().getReference("TutorialAssignment");
        getAppointment();

        LvAppointment = findViewById(R.id.lvAppointments);
        ListAppointment= new ArrayList<>();
        lvAdapter = new ArrayAdapter<TutorialAssignment>(this, android.R.layout.simple_list_item_1, ListAppointment);
        LvAppointment.setAdapter(lvAdapter);



    }

    private void getAppointment() {
        ChildDatabase = TADatabase.child(tutorId);
        ChildDatabase.addChildEventListener(this);
    }

    private void getTutor() {
        tutorChild = tutorDB.child(tutorId);
        tutorChild.addValueEventListener(this);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id){

            case R.id.btnLogout:
                toStudentLoginPage();
                break;
        }
    }



    private void toStudentLoginPage() {

        Intent i = new Intent(this ,MainActivity.class);
        startActivity(i);
        this.finish();
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {

        if(snapshot.exists()) {
            aTutor.setTutorId(Integer.valueOf(snapshot.child("tutorId").getValue().toString()));
            aTutor.setFirstName(snapshot.child("firstName").getValue().toString());
            aTutor.setLastName(snapshot.child("lastName").getValue().toString());
            aTutor.setGender(snapshot.child("gender").getValue().toString());
            aTutor.setDateOfBirth(snapshot.child("dateOfBirth").getValue().toString());
            aTutor.setEmail(snapshot.child("email").getValue().toString());
            aTutor.setSkill(snapshot.child("skill").getValue().toString());

            String name = "Welcome " + aTutor.getFirstName() + " " + aTutor.getLastName();

            tvWelcome.setText(name);
            tvDetails.setText(aTutor.toString());

        }else{
            Toast.makeText(this,"The document with the id "  + " doesn't exist",Toast.LENGTH_LONG).show();

        }

    }

    @Override
    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

        if (snapshot.exists()){
            String StudentId = snapshot.child("studentId").getValue().toString();
            String TutorId = snapshot.child("tutorId").getValue().toString();
            String TutorialDate = snapshot.child("tutorialDate").getValue().toString();
            String TutorialDescription = snapshot.child("tutorialDescription").getValue().toString();
            TutorialAssignment tutorialAssignment = new TutorialAssignment(Integer.valueOf(StudentId),Integer.valueOf(TutorId), TutorialDate,TutorialDescription);
            ListAppointment.add(tutorialAssignment);
            lvAdapter.notifyDataSetChanged();
        }
        else {
            Toast.makeText(this,"Nothing",Toast.LENGTH_LONG).show();
        }






    }

    @Override
    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

    }

    @Override
    public void onChildRemoved(@NonNull DataSnapshot snapshot) {

    }

    @Override
    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
}