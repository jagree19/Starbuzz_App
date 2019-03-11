package edu.asu.bsse.jagree19.starbuzz;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.ContentValues;
import android.view.View;

public class FoodActivity extends Activity {

    public static final String EXTRA_FOODID = "foodId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        //get the food from the intent
        int foodId = (Integer)getIntent().getExtras().get(EXTRA_FOODID);

        //Create a cursor
        SQLiteOpenHelper starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(this);
        try {
            SQLiteDatabase db = starbuzzDatabaseHelper.getReadableDatabase();
            Cursor cursor = db.query("Food",
                    new String[] {"NAME", "DESCRIPTION", "IMAGE_RESOURCE_ID", "FAVORITE"},
                    "_id=?",
                    new String[] {Integer.toString(foodId)},
                    null, null, null);

            //Move to the first record in cursor (need to do even if only one record)
            if (cursor.moveToFirst()) {
                //Get the drink details from the cursor
                String nameText = cursor.getString(0);
                String descriptionText = cursor.getString(1);
                int photoId = cursor.getInt(2);
                boolean isFavorite = (cursor.getInt(3) == 1);

                //populate the food name
                TextView name = (TextView)findViewById(R.id.name);
                name.setText(nameText);

                //populate the food description
                TextView desscription = (TextView) findViewById(R.id.description);
                desscription.setText(descriptionText);

                //populate the food image
                ImageView photo = (ImageView) findViewById(R.id.photo);
                photo.setImageResource(photoId);
                photo.setContentDescription(nameText);

                //populate the favorite checkbox
                CheckBox favorite = (CheckBox) findViewById(R.id.favorite);
                favorite.setChecked(isFavorite);
            }
            cursor.close();
            db.close();
        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void onFavoriteClicked(View view) {
        int foodId = (Integer) getIntent().getExtras().get(EXTRA_FOODID);

        //Get the values of the checkbox
        CheckBox favorite = (CheckBox) findViewById(R.id.favorite);
        ContentValues foodValues = new ContentValues();
        foodValues.put("FAVORITE", favorite.isChecked());

        //Get a reference to the database and update the FAVORITE column
        SQLiteOpenHelper starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(this);
        try {
            SQLiteDatabase db = starbuzzDatabaseHelper.getReadableDatabase();
            db.update("FOOD",
                    foodValues,
                    "_id = ?",
                    new String[] {Integer.toString(foodId)});
            db.close();
        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this,"Database Unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
