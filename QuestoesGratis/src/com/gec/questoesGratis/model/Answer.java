package com.gec.questoesGratis.model;

/**
 * Question TO.
 * 
 * @author agodinho
 */
public final class Answer {

   private Long     id;
   private Integer  number;
   private String   answer;
   private Question question;

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

   public int getAnswerInt() {
      if( answer == null )
         return -1;
      else
         return answer.charAt( 0 ) - 'a';
   }

   public void setAnswer( String answer ) {
      this.answer = answer;
   }

   public void setAnswer( int index ) {
      answer = null;
      if( index > -1 )
         answer = String.valueOf( (char) ( 'a' + index ) );
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

   /**
    * Null  - means not answered yet/
    * true  - right;
    * false - wrong;
    * @return the answer status.
    */
   public Boolean getStatus() {
      try {
         return answer.equalsIgnoreCase( question.getMatch() );
      } catch( NullPointerException e ) {
         return null;
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