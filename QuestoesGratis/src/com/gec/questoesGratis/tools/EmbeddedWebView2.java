package com.gec.questoesGratis.tools;

import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * getHeight, getContentHeight and getMeasuredHeight are ONLY updated when using
 * loadUrl that's why we need this guy.
 * 
 * TODO: wait a fix for ISSUE 12987 on gingerbread.
 */
public class EmbeddedWebView2 extends WebViewClient {

   private static final String P_MIMETYPE     = "text/html";
   private static final String P_ENCODING     = "utf-8";
   private static final String LOG_TAG        = "EmbeddedWebClientView2";
   private static final String JS_BRIDGE      = "JSB";
   private static final String JS_CONTENTSIZE = "javascript:window." + JS_BRIDGE + ".setContentSize(" + //
                                                    "document.getElementsByTagName('html')[0].getHeight()," + //
                                                    "document.getElementsByTagName('html')[0].getWidth()" + //
                                                    ");";

   @Override
   public void onPageFinished(WebView webView, String url) {

      super.onPageFinished(webView, url);

      Log.d(LOG_TAG, "webView.getHeight " + webView.getHeight());
      Log.d(LOG_TAG, "webView.getContentHeight " + webView.getContentHeight());

      WebSettings webSettings = webView.getSettings();
      webSettings.setJavaScriptEnabled(true);
      webView.addJavascriptInterface(new JavaScriptInterface(), JS_BRIDGE);
      webView.loadUrl(JS_CONTENTSIZE);
   }

   public static void loadData(WebView webView, String data) {

      webView.setWebViewClient(new EmbeddedWebView2());

      WebSettings webSettings = webView.getSettings();
      webSettings.setSavePassword(false);
      webSettings.setSaveFormData(false);
      webSettings.setJavaScriptEnabled(true);
      webSettings.setUseWideViewPort(false);

      webView.loadData(data, P_MIMETYPE, P_ENCODING);
   }

   public static void loadDataWithBaseURL(WebView webView, String baseUrl, String data) {

      webView.setWebViewClient(new EmbeddedWebView2());

      WebSettings webSettings = webView.getSettings();
      webSettings.setSavePassword(false);
      webSettings.setSaveFormData(false);
      webSettings.setJavaScriptEnabled(true);
      webSettings.setUseWideViewPort(false);

      webView.loadDataWithBaseURL(baseUrl, data, P_MIMETYPE, P_ENCODING, null);
   }

   public class JavaScriptInterface {

      private int height, width;

      public void setContentSize(String sHeight, String sWidth) {

         if (sHeight != null) {
            width = Integer.parseInt(sHeight);
            Log.d(LOG_TAG, "Result from javascript, height = " + height);
         }
         if (sWidth != null) {
            width = Integer.parseInt(sWidth);
            Log.d(LOG_TAG, "Result from javascript, width = " + width);
         }
      }
   }
}