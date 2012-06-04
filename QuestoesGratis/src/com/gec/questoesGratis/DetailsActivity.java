package com.gec.questoesGratis;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.gec.questoesGratis.model.Answer;
import com.gec.questoesGratis.tools.ActivityX;
import com.gec.questoesGratis.tools.EmbeddedWebView;

public final class DetailsActivity extends FragmentActivity {

   private static final ApplicationX xApp = ApplicationX.getInstance();

   @Override
   protected void onCreate( Bundle savedInstanceState ) {
      super.onCreate( savedInstanceState );
      setContentView( R.layout.details );
      new ActivityX( this ).setupActionBar( getString( R.string.app_name ) );

      setup();
   }

   public void onClick_previous( View view ) {
      finish();
   }

   private void setup() {
      final Answer answer = xApp.getAnswer();
      if( answer != null ) {
         final TextView description = (TextView) findViewById( R.id.details_description );
         description.setText( answer.getQuestionD() );

         final WebView webView = (WebView) findViewById( R.id.details_wv );
         EmbeddedWebView.loadData( webView, answer.getQuestion().getDescription() );
      } else {
         final TextView description = (TextView) findViewById( R.id.details_description );
         description.setText( getString( R.string.g_no_selection ) );
      }
   }
}