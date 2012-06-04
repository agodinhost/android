package com.gec.questoesGratis;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.gec.questoesGratis.tools.ActivityX;

/**
 * Activity / View for user feedback and help.
 */
public final class AboutActivity extends Activity {

   //TODO: external string!!!
   public static final String INFO_URL       = "http://www.questoesGratis.com.br";
   public static final String FEEDBACK_EMAIL = "johnDoe@somewhere.com";
   public static final String MARKET_LISTING = "market://details?id=com.gec.questoesGratis";

   @Override
   public void onCreate( Bundle savedInstanceState ) {
      super.onCreate( savedInstanceState );
      setContentView( R.layout.about );
      new ActivityX( this ).setupActionBar( getString( R.string.app_name ) );
   }

   /**
    * Sends e-mail to developer with some predefined text.
    */
   public void mailClicked( View view ) {
      final Intent i = new Intent( Intent.ACTION_SEND );
      i.setType( "text/plain" );
      i.putExtra( Intent.EXTRA_EMAIL, new String[] { FEEDBACK_EMAIL } );
      i.putExtra( Intent.EXTRA_SUBJECT, getString( R.string.about_feedback_subject ) );
      final String device = Build.MANUFACTURER + " " + Build.MODEL + " (Android " + Build.VERSION.RELEASE + ")";
      i.putExtra( Intent.EXTRA_TEXT, getString( R.string.about_feedback_text, device ) );
      startActivity( i );
   }

   /**
    * Opens website of the project.
    */
   public void webClicked( View view ) {
      startActivity( new Intent( Intent.ACTION_VIEW, Uri.parse( INFO_URL ) ) );
   }

   /**
    * Opens Android Market listing
    */
   public void rate( View view ) {
      startActivity( new Intent( Intent.ACTION_VIEW, Uri.parse( MARKET_LISTING ) ) );
   }
}