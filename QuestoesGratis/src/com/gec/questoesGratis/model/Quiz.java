package com.gec.questoesGratis.model;

import java.io.Serializable;
import java.util.List;

/**
 * Quiz TO.
 * 
 * @author agodinho
 */
public final class Quiz implements Serializable {

   private static final long serialVersionUID = -4206546859010743058L;

   private int               quizId;
   private Filter            filter;
   private List< Answer >    answers;

   public int getQuizId() {
      return quizId;
   }

   public void setQuizId( int quizId ) {
      this.quizId = quizId;
   }

   public Filter getFilter() {
      return filter;
   }

   public void setFilter( Filter filter ) {
      this.filter = filter;
   }

   public List< Answer > getAnswers() {
      return answers;
   }

   public void setAnswers( List< Answer > questions ) {
      this.answers = questions;
   }
}