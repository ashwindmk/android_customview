package com.ashwin.android.customview;

import android.app.Activity;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;

public class MainActivity extends Activity /*AppCompatActivity*/ {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        final CustomView customView = new CustomView(this);
        setContentView(customView);

        //getWindow().addFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ViewTreeObserver viewTreeObserver = customView.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Log.d("custom-view", "On-global-layout");
                int viewWidth = customView.getMeasuredWidth();
                int viewHeight = customView.getMeasuredHeight();
                Log.d("custom-view", "View: measured width: " + viewWidth + ", measured height: " + viewHeight);

                customView.setTop(50);
                customView.setLeft(50);

                int[] viewLocation = new int[2];
                customView.getLocationInWindow(viewLocation);
                Log.d("custom-view", "View: location-in-window: top: " + viewLocation[1] + ", left: " + viewLocation[0]);

                Rect viewRect = new Rect();
                customView.getWindowVisibleDisplayFrame(viewRect);
                Log.d("custom-view", "View: visible-display: top: " + viewRect.top + ", left: " + viewRect.left + ", height: " + viewRect.height());

                int top = customView.getTop();
                int left = customView.getLeft();
                Log.d("custom-view", "View: top: " + top + ", left: " + left + ", height: " + customView.getHeight());

                Window window = getWindow();

                // Display metrics
                DisplayMetrics displayMetrics = new DisplayMetrics();
                window.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                //int displayMetricsWidth = displayMetrics.widthPixels;
                int displayMetricsHeight = displayMetrics.heightPixels;
                Log.w("custom-view", "Display-metrics: height: " + displayMetricsHeight);

                // Android content view
                View androidContentView = window.findViewById(Window.ID_ANDROID_CONTENT);
                int[] locationAndroidContentView = new int[2];
                androidContentView.getLocationInWindow(locationAndroidContentView);
                Log.w("custom-view", "Android-content-view: location-in-window: top: " + locationAndroidContentView[1] + ", left: " + locationAndroidContentView[0]);

                Rect androidContentViewRect = new Rect();
                androidContentView.getWindowVisibleDisplayFrame(androidContentViewRect);
                Log.d("custom-view", "Android-content-view: visible-display: top: " + androidContentViewRect.top + ", left: " + androidContentViewRect.left + ", height: " + androidContentViewRect.height());

                int contentViewTop = androidContentView.getTop();
                int contentViewLeft = androidContentView.getLeft();
                Log.d("custom-view", "Android-content-view top: " + contentViewTop + ", left: " + contentViewLeft + ", height: " + androidContentView.getHeight());

                // Decor view
                View decorView = window.getDecorView();
                int[] locationDecorView = new int[2];
                decorView.getLocationInWindow(locationDecorView);
                Log.d("custom-view", "Decor-view: location-in-window: top: " + locationDecorView[1] + ", left: " + locationDecorView[0]);

                Rect decorViewRect = new Rect();
                decorView.getWindowVisibleDisplayFrame(decorViewRect);
                Log.d("custom-view", "Decor-view: visible-display: top: " + decorViewRect.top + ", left: " + decorViewRect.left + ", height: " + decorViewRect.height());

                Log.d("custom-view", "Decor-view: top: " + decorView.getTop() + ", left: " + decorView.getLeft() + ", height: " + decorView.getHeight());

                // Calculating status bar height
                int statusBarHeight = decorViewRect.top;
                Log.d("custom-view", "Status-bar: height: " + statusBarHeight);

                // Calculating title bar height
                int titleBarHeight = locationAndroidContentView[1] - statusBarHeight;
                Log.d("custom-view", "Title-bar: height: " + titleBarHeight);
            }
        });
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.d("custom-view", "Activity: on-attached-to-window");
    }
}
