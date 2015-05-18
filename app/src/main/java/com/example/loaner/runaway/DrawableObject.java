package com.example.loaner.runaway;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

/**
 * Created by Loaner on 5/15/2015.
 */
public class DrawableObject {
    //variables
    float xPos, yPos, xVelocity, yVelocity, maxVelocity;
    RectF bounds;
    Bitmap bitmap;
    DrawableObject interceptTarget = null;

    //constructors
    public DrawableObject(Bitmap bitmap, float xPos,float yPos, float width,float height)
    {
        xVelocity = 0;
        yVelocity = 0;
        this.bitmap = bitmap;
        this.xPos = xPos;
        this.yPos = yPos;
        bounds = new RectF(xPos-width*.5f,yPos-height*.5f,xPos+width*.5f,yPos+height*.5f); //positions always start at 0 at the top left
    }
    public DrawableObject(Bitmap bitmap, float xPos,float yPos, float width,float height, float xVelocity, float yVelocity)
    {
        this.bitmap = bitmap;
        this.xPos = xPos;
        this.yPos = yPos;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        bounds = new RectF(xPos-width*.5f,yPos-height*.5f,xPos+width*.5f,yPos+height*.5f); //positions always start at 0 at the top left
    }

    //functions
    private void move(){
        xPos+=xVelocity;
        yPos+=yVelocity;
        setBounds();
        }
    private void setBounds(){
        float width = bounds.width();
        float height = bounds.height();
        bounds.set(xPos-width*.5f,yPos-height*.5f,xPos+width*.5f,yPos+height*.5f);
    }
    private void intercept(DrawableObject target){
        float a,c,d,e,g,h,i,j,t=-1,t1,t2;

        float A,B,C; //quadratic
        //givens
        a = xPos;
        c = target.xPos;
        d = target.xVelocity;
        e = yPos;
        g = target.yPos;
        h = target.yVelocity;
        //calculated
        i = c-a;
        j = g-e;

        A = d*d+h*h-maxVelocity*maxVelocity;
        B = 2*i*d+2*j*h;
        C = i*i + j*j;

        float u = B*B-4*A*C;
        if (u < 0)
            ;//setDestination((int)player.rx, (int)player.ry);

            // no intercept move toward;
        else{
            u = (float) Math.pow(u, .5);
            t1 = (-B+u)/(2*A);
            t2 = (-B-u)/(2*A);
            if (t1>0 && t2>0)//2 valid answers
            {
                if (t1<t2)t = t1; else t = t2;
            }
            if (t1<0 && t2 >0)
                t = t2;
            if (t1>0 && t2<0)
                t = t1;

            if (t == t1 || t == t2){
                xVelocity = (i+d*t)/t;
                yVelocity = (j + h*t)/t;
              }
           }
    }
    private void drawSelf(Canvas canvas){
        canvas.drawBitmap(bitmap,null,bounds,null);
    }
    //update
   public  void update(Canvas canvas){
       if (interceptTarget != null)
           intercept(interceptTarget);

       move();
       drawSelf(canvas);
   }
}
