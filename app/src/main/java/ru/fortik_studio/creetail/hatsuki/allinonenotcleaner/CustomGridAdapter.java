package ru.fortik_studio.creetail.hatsuki.allinonenotcleaner;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * Created by Syorito on 18.02.2018.
 */

public class CustomGridAdapter extends BaseAdapter {
  Context context;
  int wallpaper[];
  LayoutInflater inflter;
  public CustomGridAdapter(Context applicationContext, int[] logos) {
    this.context = applicationContext;
    this.wallpaper = logos;
    inflter = (LayoutInflater.from(applicationContext));
  }
  @Override
  public int getCount() {
    return wallpaper.length;
  }
  @Override
  public Object getItem(int i) {
    return null;
  }
  @Override
  public long getItemId(int i) {
    return 0;
  }
  @SuppressLint("ViewHolder")
  @Override
  public View getView(int i, View view, ViewGroup viewGroup) {
    view = inflter.inflate(R.layout.grid_image, null); // inflate the layout
    ImageView icon = (ImageView) view.findViewById(R.id.icon); // get the reference of ImageView
    icon.setImageResource(wallpaper[i]); // set logo images
    return view;
  }
}
