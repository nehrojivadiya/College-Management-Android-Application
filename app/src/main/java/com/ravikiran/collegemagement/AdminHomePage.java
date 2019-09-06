package com.ravikiran.collegemagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class AdminHomePage extends AppCompatActivity implements View.OnClickListener
{
    CardView cardViewAddUser, cardViewAddBranch, cardViewAddSubject, cardViewSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_page);

        cardViewAddUser = findViewById(R.id.cv_add_user);
        cardViewAddUser.setOnClickListener(this);
        cardViewAddBranch = findViewById(R.id.cv_add_branch);
        cardViewAddBranch.setOnClickListener(this);
        cardViewAddSubject = findViewById(R.id.cv_add_subject);
        cardViewAddSubject.setOnClickListener(this);
        cardViewSettings = findViewById(R.id.cv_settings);
        cardViewSettings.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.cv_add_user:
                Intent intent=new Intent(this, CreateUserActivity.class);
                startActivity(intent);
                break;
            case R.id.cv_add_branch:
                Intent i=new Intent(this, AddBranch.class);
                startActivity(i);
                break;
            case R.id.cv_add_subject:
                Intent intentcal=new Intent(this, AddSubject.class);
                startActivity(intentcal);
                break;
            case R.id.cv_settings:
                Intent in=new Intent(this, SettingActivity.class);
                startActivity(in);
                break;
        }
    }
}
