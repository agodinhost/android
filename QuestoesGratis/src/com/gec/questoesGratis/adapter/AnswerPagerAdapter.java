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

import com.gec.questoesGratis.ApplicationX;
import com.gec.questoesGratis.R;
import com.gec.questoesGratis.model.Answer;
import com.gec.questoesGratis.model.Question;
import com.gec.questoesGratis.tools.WebViewClientX;

/**
 * http://stackoverflow.com/questions/10574114/referencing-fragments-inside-viewpager
 * 
 * @author agodinho
 *
 */
public final class AnswerPagerAdapter extends FragmentStatePagerAdapter {

   private static final ApplicationX xApp   = ApplicationX.getInstance();
   private static final String       NUMBER = "iNumber";
   private static final String       ANSWER = "iAnswer";

   public AnswerPagerAdapter( FragmentManager fm ) {
      super( fm );
   }

   @Override
   public int getCount() {
      return xApp.getAnswersCount();
   }

   @Override
   public Fragment getItem( int index ) {
      return AnswerFragment.createInstance( xApp.getAnswer( index ) );
   }

   private static final class AnswerFragment extends Fragment implements OnClickListener {

      static final AnswerFragment createInstance( Answer answer ) {

         final Bundle bundle = new Bundle();
         bundle.putInt( NUMBER, answer.getNumber() );
         bundle.putInt( ANSWER, answer.getAnswerInt() );

         final AnswerFragment f = new AnswerFragment();
         f.setArguments( bundle );
         return f;
      }

      private int iNumber = 0;
      private int iAnswer = -1;

      @Override
      public void onCreate( Bundle savedInstanceState ) {

         super.onCreate( savedInstanceState );
         final Bundle bundle = getArguments();
         if( bundle != null ) {
            iNumber = bundle.getInt( NUMBER );
            iAnswer = bundle.getInt( ANSWER );
         }
      }

      @Override
      public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
         final View view = inflater.inflate( R.layout.pager_item, container, false );
         setup( view );
         return view;
      }

      @Override
      public void onSaveInstanceState( Bundle outState ) {
         super.onSaveInstanceState( outState );
         xApp.updateAnswer( iNumber, iAnswer );
      }

      @Override
      public void onResume() {
         super.onResume();
         xApp.updateAnswer( iNumber, iAnswer );
      }

      /**
       * TODO: need to see the weight of a fragment with a webview client inside ...
       */
      private void setup( View view ) {

         final Answer answer = xApp.getAnswer( iNumber );

         final TextView vQualifier = (TextView) view.findViewById( R.id.question_qualifier );
         vQualifier.setText( answer.getQualifierD() );

         final WebView vDescription = (WebView) view.findViewById( R.id.question_description );
         WebViewClientX.loadData( vDescription, answer.getQuestionD() );

         final RadioGroup radioGroup = (RadioGroup) view.findViewById( R.id.question_group );
         setup( radioGroup, answer.getQuestion() );
      }

      private void setup( RadioGroup radioGroup, Question question ) {

         final int l = radioGroup.getChildCount();
         for( int i = 0; i < l; i++ ) {
            final RadioButton r = (RadioButton) radioGroup.getChildAt( i );
            r.setId( i );
            r.setChecked( i == iAnswer );
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
         final Answer answer = xApp.getAnswer( iNumber );
         iAnswer = view.getId();
         answer.setAnswer( iAnswer );
      }
   }
}