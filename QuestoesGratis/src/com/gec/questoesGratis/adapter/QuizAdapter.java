package com.gec.questoesGratis.adapter;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gec.questoesGratis.R;
import com.gec.questoesGratis.model.Question;

public class QuizAdapter extends ArrayAdapter<Question> {

   private List<Question> list;
   private Context        context;

   public QuizAdapter(Context context, int textViewResourceId, List<Question> objects) {
      super(context, textViewResourceId, objects);
      this.list = objects;
      this.context = context;
   }

   @Override
   public View getView(int position, View convertView, ViewGroup parent) {
      View row = convertView;
      try {
         if (row == null) {
            LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = li.inflate(R.layout.quiz_item, null);
         }

         Question item = list.get(position);
         if (item != null) {

            TextView vNumber = (TextView) row.findViewById(R.id.quiz_questionNumber);
            String sNumber = item.getQuestionNumberString();
            vNumber.setText(sNumber);

            TextView vId = (TextView) row.findViewById(R.id.quiz_questionId);
            String sId = String.valueOf(item.getQuestionId());
            vId.setText(sId);

            TextView vDescription = (TextView) row.findViewById(R.id.quiz_questionDescription);
            String sDescription = item.getDescription();
            vDescription.setText(sDescription);

            ImageView vStatus = (ImageView) row.findViewById(R.id.quiz_questionStatus);
            Boolean bStatus = item.getStatus();
            if (bStatus == null) {
               vStatus.setImageResource(R.drawable.empty);
            } else if (bStatus) {
               vStatus.setImageResource(R.drawable.right);
            } else {
               vStatus.setImageResource(R.drawable.wrong);
            }
         }
      } catch (Exception e) {
         Log.i(this.getClass().toString(), e.getMessage());
      }
      return row;
   }
}