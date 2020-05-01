package com.viralops.touchlessfoodordering;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;
import com.todkars.shimmer.ShimmerRecyclerView;
import com.viralops.touchlessfoodordering.ui.API.RetrofitClientInstance;
import com.viralops.touchlessfoodordering.ui.Support.Network;
import com.viralops.touchlessfoodordering.ui.Support.RoundedCornersTransformation;
import com.viralops.touchlessfoodordering.ui.Support.SessionManager;
import com.viralops.touchlessfoodordering.ui.Support.SessionManagerFCM;
import com.viralops.touchlessfoodordering.ui.history.OrderHistory;
import com.viralops.touchlessfoodordering.ui.main.MainFragment;
import com.viralops.touchlessfoodordering.ui.model.Action;
import com.viralops.touchlessfoodordering.ui.model.Menu;
import com.viralops.touchlessfoodordering.ui.model.Menucategory;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    static public TextView busytray;
    static public TextView busytrolley;
    static public TextView busyassciate;
    static public TextView availabletray;
    static public TextView availabletrolley;
    static public TextView availableassciate;
    static public TextView totaltext;
    static public TextView totalorders;
    private TextView text;
    private TextView text1;
    private CardView order;
    private CardView menu;
    private  TextView history;
    private ImageView logout;
    private SessionManager sessionManager;
    private SessionManagerFCM sessionManagerFCM;
    ArrayList<Menucategory> categorylist=new ArrayList<>();
    ArrayList<Menu.menu_data> menuslist=new ArrayList<>();
     Dialog dialog1;
    ParentAdapter parentAdapter;
    ShimmerRecyclerView shimmerRecyclerView;
    Order_ItemAdapterdetail order_itemAdapterdetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        sessionManager=new SessionManager(MainActivity.this);
        sessionManagerFCM=new SessionManagerFCM(MainActivity.this);

        final Typeface font = Typeface.createFromAsset(
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
        menu=findViewById(R.id.menu);

        availabletray=findViewById(R.id.avalbletray);
        availabletray.setTypeface(font1);
        availabletrolley=findViewById(R.id.avalbletrollet);
        availabletrolley.setTypeface(font1);
        availableassciate=findViewById(R.id.avalbleassociate);
        availableassciate.setTypeface(font1);
        totaltext=findViewById(R.id.total);
        totaltext.setTypeface(font);
        totalorders=findViewById(R.id.totalorders);
        order=findViewById(R.id.order);
        totalorders.setTypeface(font1);

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
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(history.getText().toString().equals("ORDERS HISTORY")) {
                    history.setText("DASHBOARD");
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_view, OrderHistory.newInstance())
                            .commitNow();
                }
                else{
                    history.setText("ORDERS HISTORY");
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_view, MainFragment.newInstance())
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
                int width1 = (int)(getResources().getDisplayMetrics().widthPixels*0.55);
                int height1 = (int)(getResources().getDisplayMetrics().heightPixels*0.95);
                dialog.getWindow().setGravity(Gravity.CENTER_VERTICAL);

                dialog.getWindow().setLayout(width1, height1);

                dialog.setCancelable(false);
                // Set dialog title
                dialog.setTitle("");
                dialog.show();
                 shimmerRecyclerView=dialog.findViewById(R.id.recyclerview);
                shimmerRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false));
                 TextView title=dialog.findViewById(R.id.hotel);
                 title.setTypeface(font);
                 final MaterialButton menubutton=dialog.findViewById(R.id.menubutton);
                 final MaterialButton backbutton=dialog.findViewById(R.id.closebutton);
                 parentAdapter=new ParentAdapter(menuslist,MainActivity.this);
                shimmerRecyclerView.setAdapter(parentAdapter);
                EditText searchtext=dialog.findViewById(R.id.searchtext);
                searchtext.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        filter1(s.toString());
                    }
                });
                 registerForContextMenu(menubutton);
                 ImageView close=dialog.findViewById(R.id.close);
                 close.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         dialog.dismiss();
                     }
                 });

                menubutton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   final Dialog dialog = new Dialog(MainActivity.this);
                   // Include dialog.xml file
                   dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                   // getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                   dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

                   dialog.setContentView(R.layout.categorypopup);
                   int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.99);
                   int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.99);

                   dialog.getWindow().setLayout(width, height);
                   dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation_2;

                  // dialog.setCancelable(true);
                   dialog.setCanceledOnTouchOutside(true);
                  // setFinishOnTouchOutside(true);
                   // Set dialog title
                   dialog.setTitle("Select Category");
                   dialog.show();
                   RecyclerView recyclerView=dialog.findViewById(R.id.recycler);
                   recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false));
                   CartegoryAdapter menupopupadapeter=new CartegoryAdapter(categorylist,MainActivity.this);
                   recyclerView.setAdapter(menupopupadapeter);
                  ImageView close=dialog.findViewById(R.id.close);
                  close.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {
                          dialog.dismiss();
                      }
                  });
                /*   menubutton.setVisibility(View.GONE);
                   backbutton.setVisibility(View.VISIBLE);*/
                   //openOptionsMenu();
                  // openContextMenu(v);
                   /*  dialog1 = new Dialog(MainActivity.this);
                   // Include dialog.xml file

                   dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                   // dialog.getWindow().setLayout(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.FILL_PARENT);            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                   dialog1.setContentView(R.layout.menupopup);


                   dialog1.setCancelable(false);
                   // Set dialog title
                   dialog1.setTitle("");
                   dialog1.show();
                   RecyclerView menupopuprecy=dialog1.findViewById(R.id.recycller);
                   menupopuprecy.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false));
                   CartegoryAdapter menupopupadapeter=new CartegoryAdapter(categorylist,MainActivity.this);
                   menupopuprecy.setAdapter(menupopupadapeter);*/
               }
           });
                /*backbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        closeContextMenu();
                       // closeOptionsMenu();
                        menubutton.setVisibility(View.VISIBLE);
                        backbutton.setVisibility(View.GONE);
                      //  dialog1.dismiss();

                    }
                });*/



            }
        });
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_view, MainFragment.newInstance())
                    .commitNow();
        }
        if (Network.isNetworkAvailable(MainActivity.this)) {
            GetMenu();


        } else if (Network.isNetworkAvailable2(MainActivity.this)) {
            GetMenu();


        }
        else{

        }

    }

    public class Order_ItemAdapterdetail extends  RecyclerView.Adapter<Order_ItemAdapterdetail.ViewHolder>{
        ArrayList<Menu.Items> order_items;
        Context context;
        String category;
        String enabled;

        public Order_ItemAdapterdetail(ArrayList<Menu.Items> order_items, Context context,String category,String enabled) {
            this.order_items = order_items;
            this.context = context;
            this.category=category;
            this.enabled=enabled;
        }

        @NonNull
        @Override
        public Order_ItemAdapterdetail.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.menu_item, parent, false);
            return new Order_ItemAdapterdetail.ViewHolder(view);
        }
        public void filterList(ArrayList<Menu.Items> filterdNames) {
            this.order_items = filterdNames;
            notifyDataSetChanged();
        }
        @Override
        public void onBindViewHolder(@NonNull final Order_ItemAdapterdetail.ViewHolder holder, int position) {
            holder.mitem=order_items.get(position);
            holder.title.setText(holder.mitem.getName());
            holder.category1.setText(category);
            holder.price.setText(getResources().getString(R.string.Rs));
            holder.price1.setText(holder.mitem.getPrice());
            holder.ingredinets.setText(holder.mitem.getDescription());
            if(enabled.equals("1")){
                holder.toggleButton1.setEnabled(true);
                holder.toggleButton1.setClickable(true);
                if(holder.mitem.getIs_enabled().equals("1")){
                    holder.toggleButton1.setChecked(true);
                    holder.gradeout.setVisibility(View.GONE);
                }
                else{
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

            if(holder.mitem.getType().equals("veg")){
                holder.category.setImageResource(R.mipmap.veggg);
            }
            else{
                holder.category.setImageResource(R.mipmap.nonveggg);

            }
            Picasso.with(context).load(R.mipmap.dish2).transform(new RoundedCornersTransformation(10,10)).into(holder.dish);
          //  holder.dish.setVisibility(View.VISIBLE);


            holder.spicy.setVisibility(View.VISIBLE);

           /* if(holder.mitem.getImage().toString().equals(null)||holder.mitem.getImage().toString().equals("")){
            }
            else {
                holder.dish.setVisibility(View.VISIBLE);
            }*/
            holder.toggleButton1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){

                        holder.gradeout.setVisibility(View.GONE);
                        if (Network.isNetworkAvailable(context)){
                            GetMenuitemenab(holder.mitem.getItem_id());
                        }
                        else if (Network.isNetworkAvailable2(context)) {

                            GetMenuitemenab(holder.mitem.getItem_id());
                        }
                        else{

                        }
                    }
                    else{

                        holder.gradeout.setVisibility(View.VISIBLE);
                        if (Network.isNetworkAvailable(context)){
                         GetMenuitemdisable(holder.mitem.getItem_id());
                        }
                        else if (Network.isNetworkAvailable2(context)) {
                            GetMenuitemdisable(holder.mitem.getItem_id());

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
            TextView spicy;
            LinearLayout gradeout;
            ToggleButton toggleButton1;


            Menu.Items mitem;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                title=itemView.findViewById(R.id.title);
                category1=itemView.findViewById(R.id.category1);
                category=itemView.findViewById(R.id.category);
                price=itemView.findViewById(R.id.price);
                price1=itemView.findViewById(R.id.price1);
                ingredinets=itemView.findViewById(R.id.ingredinets);
                spicy=itemView.findViewById(R.id.spicy);
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


    public class ParentAdapter extends  RecyclerView.Adapter<ParentAdapter.ViewHolder>{
        ArrayList<Menu.menu_data> order_items;
        Context context;
        String enabled;

        public ParentAdapter(ArrayList<Menu.menu_data> order_items, Context context) {
            this.order_items = order_items;
            this.context = context;
        }

        @NonNull
        @Override
        public ParentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.menuparent, parent, false);
            return new ParentAdapter.ViewHolder(view);
        }
        public void filterList(ArrayList<Menu.menu_data> filterdNames) {
            this.order_items = filterdNames;
            notifyDataSetChanged();
        }
        @Override
        public void onBindViewHolder(@NonNull final ParentAdapter.ViewHolder holder, int position) {
            holder.mitem=order_items.get(position);
            holder.title.setText(holder.mitem.getName()+" ( "+String.valueOf(holder.mitem.getItems().size())+" )");
            if(holder.mitem.getIs_enabled().equals("1")){
                holder.toggle.setChecked(true);
                enabled="1";
            }
            else{
                holder.toggle.setChecked(false);
                enabled="0";

            }
            holder.toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        if (Network.isNetworkAvailable(context)) {
                            GetMenucategoryenable(holder.mitem.getCategory_id());
                            enabled="1";
                        }  else if (Network.isNetworkAvailable2(context)) {
                            GetMenucategoryenable(holder.mitem.getCategory_id());
                            enabled="1";

                        }
                        else{

                        }
                    }
                    else{
                        if (Network.isNetworkAvailable(context)) {
                            GetMenucategorydisable(holder.mitem.getCategory_id());
                            enabled="0";

                        }  else if (Network.isNetworkAvailable2(context)) {
                            GetMenucategorydisable(holder.mitem.getCategory_id());
                            enabled="0";

                        }
                        else{

                        }
                    }
                }
            });


            order_itemAdapterdetail=new Order_ItemAdapterdetail(holder.mitem.getItems(),context,holder.mitem.getName(),enabled);
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


            Menu.menu_data mitem;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                title=itemView.findViewById(R.id.category);

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
        public void onBindViewHolder(@NonNull CartegoryAdapter.ViewHolder holder, int position) {
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

            }
        }



    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.dashboard_menu, menu);
        menu.setHeaderTitle("");
        menu.add(1,1,1,"Rice");
        menu.add("Breakfast");
        menu.add("Burgers and Sandwitch");
        menu.add("Appetizers");
        menu.add("Main Course");
        menu.add("Rice");
        menu.add("Pizza and Pasta");
        menu.add("fried Rice and Noodles");

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

        (RetrofitClientInstance.getApiService().logout( sessionManager.getACCESSTOKEN(),sessionManagerFCM.getToken())).enqueue(new Callback<Action>() {
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
    private void GetMenu() {
        // display a progress dialog
        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog
/*
        String credentials = Credentials.basic("admin", "LetsValet2You");
*/

        (RetrofitClientInstance.getApiService().getMenu(sessionManager.getACCESSTOKEN())).enqueue(new Callback<Menu>() {
            @Override
            public void onResponse(@NonNull Call<Menu> call, @NonNull Response<Menu> response) {

                if(response.code()==201||response.code()==200){
                    Menu  login = response.body();
                   menuslist=new ArrayList<>();
                   menuslist=login.getData();
                   categorylist =new ArrayList<>();
                   for(int i=0;i<menuslist.size();i++){
                       Menucategory menucategory=new Menucategory();
                       menucategory.setCategory_id(menuslist.get(i).getCategory_id());
                       menucategory.setName(menuslist.get(i).getName());
                       menucategory.setItems(menuslist.get(i).getItems());
                       categorylist.add(menucategory);

                   }

                }
                else if(response.code()==401){
                    Menu login = response.body();
                    Toast.makeText(MainActivity.this, "Unauthorised", Toast.LENGTH_SHORT).show();
                }
                else if(response.code()==500){
                    Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_SHORT).show();

                }
                else{

                }

                progressDialog.dismiss();



            }

            @Override
            public void onFailure(@NonNull Call<Menu> call, @NonNull Throwable t) {
                Log.d("response", Arrays.toString(t.getStackTrace()));
                progressDialog.dismiss();

            }
        });

    }
    private void GetMenunew() {
        // display a progress dialog
   //shimmerRecyclerView.showShimmer();
/*
        String credentials = Credentials.basic("admin", "LetsValet2You");
*/

        (RetrofitClientInstance.getApiService().getMenu(sessionManager.getACCESSTOKEN())).enqueue(new Callback<Menu>() {
            @Override
            public void onResponse(@NonNull Call<Menu> call, @NonNull Response<Menu> response) {

                if(response.code()==201||response.code()==200){
                    Menu  login = response.body();
                   menuslist=new ArrayList<>();
                   menuslist=login.getData();
                    categorylist =new ArrayList<>();
                    for(int i=0;i<menuslist.size();i++){
                        Menucategory menucategory=new Menucategory();
                        menucategory.setCategory_id(menuslist.get(i).getCategory_id());
                        menucategory.setName(menuslist.get(i).getName());
                        menucategory.setItems(menuslist.get(i).getItems());
                        categorylist.add(menucategory);

                    }

                    parentAdapter=new ParentAdapter(menuslist,MainActivity.this);
                    shimmerRecyclerView.setAdapter(parentAdapter);                }
                else if(response.code()==401){
                    Menu login = response.body();
                    Toast.makeText(MainActivity.this, "Unauthorised", Toast.LENGTH_SHORT).show();
                }
                else if(response.code()==500){
                    Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_SHORT).show();

                }
                else{

                }

              //  shimmerRecyclerView.hideShimmer();



            }

            @Override
            public void onFailure(@NonNull Call<Menu> call, @NonNull Throwable t) {
                Log.d("response", Arrays.toString(t.getStackTrace()));
                shimmerRecyclerView.hideShimmer();

            }
        });

    }
    private void GetMenuitemenab(String id) {
        // display a progress dialog
        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog
/*
        String credentials = Credentials.basic("admin", "LetsValet2You");
*/

        (RetrofitClientInstance.getApiService().getItemEnabled(sessionManager.getACCESSTOKEN(),BuildConfig.enable_item+"/"+id)).enqueue(new Callback<Action>() {
            @Override
            public void onResponse(@NonNull Call<Action> call, @NonNull Response<Action> response) {

                if(response.code()==201||response.code()==200){
                    Action  login = response.body();
                    Toast.makeText(MainActivity.this, login.getMessage(), Toast.LENGTH_SHORT).show();


                }
                else if(response.code()==401){
                    Action login = response.body();
                    Toast.makeText(MainActivity.this, "Unauthorised", Toast.LENGTH_SHORT).show();
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
    private void GetMenuitemdisable(String id) {
        // display a progress dialog
        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog
/*
        String credentials = Credentials.basic("admin", "LetsValet2You");
*/

        (RetrofitClientInstance.getApiService().getItemDisabled(sessionManager.getACCESSTOKEN(),BuildConfig.disable_item+"/"+id)).enqueue(new Callback<Action>() {
            @Override
            public void onResponse(@NonNull Call<Action> call, @NonNull Response<Action> response) {

                if(response.code()==201||response.code()==200){
                    Action  login = response.body();
                    Toast.makeText(MainActivity.this, login.getMessage(), Toast.LENGTH_SHORT).show();


                }
                else if(response.code()==401){
                    Action login = response.body();
                    Toast.makeText(MainActivity.this, "Unauthorised", Toast.LENGTH_SHORT).show();
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
    private void GetMenucategoryenable(String id) {
        // display a progress dialog
        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog
/*
        String credentials = Credentials.basic("admin", "LetsValet2You");
*/

        (RetrofitClientInstance.getApiService().getcategoryEnabled(sessionManager.getACCESSTOKEN(),BuildConfig.enable_category+"/"+id)).enqueue(new Callback<Action>() {
            @Override
            public void onResponse(@NonNull Call<Action> call, @NonNull Response<Action> response) {

                if(response.code()==201||response.code()==200){
                    Action  login = response.body();
                    Toast.makeText(MainActivity.this, login.getMessage(), Toast.LENGTH_SHORT).show();
                    if(Network.isNetworkAvailable(MainActivity.this)){
                        GetMenunew();

                    }
                    else if(Network.isNetworkAvailable2(MainActivity.this)){
                        GetMenunew();

                    }
                    else{

                    }


                }
                else if(response.code()==401){
                    Action login = response.body();
                    Toast.makeText(MainActivity.this, "Unauthorised", Toast.LENGTH_SHORT).show();
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
    private void GetMenucategorydisable(String id) {
        // display a progress dialog
        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog
/*
        String credentials = Credentials.basic("admin", "LetsValet2You");
*/

        (RetrofitClientInstance.getApiService().getcategorydisabled(sessionManager.getACCESSTOKEN(),BuildConfig.disable_category+"/"+id)).enqueue(new Callback<Action>() {
            @Override
            public void onResponse(@NonNull Call<Action> call, @NonNull Response<Action> response) {

                if(response.code()==201||response.code()==200){
                    Action  login = response.body();
                    Toast.makeText(MainActivity.this, login.getMessage(), Toast.LENGTH_SHORT).show();
               if(Network.isNetworkAvailable(MainActivity.this)){
                   GetMenunew();
               }
               else if(Network.isNetworkAvailable2(MainActivity.this)){
                   GetMenunew();

               }
               else{

               }

                }
                else if(response.code()==401){
                    Action login = response.body();
                    Toast.makeText(MainActivity.this, "Unauthorised", Toast.LENGTH_SHORT).show();
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

    private void filter(String text) {
        //new array list that will hold the filtered data
        ArrayList<Menu.menu_data> filterdNames = new ArrayList<>();

        //looping through existing elements
        for (Menu.menu_data s : menuslist) {
            //if the existing elements contains the search input
            if (s.getName().toLowerCase().contains(text.toLowerCase())) {
                //adding the element to filtered list
                filterdNames.add(s);
            }
        }

        //calling a method of the adapter class and passing the filtered list
        parentAdapter.filterList(filterdNames);
    }
 private void filter1(String text) {
        //new array list that will hold the filtered data
        ArrayList<Menu.Items> filterdNames1 = new ArrayList<>();

        //looping through existing elements
        for (Menu.menu_data s : menuslist) {
            for (Menu.Items s1 : s.getItems()){
                //if the existing elements contains the search input
                if (s1.getName().toLowerCase().contains(text.toLowerCase())) {
                    //adding the element to filtered list
                    filterdNames1.add(s1);
                }
        }
            order_itemAdapterdetail.filterList(filterdNames1);


        }

        //calling a method of the adapter class and passing the filtered list
    }

    // Showing the status in Snackbar

    // Method to manually check connection status

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

                    Fragment fragment1 = new MainFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.content_view, fragment1, fragment1.getClass().getSimpleName()).addToBackStack(null).commit();




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


}
