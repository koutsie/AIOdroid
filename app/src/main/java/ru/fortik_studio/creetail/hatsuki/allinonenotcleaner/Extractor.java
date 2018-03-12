package ru.fortik_studio.creetail.hatsuki.allinonenotcleaner;

/**
 * Created by Syorito on 21.02.2018.
 */

import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Environment;

import java.io.*;
import java.nio.channels.FileChannel;

public class Extractor {
  private String get_out_filename(PackageInfo info) {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.FROYO) {
      return "/AIO/Apks" + info.packageName + "_v" + info.versionCode + ".apk";
    } else {
      return "AIO/Apks/" + info.packageName + "_v" + info.versionCode + ".apk";
    }
  }

  public String extractWithoutRoot(PackageInfo info) throws Exception {
    File src = new File(info.applicationInfo.sourceDir);
    File dst;
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.FROYO) {
      dst = new File(Environment.getExternalStorageDirectory(), get_out_filename(info));
    } else {
      dst = new File(Environment.getExternalStorageDirectory(),  get_out_filename(info));
    }
    dst = buildDstPath(dst);
    try {
      copy(src, dst);
    } catch (IOException ex) {
      throw new Exception(ex.getMessage());
    }
    if (!dst.exists()) {
      throw new Exception("cannot extract file [no root]");
    }
    return dst.toString();
  }

  public String extractWithRoot(PackageInfo info) throws Exception {
    File src = new File(info.applicationInfo.sourceDir);
    String path = System.getenv("EXTERNAL_STORAGE") + get_out_filename(info);
    File dst = buildDstPath(new File(path));

    Process p = null;
    StringBuilder err = new StringBuilder();
    try {
      p = Runtime.getRuntime().exec("su -c cat " + src.getAbsolutePath() + " > " + dst.getAbsolutePath());
      p.waitFor();

      if (p.exitValue() != 0) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getErrorStream()));
        String line = "";
        while ((line = reader.readLine()) != null) {
          err.append(line);
          err.append("\n");
        }

        throw new Exception(err.toString());
      }
    } catch (IOException e) {
      throw new Exception(e.getMessage());
    } catch (InterruptedException e) {
      throw new Exception(e.getMessage());
    } finally {
      if (p != null) {
        try {
          p.destroy();
        } catch (Exception e) {
        }
      }
    }

    if (!dst.exists()) {
      throw new Exception("cannot exctract file [root]");
    }

    return dst.getAbsolutePath();
  }

  private void copy(File src, File dst) throws IOException {
    FileInputStream inStream = new FileInputStream(src);
    FileOutputStream outStream = new FileOutputStream(dst);
    FileChannel inChannel = inStream.getChannel();
    FileChannel outChannel = outStream.getChannel();
    inChannel.transferTo(0, inChannel.size(), outChannel);
    inStream.close();
    outStream.close();
  }

  private File buildDstPath(File path) throws IOException {
    if ((!path.getParentFile().exists() && !path.getParentFile().mkdirs()) || !path.getParentFile().isDirectory()) {
      throw new IOException("Cannot create directory: " + path.getParentFile().getAbsolutePath());
    }
    if (!path.exists()) return path;

    File dst = path;
    String fname = path.getName();
    int index = fname.lastIndexOf(".");
    String ext = fname.substring(index);
    String name = fname.substring(0, index);

    for (int i = 0; dst.exists(); i++) {
      dst = new File(path.getParentFile(), name + "-" + String.valueOf(i) + ext);
    }

    return dst;
  }
}
