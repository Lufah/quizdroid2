package jkluu1.washington.edu.quizdroid2;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends ActionBarActivity {
    public final static String EXTRA_MESSAGE = "edu.washington.jkluu1.quizdroid.MESSAGE";
    private HashMap<String, List<String>> questions;
    private String[] allQuestions;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ListView listView = (ListView) findViewById(R.id.listView);
        String[] names = new String[] {"Math", "Physics", "Marvel Super Heroes"};


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, names);
        // android prefixed because we are referencing a layout in the Android system
        // One text view, and will take the one item and display it there
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("ListView", "Position " + position + "pressed");
                // controller logic to say what was pressed
                String category = (String) parent.getItemAtPosition(position);
                addQuestions(category);

                Intent intent = new Intent(MainActivity.this, QuizBody.class);

                //intent.putExtra("category", category);
                intent.putExtra("questions", questions);
                intent.putExtra("category", category);
                intent.putExtra("allQuestions", allQuestions);
                startActivity(intent);

            }
        });
    }


    private void addQuestions(String category) {
        questions = new HashMap<String, List<String>>();
        //HashMap<String, List<String>> questions = new HashMap<String, List<String>>();
        List<String> answers = new ArrayList<String>();
        List<String> answers2 = new ArrayList<String>();

        if (category.equals("Math")) {
            // answers.add("Eleven");
            answers.add("3");
            answers.add("One");
            answers.add("Two");
            answers.add("Eleven");
            answers.add("Twelve");
            questions.put("What is 1 + 10?", answers);

            answers2.add("1");
            answers2.add("Because seven ate nine");
            answers2.add("Because seven");
            answers2.add("Numbers are incapable of recreating the concept of fear");
            answers2.add("Because six, seven ate");
            questions.put("Why was six afraid of seven?", answers2);


        } else if (category.equals("Physics")) {
            //answers.add("Change in velocity over change in time");
            answers.add("1");
            answers.add("Change in velocity over change in time");
            answers.add("Running fast");
            answers.add("Not possible");
            answers.add("By multiplying the density by the force");
            questions.put("How is acceleration calculated?", answers);

        } else if (category.equals("Marvel Super Heroes")) {
            // answers.add("Marvel vs. Capcom 2");
            answers.add("2");
            answers.add("Ultimate Alliance");
            answers.add("Marvel vs. Capcom 2");
            answers.add("The movie");
            answers.add("Thor");
            questions.put("What is the best Marvel Game?", answers);
        }

        allQuestions = new String[questions.size()];
        int count = 0;
        for (String qs : questions.keySet()) {
            allQuestions[count] = qs;
            count++;
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
