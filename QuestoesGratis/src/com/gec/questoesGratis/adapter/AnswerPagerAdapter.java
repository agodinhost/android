
package com.gec.questoesGratis.adapter;

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
import com.gec.questoesGratis.R;
import com.gec.questoesGratis.model.Answer;
import com.gec.questoesGratis.model.Question;
import com.gec.questoesGratis.tools.LogX;
import com.gec.questoesGratis.tools.WebViewClientX;

public final class AnswerPagerAdapter extends FragmentStatePagerAdapter {

   private static final LogX         log         = new LogX( AnswerPagerAdapter.class );
   private static final ApplicationX xApp        = ApplicationX.getInstance();
   private static final String       LAST_NUMBER = "lastNumber";
   private static final String       USER_ANSWER = "userAnswer";

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

      private int lastNumber = 0;
      private int userAnswer = -1;

      static final AnswerFragment newInstance( int lastNumberP ) {

         log.d( "newInstance of lastNumber {0}", lastNumberP );

         final Bundle bundle = new Bundle();
         bundle.putInt( LAST_NUMBER, lastNumberP );
         bundle.putInt( USER_ANSWER, -1 );

         final AnswerFragment f = new AnswerFragment();
         f.setArguments( bundle );

         return f;
      }

      @Override
      public void onCreate( Bundle savedInstanceState ) {
         log.d( "onCreate lastNumber {0}", lastNumber );
         super.onCreate( savedInstanceState );
         final Bundle bundle = getArguments();
         if( bundle != null ) {
            lastNumber = bundle.getInt( LAST_NUMBER );
            userAnswer = bundle.getInt( USER_ANSWER );
         }
      }

      @Override
      public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
         log.d( "onCreateView lastNumber {0}", lastNumber );
         final View view = inflater.inflate( R.layout.pager_item, container, false );
         setup( view );
         return view;
      }

      /**
       * TODO: one webview per fragment IS kinda heavy!!! find a way to optimize
       * it (reload the html using the same webview instance?)
       */
      private void setup( View view ) {

         final Answer answer = xApp.getAnswer( lastNumber );

         final TextView vQualifier = (TextView) view.findViewById( R.id.question_qualifier );
         vQualifier.setText( answer.getQualifierD() );

         final WebView vDescription = (WebView) view.findViewById( R.id.question_description );
         final String sDescription = answer.getQuestionD();
         WebViewClientX.loadData( vDescription, sDescription );

         final RadioGroup radioGroup = (RadioGroup) view.findViewById( R.id.question_group );
         setup( radioGroup, answer.getQuestion() );
      }

      private void setup( RadioGroup radioGroup, Question question ) {
         final int l = radioGroup.getChildCount();
         for( int i = 0; i < l; i++ ) {
            final RadioButton r = (RadioButton) radioGroup.getChildAt( i );
            r.setChecked( i == userAnswer );
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
         //final Context context = view.getContext();
         final int id = view.getId();
         Toast.makeText( view.getContext(), "onClick " + id, Toast.LENGTH_SHORT ).show();
      }
   }
}