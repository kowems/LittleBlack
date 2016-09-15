package com.example.android.littleblack;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.android.camera2basic.R;
import com.example.android.littleblack.util.DensityUtil;
import com.example.android.littleblack.util.ImageLoader;

import java.util.List;

/**
 * Created by Eric Ju on 2016/9/7.
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private String[] mPics;
    private List<String> mPaths;
    private ImageLoader imageLoader;

    public ImageAdapter(List<String> paths,Context c)
    {
        mContext=c;
        mPaths = paths;

        imageLoader = ImageLoader.getInstance(mContext);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        if( null == mPaths)
            return 0;

        return mPaths.size();
    }
    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
/*
        ImageView imageview;
        if(convertView==null)
        {
            imageview=new ImageView(mContext);
            imageview.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageview.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageview.setPadding(8,8,8,8);
        }
        else
        {
            imageview=(ImageView) convertView;
        }

        imageview.setImageBitmap(loadImageFromUrl( "file://"+CameraFragment.PATH +"/"+mPics[position],103));
        return imageview;
*/


        ViewHolder holder=null;
        if(convertView==null){
            convertView = LayoutInflater.from(this.mContext).inflate(R.layout.grid_item_layout, null);
            holder = new ViewHolder();
            holder.photo=(ImageView)convertView.findViewById(R.id.photo);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();
        }

        final String path = mPaths.get(position);
        //holder.photo.setImageBitmap(imageLoader.getBitmapFromCache(path));
        holder.photo.setImageResource(R.drawable.ic_launcher);
        holder.photo.setTag(path);
        imageLoader.loadImage(holder.photo,
                path,
                DensityUtil.dip2px(80),
                DensityUtil.dip2px(80),
                new ImageLoader.onBitmapLoadedListener() {
                    @Override
                    public void displayImage(ImageView view, Bitmap bitmap) {
                        Log.e("displayImage","aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
                        String imagePath= view.getTag().toString();
                        if(imagePath.equals(path)){
                            view.setImageBitmap(bitmap);
                        }
                    }
                });
        return convertView;
    }
/*
    public static Bitmap loadImageFromUrl(String url, int sc) {
        URL m;
        InputStream i = null;
        BufferedInputStream bis = null;
        ByteArrayOutputStream out = null;
        byte isBuffer[] = new byte[1024];
        if (url == null)
            return null;
        try {
            Log.d("loadImageFromUrl",url);
            m = new URL(url);
            i = (InputStream) m.getContent();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        bis = new BufferedInputStream(i, 1024 * 4);
        out = new ByteArrayOutputStream();
        int len = 0;
        try {
            while ((len = bis.read(isBuffer)) != -1) {
                out.write(isBuffer, 0, len);
            }
            out.close();
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        if (out == null)
            return null;
        byte[] data = out.toByteArray();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(data, 0, data.length, options);
        options.inJustDecodeBounds = false;
        int be = (int) (options.outHeight / (float) sc);
        if (be <= 0) {
            be = 1;
        } else if (be > 3) {
            be = 3;
        }
        options.inSampleSize = be;
        Bitmap bmp = null;
        try {
            bmp = BitmapFactory.decodeByteArray(data, 0, data.length, options); // 返回缩略图
        } catch (OutOfMemoryError e) {
            // TODO: handle exception
            System.gc();
            bmp = null;
        }
        return bmp;
    }*/
    public static class ViewHolder{
        public ImageView photo;
    }
}
