
package com.gec.questoesGratis.model;

/**
 * Status Enum.
 * 
 * @author agodinho
 */
public enum Status {

   Unfinished( 0, "Não Concluído" ), //
   Finished( 1, "Concluído" ); //

   private final Integer statusId;
   private final String  displayName;

   private Status( Integer id, String name ) {
      statusId = id;
      displayName = name;
   }

   public int getStatusId() {
      return statusId;
   }

   public String getDisplayName() {
      return displayName;
   }

   public static Status valueOf( Integer id ) {
      final Status status;
      if( id == 0 ) {
         status = Unfinished;
      } else {
         status = Finished;
      }
      return status;
   }
}