
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

   private static final ApplicationX xApp             = ApplicationX.getInstance();
   private static final String[]     FEEDBACK_EMAIL   = new String[] { xApp.getString( R.string.about_feedback_email ) };
   private static final String       FEEDBACK_SUBJECT = xApp.getString( R.string.about_feedback_subject );
   private static final String       MARKET_URI       = xApp.getString( R.string.about_market_uri );
   private static final String       WEB_URI          = xApp.getString( R.string.about_web_uri );

   @Override
   public void onCreate( Bundle savedInstanceState ) {
      super.onCreate( savedInstanceState );
      setContentView( R.layout.about );
      new ActivityX( this ).setupActionBar( getString( R.string.g_APP_NAME ) );
   }

   /**
    * Sends e-mail to developer with some predefined text.
    */
   public void onClick_FEEDBACK( View view ) {
      final Intent intent = new Intent( Intent.ACTION_SEND );
      intent.setType( "text/plain" );
      intent.putExtra( Intent.EXTRA_EMAIL, FEEDBACK_EMAIL );
      intent.putExtra( Intent.EXTRA_SUBJECT, FEEDBACK_SUBJECT );
      final String device = Build.MANUFACTURER + " " + Build.MODEL + " (Android " + Build.VERSION.RELEASE + ")";
      intent.putExtra( Intent.EXTRA_TEXT, getString( R.string.about_feedback_text, device ) );
      startActivity( intent );
   }

   /**
    * Opens the Android Market listing.
    */
   public void onClick_MARKET( View view ) {
      startActivity( new Intent( Intent.ACTION_VIEW, Uri.parse( MARKET_URI ) ) );
   }

   /**
    * Opens the project website.
    */
   public void onClick_WEB( View view ) {
      startActivity( new Intent( Intent.ACTION_VIEW, Uri.parse( WEB_URI ) ) );
   }

}