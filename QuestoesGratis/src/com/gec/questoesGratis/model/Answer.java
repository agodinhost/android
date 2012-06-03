package com.gec.questoesGratis.model;

import java.io.Serializable;

/**
 * Question TO.
 * 
 * @author agodinho
 */
public final class Answer implements Serializable {

   private static final long serialVersionUID = -6396025902437961658L;

   private Long              id;
   private Integer           number;
   private String            answer;
   private Question          question;

   public Long getId() {
      return id;
   }

   public void setId( Long id ) {
      this.id = id;
   }

   public Integer getNumber() {
      return number;
   }

   public void setNumber( Integer number ) {
      this.number = number;
   }

   public String getAnswer() {
      return answer;
   }

   public void setAnswer( String answer ) {
      this.answer = answer;
   }

   public Long getQuestionId() {
      return question.getId();
   }

   public Question getQuestion() {
      return question;
   }

   public void setQuestion( Question question ) {
      this.question = question;
   }

   public Boolean getStatus() {
      try {
         return answer.equalsIgnoreCase( question.getMatch() );
      } catch( NullPointerException e ) {
         return false;
      }
   }

   public String getNumberD() {
      return "Questão " + number;
   }

   public String getQuestionD() {
      try {
         return question.getDescription();
      } catch( NullPointerException e ) {
         return "";
      }
   }

   public String getQualifierD() {
      try {
         return "Questão " + String.valueOf( number ) + ": " + question.getQualifierD();
      } catch( NullPointerException e ) {
         return "";
      }
   }
}