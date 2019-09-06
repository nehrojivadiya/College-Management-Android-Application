package com.ravikiran.collegemagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class StudentHomePage extends AppCompatActivity implements View.OnClickListener
{
    //UI Components
    CardView cv1StYear, cv2NdYear, cv3RdYear, cv4ThYear;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home_page);


        // START: CardView Requirement Intialization
        cv1StYear = findViewById(R.id.cv_1st_year);
        cv1StYear.setOnClickListener(this);
        cv2NdYear = findViewById(R.id.cv_2nd_year);
        cv2NdYear.setOnClickListener(this);
        cv3RdYear = findViewById(R.id.cv_3rd_year);
        cv3RdYear.setOnClickListener(this);
        cv4ThYear = findViewById(R.id.cv_4th_year);
        cv4ThYear.setOnClickListener(this);
        // END: CardView Requirement Intialization
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            // START : Getting Into The Particular Page
            case R.id.cv_1st_year:
                Intent intentReq = new Intent(getApplicationContext(), SemesterPage.class);
                startActivity(intentReq);
                break;
            case R.id.cv_2nd_year:
                Intent intentAudit = new Intent(getApplicationContext(), SemesterPage.class);
                startActivity(intentAudit);
                break;
            case R.id.cv_3rd_year:
                Intent intentCalc = new Intent(getApplicationContext(), SemesterPage.class);
                startActivity(intentCalc);
                break;
            case R.id.cv_4th_year:
                Intent intentInfo = new Intent(getApplicationContext(), SemesterPage.class);
                startActivity(intentInfo);
                break;
            // END : Getting Into The Particular Page
        }
    }
}
