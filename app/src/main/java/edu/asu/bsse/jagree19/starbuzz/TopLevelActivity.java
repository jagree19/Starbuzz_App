package edu.asu.bsse.jagree19.starbuzz;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import android.view.View;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteDatabase;

public class TopLevelActivity extends Activity {

    private SQLiteDatabase db;
    private Cursor favoritesCursor;
    private Cursor favoriteFoodsCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_level);
        setUpOptionsListView();
        setUpFavoritesListView();
        setUpFavoriteFoodsListView();
    }

    private void setUpOptionsListView() {
        //Create an OnItemClickListener
        AdapterView.OnItemClickListener itemClickListener =
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> listView,
                                            View itemview,
                                            int position,
                                            long id) {
                        if (position == 0) {
                            Intent intent = new Intent(TopLevelActivity.this,
                                    DrinkCategoryActivity.class);
                            startActivity(intent);
                        } else if (position == 1) {
                            Intent intent = new Intent(TopLevelActivity.this,
                                    FoodCategoryActivity.class);
                            startActivity(intent);
                        } else if (position == 2) {
                            Intent intent = new Intent(TopLevelActivity.this,
                                    StoresActivity.class);
                            startActivity(intent);
                        }

                    }
                };

        //Add the listener to the list view
        ListView listView = (ListView) findViewById(R.id.list_options);
        listView.setOnItemClickListener(itemClickListener);
    }

    private void setUpFavoritesListView() {
        //Populate the list favorites from drink cursor
        ListView listFavorites = (ListView)findViewById(R.id.list_favorites);
        try {
            SQLiteOpenHelper starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(this);
            db = starbuzzDatabaseHelper.getReadableDatabase();
            favoritesCursor = db.query("DRINK",
                    new String[]{"_id", "NAME"},
                    "FAVORITE = 1",
                    null, null, null, null);
            CursorAdapter favoriteAdapter = new SimpleCursorAdapter(TopLevelActivity.this,
                    android.R.layout.simple_list_item_1,
                    favoritesCursor,
                    new String[]{"NAME"},
                    new int[]{android.R.id.text1}, 0);
            listFavorites.setAdapter(favoriteAdapter);
        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this,"Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }

        //Navigate to DrinkActivity if drink is clicked
        listFavorites.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView,
                                    View v,
                                    int position,
                                    long id) {
                Intent intent = new Intent(TopLevelActivity.this, DrinkActivity.class);
                intent.putExtra(DrinkActivity.EXTRA_DRINKID, (int)id);
                startActivity(intent);

            }
        });
    }

    private void setUpFavoriteFoodsListView() {
        //Populate the list favorites from drink cursor
        ListView listFavorites = (ListView)findViewById(R.id.list_favoriteFoods);
        try {
            SQLiteOpenHelper starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(this);
            db = starbuzzDatabaseHelper.getReadableDatabase();
            favoriteFoodsCursor = db.query("Food",
                    new String[]{"_id", "NAME"},
                    "FAVORITE = 1",
                    null, null, null, null);
            CursorAdapter favoriteAdapter = new SimpleCursorAdapter(TopLevelActivity.this,
                    android.R.layout.simple_list_item_1,
                    favoriteFoodsCursor,
                    new String[]{"NAME"},
                    new int[]{android.R.id.text1}, 0);
            listFavorites.setAdapter(favoriteAdapter);
        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this,"Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }

        //Navigate to DrinkActivity if drink is clicked
        listFavorites.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView,
                                    View v,
                                    int position,
                                    long id) {
                Intent intent = new Intent(TopLevelActivity.this, FoodActivity.class);
                intent.putExtra(FoodActivity.EXTRA_FOODID, (int)id);
                startActivity(intent);

            }
        });
    }

    @Override
    public void onRestart() {
        super.onRestart();
        Cursor newDrinkCursor = db.query("DRINK",
                new String[]{"_id", "NAME"},
                "FAVORITE = 1",
                null,null,null,null);
        Cursor newFoodCursor =db.query("FOOD",
                new String[]{"_id", "NAME"},
                "FAVORITE = 1",
                null,null,null,null);
        ListView listDrinkFavorites = (ListView)findViewById(R.id.list_favorites);
        ListView listFoodFavorites = (ListView)findViewById(R.id.list_favoriteFoods);
        CursorAdapter drinkAdapter = (CursorAdapter)listDrinkFavorites.getAdapter();
        CursorAdapter foodAdapter = (CursorAdapter)listFoodFavorites.getAdapter();
        drinkAdapter.changeCursor(newDrinkCursor);
        favoritesCursor = newDrinkCursor;
        foodAdapter.changeCursor(newFoodCursor);
        favoriteFoodsCursor = newFoodCursor;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        favoritesCursor.close();
        favoriteFoodsCursor.close();
        db.close();
    }
}
