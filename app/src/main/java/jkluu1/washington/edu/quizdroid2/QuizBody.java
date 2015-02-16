package jkluu1.washington.edu.quizdroid2;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.HashMap;
import java.util.List;


public class QuizBody extends Activity {
    private String category;
    private HashMap<String, List<String>> questions;
    private String[] allQuestions;
    private int score;
    private int qNumber;
    private int selected;
    private String correctAnswer;
    private boolean finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_body);

        Intent intent = getIntent();
        questions = (HashMap<String, List<String>>) intent.getSerializableExtra("questions");
        allQuestions = (String[]) intent.getSerializableExtra("allQuestions");
        category = intent.getStringExtra("category");

        Bundle bundle = new Bundle();
        bundle.putString("category", category);
        bundle.putSerializable("questions", questions);
        bundle.putStringArray("allQuestions", allQuestions);
        bundle.putInt("score", score);
        bundle.putInt("qNumber", qNumber);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        OverviewFragment overviewFragment = new OverviewFragment();
        overviewFragment.setArguments(bundle);
        fragmentTransaction.add(R.id.allFragments, overviewFragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz_body, menu);
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
