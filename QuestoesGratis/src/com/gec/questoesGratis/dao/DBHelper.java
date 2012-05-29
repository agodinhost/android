package com.gec.questoesGratis.dao;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * @see {http://www.vogella.com/articles/AndroidSQLite/article.html} 
 * @see {http://www.reigndesign.com/blog/using-your-own-sqlite-database-in-android-applications/}
 * 
 * @author agodinho
 */
public final class DBHelper extends SQLiteOpenHelper {

   private static final String DB_PATH    = "/data/data/com.gec.questoesGratis/databases/";
   private static final String DB_NAME    = "questoesGratis";
   private static final int    DB_VERSION = 1;

   private static final String COLUMN_ID  = "_id";

   private static final String DB_CREATE  = "CREATE TABLE questions( " + //
                                                COLUMN_ID + " integer primary key autoincrement, " + //
                                                "uf text, " + //
                                                "banca text, " + //
                                                "orgao text, " + //
                                                "cargo text, " + //
                                                "ano text, " + //
                                                "disciplina text, " + //
                                                "assunto text, " + //
                                                "question text not null, " + //
                                                "match text not null" + //
                                                ");";

   private static final String DB_DROP    = "DROP TABLE IF EXISTS questions;";

   private final Context       context;

   private SQLiteDatabase      database;

   /**
    * Constructor Takes and keeps a reference of the passed context in order to
    * access to the application assets and resources.
    * 
    * @param context
    */
   public DBHelper( Context context ) {

      super( context, DB_NAME, null, DB_VERSION );
      this.context = context;
   }

   @Override
   public void onCreate( SQLiteDatabase database ) {
      database.execSQL( DB_CREATE );
   }

   @Override
   public void onUpgrade( SQLiteDatabase database, int oldVersion, int newVersion ) {
      Log.w( DBHelper.class.getName(), //
            "Upgrading database from version " + oldVersion + //
                  " to " + newVersion + ", which will destroy all old data" );
      database.execSQL( DB_DROP );
      onCreate( database );
   }

   @Override
   public synchronized void close() {

      if( database != null ) database.close();
      super.close();
   }

   /**
    * Creates a empty database on the system and rewrites it with your own
    * database.
    */
   public void createDataBase() throws IOException {

      boolean dbExist = checkDataBase();

      if( dbExist ) {
         // do nothing - database already exist.
      } else {

         // By calling this method and empty database will be created into
         // the default system path of your application so we are gonna be
         // able to overwrite that database with our database.
         this.getReadableDatabase();

         try {
            copyDataBase();
         } catch( IOException e ) {
            throw new Error( "Error copying database" );
         }
      }
   }

   /**
    * Check if the database already exist to avoid re-copying the file each
    * time you open the application.
    * 
    * @return true if it exists, false if it doesn't
    */
   private boolean checkDataBase() {

      SQLiteDatabase checkDB = null;

      try {
         String myPath = DB_PATH + DB_NAME;
         checkDB = SQLiteDatabase.openDatabase( //
               myPath, null, SQLiteDatabase.OPEN_READONLY );

      } catch( SQLiteException e ) {
         // database does't exist yet.
      }

      if( checkDB != null ) {
         checkDB.close();
      }

      return checkDB != null;
   }

   /**
    * Copies your database from your local assets-folder to the just created
    * empty database in the system folder, from where it can be accessed and
    * handled. This is done by transfering bytestream.
    */
   private void copyDataBase() throws IOException {

      // Open your local db as the input stream.
      InputStream myInput = context.getAssets().open( DB_NAME );

      // Path to the just created empty db.
      String outFileName = DB_PATH + DB_NAME;

      // Open the empty db as the output stream.
      OutputStream myOutput = new FileOutputStream( outFileName );

      // transfer bytes from the inputfile to the outputfile.
      byte[] buffer = new byte[ 1024 ];
      int length;
      while( ( length = myInput.read( buffer ) ) > 0 ) {
         myOutput.write( buffer, 0, length );
      }

      // Close the streams.
      myOutput.flush();
      myOutput.close();
      myInput.close();
   }

   public void openDataBase() throws SQLException {

      // Open the database.
      String myPath = DB_PATH + DB_NAME;
      database = SQLiteDatabase.openDatabase( myPath, null, SQLiteDatabase.OPEN_READONLY );
   }

   public List< String > getUFs() {
      return null;
   }

   public List< String > getBancas() {
      return null;
   }

   public List< String > getOrgaos() {
      return null;
   }

   public List< String > getCargos() {
      return null;
   }

   public List< String > getAnos() {
      return null;
   }

   public List< String > getDisciplinas() {
      return null;
   }

   public List< String > getAssuntos() {
      return null;
   }

   // Add your public helper methods to access and get content from the
   // database.
   // You could return cursors by doing "return myDataBase.query(....)" so it'd
   // be easy
   // to you to create adapters for your views.

}