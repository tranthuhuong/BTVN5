package com.example.huongthutran.btvn_buoi5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Login_success extends AppCompatActivity {
    TextView infoUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success);
        infoUser=(TextView)findViewById(R.id.infoUser);
        Bundle bundle=getIntent().getExtras();

        infoUser.setText("ID: "+bundle.getInt("id")+"  UserName: "+bundle.getString("userName"));
    }
}
