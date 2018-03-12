package ru.fortik_studio.creetail.hatsuki.allinonenotcleaner;

/**
 * Created by Syorito on 21.02.2018.
 */

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import es.dmoral.toasty.Toasty;
import java.util.List;

public class ApkExtractorActivity extends AppCompatActivity {

  private ApkListAdapter apkListAdapter;

  private ProgressBar progressBar;
  private PermissionResolver permissionResolver;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.apk_extractor);

    RecyclerView listView = (RecyclerView) findViewById(android.R.id.list);

    apkListAdapter = new ApkListAdapter(this);
    listView.setLayoutManager(new LinearLayoutManager(this));
    listView.setAdapter(apkListAdapter);

    progressBar = (ProgressBar) findViewById(android.R.id.progress);
    progressBar.setVisibility(View.VISIBLE);

    new Loader(this).execute();

    permissionResolver = new PermissionResolver(this);

    setTitle("Apk Extractor");
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
      @NonNull int[] grantResults) {
    if (!permissionResolver.onRequestPermissionsResult(requestCode, permissions, grantResults)) {
      super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
  }

  public void hideProgressBar() {
    progressBar.setVisibility(View.GONE);
  }

  public void addItem(PackageInfo item) {
    apkListAdapter.addItem(item);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.apk_menu, menu);

    SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
    final SearchView searchView = (SearchView) MenuItemCompat
        .getActionView(menu.findItem(R.id.action_search));
    searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
    searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
      @SuppressLint("RestrictedApi")
      @Override
      public void onFocusChange(View view, boolean queryTextFocused) {
        if (!queryTextFocused && searchView.getQuery().length() < 1) {
          getSupportActionBar().collapseActionView();
        }
      }
    });
    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
      @Override
      public boolean onQueryTextSubmit(String s) {
        return false;
      }

      @Override
      public boolean onQueryTextChange(String s) {
        apkListAdapter.setSearchPattern(s);
        return true;
      }
    });

    return super.onCreateOptionsMenu(menu);
  }

  public void doExctract(final PackageInfo info) {
    if (!permissionResolver.resolve()) {
      return;
    }

    final Extractor extractor = new Extractor();
    try {
      String dst = extractor.extractWithoutRoot(info);
      Toasty.success(this, String.format(this.getString(R.string.toast_extracted), dst),
          Toast.LENGTH_SHORT).show();
      return;
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    new AlertDialog.Builder(this)
        .setTitle(R.string.alert_root_title)
        .setMessage(R.string.alert_root_body)
        .setPositiveButton(R.string.alert_root_yes, new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            try {
              String dst = extractor.extractWithRoot(info);
              Toasty.success(ApkExtractorActivity.this,
                  String.format(ApkExtractorActivity.this.getString(R.string.toast_extracted), dst),
                  Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
              e.printStackTrace();
              Toasty.error(ApkExtractorActivity.this, getString(R.string.toast_failed),
                  Toast.LENGTH_SHORT)
                  .show();
            }
          }
        }).setNegativeButton(R.string.alert_root_no, null)
        .show();
  }

  class Loader extends AsyncTask<Void, PackageInfo, Void> {

    ProgressDialog dialog;
    ApkExtractorActivity ApkExtractorActivity;

    public Loader(ApkExtractorActivity a) {
      dialog = ProgressDialog
          .show(a, getString(R.string.dlg_loading_title), getString(R.string.dlg_loading_body));
      ApkExtractorActivity = a;
    }

    @Override
    protected Void doInBackground(Void... params) {
      List<PackageInfo> packages = getPackageManager()
          .getInstalledPackages(PackageManager.GET_META_DATA);
      for (PackageInfo packageInfo : packages) {
        publishProgress(packageInfo);
      }
      return null;
    }

    @Override
    protected void onProgressUpdate(PackageInfo... values) {
      super.onProgressUpdate(values);
      ApkExtractorActivity.addItem(values[0]);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
      super.onPostExecute(aVoid);
      dialog.dismiss();
    }
  }

}
