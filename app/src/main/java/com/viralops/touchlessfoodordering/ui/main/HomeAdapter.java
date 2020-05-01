package com.viralops.touchlessfoodordering.ui.main;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.SystemClock;
import android.text.format.DateFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.viralops.touchlessfoodordering.R;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.viewholder>{
    ArrayList<HomeViewModel.Data> homeViewModels;
    Context context;
    private Handler mHandler = new Handler();
    ArrayList<HomeAdapter.viewholder> lstHolders;

    Timer tmr;
    private Runnable updateRemainingTimeRunnable = new Runnable() {
        @SuppressLint("SetTextI18n")
        @Override
        public void run() {
            synchronized (lstHolders) {
                long currentTime = SystemClock.elapsedRealtime();
                for (HomeAdapter.viewholder holder : lstHolders) {

                    holder.updateTimeRemaining(currentTime);


                }
            }
        }
    };

    private void startUpdateTimer() {
        tmr = new Timer();
        tmr.schedule(new TimerTask() {
            @Override
            public void run() {
                mHandler.post(updateRemainingTimeRunnable);
            }
        }, 1000, 1000);
    }
    private void stoptimer() {
        tmr.cancel();

    }

    public HomeAdapter(Context context,ArrayList<HomeViewModel.Data> homeViewModels) {
        this.context=context;
        this.homeViewModels=homeViewModels;
        lstHolders = new ArrayList<>();

        startUpdateTimer();
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.queuelist, parent, false);

        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final viewholder holder, int position) {
        holder.mitem=homeViewModels.get(position);
        holder.roomno.setText(holder.mitem.getRoom_no());
        holder.guests.setText(holder.mitem.getNo_guest());
       // holder.orderrecived.setText(holder.mitem.getOrderreceived());

            holder.orderstatus.setText("In Kitchen");



            holder.instaruction.setText(holder.mitem.getDetails());

        holder.since.setText(getDate(holder.mitem.getCreated_at()));
        if(holder.mitem.getStatus().equals("New Order")){
            holder.colorimage.setBackgroundColor(context.getResources().getColor(R.color.red));
            holder.orderstatus.setTextColor(context.getResources().getColor(R.color.gray));
            holder.since.setTextColor(context.getResources().getColor(R.color.gray));
            holder.roomno.setTextColor(context.getResources().getColor(R.color.mehroon));

        }
        else{
            holder.colorimage.setBackgroundColor(context.getResources().getColor(R.color.light_green));
            holder.orderstatus.setTextColor(context.getResources().getColor(R.color.gray));
            holder.since.setTextColor(context.getResources().getColor(R.color.gray));
            holder.roomno.setTextColor(context.getResources().getColor(R.color.mogiya));


        }
         if(holder.mitem.getItems().size()>4){
             holder.more.setVisibility(View.VISIBLE);
             holder.more.setText(String.valueOf(holder.mitem.getItems().size()-4));
         }
         else{
             holder.more.setVisibility(View.GONE);

         }

        Order_ItemAdapter1 order_itemAdapter1=new Order_ItemAdapter1(holder.mitem.getItems(),context);
        holder.recyclerView.setAdapter(order_itemAdapter1);

        holder.dispatch.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {




            }
        });

        holder.setData(holder.mitem);
        synchronized (lstHolders) {
            lstHolders.add(holder);
        }
        holder.updateTimeRemaining(SystemClock.elapsedRealtime());
    }

    @Override
    public int getItemCount() {
        return homeViewModels.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
         public TextView roomno;
         public TextView guests;
         public TextView orderrecived;
         public  TextView orderstatus;
         public TextView since;
         private TextView dispatch;
         private TextView instaruction;
         RecyclerView recyclerView;
         LinearLayout colorimage;
         HomeViewModel.Data mitem;
         private TextView more;


         public viewholder(@NonNull View itemView) {
             super(itemView);
             roomno=itemView.findViewById(R.id.roomno);
             guests=itemView.findViewById(R.id.guest);
             orderrecived=itemView.findViewById(R.id.orderreceived);
             orderstatus=itemView.findViewById(R.id.status);
             since=itemView.findViewById(R.id.since);
             colorimage=itemView.findViewById(R.id.colorimage);
             dispatch=itemView.findViewById(R.id.dispatch);
             recyclerView=itemView.findViewById(R.id.ordeitems);
             more=itemView.findViewById(R.id.more);
             instaruction=itemView.findViewById(R.id.instrctions);
             recyclerView.setLayoutManager(new GridLayoutManager(context,2));

             final Typeface font = Typeface.createFromAsset(
                     context.getAssets(),
                     "font/Roboto-Regular.ttf");
             dispatch.setTypeface(font);

             Typeface font1 = Typeface.createFromAsset(
                     context.getAssets(),
                     "font/verdana.ttf");
             more.setTypeface(font1);
             since.setTypeface(font1);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context);
                // Include dialog.xml file

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                // dialog.getWindow().setLayout(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.FILL_PARENT);            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.detail_popup);
                int width1 = (int)(context.getResources().getDisplayMetrics().widthPixels*0.38);
                int height1 = (int)(context.getResources().getDisplayMetrics().heightPixels*0.90);
                dialog.getWindow().setGravity(Gravity.CENTER_VERTICAL);

                dialog.getWindow().setLayout(width1, height1);

                dialog.setCancelable(false);
                // Set dialog title
                dialog.setTitle("");
                dialog.show();
                ImageView back=dialog.findViewById(R.id.back);
                TextView title=dialog.findViewById(R.id.title);
                title.setTypeface(font);
                TextView roomno=dialog.findViewById(R.id.roomno);
                TextView guests=dialog.findViewById(R.id.guest);
                TextView since=dialog.findViewById(R.id.since);
                TextView orderitemsdetailtext=dialog.findViewById(R.id.orderitemsdetailtext);
                orderitemsdetailtext.setTypeface(font);
                TextView orderins=dialog.findViewById(R.id.orderins);
                orderins.setTypeface(font);
                TextView orderinsdetails=dialog.findViewById(R.id.orderinsdetails);
                RecyclerView orderitemsdetail=dialog.findViewById(R.id.orderitemsdetail);
                orderitemsdetail.setLayoutManager(new GridLayoutManager(context,2));
                roomno.setText(homeViewModels.get(getAdapterPosition()).getRoom_no());
                guests.setText(homeViewModels.get(getAdapterPosition()).getNo_guest());
                since.setText(getDate(homeViewModels.get(getAdapterPosition()).getCreated_at()));
                orderinsdetails.setText(homeViewModels.get(getAdapterPosition()).getDetails());
                Order_ItemAdapterdetail order_itemAdapterdetail=new Order_ItemAdapterdetail(homeViewModels.get(getAdapterPosition()).getItems(),context);
                orderitemsdetail.setAdapter(order_itemAdapterdetail);
                LinearLayout colorimage=dialog.findViewById(R.id.colorimage);
                 guests.setText(homeViewModels.get(getAdapterPosition()).getNo_guest());

                if(homeViewModels.get(getAdapterPosition()).getStatus().equals("2")){
                    colorimage.setBackgroundColor(context.getResources().getColor(R.color.red));
                    since.setTextColor(context.getResources().getColor(R.color.gray));
                    roomno.setTextColor(context.getResources().getColor(R.color.mehroon));

                }
                else if(homeViewModels.get(getAdapterPosition()).getStatus().equals("3")){
                    colorimage.setBackgroundColor(context.getResources().getColor(R.color.light_green));
                    since.setTextColor(context.getResources().getColor(R.color.gray));
                    roomno.setTextColor(context.getResources().getColor(R.color.mogiya));


                }
                else{
                    colorimage.setBackgroundColor(context.getResources().getColor(R.color.lightgrey));
                    since.setTextColor(context.getResources().getColor(R.color.gray));
                    roomno.setTextColor(context.getResources().getColor(R.color.gray));


                }
                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

            }
        });
         }
        public void setData(HomeViewModel.Data item) {
            mitem = item;
            updateTimeRemaining(SystemClock.elapsedRealtime());



        }

        public void updateTimeRemaining(long currentTime) {
            try {


                long newtime = mitem.getTimediffrence() + currentTime;
                long secondsInMilli = 1000;
                long minutesInMilli = secondsInMilli * 60;
                long hoursInMilli = minutesInMilli * 60;
                long daysInMilli = hoursInMilli * 24;

                int elapsedDays = (int) (newtime / daysInMilli);
                newtime = newtime % daysInMilli;

                int hours = (int) (newtime / hoursInMilli);
                newtime = newtime % hoursInMilli;

                int minutes = (int) (newtime / minutesInMilli);
                newtime = newtime % minutesInMilli;

                int seconds = (int) (newtime / secondsInMilli);
                if (elapsedDays == 0) {

                    if (hours == 00 || hours == 0) {
                        if (minutes < 30) {
                            orderrecived.setText(String.valueOf(minutes) + " m : " + String.valueOf(seconds) + " s");
                            orderstatus.setTextColor(Color.parseColor("#4b8b3b"));
                            orderrecived.setTextColor(Color.parseColor("#000000"));
                            orderstatus.setText(mitem.getStatus());


                        } else {
                            orderrecived.setText(String.valueOf(minutes) + " m : " + String.valueOf(seconds) + " s");
                            orderstatus.setTextColor(Color.parseColor("#ff0000"));
                            orderrecived.setTextColor(Color.parseColor("#000000"));
                            orderstatus.setText(mitem.getStatus());

                        }

                    } else if (hours == 01 || hours == 1) {
                        orderrecived.setText(String.valueOf(hours) + " hr : " + String.valueOf(minutes) + " m : " + String.valueOf(seconds) + " s");
                        orderstatus.setTextColor(Color.parseColor("#ff0000"));
                        orderrecived.setTextColor(Color.parseColor("#000000"));
                        orderstatus.setText(mitem.getStatus());

                    } else {
                        orderrecived.setText(String.valueOf(hours) + " hrs : " + String.valueOf(minutes) + " m : " + String.valueOf(seconds) + " s");
                        orderstatus.setTextColor(Color.parseColor("#ff0000"));
                        orderrecived.setTextColor(Color.parseColor("#000000"));
                        orderstatus.setText(mitem.getStatus());
                    }
                } else {
                    if (elapsedDays == 1 || elapsedDays == 01) {
                        orderrecived.setText(String.valueOf(elapsedDays) + " Day");
                        orderstatus.setTextColor(Color.parseColor("#ff0000"));
                        orderrecived.setTextColor(Color.parseColor("#000000"));
                        orderstatus.setText(mitem.getStatus());
                    } else {
                        orderrecived.setText(String.valueOf(elapsedDays) + " Days");
                        orderstatus.setTextColor(Color.parseColor("#ff0000"));
                        orderrecived.setTextColor(Color.parseColor("#000000"));
                        orderstatus.setText(mitem.getStatus());
                    }
                }
            }catch (Exception e){

            }
        }

     }

    public class Order_ItemAdapter1 extends  RecyclerView.Adapter<Order_ItemAdapter1.ViewHolder>{
        ArrayList<HomeViewModel.Items> order_items;
        Context context;

        public Order_ItemAdapter1(ArrayList<HomeViewModel.Items> order_items, Context context) {
            this.order_items = order_items;
            this.context = context;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.order_itemvalue, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.mitem=order_items.get(position);
            holder.name.setText(holder.mitem.getDetails());
            holder.quantity.setText(holder.mitem.getCount());

        }

        @Override
        public int getItemCount() {
            return order_items.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView name;
            TextView quantity;
            HomeViewModel.Items mitem;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                name=itemView.findViewById(R.id.name);
                quantity=itemView.findViewById(R.id.quantity);
                Typeface font = Typeface.createFromAsset(
                        context.getAssets(),
                        "font/verdana.ttf");
                name.setTypeface(font);
                quantity.setTypeface(font);
            }
        }



    }
    public class Order_ItemAdapterdetail extends  RecyclerView.Adapter<Order_ItemAdapterdetail.ViewHolder>{
        ArrayList<HomeViewModel.Items> order_items;
        Context context;

        public Order_ItemAdapterdetail(ArrayList<HomeViewModel.Items> order_items, Context context) {
            this.order_items = order_items;
            this.context = context;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.order_items, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.mitem=order_items.get(position);
            holder.name.setText(holder.mitem.getCount()+" X "+holder.mitem.getDetails());

        }

        @Override
        public int getItemCount() {
            return order_items.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView name;
            HomeViewModel.Items mitem;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                name=itemView.findViewById(R.id.name);
                Typeface font = Typeface.createFromAsset(
                        context.getAssets(),
                        "font/verdana.ttf");
                name.setTypeface(font);
            }
        }



    }
    private String getDate(long time) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time * 1000);
        String date = DateFormat.format("HH:mm", cal).toString();
        return date;
    }
}
