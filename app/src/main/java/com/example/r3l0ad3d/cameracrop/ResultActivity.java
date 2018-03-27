package com.example.r3l0ad3d.cameracrop;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.r3l0ad3d.cameracrop.Crop.DrawView;

public class ResultActivity extends AppCompatActivity {

    ImageView imageView;
    Bitmap bitmap;
    Point point1,point2,point3,point4;
    int top,left,right,bottom;

    Rect rect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        bitmap=CameraActivity.bitmap;
        point1= DrawView.point1;
        point2=DrawView.point2;
        point3=DrawView.point3;
        point4=DrawView.point4;

        if(bitmap!=null){
            top=point3.x;
            left=point4.y;
            right=point1.x;
            bottom=point1.y;

            rect = new Rect(left,top,right,bottom);

        }


    }
}
