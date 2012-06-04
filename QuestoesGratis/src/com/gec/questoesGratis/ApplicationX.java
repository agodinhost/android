
package com.gec.questoesGratis;

import java.util.List;

import android.app.Application;
import android.support.v4.view.ViewPager;

import com.gec.questoesGratis.dao.DBHelper;
import com.gec.questoesGratis.model.Answer;
import com.gec.questoesGratis.model.Quiz;

public class ApplicationX extends Application {

   private static ApplicationX lastInstance;

   private Quiz                quiz;
   private int                 currentAnswer;
   private int                 answersCount = -1;

   private DBHelper            dbHelper;
   private ViewPager           pager;

   public ApplicationX() {
      super();
      lastInstance = this;
   }

   public static ApplicationX getInstance() {
      return lastInstance;
   }

   public DBHelper getDbHelper() {
      return dbHelper;
   }

   public void setDbHelper( DBHelper dbHelperP ) {
      dbHelper = dbHelperP;
   }

   public void setPager( ViewPager pagerP ) {
      pager = pagerP;
      pager.setCurrentItem( currentAnswer );
   }

   public List< Quiz > getQuizzes() {
      return dbHelper.getQuizzes();
   }

   public Quiz getQuiz() {
      return quiz;
   }

   public void setQuiz( Quiz quizP ) {
      quiz = quizP;
      currentAnswer = 0;
      final List< Answer > answers = quiz.getAnswers();
      answersCount = answers == null? -1: answers.size();
   }

   public List< Answer > getAnswers() {
      setAnswers( dbHelper.getAnswers( quiz.getId() ) );
      return quiz.getAnswers();
   }

   private void setAnswers( List< Answer > answers ) {
      quiz.setAnswers( answers );
      currentAnswer = 0;
      answersCount = answers == null? -1: answers.size();
   }

   public Answer getAnswer() {
      Answer a = null;
      if( answersCount > 0 )
         a = quiz.getAnswers().get( currentAnswer );
      return a;
   }

   public Answer getAnswer( int index ) {
      Answer a = null;
      if( answersCount > 0 )
         a = quiz.getAnswers().get( index );
      return a;
   }

   public int getAnswersCount() {
      return answersCount;
   }

   public int getCurrentAnswer() {
      return currentAnswer;
   }

   public void setCurrentAnswer( int index ) {
      currentAnswer = index;
      if( currentAnswer < 0 )
         currentAnswer = 0;
      if( currentAnswer > answersCount )
         currentAnswer = answersCount;
   }

   public void moveFirst() {
      currentAnswer = 0;
      pager.setCurrentItem( currentAnswer );
   }

   public void movePrevious() {
      currentAnswer--;
      if( currentAnswer < 0 )
         currentAnswer = 0;
      pager.setCurrentItem( currentAnswer );
   }

   public void moveNext() {
      currentAnswer++;
      if( currentAnswer > answersCount )
         currentAnswer = answersCount;
      pager.setCurrentItem( currentAnswer );
   }

   public void moveLast() {
      currentAnswer = answersCount;
      pager.setCurrentItem( currentAnswer );
   }
}