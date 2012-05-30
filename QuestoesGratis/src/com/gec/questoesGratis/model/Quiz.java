package com.gec.questoesGratis.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Quiz TO.
 * 
 * @author agodinho
 */
public final class Quiz implements Serializable {

   private static final long serialVersionUID = -8385924102847640126L;

   private Integer           quizId;
   private Date              date;
   private Integer           rating;
   private Status            status;
   private List< Answer >    answers;

   private Filter            filter;

   public Quiz() {
   }

   public Quiz( Integer quizId, Date date, Integer rating, Status status ) {
      this.quizId = quizId;
      this.date = date;
      this.rating = rating;
      this.status = status;
   }

   public Integer getQuizId() {
      return quizId;
   }

   public void setQuizId( Integer quizId ) {
      this.quizId = quizId;
   }

   public Date getDate() {
      return date;
   }

   public void setDate( Date date ) {
      this.date = date;
   }

   public Integer getRating() {
      return rating;
   }

   public void setRating( Integer rating ) {
      this.rating = rating;
   }

   public Status getStatus() {
      return status;
   }

   public void setStatus( Status status ) {
      this.status = status;
   }

   public Filter getFilter() {
      return filter;
   }

   public List< Answer > getAnswers() {
      return answers;
   }

   public void setAnswers( List< Answer > questions ) {
      this.answers = questions;
   }

   public void setFilter( Filter filter ) {
      this.filter = filter;
   }
}