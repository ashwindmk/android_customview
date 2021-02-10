package com.ashwin.android.customview.text;

import android.content.Context;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.RequiresApi;

public class CustomTextView extends androidx.appcompat.widget.AppCompatTextView {
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

    String name;
    int index;

    public CustomTextView(Context context) {
        super(context);
        init();
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        Log.d("custom-view", "CustomTextView: init");
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        SavedState savedState = (SavedState) state;
        super.onRestoreInstanceState(savedState.getSuperState());

        this.name = savedState.name;
        Log.d("custom-view", "CustomTextView: onRestoreInstanceState: " + this.name);
        setText(this.name);
        this.index = savedState.index;

        requestLayout();
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Log.d("custom-view", "CustomTextView: onSaveInstanceState");

        // Obtain any state that our super class wants to save.
        Parcelable superState = super.onSaveInstanceState();

        // Wrap our super class's state with our own.
        SavedState myState = new SavedState(superState);
        myState.name = getText().toString();
        myState.index = 1;

        // Return our state along with our super class's state.
        return myState;
    }
}
