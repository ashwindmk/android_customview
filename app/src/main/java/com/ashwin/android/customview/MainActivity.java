package com.ashwin.android.customview;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.ashwin.android.customview.dynamic.DynamicViewActivity;
import com.ashwin.android.customview.restoreable.RestoreableViewActivity;
import com.ashwin.android.customview.text.CustomTextViewActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.d("custom-view", "Activity: on-attached-to-window");
    }

    public void openDynamicView(View view) {
        Intent intent = new Intent(this, DynamicViewActivity.class);
        startActivity(intent);
    }

    public void openRestoreableView(View view) {
        Intent intent = new Intent(this, RestoreableViewActivity.class);
        startActivity(intent);
    }

    public void openTextView(View view) {
        Intent intent = new Intent(this, CustomTextViewActivity.class);
        startActivity(intent);
    }
}
