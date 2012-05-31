
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

import com.gec.questoesGratis.tools.LogX;

/**
 * @see {http://www.vogella.com/articles/AndroidSQLite/article.html} 
 * @see {http://www.reigndesign.com/blog/using-your-own-sqlite-database-in-android-applications/}
 * @see {http://www.higherpass.com/Android/Tutorials/Accessing-Data-With-Android-Cursors/}
 * 
 * @author agodinho
 */
public final class DBHelper extends SQLiteOpenHelper {

   private static final LogX log = new LogX( DBHelper.class );

   private final Context     context;

   private SQLiteDatabase    database;

   /**
    * Constructor Takes and keeps a reference of the passed context in order to
    * access to the application assets and resources.
    * 
    * @param context
    */
   public DBHelper( Context context ) {

      super( context, DBProperties.DB_NAME, null, DBProperties.DB_VERSION );
      this.context = context;
   }

   @Override
   public void onCreate( SQLiteDatabase database ) {
      // database.execSQL( DBProperties.DDL_CREATE );
   }

   @Override
   public void onUpgrade( SQLiteDatabase database, int oldVersion, int newVersion ) {
      // log.w( "Upgrading database from version {0} to {1}, which will destroy all old data ...", oldVersion, newVersion );
      // database.execSQL( DBProperties.DDL_DROP );
      // onCreate( database );
   }

   @Override
   public synchronized void close() {

      if( database != null ) {
         database.close();
      }
      super.close();
      log.d( "Database connection closed." );
   }

   /**
    * Creates a empty database on the system and rewrites it with your own
    * database.
    */
   public void createDataBase() throws IOException {

      if( !databaseExists() ) {

         // By calling this method and empty database will be created into
         // the default system path of your application so we are gonna be
         // able to overwrite that database with our database.
         this.getReadableDatabase();

         try {
            copyDataBase();
         } catch( IOException e ) {
            throw new Error( "Error copying database" );
         }

         log.d( "Database created." );
      }
   }

   /**
    * Check if the database already exist to avoid re-copying the file each
    * time you open the application.
    * 
    * @return true if it exists, false if it doesn't
    */
   private boolean databaseExists() {

      boolean exists = true;
      SQLiteDatabase db = null;

      try {
         db = SQLiteDatabase.openDatabase( //
               DBProperties.DB_FILENAME, //
               null, //
               SQLiteDatabase.OPEN_READONLY );
      } catch( SQLiteException e ) {
         exists = false;
      }

      if( exists ) {
         log.d( "Database ALREADY exists." );

         try {
            db.close();
         } catch( SQLiteException e ) {
            // just ignore.
         }

      } else {
         log.d( "Database DOES NOT exists." );
      }

      return exists;
   }

   /**
    * Copies your database from your local assets-folder to the just created
    * empty database in the system folder, from where it can be accessed and
    * handled. This is done by transfering bytestream.
    */
   private void copyDataBase() throws IOException {

      // Open your local db as the input stream.
      final InputStream is = context.getAssets().open( DBProperties.DB_NAME );

      // Open the empty db as the output stream.
      final OutputStream os = new FileOutputStream( DBProperties.DB_FILENAME );

      // Transfer bytes from the input into the output file.
      byte[] buffer = new byte[ 1024 ];
      int length;
      while( ( length = is.read( buffer ) ) > 0 ) {
         os.write( buffer, 0, length );
      }

      // Close the streams.
      os.flush();
      os.close();
      is.close();
   }

   public void openDataBase() throws SQLException {

      database = SQLiteDatabase.openDatabase( //
            DBProperties.DB_FILENAME, //
            null, //
            SQLiteDatabase.OPEN_READWRITE );
   }

   public List< String > getBancas() {
      return null;
   }

   public List< String > getAnos() {
      return null;
   }

   public List< String > getOrgaos() {
      return null;
   }

   public List< String > getUFs() {
      return null;
   }

   public List< String > getCargos() {
      return null;
   }

   public List< String > getDisciplinas() {
      return null;
   }

   public List< String > getAssuntos() {
      return null;
   }
}