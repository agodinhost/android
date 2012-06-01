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

   private Long                historyList_selectedId;
   private Integer             historyList_selectedPos;

   private Long                quizList_selectedId;
   private Integer             quizList_selectedPos;

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

   public void setDbHelper( DBHelper dbHelper ) {
      this.dbHelper = dbHelper;
   }

   public void setPager( ViewPager pager ) {
      this.pager = pager;
      pager.setCurrentItem( currentAnswer );
   }

   public Quiz getQuiz() {
      return quiz;
   }

   public void setQuiz( Quiz quiz ) {
      this.quiz = quiz;
      currentAnswer = 0;
      List< Answer > answers = quiz.getAnswers();
      answersCount = answers == null? -1: answers.size();
   }

   public void setAnswers( List< Answer > answers ) {
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

   // --- History List state.

   public Long getHistoryList_selectedId() {
      return historyList_selectedId;
   }

   public void setHistoryList_selectedId( long historyList_selectedId ) {
      this.historyList_selectedId = historyList_selectedId;
   }

   public Integer getHistoryList_selectedPos() {
      return historyList_selectedPos;
   }

   public void setHistoryList_selectedPos( int historyList_selectedPos ) {
      this.historyList_selectedPos = historyList_selectedPos;
   }

   // --- Quiz List state.

   public Long getQuizList_selectedId() {
      return quizList_selectedId;
   }

   public void setQuizList_selectedId( long quizList_selectedId ) {
      this.quizList_selectedId = quizList_selectedId;
   }

   public Integer getQuizList_selectedPos() {
      return quizList_selectedPos;
   }

   public void setQuizList_selectedPos( int quizList_selectedPos ) {
      this.quizList_selectedPos = quizList_selectedPos;
   }
}