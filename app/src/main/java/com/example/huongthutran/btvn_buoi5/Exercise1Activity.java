package com.example.huongthutran.btvn_buoi5;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Exercise1Activity extends AppCompatActivity {
    Button btnSignIn,btnSignUp;
    MySQLite mySQL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise1);
        mySQL=new MySQLite(Exercise1Activity.this);
        btnSignIn=(Button)findViewById(R.id.btnSignIn);
        btnSignUp=(Button)findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        //onClick btn Signin
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog=new Dialog(Exercise1Activity.this);
                dialog.setTitle("Thông báo");
                dialog.setContentView(R.layout.activity_dialog__signin);
                final EditText edtUserName=(EditText)dialog.findViewById(R.id.edtUserName);
                final EditText edtPassword=(EditText)dialog.findViewById(R.id.edtPassWord);
                final Button btnSignInDialog=(Button)dialog.findViewById(R.id.btnSignInDialog) ;
                final TextView tvMess=(TextView)dialog.findViewById(R.id.tvMess);
                dialog.setTitle("SIGN IN");
                dialog.show();
                btnSignInDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String userName=edtUserName.getText().toString();
                        String password=edtPassword.getText().toString();
                        User user=new User();
                        try {
                            user=mySQL.getUserByNameAndPass(userName,password);
                            /*tvMess.setText("Thành công");*/
                            Intent i=new Intent(Exercise1Activity.this,Login_success.class);
                            Bundle bundle=new Bundle();
                            bundle.putInt("id",user.getId_user());
                            bundle.putString("userName",user.getUserName());
                            i.putExtras(bundle);
                            startActivity(i);
                            dialog.dismiss();

                        }catch (Exception e){
                            tvMess.setText("Sai thông tin đăng nhập");
                        }
                    }
                });
            }
        });
        //set click Sign Up Button
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog=new Dialog(Exercise1Activity.this);
                dialog.setTitle("Thông báo");
                dialog.setContentView(R.layout.activity_dialog__signup);
                final EditText edtUserName=(EditText)dialog.findViewById(R.id.edtUserName);
                final EditText edtPassword=(EditText)dialog.findViewById(R.id.edtPassWord);
                final EditText edtRePassword=(EditText)dialog.findViewById(R.id.edtRePassWord);
                final Button btnSignInDialog=(Button)dialog.findViewById(R.id.btnSignUpDialog) ;
                final TextView tvMess=(TextView)dialog.findViewById(R.id.tvMess);
                dialog.setTitle("SIGN UP");
                dialog.show();
                btnSignInDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String userName=edtUserName.getText().toString();
                        String password=edtPassword.getText().toString();
                        String rePassword=edtRePassword.getText().toString();
                        if(password.equals(rePassword)&&!mySQL.checkUserName(userName)){
                            mySQL.InsertUser(userName,password);
                            Toast.makeText(Exercise1Activity.this,"Create a accout successful",Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                        } else{
                            tvMess.setText("Lỗi");
                        }
                    }
                });
            }
        });

    }

}
