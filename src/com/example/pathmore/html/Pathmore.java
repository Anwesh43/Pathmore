package com.example.pathmore.html;

import java.io.FileOutputStream;

import android.os.Bundle;
import android.content.*;
import android.content.pm.*;
import android.view.*;
import android.graphics.*;
import android.app.Activity;
import android.view.Menu;

public class Pathmore extends Activity {
float x[]=new float[1000],y[]=new float[1000],r1[]=new float[1000],k[]=new float[1000],a[]=new float[1000],b[]=new float[1000];
int j=0;
MyView m;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		m=new MyView(this);
		setContentView(m);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	}
	class MyView extends View
	{
		Paint p=new Paint(Paint.ANTI_ALIAS_FLAG);
		public MyView(Context context)
		{
			super(context);
			setDrawingCacheEnabled(true);
			p.setColor(Color.GREEN);
		}
		public void onDraw(Canvas canvas)
		{
			canvas.drawColor(Color.WHITE);
			for(int i=0;i<j;i++)
			{
				canvas.save();
				canvas.translate(x[i],y[i]);
				drawTr(canvas,i);
				canvas.restore();
			}
			try
			{
				Thread.sleep(100);
				invalidate();
			}
			catch(Exception ex)
			{
				
			}
		}
		public void drawTr(Canvas canvas,int n)
		{
		
			for(int i=0;i<=a[n];i++)
			{
				float x1=(float)(r1[n]*Math.cos(i*Math.PI/3)),y1=(float)(r1[n]*Math.sin(i*Math.PI/3));
				canvas.drawArc(new RectF(x1-10,y1-10,x1+10,y1+10),0,360,true,p);
			}
			
			a[n]+=k[n];
			if(a[n]==b[n])
			{
				a[n]=b[n]-k[n]*6;
				r1[n]+=k[n]*10;
				if(r1[n]==60 || r1[n]==0)
				{
					k[n]*=-1;
					r1[n]+=k[n]*10;
					b[n]=a[n];
					a[n]=b[n]-k[n]*6;
				}
				
			}
		}
		public boolean onTouchEvent(MotionEvent event)
		{
			if(event.getAction()==MotionEvent.ACTION_DOWN)
			{
				x[j]=event.getX();
				y[j]=event.getY();
				k[j]=1;
				a[j]=0;
				b[j]=6;
				r1[j]=10;
				j++;
			}
			try
			{
				FileOutputStream fos=new FileOutputStream("sdcard/bamove.jpeg");
				getDrawingCache().compress(Bitmap.CompressFormat.JPEG,90,fos);
				fos.close();
				
			}
			catch(Exception ex)
			{
				
			}
			postInvalidate();
			return true;
		}
	}

}
