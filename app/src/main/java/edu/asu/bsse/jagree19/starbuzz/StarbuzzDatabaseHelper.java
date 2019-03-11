package edu.asu.bsse.jagree19.starbuzz;

/**
 * Created by justingreene on 3/10/19.
 */

import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class StarbuzzDatabaseHelper extends SQLiteOpenHelper{

    private static final String DB_NAME = "starbuzz2"; // name of database
    private static final int DB_VERSION = 2; // the version of the database

    StarbuzzDatabaseHelper (Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate (SQLiteDatabase db)
    {
        updateMyDatabase(db,0,DB_VERSION);
    }

    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion)
    {
        updateMyDatabase(db, oldVersion, newVersion);
    }

    private static void insertDrink(SQLiteDatabase db, String name, String description, int resourceId)
    {
        ContentValues drinkValues = new ContentValues();
        drinkValues.put("NAME", name);
        drinkValues.put("Description", description);
        drinkValues.put("IMAGE_RESOURCE_ID", resourceId);
        db.insert("DRINK", null, drinkValues);
    }

    private static void insertFood(SQLiteDatabase db, String name, String description, int resourceId)
    {
        ContentValues foodValues = new ContentValues();
        foodValues.put("NAME", name);
        foodValues.put("Description", description);
        foodValues.put("IMAGE_RESOURCE_ID", resourceId);
        db.insert("FOOD", null, foodValues);
    }

    private static void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        if (oldVersion < 1)
        {
            db.execSQL("CREATE TABLE DRINK (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NAME TEXT , "
                    + "DESCRIPTION TEXT, "
                    + "IMAGE_RESOURCE_ID INTEGER);");
            db.execSQL("CREATE TABLE FOOD (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NAME TEXT , "
                    + "DESCRIPTION TEXT, "
                    + "IMAGE_RESOURCE_ID INTEGER);");
            insertDrink(db, "Latte", "Espresso and steamed milk", R.drawable.latte);
            insertDrink(db, "Cappuccino", "Espresso, steamed milk, and steamed milk foam", R.drawable.cappuccino);
            insertDrink(db, "Filter", "Our best drip coffee", R.drawable.filter);
            insertFood(db, "Supreme Pizza", "A Pizza with everything delicious on it", R.drawable.diavolo);
            insertFood(db, "Mushroom Pizza", "A funghal delight, mushrooms everywhere", R.drawable.funghi);
            insertFood(db, "Sicilian Pizza", "A thick crust sheet style pizza topped to your tastes", R.drawable.restaurant);
        }
        if (oldVersion < 2)
        {
            db.execSQL("ALTER TABLE DRINK ADD COLUMN FAVORITE NUMERIC;");
            db.execSQL("ALTER TABLE FOOD ADD COLUMN FAVORITE NUMERIC;");
        }
    }
}
