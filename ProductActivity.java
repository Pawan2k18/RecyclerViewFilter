package com.nst.airvegs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends AppCompatActivity {

    TextView pname, ori_price, our_price;
    ImageView pimage;
    Button addBtn;
    String Vpname, Vpimage;
    Spinner spinner;
    ImageButton fav_icon;
    String URL = "http://192.168.1.247:8082/vegapp/SpinnerData.php";
    String productSearchUrl = "http://146.66.68.148/~flightfa/demo/veg_app/oyegifts-admin/Api/getSearchProducts/";
    String getAllProductUrl = "http://146.66.68.148/~flightfa/demo/veg_app/oyegifts-admin/Api/getAllProducts/";
    private ArrayList<String> names = new ArrayList<String>();
    boolean isPlay = false;
    private int vegId = 0;
    RecyclerView searched_product_rec;
    //RecyclerView.Adapter catProadapter;
    private SearchedProductAdapter movieAdapter;
    List<DetailProductListGetSet> detailProductListItems;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);


        searched_product_rec = findViewById(R.id.searched_product_rec);
        searched_product_rec.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        searched_product_rec.setHasFixedSize(true);
        searched_product_rec.setAdapter(movieAdapter);

        detailProductListItems = new ArrayList<>();

        //movieList = new ArrayList<>();
        //loadSpinnerData(URL);
        loadSearchedProduct();

//        pname = findViewById(R.id.name);
//        pimage = findViewById(R.id.product_image);
//        Vpname = getIntent().getStringExtra("pname");
//        Vpimage = getIntent().getStringExtra("pimage");
//        addBtn = findViewById(R.id.add_Btn);
//        spinner = findViewById(R.id.spinner_btn);
//        fav_icon = findViewById(R.id.fav_icon);
//
//        fav_icon.setBackgroundResource(R.drawable.ic_favorite_border_black_24dp);
//        fav_icon.setOnClickListener(mTogglePlayButton);
//
//        pname.setText(Vpname);
//
//        Glide.with(this)
//                .load(Vpimage)
//                .into(pimage);
//
//
//        addBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(ProductActivity.this, "clicked", Toast.LENGTH_SHORT).show();
//
//                NoteDatabase ud = NoteDatabase.getInstance(getApplicationContext());
//
//                //init the entity
//                Note note = new Note();
//                note.setPname(Vpname);
//                note.setPprice("40");
//                note.setPquantity("5");
//                note.setPweight("100");
//                note.setPriority(1);
//
//                //init dao and perform operation
//                NoteDao dao = ud.noteDao();
//                dao.insert(note);
//            }
//        });
//
//
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                @Override
//                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                    String country = spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString();
//                    Toast.makeText(getApplicationContext(),country,Toast.LENGTH_LONG).show();
//                }
//                @Override
//                public void onNothingSelected(AdapterView<?> adapterView) {
//                    // DO Nothing here
//                }
//            });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.upper_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.up_search).getActionView();
        assert searchManager != null;
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                movieAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                movieAdapter.getFilter().filter(query);
                return false;
            }
        });
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.up_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    @Override
//    public void onBackPressed() {
//        if (!searchView.isIconified()) {
//            searchView.setIconified(true);
//            return;
//        }
//        super.onBackPressed();
//    }



//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.upper_menu,menu);
//        MenuItem myActionMenuItem = menu.findItem(R.id.up_search);
//        SearchView searchView =(SearchView)myActionMenuItem.getActionView();
////        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
////            @Override
////            public boolean onQueryTextSubmit(String query) {
////                return false;
////            }
////
////            @Override
////            public boolean onQueryTextChange(String newText) {
////                if(TextUtils.isEmpty(newText)){
////                    Toast.makeText(ProductActivity.this, "Empty", Toast.LENGTH_SHORT).show();
////                }
////                else {
////                    loadSearchedProduct(newText);
////                }
////
////                return false;
////            }
////        });
//
//
//        return super.onCreateOptionsMenu(menu);
//    }

    //    View.OnClickListener mTogglePlayButton = new View.OnClickListener(){
//        @Override
//        public void onClick(View v){
//            if(isPlay){
//                v.setBackgroundResource(R.drawable.ic_favorite_border_black_24dp);
//                Toast.makeText(ProductActivity.this, "Removed from Wishlist", Toast.LENGTH_SHORT).show();
//            }else{
//                v.setBackgroundResource(R.drawable.ic_favorite_black_24dp);
//                Toast.makeText(ProductActivity.this, "Added to Wishlist", Toast.LENGTH_SHORT).show();
//            }
//            isPlay = !isPlay; // reverse
//        }
//    };


//    private void loadSpinnerData(String url) {
//        RequestQueue requestQueue= Volley.newRequestQueue(this);
//        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try{
//                    JSONObject jsonObject=new JSONObject(response);
//                    if(jsonObject.getInt("success")==1){
//                        JSONArray jsonArray=jsonObject.getJSONArray("Name");
//                        for(int i=0;i<jsonArray.length();i++){
//                            JSONObject jsonObject1=jsonArray.getJSONObject(i);
//                            String country=jsonObject1.getString("Country");
//                            names.add(country);
//                        }
//                    }
//
//                    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(ProductActivity.this, android.R.layout.simple_spinner_item, names);
//                    spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
//                    spinner.setAdapter(spinnerArrayAdapter);
//
//                }catch (JSONException e){e.printStackTrace();}
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//            }
//        });
//        int socketTimeout = 30000;
//        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
//        stringRequest.setRetryPolicy(policy);
//        requestQueue.add(stringRequest);
//    }


    private void loadSearchedProduct() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, getAllProductUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArrayProduct = jsonObject.getJSONArray("search");

                            for (int i = 0; i < jsonArrayProduct.length(); i++) {
                                JSONObject o = jsonArrayProduct.getJSONObject(i);

                                DetailProductListGetSet item = new DetailProductListGetSet(
                                        o.getString("id"),
                                        o.getString("enable_product"),
                                        o.getString("enable_pincode"),
                                        o.getString("product_name"),
                                        o.getString("delivery_type"),
                                        o.getString("label_option"),
                                        o.getString("quantity"),
                                        o.getString("price"),
                                        o.getString("special_price"),
                                        o.getString("sku"),
                                        o.getString("stock_status"),
                                        o.getString("production_time"),
                                        o.getString("product_type"),
                                        o.getString("parent_prod_id"));

                                detailProductListItems.add(item);


                                movieAdapter = new SearchedProductAdapter(detailProductListItems, getApplicationContext(), new ClickListener() {
                                    @Override
                                    public void onPositionClicked(int position) {
                                        //Toast.makeText(getApplicationContext(), "gf" , Toast.LENGTH_LONG).show();
                                    }

                                    @Override
                                    public void onLongClicked(int position) {
                                        //Toast.makeText(getApplicationContext(),"huuju", Toast.LENGTH_LONG).show();
                                    }
                                });
                                searched_product_rec.setAdapter(movieAdapter);

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);

    }

}
