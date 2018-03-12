package ru.fortik_studio.creetail.hatsuki.allinonenotcleaner;

/**
 * Created by Syorito on 26.01.2018.
 */

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ImageAdapterForIcons extends BaseAdapter {
  private Context mContext;

  public ImageAdapterForIcons(Context c) {
    mContext = c;
  }

  public int getCount() {
    return mThumbIds.length;
  }

  public Object getItem(int position) {
    return null;
  }

  public long getItemId(int position) {
    return 0;
  }

  // Create a new ImageView for each item referenced by the Adapter
  public View getView(int position, View convertView, ViewGroup parent) {
    ImageView imageView;
    // if it's not recycled, initialize some attributes
    if (convertView == null) {
      imageView = new ImageView(mContext);
      // Center crop image
      imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
    } else {
      imageView = (ImageView) convertView;
    }
    // Set images into ImageView
    imageView.setImageResource(mThumbIds[position]);
    return imageView;
  }

  // References to our images in res > drawable
  public Integer[] mThumbIds = {

  };
}