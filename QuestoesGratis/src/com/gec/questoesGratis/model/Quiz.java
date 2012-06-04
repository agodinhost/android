package com.gec.questoesGratis.model;

import java.util.Date;
import java.util.List;

/**
 * Quiz TO.
 * 
 * @author agodinho
 */
public final class Quiz {

   public static enum Status {
      //
      UNFINISHED( 0, "Não Concluído" ), //
      FINISHED( 1, "Concluído" ); //

      public final int    id;
      public final String name;

      private Status( int idP, String nameP ) {
         id = idP;
         name = nameP;
      }

      public static Status valueOf( int id ) {
         if( id == 0 )
            return UNFINISHED;
         else
            return FINISHED;
      }
   }

   private Long           id;
   private Date           date;
   private String         filter;
   private Integer        rating;
   private Status         status;
   private Integer        lastNumber;
   private List< Answer > answers;

   public Quiz() {
   }

   public Quiz( String filterP ) {
      filter = filterP;
   }

   public Long getId() {
      return id;
   }

   public void setId( Long id ) {
      this.id = id;
   }

   public Date getDate() {
      return date;
   }

   public void setDate( Date date ) {
      this.date = date;
   }

   public String getFilter() {
      return filter;
   }

   public void setFilter( String filter ) {
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

   public void setAnswers( List< Answer > answers ) {
      this.answers = answers;
   }
}