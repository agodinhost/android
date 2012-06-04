
package com.gec.questoesGratis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.gec.questoesGratis.adapter.QuizAdapter;
import com.gec.questoesGratis.tools.ActivityHelper;

/**
 * Activity / View to list the quizzes history.
 */
public class QuizzesActivity extends Activity implements OnItemClickListener {

   private ApplicationX xApp;
   private QuizAdapter  adapter;

   @Override
   public void onCreate( Bundle savedInstanceState ) {
      super.onCreate( savedInstanceState );
      setContentView( R.layout.quizzes );
      new ActivityHelper( this ).setupActionBar( getString( R.string.app_name ) );

      xApp = (ApplicationX) getApplication();
      adapter = new QuizAdapter( getApplicationContext(), R.id.quizzes_list, xApp.getQuizzes() );

      final ListView listView = (ListView) findViewById( R.id.quizzes_list );
      listView.setAdapter( adapter );
      listView.setOnItemClickListener( this );

      //if( xApp.getSelectedQuizId() != null ) {
      //   listView.setSelection( xApp.getHistoryList_selectedPos() );
      //}
   }

   public void onClick_previous( View view ) {
      final Intent intent = new Intent( QuizzesActivity.this, MenuActivity.class );
      startActivity( intent );
   }

   @Override
   public void onItemClick( AdapterView< ? > av, View v, int index, long id ) {
      xApp.setQuiz( adapter.getQuiz( index ) );
      final Intent intent = new Intent( QuizzesActivity.this, AnswersActivity.class );
      startActivity( intent );
   }
}