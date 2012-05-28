package com.gec.questoesGratis.model;

import java.util.List;

public class Quiz {

   private int            quizId;
   private Filter         filter;
   private List<Question> questions;

   public int getQuizId() {
      return quizId;
   }

   public void setQuizId(int quizId) {
      this.quizId = quizId;
   }

   public Filter getFilter() {
      return filter;
   }

   public void setFilter(Filter filter) {
      this.filter = filter;
   }

   public List<Question> getQuestions() {
      return questions;
   }

   public void setQuestions(List<Question> questions) {
      this.questions = questions;
   }
}