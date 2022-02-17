package com.example.learnnative;

import androidx.annotation.LongDef;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    String valEdittext;
    String coba1;
    Button btnSubmit, btnChangeBG, getApiBtn;
    TextView txtView;
    Toast toast;
    ImageView homeImage;
    private TextView totalCasesWorld, totalDeathsWorld, totalRecoveredWorld;
    boolean cekBg = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RequestQueue requestQueue
                = Volley.newRequestQueue(this);

        editText = findViewById(R.id.editTextTextPersonName2);
        btnSubmit = findViewById(R.id.button);
        btnChangeBG = findViewById(R.id.btnChangeBG);
        getApiBtn = findViewById(R.id.btnApiCovid);
        homeImage = findViewById(R.id.imageView);
        txtView = findViewById(R.id.textView3);
        totalCasesWorld = findViewById(R.id.newCasesWorld);
        totalDeathsWorld = findViewById(R.id.newDeathWorld);
        totalRecoveredWorld = findViewById(R.id.newRecoveredWorld);

// Create a String request using Volley Library


        String url = "https://corona.lmao.ninja/v2/all";

        StringRequest request
                = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject myJson = new JSONObject(response);
                            Log.d("apiResponse", myJson.toString());
                            totalCasesWorld.setText(myJson.getString("cases"));
                            totalDeathsWorld.setText(myJson.getString("deaths"));
                            totalRecoveredWorld.setText(myJson.getString("recovered"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("apiResponse", e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(
                            VolleyError error) {
                        Log.d("apiResponse",error.toString());
                    }
                });
        getApiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Log.d("apiResponse", "masuk");
                requestQueue.add(request);
//                Log.d("apiResponse", "selesai");

            }
        });


        //
        btnChangeBG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.d("image", "onClick: "+homeImage.);
                if (cekBg == false) {
                    homeImage.setImageResource(R.drawable.home2);
                    cekBg = true;
                } else {
                    homeImage.setImageResource(R.drawable.home);
                    cekBg = false;
                }
            }
        });


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtView.setText(editText.getText());
                toast = Toast.makeText(getApplicationContext(), editText.getText(), Toast.LENGTH_LONG);
                toast.show();
                Log.d("eText", "onClick: " + editText.getText());
            }
        });


    }

}