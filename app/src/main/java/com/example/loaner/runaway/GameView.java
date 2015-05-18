package com.example.loaner.runaway;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;
import java.util.Vector;

/**
 * Created by Loaner on 5/15/2015.
 */
public class GameView extends View {
    //variables
    MainActivity main;
    Random rand = new Random();
    Bitmap bMonster, bScreamingPerson,bTable;
    Vector<DrawableObject> drawableObjects;
    //constructor
    public GameView(Context context) {
        super(context);
        main = (MainActivity)context;
        drawableObjects = new Vector<>();
        loadImages();
        drawableObjects.add(new DrawableObject(bMonster,100,100,100,100,5,5));
    }

    //functions
    private void loadImages(){
        bMonster = BitmapFactory.decodeResource(main.getResources(),R.drawable.monster);
        bScreamingPerson = BitmapFactory.decodeResource(main.getResources(),R.drawable.screaming_person);
        bTable = BitmapFactory.decodeResource(main.getResources(),R.drawable.table);
    }

    public boolean onTouchEvent(MotionEvent event) {
        int eventaction = event.getAction();
        float width, height;
        width = rand.nextInt(100)+50;
        height = rand.nextInt(100)+50;
        switch (eventaction) {
            case MotionEvent.ACTION_DOWN:
                float clickX = event.getX();
                float clickY = event.getY();
                drawableObjects.add(new DrawableObject(bTable,clickX,clickY,width,height));
                break;


        }
        return true;
    }
        private void drawStuff(Canvas canvas){
            for(int i = 0; i < drawableObjects.size();i++){ //for each object
                //do
                drawableObjects.elementAt(i).update(canvas);
            }
        }
        //update
        protected void onDraw(Canvas canvas) {
            //canvas.drawBitmap(backgroundimageBitmap, null, backgroundBounds, null);
            drawStuff(canvas);
            invalidate();

            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                System.err.println("Error");
            }
        }
}
