package com.gec.questoesGratis.model;

import java.io.Serializable;
import java.util.List;

/**
 * Question TO.
 * 
 * @author agodinho
 */
public final class Question implements Serializable {

   private static final long serialVersionUID = 4426101449359618224L;

   private Long              id;
   private Qualifier         q;
   private String            description;
   private List< String >    options;
   private String            match;
   private String            used;

   public Long getId() {
      return id;
   }

   public void setId( Long id ) {
      this.id = id;
   }

   public Qualifier getQualifier() {
      return q;
   }

   public void setQualifier( Qualifier q ) {
      this.q = q;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription( String description ) {
      this.description = description;
   }

   public List< String > getOptions() {
      return options;
   }

   public void setOptions( List< String > options ) {
      this.options = options;
   }

   public String getOption( Integer index ) {
      String option = null;
      try {
         option = options.get( index );
      } catch( NullPointerException e ) {
         // just ignore.
      } catch( IndexOutOfBoundsException e ) {
         // just ignore.
      }
      return option;
   }

   public String getMatch() {
      return match;
   }

   public void setMatch( String match ) {
      this.match = match;
   }

   /**
    * Get the used string according to:
    * u - used at least once;
    * r - right at least once;
    * w - wrong at least once;
    * 
    * @return the used string.
    * @see #setUsed(String)
    */
   public String getUsed() {
      return used;
   }

   /**
    * Set the used string.
    * 
    * @param used the used string.
    * @see #getUsed()
    */
   public void setUsed( String used ) {
      this.used = used;
   }

   public String getQualifierD() {
      try {
         return q.getDescription();
      } catch( NullPointerException e ) {
         return null;
      }
   }
}