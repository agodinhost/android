package com.gec.questoesGratis.dao;

import static com.gec.questoesGratis.tools.ListX.randomStart;
import static com.gec.questoesGratis.tools.ListX.shuffle;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.gec.questoesGratis.model.Answer;
import com.gec.questoesGratis.model.Filter;
import com.gec.questoesGratis.model.Filter.Ignore;
import com.gec.questoesGratis.model.Qualifier;
import com.gec.questoesGratis.model.Question;
import com.gec.questoesGratis.model.Quiz;
import com.gec.questoesGratis.tools.LogX;

/**
 * Database Helper class.
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
   private StringBuffer getPlick( List< String > list ) {

      final StringBuffer b = new StringBuffer();

      if( list != null )
         for( String item: list )
            b.append( "'" ) //
                  .append( item.trim() ) //
                  .append( "'" ) //
                  .append( "," );

      final int l = b.length();
      if( l > 0 )
         b.deleteCharAt( l - 1 );

      return b;
   }

   /* ALWAYS returns a valid String. */
   private StringBuffer getIn( String field, List< String > list ) {

      final StringBuffer b = new StringBuffer();

      final StringBuffer in = getPlick( list );
      if( in.length() > 0 ) //
         b.append( field ) //
               .append( " IN( " ) //
               .append( in ) //
               .append( " ) " );

      return b;
   }

   private StringBuffer addAnd( StringBuffer b ) {

      if( b.length() > 0 )
         b.append( "AND " );
      return b;
   }

   private void addInClause( StringBuffer b, String field, List< String > list ) {

      final StringBuffer in = getIn( field, list );
      if( in.length() > 0 )
         addAnd( b ).append( in );
   }

   /* ALWAYS returns a valid String. */
   private StringBuffer getWhere( Filter filter ) {

      final StringBuffer b = new StringBuffer();

      //TODO: filter precisa tb da qtd de questões (a tabela questions não precisa disso);

      //TODO: preciso de dois campos na questions pra controlar isso ...
      final Ignore ignore = filter.getIgnore();
      if( Ignore.ANSWERED.equals( ignore ) ) {
      } else if( Ignore.RIGHT.equals( ignore ) ) {
      } else if( Ignore.WRONG.equals( ignore ) ) {
      }

      addInClause( b, "trim(banca)", filter.getBancas() );
      addInClause( b, "trim(ano)", filter.getAnos() );
      addInClause( b, "trim(orgao)", filter.getOrgaos() );
      addInClause( b, "trim(uf)", filter.getUFs() );
      addInClause( b, "trim(cargo)", filter.getCargos() );
      addInClause( b, "trim(disciplina)", filter.getDisciplinas() );
      addInClause( b, "trim(assunto)", filter.getAssuntos() );

      return b;
   }

   /* ALWAYS returns a valid String. */
   private StringBuffer getRawWhere( Filter filter ) {

      final StringBuffer b = new StringBuffer();

      final StringBuffer where = getWhere( filter );
      if( where.length() > 0 )
         b.append( "WHERE " ) //
               .append( where );

      return b;
   }

   public int getQuestionsCount( Filter filter ) {

      final StringBuffer b = new StringBuffer( "SELECT Count(1) FROM questions " ) //
            .append( getRawWhere( filter ) );
      final Cursor c = database.rawQuery( b.toString(), null );
      c.moveToFirst();
      int count = c.getInt( 0 );
      c.close();
      return count;
   }

   /*
    * 1) Get list id, apply filter;
    * 2) Shuffle the list id ;
    * 3) Create new list id limited to the desired number of questions;
    * 4) BeginTransaction;
    * 5) create quiz;
    * 6) for each id in the list id:
    * 7)    create and add one answer/question in the quiz above;
    * 7) Commit.
    * ?) Roolback?
    */
   public Quiz createQuiz( Filter filter ) {

      database.beginTransaction();

      final Quiz quiz = new Quiz( filter.getDescription() );
      if( insertQuiz( quiz ) ) {

         quiz.setAnswers( createAnswers( quiz, filter ) );
         if( insertAnswers( quiz ) )
            database.endTransaction();
      }

      //TODO: load the created quiz (to get al fields populated).

      //TODO: throw exception.

      return quiz;
   }

   private boolean insertQuiz( Quiz quiz ) {

      boolean b = true;
      final ContentValues values = new ContentValues();
      values.put( "filter", quiz.getFilter() );
      try {
         Long id = database.insertOrThrow( "quizzes", null, values );
         quiz.setId( id );
      } catch( SQLException e ) {
         b = false;
      }
      return b;
   }

   private List< Answer > createAnswers( Quiz quiz, Filter filter ) {

      final int total = filter.getTotal();
      final List< Answer > list = new ArrayList< Answer >( total );

      final List< Long > ids = getQuestionsId( filter );
      shuffle( ids );
      final int start = randomStart( ids, total );
      for( int n = 0; n < total; n++ ) {

         final Question q = new Question();
         q.setId( ids.get( start + n ) );
         final Answer a = new Answer();
         a.setQuestion( q );
         a.setNumber( n + 1 );
         list.add( a );
      }

      return list;
   }

   private boolean insertAnswers( Quiz quiz ) {

      boolean b = true;
      try {
         final List< Answer > list = quiz.getAnswers();
         if( list != null )
            for( Answer answer: list )
               insertAnswer( quiz.getId(), answer );
      } catch( SQLException e ) {
         b = false;
      }
      return b;
   }

   private void insertAnswer( Long quizId, Answer answer ) {

      final ContentValues values = new ContentValues();
      values.put( "quizId", quizId );
      values.put( "questionId", answer.getQuestionId() );
      values.put( "number", answer.getNumber() );
      final Long id = database.insertOrThrow( "answers", null, values );
      /*/ trg_answers_ai -> update -> questions /*/
      answer.setId( id );
   }

   private List< Long > getQuestionsId( Filter filter ) {

      final List< Long > list = new ArrayList< Long >();

      final String table = "questions";
      final String columns[] = { "_id" };
      final String where = getWhere( filter ).toString();
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
         list.add( new Long( c.getLong( 0 ) ) );
      c.close();

      return list;
   }

   //TODO: 
   public List< Quiz > getQuizzes() {
      return new ArrayList< Quiz >();
   }

   //TODO: 
   public Quiz getQuiz( Long quizId ) {
      return null;
   }

   //TODO: 
   public List< Answer > getAnswers( Long quizId ) {
      return null;
   }

   //TODO: 
   public List< Question > getQuestions( Long quizId ) {

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
      q.setId( c.getLong( EQuestions.id.index ) );

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
      q.setUsed( c.getString( EQuestions.used.index ) );

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