
package com.gec.questoesGratis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.gec.questoesGratis.adapter.AnswerAdapter;
import com.gec.questoesGratis.tools.ActivityHelper;

/**
 * Activity / View to list the quiz answers.
 */
public class AnswersActivity extends Activity implements OnItemClickListener {

   private ApplicationX xApp;

   @Override
   public void onCreate( Bundle savedInstanceState ) {
      super.onCreate( savedInstanceState );
      setContentView( R.layout.answers );
      new ActivityHelper( this ).setupActionBar( getString( R.string.app_name ) );

      xApp = (ApplicationX) getApplication();
      final AnswerAdapter adapter = new AnswerAdapter( getApplicationContext(), R.id.answers_item_id, xApp.getAnswers() );

      final ListView listView = (ListView) findViewById( R.id.answers_list );
      listView.setAdapter( adapter );
      listView.setOnItemClickListener( this );
   }

   public void onClick_previous( View view ) {
      final Intent intent = new Intent( AnswersActivity.this, QuizzesActivity.class );
      startActivity( intent );
   }

   @Override
   public void onItemClick( AdapterView< ? > av, View view, int index, long id ) {
      xApp.setCurrentAnswer( index );
      final Intent intent = new Intent( AnswersActivity.this, DetailsActivity.class );
      startActivity( intent );
   }
}