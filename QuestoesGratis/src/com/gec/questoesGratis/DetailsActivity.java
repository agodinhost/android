
package com.gec.questoesGratis;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.gec.questoesGratis.model.Answer;
import com.gec.questoesGratis.tools.ActivityHelper;
import com.gec.questoesGratis.tools.EmbeddedWebView;

public class DetailsActivity extends FragmentActivity {

   @Override
   protected void onCreate( Bundle savedInstanceState ) {
      super.onCreate( savedInstanceState );

      setContentView( R.layout.details );
      new ActivityHelper( this ).setupActionBar( getString( R.string.app_name ) );
      setup();
   }

   public void onClick_previous( View view ) {
      finish();
   }

   private void setup() {

      ApplicationX xApp = (ApplicationX) getApplication();
      Answer answer = xApp.getAnswer();
      if( answer != null ) {
         TextView description = (TextView) findViewById( R.id.details_description );
         description.setText( answer.getQuestionD() );

         WebView webView = (WebView) findViewById( R.id.details_wv );
         EmbeddedWebView.loadData( webView, answer.getQuestion().getDescription() );
      } else {
         TextView description = (TextView) findViewById( R.id.details_description );
         description.setText( getString( R.string.g_no_selection ) );
      }
   }
}