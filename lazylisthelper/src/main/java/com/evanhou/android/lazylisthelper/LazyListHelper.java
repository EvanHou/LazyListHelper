package com.evanhou.android.lazylisthelper;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by evanhou on 15/11/18.
 *
 */
public class LazyListHelper {

    private ListViewAdapter adapter;
    private Context mContext;
    private Activity mActivity;
    private ListView listView;
    private ArrayList<JSONObject> mData;
    private int cellId;
    private boolean hasTextView = false;
    private boolean hasEditText = false;
    private boolean hasImageView = false;
    private int[] mTextViewId = null;
    private int[] mEditTextId = null;
    private int[] mImageViewId = null;
    private String[] mTextViewJsonKey = null;
    private String[] mEditTextJsonKey = null;
    private String[] mImageViewJsonKey = null;

    public LazyListHelper(Context context, Activity activity) {
        mContext = context;
        mActivity = activity;
    }

    public LazyListHelper onList(int listViewId) {
        listView = (ListView) mActivity.findViewById(listViewId);
        return this;
    }

    public LazyListHelper withCell(int layoutId) {
        cellId = layoutId;
        return this;
    }

    public LazyListHelper withData(ArrayList<JSONObject> data) {
        mData = data;
        return this;
    }

    public void setTextView(int[] viewId, String[] jsonKey) {
        hasTextView = true;
        mTextViewId = viewId;
        mTextViewJsonKey = jsonKey;
    }

    public void setEditText(int[] viewId, String[] jsonKey) {
        hasEditText = true;
        mEditTextId = viewId;
        mEditTextJsonKey = jsonKey;
    }

    public void setImageView(int[] viewId, String[] jsonKey) {
        hasImageView = true;
        mImageViewId = viewId;
        mImageViewJsonKey = jsonKey;
    }

    public void load() {
        adapter = new ListViewAdapter(mContext, mData, cellId,
                mTextViewId, mEditTextId, mImageViewId,
                mTextViewJsonKey, mEditTextJsonKey, mImageViewJsonKey);
        listView.setAdapter(adapter);
    }


    class ListViewAdapter extends BaseAdapter {

        private Context mContext;
        private ArrayList<JSONObject> mData;

        private int cellId;

        private boolean hasTextView = false;
        private boolean hasEditText = false;
        private boolean hasImageView = false;
        private int tvNumber = 0;
        private int etNumber = 0;
        private int ivNumber = 0;
        private int[] mTextViewId;
        private int[] mEditTextId;
        private int[] mImageViewId;
        private String[] mTextViewJsonKey;
        private String[] mEditTextJsonKey;
        private String[] mImageViewJsonKey;

        public ListViewAdapter(
                Context context, ArrayList<JSONObject> data, int cellid,
                int[] tvId, int[] etId, int[] ivId,
                String[] tvJK, String[] etJK, String[] ivJK) {

            mContext = context;
            mData = data;
            cellId = cellid;

            if (tvId != null) {
                hasTextView = true;
                tvNumber = tvId.length;
                mTextViewId = tvId;
                mTextViewJsonKey = tvJK;
            }

            if (etId != null) {
                hasEditText = true;
                etNumber = etId.length;
                mEditTextId = etId;
                mEditTextJsonKey = etJK;
            }

            if (ivId != null) {
                hasImageView = true;
                ivNumber = ivId.length;
                mImageViewId = ivId;
                mImageViewJsonKey = ivJK;
            }
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            ViewHolder holder;

            if (convertView == null) {
                convertView = inflater.inflate(cellId, null);

                holder = new ViewHolder(tvNumber, etNumber, ivNumber);

                if (hasTextView) {
                    for (int i=0; i<tvNumber; i++) {
                        holder.textViews[i] = (TextView) convertView.findViewById(mTextViewId[i]);
                    }
                }

                if (hasEditText) {
                    for (int i=0; i<etNumber; i++) {
                        holder.editTexts[i] = (EditText) convertView.findViewById(mEditTextId[i]);
                    }
                }

                if (hasImageView) {
                    for (int i=0; i<ivNumber; i++) {
                        holder.imageViews[i] = (ImageView) convertView.findViewById(mImageViewId[i]);
                    }
                }

                convertView.setTag(holder);

            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            JSONObject jObj = mData.get(position);
            try {

                if (hasTextView) {
                    for (int i=0; i<tvNumber; i++) {
                        holder.textViews[i].setText(jObj.getString(mTextViewJsonKey[i]));
                    }
                }

                if (hasEditText) {
                    for (int i=0; i<etNumber; i++) {
                        holder.editTexts[i].setText(jObj.getString(mEditTextJsonKey[i]));
                    }
                }

                if (hasImageView) {
                    for (int i=0; i<ivNumber; i++) {
                        Glide.with(mContext).load(jObj.getString(mImageViewJsonKey[i])).into(holder.imageViews[i]);
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return convertView;
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }


    }

    static class ViewHolder {

        TextView[] textViews;
        EditText[] editTexts;
        ImageView[] imageViews;

        public ViewHolder(int tvNum, int etNum, int ivNum) {

            if (tvNum>0) {
                textViews = new TextView[tvNum];
            }

            if (etNum>0) {
                editTexts = new EditText[etNum];
            }

            if (ivNum>0) {
                imageViews = new ImageView[ivNum];
            }
        }
    }
}



