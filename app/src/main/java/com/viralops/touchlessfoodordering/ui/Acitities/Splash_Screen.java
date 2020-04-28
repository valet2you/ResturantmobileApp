package com.viralops.touchlessfoodordering.ui.Acitities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.viralops.touchlessfoodordering.MainActivity;
import com.viralops.touchlessfoodordering.R;
import com.viralops.touchlessfoodordering.ui.Support.SessionManager;

public class Splash_Screen extends AppCompatActivity {
    private TextView v;
    private TextView serve;
    private TextView power;
    private TextView by;
    private TextView viral;
    private SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash__screen);
        v=findViewById(R.id.v);
        serve=findViewById(R.id.serve);
        power=findViewById(R.id.power);
        by=findViewById(R.id.by);
        viral=findViewById(R.id.viral);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            v.setLetterSpacing((float) 0.6);
            serve.setLetterSpacing((float) 0.7);
            power.setLetterSpacing((float) 0.5);
            by.setLetterSpacing((float) 0.5);
            viral.setLetterSpacing((float) 0.5);
        }
        sessionManager=new SessionManager(Splash_Screen.this);
        Thread logoTimer = new Thread() {
            public void run(){
                try{
                    int logoTimer = 0;
                    while(logoTimer < 1000){
                        sleep(100);
                        logoTimer = logoTimer +100;
                    };
                    try {
                        if (sessionManager.getKeyUsertype().equals("")) {
                            startActivity(new Intent(Splash_Screen.this, Login_Activity.class));
                            finish();
                        } else {
                            Intent intent=new Intent(Splash_Screen.this, MainActivity.class);
                            intent.putExtra("openvalue","dashboard");
                            startActivity(intent);
                            finish();
                        }
                    }
                    catch (Exception e){
                        startActivity(new Intent(Splash_Screen.this, Login_Activity.class));
                        finish();
                    }
                }

                catch (InterruptedException e) {
                    //  Auto-generated catch block
                    e.printStackTrace();
                }

                finally{
                    finish();
                }
            }
        };

        logoTimer.start();

    }
}
