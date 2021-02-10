package com.ashwin.android.customview.restoreable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.ashwin.android.customview.R;

public class RestoreableViewActivity extends AppCompatActivity {
    private RestoreableView restoreableView;
    private EditText nameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("custom-view", "RestoreableViewActivity: onCreate: " + savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restoreableview);

        restoreableView = findViewById(R.id.one_restoreableview);
        nameEditText = findViewById(R.id.name_edittext);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        Log.d("custom-view", "RestoreableViewActivity: onRestoreInstanceState: " + savedInstanceState);
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onAttachedToWindow() {
        Log.d("custom-view", "RestoreableViewActivity: onAttachedToWindow");
        super.onAttachedToWindow();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        Log.d("custom-view", "RestoreableViewActivity: onSaveInstanceState");
        super.onSaveInstanceState(outState);
    }

    public void update(View view) {
        String name = nameEditText.getText().toString();
        restoreableView.setName(name);
    }

    @Override
    protected void onStop() {
        Log.d("custom-view", "RestoreableViewActivity: onStop");
        super.onStop();
    }

    @Override
    public void onDetachedFromWindow() {
        Log.d("custom-view", "RestoreableViewActivity: onDetachedFromWindow");
        super.onDetachedFromWindow();
    }
}