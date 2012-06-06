
package com.gec.questoesGratis;

import java.util.List;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.gec.questoesGratis.adapter.AnswerPagerAdapter;
import com.gec.questoesGratis.tools.ActivityX;

public final class AnswerPagerActivity extends FragmentActivity {

   private static final ApplicationX xApp = ApplicationX.getInstance();

   @Override
   public void onCreate( Bundle savedInstanceState ) {
      super.onCreate( savedInstanceState );
      setContentView( R.layout.pager );

      final ActivityX x = new ActivityX( this );
      x.setupActionBar( getString( R.string.g_APP_NAME ) );
      x.addActionButtonCompat( R.drawable.ic_title_share, onClick_share, false );

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

   /*@formatter:off*/
   private final View.OnClickListener onClick_share = new View.OnClickListener() {

      @Override
      public void onClick( View v ) {

         final Intent intent = new Intent( android.content.Intent.ACTION_SEND );
         intent.setType( "text/plain" );
         intent.putExtra( android.content.Intent.EXTRA_TEXT, "Content to share" );

         //share via facebook.
         final PackageManager pm = v.getContext().getPackageManager();
         final List< ResolveInfo > list = pm.queryIntentActivities( intent, 0 );
         for( final ResolveInfo info : list ) {
            if( ( info.activityInfo.name ).contains( "facebook" ) ) {
               intent.addCategory( Intent.CATEGORY_LAUNCHER );
               intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED );

               final ActivityInfo activity = info.activityInfo;
               final ComponentName name = new ComponentName( activity.applicationInfo.packageName, activity.name );
               intent.setComponent( name );
               v.getContext().startActivity( intent );
               return;
            }
         }

         //share via html email.
      }
   };
   /*@formatter:on*/
}