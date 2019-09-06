package com.ravikiran.collegemagement;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity
{
    EditText editTextLogin, editTextPassword;
    Button loginButton;

    IVolleyResult loginCallback = null;
    IVolleyResult checkLoginCallback = null;

    VolleyService LoginService;
    VolleyService CheckLoginService;

    private static final String TAG = "LoginActivity";
    private static final String URL_FOR_LOGIN = "https://XXX.XXX.X.XX/android_login_example/login.php";
    ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Progress dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);

        editTextLogin=findViewById(R.id.editTextUserName);
        editTextPassword=findViewById(R.id.editTextPassword);
        loginButton=findViewById(R.id.LoginButton);

        loginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Boolean noErrors=true;
                if (editTextLogin.getText().toString().length() == 0)
                {
                    editTextLogin.setError("User Name is required!");
                    noErrors = false;
                }
                if (editTextPassword.getText().toString().length() == 0)
                {
                    editTextPassword.setError("Password is required!");
                    noErrors = false;
                }

                if(noErrors)
                {
                    loginUser(editTextLogin.getText().toString(),
                            editTextPassword.getText().toString());
                    if(editTextLogin.getText().toString().equals("admin")&&editTextPassword.getText().toString().equals("admin"))
                    {
                        Toast.makeText(LoginActivity.this, "Admin Login Success", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(),AdminHomePage.class);
                        startActivity(intent);
                    }
                    else if(editTextLogin.getText().toString().equals("student")&&editTextPassword.getText().toString().equals("student"))
                    {
                        Toast.makeText(LoginActivity.this, "Student Login Success", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(),StudentHomePage.class);
                        startActivity(intent);
                    }
                    else if(editTextLogin.getText().toString().equals("faculty")&&editTextPassword.getText().toString().equals("faculty"))
                    {
                        Toast.makeText(LoginActivity.this, "Faculty Login Success", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(),FacultyHomePage.class);
                        startActivity(intent);
                    }
                    else
                    {
                        progressDialog.dismiss();
                        Toast.makeText(LoginActivity.this, "Credentials Not Found", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void loginUser( final String email, final String password) {
        // Tag used to cancel the request
        String cancel_req_tag = "login";
        progressDialog.setMessage("Logging you in...");
        showDialog();
        StringRequest strReq = new StringRequest(
                Request.Method.POST,
                URL_FOR_LOGIN, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Register Response: " + response.toString());
                hideDialog();
                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    if (!error) {
                        String user = jObj.getJSONObject("user").getString("name");
                        // Launch User activity
                        Intent intent = new Intent(
                                LoginActivity.this,
                                AdminHomePage.class);
                        intent.putExtra("username", user);
                        startActivity(intent);
                        finish();
                    } else {

                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // Posting params to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };
        // Adding request to request queue
        //AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(strReq,cancel_req_tag);
    }

    private void showDialog() {
        if (!progressDialog.isShowing())
            progressDialog.show();
    }
    private void hideDialog() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }
}