package com.gec.questoesGratis;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.gec.questoesGratis.adapter.PagerAdapter;
import com.gec.questoesGratis.dao.XSelection;
import com.gec.questoesGratis.tools.ActivityHelper;

public class PagerActivity extends FragmentActivity {

   private XSelection xSel;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.pager);
      new ActivityHelper(this).setupActionBar(getString(R.string.app_name));

      xSel = (XSelection) getApplication();
      PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), xSel);
      ViewPager pager = (ViewPager) findViewById(R.id.pager);
      pager.setAdapter(adapter);
      xSel.setPager(pager);
   }

   public void onClick_first(View view) {
      xSel.moveFirst();
   }

   public void onClick_previous(View view) {
      xSel.movePrevious();
   }

   public void onClick_next(View view) {
      xSel.moveNext();
   }

   public void onClick_last(View view) {
      xSel.moveLast();
   }
}