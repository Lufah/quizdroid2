package jkluu1.washington.edu.quizdroid2;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by JenniferLuu on 2/12/15.
 */
public class OverviewFragment extends Fragment {

    private String category;
    private HashMap<String, List<String>> questions;
    private String[] allQuestions;
    private int score;
    private int qNumber;

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        View overviewFragmentView = inflater.inflate(R.layout.overview_fragment, container, false);

        Bundle bundle = this.getArguments();
        category = bundle.getString("category");
        questions = (HashMap<String, List<String>>) bundle.getSerializable("questions");
        allQuestions = bundle.getStringArray("allQuestions");
        score = bundle.getInt("score");
        qNumber = bundle.getInt("qNumber");


        TextView title = (TextView) overviewFragmentView.findViewById(R.id.title);
        title.setTextSize(40);
        title.setText(category);

        TextView summary = (TextView) overviewFragmentView.findViewById(R.id.overviewMsg);
        getMessage(summary, category);

        TextView questionsOverview = (TextView) overviewFragmentView.findViewById(R.id.numOfQuestions);
        questionsOverview.setText("");
        String num = "There will be " + allQuestions.length + " question(s).";
        questionsOverview.setText(num);

        score = 0;
        qNumber = 0;

        Button btn = (Button) overviewFragmentView.findViewById(R.id.submit);

        btn.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("category", category);
                bundle.putSerializable("questions", questions);
                bundle.putStringArray("allQuestions", allQuestions);
                bundle.putInt("score", score);
                bundle.putInt("qNumber", qNumber);

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                QuestionsFragment questionsFragment = new QuestionsFragment();
                questionsFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.allFragments, questionsFragment);
                //fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return overviewFragmentView;
    }

    public void getMessage(TextView summary, String c) {
        String category = c;
        summary.setText("");
        if (category.equals("Math")) {
            summary.setText("This category is about numbers.");
        } else if (category.equals("Physics")) {
            summary.setText("This category is about physics.");
        } else if (category.equals("Marvel Super Heroes")) {
            summary.setText("This category is about Marvel super heroes.");
        }

    }





}
