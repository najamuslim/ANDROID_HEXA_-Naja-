package com.tes.hexavara.hexavara_tes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
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

import de.hdodenhof.circleimageview.CircleImageView;

public class ItemActivity extends AppCompatActivity {
    public static String EXTRA_NAME = "extra_name";
    public static String EXTRA_ID = "extra_id";
    public static String EXTRA_EMAIL = "extra_email";
    public static String EXTRA_SALARY = "extra_salary";
    public static String EXTRA_IMAGE = "extra_image";
    public static String EXTRA_TOKEN = "extra_token";
    ImageView detailPhoto;
    TextView itemName, itemSalary,nameProf,itemEmail;
    CircleImageView circlePhoto;
    String url = "http://hexavara.ip-dynamic.com/androidrec/public/api/detail/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        detailPhoto = (ImageView)findViewById(R.id.detailImage);
        itemName = (TextView)findViewById(R.id.detailName);
        itemSalary = (TextView)findViewById(R.id.detailSalary);
        nameProf = (TextView)findViewById(R.id.itemName);
        itemEmail = (TextView)findViewById(R.id.itemEmail);
        circlePhoto = (CircleImageView)findViewById(R.id.item_profileimage);
    }
}