package com.example.android.littleblack;

import android.app.Activity;
import android.os.Bundle;

import com.example.android.camera2basic.R;

/**
 * Created by Eric Ju on 2016/9/7.
 */
public class PictureViewActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pictures_view);
        if (null == savedInstanceState) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.pic_view_container, PictureViewFragment.newInstance())
                    .commit();
        }
    }
}
