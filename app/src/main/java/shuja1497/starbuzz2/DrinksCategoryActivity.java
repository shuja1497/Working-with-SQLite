package shuja1497.starbuzz2;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;


//ListActiivty contains a listview on its own and also an event listener
public class DrinksCategoryActivity extends ListActivity {

    private SQLiteDatabase db;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //no need to use setContentView..list activity define their own layout
        //setContentView(R.layout.activity_drinks_category);

        // in place of using arrayadapter ..cursoradapter will be used ..
        //arrayadap can be used for amall database but for larger db it will take more memory and hence more time also
        //cursoradapter reads only the data it needs


        ListView listDrinks = getListView();

       try{
           SQLiteOpenHelper starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(this);
           db = starbuzzDatabaseHelper.getReadableDatabase();

           cursor = db.query("DRINK",
                   new String[]{"_id", "NAME"},
                   null, null, null, null, null);
           CursorAdapter listAdapter  = new SimpleCursorAdapter(this,
                   android.R.layout.simple_list_item_1,
                   cursor,
                   new String[]{"NAME"},
                   new int[]{android.R.id.text1},
                   0);
           listDrinks.setAdapter(listAdapter);
       }

       catch(SQLiteException e){

           Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
           toast.show();
       }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        cursor.close();
        db.close();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Intent i  =new Intent(this,DrinkActivity.class);
        i.putExtra(DrinkActivity.EXTRA_DRINKNO,(int) id);
        startActivity(i);
    }
}
