package ru.fortik_studio.creetail.hatsuki.allinonenotcleaner;

import android.app.WallpaperManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import es.dmoral.toasty.Toasty;
import java.io.IOException;

/**
 * Created by Syorito on 11.02.2018.
 */

public class WallpaperFragment extends Fragment{

  GridView simpleGrid;
  int twallpaper[] = {R.drawable.twp1, R.drawable.twp2, R.drawable.twp3, R.drawable.twp4,
      R.drawable.twp5, R.drawable.twp6};

  int swallpaper[] = {R.drawable.wp1, R.drawable.wp2, R.drawable.twp3, R.drawable.wp4,
      R.drawable.wp5, R.drawable.wp6};

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    //LAYOUT IMPORT
    View v = inflater.inflate(R.layout.wallpaper_fragment, null);

    //APPBAR TITLE
    getActivity().setTitle("Wallpaper");


    simpleGrid = (GridView) v.findViewById(R.id.simpleGridView); // init GridView
    // Create an object of CustomAdapter and set Adapter to GirdView
    CustomGridAdapter customAdapter = new CustomGridAdapter(getActivity(), twallpaper);
    simpleGrid.setAdapter(customAdapter);
    // implement setOnItemClickListener event on GridView
    simpleGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        WallpaperManager wallmgr = WallpaperManager
            .getInstance(getActivity().getApplicationContext());
        try {
          wallmgr.setResource(+ swallpaper[position]);
          Toasty.success(getActivity(), getString(R.string.wallpaper_update),
              Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });

    //RETURN FOR VIEW
    return v;
  }
}
