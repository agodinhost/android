package com.gec.questoesGratis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.gec.questoesGratis.adapter.AnswersAdapter;
import com.gec.questoesGratis.tools.ActivityX;

/**
 * Activity / View to list the quiz details.
 */
public final class AnswersActivity extends Activity implements OnItemClickListener {

   private static final ApplicationX xApp = ApplicationX.getInstance();

   @Override
   public void onCreate( Bundle savedInstanceState ) {
      super.onCreate( savedInstanceState );
      setContentView( R.layout.answers );
      new ActivityX( this ).setupActionBar( getString( R.string.app_name ) );

      final AnswersAdapter adapter = new AnswersAdapter( getApplicationContext(), R.id.answers_item_id, xApp.getAnswers() );
      final ListView listView = (ListView) findViewById( R.id.answers_list );
      listView.setAdapter( adapter );
      listView.setOnItemClickListener( this );
   }

   public void onClick_previous( View view ) {
      startActivity( new Intent( this, QuizzesActivity.class ) );
   }

   @Override
   public void onItemClick( AdapterView< ? > av, View view, int index, long id ) {
      xApp.setCurrentAnswer( index );
      startActivity( new Intent( this, DetailsActivity.class ) );
   }
}