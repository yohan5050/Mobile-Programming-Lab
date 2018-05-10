package yohan.org.lab4_1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by 이상원 on 2018-05-10.
 */
public class MyDrawEx extends View {
    // ArrayList to have the vertices touched (터치한 점을 저장하기 위한 어레이리스트)
    ArrayList<Vertex> alVertex = null;

    // (xml에서 layout으로 사용하기 위해서는 생성자 두 개 필요 ***)
    public MyDrawEx(Context context) {
        super(context);
        if(alVertex == null)
            alVertex = new ArrayList<Vertex>();
    }
    public MyDrawEx(Context c, AttributeSet a) {
        super(c, a);
        if(alVertex == null)
            alVertex = new ArrayList<Vertex>();
    }

    public boolean onTouchEvent(MotionEvent event) {
        // when touch is continued (터치하거나 드래그 중인 경우)
        if(event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
            // get the position(x, y) of touch point
            float posX = event.getX();
            float posY = event.getY();

            // add the vertex touched (터치된 정점 추가)
            alVertex.add(new Vertex(posX, posY, false));
            // initialize canvas and recall onDraw function!
            invalidate();
        }

       //  when touch is over (손을 뗀 경우)
        if(event.getAction() == MotionEvent.ACTION_UP) {
            // get the position(x, y) of touch up
            float posX = event.getX();
            float posY = event.getY();
            alVertex.add(new Vertex(posX, posY, true));
        }

        return true;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint pnt = new Paint(Paint.ANTI_ALIAS_FLAG);

        pnt.setStrokeWidth(20); // thickness of paint
        pnt.setColor(Color.BLUE); // color of paint

        for(int i = 0; i < alVertex.size() - 1; i++) {
            float sX = alVertex.get(i).x;
            float sY = alVertex.get(i).y;
            float eX = alVertex.get(i + 1).x;
            float eY = alVertex.get(i + 1).y;

            boolean isActionUp = alVertex.get(i).isActionUp;

            // connect points if touch is continued (손을 떼지 않은 경우에만 점을 이어준다.)
            if(!isActionUp)
                canvas.drawLine(sX, sY, eX, eY, pnt);
            else // draw points. (손을 떼었어도 점은 찍어준다.)
                canvas.drawPoint(eX, eY, pnt);
        }
    }

    // The class of vertex (x, y)
    public class Vertex {
        float x, y;
        boolean isActionUp;

        public Vertex(float x, float y, boolean isActionUp) {
            this.x = x;
            this.y = y;
            this.isActionUp = isActionUp;
        }
    }
}
