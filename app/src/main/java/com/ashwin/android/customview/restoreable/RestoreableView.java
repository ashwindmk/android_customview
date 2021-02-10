package com.ashwin.android.customview.restoreable;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class RestoreableView extends View {
    private static class SavedState extends BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };

        String name;
        int index;

        public SavedState(Parcelable superState) {
            super(superState);
        }

        public SavedState(Parcel source) {
            super(source);
            name = source.readString();
            index = source.readInt();
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        public SavedState(Parcel source, ClassLoader loader) {
            super(source, loader);
            name = source.readString();
            index = source.readInt();
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeString(name);
            out.writeInt(index);
        }
    }

    private String name;
    private int index;

    TextPaint mTextPaint;
    StaticLayout mStaticLayout;

    public RestoreableView(Context context) {
        super(context);
        init();
    }

    public RestoreableView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RestoreableView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

//    public RestoreableView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            super(context, attrs, defStyleAttr, defStyleRes);
//        }
//        init();
//    }

    private void init() {
        setSaveEnabled(true);
        this.name = "Hello world";
        this.index = 0;

        mTextPaint = new TextPaint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(16 * getResources().getDisplayMetrics().density);
        mTextPaint.setColor(0xFF000000);

        Log.d("custom-view", "RestoreableView: init");
    }

    public void setName(String name) {
        Log.d("custom-view", "RestoreableView: setName");
        if (name == null) {
            this.name = "";
        }

        this.name = name;
        //invalidate();
        requestLayout();
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.d("custom-view", "RestoreableView: onMeasure");
        // Tell the parent layout how big this view would like to be
        // but still respect any requirements (measure specs) that are passed down.

        write();

        // determine the width
        int width;
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthRequirement = MeasureSpec.getSize(widthMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthRequirement;
        } else {
            width = mStaticLayout.getWidth() + getPaddingLeft() + getPaddingRight();
            if (widthMode == MeasureSpec.AT_MOST) {
                if (width > widthRequirement) {
                    width = widthRequirement;
                    // too long for a single line so relayout as multiline
                    mStaticLayout = new StaticLayout(name, mTextPaint, width, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0, false);
                }
            }
        }

        // determine the height
        int height;
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightRequirement = MeasureSpec.getSize(heightMeasureSpec);
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightRequirement;
        } else {
            height = mStaticLayout.getHeight() + getPaddingTop() + getPaddingBottom();
            if (heightMode == MeasureSpec.AT_MOST) {
                height = Math.min(height, heightRequirement);
            }
        }

        // Required call: set width and height
        setMeasuredDimension(width, height);
    }

    private void write() {
        Log.d("custom-view", "RestoreableView: write: " + this.name);

        // default to a single line of text
        int width = (int) mTextPaint.measureText(name);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            StaticLayout.Builder builder = StaticLayout.Builder.obtain(name, 0, name.length(), mTextPaint, width)
                    .setAlignment(Layout.Alignment.ALIGN_NORMAL)
                    .setLineSpacing(1, 0) // multiplier, add
                    .setIncludePad(false);
            mStaticLayout = builder.build();
        } else {
            mStaticLayout = new StaticLayout(name, mTextPaint, (int) width, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0, false);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("custom-view", "RestoreableView: onDraw");
        // do as little as possible inside onDraw to improve performance

        // draw the text on the canvas after adjusting for padding
        canvas.save();
        canvas.translate(getPaddingLeft(), getPaddingTop());
        mStaticLayout.draw(canvas);
        canvas.restore();
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Log.d("custom-view", "RestoreableView: onSaveInstanceState");

        // Obtain any state that our super class wants to save.
        Parcelable superState = super.onSaveInstanceState();

        // Wrap our super class's state with our own.
        SavedState myState = new SavedState(superState);
        myState.name = this.name;
        myState.index = this.index;

        // Return our state along with our super class's state.
        return myState;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        Log.d("custom-view", "RestoreableView: onRestoreInstanceState");

        // Cast the incoming Parcelable to our custom SavedState. We produced
        // this Parcelable before, so we know what type it is.
        SavedState savedState = (SavedState) state;

        // Let our super class process state before we do because we should
        // depend on our super class, we shouldn't imply that our super class
        // might need to depend on us.
        super.onRestoreInstanceState(savedState.getSuperState());

        // Grab our properties out of our SavedState.
        this.name = savedState.name;
        this.index = savedState.index;

        // Update our visuals in whatever way we want, like...
        requestLayout(); //...or...
        //invalidate(); //...or...
    }
}
