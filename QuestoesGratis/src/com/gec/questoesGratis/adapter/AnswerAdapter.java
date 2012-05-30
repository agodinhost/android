package com.gec.questoesGratis.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gec.questoesGratis.R;
import com.gec.questoesGratis.model.Answer;
import com.gec.questoesGratis.tools.LogX;

public class AnswerAdapter extends ArrayAdapter<Answer> {

   private static final LogX log = new LogX(AnswerAdapter.class);

   private List<Answer>      list;
   private Context           context;

   public AnswerAdapter(Context context, int textViewResourceId, List<Answer> objects) {
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
            row = li.inflate(R.layout.answer_item, null);
         }

         Answer answer = list.get(position);
         if (answer != null) {

            TextView vNumber = (TextView) row.findViewById(R.id.answer_questionNumber);
            String sNumber = answer.getNumberD();
            vNumber.setText(sNumber);

            TextView vId = (TextView) row.findViewById(R.id.answer_questionId);
            String sId = String.valueOf(answer.getId());
            vId.setText(sId);

            TextView vDescription = (TextView) row.findViewById(R.id.answer_questionDescription);
            String sDescription = answer.getQualifierD();
            vDescription.setText(sDescription);

            ImageView vStatus = (ImageView) row.findViewById(R.id.answer_questionStatus);
            Boolean bStatus = answer.getStatus();
            if (bStatus == null) {
               vStatus.setImageResource(R.drawable.empty);
            } else if (bStatus) {
               vStatus.setImageResource(R.drawable.right);
            } else {
               vStatus.setImageResource(R.drawable.wrong);
            }
         }
      } catch (Exception e) {
         log.e(e);
      }
      return row;
   }
}