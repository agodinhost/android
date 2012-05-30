
package com.gec.questoesGratis.model;

import java.io.Serializable;
import java.util.List;

/**
 * Question TO.
 * 
 * @author agodinho
 */
public final class Question implements Serializable {

   private static final long serialVersionUID = 1823845473939566367L;

   private Integer           id;
   private Qualifier         q;
   private String            description;
   private List< String >    options;
   private String            match;

   public Integer getId() {
      return id;
   }

   public void setId( Integer id ) {
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

   public String getQualifierD() {
      try {
         return q.getDescription();
      } catch( NullPointerException e ) {
         return null;
      }
   }
}