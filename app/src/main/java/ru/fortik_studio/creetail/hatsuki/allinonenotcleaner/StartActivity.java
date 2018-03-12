package ru.fortik_studio.creetail.hatsuki.allinonenotcleaner;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import es.dmoral.toasty.Toasty;
import java.io.File;

//MAIN ACTIVITY
public class StartActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {

  String folderPath;
  File folder;
  boolean doubleBackToExitPressedOnce = false;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_start);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.addDrawerListener(toggle);
    toggle.syncState();

    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);

    displaySelectedScreen(R.id.nav_start);

    folderPath = createFolder();
  }

  //CREATE FOLDER FOR FILES
  private String createFolder() {
    String extStorageDirectory = Environment
        .getExternalStorageDirectory().toString();
    folder = new File(extStorageDirectory, "/AIO");
    if (!folder.exists()) {
      folder.mkdir();
    }
    return folder.getPath().toString();
  }

  //BACK PRESS
  @Override
  public void onBackPressed() {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      if (doubleBackToExitPressedOnce) {
        super.onBackPressed();
        return;
      }

      this.doubleBackToExitPressedOnce = true;
      Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

      new Handler().postDelayed(new Runnable() {

        @Override
        public void run() {
          doubleBackToExitPressedOnce = false;
        }
      }, 2000);
    }
  }

  //...MENU
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.start, menu);
    return true;
  }

  //...MENU CLICK
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_feedback) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  //FRAGMENT SELECTION
  private void displaySelectedScreen(int id) {

    Intent intent = null;
    Fragment fragment = null;

    switch (id) {
      case R.id.nav_start:
        fragment = new LaunchFragment();
        break;
      case R.id.nav_home:
        fragment = new HomeFragment();
        break;
      case R.id.nav_icons:
        fragment = new IconsFragment();
        break;
      case R.id.nav_changelog:
        fragment = new ChangelogFragment();
        break;
      case R.id.nav_device_info:
        fragment = new DeviceInfoFragment();
        break;
      case R.id.nav_wallpapers:
        fragment = new WallpaperFragment();
        break;
      case R.id.nav_extractor:
        intent = new Intent(this, ApkExtractorActivity.class);
        startActivity(intent);
        break;
      case R.id.nav_file_manager:
        intent = new Intent(this, FMActivity.class);
        startActivity(intent);
        break;
      case R.id.nav_settings:
        Toasty.warning(this, "Coming Soon...", Toast.LENGTH_SHORT).show();
        break;
      case R.id.nav_about:
        intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
        break;
    }

    if (fragment != null) {
      FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
      ft.replace(R.id.content_start, fragment);
      ft.commit();
    }

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
  }

  @SuppressWarnings("StatementWithEmptyBody")
  @Override
  public boolean onNavigationItemSelected(MenuItem item) {

    int id = item.getItemId();

    displaySelectedScreen(id);

    return true;
  }
}
