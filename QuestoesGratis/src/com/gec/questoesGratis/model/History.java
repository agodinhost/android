package com.gec.questoesGratis.model;

import java.io.Serializable;
import java.util.Date;

/**
 * History TO.
 * 
 * @author agodinho
 */
public final class History implements Serializable {

   private static final long serialVersionUID = -7484043909854950020L;

   private int               quizId;
   private Date              date;
   private int               rating;
   private int               status;

   public History( int quizId, Date date, int rating, int status ) {
      this.quizId = quizId;
      this.date = date;
      this.rating = rating;
      this.status = status;
   }

   public int getQuizId() {
      return quizId;
   }

   public void setQuizId( int quizId ) {
      this.quizId = quizId;
   }

   public Date getDate() {
      return date;
   }

   public void setDate( Date date ) {
      this.date = date;
   }

   public int getRating() {
      return rating;
   }

   public void setRating( int rating ) {
      this.rating = rating;
   }

   public int getStatus() {
      return status;
   }

   public void setStatus( int status ) {
      this.status = status;
   }
}