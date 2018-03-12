package ru.fortik_studio.creetail.hatsuki.allinonenotcleaner;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import es.dmoral.toasty.Toasty;
import java.util.ArrayList;
import java.util.HashMap;

public class IconsFragment extends ListFragment {

  ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
  SimpleAdapter adapter;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {


    String[] names = {
        "2248",
        "ADW Launcher",
        "AIDE",
        "AliExpress",
        "All In One [NOT CLEANER]",
        //"Androidify",
        "AZ Screen Recorder",
        "VK",
        "YouTube"
    };

    String[] type = {
        getString(R.string.type_game),
        getString(R.string.type_launcher),
        getString(R.string.type_programming),
        getString(R.string.type_shop),
        getString(R.string.type_icon_pack),
        //getString(R.string.type_desing),
        getString(R.string.type_screen_rec),
        getString(R.string.type_social),
        getString(R.string.type_social)
    };

    int[] icons = {
        R.drawable.game_2248_icon,
        R.drawable.adw_icon,
        R.drawable.aide_icon,
        R.drawable.ali_icon,
        R.drawable.aio_icon,
        //R.drawable.androidify_icon,
        R.drawable.az_icon,
        R.drawable.vk_icon,
        R.drawable.yt_icon
    };


    getActivity().setTitle("Icons");

    //MAP
    HashMap<String, String> map = new HashMap<String, String>();

    //FILL
    for (int i = 0; i < names.length; i++) {
      map = new HashMap<String, String>();
      map.put("Names", names[i]);
      map.put("Type", type[i]);
      map.put("Icons", Integer.toString(icons[i]));

      data.add(map);
    }

    //KEYS IN MAP
    String[] from = {"Names", "Type", "Icons"};

    //IDS OF VIEWS
    int[] to = {R.id.nameTxt, R.id.nameType, R.id.imageView1};

    //ADAPTER
    adapter = new SimpleAdapter(getActivity(), data, R.layout.icons_fragment, from, to);
    setListAdapter(adapter);

    return super.onCreateView(inflater, container, savedInstanceState);
  }

  @Override
  public void onStart() {
    super.onStart();

    getListView().setOnItemClickListener(new OnItemClickListener() {

      @Override
      public void onItemClick(AdapterView<?> av, View v, int pos,
          long id) {

        Toasty.info(getActivity(), "Name:" + data.get(pos).get("Names") + "\n" + "Type: " + data.get(pos).get("Type"), Toast.LENGTH_SHORT).show();

      }
    });
  }

}
