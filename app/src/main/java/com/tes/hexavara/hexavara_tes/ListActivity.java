package com.tes.hexavara.hexavara_tes;

import android.app.LauncherActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class ListActivity extends AppCompatActivity {
    public static String EXTRA_NAME = "extra_name";
    public static String EXTRA_ID = "extra_id";
    public static String EXTRA_EMAIL = "extra_email";
    public static String EXTRA_ADDRESS = "extra_address";
    public static String EXTRA_IMAGE = "extra_image";
    public static String EXTRA_TOKEN = "extra_token";
    private List<ListItem> list;
    TextView fullname;
    TextView username;
    TextView email;
    TextView address;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    CircleImageView profile;
    String token;
    String url = "http://hexavara.ip-dynamic.com/androidrec/public/api/mylist";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        fullname = (TextView)findViewById(R.id.fullName);
        username = (TextView)findViewById(R.id.userName);
        email    = (TextView)findViewById(R.id.email);
        address  = (TextView)findViewById(R.id.address);
        recyclerView = (RecyclerView)findViewById(R.id.rv_list);
        profile = (CircleImageView)findViewById(R.id.circle_profile);
        String fname = getIntent().getStringExtra(EXTRA_NAME);
        fullname.setText(fname);
        String id = getIntent().getStringExtra(EXTRA_ID);
        username.setText(id);
        String uemail = getIntent().getStringExtra(EXTRA_EMAIL);
        email.setText(uemail);
        String uaddress = getIntent().getStringExtra(EXTRA_ADDRESS);
        address.setText(uaddress);
        String utoken = getIntent().getStringExtra(EXTRA_TOKEN);
        token = utoken;
        String uimage = getIntent().getStringExtra(EXTRA_IMAGE);
        Glide.with(getApplicationContext()).load("http://"+uimage).into(profile);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();

        //ListData();
        loadRecyclerViewData();
    }
    /*private void loadRecyclerViewData(){
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ListItemAdapter(list, this);
        recyclerView.setAdapter(adapter);
    }*/
    private void loadRecyclerViewData(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading data...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        JSONArray jsonArray = null;
                        try {
                            jsonArray = new JSONArray(response);

                            for(int i=0; i<jsonArray.length(); i++){
                                JSONObject o = jsonArray.getJSONObject(i);
                                String Name = o.getString("name");
                                String Salary = o.getString("salary");
                                String Image = o.getString("image");
                                ListItem item = new ListItem(
                                      Name,Salary,"http://"+Image
                                );
                                list.add(item);
                            }
                            adapter = new ListItemAdapter(list,getApplicationContext());
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(ListActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                error.printStackTrace();
                //requestQueue.stop();
            }
        }){
            public Map<String, String> getHeaders()  {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("authorization", token);
                return MyData;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(ListActivity.this);
        requestQueue.add(stringRequest);
        //Intent i1 = new Intent(getApplicationContext(),LoginActivity.class);
        //startActivity(i1);
        //finish();
    }
}
