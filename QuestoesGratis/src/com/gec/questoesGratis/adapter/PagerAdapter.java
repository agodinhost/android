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

public final class PagerAdapter extends FragmentStatePagerAdapter {

   private static ApplicationX xApp;

   private static final LogX   log         = new LogX(PagerAdapter.class);
   private static final int    P_MAX_CHARS = 150;

   public PagerAdapter(FragmentManager fm, ApplicationX xInstance) {
      super(fm);
      xApp = xInstance;
      log.d("constructor");
   }

   @Override
   public int getCount() {
      log.d("getCount");
      return xApp.getAnswersCount();
   }

   @Override
   public Fragment getItem(int item) {
      log.d("getItem {0}", item);
      return QuestionFragment.newInstance(item);
   }

   private static final class QuestionFragment extends Fragment implements OnClickListener {

      private int questionNumber = 0;
      private int userOption     = -1;

      static final QuestionFragment newInstance(int questionNumber) {

         log.d("newInstance of questionNumber {0}", questionNumber);

         Bundle bundle = new Bundle();
         bundle.putInt("questionNumber", questionNumber);
         bundle.putInt("userOption", -1);

         QuestionFragment f = new QuestionFragment();
         f.setArguments(bundle);

         return f;
      }

      @Override
      public void onCreate(Bundle savedInstanceState) {
         log.d("onCreate questionNumber {0}", questionNumber);
         super.onCreate(savedInstanceState);
         Bundle bundle = getArguments();
         if (bundle != null) {
            questionNumber = bundle.getInt("questionNumber");
            userOption = bundle.getInt("userOption");
         }
      }

      @Override
      public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         log.d("onCreateView questionNumber {0}", questionNumber);
         View view = inflater.inflate(R.layout.pager_item, container, false);
         setup(view);
         return view;
      }

      /**
       * TODO: one webview per fragment IS kinda heavy!!! find a way to optimize
       * it (reload the html using the same webview instance?)
       */
      private void setup(View view) {

         Answer answer = xApp.getAnswer(questionNumber);

         TextView qd = (TextView) view.findViewById(R.id.question_description);
         qd.setText(answer.getQualifierD());

         String qp = answer.getQuestionD();
         boolean showDetails = qp.length() > P_MAX_CHARS || qp.contains("<image");
         if (showDetails) {
            qp = qp.substring(0, P_MAX_CHARS - 1) + " ...";
         }

         WebView webView = (WebView) view.findViewById(R.id.question_wv);
         EmbeddedWebView.loadData(webView, qp);

         // Simple approach to decide weather to display the "details" button.
         // It does not take into account any image or fancy sizing style.
         TextView details = (TextView) view.findViewById(R.id.question_details);
         details.setVisibility(showDetails ? TextView.VISIBLE : TextView.GONE);
         details.setOnClickListener(this);

         RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.question_group);
         setup(radioGroup, answer.getQuestion());
      }

      private void setup(RadioGroup radioGroup, Question question) {

         int l = radioGroup.getChildCount();
         for (int i = 0; i < l; i++) {
            RadioButton r = (RadioButton) radioGroup.getChildAt(i);
            r.setChecked(i == userOption);
            String o = question.getOption(i);
            if (o != null) {
               r.setText(o);
               r.setVisibility(RadioGroup.VISIBLE);
               r.setOnClickListener(this);
            } else {
               r.setVisibility(RadioGroup.GONE);
            }
         }
      }

      @Override
      public void onClick(View view) {

         Context context = view.getContext();
         int id = view.getId();
         if (id == R.id.question_details) {
            Intent intent = new Intent(context, DetailsActivity.class);
            startActivity(intent);
         } else {
            Toast.makeText(view.getContext(), "onClick " + id, Toast.LENGTH_SHORT).show();
         }
      }
   }
}