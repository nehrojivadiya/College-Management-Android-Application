package com.ravikiran.collegemagement;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class VolleyService
{
    SharedPreferences mPrefs;

    IVolleyResult volleyResultCallback = null;
    Context volleyContext;

    public VolleyService(IVolleyResult resultCallback, Context context)
    {
        volleyResultCallback = resultCallback;
        volleyContext = context;
    }

    // Central Login Function
    public void LoginRequest(String url, JSONObject sendObj)
    {
        try
        {
            final RequestQueue queue = Volley.newRequestQueue(volleyContext);
            JsonObjectRequest jsonObj = new JsonObjectRequest(url, sendObj, new Response.Listener<JSONObject>()
            {
                @Override
                public void onResponse(JSONObject response)
                {
                    if (volleyResultCallback != null)
                    {
                        volleyResultCallback.notifySuccess(response);

                        // clear the cache and stop the queue
                        queue.getCache().clear();
                        queue.stop();
                    }
                }
            }, new Response.ErrorListener()
            {
                @Override
                public void onErrorResponse(VolleyError error)
                {
                    if (error instanceof TimeoutError || error instanceof NoConnectionError)
                    {
                        Toast.makeText(volleyContext, "No Internet Connection", Toast.LENGTH_LONG).show();
                        volleyResultCallback.notifyError(error);
                    }
                    if (volleyResultCallback != null)
                    {
                        volleyResultCallback.notifyError(error);
                    }

                    // clear the cache and stop the queue
                    queue.getCache().clear();
                    queue.stop();
                }
            });

            queue.add(jsonObj);
        }
        catch (Exception e)
        {

        }
    }

    // Central Get Request Function
    public void getRequest(String url)
    {
        try
        {
            // Generate Header - application/json and Authorization
            final RequestQueue queue = Volley.newRequestQueue(volleyContext);
            JSONObject postparams = new JSONObject();
            postparams.put("Content-Type", "application/json");

            // Get the token from SharedPreferences
            SharedPreferences sobj = volleyContext.getSharedPreferences(Config.SHARED_PREF, Context.MODE_PRIVATE);
            final String str = sobj.getString("token", "");
            postparams.put("Authorization", "Bearer " + str);

            JsonObjectRequest jsonObj = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>()
            {
                @Override
                public void onResponse(JSONObject response)
                {
                    if (volleyResultCallback != null)
                    {
                        volleyResultCallback.notifySuccess(response);

                        // clear the cache and stop the queue
                        queue.getCache().clear();
                        queue.stop();
                    }
                }
            }, new Response.ErrorListener()
            {
                @Override
                public void onErrorResponse(VolleyError error)
                {
                    Log.d(Config.TAG, "Volley Error" + error);

                    if (error instanceof TimeoutError || error instanceof NoConnectionError)
                    {
                        volleyResultCallback.notifyError(error);
                    }
                    else if (error instanceof AuthFailureError)
                    {
                        Log.d(Config.TAG, "Auth Failure Error" + error);
                        mPrefs =  volleyContext.getSharedPreferences(Config.SHARED_PREF, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = mPrefs.edit();
                        editor.putString("agentId", "agentId");
                        editor.remove("token");
                        editor.apply();
                        Intent myIntent = new Intent(volleyContext , LoginActivity.class);
                        volleyContext.startActivity(myIntent);
                    }

                    if (volleyResultCallback != null)
                    {
                        volleyResultCallback.notifyError(error);
                    }

                    // clear the cache and stop the queue
                    queue.getCache().clear();
                    queue.stop();
                }
            })
            {
                @Override
                public Map getHeaders() throws AuthFailureError
                {
                    HashMap headers = new HashMap();
                    headers.put("Content-Type", "application/json");
                    headers.put("Authorization", "Bearer " + str);
                    return headers;
                }
            };
            queue.add(jsonObj);
        }
        catch (Exception e)
        {

        }
    }

    // Central Post Request Function
    public void postRequest(final String url, final JSONObject sendObj)
    {
        try
        {
            final RequestQueue queue = Volley.newRequestQueue(volleyContext);

            // Get the token from SharedPreferences
            SharedPreferences sobj = volleyContext.getSharedPreferences(Config.SHARED_PREF, Context.MODE_PRIVATE);
            final String str = sobj.getString("token", "");

            final JsonObjectRequest jsonObj = new JsonObjectRequest(url, sendObj, new Response.Listener<JSONObject>()
            {
                @Override
                public void onResponse(JSONObject response)
                {
                    if (volleyResultCallback != null)
                    {
                        volleyResultCallback.notifySuccess(response);

                        // clear the cache and stop the queue
                        queue.getCache().clear();
                        queue.stop();

                    }
                }
            }, new Response.ErrorListener()
            {
                @Override
                public void onErrorResponse(VolleyError error)
                {

                    Log.d(Config.TAG, "Volley Error" + error);

                    if (error instanceof TimeoutError || error instanceof NoConnectionError)
                    {
                        volleyResultCallback.notifyError(error);
                    }

                    else if (error instanceof AuthFailureError)
                    {
                        Log.d(Config.TAG, "Auth Failure Error" + error);
                        mPrefs =  volleyContext.getSharedPreferences(Config.SHARED_PREF, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = mPrefs.edit();
                        editor.putString("agentId", "agentId");
                        editor.remove("token");
                        editor.apply();
                        Intent myIntent = new Intent(volleyContext , LoginActivity.class);
                        volleyContext.startActivity(myIntent);
                    }

                    if (volleyResultCallback != null)
                    {
                        volleyResultCallback.notifyError(error);
                    }

                    // clear the cache and stop the queue
                    queue.getCache().clear();
                    queue.stop();
                }
            })
            {
                @Override
                public Map getHeaders() throws AuthFailureError
                {
                    HashMap headers = new HashMap();
                    headers.put("Content-Type", "application/json");
                    headers.put("Authorization", "Bearer " + str);
                    return headers;
                }
            };
            queue.add(jsonObj);
        }
        catch (Exception e)
        {

        }
    }
}
