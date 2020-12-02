package com.example.skmall;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.skmall.Models.GroceryItem;
import com.example.skmall.Models.Review;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Utils {
    private static int id = 0;
    private static final String TAG = "Utils";
    private static final String DATA_BASE_NAME = "fake_database";
    private Context context;
    private static int order_id=0;

    public static int getOrder_id() {
        order_id++;
        return order_id;
    }

    public Utils(Context context) {
        this.context = context;
    }

    public ArrayList<Review> getReviewForItem(int id) {
        Log.d(TAG, "getReviewForItem: started");
        SharedPreferences sharedPreferences = context.getSharedPreferences(DATA_BASE_NAME, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<GroceryItem>>() {
        }.getType();
        ArrayList<GroceryItem> items = gson.fromJson(sharedPreferences.getString("allItems", null), type);
        if (items != null) {
            for (GroceryItem item : items) {
                if (item.getId() == id) {
                    return item.getReviews();
                }
            }
        }
        return null;
    }

    public static int getId() {
        id++;
        return id;
    }

    public boolean addReview(Review review) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(DATA_BASE_NAME, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<GroceryItem>>() {
        }.getType();
        ArrayList<GroceryItem> items = gson.fromJson(sharedPreferences.getString("allItems", ""), type);
        if (items != null) {
            ArrayList<GroceryItem> newItems = new ArrayList<>();
            for (GroceryItem item : items) {
                if (item.getId() == review.getGroceryItemId()) {
                    ArrayList<Review> reviews = item.getReviews();
                    reviews.add(review);
                    item.setReviews(reviews);
                }

                newItems.add(item);
            }
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("allItems", gson.toJson(newItems));
            editor.apply();
            return true;
        }
        return false;
    }

    public void updateRate(GroceryItem item, int newRate) {
        Log.d(TAG, "updateRate: started");
        SharedPreferences sharedPreferences = context.getSharedPreferences(DATA_BASE_NAME, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<GroceryItem>>() {
        }.getType();
        ArrayList<GroceryItem> items = gson.fromJson(sharedPreferences.getString("allItems", ""), type);
        if (items != null) {
            ArrayList<GroceryItem> newItems = new ArrayList<>();
            for (GroceryItem item1 : items) {
                if (item.getId() == item1.getId()) {
                    item1.setRate(newRate);
                }
                newItems.add(item1);
            }
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("allItems", gson.toJson(newItems));
            editor.apply();
        }

    }

    public void AddItemToCart(int id) {
        Log.d(TAG, "AddItemToCart: started");

        SharedPreferences sharedPreferences = context.getSharedPreferences(DATA_BASE_NAME, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Integer>>() {
        }.getType();
        ArrayList<Integer> cartitems = gson.fromJson(sharedPreferences.getString("CartItems", null), type);
        if (cartitems == null) {
            cartitems = new ArrayList<>();

        }

        cartitems.add(id);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("CartItems", gson.toJson(cartitems));
        editor.commit();
    }

    public void initDatabase() {
        Log.d(TAG, "initDatabase: started");
        SharedPreferences sharedPreferences = context.getSharedPreferences(DATA_BASE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        Type type = new TypeToken<ArrayList<GroceryItem>>() {
        }.getType();

        ArrayList<GroceryItem> possibleItems = gson.fromJson(sharedPreferences.getString("allItems", ""), type);
        if (possibleItems == null) {
            initAllItems();
        }


    }

    private void initAllItems() {
        Log.d(TAG, "initAllItems: started");
        SharedPreferences sharedPreferences = context.getSharedPreferences(DATA_BASE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();


        ArrayList<GroceryItem> allItems = new ArrayList<>();

        GroceryItem iceCream = new GroceryItem("ice Cream", "cool and tassty", "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSq1LQgITDDTRz0d39mkelRZLkCHz31M9zCa2eETHZqY9MNCVIV&usqp=CAU",
                "Food", 10, 20);


        allItems.add(new GroceryItem("cheese", "fresh and pure",
                "https://www.midwestfarmreport.com/wp-content/uploads/2020/01/shutterstock_796026844-scaled.jpg", "food", 10, 20.0));
        allItems.add(new GroceryItem("Cucumber", "fresh and pure",
                "https://www.healthline.com/hlcmsresource/images/AN_images/AN88-Cucumbers-732x549-thumb.jpg", "vegetables", 100, 5.0));
        allItems.add(new GroceryItem("Coco Cola", "quality drink",
                "https://www.coca-colaindia.com/content/dam/journey/in/en/private/our-brands/coca-cola/India-prod-info.rendition.300.270.jpg", "drinks", 20, 20));
        allItems.add(new GroceryItem("Spaghetti", "easy made meal",
                "https://i.ytimg.com/vi/ErEy38dcCVg/maxresdefault.jpg", "food", 20, 30.0));
        allItems.add(new GroceryItem("Chicken", "good for health", "https://thestayathomechef.com/wp-content/uploads/2016/06/Fried-Chicken-4-1.jpg", "food", 10, 200.0));
        allItems.add(new GroceryItem("Shampoo", "Anti dandruff",
                "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/hbz-drugstore-shampoo-index-1564084845.jpg?crop=1.00xw:1.00xh;0,0&resize=1200:*", "hygiene", 20, 50.0));
        allItems.add(new GroceryItem("DeoDorant", "good for boys",
                "https://rukminim1.flixcart.com/image/352/352/jy0frm80/deodorant/g/f/p/400-hamilton-deodorant-body-spray-denver-men-original-imafgb9fsafampwz.jpeg?q=70", "hygiene", 5, 250.0));
        allItems.add(new GroceryItem("Soap", "with moisture included",
                "https://rukminim1.flixcart.com/image/352/352/jyg5lzk0/soap/q/g/9/5-375-oil-clear-glow-soap-bar-75-gms-pack-of-5-pears-original-imafhmwfhus6hnzf.jpeg?q=70", "hygiene", 20, 40.0));
        allItems.add(iceCream);
        String finalString = gson.toJson(allItems);
        editor.putString("allItems", finalString);
        editor.commit();

    }

    public ArrayList<GroceryItem> getAllItems() {
        Gson gson = new Gson();
        SharedPreferences sharedPreferences = context.getSharedPreferences(DATA_BASE_NAME, context.MODE_PRIVATE);
        Type type = new TypeToken<ArrayList<GroceryItem>>() {
        }.getType();
        ArrayList<GroceryItem> AllItems = gson.fromJson(sharedPreferences.getString("allItems", null), type);
        return AllItems;
    }

    public ArrayList<String> getAllCategories() {
        Log.d(TAG, "getThreeCategories: started");
        SharedPreferences sharedPreferences = context.getSharedPreferences(DATA_BASE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<GroceryItem>>() {
        }.getType();
        ArrayList<GroceryItem> allitems = gson.fromJson(sharedPreferences.getString("allItems", ""), type);
        ArrayList<String> categories = new ArrayList<>();
        if (null != allitems) {
            for (int i = 0; i < allitems.size(); i++) {

                boolean flag = false;
                for (String s : categories) {
                    if (allitems.get(i).getCategory().equalsIgnoreCase(s)) {
                        flag = true;
                    }
                }
                if (flag != true) {
                    categories.add(allitems.get(i).getCategory());
                }

            }
        }
        return categories;
    }


    public ArrayList<GroceryItem> searchForItem(String text) {

        Log.d(TAG, "searchForItem: started");

        SharedPreferences sharedPreferences = context.getSharedPreferences(DATA_BASE_NAME, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<GroceryItem>>() {
        }.getType();

        ArrayList<GroceryItem> allItems = gson.fromJson(sharedPreferences.getString("allItems", ""), type);
        ArrayList<GroceryItem> searchItems = new ArrayList<>();
        if (allItems != null) {
            for (GroceryItem item : allItems) {
                if (item.getName().equalsIgnoreCase(text)) {
                    searchItems.add(item);
                }

                String[] splittedString = item.getName().split(" ");
                for (int i = 0; i < splittedString.length; i++) {


                    if (splittedString[i].equalsIgnoreCase(text)) {
                        boolean flag = false;
                        for (GroceryItem search : searchItems) {
                            if (search.equals(item)) {
                                flag = true;

                            }

                        }

                        if (!flag) {
                            searchItems.add(item);
                        }

                    }
                }
            }
        }
        return searchItems;
    }

    public ArrayList<String> getThreeCategories() {
        Log.d(TAG, "getThreeCategories: started");
        SharedPreferences sharedPreferences = context.getSharedPreferences(DATA_BASE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<GroceryItem>>() {
        }.getType();
        ArrayList<GroceryItem> allitems = gson.fromJson(sharedPreferences.getString("allItems", ""), type);
        ArrayList<String> categories = new ArrayList<>();
        if (null != allitems) {
            for (int i = 0; i < allitems.size(); i++) {
                if (categories.size() < 3) {
                    boolean flag = false;
                    for (String s : categories) {
                        if (allitems.get(i).getCategory().equalsIgnoreCase(s)) {
                            flag = true;
                        }
                    }
                    if (flag != true) {
                        categories.add(allitems.get(i).getCategory());
                    }
                }
            }
        }
        return categories;
    }

    public ArrayList<GroceryItem> getItemsByCategoryName(String name) {
        Log.d(TAG, "getThreeCategories: started");
        SharedPreferences sharedPreferences = context.getSharedPreferences(DATA_BASE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<GroceryItem>>() {
        }.getType();
        ArrayList<GroceryItem> allItems = gson.fromJson(sharedPreferences.getString("allItems", ""), type);
        ArrayList<GroceryItem> categoriesItems = new ArrayList<>();

        for (GroceryItem item : allItems) {
            if (item.getCategory().equalsIgnoreCase(name)) {
                categoriesItems.add(item);
            }
        }
        return categoriesItems;
    }

    public ArrayList<Integer> getCartItems(){
        Log.d(TAG, "getCartItems: started");

        SharedPreferences sharedPreferences = context.getSharedPreferences(DATA_BASE_NAME, Context.MODE_PRIVATE);

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Integer>>() {
        }.getType();
        ArrayList<Integer> CartItems = gson.fromJson(sharedPreferences.getString("CartItems", null), type);
        return CartItems;
    }

    public ArrayList<Integer> deleteCartItem(GroceryItem item1){
        Log.d(TAG, "deleteCartItem: started");
        SharedPreferences sharedPreferences = context.getSharedPreferences(DATA_BASE_NAME, Context.MODE_PRIVATE);

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Integer>>() {
        }.getType();
        ArrayList<Integer> CartItems = gson.fromJson(sharedPreferences.getString("CartItems", null), type);
        ArrayList<Integer> newitems=new ArrayList<>();
        if(CartItems!=null){

            for(int i:CartItems){
                if (item1.getId()!=i){
                    newitems.add(i);
                }
            }
            SharedPreferences.Editor editor2=sharedPreferences.edit();
            editor2.putString("CartItems",gson.toJson(newitems));
            editor2.commit();
        }

    return newitems;
    }

    public  ArrayList<GroceryItem> getItemsByID(ArrayList<Integer> ids){
        Log.d(TAG, "getItemsByID: started");
        SharedPreferences sharedPreferences = context.getSharedPreferences(DATA_BASE_NAME, Context.MODE_PRIVATE);

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<GroceryItem>>() {
        }.getType();
        ArrayList<GroceryItem> allItems = gson.fromJson(sharedPreferences.getString("allItems", null), type);
        ArrayList<GroceryItem> result=new ArrayList<>();
        for(int id:ids){
            if(null!=allItems){
                for (GroceryItem item :allItems){
                    if(item.getId()==id){
                        result.add(item);
                    }
                }
            }
        }
        return result;
    }

    public void RemoveCartItems(){
        Log.d(TAG, "RemoveCartItems: started");
        SharedPreferences sharedPreferences = context.getSharedPreferences(DATA_BASE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        Gson gson = new Gson();
        editor.remove("CartItems");
        editor.apply();
    }

    public  void addPopularityPoint(ArrayList<Integer> items){
        Log.d(TAG, "addPopularityPoint: ");
        SharedPreferences sharedPreferences = context.getSharedPreferences(DATA_BASE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        Type type = new TypeToken<ArrayList<GroceryItem>>() {
        }.getType();

        Gson gson = new Gson();
        ArrayList<GroceryItem> allItems = gson.fromJson(sharedPreferences.getString("allItems", null), type);
        ArrayList<GroceryItem> newItems=new ArrayList<>();
        for (GroceryItem i:allItems
             ) {
            boolean doesExist=false;
            for(int j:items){
                if(i.getId()==j){
                    doesExist=true;
                }

            }
            if (doesExist){
                i.setPopularityPoint(i.getPopularityPoint()+1);
            }
            newItems.add(i);
        }
        editor.putString("allItems",gson.toJson(newItems));
        editor.apply();
    }

}

