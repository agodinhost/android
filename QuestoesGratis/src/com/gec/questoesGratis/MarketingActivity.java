package com.gec.questoesGratis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.gec.questoesGratis.tools.ActivityHelper;
import com.gec.questoesGratis.tools.EmbeddedWebView;

/**
 * Activity / View for user feedback and help.
 */
public class MarketingActivity extends Activity {

   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.marketing);
      new ActivityHelper(this).setupActionBar(getString(R.string.app_name));

      WebView webView = (WebView) findViewById(R.id.marketing_wv);
      EmbeddedWebView.loadUrl(webView, getString(R.string.marketing_uri));
   }

   public void onClick_previous(View view) {
      Intent intent = new Intent(MarketingActivity.this, MenuActivity.class);
      startActivity(intent);
   }
}