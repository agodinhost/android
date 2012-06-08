
package com.gec.questoesGratis;

import java.util.List;

import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.gec.questoesGratis.adapter.AnswerAdapter;
import com.gec.questoesGratis.tools.ActivityX;
import com.gec.questoesGratis.tools.DialogX;

public final class AnswerActivity extends FragmentActivity {

   private static final ApplicationX xApp = ApplicationX.getInstance();

   @Override
   public void onCreate( Bundle savedInstanceState ) {
      super.onCreate( savedInstanceState );
      setContentView( R.layout.answer );

      final ActivityX x = new ActivityX( this );
      x.setupActionBar( getString( R.string.g_APP_NAME ) );
      x.addActionButtonCompat( R.drawable.ic_title_share, onClick_share, false );

      final ViewPager pager = (ViewPager) findViewById( R.id.answer_pager );
      pager.setAdapter( new AnswerAdapter( getSupportFragmentManager() ) );
      xApp.setPager( pager );
      toggleNavButtons();
   }

   public void onClick_FIRST( View view ) {
      xApp.moveFirst();
      toggleNavButtons();
   }

   public void onClick_PREVIOUS( View view ) {
      xApp.movePrevious();
      toggleNavButtons();
   }

   public void onClick_NEXT( View view ) {
      xApp.moveNext();
      toggleNavButtons();
   }

   public void onClick_LAST( View view ) {
      xApp.moveLast();
      toggleNavButtons();
   }

   public void onClick_FINISH( View view ) {
      DialogX.confirm( //
            this, //
            onClick_confirm, //
            xApp.isMissingAnyAnswer()? //
            R.string.answer_confirm_incomplete //
                  : //
                  R.string.answer_confirm_complete );
   }

   private void toggleNavButtons() {
      toggle( R.id.answer_first, R.id.answer_previous, xApp.isFirstAnswer() );
      toggle( R.id.answer_last, R.id.answer_next, xApp.isLastAnswer() );
   }

   private void toggle( int resId1, int resId2, boolean disabled ) {
      final Button btn1 = (Button) findViewById( resId1 );
      btn1.setEnabled( !disabled );
      final Button btn2 = (Button) findViewById( resId2 );
      btn2.setEnabled( !disabled );
   }

   /*@formatter:off*/

   private static final DialogInterface.OnClickListener onClick_confirm = new OnClickListener() {
      @Override
      public void onClick( DialogInterface dialog, int response ) {
         if( response == android.R.string.yes ) {
            final float rating = xApp.finalizeQuiz();
            //TODO: move to screen ...
         }
      }
   };

   private final View.OnClickListener onClick_share = new View.OnClickListener() {

      @Override
      public void onClick( View v ) {

         final Intent intent = new Intent( android.content.Intent.ACTION_SEND );
         intent.setType( "text/plain" );
         intent.putExtra( android.content.Intent.EXTRA_TEXT, xApp.getFormat2Fabebook() );

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