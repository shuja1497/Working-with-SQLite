package shuja1497.starbuzz2;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


//ListActiivty contains a listview on its own and also an event listener
public class DrinksCategoryActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //no need to use setContentView..list activity define their own layout
        //setContentView(R.layout.activity_drinks_category);


        ListView listDrinks = getListView();

        ArrayAdapter<Drink> listAdapter = new ArrayAdapter<Drink>(this,android.R.layout.simple_list_item_1, Drink.drinks);
        listDrinks.setAdapter(listAdapter);

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Intent i  =new Intent(this,DrinkActivity.class);
        i.putExtra(DrinkActivity.EXTRA_DRINKNO,(int) id);
        startActivity(i);
    }
}
