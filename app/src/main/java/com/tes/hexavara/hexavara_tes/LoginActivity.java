package com.tes.hexavara.hexavara_tes;

        import android.content.SharedPreferences;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
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

public class LoginActivity extends AppCompatActivity {
    EditText user,password;
    Button login;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Boolean savelogin;
    CheckBox savelogincheckbox;
    TextView serverresponse;
    String url = "hexavara.ip-dynamic.com/androidrec/public/api/login";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user=(EditText)findViewById(R.id.txtuser);
        password=(EditText)findViewById(R.id.usrpass);
        login=(Button)findViewById(R.id.btnlogin);
        savelogincheckbox = (CheckBox)findViewById(R.id.checkbox);
        serverresponse=(TextView)findViewById(R.id.JsonResponse);

        sharedPreferences = getSharedPreferences("loginref",MODE_PRIVATE);
        editor = sharedPreferences.edit();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        savelogin=sharedPreferences.getBoolean("savelogin",false);
        if(savelogin==true){
            user.setText(sharedPreferences.getString("username",null));
            password.setText(sharedPreferences.getString("userpass",null));
        }
    }

    public void login(){
        String usrname = user.getText().toString();
        String usrpass = password.getText().toString();
        if(usrname.length() == 0 || usrpass.length() < 6) {
            Toast.makeText(this,"error username or password",Toast.LENGTH_LONG).show();
        }
        else {
            final RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,

                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                                serverresponse.setText(response);
                                Toast.makeText(LoginActivity.this,response,Toast.LENGTH_LONG).show();
                                requestQueue.stop();
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(LoginActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                    error.printStackTrace();
                    requestQueue.stop();
                }
            });
            requestQueue.add(stringRequest);
            if (savelogincheckbox.isChecked())
                editor.putBoolean("savelogin", true);
            else
                editor.putBoolean("savelogin", false);
            editor.putString("username", usrname);
            editor.putString("userpass", usrpass);
            editor.commit();
            editor.apply();
        }
    }
}
