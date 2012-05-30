package com.gec.questoesGratis.tools;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import android.app.Application;
import android.content.res.AssetManager;
import android.content.res.Resources;

/**
 * Android Properties X helper class.
 * 
 * @author agodinho
 */
public final class PropertiesX {

   private static final LogX log = new LogX(PropertiesX.class);

   private final Properties  properties;

   public PropertiesX(Application xApp, String filename) {

      final Resources resources = xApp.getResources();
      if (resources == null) {
         throw new RuntimeException("Could not get the application resources ...");
      }

      final AssetManager assetManager = resources.getAssets();
      if (assetManager == null) {
         throw new RuntimeException("Could not get the asset manager ...");
      }

      properties = new Properties();

      try {

         final InputStream is = assetManager.open(filename);
         log.d("properties [{0}] open.", filename);

         properties.load(is);
         log.d("properties [{0}] loaded.", filename);

         is.close();
         log.d("properties [{0}] close.", filename);

      } catch (IOException e) {
         log.e(e, "Failed to open the [{0}] file.", filename);
      }
   }

   public String getString(String key) {
      final String value = properties.getProperty(key);
      log.d("property [{0}] = [{1}]", key, value);
      return value;
   }

   public Integer getInteger(String key) {
      final String value = properties.getProperty(key);
      log.d("property [{0}] = [{1}]", key, value);
      try {
         return Integer.parseInt(value);
      } catch (NumberFormatException e) {
         return null;
      }
   }
}