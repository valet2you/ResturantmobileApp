package com.viralops.touchlessfoodordering;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import com.viralops.touchlessfoodordering.ui.main.MainFragment;

public class MainActivity extends AppCompatActivity {
    private TextView busytray;
    private TextView busytrolley;
    private TextView busyassciate;
    private TextView availabletray;
    private TextView availabletrolley;
    private TextView availableassciate;
    private TextView totaltext;
    private TextView totalorders;
    private TextView text;
    private TextView text1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        Typeface font = Typeface.createFromAsset(
                getAssets(),
                "font/Roboto-Regular.ttf");
        Typeface font1 = Typeface.createFromAsset(
                getAssets(),
                "font/Roboto-Thin.ttf");

        busytray=findViewById(R.id.busytray);
        busytray.setTypeface(font);
        busytrolley=findViewById(R.id.busytrolley);
        busytrolley.setTypeface(font);
        busyassciate=findViewById(R.id.busyassociate);
        busyassciate.setTypeface(font);

        availabletray=findViewById(R.id.avalbletray);
        availabletray.setTypeface(font1);
        availabletrolley=findViewById(R.id.avalbletrollet);
        availabletrolley.setTypeface(font1);
        availableassciate=findViewById(R.id.avalbleassociate);
        availableassciate.setTypeface(font1);
        totaltext=findViewById(R.id.total);
        totaltext.setTypeface(font);
        totalorders=findViewById(R.id.totalorders);
        totalorders.setTypeface(font1);

        text=findViewById(R.id.text);
        text.setTypeface(font);
        text1=findViewById(R.id.text1);
        text1.setTypeface(font1);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_view, MainFragment.newInstance())
                    .commitNow();
        }
    }
}
