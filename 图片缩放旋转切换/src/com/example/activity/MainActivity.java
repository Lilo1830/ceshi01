package com.example.activity;

import com.iflytek.voiceads.AdError;
import com.iflytek.voiceads.AdKeys;
import com.iflytek.voiceads.IFLYAdListener;
import com.iflytek.voiceads.IFLYAdSize;
import com.iflytek.voiceads.IFLYFullScreenAd;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends Activity {
	private IFLYFullScreenAd ad;
	
	
	private int[] images;       //ͼƬ��Դid����  
    private int currentImage;   //��ǰ��ʾͼƬ  
    private int alpha;          //͸����  
      
    private Matrix matrix;  
    private int anglel;         //�Ƕ�  
      
    private int imageWidth;  
    private int imageHeight;  
      
    private int addWidth;  
    private int addHeight;  
      
    private ImageView image_all;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		createAd();
		setContentView(R.layout.activity_main);
		init();
	}
	
	private void createAd() {
		ad = IFLYFullScreenAd.createFullScreenAd(this,
				"002118DA8C697FC7971CEDE49F7A9183");
		ad.setAdSize(IFLYAdSize.FULLSCREEN);
		ad.setParameter(AdKeys.SHOW_TIME_FULLSCREEN, "6000");
		ad.setParameter(AdKeys.DOWNLOAD_ALERT, "true");
		ad.loadAd(new IFLYAdListener() {
			
			@Override
			public void onAdReceive() {
				// TODO Auto-generated method stub
				ad.showAd();
			}
			
			@Override
			public void onAdFailed(AdError arg0) {
				Toast.makeText(
						getApplicationContext(),
						arg0.getErrorCode() + "****"
								+ arg0.getErrorDescription(), 0).show();
				
			}
			
			@Override
			public void onAdExposure() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAdClose() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAdClick() {
				// TODO Auto-generated method stub
				
			}

			
		});
	}
	
	 //��ʼ����Ա����  
    private void init() {  
        images = new int[]{R.drawable.n1, R.drawable.n2,R.drawable.n3,R.drawable.n4,R.drawable.n5,
        		R.drawable.n6, R.drawable.n7,R.drawable.n8,R.drawable.n9,R.drawable.n10};  
        currentImage = Integer.MAX_VALUE / 2;  
        alpha = 255;  
          
        matrix = new Matrix();  
          
        image_all = (ImageView) findViewById(R.id.image_all);  
        image_all.setImageResource(images[currentImage % images.length]);
    }
    
    public void onClick(View view) {  
        int id = view.getId();  
          
        switch (id) {  
            case R.id.alpha_plus:   //����͸����  
                alpha += 20;  
                if(alpha >= 255)  
                    alpha = 255;  
                image_all.setAlpha(alpha);  
                break;  
                  
            case R.id.alpha_minus:  //��С͸����  
                alpha -= 20;  
                if(alpha <= 0)  
                    alpha = 0;  
                image_all.setAlpha(alpha);  
                break;  
                  
            case R.id.next_page:    //��ʾ��һ��ͼƬ  
                //Ϊ�˱�֤ͼƬ�ܹ�ѭ��, ����ģ�����ǹؼ�, ��ʾͼƬ���±�ʼ���ǳ��ȵ�ģ  
                image_all.setImageResource(images[ ++currentImage % images.length ]);  
                break;  
              
            case R.id.prev_page:    //��ʾ��һ��ͼƬ  
                image_all.setImageResource(images[ --currentImage % images.length ]);  
                break;  
                  
            case R.id.big:          //�Ŵ�ͼƬ  
                imageWidth += addWidth;  
                imageHeight += addHeight;  
                      
                image_all.setLayoutParams(new LinearLayout.LayoutParams(imageWidth, imageHeight));  
                break;  
                  
            case R.id.small:        //��СͼƬ  
                imageWidth -= addWidth;  
                imageHeight -= addHeight;  
                if(imageWidth <= 0 || imageHeight <=0){  
                    imageWidth += addWidth;  
                    imageHeight += addHeight;  
                }  
                  
                image_all.setLayoutParams(new LinearLayout.LayoutParams(imageWidth, imageHeight));  
                break;  
                  
            case R.id.turn_left:    //������ת  
                anglel += 45;  
                matrix.setRotate(anglel);  
                Bitmap bitmap = ((BitmapDrawable) getResources().getDrawable(images[currentImage % images.length])).getBitmap();  
                bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),bitmap.getHeight(), matrix, true);   
                image_all.setImageBitmap(bitmap);  
                break;  
                  
            case R.id.turn_right:   //������ת  
                anglel -= 45;  
                matrix.setRotate(anglel);  
                Bitmap bitmap1 = ((BitmapDrawable) getResources().getDrawable(images[currentImage % images.length])).getBitmap();  
                bitmap1 = Bitmap.createBitmap(bitmap1, 0, 0, bitmap1.getWidth(),bitmap1.getHeight(), matrix, true);   
                image_all.setImageBitmap(bitmap1);  
                break;  
                  
            default:  
                break;  
        }  
    }
    
    @Override  
    public void onWindowFocusChanged(boolean hasFocus) {  
        // TODO Auto-generated method stub  
        super.onWindowFocusChanged(hasFocus);  
        //��ȡImageView����Ŀ��  
        imageWidth = image_all.getWidth();  
        imageHeight = image_all.getHeight();  
          
        //����ÿ�������Լ���ֵ  
        addWidth = imageWidth / 5;  
        addHeight = imageHeight / 5;  
    }  
}
