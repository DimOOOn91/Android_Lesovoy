package com.example.dima.android_lesovoy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static Board sBoard;
    private static boolean isCrossesTurn;

    private Button mButtonStart;
    private TextView mTextView;
    private ImageView mImageView;

    private RelativeLayout mBoardLayout;
    private TextView mPlayer;
    private Button mField_0_0;
    private Button mField_0_1;
    private Button mField_0_2;
    private Button mField_1_0;
    private Button mField_1_1;
    private Button mField_1_2;
    private Button mField_2_0;
    private Button mField_2_1;
    private Button mField_2_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mButtonStart = (Button) findViewById(R.id.button_start);
        mButtonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mField_0_0.setBackground(view.getBackground());
                mField_0_1.setBackground(view.getBackground());
                mField_0_2.setBackground(view.getBackground());
                mField_1_0.setBackground(view.getBackground());
                mField_1_1.setBackground(view.getBackground());
                mField_1_2.setBackground(view.getBackground());
                mField_2_0.setBackground(view.getBackground());
                mField_2_1.setBackground(view.getBackground());
                mField_2_2.setBackground(view.getBackground());

                mTextView.setVisibility(View.GONE);
                mImageView.setVisibility(View.GONE);
                mButtonStart.setVisibility(View.GONE);


                mBoardLayout.setVisibility(View.VISIBLE);
                sBoard = new Board();
                isCrossesTurn = true;
            }
        });

        mBoardLayout = (RelativeLayout) findViewById(R.id.board);
        mTextView = (TextView) findViewById(R.id.text_winner);
        mImageView = (ImageView) findViewById(R.id.image_winner);
        mPlayer = (TextView) findViewById(R.id.player);
        mField_0_0 = (Button) findViewById(R.id.field_0_0);
        mField_0_1 = (Button) findViewById(R.id.field_0_1);
        mField_0_2 = (Button) findViewById(R.id.field_0_2);
        mField_1_0 = (Button) findViewById(R.id.field_1_0);
        mField_1_1 = (Button) findViewById(R.id.field_1_1);
        mField_1_2 = (Button) findViewById(R.id.field_1_2);
        mField_2_0 = (Button) findViewById(R.id.field_2_0);
        mField_2_1 = (Button) findViewById(R.id.field_2_1);
        mField_2_2 = (Button) findViewById(R.id.field_2_2);

    }

    public void click(View view) {
        switch (view.getId()) {
            case R.id.field_0_0:
                turn(view, new int[]{0, 0});
                break;

            case R.id.field_0_1:
                turn(view, new int[]{0, 1});
                break;

            case R.id.field_0_2:
                turn(view, new int[]{0, 2});
                break;

            case R.id.field_1_0:
                turn(view, new int[]{1, 0});
                break;

            case R.id.field_1_1:
                turn(view, new int[]{1, 1});
                break;

            case R.id.field_1_2:
                turn(view, new int[]{1, 2});
                break;

            case R.id.field_2_0:
                turn(view, new int[]{2, 0});
                break;

            case R.id.field_2_1:
                turn(view, new int[]{2, 1});
                break;

            case R.id.field_2_2:
                turn(view, new int[]{2, 2});
                break;

            default:
                break;
        }
    }

    private void turn(View view, int[] attempt) {
        try {
            sBoard.setPosition(attempt, isCrossesTurn);
        } catch (Exception e) {
            Log.v(String.valueOf(view.getId()), e.getMessage());
            return;
        }

        int image;
        if (isCrossesTurn) {
            image = R.drawable.ic_clear_black_48dp;
        } else {
            image = R.drawable.ic_panorama_fish_eye_black_48dp;
        }
        view.setBackgroundResource(image);

        if (sBoard.checkColumns() > 0 || sBoard.checkLines() > 0 || sBoard.checkDiagonals() > 0) {
            endTheGame(R.string.player_1_is_winner);
            mImageView.setBackgroundResource(R.drawable.ic_clear_black_48dp);
            mImageView.setVisibility(View.VISIBLE);
            return;
        }
        if (sBoard.checkColumns() < 0 || sBoard.checkLines() < 0 || sBoard.checkDiagonals() < 0) {
            endTheGame(R.string.player_2_is_winner);
            mImageView.setBackgroundResource(R.drawable.ic_panorama_fish_eye_black_48dp);
            mImageView.setVisibility(View.VISIBLE);
            return;
        }
        if (sBoard.fullBoard()) {
            endTheGame(R.string.game_in_a_draw);
            return;
        }
        isCrossesTurn = !isCrossesTurn;
        if (isCrossesTurn) {
            mPlayer.setText(R.string.player_1);
        } else {
            mPlayer.setText(R.string.player_2);
        }
    }

    private void endTheGame(int message) {
        mBoardLayout.setVisibility(View.GONE);

        mTextView.setText(message);
        mTextView.setVisibility(View.VISIBLE);

        mButtonStart.setText(R.string.new_game);
        mButtonStart.setTextSize(TypedValue.COMPLEX_UNIT_SP, 50);
        mButtonStart.setVisibility(View.VISIBLE);
    }


}
