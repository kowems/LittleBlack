package com.example.android.littleblack;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.app.Fragment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.android.camera2basic.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eric Ju on 2016/9/7.
 */
public class PictureViewFragment extends Fragment {
    private GridView mGridview;
    private List<String> paths;

    public static PictureViewFragment newInstance() {
        return new PictureViewFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        paths = new ArrayList<String>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        return inflater.inflate(R.layout.fragment_pictures_view, container, false);
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);
        mGridview = (GridView) view.findViewById(R.id.pic_view_grid_view);
        Context context = getActivity();
        paths.clear();
        loadPhotoPaths();
        mGridview.setAdapter(new ImageAdapter(paths,context));//调用ImageAdapter.java
        mGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {//监听事件
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(mGridview.this, ""+position,Toast.LENGTH_SHORT).show();//显示信息;
            }
        });
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void loadPhotoPaths() {
        String selection = MediaStore.Images.Media.DATA + " like ?";
        String path = CameraFragment.PATH.replace("file:/","");
        String[] selectionArgs = {path+"%"};
        // 查询数据库
        Cursor cursor = getActivity().getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, selection, selectionArgs, null);
        while (cursor.moveToNext()) {
            String file = cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.DATA));
            paths.add(file);
        }
        cursor.close();
    }
}
