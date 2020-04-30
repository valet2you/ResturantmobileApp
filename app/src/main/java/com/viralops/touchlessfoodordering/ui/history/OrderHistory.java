package com.viralops.touchlessfoodordering.ui.history;

import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.lifecycle.ViewModelProviders;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.todkars.shimmer.ShimmerRecyclerView;
import com.viralops.touchlessfoodordering.R;

import java.util.ArrayList;

public class OrderHistory extends Fragment implements SearchView.OnQueryTextListener {

    private OrderHistoryViewModel mViewModel;
    ShimmerRecyclerView recyclerview;
    TextView roomno;
    TextView guest;
    TextView orderreceivedtext;
    TextView deliveredat;
    TextView orderacceptedtext;
    TextView norecord;
   ArrayList<Order> orderslist;
    SearchView searchView;

    public static OrderHistory newInstance() {
        return new OrderHistory();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.order_history_fragment, container, false);
        recyclerview=view.findViewById(R.id.sidelist);
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        roomno=view.findViewById(R.id.roomno);
        guest=view.findViewById(R.id.guest);
        orderreceivedtext=view.findViewById(R.id.orderreceivedtext);
        deliveredat=view.findViewById(R.id.deliveredat);
        orderacceptedtext=view.findViewById(R.id.orderacceptedtext);
        norecord=view.findViewById(R.id.norecord);
        final Typeface font = Typeface.createFromAsset(
                getActivity().getAssets(),
                "font/Roboto-Regular.ttf");
        roomno.setTypeface(font);
        guest.setTypeface(font);
        orderreceivedtext.setTypeface(font);
        deliveredat.setTypeface(font);
        orderacceptedtext.setTypeface(font);

        Typeface font1 = Typeface.createFromAsset(
                getActivity().getAssets(),
                "font/verdana.ttf");
        orderslist=new ArrayList<>();
        for(int i=0;i<10;i++){
            Order orders=new Order();
            orders.setRoomno("123456");
            orders.setGuests("5");
            orders.setStatus("3");
            orders.setOrderreceivedat("Today at 12:30 am");
            orders.setDeliverytime("Today at 1:30 pm");
            orderslist.add(orders);

        }
        HistoryAdapter historyAdapter=new HistoryAdapter(getActivity(),orderslist);
        recyclerview.setAdapter(historyAdapter);

        return view;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        //  drawer_adapter=new ReleaseCarByToken.MyItemRecyclerViewAdapter(dashboards,getActivity());
        //recyclerView.setAdapter(drawer_adapter);
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);

        MenuItem search = menu.findItem(R.id.search);
        searchView = (SearchView) MenuItemCompat.getActionView(search);
        searchView.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
        searchView.setInputType(InputType.TYPE_CLASS_NUMBER);

        searchView.setBackgroundColor(Color.DKGRAY);

        // search(searchView);
        searchView.setOnQueryTextListener(this);  }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
