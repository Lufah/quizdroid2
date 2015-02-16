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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by JenniferLuu on 2/12/15.
 */
public class QuestionsFragment extends Fragment {

    private String category;
    private HashMap<String, List<String>> questions;
    private String[] allQuestions;
    private int score;
    private int qNumber;
    private int selected;
    private String correctAnswer;
    private View questionsFragmentView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        questionsFragmentView = inflater.inflate(R.layout.questions_fragment, container, false);

        Bundle bundle = this.getArguments();
        category = bundle.getString("category");
        questions = (HashMap<String, List<String>>) bundle.getSerializable("questions");
        allQuestions = bundle.getStringArray("allQuestions");
        score = bundle.getInt("score");
        qNumber = bundle.getInt("qNumber");


        TextView total = (TextView) questionsFragmentView.findViewById(R.id.totalQs);
        total.setText("Question Number " + (qNumber + 1));

        TextView q = (TextView) questionsFragmentView.findViewById(R.id.question);

        String key = allQuestions[qNumber]; // returns question


        List<String> answer = questions.get(key);
        q.setText(key);
        correctAnswer = answer.get(Integer.parseInt(answer.get(0)));

        RadioButton a1 = (RadioButton) questionsFragmentView.findViewById(R.id.a);
        a1.setText(answer.get(1));
        RadioButton a2 = (RadioButton) questionsFragmentView.findViewById(R.id.b);
        a2.setText(answer.get(2));
        RadioButton a3 = (RadioButton) questionsFragmentView.findViewById(R.id.c);
        a3.setText(answer.get(3));
        RadioButton a4 = (RadioButton) questionsFragmentView.findViewById(R.id.d);
        a4.setText(answer.get(4));


        RadioGroup radioGroup = (RadioGroup) questionsFragmentView.findViewById(R.id.radioGroup);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected

                RadioButton rButton = (RadioButton) group.findViewById(checkedId);
                selected = group.indexOfChild(rButton) + 1;

                Button btn = (Button) questionsFragmentView.findViewById(R.id.submit);
                btn.setText("" + selected);
                btn.setVisibility(View.VISIBLE);

            }
        });


        Button btn = (Button) questionsFragmentView.findViewById(R.id.submit);

        btn.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("category", category);
                bundle.putSerializable("questions", questions);
                bundle.putStringArray("allQuestions", allQuestions);
                bundle.putInt("score", score);
                bundle.putInt("qNumber", qNumber);
                bundle.putInt("selected", selected);
                bundle.putString("correctAnswer", correctAnswer);


                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                AnswersFragment answersFragment = new AnswersFragment();
                answersFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.allFragments, answersFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return questionsFragmentView;
    }

}
