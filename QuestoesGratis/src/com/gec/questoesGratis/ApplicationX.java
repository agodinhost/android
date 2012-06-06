
package com.gec.questoesGratis;

import java.io.IOException;
import java.util.List;

import android.app.Application;
import android.content.Context;
import android.database.SQLException;
import android.support.v4.view.ViewPager;

import com.gec.questoesGratis.dao.DBHelper;
import com.gec.questoesGratis.model.Answer;
import com.gec.questoesGratis.model.Filter;
import com.gec.questoesGratis.model.Quiz;

/**
 * Singleton.
 * 
 * @author agodinho
 */
public final class ApplicationX extends Application {

   private static ApplicationX instance;
   private static Context      context;
   private static DBHelper     dbHelper;

   private Quiz                quiz;
   private Filter              filter;
   private int                 currentAnswer;
   private int                 answersCount = -1;

   private ViewPager           pager;

   public static ApplicationX getInstance() {
      return instance;
   }

   public static Context getContext() {
      return context;
   }

   @Override
   public void onCreate() {
      instance = this;
      context = instance.getApplicationContext();
      dbHelper = new DBHelper( context );

      try {
         dbHelper.createDataBase();
      } catch( IOException e ) {
         throw new Error( "Unable to create the application database ..." );
      }

      try {
         dbHelper.openDataBase();
      } catch( SQLException e ) {
         throw new Error( "Unable to open the application database ..." );
      }
   }

   @Override
   public void onTerminate() {
      try {
         dbHelper.close();
      } catch( Exception e ) {
         // just ignore.
      }
   }

   public List< String > getBancas() {
      return dbHelper.getBancas();
   }

   public List< String > getAnos() {
      return dbHelper.getAnos();
   }

   public List< String > getOrgaos() {
      return dbHelper.getOrgaos();
   }

   public List< String > getUFs() {
      return dbHelper.getUFs();
   }

   public List< String > getCargos() {
      return dbHelper.getCargos();
   }

   public List< String > getDisciplinas() {
      return dbHelper.getDisciplinas();
   }

   public List< String > getAssuntos() {
      return dbHelper.getAssuntos();
   }

   public void setFilter( Filter filterP ) {
      filter = filterP;
   }

   public int getQuestionsCount() {
      return dbHelper.getQuestionsCount( filter );
   }

   public void createQuiz() {
      setQuiz( dbHelper.createQuiz( filter ) );
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
      return answersCount > 0? //
      quiz.getAnswers().get( currentAnswer )
            : //
            null;
   }

   public Answer getAnswer( int index ) {
      return answersCount > 0? //
      quiz.getAnswers().get( index )
            : //
            null;
   }

   public void updateAnswer( int index, int answerI ) {
      if( answerI > 0 ) {
         final Answer answer = getAnswer( index );
         answer.setAnswer( answerI );
         dbHelper.updateAnswer( answer.getId(), answer.getAnswer() );
      }
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

   public void setPager( ViewPager pagerP ) {
      pager = pagerP;
      pager.setCurrentItem( currentAnswer );
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

   public boolean isLastAnswer() {
      return currentAnswer == answersCount;
   }
}