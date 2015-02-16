package jkluu1.washington.edu.quizdroid2;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
public class AnswersFragment extends Fragment {

    private String category;
    private HashMap<String, List<String>> questions;
    private String[] allQuestions;
    private int score;
    private int qNumber;
    private int selected;
    private String correctAnswer;
    private boolean isFinished;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View answersFragmentView = inflater.inflate(R.layout.answers_fragment, container, false);

        Bundle bundle = this.getArguments();
        category = bundle.getString("category");
        questions = (HashMap<String, List<String>>) bundle.getSerializable("questions");
        allQuestions = bundle.getStringArray("allQuestions");
        score = bundle.getInt("score");
        qNumber = bundle.getInt("qNumber");
        selected = bundle.getInt("selected");
        correctAnswer = bundle.getString("correctAnswer");

        TextView r = (TextView) answersFragmentView.findViewById(R.id.results);

        TextView c = (TextView) answersFragmentView.findViewById(R.id.correct);
        TextView i = (TextView) answersFragmentView.findViewById(R.id.incorrect);

        String key = allQuestions[qNumber];

        if (correctAnswer.equals(questions.get(key).get(selected))) {
            score++;
            r.setText("Correct!!");
        } else {
            r.setText("Sorry, that was incorrect.");
            i.setText("Your response was: " + questions.get(key).get(selected));
        }

        c.setText("The correct Answer was : " + correctAnswer);

        TextView state = (TextView) answersFragmentView.findViewById(R.id.status);
        state.setText("You have gotten " + score + " correct out of " + allQuestions.length);
        qNumber++;

        Button b = (Button) answersFragmentView.findViewById(R.id.submit);
        if (allQuestions.length >= (qNumber + 1)) {
            b.setText("Next");
            isFinished = false;
        } else {
            b.setText("Finish");
            isFinished = true;
        }

        btnSetup(answersFragmentView);

        return answersFragmentView;
    }

    public void btnSetup(View answersFragmentView) {
        Button btn = (Button) answersFragmentView.findViewById(R.id.submit);

        btn.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                if (isFinished) {

                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                    getActivity().finish();

                } else {
                    Bundle bundle = new Bundle();
                    bundle.putString("category", category);
                    bundle.putSerializable("questions", questions);
                    bundle.putStringArray("allQuestions", allQuestions);
                    bundle.putInt("score", score);
                    bundle.putInt("qNumber", qNumber);
                    bundle.putInt("selected", selected);
                    bundle.putString("correctAnswer", correctAnswer);

                    QuestionsFragment questionsFragment = new QuestionsFragment();
                    questionsFragment.setArguments(bundle);
                    fragmentTransaction.replace(R.id.allFragments, questionsFragment);
                    fragmentTransaction.addToBackStack(null);
                }
                fragmentTransaction.commit();
            }
        });
    }

}
