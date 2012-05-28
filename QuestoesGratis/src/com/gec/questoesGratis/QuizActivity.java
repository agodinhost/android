package com.gec.questoesGratis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.gec.questoesGratis.adapter.QuizAdapter;
import com.gec.questoesGratis.dao.XSelection;
import com.gec.questoesGratis.model.Quiz;
import com.gec.questoesGratis.tools.ActivityHelper;

/**
 * Activity / View to list the quiz details.
 */
public class QuizActivity extends Activity implements OnItemClickListener {

   private XSelection xSel;
   private ListView   listView;

   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.quiz);
      new ActivityHelper(this).setupActionBar(getString(R.string.app_name));

      xSel = (XSelection) getApplication();

      long id = xSel.getHistoryList_selectedId();
      Quiz quiz = xSel.getQuizFromDB(id);
      QuizAdapter adapter = new QuizAdapter(getApplicationContext(), R.id.quiz_questionId, quiz.getQuestions());

      listView = (ListView) findViewById(R.id.quiz_list);
      listView.setAdapter(adapter);
      listView.setOnItemClickListener(this);
   }

   public void onClick_previous(View view) {
      Intent intent = new Intent(QuizActivity.this, HistoryActivity.class);
      startActivity(intent);
   }

   @Override
   public void onItemClick(AdapterView<?> av, View view, int arg2, long arg3) {

      // TODO: set current question ...
      Intent intent = new Intent(QuizActivity.this, DetailsActivity.class);
      startActivity(intent);
   }
}