package com.gec.questoesGratis;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.gec.questoesGratis.adapter.AnswerAdapter;
import com.gec.questoesGratis.dao.DBHelper;
import com.gec.questoesGratis.model.Answer;
import com.gec.questoesGratis.tools.ActivityHelper;

/**
 * Activity / View to list the quiz details.
 */
public class AnswerActivity extends Activity implements OnItemClickListener {

   private ApplicationX xApp;
   private ListView     listView;

   @Override
   public void onCreate( Bundle savedInstanceState ) {
      super.onCreate( savedInstanceState );
      setContentView( R.layout.answer );
      new ActivityHelper( this ).setupActionBar( getString( R.string.app_name ) );

      xApp = (ApplicationX) getApplication();
      final DBHelper dbh = xApp.getDbHelper();
      final long quizId = xApp.getHistoryList_selectedId();
      final List< Answer > answers = dbh.getAnswers( quizId );
      AnswerAdapter adapter = new AnswerAdapter( getApplicationContext(), R.id.answer_questionId, answers );

      listView = (ListView) findViewById( R.id.answer_list );
      listView.setAdapter( adapter );
      listView.setOnItemClickListener( this );
   }

   public void onClick_previous( View view ) {
      Intent intent = new Intent( AnswerActivity.this, QuizzesActivity.class );
      startActivity( intent );
   }

   @Override
   public void onItemClick( AdapterView< ? > av, View view, int arg2, long arg3 ) {

      // TODO: set current question ...
      Intent intent = new Intent( AnswerActivity.this, DetailsActivity.class );
      startActivity( intent );
   }
}