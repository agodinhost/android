package com.gec.questoesGratis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.gec.questoesGratis.tools.ActivityHelper;

public class MenuActivity extends Activity {

   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);

      setContentView(R.layout.menu);
      new ActivityHelper(this).setupActionBar(getString(R.string.app_name), false);
   }

   public void onClick_button1(View v) {
      Intent intent = new Intent(MenuActivity.this, FilterActivity.class);
      startActivity(intent);
   }

   public void onClick_button2(View v) {
      Intent intent = new Intent(MenuActivity.this, HistoryActivity.class);
      startActivity(intent);
   }

   public void onClick_button3(View v) {
      Intent intent = new Intent(MenuActivity.this, MarketingActivity.class);
      startActivity(intent);
   }

   public void onClick_button4(View v) {
      Intent intent = new Intent(MenuActivity.this, AboutActivity.class);
      startActivity(intent);
   }
}