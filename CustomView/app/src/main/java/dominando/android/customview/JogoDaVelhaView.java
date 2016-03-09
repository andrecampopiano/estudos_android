package dominando.android.customview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by andre.campopiano on 09/03/2016.
 */
public class JogoDaVelhaView extends View {

    public static final int XIS = 1;
    public static final int BOLA = 2;

    int mTamanho;
    int mVez;
    int[][] mTabuleiro;

    Paint mPaint;
    Bitmap mImageX;
    Bitmap mImageO;

    public JogoDaVelhaView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mVez = XIS;
        mTabuleiro = new int[3][3];
    }

    @Override
    public void onAttachedToWindow(){
        super.onAttachedToWindow();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);

      /*  mImageX = BitmapFactory.decodeResource(getResources(), R.drawable.x_mark);
        mImageO = BitmapFactory.decodeResource(getResources(), R.drawable.o_mark);*/

    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mTabuleiro = null;
        mPaint = null;
        mImageX = null;
        mImageO = null;

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if(getLayoutParams().width == ViewGroup.LayoutParams.WRAP_CONTENT){
            Resources r = getResources();
            float quadrante = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 48 ,r.getDisplayMetrics());
            mTamanho = (int) quadrante * 3;
        }else if (getLayoutParams().width == ViewGroup.LayoutParams.MATCH_PARENT){
            mTamanho = Math.min(MeasureSpec.getSize(widthMeasureSpec),
                    MeasureSpec.getSize(heightMeasureSpec));
        }else{
            mTamanho = getLayoutParams().width;
        }
        setMeasuredDimension(mTamanho, mTamanho);
    }
}
