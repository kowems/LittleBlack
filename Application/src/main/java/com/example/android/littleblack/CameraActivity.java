/*
 * Copyright 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.littleblack;

import com.example.android.littleblack.BuileGestureExt;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.android.camera2basic.R;

import java.util.ArrayList;

public class CameraActivity extends Activity {

    private ArrayList<OnTouchListener> onTouchListeners = new ArrayList<OnTouchListener>(10);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        if (null == savedInstanceState) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, CameraFragment.newInstance())
                    .commit();
        }
                /**/
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        for (OnTouchListener listener : onTouchListeners) {
            listener.onTouch(ev);
        }
        return super.dispatchTouchEvent(ev);
    }

    public void registerOnTouchListener(OnTouchListener onTouchListener) {
        onTouchListeners.add(onTouchListener);
    }

    public void unregisterOnTouchListener(OnTouchListener onTouchListener) {
        onTouchListeners.remove(onTouchListener);
    }

    public interface OnTouchListener {
        public boolean onTouch(MotionEvent ev);
    }
}
