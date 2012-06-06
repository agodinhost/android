
package com.gec.questoesGratis;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.gec.questoesGratis.model.Answer;
import com.gec.questoesGratis.tools.ActivityX;
import com.gec.questoesGratis.tools.WebViewClientX;

public final class DetailsActivity extends FragmentActivity {

   private static final ApplicationX xApp    = ApplicationX.getInstance();
   private static final String       NO_DATA = xApp.getString( R.string.create_no_data );

   @Override
   public void onCreate( Bundle savedInstanceState ) {
      super.onCreate( savedInstanceState );
      setContentView( R.layout.details );
      new ActivityX( this ).setupActionBar( getString( R.string.g_APP_NAME ) );

      setup();
   }

   public void onClick_BACK( View view ) {
      finish();
   }

   private void setup() {
      final Answer answer = xApp.getAnswer();
      if( answer != null ) {
         final TextView description = (TextView) findViewById( R.id.details_description );
         description.setText( answer.getQuestionD() );

         final WebView webView = (WebView) findViewById( R.id.details_wv );
         WebViewClientX.loadData( webView, answer.getQuestion().getDescription() );
      } else {
         final TextView description = (TextView) findViewById( R.id.details_description );
         description.setText( NO_DATA );
      }
   }
}