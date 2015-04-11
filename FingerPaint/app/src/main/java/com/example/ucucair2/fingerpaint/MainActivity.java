package com.example.ucucair2.fingerpaint;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;


public class MainActivity extends ActionBarActivity implements View.OnTouchListener {
    Canvas mCanvas;
    Paint mPaint;
    Path mPath;
    Bitmap mBitmap;
    ImageView mImageView;
    float x1, y1;
    int width, height;
    AlertDialog.Builder mAlertBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView = (ImageView) findViewById(R.id.imageView);
        mImageView.setOnTouchListener(this);
        mPaint = new Paint();
        mPaint.setStrokeWidth(5.0f);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPath = new Path();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (mBitmap == null) {
            width = mImageView.getWidth();
            height = mImageView.getHeight();
            mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            mCanvas = new Canvas(mBitmap);
            mCanvas.drawColor(Color.WHITE);
            mImageView.setImageBitmap(mBitmap);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.reset();
                mPath.moveTo(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
                mPath.quadTo(x1, y1, x, y);
                mCanvas.drawPath(mPath, mPaint);
                break;
        }
        x1 = x;
        y1 = y;
        mImageView.setImageBitmap(mBitmap);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.menu_main, menu);
        // return true;
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.menu_save:
                save();
                break;
            case R.id.menu_open:
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(intent, 0);
                break;
            case R.id.menu_color_change:
                break;
            case R.id.menu_new:
                mAlertBuilder = new AlertDialog.Builder(this);
                mAlertBuilder.setTitle(R.string.menu_new);
                mAlertBuilder.setMessage("作業内容が破棄されます。よろしいですか？");
                mAlertBuilder.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                mCanvas.drawColor(Color.WHITE);
                                mImageView.setImageBitmap(mBitmap);
                            }
                        });
                mAlertBuilder.setNegativeButton("キャンセル",
                        new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                mAlertBuilder.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onAcivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri = data.getData();
        try{
            mBitmap = loadImage(uri);
        }catch (IOException e){
            e.printStackTrace();
        }
        mCanvas = new Canvas(mBitmap);
        mImageView.setImageBitmap(mBitmap);
    }

    void save() {
        SharedPreferences prefs = getSharedPreferences("FingarPaintPreferences", MODE_PRIVATE);
        int imageNumber = prefs.getInt("imageNumber", 1);
        File file = null;
        DecimalFormat form = new DecimalFormat("0000");
        String dirPath = Environment.getExternalStorageDirectory() + "/FingerPaint/";
        File outDir = new File(dirPath);

        if (!outDir.exists()) outDir.mkdir();
        do {
            file = new File(dirPath + "img" + form.format(imageNumber) + ".png");
            imageNumber++;
        } while (file.exists());
        if (writeImage(file)) {
            scanMedia(file.getPath());
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("ImageNumber", imageNumber + 1);
            editor.commit();
        }
    }
    boolean writeImage(File file) {
        try{
            FileOutputStream fo = new FileOutputStream(file);
            mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fo);
            fo.flush();
            fo.close();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return false;
        }
        return true;
    }
    MediaScannerConnection mc;
    void scanMedia(final String fp) {
        mc = new MediaScannerConnection(this, new MediaScannerConnection.MediaScannerConnectionClient() {
            @Override
            public void onMediaScannerConnected() {
                mc.scanFile(fp, "image/*");
            }

            @Override
            public void onScanCompleted(String path, Uri uri) {
                mc.disconnect();
            }
        });
        mc.connect();
    }
}
