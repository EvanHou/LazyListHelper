package com.evanhou.android.lazylistsample;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.evanhou.android.lazylisthelper.LazyListHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    LazyListHelper helper;
    ArrayList<JSONObject> data = new ArrayList<>();
    String[] textKey = new String[]{"title", "content", "tag"};
    String[] imgKey = new String[]{"img"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        int i = 0;
        while (i<2) {
            data.add(makeJson());
            i++;
        }


        helper = new LazyListHelper(getBaseContext(), this).onList(R.id.listView).withCell(R.layout.cell_listview).withData(data);
        helper.setTextView(new int[]{R.id.textViewTitle, R.id.textViewDescription, R.id.textViewTag}, textKey);
        helper.setImageView(new int[]{R.id.imageViewPic}, imgKey);
        helper.load();

    }

    private JSONObject makeJson() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(textKey[0], "這是一則標題");
            jsonObject.put(textKey[1], "內文內文內文內文內文內文內文內文內文內文內文內文內文內文內文");
            jsonObject.put(textKey[2], "2015年11月20日");
            jsonObject.put(imgKey[0], "http://www.cinemablend.com/images/news_img/88987/star_wars_the_force_awakens_88987.jpg");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
