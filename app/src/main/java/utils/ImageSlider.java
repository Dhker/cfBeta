package utils;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import java.util.HashMap;

/**
 * Created by Dhker on 12/29/2015.
 */
public class ImageSlider implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    private SliderLayout mDemoSlider;
    private HashMap<String,String> url_map ;
    private Context context;

    public ImageSlider(Context context ,SliderLayout slider)
    {
        this.context= context;
        this.mDemoSlider=slider ;
        this.url_map=new HashMap<String, String>();



    }

    public void setupSlider()
    {
        for(String name : url_map.keySet()){
            TextSliderView textSliderView = new TextSliderView(context);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(url_map.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);

            mDemoSlider.addSlider(textSliderView);
        }

        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(this);
    }

    //CALL ON STOP
    public void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        mDemoSlider.stopAutoCycle();
    }

    public void addImage(String key,String image)
    {
        this.url_map.put(key,image);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(context,slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();


    }
}
