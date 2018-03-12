package ru.fortik_studio.creetail.hatsuki.allinonenotcleaner;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import com.chrisplus.rootmanager.RootManager;

/*
 * Created by Syorito on 20.01.2018.
 */

public class HomeFragment extends Fragment {



  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.home_fragment, null);
    getActivity().setTitle("Home");

    return v;
  }
}