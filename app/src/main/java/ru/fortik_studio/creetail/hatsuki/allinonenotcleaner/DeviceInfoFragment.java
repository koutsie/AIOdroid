package ru.fortik_studio.creetail.hatsuki.allinonenotcleaner;


import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.chrisplus.rootmanager.RootManager;

/**
 * Created by Syorito on 09.02.2018.
 */

public class DeviceInfoFragment extends Fragment {

  @SuppressLint({"SetTextI18n", "HardwareIds"})
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    inflater.inflate(R.layout.device_info_fragment, null);
    getActivity().setTitle("Device Info");
    LayoutInflater lf = getActivity().getLayoutInflater();
    View view = lf.inflate(R.layout.device_info_fragment, container,
        false); //pass the correct layout name for the fragment

    DisplayMetrics metrics = new DisplayMetrics();
    getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);

    TextView rs = (TextView) view.findViewById(R.id.root_status);
    rs.setText("" + RootManager.getInstance().hasRooted());

    TextView brand = (TextView) view.findViewById(R.id.device);
    brand.setText("" + Build.BRAND);

    TextView model = (TextView) view.findViewById(R.id.model);
    model.setText("" + Build.MODEL);

    TextView sdk = (TextView) view.findViewById(R.id.sdk);
    sdk.setText("" + VERSION.SDK);

    TextView version = (TextView) view.findViewById(R.id.version);
    version.setText("" + Build.VERSION.RELEASE);

    TextView serial = (TextView) view.findViewById(R.id.serial);
    serial.setText("" + Build.SERIAL);

    TextView bn = (TextView) view.findViewById(R.id.bn);
    bn.setText("" + Build.DISPLAY);

    TextView kv = (TextView) view.findViewById(R.id.kv);
    kv.setText("" + Build.HARDWARE);

    TextView dp = (TextView) view.findViewById(R.id.dp);
    dp.setText(metrics.heightPixels + "*" + metrics.widthPixels);
    return view;
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

  }
}
