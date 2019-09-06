package com.ravikiran.collegemagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class SemesterPage extends AppCompatActivity implements View.OnClickListener
{
    //UI Components
    CardView cv1StSemester, cv2NdSemester;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semester_page);

        // START: CardView Requirement Intialization
        cv1StSemester = findViewById(R.id.cv_1st_semester);
        cv1StSemester.setOnClickListener(this);
        cv2NdSemester = findViewById(R.id.cv_2nd_semester);
        cv2NdSemester.setOnClickListener(this);
        // END: CardView Requirement Intialization
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            // START : Getting Into The Particular Page
            case R.id.cv_1st_semester:
                Intent intentReq = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intentReq);
                break;
            case R.id.cv_2nd_semester:
                Intent intentAudit = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intentAudit);
                break;
            // END : Getting Into The Particular Page
        }

    }
}
