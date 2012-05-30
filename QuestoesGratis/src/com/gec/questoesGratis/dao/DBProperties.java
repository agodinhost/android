package com.gec.questoesGratis.dao;

import com.gec.questoesGratis.ApplicationX;
import com.gec.questoesGratis.tools.PropertiesX;

public final class DBProperties {

   public static final String       DB_PATH     = "/data/data/com.gec.questoesGratis/databases/";

   private static final PropertiesX p           = new PropertiesX(ApplicationX.getInstance(), DB_PATH);

   public static final String       DB_NAME     = p.getString("DB_NAME");
   public static final String       DB_FILENAME = DB_PATH + DB_NAME;

   public static final Integer      DB_VERSION  = p.getInteger("DB_VERSION");

   public static final String       DDL_CREATE  = p.getString("DDL_CREATE");
   public static final String       DDL_DROP    = p.getString("DDL_DROP");

}