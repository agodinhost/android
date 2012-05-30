package com.gec.questoesGratis;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.gec.questoesGratis.adapter.PagerAdapter;
import com.gec.questoesGratis.tools.ActivityHelper;

public class PagerActivity extends FragmentActivity {

   private ApplicationX xApp;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.pager);
      new ActivityHelper(this).setupActionBar(getString(R.string.app_name));

      xApp = (ApplicationX) getApplication();
      PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), xApp);
      ViewPager pager = (ViewPager) findViewById(R.id.pager);
      pager.setAdapter(adapter);
      xApp.setPager(pager);
   }

   public void onClick_first(View view) {
      xApp.moveFirst();
   }

   public void onClick_previous(View view) {
      xApp.movePrevious();
   }

   public void onClick_next(View view) {
      xApp.moveNext();
   }

   public void onClick_last(View view) {
      xApp.moveLast();
   }
}