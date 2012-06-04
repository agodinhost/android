
package com.gec.questoesGratis;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;

import com.gec.questoesGratis.dao.DBHelper;
import com.gec.questoesGratis.tools.ActivityHelper;

public class MenuActivity extends Activity {

   @Override
   public void onCreate( Bundle savedInstanceState ) {
      super.onCreate( savedInstanceState );

      final DBHelper dbHelper = new DBHelper( this );
      final ApplicationX xApp = (ApplicationX) getApplication();
      xApp.setDbHelper( dbHelper );

      try {
         dbHelper.createDataBase();
      } catch( IOException e ) {
         throw new Error( "Unable to create the application database ..." );
      }

      try {
         dbHelper.openDataBase();
      } catch( SQLException e ) {
         throw new Error( "Unable to open the application database ..." );
      }

      setContentView( R.layout.menu );
      new ActivityHelper( this ).setupActionBar( getString( R.string.app_name ), false );
   }

   public void onClick_NEW( View v ) {
      final Intent intent = new Intent( MenuActivity.this, FilterActivity.class );
      startActivity( intent );
   }

   public void onClick_HISTORY( View v ) {
      final Intent intent = new Intent( MenuActivity.this, QuizzesActivity.class );
      startActivity( intent );
   }

   public void onClick_MORE( View v ) {
      final Intent intent = new Intent( MenuActivity.this, MarketingActivity.class );
      startActivity( intent );
   }

   public void onClick_ABOUT( View v ) {
      final Intent intent = new Intent( MenuActivity.this, AboutActivity.class );
      startActivity( intent );
   }
}