package com.viralops.touchlessfoodordering.ui.history;

import android.content.Context;
import android.graphics.Typeface;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.viralops.touchlessfoodordering.R;
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
        if(holder.mitem.getStatus().equals("2")){
            holder.colorimage.setBackgroundColor(context.getResources().getColor(R.color.red));
            holder.orderstatus.setTextColor(context.getResources().getColor(R.color.gray));
            holder.roomno.setTextColor(context.getResources().getColor(R.color.mehroon));
            holder.guests.setTextColor(context.getResources().getColor(R.color.mehroon));

        }
        else if(holder.mitem.getStatus().equals("3")){
            holder.colorimage.setBackgroundColor(context.getResources().getColor(R.color.light_green));
            holder.orderstatus.setTextColor(context.getResources().getColor(R.color.gray));
            holder.roomno.setTextColor(context.getResources().getColor(R.color.mogiya));
            holder.guests.setTextColor(context.getResources().getColor(R.color.mogiya));



        }
        else{
            holder.colorimage.setBackgroundColor(context.getResources().getColor(R.color.lightgrey));
            holder.orderstatus.setTextColor(context.getResources().getColor(R.color.gray));
            holder.roomno.setTextColor(context.getResources().getColor(R.color.gray));
            holder.guests.setTextColor(context.getResources().getColor(R.color.gray));


        }


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


         }


     }

    private String getDate(long time) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time * 1000);
        String date = DateFormat.format("hh:mm a", cal).toString();
        return date;
    }

}
