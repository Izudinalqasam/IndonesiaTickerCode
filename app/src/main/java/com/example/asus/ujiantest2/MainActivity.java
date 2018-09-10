package com.example.asus.ujiantest2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

    static final String API_KEY = "FW6L9MZ7ZJN5YK3A";
    static final String URL_TIME = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=MSFT&apikey=";

    RequestQueue call;

    TextView timer, hasil;
    EditText editNegara;
    Button cekNegara;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        call = Volley.newRequestQueue(this);

        timer = (TextView) findViewById(R.id.timer);
        hasil = (TextView) findViewById(R.id.textHasil);
        editNegara = (EditText) findViewById(R.id.editNegara);
        cekNegara = (Button) findViewById(R.id.cekNegara);

        cekNegara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cekNegara(editNegara.getText().toString());
            }
        });
        aksesTime();
    }


    public void cekNegara(String negara){

        if (negara.endsWith(".JK")){
            hasil.setText("itu negara Idonesia");
        }else {
            negara = negara+".JK";
            hasil.setText("COde tersebut bukanlah dari negara indonesia jika dijadikan negara indonesia maka akan menjadi "+ negara);
        }
    }

    String metaData = "";
    public void aksesTime() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_TIME + API_KEY, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    metaData = jsonObject.getString("Meta Data");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                timer.setText(metaData);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        call.add(stringRequest);
    }

}
