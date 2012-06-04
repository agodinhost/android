package com.gec.questoesGratis;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.gec.questoesGratis.adapter.QuizAdapter;
import com.gec.questoesGratis.dao.DBHelper;
import com.gec.questoesGratis.model.Quiz;
import com.gec.questoesGratis.tools.ActivityHelper;

/**
 * Activity / View to list the quizzes history.
 */
public class QuizzesActivity extends Activity implements OnItemClickListener {

   private ApplicationX xApp;
   private ListView     listView;

   @Override
   public void onCreate( Bundle savedInstanceState ) {
      super.onCreate( savedInstanceState );
      setContentView( R.layout.quizzes );
      new ActivityHelper( this ).setupActionBar( getString( R.string.app_name ) );

      xApp = (ApplicationX) getApplication();
      final DBHelper dbh = xApp.getDbHelper();
      final List< Quiz > list = dbh.getQuizzes();
      QuizAdapter adapter = new QuizAdapter( getApplicationContext(), R.id.quizzes_list, list );

      listView = (ListView) findViewById( R.id.quizzes_list );
      listView.setAdapter( adapter );
      listView.setOnItemClickListener( this );

      if( xApp.getHistoryList_selectedId() != null ) {
         listView.setSelection( xApp.getHistoryList_selectedPos() );
      }
   }

   public void onClick_previous( View view ) {
      Intent intent = new Intent( QuizzesActivity.this, MenuActivity.class );
      startActivity( intent );
   }

   @Override
   public void onItemClick( AdapterView< ? > av, View v, int position, long id ) {
      xApp.setHistoryList_selectedPos( position );
      xApp.setHistoryList_selectedId( id );

      Intent intent = new Intent( QuizzesActivity.this, AnswerActivity.class );
      startActivity( intent );
   }
}