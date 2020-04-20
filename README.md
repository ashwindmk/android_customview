# Custom View

This project shows to set custom view instead of XML in an Activity.

### Calculating status bar height
```java
View decorView = getWindow().getDecorView();
Rect rect = new Rect();
decorView.getWindowVisibleDisplayFrame(rect);
int statusBarHeight = rect.top;
```

### Calculating title bar height
```java
View contentView = getWindow().findViewById(Window.ID_ANDROID_CONTENT);
int[] location = new int[2];
contentView.getLocationInWindow(location);
int titleBarHeight = location[1] - statusBarHeight;
```
