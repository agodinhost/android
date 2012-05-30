package com.gec.questoesGratis.adapter;

import java.text.SimpleDateFormat;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.gec.questoesGratis.R;
import com.gec.questoesGratis.model.History;
import com.gec.questoesGratis.model.StatusEnum;
import com.gec.questoesGratis.tools.LogX;

public class HistoryAdapter extends ArrayAdapter<History> {

   private static final LogX             log = new LogX(HistoryAdapter.class);

   private List<History>                 list;
   private Context                       context;

   private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy - HH:mm");

   public HistoryAdapter(Context context, int textViewResourceId, List<History> objects) {
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
            row = li.inflate(R.layout.history_item, null);
         }

         History item = list.get(position);
         if (item != null) {

            TextView vDate = (TextView) row.findViewById(R.id.history_date);
            String sDate = sdf.format(item.getDate());
            vDate.setText(sDate);

            TextView vStatus = (TextView) row.findViewById(R.id.history_status);
            String sStatus = StatusEnum.valueOf(item.getStatus()).getDisplayName();
            vStatus.setText(sStatus);

            TextView vRating = (TextView) row.findViewById(R.id.history_rating);
            String sRating = String.valueOf(item.getRating()) + " %";
            vRating.setText(sRating);
         }
      } catch (Exception e) {
         log.e(e);
      }
      return row;
   }
}
