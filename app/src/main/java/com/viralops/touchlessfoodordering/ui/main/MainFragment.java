package com.viralops.touchlessfoodordering.ui.main;

import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.TimeZoneNames;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.SystemClock;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AutoCompleteTextView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.todkars.shimmer.ShimmerRecyclerView;
import com.viralops.touchlessfoodordering.BuildConfig;
import com.viralops.touchlessfoodordering.MainActivity;
import com.viralops.touchlessfoodordering.R;
import com.viralops.touchlessfoodordering.ui.API.RetrofitClientInstance;
import com.viralops.touchlessfoodordering.ui.Support.Internetconnection;
import com.viralops.touchlessfoodordering.ui.Support.Network;
import com.viralops.touchlessfoodordering.ui.Support.SessionManager;
import com.viralops.touchlessfoodordering.ui.model.Action;
import com.viralops.touchlessfoodordering.ui.model.Header;
import com.viralops.touchlessfoodordering.ui.model.Menucategory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

import jp.wasabeef.recyclerview.animators.SlideInDownAnimator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    ShimmerRecyclerView shimmerRecyclerView;
    ArrayList<HomeViewModel.Data> queuelist=new ArrayList<>();
    ArrayList<Order_Item> orderlist;
    SessionManager sessionManager;
    TextView norecord;
    AutoCompleteTextView searchView;
    HomeAdapter homeAdapter;
    Animation animation;
    private SearchView.OnQueryTextListener queryTextListener;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.main_fragment, container, false);
        setHasOptionsMenu(true);
        homeAdapter=new HomeAdapter(getActivity(),queuelist);

        searchView =  view.findViewById(R.id.searchView);
        sessionManager=new SessionManager(getActivity());
        shimmerRecyclerView=view.findViewById(R.id.recyclerview);
        shimmerRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),5));
        norecord=view.findViewById(R.id.norecord);

        if(Network.isNetworkAvailable(getActivity())){
            GetMenu();

        }
        else if(Network.isNetworkAvailable2(getActivity())){
            GetMenu();

        }
        else{
            Intent intent=new Intent(getActivity(), Internetconnection.class);
            startActivity(intent);
            this.getActivity().finish();
        }

        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
        try {
            MainActivity.neworderss.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (queuelist.size() != 0) {
                        try {
                            if(MainActivity.imgBell.getVisibility()==v.VISIBLE) {
                                shimmerRecyclerView.smoothScrollToPosition(queuelist.size() - 1);
                                MainActivity.imgBell.setVisibility(View.GONE);
                            }
                            else{

                            }
                        } catch (Exception e) {

                        }
                    }
                }
            });
        }
        catch (Exception e){

        }
        shimmerRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if(MainActivity.imgBell.getVisibility()==View.GONE) {
                    GridLayoutManager layoutManager = GridLayoutManager.class.cast(recyclerView.getLayoutManager());
                    int lastItem = homeAdapter.getItemCount() - 1;
                    tryAnimation(layoutManager.findViewByPosition(lastItem));
                }
                else{

                }
            }
        });


        return view;

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);


        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main:
                // Not implemented here
                return false;
            default:
                break;
        }

        return false;
    }

    private void GetMenu() {
        // display a progress dialog
      shimmerRecyclerView.showShimmer();
/*
        String credentials = Credentials.basic("admin", "LetsValet2You");
*/

        (RetrofitClientInstance.getApiService().getOrders(sessionManager.getACCESSTOKEN())).enqueue(new Callback<HomeViewModel>() {
            @Override
            public void onResponse(@NonNull Call<HomeViewModel> call, @NonNull Response<HomeViewModel> response) {

                if(response.code()==201||response.code()==200){
                    HomeViewModel  login = response.body();
                    queuelist=new ArrayList<>();
                    queuelist=login.getData();
                    if(queuelist.size()!=0){
                         homeAdapter=new HomeAdapter(getActivity(),queuelist);
                        shimmerRecyclerView.setAdapter(homeAdapter);
                        norecord.setVisibility(View.GONE);
                    }
                    else{
                         homeAdapter=new HomeAdapter(getActivity(),queuelist);
                        shimmerRecyclerView.setAdapter(homeAdapter);
                        norecord.setVisibility(View.VISIBLE);
                    }

                    for(int i=0;i<queuelist.size();i++) {
                        if(queuelist.get(i).getStatus().equals("new_entry")) {
                            long timeDiff = 0;
                            Date date1 = null;
                            Date date2 = null;
                            final Calendar calendar = Calendar.getInstance();
                            String timeStamp = new SimpleDateFormat("dd MMM yyyy hh:mm a").format(new Date());
                            System.out.println("current date" + timeStamp + " " + queuelist.get(i).getCreated_at());
                            SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy hh:mm a");

                            Calendar cal = Calendar.getInstance(Locale.ENGLISH);
                            cal.setTimeInMillis(queuelist.get(i).getCreated_at() * 1000L);
                            String date = android.text.format.DateFormat.format("dd MMM yyyy hh:mm a", cal).toString();
                            try {
                                date1 = formatter.parse(timeStamp);
                                date2 = formatter.parse(date);
                                System.out.println("current datem 2 " + date1 + " " + date2);

                            } catch (ParseException e) {
                                e.printStackTrace();
                                System.out.println("current datem 2 " + e.toString());

                            }
                            //  timeDiff = date1.getTime() - date2.getTime();
                            timeDiff = (date1.getTime() - date2.getTime()) - SystemClock.elapsedRealtime();
                            System.out.println(" time diff"
                                    + timeDiff);
                            queuelist.get(i).setTimediffrence(timeDiff);
                            queuelist.get(i).setStatus("New Order");
                        }
                        else{
                            long timeDiff = 0;
                            Date date1 = null;
                            Date date2 = null;
                            final Calendar calendar = Calendar.getInstance();
                            String timeStamp = new SimpleDateFormat("dd MMM yyyy hh:mm a").format(new Date());
                            System.out.println("current date" + timeStamp + " " + queuelist.get(i).getConfirm_at());
                            SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy hh:mm a");

                            Calendar cal = Calendar.getInstance(Locale.ENGLISH);
                            cal.setTimeInMillis(queuelist.get(i).getConfirm_at() * 1000L);
                            String date = android.text.format.DateFormat.format("dd MMM yyyy hh:mm a", cal).toString();
                            try {
                                date1 = formatter.parse(timeStamp);
                                date2 = formatter.parse(date);
                                System.out.println("current datem 2 " + date1 + " " + date2);

                            } catch (ParseException e) {
                                e.printStackTrace();
                                System.out.println("current datem 2 " + e.toString());

                            }
                            //  timeDiff = date1.getTime() - date2.getTime();
                            timeDiff = (date1.getTime() - date2.getTime()) - SystemClock.elapsedRealtime();
                            System.out.println(" time diff"
                                    + timeDiff);
                            queuelist.get(i).setTimediffrence(timeDiff);
                            queuelist.get(i).setStatus("In Kitchen");

                        }

                    }

                    if(Network.isNetworkAvailable(getActivity())){
                        GetHeader();
                    }else if(Network.isNetworkAvailable2(getActivity())){
                        GetHeader();
                    }
                    else{

                    }


                }
                else if(response.code()==401){
                  HomeViewModel login = response.body();
                    Toast.makeText(getActivity(), "Unauthorised", Toast.LENGTH_SHORT).show();
                    sessionManager.logoutsession();

                }
                else if(response.code()==500){
                    Toast.makeText(getActivity(), "Something went wrong.", Toast.LENGTH_SHORT).show();

                }
                else{

                }

                shimmerRecyclerView.hideShimmer();



            }

            @Override
            public void onFailure(@NonNull Call<HomeViewModel> call, @NonNull Throwable t) {
                Log.d("response", Arrays.toString(t.getStackTrace()));
                shimmerRecyclerView.hideShimmer();

            }
        });

    }
    private void GetHeader() {
        // display a progress dialog
/*
        String credentials = Credentials.basic("admin", "LetsValet2You");
*/

        (RetrofitClientInstance.getApiService().getHeader(sessionManager.getACCESSTOKEN())).enqueue(new Callback<Header>() {
            @Override
            public void onResponse(@NonNull Call<Header> call, @NonNull Response<Header> response) {

                if(response.code()==201||response.code()==200){
                    Header  login = response.body();
                   MainActivity.availabletrolley.setText(String.valueOf(login.getData().getNew_orders()));
                   MainActivity.availabletray.setText(String.valueOf(login.getData().getAccepted_orders()));
                   MainActivity.availableassciate.setText(String.valueOf(login.getData().getDispatched_orders()));
                   MainActivity.totalorders.setText(String.valueOf(login.getData().getNew_orders()+login.getData().getAccepted_orders()+login.getData().getDispatched_orders()));


                }
                else if(response.code()==401){
                    Header login = response.body();
                    Toast.makeText(getActivity(), "Unauthorised", Toast.LENGTH_SHORT).show();
                    sessionManager.logoutsession();
                }
                else if(response.code()==500){
                    Toast.makeText(getActivity(), "Something went wrong.", Toast.LENGTH_SHORT).show();

                }
                else{

                }




            }

            @Override
            public void onFailure(@NonNull Call<Header> call, @NonNull Throwable t) {
                Log.d("response", Arrays.toString(t.getStackTrace()));

            }
        });

    }
    private void setAccept(String id) {
        // display a progress dialog
/*
        String credentials = Credentials.basic("admin", "LetsValet2You");
*/

        (RetrofitClientInstance.getApiService().OrderAccepted(BuildConfig.order_accept+"/"+id,sessionManager.getACCESSTOKEN())).enqueue(new Callback<Action>() {
            @Override
            public void onResponse(@NonNull Call<Action> call, @NonNull Response<Action> response) {

                if(response.code()==201||response.code()==200){
                    Action  login = response.body();
                    Toast.makeText(getActivity(),login.getMessage(),Toast.LENGTH_SHORT).show();
                    if(Network.isNetworkAvailable(getActivity())){
                        GetMenu();
                    }
                    else if(Network.isNetworkAvailable2(getActivity())){
                        GetMenu();
                    }
                  else{

                    }

                }
                else if(response.code()==401){
                    Action login = response.body();
                    Toast.makeText(getActivity(), "Unauthorised", Toast.LENGTH_SHORT).show();
                    sessionManager.logoutsession();

                }
                else if(response.code()==500){
                    Toast.makeText(getActivity(), "Something went wrong.", Toast.LENGTH_SHORT).show();

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

        (RetrofitClientInstance.getApiService().OrderDispatched(BuildConfig.order_dispatch+"/"+id,sessionManager.getACCESSTOKEN())).enqueue(new Callback<Action>() {
            @Override
            public void onResponse(@NonNull Call<Action> call, @NonNull Response<Action> response) {

                if(response.code()==201||response.code()==200){
                    Action  login = response.body();
                    Toast.makeText(getActivity(),login.getMessage(),Toast.LENGTH_SHORT).show();
                    if(Network.isNetworkAvailable(getActivity())){
                        GetMenu();
                    }
                    else if(Network.isNetworkAvailable2(getActivity())){
                        GetMenu();
                    }
                    else{

                    }

                }
                else if(response.code()==401){
                    Action login = response.body();
                    Toast.makeText(getActivity(), "Unauthorised", Toast.LENGTH_SHORT).show();
                    sessionManager.logoutsession();

                }
                else if(response.code()==500){
                    Toast.makeText(getActivity(), "Something went wrong.", Toast.LENGTH_SHORT).show();

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

    public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.viewholder> {
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
        public HomeAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.queuelist, parent, false);

            return new HomeAdapter.viewholder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final HomeAdapter.viewholder holder, int position) {
            holder.mitem=homeViewModels.get(position);
            holder.roomno.setText(holder.mitem.getRoom_no());
            holder.guests.setText(holder.mitem.getNo_guest());
            // holder.orderrecived.setText(holder.mitem.getOrderreceived())

            if (holder.mitem.getDetails() != null) {
                holder.instaruction.setText(holder.mitem.getDetails());

          }
          else{
              holder.instaruction.setText("-");

          }

            holder.since.setText(getDate(holder.mitem.getCreated_at()));
            if(holder.mitem.getStatus().equals("New Order")){
                holder.dispatch.setText("ACCEPT");
                holder.colorimage.setBackgroundColor(context.getResources().getColor(R.color.red));
                holder.orderstatus.setTextColor(context.getResources().getColor(R.color.gray));
                holder.since.setTextColor(context.getResources().getColor(R.color.gray));
                holder.roomno.setTextColor(context.getResources().getColor(R.color.mehroon));

            }
            else{
                holder.dispatch.setText("CLEAR");

                holder.colorimage.setBackgroundColor(context.getResources().getColor(R.color.light_green));
                holder.orderstatus.setTextColor(context.getResources().getColor(R.color.gray));
                holder.since.setTextColor(context.getResources().getColor(R.color.gray));
                holder.roomno.setTextColor(context.getResources().getColor(R.color.mogiya));


            }
            if(holder.mitem.getItems().size()>4){
                holder.more.setVisibility(View.VISIBLE);
                holder.more.setText(String.valueOf(holder.mitem.getItems().size()-4)+" More");
            }
            else{
                holder.more.setVisibility(View.GONE);

            }

            HomeAdapter.Order_ItemAdapter1 order_itemAdapter1=new HomeAdapter.Order_ItemAdapter1(holder.mitem.getItems(),context);
            holder.recyclerView.setAdapter(order_itemAdapter1);

            holder.dispatch.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("WrongConstant")
                @Override
                public void onClick(View v) {
                    if(holder.mitem.getStatus().equals("New Order")) {
                        if(Network.isNetworkAvailable(getActivity())){
                            setAccept(holder.mitem.getOrder_id());
                        }else if(Network.isNetworkAvailable2(getActivity())){
                            setAccept(holder.mitem.getOrder_id());

                        }
                        else{

                        }

                    }
                    else{
                        if(Network.isNetworkAvailable(getActivity())){
                            setDispatch(holder.mitem.getOrder_id());
                        }else if(Network.isNetworkAvailable2(getActivity())){
                            setDispatch(holder.mitem.getOrder_id());
                        }
                        else{

                        }
                    }




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
        public void filterList(ArrayList<HomeViewModel.Data> filterdNames) {
            this.homeViewModels = filterdNames;
            notifyDataSetChanged();
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

                final Typeface font1 = Typeface.createFromAsset(
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
                        TextView acceptedat=dialog.findViewById(R.id.accepttext);
                        acceptedat.setTypeface(font);
                        TextView accepted=dialog.findViewById(R.id.accepted);
                        TextView dispatchbutton=dialog.findViewById(R.id.dispatch);
                        dispatch.setTypeface(font);
                        if(homeViewModels.get(getAdapterPosition()).getStatus().equals("New Order")){
                            dispatchbutton.setText("ACCEPT");
                        }
                        else{
                            dispatchbutton.setText("CLEAR");
                        }


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
                        HomeAdapter.Order_ItemAdapterdetail order_itemAdapterdetail=new HomeAdapter.Order_ItemAdapterdetail(homeViewModels.get(getAdapterPosition()).getItems(),context);
                        orderitemsdetail.setAdapter(order_itemAdapterdetail);
                        LinearLayout colorimage=dialog.findViewById(R.id.colorimage);
                        guests.setText(homeViewModels.get(getAdapterPosition()).getNo_guest());
                        if(homeViewModels.get(getAdapterPosition()).getStatus().equals("New Order")){
                            accepted.setText("-");
                        }
                        else{
                            accepted.setText(getDate(homeViewModels.get(getAdapterPosition()).getConfirm_at()));

                        }

                        if(homeViewModels.get(getAdapterPosition()).getStatus().equals("New Order")){
                            colorimage.setBackgroundColor(context.getResources().getColor(R.color.red));
                            since.setTextColor(context.getResources().getColor(R.color.gray));
                            roomno.setTextColor(context.getResources().getColor(R.color.mehroon));

                        }
                        else{
                            colorimage.setBackgroundColor(context.getResources().getColor(R.color.light_green));
                            since.setTextColor(context.getResources().getColor(R.color.gray));
                            roomno.setTextColor(context.getResources().getColor(R.color.mogiya));


                        }
                        back.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });


                        dispatchbutton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(homeViewModels.get(getAdapterPosition()).getStatus().equals("New Order")){
                                    if(Network.isNetworkAvailable(getActivity())){
                                        setAccept(homeViewModels.get(getAdapterPosition()).getOrder_id());
                                        dialog.dismiss();
                                    }
                                   else if(Network.isNetworkAvailable2(getActivity())){
                                        setAccept(homeViewModels.get(getAdapterPosition()).getOrder_id());
                                        dialog.dismiss();

                                    }
                                   else{

                                    }

                                }
                                else{
                                    if(Network.isNetworkAvailable(getActivity())){
                                        setDispatch(homeViewModels.get(getAdapterPosition()).getOrder_id());
                                        dialog.dismiss();

                                    }
                                    else if(Network.isNetworkAvailable2(getActivity())){
                                        setDispatch(homeViewModels.get(getAdapterPosition()).getOrder_id());
                                        dialog.dismiss();

                                    }
                                    else{

                                    }
                                }
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
                            if(mitem.getStatus().equals("New Order")){
                                orderrecived.setText(String.valueOf(minutes) + " m : " + String.valueOf(seconds) + " s");
                                orderstatus.setTextColor(context.getResources().getColor(R.color.mehroon));
                                orderrecived.setTextColor(Color.parseColor("#000000"));
                                orderstatus.setText(mitem.getStatus());

                            }else{

                                orderrecived.setText(String.valueOf(minutes) + " m : " + String.valueOf(seconds) + " s");
                                orderstatus.setTextColor(context.getResources().getColor(R.color.mogiya));
                                orderrecived.setTextColor(Color.parseColor("#000000"));
                                orderstatus.setText(mitem.getStatus());}






                        } else if (hours == 01 || hours == 1) {
                            if (mitem.getStatus().equals("New Order")) {
                                orderrecived.setText(String.valueOf(hours) + " hr : " + String.valueOf(minutes) + " m : " + String.valueOf(seconds) + " s");
                                orderstatus.setTextColor(context.getResources().getColor(R.color.mehroon));
                                orderrecived.setTextColor(Color.parseColor("#000000"));
                                orderstatus.setText(mitem.getStatus());

                            } else {

                                orderrecived.setText(String.valueOf(hours) + " hr : " + String.valueOf(minutes) + " m : " + String.valueOf(seconds) + " s");
                                orderstatus.setTextColor(context.getResources().getColor(R.color.mogiya));
                                orderrecived.setTextColor(Color.parseColor("#000000"));
                                orderstatus.setText(mitem.getStatus());
                            }



                        } else {
                            if (mitem.getStatus().equals("New Order")) {
                                orderrecived.setText(String.valueOf(hours) + " hrs : " + String.valueOf(minutes) + " m : " + String.valueOf(seconds) + " s");
                                orderstatus.setTextColor(context.getResources().getColor(R.color.mehroon));
                                orderrecived.setTextColor(Color.parseColor("#000000"));
                                orderstatus.setText(mitem.getStatus());

                            } else {

                                orderrecived.setText(String.valueOf(hours) + " hrs : " + String.valueOf(minutes) + " m : " + String.valueOf(seconds) + " s");
                                orderstatus.setTextColor(context.getResources().getColor(R.color.mogiya));
                                orderrecived.setTextColor(Color.parseColor("#000000"));
                                orderstatus.setText(mitem.getStatus());
                            }

                        }
                    } else {
                        if (elapsedDays == 1 || elapsedDays == 01) {
                            if (mitem.getStatus().equals("New Order")) {
                                orderrecived.setText(String.valueOf(elapsedDays) + " Day");
                                orderstatus.setTextColor(context.getResources().getColor(R.color.mehroon));
                                orderrecived.setTextColor(Color.parseColor("#000000"));
                                orderstatus.setText(mitem.getStatus());

                            } else {

                                orderrecived.setText(String.valueOf(elapsedDays) + " Day");
                                orderstatus.setTextColor(context.getResources().getColor(R.color.mogiya));
                                orderrecived.setTextColor(Color.parseColor("#000000"));
                                orderstatus.setText(mitem.getStatus());
                            }

                        } else {
                            if (mitem.getStatus().equals("New Order")) {
                                orderrecived.setText(String.valueOf(elapsedDays) + " Days");
                                orderstatus.setTextColor(context.getResources().getColor(R.color.mehroon));
                                orderrecived.setTextColor(Color.parseColor("#000000"));
                                orderstatus.setText(mitem.getStatus());

                            } else {

                                orderrecived.setText(String.valueOf(elapsedDays) + " Days");
                                orderstatus.setTextColor(context.getResources().getColor(R.color.mogiya));
                                orderrecived.setTextColor(Color.parseColor("#000000"));
                                orderstatus.setText(mitem.getStatus());
                            }

                    }
                    }
                }catch (Exception e){

                }
            }

        }

        public class Order_ItemAdapter1 extends  RecyclerView.Adapter<HomeAdapter.Order_ItemAdapter1.ViewHolder>{
            ArrayList<HomeViewModel.Items> order_items;
            Context context;

            public Order_ItemAdapter1(ArrayList<HomeViewModel.Items> order_items, Context context) {
                this.order_items = order_items;
                this.context = context;
            }

            @NonNull
            @Override
            public HomeAdapter.Order_ItemAdapter1.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.order_itemvalue, parent, false);
                return new HomeAdapter.Order_ItemAdapter1.ViewHolder(view);
            }

            @Override
            public void onBindViewHolder(@NonNull HomeAdapter.Order_ItemAdapter1.ViewHolder holder, int position) {
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
        public class Order_ItemAdapterdetail extends  RecyclerView.Adapter<HomeAdapter.Order_ItemAdapterdetail.ViewHolder>{
            ArrayList<HomeViewModel.Items> order_items;
            Context context;

            public Order_ItemAdapterdetail(ArrayList<HomeViewModel.Items> order_items, Context context) {
                this.order_items = order_items;
                this.context = context;
            }

            @NonNull
            @Override
            public HomeAdapter.Order_ItemAdapterdetail.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.order_items, parent, false);
                return new HomeAdapter.Order_ItemAdapterdetail.ViewHolder(view);
            }

            @Override
            public void onBindViewHolder(@NonNull HomeAdapter.Order_ItemAdapterdetail.ViewHolder holder, int position) {
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
    private void filter(String text) {
        //new array list that will hold the filtered data
        ArrayList<HomeViewModel.Data> filterdNames = new ArrayList<>();

        //looping through existing elements
        for (HomeViewModel.Data s :queuelist) {
            //if the existing elements contains the search input
            if (s.getRoom_no().toLowerCase().contains(text.toLowerCase())) {
                //adding the element to filtered list
                filterdNames.add(s);
            }
        }

        //calling a method of the adapter class and passing the filtered list
        homeAdapter.filterList(filterdNames);
    }
    public void tryAnimation(View view) {
        Animation animation = AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_in_left);
        if (view != null)
            view.startAnimation(animation);
    }
}
