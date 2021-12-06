package com.lasalle.exercie.studenthelpproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lasalle.exercie.studenthelpproject.model.Tutor;
import com.lasalle.exercie.studenthelpproject.model.TutorialAssignment;

import java.util.ArrayList;

public class TutorStudentAssignment extends AppCompatActivity implements ChildEventListener, View.OnClickListener, AdapterView.OnItemClickListener {

    Button btnRegister;
    EditText edTutorialdate, edTutorialDescription;
    TextView tvStudentId, tvTutorId;
    ListView lvTutorId;

    String studentid ;

    ArrayList<Tutor> listTutor;
    ArrayAdapter<Tutor> lvAdapter;
    DatabaseReference tutorDB, tutorChildDB;

    DatabaseReference tutorialAssignmentDB,tutorialAssignmentChild;

    int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_student_assignment);

        initialize();
    }

    private void initialize() {
        btnRegister = findViewById(R.id.btnRegister);
        edTutorialdate = findViewById(R.id.edTutorialDate);
        edTutorialDescription = findViewById(R.id.edTutorialDescription);
        tvStudentId = findViewById(R.id.tvStudenId);
        tvTutorId = findViewById(R.id.tvTutorId);
        lvTutorId = findViewById(R.id.lvListTutors);
        btnRegister.setOnClickListener(this);


        Intent  i = getIntent();
        studentid = i.getStringExtra("studentid");
        tvStudentId.setText(studentid);


        listTutor = new  ArrayList<Tutor>();
        lvAdapter = new ArrayAdapter<Tutor>(this, android.R.layout.simple_list_item_1,listTutor);
        tutorDB = FirebaseDatabase.getInstance().getReference("Tutor");
        tutorDB.addChildEventListener(this);
        lvTutorId.setAdapter(lvAdapter);

        lvTutorId.setOnItemClickListener(this);




        tutorialAssignmentDB = FirebaseDatabase.getInstance().getReference("TutorialAssignment");
        tutorialAssignmentDB.addChildEventListener(this);



    }


    @Override
    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

        try {


            if (snapshot.exists()) {
                int id = Integer.parseInt(snapshot.child("TutorId").getValue().toString());
                String fname = snapshot.child("FirstName").getValue().toString();
                String lname = snapshot.child("LastName").getValue().toString();
                String gender = snapshot.child("Gender").getValue().toString();
                String bday = snapshot.child("DateOfBirth").getValue().toString();
                String email = snapshot.child("Email").getValue().toString();
                String skill = snapshot.child("skill").getValue().toString();
                Tutor tutor = new Tutor(id, fname, lname, email, gender, bday,skill);
                listTutor.add(tutor);
                lvAdapter.notifyDataSetChanged();


            } else {


                Toast.makeText(this, "there is a problem", Toast.LENGTH_LONG).show();
            }

        }catch (Exception e){

            Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_LONG).show();



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



    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Tutor tutor = listTutor.get(i);

        tvTutorId.setText(String.valueOf(tutor.getTutorId()));


    }

    @Override
    public void onClick(View view) {
        try {
//
            TutorialAssignment tutoAssign = new TutorialAssignment();

            tutoAssign.setStudentId(Integer.valueOf(tvStudentId.getText().toString()));
            tutoAssign.setTutorId(Integer.valueOf(tvTutorId.getText().toString()));
            tutoAssign.setTutorialDate(edTutorialdate.getText().toString());
            tutoAssign.setTutorialDescription(edTutorialDescription.getText().toString());

            tutorialAssignmentChild = tutorialAssignmentDB.child(tvStudentId.getText().toString()).child(String.valueOf(count));
            tutorialAssignmentChild.setValue(tutoAssign);
            count++;
        }catch (Exception e){

            Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_LONG).show();

        }

    }
}