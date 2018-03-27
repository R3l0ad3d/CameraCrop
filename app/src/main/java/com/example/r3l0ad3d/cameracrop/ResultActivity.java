package com.example.r3l0ad3d.cameracrop;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.r3l0ad3d.cameracrop.Crop.DrawView;

public class ResultActivity extends AppCompatActivity {

    ImageView imageView,image;
    Bitmap bitmap;
    Point point1,point2,point3,point4;
    int top,left,right,bottom;

    Rect rect;

    public static int heightD;
    public static int widthD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_result);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        heightD = displayMetrics.heightPixels;
        widthD = displayMetrics.widthPixels;

        imageView=findViewById(R.id.ivResult);
        image=findViewById(R.id.ivImage);

        bitmap=CameraActivity.bitmap;
        point1= DrawView.point1;
        point2=DrawView.point2;
        point3=DrawView.point3;
        point4=DrawView.point4;

        float scaleX=bitmap.getWidth()/widthD;
        float scaleY=bitmap.getHeight()/heightD;

        if(bitmap!=null){
            top=point1.y;
            left=point1.x;
            right=point3.x;
            bottom=point3.y;

            Log.d("Point 1 : ","("+point1.x+","+point1.y+")");
            Log.d("Point 2 : ","("+point2.x+","+point2.y+")");
            Log.d("Point 3 : ","("+point3.x+","+point3.y+")");
            Log.d("Point 4 : ","("+point4.x+","+point4.y+")");
            Log.d("ScaleX : ",""+scaleY);
            Log.d("ScaleY : ",""+scaleX);

            /*if(scaleX==scaleY){
                top*=scaleX;
                left*=scaleX;
                right*=scaleX;
                bottom*=scaleX;
            }else {
                top/=scaleX;
                left/=scaleX;
                right/=scaleX;
                bottom/=scaleX;
            }*/

            image.setImageBitmap(bitmap);
            float [] m=new float[9];
            image.getImageMatrix().getValues(m);
            //image.setVisibility(View.GONE);

            rect = new Rect(left,top,right,bottom);

            left= (int) (left/m[Matrix.MSCALE_X]-m[Matrix.MTRANS_X]);
            top= (int) (top/m[Matrix.MSCALE_Y]-m[Matrix.MTRANS_Y]);
            right= (int) (right/m[Matrix.MSCALE_X]-m[Matrix.MTRANS_X]);
            bottom= (int) (bottom/m[Matrix.MSCALE_Y]-m[Matrix.MTRANS_Y]);

            Bitmap bitmap1=Bitmap.createBitmap(bitmap,left,top,right-left,bottom-top);

            imageView.setImageBitmap(bitmap1);

        }


    }
}
