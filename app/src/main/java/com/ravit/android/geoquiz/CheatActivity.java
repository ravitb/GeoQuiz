package com.ravit.android.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by ravit on 05/09/15.
 */
public class CheatActivity extends Activity {

    public static final String EXTRA_ANSWER_IS_TRUE = "com.ravit.android.geoquiz.answer_is_true";
    public static final String EXTRA_ANSWER_SHOWN = "com.ravit.android.geoquiz.answer_shown";
    private static final String KEY_CHEATER = "cheater";

    private boolean mAnswerIsTrue;
    private TextView mAnswerTextView;
    private Button mShowAnswer;

    private boolean mCurrentCheater = false;

    private void setAnswerShownResult(boolean isAnswerShown) {
        Intent data = new Intent();
        mCurrentCheater = isAnswerShown;
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }

    private void showAnswer() {
        if (mAnswerIsTrue) {
            mAnswerTextView.setText(R.string.true_button);
        } else {
            mAnswerTextView.setText(R.string.false_button);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        // Answer will not be shown until the user presses the button
        setAnswerShownResult(false);

        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        mAnswerTextView = (TextView)findViewById(R.id.answerTextView);

        mShowAnswer = (Button)findViewById(R.id.showAnswerButton);
        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                showAnswer();
                setAnswerShownResult(true);
            }
        });

        if (savedInstanceState != null) {
            mCurrentCheater = savedInstanceState.getBoolean(KEY_CHEATER, false);
            showAnswer();
            setAnswerShownResult(mCurrentCheater);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean(KEY_CHEATER, mCurrentCheater);
    }
}
