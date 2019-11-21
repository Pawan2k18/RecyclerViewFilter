package com.nst.airvegs;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchedProductAdapter extends RecyclerView.Adapter<SearchedProductAdapter.ViewHolder> implements Filterable {

    private final ClickListener listener;
    private List<DetailProductListGetSet> listItems;
    private List<DetailProductListGetSet> listItemsFiltered;
    private Context context;
    private static final String BaseUrlForProduct2 = "http://146.66.68.148/~flightfa/demo/veg_app/oyegifts-admin/vendordata/product/";
    Button addBtn;
    ImageButton fav_icon;
    //Spinner spinner;
    boolean isPlay = false;
    String URL = "http://192.168.1.247:8082/vegapp/SpinnerData.php";
    private ArrayList<String> names = new ArrayList<String>();

    public SearchedProductAdapter(List<DetailProductListGetSet> listItems, Context context, ClickListener listener) {
        this.listItems = listItems;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_items, parent, false);

        //loadSpinnerData(URL);
        addBtn = view.findViewById(R.id.add_Btn);
       //spinner = view.findViewById(R.id.spinner_btn);
        fav_icon = view.findViewById(R.id.fav_icon);

        fav_icon.setBackgroundResource(R.drawable.ic_favorite_border_black_24dp);
        fav_icon.setOnClickListener(mTogglePlayButton);

        return new ViewHolder(view);
    }


    View.OnClickListener mTogglePlayButton = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            if(isPlay){
                v.setBackgroundResource(R.drawable.ic_favorite_border_black_24dp);
                Toast.makeText(context, "Removed from Wishlist", Toast.LENGTH_SHORT).show();
            }else{
                v.setBackgroundResource(R.drawable.ic_favorite_black_24dp);
                Toast.makeText(context, "Added to Wishlist", Toast.LENGTH_SHORT).show();
            }
            isPlay = !isPlay; // reverse
        }
    };




    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final DetailProductListGetSet listItem = listItems.get(position);

        holder.pro_name.setText(listItem.getProduct_name());
        String act_price = ("Rs " + listItem.getPrice());
        holder.act_price.setText(act_price);

        String dis_price = ("Rs " + listItem.getSpecial_price());

        holder.dis_price.setText(dis_price);
        Log.d("LISTITEMS", "onBindViewHolder: "+listItem);
        //String imgUrl = listItem.getImages();

        //String[] allIdsArray = TextUtils.split(imgUrl, ",");
//        ArrayList<String> idsList = new ArrayList<>(Arrays.asList(allIdsArray));
//        for(String element : idsList){
//            Log.i("Result", element);
//            Picasso.get()
//                    .load(BaseUrlForProduct2+idsList.get(0))
//                    .into(holder.pro_images);
//        }

        // for procduct off precentage
        int pro_specialpriceInt = Integer.parseInt(listItem.getPrice());
        int act_priceInt = Integer.parseInt(listItem.getSpecial_price());
        double perOffer = (Math.abs((((double) pro_specialpriceInt / act_priceInt) *100) -100));
        int offP = (int)perOffer;
        Log.d("offP-",""+offP+" % off");
        holder.off_per.setText(""+offP+" % off");




        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show();

//                NoteDatabase ud = NoteDatabase.getInstance(context);
//
//                //init the entity
//                Note note = new Note();
//                note.setPname("yes");
//                note.setPprice("40");
//                note.setPquantity("5");
//                note.setPweight("100");
//                note.setPriority(1);
//
//                //init dao and perform operation
//                NoteDao dao = ud.noteDao();
//                dao.insert(note);

//                Intent intent = new Intent(context, DetailActivity.class);
//                intent.putExtra("catproid",listItem.getId());
//                context.startActivity(intent);

                Intent i = new Intent(context, DetailActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra(DetailActivity.KEY_ID,listItem.getId());
                context.startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

//    @Override
//    public void onClick(View v) {
//        Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public boolean onLongClick(View v) {
//        return false;
//    }



    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    listItemsFiltered = listItems;
                } else {
                    List<DetailProductListGetSet> filteredList = new ArrayList<>();
                    for (DetailProductListGetSet movie : listItems) {
                        if (movie.getProduct_name().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(movie);
                        }
                    }
                    listItemsFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = listItemsFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listItemsFiltered = (ArrayList<DetailProductListGetSet>) filterResults.values;

                notifyDataSetChanged();
            }
        };
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        //public TextView id;
        public TextView pro_name;
        public TextView act_price;
        public TextView dis_price;
        public TextView off_per;
        public ImageView pro_images;
        public Spinner spinner;


        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            //id = itemView.findViewById(R.id.id);
            pro_name = itemView.findViewById(R.id.name);
            act_price = itemView.findViewById(R.id.original_price);
            dis_price = itemView.findViewById(R.id.offer_price);
            pro_images = itemView.findViewById(R.id.product_image);
            act_price.setPaintFlags(act_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            off_per = itemView.findViewById(R.id.off_per);

        }

    }
}
