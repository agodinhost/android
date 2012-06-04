package com.gec.questoesGratis.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.gec.questoesGratis.ApplicationX;
import com.gec.questoesGratis.DetailsActivity;
import com.gec.questoesGratis.R;
import com.gec.questoesGratis.model.Answer;
import com.gec.questoesGratis.model.Question;
import com.gec.questoesGratis.tools.EmbeddedWebView;
import com.gec.questoesGratis.tools.LogX;

public final class AnswerPagerAdapter extends FragmentStatePagerAdapter {

   private static final LogX         log         = new LogX( AnswerPagerAdapter.class );
   private static final ApplicationX xApp        = ApplicationX.getInstance();
   private static final int          P_MAX_CHARS = 150;

   public AnswerPagerAdapter( FragmentManager fm ) {
      super( fm );
      log.d( "constructor" );
   }

   @Override
   public int getCount() {
      log.d( "getCount" );
      return xApp.getAnswersCount();
   }

   @Override
   public Fragment getItem( int item ) {
      log.d( "getItem {0}", item );
      return AnswerFragment.newInstance( item );
   }

   private static final class AnswerFragment extends Fragment implements OnClickListener {

      private int questionNumber = 0;
      private int userOption     = -1;

      static final AnswerFragment newInstance( int questionNumber ) {

         log.d( "newInstance of questionNumber {0}", questionNumber );

         final Bundle bundle = new Bundle();
         bundle.putInt( "questionNumber", questionNumber );
         bundle.putInt( "userOption", -1 );

         final AnswerFragment f = new AnswerFragment();
         f.setArguments( bundle );

         return f;
      }

      @Override
      public void onCreate( Bundle savedInstanceState ) {
         log.d( "onCreate questionNumber {0}", questionNumber );
         super.onCreate( savedInstanceState );
         final Bundle bundle = getArguments();
         if( bundle != null ) {
            questionNumber = bundle.getInt( "questionNumber" );
            userOption = bundle.getInt( "userOption" );
         }
      }

      @Override
      public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
         log.d( "onCreateView questionNumber {0}", questionNumber );
         final View view = inflater.inflate( R.layout.pager_item, container, false );
         setup( view );
         return view;
      }

      /**
       * TODO: one webview per fragment IS kinda heavy!!! find a way to optimize
       * it (reload the html using the same webview instance?)
       */
      private void setup( View view ) {

         final Answer answer = xApp.getAnswer( questionNumber );

         final TextView qd = (TextView) view.findViewById( R.id.question_description );
         qd.setText( answer.getQualifierD() );

         String qp = answer.getQuestionD();
         final boolean showDetails = qp != null && ( qp.length() > P_MAX_CHARS || qp.contains( "<image" ) );
         if( showDetails ) {
            qp = qp.substring( 0, P_MAX_CHARS - 1 ) + " ...";
         }

         final WebView webView = (WebView) view.findViewById( R.id.question_wv );
         EmbeddedWebView.loadData( webView, qp );

         // Simple approach to decide weather to display the "details" button.
         // It does not take into account any image or fancy sizing style.
         final TextView details = (TextView) view.findViewById( R.id.question_details );
         details.setVisibility( showDetails? TextView.VISIBLE: TextView.GONE );
         details.setOnClickListener( this );

         final RadioGroup radioGroup = (RadioGroup) view.findViewById( R.id.question_group );
         setup( radioGroup, answer.getQuestion() );
      }

      private void setup( RadioGroup radioGroup, Question question ) {
         final int l = radioGroup.getChildCount();
         for( int i = 0; i < l; i++ ) {
            final RadioButton r = (RadioButton) radioGroup.getChildAt( i );
            r.setChecked( i == userOption );
            final String o = question.getOption( i );
            if( o != null ) {
               r.setText( o );
               r.setVisibility( RadioGroup.VISIBLE );
               r.setOnClickListener( this );
            } else {
               r.setVisibility( RadioGroup.GONE );
            }
         }
      }

      @Override
      public void onClick( View view ) {
         final Context context = view.getContext();
         final int id = view.getId();
         if( id == R.id.question_details ) {
            startActivity( new Intent( context, DetailsActivity.class ) );
         } else {
            Toast.makeText( view.getContext(), "onClick " + id, Toast.LENGTH_SHORT ).show();
         }
      }
   }
}