
package com.gec.questoesGratis;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.gec.questoesGratis.adapter.AnswerPagerAdapter;
import com.gec.questoesGratis.tools.ActivityX;

public final class AnswerPagerActivity extends FragmentActivity {

   private static final ApplicationX xApp = ApplicationX.getInstance();

   @Override
   protected void onCreate( Bundle savedInstanceState ) {
      super.onCreate( savedInstanceState );
      setContentView( R.layout.pager );
      new ActivityX( this ).setupActionBar( getString( R.string.g_APP_NAME ) );

      final ViewPager pager = (ViewPager) findViewById( R.id.pager );
      pager.setAdapter( new AnswerPagerAdapter( getSupportFragmentManager() ) );
      xApp.setPager( pager );
   }

   public void onClick_FIRST( View view ) {
      xApp.moveFirst();
   }

   public void onClick_PREVIOUS( View view ) {
      xApp.movePrevious();
   }

   public void onClick_NEXT( View view ) {
      xApp.moveNext();
   }

   public void onClick_LAST( View view ) {
      xApp.moveLast();
   }
}