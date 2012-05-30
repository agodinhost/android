package com.gec.questoesGratis.dao;

import com.gec.questoesGratis.ApplicationX;
import com.gec.questoesGratis.tools.PropertiesX;

public final class DBProperties {

   private static final String      DB_PATH                = "/data/data/com.gec.questoesGratis/databases/";
   private static final PropertiesX p                      = new PropertiesX( ApplicationX.getInstance(), DB_PATH );

   public static final String       DB_NAME                = p.getString( "DB_NAME" );
   public static final String       DB_FILENAME            = DB_PATH + DB_NAME;

   public static final Integer      DB_VERSION             = p.getInteger( "DB_VERSION" );

   public static final String       DDL_CREATE             = p.getString( "DDL_CREATE" );
   public static final String       DDL_DROP               = p.getString( "DDL_DROP" );

   public static final String       SQL_INSERT_QUIZ        = p.getString( "SQL_INSERT_QUIZ" );

   public static final String       SQL_SELECT_UFs         = p.getString( "SQL_SELECT_UFs" );
   public static final String       SQL_SELECT_Bancas      = p.getString( "SQL_SELECT_Bancas" );
   public static final String       SQL_SELECT_Orgaos      = p.getString( "SQL_SELECT_Orgaos" );
   public static final String       SQL_SELECT_Cargos      = p.getString( "SQL_SELECT_Cargos" );
   public static final String       SQL_SELECT_Anos        = p.getString( "SQL_SELECT_Anos" );
   public static final String       SQL_SELECT_Disciplinas = p.getString( "SQL_SELECT_Disciplinas" );
   public static final String       SQL_SELECT_Assuntos    = p.getString( "SQL_SELECT_Assuntos" );

}