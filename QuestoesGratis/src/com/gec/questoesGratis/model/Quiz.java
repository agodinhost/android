
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

   private static final long serialVersionUID = 383288274571355914L;

   private Integer           id;
   private Date              date;
   private Filter            filter;
   private Integer           rating;
   private Status            status;
   private Integer           lastNumber;
   private List< Answer >    answers;

   public Quiz() {
   }

   public Quiz( Integer id, Date date, Integer rating, Status status ) {
      this.id = id;
      this.date = date;
      this.rating = rating;
      this.status = status;
   }

   public Integer getId() {
      return id;
   }

   public void setId( Integer id ) {
      this.id = id;
   }

   public Date getDate() {
      return date;
   }

   public void setDate( Date date ) {
      this.date = date;
   }

   public Filter getFilter() {
      return filter;
   }

   public void setFilter( Filter filter ) {
      this.filter = filter;
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

   public Integer getLastNumber() {
      return lastNumber;
   }

   public void setLastNumber( Integer lastNumber ) {
      this.lastNumber = lastNumber;
   }

   public List< Answer > getAnswers() {
      return answers;
   }

   public void setAnswers( List< Answer > questions ) {
      this.answers = questions;
   }
}