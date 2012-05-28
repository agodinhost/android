package com.gec.questoesGratis;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.gec.questoesGratis.dao.XSelection;
import com.gec.questoesGratis.model.Question;
import com.gec.questoesGratis.tools.ActivityHelper;
import com.gec.questoesGratis.tools.EmbeddedWebView;

public class DetailsActivity extends FragmentActivity {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);

      setContentView(R.layout.details);
      new ActivityHelper(this).setupActionBar(getString(R.string.app_name));
      setup();
   }

   public void onClick_previous(View view) {
      finish();
   }

   private void setup() {

      XSelection xSel = (XSelection) getApplication();
      Question question = xSel.getQuestion();

      if (question != null) {
         TextView description = (TextView) findViewById(R.id.details_description);
         description.setText(question.getCompleteDescription());

         WebView webView = (WebView) findViewById(R.id.details_wv);
         EmbeddedWebView.loadData(webView, question.getQuestion());
      } else {
         TextView description = (TextView) findViewById(R.id.details_description);
         description.setText(getString(R.string.g_no_selection));
      }
   }
}