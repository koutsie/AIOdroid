package ru.fortik_studio.creetail.hatsuki.allinonenotcleaner;

import android.app.ActionBar;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

/**
 * Created by Syorito on 20.02.2018.
 */

public class AboutActivity extends PreferenceActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    addPreferencesFromResource(R.xml.about);
    LinearLayout root = (LinearLayout)findViewById(android.R.id.list).getParent().getParent().getParent();
    Toolbar bar = (Toolbar) LayoutInflater.from(this).inflate(R.layout.toolbar, root, false);
    root.addView(bar, 0); // insert at top

    Preference email = (Preference) findPreference("pref_email");
    email.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
      @Override
      public boolean onPreferenceClick(Preference preference) {
        startActivity(new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:hikirihatsuki@gmail.com")));
        return false;
      }
    });
  }

}
