
package com.gec.questoesGratis.tools;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class EmbeddedWebView extends WebViewClient {

   private static final String P_MIMETYPE = "text/html";
   private static final String P_ENCODING = "utf-8";

   @Override
   public boolean shouldOverrideUrlLoading( WebView view, String url ) {
      view.loadUrl( url );
      return true;
   }

   public static void loadUrl( WebView webView, String url ) {

      webView.setWebViewClient( new EmbeddedWebView() );
      webView.setInitialScale( 65 );

      WebSettings webSettings = webView.getSettings();
      webSettings.setSavePassword( false );
      webSettings.setSaveFormData( false );
      webSettings.setJavaScriptEnabled( false );
      webSettings.setUseWideViewPort( false );

      webView.loadUrl( url );
   }

   public static void loadData( WebView webView, String data ) {

      webView.setWebViewClient( new EmbeddedWebView() );

      WebSettings webSettings = webView.getSettings();
      webSettings.setSavePassword( false );
      webSettings.setSaveFormData( false );
      webSettings.setJavaScriptEnabled( false );
      webSettings.setUseWideViewPort( false );

      webView.loadData( data, P_MIMETYPE, P_ENCODING );
   }

   public static void loadDataWithBaseURL( WebView webView, String baseUrl, String data ) {

      webView.setWebViewClient( new EmbeddedWebView() );

      WebSettings webSettings = webView.getSettings();
      webSettings.setSavePassword( false );
      webSettings.setSaveFormData( false );
      webSettings.setJavaScriptEnabled( false );
      webSettings.setUseWideViewPort( false );

      webView.loadDataWithBaseURL( baseUrl, data, P_MIMETYPE, P_ENCODING, null );
   }
}