package com.gec.questoesGratis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.gec.questoesGratis.adapter.QuizzesAdapter;
import com.gec.questoesGratis.tools.ActivityX;

/**
 * Activity / View to list the quizzes history.
 */
public final class QuizzesActivity extends Activity implements OnItemClickListener {

   private static final ApplicationX xApp = ApplicationX.getInstance();

   private QuizzesAdapter            adapter;

   @Override
   public void onCreate( Bundle savedInstanceState ) {
      super.onCreate( savedInstanceState );
      setContentView( R.layout.quizzes );
      new ActivityX( this ).setupActionBar( getString( R.string.app_name ) );

      adapter = new QuizzesAdapter( getApplicationContext(), R.id.quizzes_list, xApp.getQuizzes() );

      final ListView listView = (ListView) findViewById( R.id.quizzes_list );
      listView.setAdapter( adapter );
      listView.setOnItemClickListener( this );
   }

   public void onClick_previous( View view ) {
      startActivity( new Intent( this, MenuActivity.class ) );
   }

   @Override
   public void onItemClick( AdapterView< ? > av, View v, int index, long id ) {
      xApp.setQuiz( adapter.getQuiz( index ) );
      startActivity( new Intent( this, AnswersActivity.class ) );
   }
}