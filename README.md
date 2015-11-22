# LazyListHelper

Download
--------------
Use Gradle:
```gradle
dependencies {
    compile 'com.evanhou.android:lazylisthelper:1.0.0'
}
```


How to use:
--------------
In your activity, save data into ArratList with JSONObject.
```java
private ArrayList<JSONObject> data;
```

Then save your view component id and key used in JSONObject into int array & String array.
```java
int[] textViewResourceId = new int[]{R.id.textViewTitle, R.id.textViewContent,  R.id.textViewDate};
int[] imageViewResourceId = new int[]{R.id.imageViewPic};
private String[] textKey = new String[]{"title", "content", "date"};
private String[] imgKey = new String[]{"img"};
```

Then initiate the LazyListHelper and set "Resource Id" and "Key" used in JSONObject.
```java
LazyListHelper helper;
helper = new LazyListHelper(context, activity).onList(R.id.listView).withCell(R.layout.cell_listview).withData(data);
helper.setTextView(textViewResourceId, textKey);
helper.setImageView(imageViewResourceId, imgKey);
helper.load();
```
Compile and run it!
