package edu.asu.bsse.jagree19.starbuzz;

/**
 * Created by justingreene on 3/9/19.
 */

public class Drink {

    private String name;
    private String description;
    private int imageResourceId;

    //drinks is an array of Drinks
    public static final Drink[] drinks = {
            new Drink("Latte", "A couple of shots of espresso with steamed milk", R.drawable.latte),
            new Drink("Cappuccino", "Espresso, steamed milk, and steamed milk foam", R.drawable.cappuccino),
            new Drink("Filter", "Highest quality beans roasted and brewed fresh", R.drawable.filter)
    };

    //Drink constructor
    //Each Drink has a name, description, and an image resource id
    private Drink(String name, String description, int imageResourceId) {
        this.name = name;
        this.description = description;
        this.imageResourceId = imageResourceId;
    }

    //Getters
    public String getDescription()
    {
        return description;
    }

    public String getName()
    {
        return name;
    }

    public int getImageResourceId()
    {
        return imageResourceId;
    }

    //String representation of name
    public String toString()
    {
        return this.name;
    }
}
