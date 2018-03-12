package ru.fortik_studio.creetail.hatsuki.allinonenotcleaner;

/**
 * Created by Syorito on 22.02.2018.
 */

import android.app.WallpaperManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import es.dmoral.toasty.Toasty;
import java.io.IOException;

public class AndroidImageAdapter extends PagerAdapter {

  Context mContext;

  private int[] sliderImagesId = new int[]{
      R.drawable.twp1,
      R.drawable.twp2,
      R.drawable.twp3,
      R.drawable.twp4,
      R.drawable.twp5,
      R.drawable.twp6
  };

  AndroidImageAdapter(Context context) {
    this.mContext = context;
  }

  @Override
  public int getCount() {
    return sliderImagesId.length;
  }

  @Override
  public boolean isViewFromObject(View v, Object obj) {
    return v == ((ImageView) obj);
  }

  @Override
  public Object instantiateItem(ViewGroup container, int i) {
    ImageView mImageView = new ImageView(mContext);
    mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
    mImageView.setImageResource(sliderImagesId[i]);
    ((ViewPager) container).addView(mImageView, 0);
    return mImageView;
  }

  @Override
  public void destroyItem(ViewGroup container, int i, Object obj) {
    ((ViewPager) container).removeView((ImageView) obj);
  }
}
