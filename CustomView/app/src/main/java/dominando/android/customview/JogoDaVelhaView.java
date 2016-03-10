package dominando.android.customview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
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
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);

        mImageX = BitmapFactory.decodeResource(getResources(), R.drawable.x_mark);
        mImageO = BitmapFactory.decodeResource(getResources(), R.drawable.o_mark);

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

        if (getLayoutParams().width == ViewGroup.LayoutParams.WRAP_CONTENT) {
            Resources r = getResources();
            float quadrante = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 48, r.getDisplayMetrics());
            mTamanho = (int) quadrante * 3;
        } else if (getLayoutParams().width == ViewGroup.LayoutParams.MATCH_PARENT) {
            mTamanho = Math.min(MeasureSpec.getSize(widthMeasureSpec),
                    MeasureSpec.getSize(heightMeasureSpec));
        } else {
            mTamanho = getLayoutParams().width;
        }
        setMeasuredDimension(mTamanho, mTamanho);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int quadrante = mTamanho / 3;

        //Desenhando as linhas
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(3);

        //Verticais
        canvas.drawLine(quadrante, 0, quadrante, mTamanho, mPaint);
        canvas.drawLine(quadrante * 2, 0, quadrante * 2, mTamanho, mPaint);

        //Horizontais
        canvas.drawLine(0, quadrante, mTamanho, quadrante, mPaint);
        canvas.drawLine(0, quadrante * 2, mTamanho, quadrante * 2, mPaint);

        for (int linha = 0; linha < 3; linha++) {
            for (int coluna = 0; coluna < 3; coluna++) {

                int x = coluna * quadrante;
                int y = linha * quadrante;

                Rect rect =  new Rect(x,y,x + quadrante, y + quadrante);

                if(mTabuleiro[linha][coluna] == XIS){
                    canvas.drawBitmap(mImageX, null , rect , null);
                }else if (mTabuleiro[linha][coluna] == BOLA){
                    canvas.drawBitmap(mImageO, null, rect, null);
                }
            }
        }

    }
}
