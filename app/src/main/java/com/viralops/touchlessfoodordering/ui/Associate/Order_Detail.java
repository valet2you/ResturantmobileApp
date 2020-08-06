package com.viralops.touchlessfoodordering.ui.Associate;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonArray;
import com.viralops.touchlessfoodordering.BuildConfig;
import com.viralops.touchlessfoodordering.R;
import com.viralops.touchlessfoodordering.ui.API.RetrofitClientInstance;
import com.viralops.touchlessfoodordering.ui.Laundry.HomeAdapter;
import com.viralops.touchlessfoodordering.ui.Laundry.Laundry_Dashboard;
import com.viralops.touchlessfoodordering.ui.Support.Network;
import com.viralops.touchlessfoodordering.ui.Support.SessionManager;
import com.viralops.touchlessfoodordering.ui.model.Action;
import com.viralops.touchlessfoodordering.ui.model.OrderHistory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Order_Detail extends AppCompatActivity {

    private Typeface font;
    SessionManager sessionManager;
    ArrayList<Laundry_Dashboard.Order_laundry_items> order_laundry_items=new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.parseColor("#091F42"));
        }
        setContentView(R.layout.ordee_detail);
        sessionManager=new SessionManager(getApplicationContext());
        font = Typeface.createFromAsset(
                getAssets(),
                "font/Roboto-Regular.ttf");
        ImageView back=findViewById(R.id.back);
        TextView title=findViewById(R.id.date);
        title.setTypeface(font);
        TextView roomno=findViewById(R.id.roomno);
        TextView guests=findViewById(R.id.guest);
        TextView since=findViewById(R.id.since);
        TextView orderitemsdetailtext=findViewById(R.id.orderitemsdetailtext);
        orderitemsdetailtext.setTypeface(font);
        TextView orderins=findViewById(R.id.orderins);
        orderins.setTypeface(font);
        TextView orderinsdetails=findViewById(R.id.orderinsdetails);
        TextView acceptedat=findViewById(R.id.accepttext);
        acceptedat.setTypeface(font);
        TextView accepted=findViewById(R.id.accepted);
        TextView dispatchbutton=findViewById(R.id.dispatch);
        if(getIntent().getStringExtra("status").equals("new_order")){
            dispatchbutton.setText("ACCEPT");
            guests.setText("New Order");
            guests.setTextColor(getResources().getColor(R.color.mehroon));


        }
        else{
            dispatchbutton.setText("CLEAR");
            guests.setText("Accepted");
            guests.setTextColor(getResources().getColor(R.color.mogiya));

        }


        RecyclerView orderitemsdetail=findViewById(R.id.orderitemsdetail);
        orderitemsdetail.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        roomno.setText(getIntent().getStringExtra("roomno"));
        since.setText(getDate1(getIntent().getStringExtra("created")));

        orderinsdetails.setText(getDate1(getIntent().getStringExtra("pickuptime")));
       String order=getIntent().getStringExtra("orderitems");

        // order_laundry_items = (ArrayList<Laundry_Dashboard.Order_laundry_items>)getIntent().getSerializableExtra("orderitems");
      // Order_ItemAdapterdetail order_itemAdapterdetail=new Order_ItemAdapterdetail(order_laundry_items,Order_Detail.this);
        //orderitemsdetail.setAdapter(order_itemAdapterdetail);
        LinearLayout colorimage=findViewById(R.id.colorimage);
        if(getIntent().getStringExtra("status").equals("new_order")){
            accepted.setText("-");
        }
        else{
            accepted.setText(getDate1(getIntent().getStringExtra("accepted")));

        }

        if(getIntent().getStringExtra("status").equals("new_order")){
            colorimage.setBackgroundColor(getResources().getColor(R.color.red));
            since.setTextColor(getResources().getColor(R.color.gray));
            roomno.setTextColor(getResources().getColor(R.color.mehroon));

        }
        else{
            colorimage.setBackgroundColor(getResources().getColor(R.color.light_green));
            since.setTextColor(getResources().getColor(R.color.gray));
            roomno.setTextColor(getResources().getColor(R.color.mogiya));


        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });


        dispatchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getIntent().getStringExtra("status").equals("new_order")){
                    if(Network.isNetworkAvailable(Order_Detail.this)){

                        setAccept(getIntent().getStringExtra("id"));
                        setDispatch(getIntent().getStringExtra("id"));

                    }
                    else if(Network.isNetworkAvailable2(Order_Detail.this)){

                        setAccept(getIntent().getStringExtra("id"));
                        setDispatch(getIntent().getStringExtra("id"));


                    }
                    else{

                    }

                }
                else{

                    //super.onBackPressed();
                    final Dialog dialog1 = new Dialog(Order_Detail.this);
                    // Include dialog.xml file

                    dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                    // dialog.getWindow().setLayout(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.FILL_PARENT);            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog1.setContentView(R.layout.delet_dialog);
                    int width1 = (int)(getResources().getDisplayMetrics().widthPixels*0.50);
                    int height1 = (int)(getResources().getDisplayMetrics().heightPixels*0.90);
                    dialog1.getWindow().setGravity(Gravity.CENTER_VERTICAL);

                    dialog1.getWindow().setLayout(width1, height1);

                    dialog1.setCancelable(false);
                    // Set dialog title
                    dialog1.setTitle("");
                    dialog1.show();
                    TextView textView=dialog1.findViewById(R.id.text) ;
                    textView.setText("Send to History?");
                    // String textstring="Do you confirm that room is cleared and trolley is back to IRD operation? or"+<b>
                    TextView confirm=dialog1.findViewById(R.id.cancel) ;
                    TextView cancel1=dialog1.findViewById(R.id.confirm);

                    confirm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            dialog1.dismiss();
                        }
                    });
                    cancel1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(Network.isNetworkAvailable(Order_Detail.this)){
                                setDispatch(getIntent().getStringExtra("id"));

                                setClear(getIntent().getStringExtra("id"));


                            }
                            else if(Network.isNetworkAvailable2(Order_Detail.this)){

                                setDispatch(getIntent().getStringExtra("id"));

                                setClear(getIntent().getStringExtra("id"));

                            }
                            else{

                            }
                        }
                    });


                }
            }
        });

    }
    private String getDate1(String time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = null;//You will get date object relative to server/client timezone wherever it is parsed
        try {
            date = dateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, hh:mm a");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
//If you need time just put specific format for time like 'HH:mm:ss'
        String dateStr = formatter.format(date);
        return dateStr;
    }
    private void setAccept(String id) {
        // display a progress dialog
/*
        String credentials = Credentials.basic("admin", "LetsValet2You");
*/

        (RetrofitClientInstance.getApiService().Laundry_OrderAccepted(BuildConfig.laundry_order_accept+id,"Bearer "+sessionManager.getACCESSTOKEN())).enqueue(new Callback<Action>() {
            @Override
            public void onResponse(@NonNull Call<Action> call, @NonNull Response<Action> response) {

                if(response.code()==202||response.code()==200){
                    Action login = response.body();
                    Toast.makeText(Order_Detail.this,login.getMessage(),Toast.LENGTH_SHORT).show();
                  finish();

                }
                else if(response.code()==401){
                    Action login = response.body();
                    Toast.makeText(Order_Detail.this, "Unauthorised", Toast.LENGTH_SHORT).show();
                    sessionManager.logoutsession();

                }
                else if(response.code()==500){
                    Toast.makeText(Order_Detail.this, "Something went wrong.", Toast.LENGTH_SHORT).show();

                }
                else{

                }




            }

            @Override
            public void onFailure(@NonNull Call<Action> call, @NonNull Throwable t) {
                Log.d("response", Arrays.toString(t.getStackTrace()));

            }
        });

    }
    private void setDispatch(String id) {
        // display a progress dialog
/*
        String credentials = Credentials.basic("admin", "LetsValet2You");
*/

        (RetrofitClientInstance.getApiService().Laundry_OrderDispatched(BuildConfig.laundry_order_dispatch+id,"Bearer "+sessionManager.getACCESSTOKEN())).enqueue(new Callback<Action>() {
            @Override
            public void onResponse(@NonNull Call<Action> call, @NonNull Response<Action> response) {

                if(response.code()==202||response.code()==200||response.code()==201){
                    Action login = response.body();
                    //  Toast.makeText(getActivity(),login.getMessage(),Toast.LENGTH_SHORT).show();


                }
                else if(response.code()==401){
                    Action login = response.body();
                    Toast.makeText(Order_Detail.this, "Unauthorised", Toast.LENGTH_SHORT).show();
                    sessionManager.logoutsession();

                }
                else if(response.code()==500){
                    Toast.makeText(Order_Detail.this, "Something went wrong.", Toast.LENGTH_SHORT).show();

                }
                else{

                }




            }

            @Override
            public void onFailure(@NonNull Call<Action> call, @NonNull Throwable t) {
                Log.d("response", Arrays.toString(t.getStackTrace()));

            }
        });

    }
    private void setClear(String id) {
        // display a progress dialog
/*
        String credentials = Credentials.basic("admin", "LetsValet2You");
*/

        (RetrofitClientInstance.getApiService().Laundry_OrderCleared(BuildConfig.laundry_order_clear+id,"Bearer "+sessionManager.getACCESSTOKEN())).enqueue(new Callback<Action>() {
            @Override
            public void onResponse(@NonNull Call<Action> call, @NonNull Response<Action> response) {

                if(response.code()==202||response.code()==200){
                    Action login = response.body();
                    Toast.makeText(Order_Detail.this,login.getMessage(),Toast.LENGTH_SHORT).show();
                   finish();

                }
                else if(response.code()==401){
                    Action login = response.body();
                    Toast.makeText(Order_Detail.this, "Unauthorised", Toast.LENGTH_SHORT).show();
                    sessionManager.logoutsession();

                }
                else if(response.code()==500){
                    Toast.makeText(Order_Detail.this, "Something went wrong.", Toast.LENGTH_SHORT).show();

                }
                else{

                }




            }

            @Override
            public void onFailure(@NonNull Call<Action> call, @NonNull Throwable t) {
                Log.d("response", Arrays.toString(t.getStackTrace()));

            }
        });

    }
    public class Order_ItemAdapterdetail extends  RecyclerView.Adapter<Order_ItemAdapterdetail.ViewHolder>{
        ArrayList<Laundry_Dashboard.Order_laundry_items> order_items;
        Context context;

        public Order_ItemAdapterdetail(ArrayList<Laundry_Dashboard.Order_laundry_items> order_items, Context context) {
            this.order_items = order_items;
            this.context = context;
        }

        @NonNull
        @Override
        public Order_ItemAdapterdetail.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.laundryorder_items, parent, false);
            return new Order_ItemAdapterdetail.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull Order_ItemAdapterdetail.ViewHolder holder, int position) {
            holder.mitem=order_items.get(position);
            holder.name.setText(holder.mitem.getQuantity()+" X "+holder.mitem.getItem().getName());
            holder.categorylist.setText(holder.mitem.getItem().getLaundry_category().getName());
            if(holder.mitem.getIs_express_delivery().equals("1")){
                holder.addonslist.setText("Express");
                holder.addonslist.setTextColor(Color.parseColor("#4CAF50"));
            }
            else{
                holder.addonslist.setText("Standard");
                holder.addonslist.setTextColor(context.getResources().getColor(R.color.redblood));

            }

        }

        @Override
        public int getItemCount() {
            return order_items.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView name;
            TextView categorylist;
            Laundry_Dashboard.Order_laundry_items mitem;
            TextView addonslist;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                name=itemView.findViewById(R.id.name);
                addonslist=itemView.findViewById(R.id.addonslist);
                categorylist=itemView.findViewById(R.id.categorylist);

                Typeface font = Typeface.createFromAsset(
                        context.getAssets(),
                        "font/verdana.ttf");
                name.setTypeface(font);

            }
        }



    }

}
