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

public final class AnswersAdapter extends ArrayAdapter< Answer > {

   private static final LogX log = new LogX( AnswersAdapter.class );

   private List< Answer >    list;
   private Context           context;

   public AnswersAdapter( Context contextP, int textViewResourceId, List< Answer > listP ) {
      super( contextP, textViewResourceId, listP );
      context = contextP;
      list = listP;
   }

   @Override
   public View getView( int position, View convertView, ViewGroup parent ) {
      final View row;
      if( convertView == null ) {
         final LayoutInflater li = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
         row = li.inflate( R.layout.answers_item, null );
      } else {
         row = convertView;
      }

      try {
         final Answer answer = list.get( position );
         if( answer != null ) {
            final TextView vNumber = (TextView) row.findViewById( R.id.answers_item_number );
            final String sNumber = answer.getNumberD();
            vNumber.setText( sNumber );

            final TextView vId = (TextView) row.findViewById( R.id.answers_item_id );
            final String sId = String.valueOf( answer.getId() );
            vId.setText( sId );

            final TextView vDescription = (TextView) row.findViewById( R.id.answers_item_description );
            final String sDescription = answer.getQualifierD();
            vDescription.setText( sDescription );

            final ImageView vStatus = (ImageView) row.findViewById( R.id.answers_item_status );
            final Boolean bStatus = answer.getStatus();
            if( bStatus == null ) {
               vStatus.setImageResource( R.drawable.empty );
            } else if( bStatus ) {
               vStatus.setImageResource( R.drawable.right );
            } else {
               vStatus.setImageResource( R.drawable.wrong );
            }
         }
      } catch( Exception e ) {
         log.e( e );
      }
      return row;
   }
}