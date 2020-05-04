package com.viralops.touchlessfoodordering.ui.history;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.text.format.DateFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.viralops.touchlessfoodordering.R;
import com.viralops.touchlessfoodordering.ui.main.HomeViewModel;
import com.viralops.touchlessfoodordering.ui.main.MainFragment;
import com.viralops.touchlessfoodordering.ui.model.Order;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.viewholder>{
    ArrayList<Order.Data> homeViewModels;
    Context context;

    public HistoryAdapter(Context context, ArrayList<Order.Data> homeViewModels) {
        this.context=context;
        this.homeViewModels=homeViewModels;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_historylits, parent, false);

        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final viewholder holder, int position) {
        holder.mitem=homeViewModels.get(position);
        holder.roomno.setText(holder.mitem.getRoom_no());
        holder.guests.setText(holder.mitem.getNo_guest());
        holder.orderrecived.setText(getDate(holder.mitem.getCreated_at()));
        holder.orderstatus.setText(getDate(holder.mitem.getDispatched_at()));
        holder.acceptedat.setText(getDate(holder.mitem.getConfirm_at()));



            holder.colorimage.setBackgroundColor(context.getResources().getColor(R.color.light_green));
            holder.orderstatus.setTextColor(context.getResources().getColor(R.color.gray));
            holder.roomno.setTextColor(context.getResources().getColor(R.color.mogiya));
            holder.guests.setTextColor(context.getResources().getColor(R.color.mogiya));







    }
    public void filterList(ArrayList<Order.Data> filterdNames) {
        this.homeViewModels = filterdNames;
        notifyDataSetChanged();
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
         public  TextView acceptedat;

        LinearLayout colorimage;
        Order.Data mitem;



         public viewholder(@NonNull View itemView) {
             super(itemView);
             roomno=itemView.findViewById(R.id.roomno);
             guests=itemView.findViewById(R.id.guest);
             orderrecived=itemView.findViewById(R.id.orderreceivedtext);
             orderstatus=itemView.findViewById(R.id.deliveredat);
             acceptedat=itemView.findViewById(R.id.acceptedat);
             colorimage=itemView.findViewById(R.id.colorimage);

             final Typeface font = Typeface.createFromAsset(
                     context.getAssets(),
                     "font/Roboto-Regular.ttf");

             Typeface font1 = Typeface.createFromAsset(
                     context.getAssets(),
                     "font/Roboto-Thin.ttf");
             roomno.setTypeface(font1);
             guests.setTypeface(font1);
             orderrecived.setTypeface(font1);
             orderstatus.setTypeface(font1);
             acceptedat.setTypeface(font1);

             itemView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     final Dialog dialog = new Dialog(context);
                     // Include dialog.xml file

                     dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                     // dialog.getWindow().setLayout(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.FILL_PARENT);            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                     dialog.setContentView(R.layout.history_detail);
                     int width1 = (int)(context.getResources().getDisplayMetrics().widthPixels*0.48);
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
                     TextView acceptedat=dialog.findViewById(R.id.accepttext);
                     acceptedat.setTypeface(font);
                     TextView accepted=dialog.findViewById(R.id.accepted);
                     TextView distpatchtext=dialog.findViewById(R.id.distpatchtext);
                     distpatchtext.setTypeface(font);
                     TextView dispatcg=dialog.findViewById(R.id.dispactch);
                     RecyclerView orderitemsdetail=dialog.findViewById(R.id.orderitemsdetail);
                     orderitemsdetail.setLayoutManager(new GridLayoutManager(context,2));
                     roomno.setText(homeViewModels.get(getAdapterPosition()).getRoom_no());
                     guests.setText(homeViewModels.get(getAdapterPosition()).getNo_guest());
                     since.setText(getDate(homeViewModels.get(getAdapterPosition()).getCreated_at()));
                     if (homeViewModels.get(getAdapterPosition()).getDetails() != null) {
                         orderinsdetails.setText(homeViewModels.get(getAdapterPosition()).getDetails());

                     }
                     else{
                         orderinsdetails.setText("-");

                     }
                     Order_ItemAdapterdetail order_itemAdapterdetail=new Order_ItemAdapterdetail(homeViewModels.get(getAdapterPosition()).getItems(),context);
                     orderitemsdetail.setAdapter(order_itemAdapterdetail);
                     LinearLayout colorimage=dialog.findViewById(R.id.colorimage);
                     guests.setText(homeViewModels.get(getAdapterPosition()).getNo_guest());

                         accepted.setText(getDate(homeViewModels.get(getAdapterPosition()).getConfirm_at()));
                         dispatcg.setText(getDate(homeViewModels.get(getAdapterPosition()).getDispatched_at()));




                         colorimage.setBackgroundColor(context.getResources().getColor(R.color.light_green));
                         since.setTextColor(context.getResources().getColor(R.color.gray));
                         roomno.setTextColor(context.getResources().getColor(R.color.mogiya));



                     back.setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View v) {
                             dialog.dismiss();
                         }
                     });

                 }
             });
         }
        

     }

    private String getDate(long time) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time * 1000);
        String date = DateFormat.format("hh:mm a", cal).toString();
        return date;
    }
    public class Order_ItemAdapterdetail extends  RecyclerView.Adapter<Order_ItemAdapterdetail.ViewHolder>{
        ArrayList<Order.Items> order_items;
        Context context;

        public Order_ItemAdapterdetail(ArrayList<Order.Items> order_items, Context context) {
            this.order_items = order_items;
            this.context = context;
        }

        @NonNull
        @Override
        public Order_ItemAdapterdetail.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.order_items, parent, false);
            return new Order_ItemAdapterdetail.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull Order_ItemAdapterdetail.ViewHolder holder, int position) {
            holder.mitem=order_items.get(position);
            holder.name.setText(holder.mitem.getCount()+" X "+holder.mitem.getName());

        }

        @Override
        public int getItemCount() {
            return order_items.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView name;
            Order.Items mitem;

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

}
