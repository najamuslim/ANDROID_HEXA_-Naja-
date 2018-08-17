package com.tes.hexavara.hexavara_tes;

        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.media.Image;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.CheckBox;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.android.volley.Request;
        import com.android.volley.RequestQueue;
        import com.android.volley.Response;
        import com.android.volley.VolleyError;
        import com.android.volley.toolbox.StringRequest;
        import com.android.volley.toolbox.Volley;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.util.HashMap;
        import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    Boolean errorserver=false;
    EditText user,password;
    Button login;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String Name,Id,Email,Address,Image,Token;
    String url = "http://hexavara.ip-dynamic.com/androidrec/public/api/login";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user=(EditText)findViewById(R.id.txtuser);
        password=(EditText)findViewById(R.id.usrpass);
        login=(Button)findViewById(R.id.btnlogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    public void login(){
        final String usrname = user.getText().toString();
        final String usrpass = password.getText().toString();
        if(usrname.length() == 0 || usrpass.length() < 6) {
            Toast.makeText(this,"error username or password",Toast.LENGTH_LONG).show();
        }
        else {
            final RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,

                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            JSONObject r = null;
                            try {
                                r = new JSONObject(response);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            try {
                                    //Log.e("AAA", "onCreate: "+User.getString("")+" "+User.getInt(""));
                                    String name = r.getString("fullname");
                                    Name = name;
                                    String id   = r.getString("username");
                                    Id = id;
                                    String email = r.getString("email");
                                    Email = email;
                                    String address = r.getString("address");
                                    Address = address;
                                    String image = r.getString("photo");
                                    Image = image;
                                    String token = r.getString("token");
                                    Token = token;

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                                //Toast.makeText(LoginActivity.this,Token,Toast.LENGTH_LONG).show();
                                Intent i1 = new Intent(LoginActivity.this, ListActivity.class);
                                i1.putExtra(ListActivity.EXTRA_NAME, Name);
                                i1.putExtra(ListActivity.EXTRA_ID, Id);
                                i1.putExtra(ListActivity.EXTRA_EMAIL, Email);
                                i1.putExtra(ListActivity.EXTRA_ADDRESS, Address);
                                i1.putExtra(ListActivity.EXTRA_IMAGE, Image);
                                i1.putExtra(ListActivity.EXTRA_TOKEN, Token);
                                startActivity(i1);
                                finish();
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    errorserver=true;
                    Toast.makeText(LoginActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                    error.printStackTrace();
                    requestQueue.stop();
                }
            }){
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<String, String>();
                    MyData.put("username", usrname);
                    MyData.put("password",usrpass);
                    return MyData;
                }
            };
            requestQueue.add(stringRequest);
        }
    }
}
