package edu.asu.bsse.jagree19.starbuzz;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class StoresActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stores);

        //populate the drink description
        TextView desscription = (TextView) findViewById(R.id.description);
        desscription.setText("MapView planned for store locations");
    }
}
