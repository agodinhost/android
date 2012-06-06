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
      new ActivityX( this ).setupActionBar( getString( R.string.g_APP_NAME ), false );
   }

   public void onClick_CREATE( View v ) {
      startActivity( new Intent( this, CreateActivity.class ) );
   }

   public void onClick_QUIZZES( View v ) {
      startActivity( new Intent( this, QuizzesActivity.class ) );
   }

   public void onClick_MORE( View v ) {
      startActivity( new Intent( this, MoreActivity.class ) );
   }

   public void onClick_ABOUT( View v ) {
      startActivity( new Intent( this, AboutActivity.class ) );
   }
}