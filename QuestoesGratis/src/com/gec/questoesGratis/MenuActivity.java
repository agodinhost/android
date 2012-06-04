package com.gec.questoesGratis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.gec.questoesGratis.tools.ActivityX;

public final class MenuActivity extends Activity {

   @Override
   public void onCreate( Bundle savedInstanceState ) {
      super.onCreate( savedInstanceState );
      setContentView( R.layout.menu );
      new ActivityX( this ).setupActionBar( getString( R.string.app_name ), false );

      getApplication();
   }

   public void onClick_NEW( View v ) {
      startActivity( new Intent( this, CreateActivity.class ) );
   }

   public void onClick_HISTORY( View v ) {
      startActivity( new Intent( this, QuizzesActivity.class ) );
   }

   public void onClick_MORE( View v ) {
      startActivity( new Intent( this, MoreActivity.class ) );
   }

   public void onClick_ABOUT( View v ) {
      startActivity( new Intent( this, AboutActivity.class ) );
   }
}