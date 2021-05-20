package com.example.validation;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {

    final static private String URL ="http://jyj98071.cafe24.com/dbinfo.php";
    private Map<String,String> parameters;


    public RegisterRequest(String userEmail, String userTechNum, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(Method.POST, URL, listener, errorListener);
        parameters = new HashMap<>();
        parameters.put("userEmail",userEmail);
        parameters.put("userTechNum",userTechNum);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return parameters;
    }
}
