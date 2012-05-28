package com.gec.questoesGratis.model;

public enum StatusEnum {

   Unfinished(0, "Não Concluído"), //
   Finished(1, "Concluído"); //

   private final int    statusId;
   private final String displayName;

   private StatusEnum(int id, String name) {
      this.statusId = id;
      this.displayName = name;
   }

   public int getStatusId() {
      return statusId;
   }

   public String getDisplayName() {
      return displayName;
   }

   public static StatusEnum valueOf(int statusId) {
      final StatusEnum status;
      if (statusId == 0) {
         status = Unfinished;
      } else {
         status = Finished;
      }
      return status;
   }
}
