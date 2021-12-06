package com.lasalle.exercie.studenthelpproject;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnLogin,btnStudentSignUp, btnTutorSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize() {
        btnLogin = findViewById(R.id.btnLogin);
        btnStudentSignUp = findViewById(R.id.btnStudentSignUp);
        btnTutorSignUp = findViewById(R.id.btnTutorSignUp);
        btnLogin.setOnClickListener(this);
        btnStudentSignUp.setOnClickListener(this);
        btnTutorSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id){

            case R.id.btnLogin:
                toStudentLoginPage();
                break;
            case R.id.btnStudentSignUp:
                toStudentSignUp();
                break;
            case R.id.btnTutorSignUp:
                toTutorSignUp();

                break;

    }
}

    private void toTutorSignUp() {
            Intent i = new Intent(this ,TutorRegistration.class);
            startActivity(i);
            this.finish();

    }

    private void toStudentSignUp() {
        Intent i = new Intent(this ,StudentRegistration.class);
        startActivity(i);
        this.finish();
    }

    private void toStudentLoginPage() {
        Intent i = new Intent(this, StudentLoginPage.class);
        startActivity(i);
        this.finish();
    }
}