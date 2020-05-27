package com.viralops.touchlessfoodordering.ui.Acitities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.viralops.touchlessfoodordering.MainActivity;
import com.viralops.touchlessfoodordering.R;
import com.viralops.touchlessfoodordering.ui.API.RetrofitClientInstance;
import com.viralops.touchlessfoodordering.ui.Support.Network;
import com.viralops.touchlessfoodordering.ui.Support.SessionManager;
import com.viralops.touchlessfoodordering.ui.Support.SessionManagerFCM;
import com.viralops.touchlessfoodordering.ui.model.Login;

import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Login_Activity extends AppCompatActivity implements View.OnClickListener {
private TextView text;
private TextView button;
private EditText username;
private EditText password;
SessionManager sessionManager;
SessionManagerFCM sessionManagerFCM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_PORTRAIT) {
            setContentView(R.layout.activity_login_);
        } else {
            setContentView(R.layout.activity_login);
        }
         sessionManager=new SessionManager(Login_Activity.this);
         sessionManagerFCM=new SessionManagerFCM(Login_Activity.this);

        Typeface font = Typeface.createFromAsset(
                getAssets(),
                "font/Roboto-Regular.ttf");
        text=findViewById(R.id.text);
        username=findViewById(R.id.username);
        username.setTypeface(font);
        password=findViewById(R.id.password);
        password.setTypeface(font);

        button=findViewById(R.id.button);
        button.setTypeface(font);

        button.setOnClickListener(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            text.setLetterSpacing((float) 0.2);

        }
    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.button){
            if(username.getText().toString().equals("")){
                Toast.makeText(Login_Activity.this,"Please enter a username.",Toast.LENGTH_SHORT).show();

                username.setError("Please enter a username");
            }
            else if(password.getText().toString().equals("")){
                Toast.makeText(Login_Activity.this,"Please enter a password.",Toast.LENGTH_SHORT).show();

                password.setError("Please enter a password");

            }
            else {

                if (Network.isNetworkAvailable(Login_Activity.this)) {
                    SignIn();
                } else if (Network.isNetworkAvailable2(Login_Activity.this)) {
                    SignIn();

                } else {

                }
            }
        }
    }

    private void SignIn() {
        // display a progress dialog
        final ProgressDialog progressDialog = new ProgressDialog(Login_Activity.this);

        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog


        (RetrofitClientInstance.getApiService().SignIn(username.getText().toString().trim(),password.getText().toString().trim(),sessionManagerFCM.getToken(),"android")).enqueue(new Callback<Login>() {
            @Override
            public void onResponse(@NonNull Call<Login> call, @NonNull Response<Login> response) {

                if(response.code()==201||response.code()==200){
                    Login  login = response.body();
                    Toast.makeText(Login_Activity.this,login.getMessage(),Toast.LENGTH_SHORT).show();
                    sessionManager.setPorchName(login.getHotel().getName());
                    sessionManager.setACCESSTOKEN(login.getAccess_token());

                    Intent intent = new Intent(Login_Activity.this, MainActivity.class);
                    startActivity(intent);

                    progressDialog.dismiss();

                }
                else if(response.code()==401){
                    Login login = response.body();
                   Toast.makeText(Login_Activity.this, "Invalid credentials. Please try again.", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();

                }
                else if(response.code()==500){
                    Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();


                }
                else{

                }




            }

            @Override
            public void onFailure(@NonNull Call<Login> call, @NonNull Throwable t) {
                Log.d("response", Arrays.toString(t.getStackTrace()));
                progressDialog.dismiss();

            }
        });

    }


}
