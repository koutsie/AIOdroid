package ru.fortik_studio.creetail.hatsuki.allinonenotcleaner;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Syorito on 11.02.2018.
 */

public class LaunchFragment extends Fragment {

  ImageView author;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.start_fragment, null);
    getActivity().setTitle("All In One");

    return v;
  }

  public void onClick(View v) {
    Intent i = null;
    if(v.getId() == R.id.nav_start) {
      i = new Intent(Intent.ACTION_VIEW,Uri.parse("http://fortik-studio.ru/"));
      startActivity(i);

    }
  }

}
