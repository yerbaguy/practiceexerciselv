package com.bartoszmaliszewski.practiceexerciselv;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class WordListActivity extends AppCompatActivity {

    TextView textViewPracticeExercise;

    private DBManager dbManager;

    private ListView listView;

    private SimpleCursorAdapter adapter;

    // final String[] from = new String[] { DatabaseHelper.ID, DatabaseHelper.engword, DatabaseHelper.plword };

    final String[] from = new String[] { DatabaseHelper._id, DatabaseHelper.engword, DatabaseHelper.plword };

    // final String[] from = new String[] { DatabaseHelper.engword, DatabaseHelper.plword };


    final int[] to = new int[] { R.id.textViewId, R.id.textViewEngWord, R.id.textViewPlWord };

    //  final int[] to = new int[] { R.id.textViewEngWord, R.id.textViewPlWord };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_word_list);

        setContentView(R.layout.fragment_emp_list);


        dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();

        listView = (ListView) findViewById(R.id.list_view);
        // listView.setEmptyView(findViewById(R.id.empty));

        adapter = new SimpleCursorAdapter(this, R.layout.activity_view_layout, cursor, from, to, 0);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                TextView idTextView = (TextView) view.findViewById(R.id.textViewId);
                TextView engWordTextView = (TextView) view.findViewById(R.id.textViewEngWord);
                TextView plWordTextView = (TextView) view.findViewById(R.id.textViewPlWord);


                String id = idTextView.getText().toString();
                String engword = engWordTextView.getText().toString();
                String plword = plWordTextView.getText().toString();

                Intent modify_intent = new Intent(getApplicationContext(), ModifyWordListActivitylv.class);

                modify_intent.putExtra("id", id);
                modify_intent.putExtra("engword", engword);
                modify_intent.putExtra("plword", plword);

                startActivity(modify_intent);




            }
        });






        textViewPracticeExercise = (TextView) findViewById(R.id.textViewPracticeExercise);


        //     textViewPracticeExercise.setOnClickListener(new View.OnClickListener() {
        //         @Override
        //         public void onClick(View view) {

        //             Intent intent = new Intent(getApplicationContext(), PracticeActivity.class);
        //             startActivity(intent);
        //         }
        //     });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.practice) {

            Intent intent_practice_exercise = new Intent(this, PracticeExerciselvActivity.class);
            startActivity(intent_practice_exercise);
        }

        return super.onOptionsItemSelected(item);
    }
}
