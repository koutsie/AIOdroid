package ru.fortik_studio.creetail.hatsuki.allinonenotcleaner;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/*
 * Created by Syorito on 21.01.2018.
 */

public class ChangelogFragment extends Fragment {

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    getActivity().setTitle("Changelog");
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.changelog_fragment, container, false);
  }
}
