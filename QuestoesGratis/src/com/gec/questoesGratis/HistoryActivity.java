package com.gec.questoesGratis;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.gec.questoesGratis.adapter.HistoryAdapter;
import com.gec.questoesGratis.dao.XSelection;
import com.gec.questoesGratis.model.History;
import com.gec.questoesGratis.tools.ActivityHelper;

/**
 * Activity / View to list the quiz history.
 */
public class HistoryActivity extends Activity implements OnItemClickListener {

   private XSelection xSel;
   private ListView   listView;

   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.history);
      new ActivityHelper(this).setupActionBar(getString(R.string.app_name));

      xSel = (XSelection) getApplication();

      List<History> list = xSel.getHistoryFromDB();
      HistoryAdapter adapter = new HistoryAdapter(getApplicationContext(), R.id.history_list, list);

      listView = (ListView) findViewById(R.id.history_list);
      listView.setAdapter(adapter);
      listView.setOnItemClickListener(this);

      if (xSel.getHistoryList_selectedId() != null) {
         listView.setSelection(xSel.getHistoryList_selectedPos());
      }
   }

   public void onClick_previous(View view) {
      Intent intent = new Intent(HistoryActivity.this, MenuActivity.class);
      startActivity(intent);
   }

   @Override
   public void onItemClick(AdapterView<?> av, View v, int position, long id) {
      xSel.setHistoryList_selectedPos(position);
      xSel.setHistoryList_selectedId(id);

      Intent intent = new Intent(HistoryActivity.this, QuizActivity.class);
      startActivity(intent);
   }
}