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
import com.gec.questoesGratis.model.Quiz;
import com.gec.questoesGratis.tools.LogX;

public final class QuizzesAdapter extends ArrayAdapter< Quiz > {

   private static final LogX             log = new LogX( QuizzesAdapter.class );
   private static final SimpleDateFormat sdf = new SimpleDateFormat( "dd/mm/yyyy - HH:mm" );

   private List< Quiz >                  list;
   private Context                       context;

   public QuizzesAdapter( Context contextP, int textViewResourceId, List< Quiz > listP ) {
      super( contextP, textViewResourceId, listP );
      context = contextP;
      list = listP;
   }

   @Override
   public View getView( int position, View convertView, ViewGroup parent ) {
      final View row;
      if( convertView == null ) {
         final LayoutInflater li = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
         row = li.inflate( R.layout.quizzes_item, null );
      } else {
         row = convertView;
      }

      try {
         final Quiz quiz = list.get( position );
         if( quiz != null ) {
            final TextView vDate = (TextView) row.findViewById( R.id.quizzes_item_date );
            final String sDate = sdf.format( quiz.getDate() );
            vDate.setText( sDate );

            final TextView vStatus = (TextView) row.findViewById( R.id.quizzes_item_status );
            final String sStatus = quiz.getStatus().name;
            vStatus.setText( sStatus );

            final TextView vRating = (TextView) row.findViewById( R.id.quizzes_item_rating );
            final String sRating = String.valueOf( quiz.getRating() ) + " %";
            vRating.setText( sRating );
         }
      } catch( Exception e ) {
         log.e( e );
      }
      return row;
   }

   public Quiz getQuiz( int index ) {
      return list.get( index );
   }
}