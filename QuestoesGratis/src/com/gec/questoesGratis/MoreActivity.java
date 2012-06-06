package com.gec.questoesGratis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.gec.questoesGratis.tools.ActivityX;
import com.gec.questoesGratis.tools.WebViewClientX;

/**
 * Activity / View for user feedback and help.
 */
public final class MoreActivity extends Activity {

   @Override
   public void onCreate( Bundle savedInstanceState ) {
      super.onCreate( savedInstanceState );
      setContentView( R.layout.more );
      new ActivityX( this ).setupActionBar( getString( R.string.g_APP_NAME ) );

      final WebView webView = (WebView) findViewById( R.id.more_wv );
      WebViewClientX.loadUrl( webView, getString( R.string.more_uri ) );
   }

   public void onClick_BACK( View view ) {
      startActivity( new Intent( this, MenuActivity.class ) );
   }
}