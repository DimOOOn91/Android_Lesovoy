package com.example.module02task01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editAuthor;
    private EditText editTheme;
    private EditText editContent;
    private Button buttonSubmit;
    private TextView author;
    private TextView date;
    private TextView theme;
    private TextView content;
    private Button buttonShowText;
    private Button buttonEditText;
    private Button buttonEdit;
    private Toast toast;

    private static final List<DataSave> DATA = new ArrayList<>();
    private static int currentPositionInList = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editAuthor = (EditText) findViewById(R.id.edit_author);
        editTheme = (EditText) findViewById(R.id.edit_theme);
        editContent = (EditText) findViewById(R.id.edit_content);

        buttonSubmit = (Button) findViewById(R.id.button_submit);
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click(view);
            }
        });

        author = (TextView) findViewById(R.id.text_author);
        date = (TextView) findViewById(R.id.text_date);
        theme = (TextView) findViewById(R.id.text_theme);
        content = (TextView) findViewById(R.id.text_content);

        buttonShowText = (Button) findViewById(R.id.button_show_text);
        buttonShowText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click(view);
            }
        });
        buttonEditText = (Button) findViewById(R.id.button_edit_text);
        buttonEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click(view);
            }
        });
        buttonEdit = (Button) findViewById(R.id.button_edit);
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click(view);
            }
        });

    }

    private void click(View view) {
        String author = editAuthor.getText().toString();
        String theme = editTheme.getText().toString();
        String content = editContent.getText().toString();

        switch (view.getId()) {
            case R.id.button_submit:

                if (author.equals("") || theme.equals("") || content.equals("")) {
                    showToast("Please fill all fields");
                    break;
                }

                DATA.add(new DataSave(author, theme, content));
                showToast("Submitted!");

                cleanEditText();
                break;

            case R.id.button_show_text:

                if (currentPositionInList == DATA.size() - 1) {
                    currentPositionInList = 0;
                } else {
                    currentPositionInList++;
                }

                DataSave dataToShow = DATA.get(currentPositionInList);

                this.author.setText(dataToShow.getAuthor());
                this.theme.setText(dataToShow.getTheme());
                this.content.setText(dataToShow.getContext());
                Date dateOfCreating = dataToShow.getDateOfCreating();
                Date dateOfEditing = dataToShow.getDateOfEditing();
                date.setText(dateOfCreating != dateOfEditing ? "EDITED: " + dateOfEditing.toString() : dateOfCreating.toString());

                if (allTextFieldsAreEmpty()) {
                    break;
                }
                buttonShowText.setText(R.string.next);
                buttonEditText.setVisibility(View.VISIBLE);
                break;

            case R.id.button_edit_text:

                editAuthor.setText(this.author.getText());
                editTheme.setText(this.theme.getText());
                editContent.setText(this.content.getText());

                buttonSubmit.setVisibility(View.GONE);
                buttonEdit.setVisibility(View.VISIBLE);
                break;

            case R.id.button_edit:

                if (author.equals("") || theme.equals("") || content.equals("")) {
                    showToast("Please fill all fields");
                    break;
                }

                DataSave editedData = DATA.get(currentPositionInList);
                editedData.setDateOfEditing(new Date());
                editedData.setAuthor(author);
                editedData.setTheme(theme);
                editedData.setContext(content);

                DATA.set(currentPositionInList, editedData);
                showToast("Edited Successfully!");

                cleanEditText();
                cleanTextView();
                buttonEditText.setVisibility(View.GONE);
                buttonEdit.setVisibility(View.GONE);
                buttonSubmit.setVisibility(View.VISIBLE);
                buttonShowText.setText(R.string.show_text);
                break;

            default:
                break;

        }
    }

    private void cleanEditText() {
        editAuthor.setText("");
        editTheme.setText("");
        editContent.setText("");
    }

    private void cleanTextView() {
        this.author.setText("");
        this.theme.setText("");
        this.content.setText("");
        this.date.setText("");
    }

    private boolean allTextFieldsAreEmpty() {
        return this.author.getText().toString().equals("") &&
                this.date.getText().toString().equals("") &&
                this.theme.getText().toString().equals("") &&
                this.content.getText().toString().equals("");
    }

    private void showToast(String s) {
        toast = Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
