package com.ashwin.android.customview.text;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.ashwin.android.customview.R;

public class CustomTextViewActivity extends AppCompatActivity {
    private CustomTextView customTextView;
    private EditText nameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customtextview);

        customTextView = findViewById(R.id.one_customtextview);
        nameEditText = findViewById(R.id.name_edittext);
    }

    public void update(View view) {
        String name = nameEditText.getText().toString();
        customTextView.setText(name);
    }
}