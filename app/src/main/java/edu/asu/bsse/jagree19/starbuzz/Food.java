package edu.asu.bsse.jagree19.starbuzz;

/**
 * Created by justingreene on 3/9/19.
 */

public class Food {

    private String name;
    private String description;
    private int imageResourceId;

    //drinks is an array of Drinks
    public static final Food[] foods = {
            new Food("Supreme Pizza", "A Pizza with everything delicious on it", R.drawable.diavolo),
            new Food("Mushroom Pizza", "A funghal delight, mushrooms everywhere", R.drawable.funghi),
            new Food("Sicilian Pizza", "A thick crust sheet style pizza topped to your tastes", R.drawable.restaurant)
    };

    //Drink constructor
    //Each Drink has a name, description, and an image resource id
    private Food(String name, String description, int imageResourceId) {
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
