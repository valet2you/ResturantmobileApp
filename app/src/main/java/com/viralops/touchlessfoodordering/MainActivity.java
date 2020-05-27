package com.viralops.touchlessfoodordering;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.squareup.picasso.Picasso;
import com.todkars.shimmer.ShimmerRecyclerView;
import com.viralops.touchlessfoodordering.ui.API.RetrofitClientInstance;
import com.viralops.touchlessfoodordering.ui.Laundry.LaudryModel;
import com.viralops.touchlessfoodordering.ui.Laundry.LaundryOrderHistory;
import com.viralops.touchlessfoodordering.ui.Laundry.Laundry_Category;
import com.viralops.touchlessfoodordering.ui.Laundry.Laundry_fragment;
import com.viralops.touchlessfoodordering.ui.Laundry.Laundry_item;
import com.viralops.touchlessfoodordering.ui.Support.Internetconnection;
import com.viralops.touchlessfoodordering.ui.Support.Network;
import com.viralops.touchlessfoodordering.ui.Support.SessionManager;
import com.viralops.touchlessfoodordering.ui.Support.SessionManagerFCM;
import com.viralops.touchlessfoodordering.ui.history.OrderHistory;
import com.viralops.touchlessfoodordering.ui.model.Action;
import com.viralops.touchlessfoodordering.ui.model.IRD_Addons;
import com.viralops.touchlessfoodordering.ui.model.IRD_Data;
import com.viralops.touchlessfoodordering.ui.model.IRD_Item;
import com.viralops.touchlessfoodordering.ui.model.IRd_SubCategory;
import com.viralops.touchlessfoodordering.ui.model.Menu;
import com.viralops.touchlessfoodordering.ui.model.Menucategory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ArrayList<Menu.menu_data> laudryModelslist=new ArrayList<>();
    private TextView text;
    RecyclerView recyclerView;
     Dialog menudialog;
    private TextView text1;
    private CardView order;
    private CardView menu;
    private  TextView history;
    private ImageView logout;
    private SessionManager sessionManager;
    private SessionManagerFCM sessionManagerFCM;
     Dialog dialog1;
    LaundryCAtegoryAdapter   laundryCartegoryAdapter;
    ShimmerRecyclerView shimmerRecyclerView;
    static public boolean isvisisble=true;

    static public LinearLayout neworderss;
    static public TextView standardsrvice;
    static public TextView standtotal;
    static public TextView express;
    static public TextView totalexpress;
    static public TextView totallaudry;
    static public TextView totalorderslaundry;
    static  public ImageView belllaundry;
    static public LinearLayout startndard;
    ArrayList<Laundry_Category> laundrymenulist=new ArrayList<>();
    ArrayList<Laundry_Category> laundrycategorylistlist=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        sessionManager=new SessionManager(MainActivity.this);
        sessionManagerFCM=new SessionManagerFCM(MainActivity.this);
        sessionManager.setIsINternet("false");

        final Typeface font = Typeface.createFromAsset(
                getAssets(),
                "font/Roboto-Regular.ttf");
        Typeface font1 = Typeface.createFromAsset(
                getAssets(),
                "font/Roboto-Thin.ttf");


        menu=findViewById(R.id.menu);
        belllaundry=  findViewById(R.id.belllaundry);
        belllaundry.setImageResource(R.mipmap.calling);
        belllaundry.setVisibility(View.INVISIBLE);

        standardsrvice=findViewById(R.id.standardsrvice);
        standardsrvice.setTypeface(font);
        express=findViewById(R.id.express);
        express.setTypeface(font);
        totallaudry=findViewById(R.id.totallaudry);
        totallaudry.setTypeface(font);
        standtotal=findViewById(R.id.standtotal);
        standtotal.setTypeface(font1);
        totalexpress=findViewById(R.id.totalexpress);
        totalexpress.setTypeface(font);
        totalorderslaundry=findViewById(R.id.totalorderslaundry);
        totalorderslaundry.setTypeface(font);
        startndard=findViewById(R.id.startndard);

        text=findViewById(R.id.text);
        text.setTypeface(font);
        text1=findViewById(R.id.text1);
        text.setText(sessionManager.getPorchName());
        history=findViewById(R.id.history);
        text1.setTypeface(font1);
        text1.setText("");
        logout=findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(MainActivity.this);
                // Include dialog.xml file

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                // dialog.getWindow().setLayout(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.FILL_PARENT);            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.delet_dialog);
                int width1 = (int)(getResources().getDisplayMetrics().widthPixels*0.50);
                int height1 = (int)(getResources().getDisplayMetrics().heightPixels*0.90);
                dialog.getWindow().setGravity(Gravity.CENTER_VERTICAL);

                dialog.getWindow().setLayout(width1, height1);

                dialog.setCancelable(false);
                // Set dialog title
                dialog.setTitle("");
                dialog.show();
                TextView textView=(TextView)dialog.findViewById(R.id.text) ;
                // String textstring="Do you confirm that room is cleared and trolley is back to IRD operation? or"+<b>
                TextView confirm=dialog.findViewById(R.id.confirm) ;
                TextView cancel1=dialog.findViewById(R.id.cancel);

                confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(Network.isNetworkAvailable(MainActivity.this)){
                            Logout();
                            dialog.dismiss();
                        } if(Network.isNetworkAvailable2(MainActivity.this)){
                            Logout();
                            dialog.dismiss();

                        } else{
                        }
                    }
                });
                cancel1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

            }
        });
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(history.getText().toString().equals("ORDERS HISTORY")) {
                    isvisisble=false;
                    history.setText("DASHBOARD");
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_view, LaundryOrderHistory.newInstance())
                            .commitNow();
                }
                else{
                    isvisisble=true;

                    history.setText("ORDERS HISTORY");
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_view, Laundry_fragment.newInstance())
                            .commitNow();
                }
            }
        });
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final Dialog dialog = new Dialog(MainActivity.this);
                // Include dialog.xml file

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                // dialog.getWindow().setLayout(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.FILL_PARENT);            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.menulist);
                int width1 = (int) (getResources().getDisplayMetrics().widthPixels * 0.55);
                int height1 = (int) (getResources().getDisplayMetrics().heightPixels * 0.95);
                dialog.getWindow().setGravity(Gravity.CENTER_VERTICAL);

                dialog.getWindow().setLayout(width1, height1);

                dialog.setCancelable(false);
                // Set dialog title
                dialog.setTitle("");
                dialog.show();
                shimmerRecyclerView = dialog.findViewById(R.id.recyclerview);
                shimmerRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
                TextView title = dialog.findViewById(R.id.hotel);
                title.setTypeface(font);
                title.setText("Laundry Menu");
                final MaterialButton menubutton = dialog.findViewById(R.id.menubutton);
                final MaterialButton backbutton = dialog.findViewById(R.id.closebutton);
                laundryCartegoryAdapter = new LaundryCAtegoryAdapter(laundrymenulist, MainActivity.this);
                shimmerRecyclerView.setAdapter(laundryCartegoryAdapter);

                registerForContextMenu(menubutton);
                ImageView close = dialog.findViewById(R.id.close);
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                menubutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        menudialog = new Dialog(MainActivity.this);
                        // Include dialog.xml file
                        menudialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        // getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                        menudialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

                        menudialog.setContentView(R.layout.categorypopup);
                        int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.99);
                        int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.99);

                        menudialog.getWindow().setLayout(width, height);
                        menudialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation_2;

                        // dialog.setCancelable(true);
                        menudialog.setCanceledOnTouchOutside(true);
                        // setFinishOnTouchOutside(true);
                        // Set dialog title
                        menudialog.setTitle("Select Category");
                        menudialog.show();
                        recyclerView = menudialog.findViewById(R.id.recycler);
                        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
                        LaundryCartegoryAdapter menupopupadapeter = new LaundryCartegoryAdapter(laundrycategorylistlist, MainActivity.this);
                        recyclerView.setAdapter(menupopupadapeter);
                        ImageView close = menudialog.findViewById(R.id.close);
                        close.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                menudialog.dismiss();
                            }
                        });

                    }
                });


            }
        });
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_view, Laundry_fragment.newInstance())
                    .commitNow();
        }
        if (Network.isNetworkAvailable(MainActivity.this)) {
            new  LaundryIRDenu().execute();


        } else if (Network.isNetworkAvailable2(MainActivity.this)) {
            new  LaundryIRDenu().execute();


        }
        else{
            if (sessionManager.getIsINternet().equals("false")) {
                Intent intent = new Intent(MainActivity.this, Internetconnection.class);
                startActivity(intent);

                sessionManager.setIsINternet("true");
                finish();

            } else {

            }
        }

    }



    public class TagsAdapter extends  RecyclerView.Adapter<TagsAdapter.ViewHolder>{
        String[] order_items;
        Context context;
        String enabled;

        public TagsAdapter(String[] order_items, Context context) {
            this.order_items = order_items;
            this.context = context;
        }

        @NonNull
        @Override
        public TagsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout, parent, false);
            return new TagsAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final TagsAdapter.ViewHolder holder, int position) {


            holder.title.setText(capitailizeWord(order_items[position]));
            if(order_items[position].equals("Spicy")||order_items[position].equals("spicy")||order_items[position].equals("SPICY")){
                holder.title.setChipBackgroundColor(ColorStateList.valueOf(Color.parseColor("#C32B20")));

            }
            else {
                int[] androidColors = getResources().getIntArray(R.array.androidcolors);
                int randomAndroidColor = androidColors[new Random().nextInt(androidColors.length)];
                holder.title.setChipBackgroundColor(ColorStateList.valueOf(randomAndroidColor));
            }

        }

        @Override
        public int getItemCount() {
            return order_items.length;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            Chip title;
            String[] mitem;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                title=itemView.findViewById(R.id.chipentry);


                final Typeface font = Typeface.createFromAsset(
                        getAssets(),
                        "font/Roboto-Regular.ttf");
                Typeface font1 = Typeface.createFromAsset(
                        getAssets(),
                        "font/Roboto-Thin.ttf");

                title.setTypeface(font);

            }
        }



    }

    public class CartegoryAdapter extends  RecyclerView.Adapter<CartegoryAdapter.ViewHolder>{
        ArrayList<Menucategory> order_items;
        Context context;

        public CartegoryAdapter(ArrayList<Menucategory> order_items, Context context) {
            this.order_items = order_items;
            this.context = context;
        }

        @NonNull
        @Override
        public CartegoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.category_item, parent, false);
            return new CartegoryAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CartegoryAdapter.ViewHolder holder, final int position) {
            holder.mitem=order_items.get(position);
            holder.title.setText(holder.mitem.getName());
            holder.category1.setText(String.valueOf(holder.mitem.getItems().size()));


        }

        @Override
        public int getItemCount() {
            return order_items.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView title;
            TextView category1;



            Menucategory mitem;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                title=itemView.findViewById(R.id.title);
                category1=itemView.findViewById(R.id.count);

                final Typeface font = Typeface.createFromAsset(
                        getAssets(),
                        "font/Roboto-Regular.ttf");
                Typeface font1 = Typeface.createFromAsset(
                        getAssets(),
                        "font/Roboto-Thin.ttf");

                title.setTypeface(font);
                category1.setTypeface(font);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        shimmerRecyclerView.smoothScrollToPosition(getAdapterPosition());
                        menudialog.dismiss();

                    }
                });

            }
        }



    }


    private void Logout() {
        // display a progress dialog
        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog
/*
        String credentials = Credentials.basic("admin", "LetsValet2You");
*/

        (RetrofitClientInstance.getApiService().logout( "Bearer "+sessionManager.getACCESSTOKEN())).enqueue(new Callback<Action>() {
            @Override
            public void onResponse(@NonNull Call<Action> call, @NonNull Response<Action> response) {

                if(response.code()==201||response.code()==200){
                    Action  login = response.body();
                    Toast.makeText(MainActivity.this,login.getMessage(),Toast.LENGTH_SHORT).show();
                    sessionManager.logoutsession();
                    finish();


                }
                else if(response.code()==401){
                    Action login = response.body();
                    Toast.makeText(MainActivity.this, "Unauthorised", Toast.LENGTH_SHORT).show();
                    sessionManager.logoutsession();
                    finish();
                }
                else if(response.code()==500){
                    Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_SHORT).show();

                }
                else{

                }

                progressDialog.dismiss();



            }

            @Override
            public void onFailure(@NonNull Call<Action> call, @NonNull Throwable t) {
                Log.d("response", Arrays.toString(t.getStackTrace()));
                progressDialog.dismiss();

            }
        });

    }



    // Showing the status in Snackbar

    // Method to manually check connection status

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(isvisisble==true) {
                belllaundry.setVisibility(View.VISIBLE);
                AnimateBell();

                Fragment fragment1 = new Laundry_fragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.content_view, fragment1, fragment1.getClass().getSimpleName()).addToBackStack(null).commit();
            }
            else {
                isvisisble=false;
                belllaundry.setVisibility(View.VISIBLE);
                AnimateBell();
            }



        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        try {

            registerReceiver(mMessageReceiver, new IntentFilter("com.viralops.touchlessfoodordering"));

            unregisterReceiver(mMessageReceiver);

        }
        catch (Exception e)
        {
        }

    }

    @Override
    protected void onResume() {
        super.onResume();



        try {

            registerReceiver(mMessageReceiver, new IntentFilter("com.viralops.touchlessfoodordering"));


        }
        catch (Exception e)
        {
        }

    }
    public void onBackPressed() {

            //super.onBackPressed();
            final Dialog dialog = new Dialog(MainActivity.this);
            // Include dialog.xml file

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            // dialog.getWindow().setLayout(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.FILL_PARENT);            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.delet_dialog);
            int width1 = (int)(getResources().getDisplayMetrics().widthPixels*0.50);
            int height1 = (int)(getResources().getDisplayMetrics().heightPixels*0.90);
            dialog.getWindow().setGravity(Gravity.CENTER_VERTICAL);

            dialog.getWindow().setLayout(width1, height1);

            dialog.setCancelable(false);
            // Set dialog title
            dialog.setTitle("");
            dialog.show();
            TextView textView=dialog.findViewById(R.id.text) ;
            textView.setText("Are you sure you want to exit ?");
            // String textstring="Do you confirm that room is cleared and trolley is back to IRD operation? or"+<b>
            TextView confirm=dialog.findViewById(R.id.cancel) ;
            TextView cancel1=dialog.findViewById(R.id.confirm);

            confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            cancel1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity.this.finish();
                    dialog.dismiss();
                }
            });

        }

    public void AnimateBell() {
        Animation shake = AnimationUtils.loadAnimation(MainActivity.this, R.anim.shakeanimation);

        belllaundry.setAnimation(shake);

    }
    // Method to convert the string
    static String capitailizeWord(String str) {
        StringBuffer s = new StringBuffer();

        // Declare a character of space
        // To identify that the next character is the starting
        // of a new word
        char ch = ' ';
        for (int i = 0; i < str.length(); i++) {

            // If previous character is space and current
            // character is not space then it shows that
            // current letter is the starting of the word
            if (ch == ' ' && str.charAt(i) != ' ')
                s.append(Character.toUpperCase(str.charAt(i)));
            else
                s.append(str.charAt(i));
            ch = str.charAt(i);
        }

        // Return the string with trimming
        return s.toString().trim();
    }


    //-------------Laundry------------------------------//

    public class LaundryIRDenu extends AsyncTask<String, String, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }


        @Override
        protected String doInBackground(String... params) {
            String result = "";
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS).build();


            try {
                String credentials = Credentials.basic("admin", "LetsValet2You");

                Request request = new Request.Builder()
                        .url(BuildConfig.BASE_URL + BuildConfig.laundry_menu)
                        .addHeader("Authorization", "Bearer " + sessionManager.getACCESSTOKEN())
                        .get()
                        .build();
                okhttp3.Response response = client.newCall(request).execute();
                if (!response.isSuccessful()) {
                    return null;
                }
                return response.body().string();
            } catch (Exception e) {
                return null;
            }

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            laundrymenulist.clear();
            laundrycategorylistlist.clear();

            if (result != null) {

                try {
                    JSONObject jsonObject = new JSONObject(result.replaceAll("\t", "").trim());
                    JSONObject data=jsonObject.getJSONObject("data");
                    IRD_Data ird_data=new IRD_Data();
                    ird_data.setId(data.getString("id"));
                    ird_data.setName(data.getString("name"));
                    ird_data.setHotel_id(data.getString("hotel_id"));
                    ird_data.setDescription(data.getString("description"));
                    ird_data.setEnabled(data.getString("enabled"));
                    ird_data.setCreated_at(data.getString("created_at"));
                    ird_data.setUpdated_at(data.getString("updated_at"));
                    JSONArray jsonArray=data.getJSONArray("categories");
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject1=jsonArray.getJSONObject(i);
                        Laundry_Category ird_category=new Laundry_Category();
                        ird_category.setId(jsonObject1.getString("id"));
                        ird_category.setCreated_at(jsonObject1.getString("created_at"));
                        ird_category.setUpdated_at(jsonObject1.getString("updated_at"));
                        ird_category.setLaundry_id(jsonObject1.getString("laundry_id"));
                        ird_category.setDescription(jsonObject1.getString("description"));
                        ird_category.setEnabled(jsonObject1.getString("enabled"));
                        ird_category.setName(jsonObject1.getString("name"));
                        ird_category.setTags(jsonObject1.getString("tags"));
                        ird_category.setWithout_sub_category_items(jsonObject1.getJSONArray("without_sub_category_items"));
                        ird_category.setSub_categories(jsonObject1.getJSONArray("sub_categories"));
                        laundrymenulist.add(ird_category);
                        laundrycategorylistlist.add(ird_category);
                    }


                }
                catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }
    }
    public class LaundryIRDenunew extends AsyncTask<String, String, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }


        @Override
        protected String doInBackground(String... params) {
            String result = "";
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS).build();


            try {
                String credentials = Credentials.basic("admin", "LetsValet2You");

                Request request = new Request.Builder()
                        .url(BuildConfig.BASE_URL + BuildConfig.laundry_menu)
                        .addHeader("Authorization", "Bearer " + sessionManager.getACCESSTOKEN())
                        .get()
                        .build();
                okhttp3.Response response = client.newCall(request).execute();
                if (!response.isSuccessful()) {
                    return null;
                }
                return response.body().string();
            } catch (Exception e) {
                return null;
            }

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            laundrymenulist.clear();
            laundrycategorylistlist.clear();

            if (result != null) {

                try {
                    JSONObject jsonObject = new JSONObject(result.replaceAll("\t", "").trim());
                    JSONObject data=jsonObject.getJSONObject("data");
                    IRD_Data ird_data=new IRD_Data();
                    ird_data.setId(data.getString("id"));
                    ird_data.setName(data.getString("name"));
                    ird_data.setHotel_id(data.getString("hotel_id"));
                    ird_data.setDescription(data.getString("description"));
                    ird_data.setEnabled(data.getString("enabled"));
                    ird_data.setCreated_at(data.getString("created_at"));
                    ird_data.setUpdated_at(data.getString("updated_at"));
                    JSONArray jsonArray=data.getJSONArray("categories");
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject1=jsonArray.getJSONObject(i);
                        Laundry_Category ird_category=new Laundry_Category();
                        ird_category.setId(jsonObject1.getString("id"));
                        ird_category.setCreated_at(jsonObject1.getString("created_at"));
                        ird_category.setUpdated_at(jsonObject1.getString("updated_at"));
                        ird_category.setLaundry_id(jsonObject1.getString("laundry_id"));
                        ird_category.setDescription(jsonObject1.getString("description"));
                        ird_category.setEnabled(jsonObject1.getString("enabled"));
                        ird_category.setName(jsonObject1.getString("name"));
                        ird_category.setTags(jsonObject1.getString("tags"));
                        ird_category.setWithout_sub_category_items(jsonObject1.getJSONArray("without_sub_category_items"));
                        ird_category.setSub_categories(jsonObject1.getJSONArray("sub_categories"));
                        laundrymenulist.add(ird_category);
                        laundrycategorylistlist.add(ird_category);
                    }
                    laundryCartegoryAdapter =new LaundryCAtegoryAdapter(laundrymenulist,MainActivity.this);
                    shimmerRecyclerView.setAdapter(laundryCartegoryAdapter);
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }
    }
    private void LaundryGetMenuitemenab(String id) {
        // display a progress dialog
        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog
/*
        String credentials = Credentials.basic("admin", "LetsValet2You");
*/

        (RetrofitClientInstance.getApiService().getItemEnabledLaundry("Bearer "+sessionManager.getACCESSTOKEN(),BuildConfig.laundry_item_enable+id)).enqueue(new Callback<Action>() {
            @Override
            public void onResponse(@NonNull Call<Action> call, @NonNull Response<Action> response) {

                if(response.code()==202||response.code()==200){
                    Action  login = response.body();
                    Toast.makeText(MainActivity.this, login.getMessage(), Toast.LENGTH_SHORT).show();
                    if(Network.isNetworkAvailable(MainActivity.this)){
                        new LaundryIRDenunew().execute();

                    }
                    else if(Network.isNetworkAvailable2(MainActivity.this)){
                        new LaundryIRDenunew().execute();

                    }
                    else{

                    }

                }
                else if(response.code()==401){
                    Action login = response.body();
                    Toast.makeText(MainActivity.this, "Unauthorised", Toast.LENGTH_SHORT).show();
                    sessionManager.logoutsession();
                    finish();
                }
                else if(response.code()==500){
                    Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_SHORT).show();

                }
                else{

                }

                progressDialog.dismiss();



            }

            @Override
            public void onFailure(@NonNull Call<Action> call, @NonNull Throwable t) {
                Log.d("response", Arrays.toString(t.getStackTrace()));
                progressDialog.dismiss();

            }
        });

    }
    private void LaundryGetMenuitemdisable(String id) {
        // display a progress dialog
        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog
/*
        String credentials = Credentials.basic("admin", "LetsValet2You");
*/

        (RetrofitClientInstance.getApiService().getItemDisabledLaundry("Bearer "+sessionManager.getACCESSTOKEN(),BuildConfig.laundry_disable_item+id)).enqueue(new Callback<Action>() {
            @Override
            public void onResponse(@NonNull Call<Action> call, @NonNull Response<Action> response) {

                if(response.code()==202||response.code()==200){
                    Action  login = response.body();
                    Toast.makeText(MainActivity.this, login.getMessage(), Toast.LENGTH_SHORT).show();
                    if(Network.isNetworkAvailable(MainActivity.this)){
                        new LaundryIRDenunew().execute();

                    }
                    else if(Network.isNetworkAvailable2(MainActivity.this)){
                        new LaundryIRDenunew().execute();

                    }
                    else{

                    }

                }
                else if(response.code()==404){
                    Action login = response.body();
                    Toast.makeText(MainActivity.this, "Unauthorised", Toast.LENGTH_SHORT).show();
                    sessionManager.logoutsession();
                    finish();
                }
                else if(response.code()==500){
                    Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_SHORT).show();

                }
                else{

                }

                progressDialog.dismiss();



            }

            @Override
            public void onFailure(@NonNull Call<Action> call, @NonNull Throwable t) {
                Log.d("response", Arrays.toString(t.getStackTrace()));
                progressDialog.dismiss();

            }
        });

    }
    private void LaundryGetMenucategoryenable(String id) {
        // display a progress dialog
        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog
/*
        String credentials = Credentials.basic("admin", "LetsValet2You");
*/

        (RetrofitClientInstance.getApiService().getcategoryEnabledLaundry("Bearer "+sessionManager.getACCESSTOKEN(),BuildConfig.laundry_enable_category+id)).enqueue(new Callback<Action>() {
            @Override
            public void onResponse(@NonNull Call<Action> call, @NonNull Response<Action> response) {

                if(response.code()==202||response.code()==200){
                    Action  login = response.body();
                    Toast.makeText(MainActivity.this, login.getMessage(), Toast.LENGTH_SHORT).show();
                    if(Network.isNetworkAvailable(MainActivity.this)){
                        new LaundryIRDenunew().execute();

                    }
                    else if(Network.isNetworkAvailable2(MainActivity.this)){
                        new LaundryIRDenunew().execute();

                    }
                    else{

                    }


                }
                else if(response.code()==401){
                    Action login = response.body();
                    Toast.makeText(MainActivity.this, "Unauthorised", Toast.LENGTH_SHORT).show();
                    sessionManager.logoutsession();
                    finish();
                }
                else if(response.code()==500){
                    Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_SHORT).show();

                }
                else{

                }

                progressDialog.dismiss();



            }

            @Override
            public void onFailure(@NonNull Call<Action> call, @NonNull Throwable t) {
                Log.d("response", Arrays.toString(t.getStackTrace()));
                progressDialog.dismiss();

            }
        });

    }
    private void LaundryGetMenucategorydisable(String id) {
        // display a progress dialog
        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog
/*
        String credentials = Credentials.basic("admin", "LetsValet2You");
*/

        (RetrofitClientInstance.getApiService().getcategorydisabledLaundry("Bearer "+sessionManager.getACCESSTOKEN(),BuildConfig.laundry_disable_category+id)).enqueue(new Callback<Action>() {
            @Override
            public void onResponse(@NonNull Call<Action> call, @NonNull Response<Action> response) {

                if(response.code()==202||response.code()==200){
                    Action  login = response.body();
                    Toast.makeText(MainActivity.this, login.getMessage(), Toast.LENGTH_SHORT).show();
                    if(Network.isNetworkAvailable(MainActivity.this)){
                        new LaundryIRDenunew().execute();

                    }
                    else if(Network.isNetworkAvailable2(MainActivity.this)){
                        new LaundryIRDenunew().execute();

                    }
                    else{

                    }

                }
                else if(response.code()==401){
                    Action login = response.body();
                    Toast.makeText(MainActivity.this, "Unauthorised", Toast.LENGTH_SHORT).show();
                    sessionManager.logoutsession();
                    finish();
                }
                else if(response.code()==500){
                    Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_SHORT).show();

                }
                else{

                }

                progressDialog.dismiss();



            }

            @Override
            public void onFailure(@NonNull Call<Action> call, @NonNull Throwable t) {
                Log.d("response", Arrays.toString(t.getStackTrace()));
                progressDialog.dismiss();

            }
        });

    }
    private void LaundryMinibarGetMenuaddenab(String id) {
        // display a progress dialog
        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog
/*
        String credentials = Credentials.basic("admin", "LetsValet2You");
*/

        (RetrofitClientInstance.getApiService().getSubaddonitemenableLaundry("Bearer "+sessionManager.getACCESSTOKEN(),BuildConfig.laundry_addon_itemenable+id)).enqueue(new Callback<Action>() {
            @Override
            public void onResponse(@NonNull Call<Action> call, @NonNull Response<Action> response) {

                if(response.code()==202||response.code()==200){
                    Action  login = response.body();
                    Toast.makeText(MainActivity.this, login.getMessage(), Toast.LENGTH_SHORT).show();


                }
                else if(response.code()==401){
                    Action login = response.body();
                    Toast.makeText(MainActivity.this, "Unauthorised", Toast.LENGTH_SHORT).show();
                    sessionManager.logoutsession();
                    finish();
                }
                else if(response.code()==500){
                    Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_SHORT).show();

                }
                else{

                }

                progressDialog.dismiss();



            }

            @Override
            public void onFailure(@NonNull Call<Action> call, @NonNull Throwable t) {
                Log.d("response", Arrays.toString(t.getStackTrace()));
                progressDialog.dismiss();

            }
        });

    }
    private void LaundryGetMenuadddisable(String id) {
        // display a progress dialog
        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog
/*
        String credentials = Credentials.basic("admin", "LetsValet2You");
*/

        (RetrofitClientInstance.getApiService().getSubaddonitemdisableLaundry("Bearer "+sessionManager.getACCESSTOKEN(),BuildConfig.laundry_addon_itemdisable+id)).enqueue(new Callback<Action>() {
            @Override
            public void onResponse(@NonNull Call<Action> call, @NonNull Response<Action> response) {

                if(response.code()==202||response.code()==200){
                    Action  login = response.body();
                    Toast.makeText(MainActivity.this, login.getMessage(), Toast.LENGTH_SHORT).show();


                }
                else if(response.code()==404){
                    Action login = response.body();
                    Toast.makeText(MainActivity.this, "Unauthorised", Toast.LENGTH_SHORT).show();
                    sessionManager.logoutsession();
                    finish();
                }
                else if(response.code()==500){
                    Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_SHORT).show();

                }
                else{

                }

                progressDialog.dismiss();



            }

            @Override
            public void onFailure(@NonNull Call<Action> call, @NonNull Throwable t) {
                Log.d("response", Arrays.toString(t.getStackTrace()));
                progressDialog.dismiss();

            }
        });

    }
    private void LaundryGetMenuaddoncategoryenable(String id) {
        // display a progress dialog
        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog
/*
        String credentials = Credentials.basic("admin", "LetsValet2You");
*/

        (RetrofitClientInstance.getApiService().getSubaddonenableLaundry("Bearer "+sessionManager.getACCESSTOKEN(),BuildConfig.laundry_addon_categoryenable+id)).enqueue(new Callback<Action>() {
            @Override
            public void onResponse(@NonNull Call<Action> call, @NonNull Response<Action> response) {

                if(response.code()==202||response.code()==200){
                    Action  login = response.body();
                    Toast.makeText(MainActivity.this, login.getMessage(), Toast.LENGTH_SHORT).show();
                    if(Network.isNetworkAvailable(MainActivity.this)){
                        new LaundryIRDenunew().execute();

                    }
                    else if(Network.isNetworkAvailable2(MainActivity.this)){
                        new LaundryIRDenunew().execute();

                    }
                    else{

                    }


                }
                else if(response.code()==401){
                    Action login = response.body();
                    Toast.makeText(MainActivity.this, "Unauthorised", Toast.LENGTH_SHORT).show();
                    sessionManager.logoutsession();
                    finish();
                }
                else if(response.code()==500){
                    Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_SHORT).show();

                }
                else{

                }

                progressDialog.dismiss();



            }

            @Override
            public void onFailure(@NonNull Call<Action> call, @NonNull Throwable t) {
                Log.d("response", Arrays.toString(t.getStackTrace()));
                progressDialog.dismiss();

            }
        });

    }
    private void LaundryGetMenuaddoncategorydisable(String id) {
        // display a progress dialog
        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog
/*
        String credentials = Credentials.basic("admin", "LetsValet2You");
*/

        (RetrofitClientInstance.getApiService().getSubaddondisabledLaundry("Bearer "+sessionManager.getACCESSTOKEN(),BuildConfig.laundry_addon_categorydisable+id)).enqueue(new Callback<Action>() {
            @Override
            public void onResponse(@NonNull Call<Action> call, @NonNull Response<Action> response) {

                if(response.code()==202||response.code()==200){
                    Action  login = response.body();
                    Toast.makeText(MainActivity.this, login.getMessage(), Toast.LENGTH_SHORT).show();
                    if(Network.isNetworkAvailable(MainActivity.this)){
                        new LaundryIRDenunew().execute();

                    }
                    else if(Network.isNetworkAvailable2(MainActivity.this)){
                        new LaundryIRDenunew().execute();

                    }
                    else{

                    }

                }
                else if(response.code()==401){
                    Action login = response.body();
                    Toast.makeText(MainActivity.this, "Unauthorised", Toast.LENGTH_SHORT).show();
                    sessionManager.logoutsession();
                    finish();
                }
                else if(response.code()==500){
                    Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_SHORT).show();

                }
                else{

                }

                progressDialog.dismiss();



            }

            @Override
            public void onFailure(@NonNull Call<Action> call, @NonNull Throwable t) {
                Log.d("response", Arrays.toString(t.getStackTrace()));
                progressDialog.dismiss();

            }
        });

    }
    private void LaundryGetMenuSUbcategoryenable(String id) {
        // display a progress dialog
        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog
/*
        String credentials = Credentials.basic("admin", "LetsValet2You");
*/

        (RetrofitClientInstance.getApiService().getSubcategoryEnabledLaundry("Bearer "+sessionManager.getACCESSTOKEN(),BuildConfig.laundry_enable_subcategory+id)).enqueue(new Callback<Action>() {
            @Override
            public void onResponse(@NonNull Call<Action> call, @NonNull Response<Action> response) {

                if(response.code()==202||response.code()==200){
                    Action  login = response.body();
                    Toast.makeText(MainActivity.this, login.getMessage(), Toast.LENGTH_SHORT).show();
                    if(Network.isNetworkAvailable(MainActivity.this)){
                        new LaundryIRDenunew().execute();

                    }
                    else if(Network.isNetworkAvailable2(MainActivity.this)){
                        new LaundryIRDenunew().execute();

                    }
                    else{

                    }


                }
                else if(response.code()==401){
                    Action login = response.body();
                    Toast.makeText(MainActivity.this, "Unauthorised", Toast.LENGTH_SHORT).show();
                    sessionManager.logoutsession();
                    finish();
                }
                else if(response.code()==500){
                    Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_SHORT).show();

                }
                else{

                }

                progressDialog.dismiss();



            }

            @Override
            public void onFailure(@NonNull Call<Action> call, @NonNull Throwable t) {
                Log.d("response", Arrays.toString(t.getStackTrace()));
                progressDialog.dismiss();

            }
        });

    }
    private void LaundryGetMenuSUbcategorydisable(String id) {
        // display a progress dialog
        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog
/*
        String credentials = Credentials.basic("admin", "LetsValet2You");
*/

        (RetrofitClientInstance.getApiService().getSubcategorydisabledLaundry("Bearer "+sessionManager.getACCESSTOKEN(),BuildConfig.laundry_disable_subcategory+id)).enqueue(new Callback<Action>() {
            @Override
            public void onResponse(@NonNull Call<Action> call, @NonNull Response<Action> response) {

                if(response.code()==202||response.code()==200){
                    Action  login = response.body();
                    Toast.makeText(MainActivity.this, login.getMessage(), Toast.LENGTH_SHORT).show();
                    if(Network.isNetworkAvailable(MainActivity.this)){
                        new LaundryIRDenunew().execute();

                    }
                    else if(Network.isNetworkAvailable2(MainActivity.this)){
                        new LaundryIRDenunew().execute();

                    }
                    else{

                    }

                }
                else if(response.code()==401){
                    Action login = response.body();
                    Toast.makeText(MainActivity.this, "Unauthorised", Toast.LENGTH_SHORT).show();
                    sessionManager.logoutsession();
                    finish();
                }
                else if(response.code()==500){
                    Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_SHORT).show();

                }
                else{

                }

                progressDialog.dismiss();



            }

            @Override
            public void onFailure(@NonNull Call<Action> call, @NonNull Throwable t) {
                Log.d("response", Arrays.toString(t.getStackTrace()));
                progressDialog.dismiss();

            }
        });

    }

    //------------End of Laundry--------------------------------------//
    //-----------------LaundryAdpater-----------------------------------//

    public class LaundryCAtegoryAdapter extends  RecyclerView.Adapter<LaundryCAtegoryAdapter.ViewHolder>{
        ArrayList<Laundry_Category> order_items;
        Context context;
        String enabled;
        ArrayList<Laundry_item> iRd_subCategories;
        ArrayList<IRD_Item> iRd_withCategories;

        public LaundryCAtegoryAdapter(ArrayList<Laundry_Category> order_items, Context context) {
            this.order_items = order_items;
            this.context = context;
        }

        @NonNull
        @Override
        public LaundryCAtegoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.irdmenuparent, parent, false);
            return new LaundryCAtegoryAdapter.ViewHolder(view);
        }
        public void filterList(ArrayList<Laundry_Category> filterdNames) {
            this.order_items = filterdNames;
            notifyDataSetChanged();
        }
        @Override
        public void onBindViewHolder(@NonNull final LaundryCAtegoryAdapter.ViewHolder holder, int position) {
            holder.mitem=order_items.get(position);
            holder.title.setText(holder.mitem.getName()+" ( "+String.valueOf(holder.mitem.getSub_categories().length()+holder.mitem.getWithout_sub_category_items().length())+" )");
            if(!holder.mitem.getDescription().equals("null")){
                holder.descriprtion.setText(holder.mitem.getDescription());

            }
            else{
                holder.descriprtion.setText("");

            }

            if(holder.mitem.getEnabled().equals("1")){
                holder.toggle.setOnCheckedChangeListener(null);
                holder.toggle.setChecked(true);
                enabled="1";
            }
            else{
                holder.toggle.setOnCheckedChangeListener(null);
                holder.toggle.setChecked(false);
                enabled="0";

            }
            holder.toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        if (Network.isNetworkAvailable(context)) {
                            LaundryGetMenucategoryenable(holder.mitem.getId());
                            enabled="1";
                        }  else if (Network.isNetworkAvailable2(context)) {
                            LaundryGetMenucategoryenable(holder.mitem.getId());
                            enabled="1";

                        }
                        else{

                        }
                    }
                    else{
                        if (Network.isNetworkAvailable(context)) {
                            LaundryGetMenucategorydisable(holder.mitem.getId());
                            enabled="0";

                        }  else if (Network.isNetworkAvailable2(context)) {
                            LaundryGetMenucategorydisable(holder.mitem.getId());
                            enabled="0";

                        }
                        else{

                        }
                    }
                }
            });
            iRd_subCategories=new ArrayList<>();
            iRd_subCategories.clear();
          /*  if(holder.mitem.getWithout_sub_category_items().length()!=0) {


                IRd_SubCategory iRd_subCategory = new IRd_SubCategory();
                iRd_subCategory.setId("");
                iRd_subCategory.setMenu_category_id("");
                iRd_subCategory.setName("");
                iRd_subCategory.setDescription("");
                iRd_subCategory.setMenu_category_id("");
                iRd_subCategory.setEnabled("1");
                iRd_subCategory.setCreated_at("");
                iRd_subCategory.setUpdated_at("");
                iRd_subCategory.setItems(holder.mitem.getWithout_sub_category_items());
                iRd_subCategories.add(iRd_subCategory);


            }
            if(holder.mitem.getSub_categories().length()!=0) {

                for (int i = 0; i < holder.mitem.getSub_categories().length(); i++) {
                    try {
                        JSONObject jsonObject = holder.mitem.getSub_categories().getJSONObject(i);
                        IRd_SubCategory iRd_subCategory = new IRd_SubCategory();
                        iRd_subCategory.setId(jsonObject.getString("id"));
                        iRd_subCategory.setMenu_category_id(jsonObject.getString("menu_category_id"));
                        iRd_subCategory.setName(jsonObject.getString("name"));
                        iRd_subCategory.setDescription(jsonObject.getString("description"));
                        iRd_subCategory.setMenu_category_id(jsonObject.getString("tags"));
                        iRd_subCategory.setEnabled(jsonObject.getString("enabled"));
                        iRd_subCategory.setCreated_at(jsonObject.getString("created_at"));
                        iRd_subCategory.setUpdated_at(jsonObject.getString("updated_at"));
                        iRd_subCategory.setItems(jsonObject.getJSONArray("items"));
                        iRd_subCategories.add(iRd_subCategory);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }


            MinibarIRD_SubCATegoryAdapter order_itemAdapterdetail = new MinibarIRD_SubCATegoryAdapter(iRd_subCategories, context, holder.mitem.getName(), enabled);
            holder.shimmerRecyclerView.setAdapter(order_itemAdapterdetail);
 */         /*  iRd_subCategories=new ArrayList<>();
            iRd_subCategories.clear();
            if(holder.mitem.getSub_categories().length()!=0) {

                for (int i = 0; i < holder.mitem.getSub_categories().length(); i++) {
                    try {
                        JSONObject jsonObject = holder.mitem.getSub_categories().getJSONObject(i);
                        IRd_SubCategory iRd_subCategory = new IRd_SubCategory();
                        iRd_subCategory.setId(jsonObject.getString("id"));
                        iRd_subCategory.setMenu_category_id(jsonObject.getString("menu_category_id"));
                        iRd_subCategory.setName(jsonObject.getString("name"));
                        iRd_subCategory.setDescription(jsonObject.getString("description"));
                        iRd_subCategory.setMenu_category_id(jsonObject.getString("tags"));
                        iRd_subCategory.setEnabled(jsonObject.getString("enabled"));
                        iRd_subCategory.setCreated_at(jsonObject.getString("created_at"));
                        iRd_subCategory.setUpdated_at(jsonObject.getString("updated_at"));
                        iRd_subCategory.setItems(jsonObject.getJSONArray("items"));
                        iRd_subCategories.add(iRd_subCategory);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                IRD_SubCATegoryAdapter order_itemAdapterdetail = new IRD_SubCATegoryAdapter(iRd_subCategories, context, holder.mitem.getName(), enabled);
                holder.shimmerRecyclerView.setAdapter(order_itemAdapterdetail);



            }
            iRd_withCategories=new ArrayList<>();
            iRd_withCategories.clear();

            if(holder.mitem.getWithout_sub_category_items().length()!=0) {

                for (int i = 0; i < holder.mitem.getWithout_sub_category_items().length(); i++) {
                    try {
                        JSONObject jsonObject = holder.mitem.getWithout_sub_category_items().getJSONObject(i);
                        IRD_Item iRd_subCategory=new IRD_Item();
                        iRd_subCategory.setId(jsonObject.getString("id"));
                        iRd_subCategory.setMenu_category_id(jsonObject.getString("menu_category_id"));
                        iRd_subCategory.setName(jsonObject.getString("name"));
                        iRd_subCategory.setDescription(jsonObject.getString("description"));
                        iRd_subCategory.setTags(jsonObject.getString("tags"));
                        iRd_subCategory.setEnabled(jsonObject.getString("enabled"));
                        iRd_subCategory.setCreated_at(jsonObject.getString("created_at"));
                        iRd_subCategory.setUpdated_at(jsonObject.getString("updated_at"));
                        iRd_subCategory.setType(jsonObject.getString("type"));
                        iRd_subCategory.setPrice(jsonObject.getString("price"));
                        iRd_subCategory.setThumbnail(jsonObject.getString("thumbnail"));
                        iRd_subCategory.setAddons(jsonObject.getJSONArray("addons"));
                        iRd_subCategory.setMenu_sub_category_id(jsonObject.getString("menu_sub_category_id"));
                        iRd_withCategories.add(iRd_subCategory);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                IRD_ItemsWithoutAdapter order_itemAdapterdetail = new IRD_ItemsWithoutAdapter(iRd_withCategories, context, holder.mitem.getName(), enabled);
                holder.shimmerRecyclerView.setAdapter(order_itemAdapterdetail);

            }
*/
            for(int i=0;i<holder.mitem.getWithout_sub_category_items().length();i++){
                try {
                    JSONObject jsonObject=holder.mitem.getWithout_sub_category_items().getJSONObject(i);
                    Laundry_item iRd_subCategory=new Laundry_item();
                    iRd_subCategory.setId(jsonObject.getString("id"));
                    iRd_subCategory.setLaundry_id(jsonObject.getString("laundry_category_id"));
                    iRd_subCategory.setName(jsonObject.getString("name"));
                    iRd_subCategory.setDescription(jsonObject.getString("description"));
                    iRd_subCategory.setTags(jsonObject.getString("tags"));
                    iRd_subCategory.setEnabled(jsonObject.getString("enabled"));
                    iRd_subCategory.setCreated_at(jsonObject.getString("created_at"));
                    iRd_subCategory.setUpdated_at(jsonObject.getString("updated_at"));
                    iRd_subCategory.setIs_express_delivery(jsonObject.getString("is_express_delivery"));
                    iRd_subCategory.setExpress_delivery_price(jsonObject.getString("express_delivery_price"));
                    iRd_subCategory.setPrice(jsonObject.getString("price"));
                    iRd_subCategory.setThumbnail(jsonObject.getString("thumbnail"));
                    iRd_subCategory.setLaundry_id(jsonObject.getString("laundry_id"));
                    iRd_subCategory.setLaundry_sub_category_id(jsonObject.getString("laundry_sub_category_id"));
                    iRd_subCategories.add(iRd_subCategory);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            LaundryIRD_ItemsAdapter order_itemAdapterdetail=new LaundryIRD_ItemsAdapter(iRd_subCategories,context,holder.mitem.getName(),enabled);
            holder.shimmerRecyclerView.setAdapter(order_itemAdapterdetail);



        }

        @Override
        public int getItemCount() {
            return order_items.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView title;
            ToggleButton toggle;
            ShimmerRecyclerView shimmerRecyclerView;
            TextView descriprtion;


            Laundry_Category mitem;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                title=itemView.findViewById(R.id.category);
                descriprtion=itemView.findViewById(R.id.descriprtion);

                toggle=itemView.findViewById(R.id.toggleButton1);
                shimmerRecyclerView=itemView.findViewById(R.id.rv_child);
                shimmerRecyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));

                final Typeface font = Typeface.createFromAsset(
                        getAssets(),
                        "font/Roboto-Regular.ttf");
                Typeface font1 = Typeface.createFromAsset(
                        getAssets(),
                        "font/Roboto-Thin.ttf");

                title.setTypeface(font);
                descriprtion.setTypeface(font);

            }
        }



    }
    public class LaundryIRD_SubCATegoryAdapter extends  RecyclerView.Adapter<LaundryIRD_SubCATegoryAdapter.ViewHolder>{
        ArrayList<IRd_SubCategory> order_items;
        ArrayList<IRd_SubCategory> continentList;
        Context context;
        String category;
        String enabled;
        String enabled1;
        ArrayList<IRD_Item> ird_itemArrayList;

        public LaundryIRD_SubCATegoryAdapter(ArrayList<IRd_SubCategory> order_items, Context context,String category,String enabled) {
            this.order_items = order_items;
            this.context = context;
            this.category=category;
            this.enabled=enabled;
            continentList=new ArrayList<>();
        }

        @NonNull
        @Override
        public LaundryIRD_SubCATegoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.itemsub, parent, false);
            return new LaundryIRD_SubCATegoryAdapter.ViewHolder(view);
        }
        public void filterList(ArrayList<IRd_SubCategory> filterdNames) {
            this.order_items = filterdNames;
            notifyDataSetChanged();
        }
        @Override
        public void onBindViewHolder(@NonNull final LaundryIRD_SubCATegoryAdapter.ViewHolder holder, int position) {
            holder.mitem=order_items.get(position);
            if(holder.mitem.getName().equals("")){
                holder.parent.setVisibility(View.GONE);
            }
            else{
                holder.parent.setVisibility(View.VISIBLE);
                holder.title.setText(holder.mitem.getName()+" ( "+String.valueOf(holder.mitem.getItems().length()+" ) "));


            }

            if(!holder.mitem.getDescription().toString().equals("null")) {
                holder.descriprtion.setText(holder.mitem.getDescription());
            }
            else{
                holder.descriprtion.setText("");

            }

            if(enabled.equals("1")){
                holder.toggleButton1.setEnabled(true);
                holder.toggleButton1.setClickable(true);
                if(holder.mitem.getEnabled().equals("1")){
                    holder.toggleButton1.setChecked(true);
                    enabled1="1";
                }
                else{
                    holder.toggleButton1.setChecked(false);
                    enabled1="0";


                }
            }
            else{
                enabled1="0";
                holder.toggleButton1.setChecked(false);
                holder.toggleButton1.setEnabled(false);
                holder.toggleButton1.setClickable(false);
            }




            holder.toggleButton1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){

                        if (Network.isNetworkAvailable(context)){
                            LaundryGetMenuSUbcategoryenable(holder.mitem.getId());
                            enabled1="1";

                        }
                        else if (Network.isNetworkAvailable2(context)) {

                            LaundryGetMenuSUbcategoryenable(holder.mitem.getId());
                            enabled1="1";

                        }
                        else{

                        }
                    }
                    else{


                        if (Network.isNetworkAvailable(context)){
                            LaundryGetMenuSUbcategorydisable(holder.mitem.getId());
                            enabled1="0";

                        }
                        else if (Network.isNetworkAvailable2(context)) {
                            LaundryGetMenuSUbcategorydisable(holder.mitem.getId());
                            enabled1="0";


                        }
                        else{

                        }
                    }
                }
            });
            ird_itemArrayList=new ArrayList<>();

            for(int i=0;i<holder.mitem.getItems().length();i++){
                try {
                    JSONObject jsonObject=holder.mitem.getItems().getJSONObject(i);
                    IRD_Item iRd_subCategory=new IRD_Item();
                    iRd_subCategory.setId(jsonObject.getString("id"));
                    iRd_subCategory.setMenu_category_id(jsonObject.getString("menu_category_id"));
                    iRd_subCategory.setName(jsonObject.getString("name"));
                    iRd_subCategory.setDescription(jsonObject.getString("description"));
                    iRd_subCategory.setTags(jsonObject.getString("tags"));
                    iRd_subCategory.setEnabled(jsonObject.getString("enabled"));
                    iRd_subCategory.setCreated_at(jsonObject.getString("created_at"));
                    iRd_subCategory.setUpdated_at(jsonObject.getString("updated_at"));
                    iRd_subCategory.setType(jsonObject.getString("type"));
                    iRd_subCategory.setPrice(jsonObject.getString("price"));
                    iRd_subCategory.setThumbnail(jsonObject.getString("thumbnail"));
                    iRd_subCategory.setSub_addons(jsonObject.getJSONArray("sub_addons"));
                    iRd_subCategory.setMenu_sub_category_id(jsonObject.getString("menu_sub_category_id"));
                    ird_itemArrayList.add(iRd_subCategory);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            /////LaundryIRD_ItemsAdapter order_itemAdapterdetail=new LaundryIRD_ItemsAdapter(ird_itemArrayList,context,holder.mitem.getName(),enabled,enabled1);
            //holder.rv_child.setAdapter(order_itemAdapterdetail);

        }

        @Override
        public int getItemCount() {
            return order_items.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView title;
            TextView descriprtion;
            RecyclerView rv_child;
            ToggleButton toggleButton1;
            RelativeLayout parent;

            IRd_SubCategory mitem;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                title=itemView.findViewById(R.id.category);
                descriprtion=itemView.findViewById(R.id.descriprtion);
                parent=itemView.findViewById(R.id.parent);
                rv_child=itemView.findViewById(R.id.rv_child);
                rv_child.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
                toggleButton1=itemView.findViewById(R.id.toggleButton1);

                final Typeface font = Typeface.createFromAsset(
                        getAssets(),
                        "font/Roboto-Regular.ttf");
                Typeface font1 = Typeface.createFromAsset(
                        getAssets(),
                        "font/Roboto-Thin.ttf");

                title.setTypeface(font);
                descriprtion.setTypeface(font);
            }
        }



    }
    public class LaundryIRD_ItemsAdapter extends  RecyclerView.Adapter<LaundryIRD_ItemsAdapter.ViewHolder>{
        ArrayList<Laundry_item> order_items;
        ArrayList<IRD_Addons> continentList;
        Context context;
        String category;
        String enabled;
        String enabled1;

        public LaundryIRD_ItemsAdapter(ArrayList<Laundry_item> order_items, Context context,String category,String enabled) {
            this.order_items = order_items;
            this.context = context;
            this.category=category;
            this.enabled=enabled;
        }

        @NonNull
        @Override
        public LaundryIRD_ItemsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.laundry_item, parent, false);
            return new LaundryIRD_ItemsAdapter.ViewHolder(view);
        }
        public void filterList(ArrayList<Laundry_item> filterdNames) {
            this.order_items = filterdNames;
            notifyDataSetChanged();
        }
        @Override
        public void onBindViewHolder(@NonNull final LaundryIRD_ItemsAdapter.ViewHolder holder, int position) {
            holder.mitem=order_items.get(position);
            holder.title.setText(holder.mitem.getName());
            holder.category1.setText(category);
            holder.price.setText(getResources().getString(R.string.Rs));
            holder.price1.setText(holder.mitem.getPrice());
            holder.expressprice.setText(getResources().getString(R.string.Rs));
            holder.expressprice1.setText(holder.mitem.getExpress_delivery_price());
            if(!holder.mitem.getDescription().equals("null")){
                holder.ingredinets.setText(holder.mitem.getDescription());

            }
            else{
                holder.ingredinets.setText("");

            }
            if(holder.mitem.getIs_express_delivery().equals("1")){
                holder.express.setVisibility(View.VISIBLE);
                holder.expressprice1.setVisibility(View.VISIBLE);
                holder.expressprice.setVisibility(View.VISIBLE);

            }
            else{
                holder.express.setVisibility(View.GONE);
                holder.expressprice1.setVisibility(View.GONE);
                holder.expressprice.setVisibility(View.GONE);
            }
            if(enabled.equals("1")){
                holder.toggleButton1.setEnabled(true);
                holder.toggleButton1.setClickable(true);
                if (holder.mitem.getEnabled().equals("1")) {
                    holder.toggleButton1.setChecked(true);
                    holder.gradeout.setVisibility(View.GONE);
                } else {
                    holder.toggleButton1.setChecked(false);
                    holder.gradeout.setVisibility(View.VISIBLE);

                }


            }
            else{

                holder.toggleButton1.setChecked(false);
                holder.toggleButton1.setEnabled(false);
                holder.toggleButton1.setClickable(false);
                holder.gradeout.setVisibility(View.VISIBLE);
            }



            if (!holder.mitem.getThumbnail().equals("null")) {
                // byte[] decodedString = Base64.decode(holder.mitem.getImage(), Base64.DEFAULT);
             /*   String base64Image = holder.mitem.getThumbnail().split(",")[1];

                byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                holder.dish.setImageBitmap(decodedByte);*/
                Picasso.with(context).load(BuildConfig.imageurl+holder.mitem.getThumbnail()).into(holder.dish);
                //holder.dish.setVisibility(View.VISIBLE);
                holder.dish.setVisibility(View.VISIBLE);

            }
            else{
                holder.dish.setVisibility(View.GONE);
            }
            holder.category.setVisibility(View.GONE);

            if (!holder.mitem.getTags().equals("null")) {
                String strings=holder.mitem.getTags().replaceAll("/","").replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\"","");

                //Split string with comma

                String[] namesArray = strings.split(",");
                Arrays.asList(namesArray);
                LaundryTagsAdapter tagsAdapter = new LaundryTagsAdapter(namesArray, context);
                holder.spicy.setAdapter(tagsAdapter);
                holder.spicy.setVisibility(View.VISIBLE);

                // byte[] decodedString = Base64.decode(holder.mitem.getImage(), Base64.DEFAULT);
            }
            else{
                holder.spicy.setVisibility(View.GONE);
            }


           /* if(holder.mitem.getImage().toString().equals(null)||holder.mitem.getImage().toString().equals("")){
            }
            else {
                holder.dish.setVisibility(View.VISIBLE);
            } */
            holder.toggleButton1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){

                        holder.gradeout.setVisibility(View.GONE);
                        if (Network.isNetworkAvailable(context)){
                            LaundryGetMenuitemenab(holder.mitem.getId());
                        }
                        else if (Network.isNetworkAvailable2(context)) {

                            LaundryGetMenuitemenab(holder.mitem.getId());

                        }
                        else{

                        }
                    }
                    else{

                        holder.gradeout.setVisibility(View.VISIBLE);
                        if (Network.isNetworkAvailable(context)){
                            LaundryGetMenuitemdisable(holder.mitem.getId());

                        }
                        else if (Network.isNetworkAvailable2(context)) {
                            LaundryGetMenuitemdisable(holder.mitem.getId());


                        }
                        else{

                        }
                    }
                }
            });



        }

        @Override
        public int getItemCount() {
            return order_items.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView title;
            TextView category1;
            TextView price1;
            TextView price;
            TextView standard;
            TextView express;
            TextView expressprice1;
            TextView expressprice;
            TextView customise;
            TextView ingredinets;
            ImageView dish;
            ImageView category;
            RecyclerView spicy;
            RecyclerView customiselist;
            LinearLayout gradeout;
            ToggleButton toggleButton1;


            Laundry_item mitem;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                title=itemView.findViewById(R.id.title);
                category1=itemView.findViewById(R.id.category1);
                category=itemView.findViewById(R.id.category);
                price=itemView.findViewById(R.id.price);
                price1=itemView.findViewById(R.id.price1);
                ingredinets=itemView.findViewById(R.id.ingredinets);
                spicy=itemView.findViewById(R.id.spicy);
                spicy.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
                dish=itemView.findViewById(R.id.dish);
                express=itemView.findViewById(R.id.express);
                standard=itemView.findViewById(R.id.standard);
                expressprice1=itemView.findViewById(R.id.expressprice1);
                expressprice=itemView.findViewById(R.id.expressprice);
                gradeout=itemView.findViewById(R.id.gradeout);
                toggleButton1=itemView.findViewById(R.id.toggleButton1);

                final Typeface font = Typeface.createFromAsset(
                        getAssets(),
                        "font/Roboto-Regular.ttf");
                Typeface font1 = Typeface.createFromAsset(
                        getAssets(),
                        "font/Roboto-Thin.ttf");

                title.setTypeface(font1);
                category1.setTypeface(font);
                price1.setTypeface(font);
                price.setTypeface(font);
                expressprice.setTypeface(font);
                expressprice1.setTypeface(font);
                ingredinets.setTypeface(font1);
            }
        }



    }
   /* public class LaundryIRD_SubAddonAdapter extends  RecyclerView.Adapter<LaundryIRD_SubAddonAdapter.ViewHolder>{
        ArrayList<IRD_Addons> order_items;
        ArrayList<IRD_AddonsItem> continentList;
        Context context;
        String category;
        String enabled;
        String enabled1;
        String enabled2;
        String enabled3;
        ArrayList<IRD_AddonsItem> ird_itemArrayList;

        public LaundryIRD_SubAddonAdapter(ArrayList<IRD_Addons> order_items, Context context,String category,String enabled,String enabled1,String enabled2) {
            this.order_items = order_items;
            this.context = context;
            this.category=category;
            this.enabled=enabled;
            this.enabled1=enabled1;
            this.enabled2=enabled2;
            continentList=new ArrayList<>();
        }

        @NonNull
        @Override
        public LaundryIRD_SubAddonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.irdmenusubcategory, parent, false);
            return new LaundryIRD_SubAddonAdapter.ViewHolder(view);
        }
        public void filterList(ArrayList<IRD_Addons> filterdNames) {
            this.order_items = filterdNames;
            notifyDataSetChanged();
        }
        @Override
        public void onBindViewHolder(@NonNull final LaundryIRD_SubAddonAdapter.ViewHolder holder, int position) {
            holder.mitem=order_items.get(position);
            if(holder.mitem.getName().equals("")){
                holder.parent.setVisibility(View.GONE);
            }
            else{
                holder.parent.setVisibility(View.VISIBLE);
                holder.title.setText(holder.mitem.getName()+" ( "+String.valueOf(holder.mitem.getAddons().length()+" ) "));


            }
            enabled3=holder.mitem.getEnabled();

            if(!holder.mitem.getDescription().toString().equals("null")) {
                holder.descriprtion.setText(holder.mitem.getDescription());
            }
            else{
                holder.descriprtion.setText("");

            }

            if(enabled.equals("1")){
                if(enabled1.equals("1")) {
                    if(enabled2.equals("1")) {
                        holder.toggleButton1.setEnabled(true);
                        holder.toggleButton1.setClickable(true);
                        if (holder.mitem.getEnabled().equals("1")) {
                            holder.toggleButton1.setChecked(true);
                        } else {
                            holder.toggleButton1.setChecked(false);

                        }
                    }
                    else{
                        holder.toggleButton1.setChecked(false);
                        holder.toggleButton1.setEnabled(false);
                        holder.toggleButton1.setClickable(false);
                    }
                }
                else{

                    holder.toggleButton1.setChecked(false);
                    holder.toggleButton1.setEnabled(false);
                    holder.toggleButton1.setClickable(false);
                }
            }
            else{

                holder.toggleButton1.setChecked(false);
                holder.toggleButton1.setEnabled(false);
                holder.toggleButton1.setClickable(false);
            }



            holder.toggleButton1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){

                        if (Network.isNetworkAvailable(context)){
                            MinibarGetMenuaddoncategoryenable(holder.mitem.getId());
                            enabled3="1";

                        }
                        else if (Network.isNetworkAvailable2(context)) {

                            MinibarGetMenuaddoncategoryenable(holder.mitem.getId());
                            enabled3="1";

                        }
                        else{

                        }
                    }
                    else{


                        if (Network.isNetworkAvailable(context)){
                            MinibarGetMenuaddoncategorydisable(holder.mitem.getId());
                            enabled3="0";

                        }
                        else if (Network.isNetworkAvailable2(context)) {
                            MinibarGetMenuaddoncategorydisable(holder.mitem.getId());
                            enabled3="0";


                        }
                        else{

                        }
                    }
                }
            });
            ird_itemArrayList=new ArrayList<>();

            for(int i=0;i<holder.mitem.getAddons().length();i++){
                try {
                    JSONObject jsonObject=holder.mitem.getAddons().getJSONObject(i);
                    IRD_AddonsItem iRd_subCategory=new IRD_AddonsItem();
                    iRd_subCategory.setId(jsonObject.getString("id"));
                    iRd_subCategory.setMenu_item_id(jsonObject.getString("menu_item_id"));
                    iRd_subCategory.setName(jsonObject.getString("name"));
                    iRd_subCategory.setDescription(jsonObject.getString("description"));
                    iRd_subCategory.setTags(jsonObject.getString("tags"));
                    iRd_subCategory.setEnabled(jsonObject.getString("enabled"));
                    iRd_subCategory.setCreated_at(jsonObject.getString("created_at"));
                    iRd_subCategory.setUpdated_at(jsonObject.getString("updated_at"));
                    iRd_subCategory.setType(jsonObject.getString("type"));
                    iRd_subCategory.setPrice(jsonObject.getString("price"));
                    iRd_subCategory.setMax_per_order(jsonObject.getString("max_per_order"));
                    iRd_subCategory.setMin_per_order(jsonObject.getString("min_per_order"));
                    iRd_subCategory.setThumbnail(jsonObject.getString("thumbnail"));
                    iRd_subCategory.setMenu_item_subaddon_id(jsonObject.getString("menu_item_subaddon_id"));
                    ird_itemArrayList.add(iRd_subCategory);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            MinibarIRD_ItemsWithoutAdapter order_itemAdapterdetail=new MinibarIRD_ItemsWithoutAdapter(ird_itemArrayList,context,holder.mitem.getName(),enabled,enabled1,enabled2,enabled3);
            holder.rv_child.setAdapter(order_itemAdapterdetail);

        }

        @Override
        public int getItemCount() {
            return order_items.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView title;
            TextView descriprtion;
            RecyclerView rv_child;
            ToggleButton toggleButton1;
            RelativeLayout parent;

            IRD_Addons mitem;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                title=itemView.findViewById(R.id.category);
                descriprtion=itemView.findViewById(R.id.descriprtion);
                parent=itemView.findViewById(R.id.parent);
                rv_child=itemView.findViewById(R.id.rv_child);
                rv_child.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
                toggleButton1=itemView.findViewById(R.id.toggleButton1);

                final Typeface font = Typeface.createFromAsset(
                        getAssets(),
                        "font/Roboto-Regular.ttf");
                Typeface font1 = Typeface.createFromAsset(
                        getAssets(),
                        "font/Roboto-Thin.ttf");

                title.setTypeface(font);
                descriprtion.setTypeface(font);
            }
        }



    }

    public class LaundryIRD_ItemsWithoutAdapter extends  RecyclerView.Adapter<LaundryIRD_ItemsWithoutAdapter.ViewHolder>{
        ArrayList<IRD_AddonsItem> order_items;
        ArrayList<Menu.Items> continentList;
        Context context;
        String category;
        String enabled;
        String enabled1;
        String enabled2;
        String enabled3;
        String enabled4;

        public LaundryIRD_ItemsWithoutAdapter(ArrayList<IRD_AddonsItem> order_items, Context context,String category,String enabled,String enabled1,String enabled2,String enabled3) {
            this.order_items = order_items;
            this.context = context;
            this.category=category;
            this.enabled=enabled;
            this.enabled1=enabled1;
            this.enabled2=enabled2;
            this.enabled3=enabled3;
            continentList=new ArrayList<>();
        }

        @NonNull
        @Override
        public LaundryIRD_ItemsWithoutAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.addon_item, parent, false);
            return new LaundryIRD_ItemsWithoutAdapter.ViewHolder(view);
        }
        public void filterList(ArrayList<IRD_AddonsItem> filterdNames) {
            this.order_items = filterdNames;
            notifyDataSetChanged();
        }
        @Override
        public void onBindViewHolder(@NonNull final LaundryIRD_ItemsWithoutAdapter.ViewHolder holder, int position) {
            holder.mitem=order_items.get(position);
            holder.title.setText(holder.mitem.getName());
            holder.category1.setText(category);
            holder.price.setText(getResources().getString(R.string.Rs));
            holder.price1.setText(holder.mitem.getPrice());
            if(!holder.mitem.getDescription().equals("null")){
                holder.ingredinets.setText(holder.mitem.getDescription());
            }
            else{
                holder.ingredinets.setText("");
                holder.ingredinets.setVisibility(View.GONE);

            }
            if(enabled.equals("1")){
                if(enabled1.equals("1")) {
                    if(enabled2.equals("1")) {
                        if(enabled3.equals("1")) {
                            holder.toggleButton1.setEnabled(true);
                            holder.toggleButton1.setClickable(true);
                            if (holder.mitem.getEnabled().equals("1")) {
                                holder.toggleButton1.setChecked(true);
                            } else {
                                holder.toggleButton1.setChecked(false);

                            }
                        }
                        else{
                            holder.toggleButton1.setChecked(false);
                            holder.toggleButton1.setEnabled(false);
                            holder.toggleButton1.setClickable(false);
                        }
                    }
                    else{
                        holder.toggleButton1.setChecked(false);
                        holder.toggleButton1.setEnabled(false);
                        holder.toggleButton1.setClickable(false);
                    }
                }
                else{

                    holder.toggleButton1.setChecked(false);
                    holder.toggleButton1.setEnabled(false);
                    holder.toggleButton1.setClickable(false);
                }
            }
            else{

                holder.toggleButton1.setChecked(false);
                holder.toggleButton1.setEnabled(false);
                holder.toggleButton1.setClickable(false);
            }
            if(holder.mitem.getType().equals("veg")){
                holder.category.setImageResource(R.mipmap.veggg);
            }
            else{
                holder.category.setImageResource(R.mipmap.nonveggg);

            }

            if (!holder.mitem.getThumbnail().equals("null") ) {
                // byte[] decodedString = Base64.decode(holder.mitem.getImage(), Base64.DEFAULT);
             *//*   String base64Image = holder.mitem.getThumbnail().split(",")[1];

                byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                holder.dish.setImageBitmap(decodedByte);*//*
                Picasso.with(context).load(BuildConfig.imageurl+holder.mitem.getThumbnail()).into(holder.dish);
                //holder.dish.setVisibility(View.VISIBLE);
                holder.dish.setVisibility(View.VISIBLE);

            }
            else{
                holder.dish.setVisibility(View.GONE);
            }
            holder.toggleButton1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){

                        holder.gradeout.setVisibility(View.GONE);
                        if (Network.isNetworkAvailable(context)){
                            MinibarGetMenuaddenab(holder.mitem.getId());
                        }
                        else if (Network.isNetworkAvailable2(context)) {

                            MinibarGetMenuaddenab(holder.mitem.getId());
                        }
                        else{

                        }
                    }
                    else{

                        holder.gradeout.setVisibility(View.VISIBLE);
                        if (Network.isNetworkAvailable(context)){
                            MinibarGetMenuadddisable(holder.mitem.getId());
                        }
                        else if (Network.isNetworkAvailable2(context)) {
                            MinibarGetMenuadddisable(holder.mitem.getId());

                        }
                        else{

                        }
                    }
                }
            });

        }

        @Override
        public int getItemCount() {
            return order_items.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView title;
            TextView category1;
            TextView price1;
            TextView price;
            TextView ingredinets;
            ImageView dish;
            ImageView category;
            LinearLayout gradeout;
            ToggleButton toggleButton1;


            IRD_AddonsItem mitem;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                title=itemView.findViewById(R.id.title);
                category1=itemView.findViewById(R.id.category1);
                category=itemView.findViewById(R.id.category);
                price=itemView.findViewById(R.id.price);
                price1=itemView.findViewById(R.id.price1);
                ingredinets=itemView.findViewById(R.id.ingredinets);
                dish=itemView.findViewById(R.id.dish);
                gradeout=itemView.findViewById(R.id.gradeout);
                toggleButton1=itemView.findViewById(R.id.toggleButton1);

                final Typeface font = Typeface.createFromAsset(
                        getAssets(),
                        "font/Roboto-Regular.ttf");
                Typeface font1 = Typeface.createFromAsset(
                        getAssets(),
                        "font/Roboto-Thin.ttf");

                title.setTypeface(font1);
                category1.setTypeface(font);
                price1.setTypeface(font);
                price.setTypeface(font);
                ingredinets.setTypeface(font1);
            }
        }



    }
*/

    public class LaundryTagsAdapter extends  RecyclerView.Adapter<LaundryTagsAdapter.ViewHolder>{
        String[] order_items;
        Context context;
        String enabled;

        public LaundryTagsAdapter(String[] order_items, Context context) {
            this.order_items = order_items;
            this.context = context;
        }

        @NonNull
        @Override
        public LaundryTagsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout, parent, false);
            return new LaundryTagsAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final LaundryTagsAdapter.ViewHolder holder, int position) {


            holder.title.setText(capitailizeWord(order_items[position]));
            if(order_items[position].equals("Non-veg")||order_items[position].equals("non-veg")||order_items[position].equals("NON-VEG")){
                holder.title.setChipBackgroundColor(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));

            }
            else {
                int[] androidColors = getResources().getIntArray(R.array.androidcolors);
                int randomAndroidColor = androidColors[new Random().nextInt(androidColors.length)];
                holder.title.setChipBackgroundColor(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));
            }

        }

        @Override
        public int getItemCount() {
            return order_items.length;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            Chip title;
            String[] mitem;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                title=itemView.findViewById(R.id.chipentry);


                final Typeface font = Typeface.createFromAsset(
                        getAssets(),
                        "font/Roboto-Regular.ttf");
                Typeface font1 = Typeface.createFromAsset(
                        getAssets(),
                        "font/Roboto-Thin.ttf");

                title.setTypeface(font);

            }
        }



    }

    public class LaundryCartegoryAdapter extends  RecyclerView.Adapter<LaundryCartegoryAdapter.ViewHolder>{
        ArrayList<Laundry_Category> order_items;
        Context context;

        public LaundryCartegoryAdapter(ArrayList<Laundry_Category> order_items, Context context) {
            this.order_items = order_items;
            this.context = context;
        }

        @NonNull
        @Override
        public LaundryCartegoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.category_item, parent, false);
            return new LaundryCartegoryAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull LaundryCartegoryAdapter.ViewHolder holder, final int position) {
            holder.mitem=order_items.get(position);
            holder.title.setText(holder.mitem.getName());
            holder.category1.setText(String.valueOf(holder.mitem.getSub_categories().length()));


        }

        @Override
        public int getItemCount() {
            return order_items.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView title;
            TextView category1;



            Laundry_Category mitem;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                title=itemView.findViewById(R.id.title);
                category1=itemView.findViewById(R.id.count);

                final Typeface font = Typeface.createFromAsset(
                        getAssets(),
                        "font/Roboto-Regular.ttf");
                Typeface font1 = Typeface.createFromAsset(
                        getAssets(),
                        "font/Roboto-Thin.ttf");

                title.setTypeface(font);
                category1.setTypeface(font);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        shimmerRecyclerView.smoothScrollToPosition(getAdapterPosition());
                        menudialog.dismiss();

                    }
                });

            }
        }



    }

    //-------------End id Laundaryadapter------------------------------//


}
