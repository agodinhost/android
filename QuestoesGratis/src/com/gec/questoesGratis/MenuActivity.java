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

   public void onClick_NEW(View v) {
      Intent intent = new Intent(MenuActivity.this, FilterActivity.class);
      startActivity(intent);
   }

   public void onClick_HISTORY(View v) {
      Intent intent = new Intent(MenuActivity.this, QuizActivity.class);
      startActivity(intent);
   }

   public void onClick_MORE(View v) {
      Intent intent = new Intent(MenuActivity.this, MarketingActivity.class);
      startActivity(intent);
   }

   public void onClick_ABOUT(View v) {
      Intent intent = new Intent(MenuActivity.this, AboutActivity.class);
      startActivity(intent);
   }
}