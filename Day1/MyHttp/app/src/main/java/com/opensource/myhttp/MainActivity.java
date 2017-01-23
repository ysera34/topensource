package com.opensource.myhttp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    private RequestQueue mQueue;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQueue = Volley.newRequestQueue(getApplicationContext());
        mTextView = (TextView) findViewById(R.id.text_view);

        (findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request();
            }
        });
    }

    private void request() {
        String url = "http://192.168.211.233:3000";
        StringRequest request = new StringRequest(
                // for request
                Request.Method.POST, url,
                // for response
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        println(response);
                        processResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        println(error.getMessage());
                    }
                }
        );
        request.setShouldCache(false);
        mQueue.add(request);
        println("request ~~~~~~~~~~>");
    }

    private void println(String data) {
        mTextView.append(data + "\n");
    }

    private void processResponse(String data) {
        Gson gson = new Gson();
        Person person = gson.fromJson(data, Person.class);
//        println(person.getName());
//        println(String.valueOf(person.getAge()));
//        println(person.getMobile());
        println(person.name);
        println(String.valueOf(person.age));
        println(person.mobile);
    }
}
