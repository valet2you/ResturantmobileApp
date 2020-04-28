package com.viralops.touchlessfoodordering.ui.main;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.todkars.shimmer.ShimmerRecyclerView;
import com.viralops.touchlessfoodordering.R;

import java.util.ArrayList;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    ShimmerRecyclerView shimmerRecyclerView;
    ArrayList<HomeViewModel> queuelist=new ArrayList<>();
    ArrayList<Order_Item> orderlist;
    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.main_fragment, container, false);
        shimmerRecyclerView=view.findViewById(R.id.recyclerview);
        shimmerRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),6));
        orderlist=new ArrayList<>();
        Order_Item order_item=new Order_Item();
        order_item.setId("1");
        order_item.setName("Vegetable Spring Roll");
        order_item.setPrice("200");
        order_item.setQuantity("1");
        orderlist.add(order_item);
        Order_Item order_item1=new Order_Item();
        order_item1.setId("1");
        order_item1.setName("Crispy Szechuan Chicken NV");
        order_item1.setPrice("100");
        order_item1.setQuantity("2");
        orderlist.add(order_item1);
        Order_Item order_item2=new Order_Item();
        order_item2.setId("1");
        order_item2.setName("Multani Paneer Tikka");
        order_item2.setPrice("200");
        order_item2.setQuantity("1");
        orderlist.add(order_item2);
        Order_Item order_item3=new Order_Item();
        order_item3.setId("1");
        order_item3.setName("Subz Gifali Seekh");
        order_item3.setPrice("200");
        order_item3.setQuantity("2");
        orderlist.add(order_item3);
        Order_Item order_item4=new Order_Item();
        order_item4.setId("1");
        order_item4.setName("Chukandar ki Shami");
        order_item4.setPrice("200");
        order_item4.setQuantity("1");
        orderlist.add(order_item4);

        Order_Item order_item5=new Order_Item();
        order_item5.setId("1");
        order_item5.setName("Tandoori Murg NV");
        order_item5.setPrice("200");
        order_item5.setQuantity("4");
        orderlist.add(order_item5);


        HomeViewModel homeViewModel=new HomeViewModel();
        homeViewModel.setRoomno("123456");
        homeViewModel.setGuests("5");
        homeViewModel.setStatus("1");
        homeViewModel.setCheckstatus("0");
        homeViewModel.setOrder_itemslist(orderlist);
        homeViewModel.setOrderreceived("03:10:00");
        homeViewModel.setOrderstatus("In Kitchen");
        homeViewModel.setSince("03:10 PM");
        HomeViewModel homeViewModel1=new HomeViewModel();
        homeViewModel1.setRoomno("123456");
        homeViewModel1.setGuests("5");
        homeViewModel1.setCheckstatus("0");
        homeViewModel1.setOrder_itemslist(orderlist);
        homeViewModel1.setStatus("2");
        homeViewModel1.setOrderreceived("03:10:00");
        homeViewModel1.setOrderstatus("Pending");
        homeViewModel1.setSince("03:10 PM");
        HomeViewModel homeViewModel2=new HomeViewModel();
        homeViewModel2.setRoomno("123456");
        homeViewModel2.setGuests("5");
        homeViewModel2.setOrder_itemslist(orderlist);
        homeViewModel2.setStatus("3");
        homeViewModel2.setCheckstatus("0");
        homeViewModel2.setOrderreceived("03:10:00");
        homeViewModel2.setOrderstatus("In Kitchen");
        homeViewModel2.setSince("03:10 PM");
        queuelist.add(homeViewModel);
        queuelist.add(homeViewModel1);
        queuelist.add(homeViewModel2);
        queuelist.add(homeViewModel);
        queuelist.add(homeViewModel2);
        queuelist.add(homeViewModel1);
        queuelist.add(homeViewModel2);
        queuelist.add(homeViewModel);
        queuelist.add(homeViewModel1);
        queuelist.add(homeViewModel);
        queuelist.add(homeViewModel1);
        queuelist.add(homeViewModel2);
        queuelist.add(homeViewModel);
        HomeAdapter homeAdapter=new HomeAdapter(getActivity(),queuelist);
        shimmerRecyclerView.setAdapter(homeAdapter);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        // TODO: Use the ViewModel
    }

}
