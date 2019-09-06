package com.ravikiran.collegemagement;

import com.android.volley.VolleyError;

import org.json.JSONObject;

public interface IVolleyResult
{
     void notifySuccess(JSONObject response);
     void notifyError(VolleyError error);
}
