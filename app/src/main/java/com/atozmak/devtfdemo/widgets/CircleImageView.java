package com.atozmak.devtfdemo.widgets;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import com.atozmak.devtfdemo.ui.ArticlesActivity;
import com.atozmak.devtfdemo.util.LogUtils;

/**
 * ?
 */
public class CircleImageView extends ImageView {

    private static final String STATE_INSTANCE = "state_instance";
    private static final String RADIUS = "radius";
    private static final String TAG = LogUtils.makeLogTag("CircleImageView");

    private Paint mBitmapPaint;
    private int mRadius;
    /**
     * ?
     */
    private Matrix mMatrix;
    /**
     * ?
     */
    private BitmapShader mBitmapShader;
    private int mWidth;

    public CircleImageView(Context context) {
        super(context);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mMatrix = new Matrix();
        mBitmapPaint = new Paint();
        mBitmapPaint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        /**
         * ?
         */
        mWidth = Math.min(getMeasuredWidth(), getMeasuredHeight());
        mRadius = mWidth / 2;
        setMeasuredDimension(mWidth, mWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.e(TAG, "onDraw");
        if (getDrawable() == null) {
            return;
        }
        setUpShader();
        canvas.drawCircle(mRadius, mRadius, mRadius, mBitmapPaint);
    }


    private void setUpShader() {
        /**
         * ?
         */
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }

        Bitmap bmp = drawableToBitmap(drawable);

        mBitmapShader = new BitmapShader(bmp, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        float scale = 1.0f;
        int bSize = Math.min(bmp.getWidth(), bmp.getHeight());
        scale = mWidth * 1.0f / bSize;

        mMatrix.setScale(scale, scale);
        mBitmapShader.setLocalMatrix(mMatrix);
        mBitmapPaint.setShader(mBitmapShader);

    }

    private Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bd = (BitmapDrawable) drawable;
            return bd.getBitmap();
        }
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        drawable.draw(canvas);
        return bitmap;
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(STATE_INSTANCE, super.onSaveInstanceState());
        bundle.putInt(RADIUS, mRadius);
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            super.onRestoreInstanceState(((Bundle) state).getParcelable(STATE_INSTANCE));
            this.mRadius = bundle.getInt(RADIUS);
        } else {
            super.onRestoreInstanceState(state);
        }
    }
}
