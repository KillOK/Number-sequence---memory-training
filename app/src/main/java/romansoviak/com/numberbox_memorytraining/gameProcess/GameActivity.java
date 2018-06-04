package romansoviak.com.numberbox_memorytraining.gameProcess;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import romansoviak.com.numberbox_memorytraining.R;
import romansoviak.com.numberbox_memorytraining.Utils.Constants;

/**
 * Created by Soviak Roman on 30.05.2018.
 */
// at the start get number from text view
public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    SparseArray<String> keyValues = new SparseArray<>();

    InputConnection inputConnection;
    private ImageButton mNumberOneButton;
    private ImageButton mNumberTwoButton;
    private ImageButton mNumberThreeButton;
    private ImageButton mNumberFourButton;
    private ImageButton mNumberFiveButton;
    private ImageButton mNumberSixButton;
    private ImageButton mNumberSevenButton;
    private ImageButton mNumberEightButton;
    private ImageButton mNumberNineButton;
    private ImageButton mNumberZeroButton;
    private ImageButton mDeleteButton;
    private ImageButton mConfirmButton;
    private TextView mPointsCountView;
    private EditText mAnswerEditText;
    private TextView mSampleNumbersView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);
        initKeyBoard();
        mPointsCountView = findViewById(R.id.points_count_txt_view);
        mAnswerEditText = findViewById(R.id.answer_edit_text);
        mSampleNumbersView = findViewById(R.id.sample_numbers_txt_view);
        mSampleNumbersView.setVisibility(View.INVISIBLE);

        mAnswerEditText.setRawInputType(InputType.TYPE_CLASS_TEXT);
        mAnswerEditText.setTextIsSelectable(true);

        InputConnection inputConnection = mAnswerEditText.onCreateInputConnection(new EditorInfo());
        setInputConnection(inputConnection);
    }

    private void initKeyBoard() {
        mNumberOneButton = findViewById(R.id.number_one_btn);
        mNumberTwoButton = findViewById(R.id.number_two_btn);
        mNumberThreeButton = findViewById(R.id.number_three_btn);
        mNumberFourButton = findViewById(R.id.number_four_btn);
        mNumberFiveButton = findViewById(R.id.number_five_btn);
        mNumberSixButton = findViewById(R.id.number_six_btn);
        mNumberSevenButton = findViewById(R.id.number_seven_btn);
        mNumberEightButton = findViewById(R.id.number_eight_btn);
        mNumberNineButton = findViewById(R.id.number_nine_btn);
        mNumberZeroButton = findViewById(R.id.number_zero_btn);
        mDeleteButton = findViewById(R.id.delete_btn);
        mConfirmButton = findViewById(R.id.confirm_btn);

        mNumberOneButton.setOnClickListener(this);
        mNumberTwoButton.setOnClickListener(this);
        mNumberThreeButton.setOnClickListener(this);
        mNumberFourButton.setOnClickListener(this);
        mNumberFiveButton.setOnClickListener(this);
        mNumberSixButton.setOnClickListener(this);
        mNumberSevenButton.setOnClickListener(this);
        mNumberEightButton.setOnClickListener(this);
        mNumberNineButton.setOnClickListener(this);
        mNumberZeroButton.setOnClickListener(this);
        mDeleteButton.setOnClickListener(this);
        mConfirmButton.setOnClickListener(this);

        keyValues.put(R.id.number_one_btn, Constants.Numbers.ONE);
        keyValues.put(R.id.number_two_btn, Constants.Numbers.TWO);
        keyValues.put(R.id.number_three_btn, Constants.Numbers.THREE);
        keyValues.put(R.id.number_four_btn, Constants.Numbers.FOUR);
        keyValues.put(R.id.number_five_btn, Constants.Numbers.FIVE);
        keyValues.put(R.id.number_six_btn, Constants.Numbers.SIX);
        keyValues.put(R.id.number_seven_btn, Constants.Numbers.SEVEN);
        keyValues.put(R.id.number_eight_btn, Constants.Numbers.EIGHT);
        keyValues.put(R.id.number_nine_btn, Constants.Numbers.NINE);
        keyValues.put(R.id.number_zero_btn, Constants.Numbers.ZERO);
    }

    @Override
    public void onClick(View v) {

        if (inputConnection == null) return;

        if (v.getId() == R.id.delete_btn) {
            CharSequence selectedText = inputConnection.getSelectedText(0);
            if (TextUtils.isEmpty(selectedText)) {
                // no selection, so delete previous character
                inputConnection.deleteSurroundingText(1, 0);
            } else {
                // delete the selection
                inputConnection.commitText("", 1);
            }
        } else if (v.getId() == R.id.confirm_btn) {
            if (mAnswerEditText.getText().toString().equals("12345")) {
                countPoints();
                mSampleNumbersView.setVisibility(View.VISIBLE);
                startBottomToTopAnimation(mSampleNumbersView);
                mSampleNumbersView.setVisibility(View.GONE);

            } else {
                Toast.makeText(this, "You are not fucking right", Toast.LENGTH_SHORT).show();
            }

        } else {
            String value = keyValues.get(v.getId());
            inputConnection.commitText(value, 1);
        }
    }

    private void countPoints() {
        int totalPoints;
        String currentPoints = mPointsCountView.getText().toString();
        if (currentPoints.isEmpty()) {
            totalPoints = 0;
            mPointsCountView.setText((Integer.toString(totalPoints)));
        } else {
            totalPoints = Integer.parseInt(currentPoints);
            totalPoints = totalPoints + 10;
            mPointsCountView.setText((Integer.toString(totalPoints)));
        }
    }

    public void setInputConnection(InputConnection ic) {
        this.inputConnection = ic;
    }

    private void startBottomToTopAnimation(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.table_up));
    }

    private void startTopToBottomAnimation(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.table_down));
    }

}