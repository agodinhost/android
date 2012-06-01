
package com.gec.questoesGratis.dao;

import static com.gec.questoesGratis.tools.ListX.randomStart;
import static com.gec.questoesGratis.tools.ListX.shuffle;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.gec.questoesGratis.model.Answer;
import com.gec.questoesGratis.model.Filter;
import com.gec.questoesGratis.model.Filter.IgnoreQuestions;
import com.gec.questoesGratis.model.Qualifier;
import com.gec.questoesGratis.model.Question;
import com.gec.questoesGratis.model.Quiz;
import com.gec.questoesGratis.tools.LogX;

/**
 * @see {http://www.vogella.com/articles/AndroidSQLite/article.html} 
 * @see {http://www.reigndesign.com/blog/using-your-own-sqlite-database-in-android-applications/}
 * @see {http://www.higherpass.com/Android/Tutorials/Accessing-Data-With-Android-Cursors/}
 * @see {http://stackoverflow.com/questions/513084/how-to-ship-an-android-application-with-a-database}
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

   private List< String > getNames( String sql, String columnName ) {

      final List< String > list = new ArrayList< String >();

      final Cursor cursor = database.rawQuery( sql, null );
      if( cursor != null ) {

         final int nameIdx = cursor.getColumnIndex( columnName );
         while( cursor.moveToNext() ) {
            final String name = cursor.getString( nameIdx );
            list.add( name );
         }
         cursor.close();
      }

      return list;
   }

   private List< String > getNames( String sql ) {
      return getNames( sql, "name" );
   }

   public List< String > getBancas() {
      return getNames( DBProperties.SQL_SELECT_Bancas );
   }

   public List< String > getAnos() {
      return getNames( DBProperties.SQL_SELECT_Anos );
   }

   public List< String > getOrgaos() {
      return getNames( DBProperties.SQL_SELECT_Orgaos );
   }

   public List< String > getUFs() {
      return getNames( DBProperties.SQL_SELECT_UFs );
   }

   public List< String > getCargos() {
      return getNames( DBProperties.SQL_SELECT_Cargos );
   }

   public List< String > getDisciplinas() {
      return getNames( DBProperties.SQL_SELECT_Disciplinas );
   }

   public List< String > getAssuntos() {
      return getNames( DBProperties.SQL_SELECT_Assuntos );
   }

   /* ALWAYS returns a valid String. */
   private String getPlick( List< String > list ) {

      StringBuffer b = new StringBuffer();

      if( list != null )
         for( String item : list )
            b.append( "'" ) //
                  .append( item.trim() ) //
                  .append( "'" ) //
                  .append( "," );

      final int l = b.length();
      if( l > 0 )
         b.deleteCharAt( l - 1 );

      return b.toString();
   }

   /* ALWAYS returns a valid String. */
   private String getIn( String field, List< String > list ) {

      final StringBuffer b = new StringBuffer();
      final String in = getPlick( list );
      if( in.length() > 0 ) //
         b.append( field ) //
               .append( " IN( " ) //
               .append( in ) //
               .append( " ) " );
      return b.toString();
   }

   private void addInClause( StringBuffer b, String field, List< String > list ) {

      final String in = getIn( field, list );
      if( in.length() > 0 ) {
         if( b.length() > 0 )
            b.append( "AND " );
         b.append( in );
      }
   }

   /* ALWAYS returns a valid String. */
   private String getWhere( Filter filter ) {

      final StringBuffer b = new StringBuffer();

      //TODO: filter precisa tb da qtd de questões (a tabela questions não precisa disso);

      //TODO: preciso de dois campos na questions pra controlar isso ...
      if( IgnoreQuestions.AlreadyAnswered.equals( filter.getIgnore() ) ) {
      } else if( IgnoreQuestions.AlreadyAnswered_Right.equals( filter.getIgnore() ) ) {
      } else if( IgnoreQuestions.AlreadyAnswered_Wrong.equals( filter.getIgnore() ) ) {
      }

      addInClause( b, "trim(banca)", filter.getBancas() );
      addInClause( b, "trim(ano)", filter.getAnos() );
      addInClause( b, "trim(orgao)", filter.getOrgaos() );
      addInClause( b, "trim(uf)", filter.getUFs() );
      addInClause( b, "trim(cargo)", filter.getCargos() );
      addInClause( b, "trim(disciplina)", filter.getDisciplinas() );
      addInClause( b, "trim(assunto)", filter.getAssuntos() );

      return b.toString();
   }

   /* ALWAYS returns a valid String. */
   private String getRawWhere( Filter filter ) {

      String raw = "";

      final String where = getWhere( filter );
      if( where.length() > 0 ) {
         raw = "WHERE " + where;
      }
      return raw;
   }

   public int getQuestionsCount( Filter filter ) {

      final String where = getRawWhere( filter );
      final Cursor c = database.rawQuery( //
            "SELECT Count(1) FROM questions " + where, //
            null );
      c.moveToFirst();
      int count = c.getInt( 0 );
      c.close();
      return count;
   }

   /*
    * 1) Get complete list id;
    * 2) List id shuffle;
    * 3) Create new list id limited to the desired number of questions;
    * 4) BeginTransaction;
    * 5) create quiz;
    * 6) for each id in the list id:
    * 7)    create and add one answer/question in the quiz above;
    * 7) Commit.
    * ?) Roolback?
    * 
    * used TEXT "urw":
    * u - used at least once;
    * r - right at least once;
    * w - wrong at least once;
    */

   public Quiz createQuiz( Filter filter ) {

      final Quiz quiz = new Quiz();
      quiz.setFilter( filter );

      final List< Answer > answers = createAnswers( quiz );
      quiz.setAnswers( answers );

      return quiz;
   }

   private List< Answer > createAnswers( Quiz quiz ) {

      final Filter filter = quiz.getFilter();
      final List< Integer > ids = getQuestionsId( filter );
      shuffle( ids );
      final int start = randomStart( ids, filter.getQuestions() );
      //TODO: updateUsedIds(ids,10);

      return null;
   }

   private List< Integer > getQuestionsId( Filter filter ) {

      final List< Integer > list = new ArrayList< Integer >();

      final String table = "questions";
      final String columns[] = { "_id" };
      final String where = getWhere( filter );
      final String whereArgs[] = null;
      final String groupBy = null;
      final String having = null;
      final String orderBy = null;

      final Cursor c = database.query( //
            table, //
            columns, //
            where, //
            whereArgs, //
            groupBy, //
            having, //
            orderBy );

      while( c.moveToNext() )
         list.add( new Integer( c.getInt( 0 ) ) );
      c.close();

      return list;
   }

   //TODO: 
   public List< Quiz > getQuizzes() {
      return null;
   }

   //TODO: 
   public List< Question > getQuiz( Integer quizId ) {

      final List< Question > list = new ArrayList< Question >();

      final String table = "questions";
      final String columns[] = null;
      final String where = null;
      final String whereArgs[] = null;
      final String groupBy = null;
      final String having = null;
      final String orderBy = null;

      final Cursor c = database.query( //
            table, //
            columns, //
            where, //
            whereArgs, //
            groupBy, //
            having, //
            orderBy );

      while( c.moveToNext() )
         getQuestion( c );
      c.close();

      return list;
   }

   private Question getQuestion( Cursor c ) {

      final Question q = new Question();
      q.setId( c.getInt( EQuestions.id.index ) );

      final Qualifier x = new Qualifier();
      x.setBanca( c.getString( EQuestions.banca.index ) );
      x.setAno( c.getString( EQuestions.ano.index ) );
      x.setOrgao( c.getString( EQuestions.orgao.index ) );
      x.setUF( c.getString( EQuestions.uf.index ) );
      x.setCargo( c.getString( EQuestions.cargo.index ) );
      x.setDisciplina( c.getString( EQuestions.disciplina.index ) );
      x.setAssunto( c.getString( EQuestions.assunto.index ) );
      q.setQualifier( x );

      q.setDescription( c.getString( EQuestions.question.index ) );

      final List< String > options = new ArrayList< String >();

      for( int i = 0; i < MAX_QUESTIONS; i++ ) {
         final String option = c.getString( EQuestions.optionA.index + i );
         if( option != null )
            options.add( option );
      }
      q.setOptions( options );

      q.setMatch( c.getString( EQuestions.match.index ) );
      q.setUsed( c.getInt( EQuestions.used.index ) );

      return q;
   }

   public static final int MAX_QUESTIONS = 5;

   private static enum EQuestions {
      id( 0 ), //
      banca( 1 ), //
      ano( 2 ), //
      orgao( 3 ), //
      uf( 4 ), //
      cargo( 5 ), //
      disciplina( 6 ), //
      assunto( 7 ), //
      question( 8 ), //
      optionA( 9 ), //
      optionB( 10 ), //
      optionC( 11 ), //
      optionD( 12 ), //
      optionE( 13 ), //
      match( 14 ), //
      used( 15 );

      public final int index;

      private EQuestions( int indexP ) {
         index = indexP;
      }
   }
}