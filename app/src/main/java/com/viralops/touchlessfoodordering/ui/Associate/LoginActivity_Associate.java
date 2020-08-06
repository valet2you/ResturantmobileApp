package com.viralops.touchlessfoodordering.ui.Associate;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity_Associate extends AppCompatActivity implements OnClickListener {
     Login login;
    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{"foo@example.com:hello", "bar@example.com:world"};
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */


    // UI references.
    private EditText mEmailView;
    private EditText mPasswordView;
    private View mLoginFormView;
    private SessionManager sessionManager;
    private SessionManagerFCM sessionManagerFCM;
    private TextView reset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.parseColor("#0f4693"));
        }
        // Set up the login form.
        setContentView(R.layout.associate_login);

        sessionManager=new SessionManager(LoginActivity_Associate.this);
        sessionManagerFCM=new SessionManagerFCM(LoginActivity_Associate.this);

        mEmailView = (EditText) findViewById(R.id.email);

        mPasswordView = (EditText) findViewById(R.id.password);


        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mEmailView.getText().toString().equals("")){
                    mEmailView.setError("Enter username");
                }
                else if(mPasswordView.getText().toString().equals("")){
                    mPasswordView.setError("Enter password");
                }
                else{
                    if(Network.isNetworkAvailable(LoginActivity_Associate.this)){
                        SignIn();
                    }
                    else if(Network.isNetworkAvailable2(LoginActivity_Associate.this)){
                        SignIn();
                    }
                    else{

                    }
                }




            }
        });

        mLoginFormView = findViewById(R.id.login_form);
    }


    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    private void SignIn() {
        // display a progress dialog
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity_Associate.this);

        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog


        (RetrofitClientInstance.getApiService().SignIn(mEmailView.getText().toString().trim(),mPasswordView.getText().toString().trim(),sessionManagerFCM.getToken(),"android")).enqueue(new Callback<Login>() {
            @Override
            public void onResponse(@NonNull Call<Login> call, @NonNull Response<Login> response) {

                if(response.code()==201||response.code()==200){
                    Login  login = response.body();
                    Toast.makeText(LoginActivity_Associate.this,login.getMessage(),Toast.LENGTH_SHORT).show();
                    sessionManager.setPorchName(login.getHotel().getName());
                    sessionManager.setACCESSTOKEN(login.getAccess_token());

                    Intent intent = new Intent(LoginActivity_Associate.this, AssociateMain.class);
                    startActivity(intent);

                    progressDialog.dismiss();

                }
                else if(response.code()==401){
                    Login login = response.body();
                    Toast.makeText(LoginActivity_Associate.this, "Invalid credentials. Please try again.", Toast.LENGTH_SHORT).show();
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


    @Override
    public void onClick(View view) {

    }


}

