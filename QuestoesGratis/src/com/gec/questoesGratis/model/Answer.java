package com.gec.questoesGratis.model;

import java.io.Serializable;

/**
 * Question TO.
 * 
 * @author agodinho
 */
public final class Answer implements Serializable {

   private static final long serialVersionUID = 5531609323631447934L;

   private int               id;
   private int               number;
   private String            answer;
   private Question          question;

   public int getId() {
      return id;
   }

   public void setId( int id ) {
      this.id = id;
   }

   public int getNumber() {
      return number;
   }

   public void setNumber( int number ) {
      this.number = number;
   }

   public String getAnswer() {
      return answer;
   }

   public void setAnswer( String answer ) {
      this.answer = answer;
   }

   public Question getQuestion() {
      return question;
   }

   public void setQuestion( Question question ) {
      this.question = question;
   }

   public Boolean getStatus() {
      Boolean status = null;
      String match = question.getMatch();
      if( match != null && answer != null ) {
         status = answer.equalsIgnoreCase( match );
      }
      return status;
   }

   public String getNumberD() {
      return "Quest�o " + number;
   }

   public String getQuestionD() {
      try {
         return question.getDescription();
      } catch( NullPointerException e ) {
         return null;
      }
   }

   public String getQualifierD() {
      try {
         return "Quest�o " + String.valueOf( number ) + ": " + question.getQualifierD();
      } catch( NullPointerException e ) {
         return null;
      }
   }
}