
package com.gec.questoesGratis;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Application;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.gec.questoesGratis.dao.DBHelper;
import com.gec.questoesGratis.model.Answer;
import com.gec.questoesGratis.model.Qualifier;
import com.gec.questoesGratis.model.Question;
import com.gec.questoesGratis.model.Quiz;
import com.gec.questoesGratis.model.Status;

public class ApplicationX extends Application {

   private static ApplicationX lastInstance;

   private Quiz                quiz;
   private int                 currentAnswer;
   private int                 answersCount = -1;

   private DBHelper            dbHelper;
   private ViewPager           pager;

   private Long                historyList_selectedId;
   private Integer             historyList_selectedPos;

   private Long                quizList_selectedId;
   private Integer             quizList_selectedPos;

   public ApplicationX() {
      super();
      lastInstance = this;
   }

   public static ApplicationX getInstance() {
      return lastInstance;
   }

   public DBHelper getDbHelper() {
      return dbHelper;
   }

   public void setDbHelper( DBHelper dbHelper ) {
      this.dbHelper = dbHelper;
   }

   public void setPager( ViewPager pager ) {
      this.pager = pager;
      pager.setCurrentItem( currentAnswer );
   }

   public Quiz getQuiz() {
      return quiz;
   }

   public void setQuiz( Quiz quiz ) {
      this.quiz = quiz;
      currentAnswer = 0;
      List< Answer > answers = quiz.getAnswers();
      answersCount = answers == null? -1: answers.size();
   }

   public void setAnswers( List< Answer > answers ) {
      quiz.setAnswers( answers );
      currentAnswer = 0;
      answersCount = answers == null? -1: answers.size();
   }

   public Answer getAnswer() {
      Answer a = null;
      if( answersCount > 0 ) a = quiz.getAnswers().get( currentAnswer );
      return a;
   }

   public Answer getAnswer( int index ) {
      Answer a = null;
      if( answersCount > 0 ) a = quiz.getAnswers().get( index );
      return a;
   }

   public int getAnswersCount() {
      return answersCount;
   }

   public int getCurrentAnswer() {
      return currentAnswer;
   }

   public void moveFirst() {
      currentAnswer = 0;
      pager.setCurrentItem( currentAnswer );
   }

   public void movePrevious() {
      currentAnswer--;
      if( currentAnswer < 0 ) currentAnswer = 0;
      pager.setCurrentItem( currentAnswer );
   }

   public void moveNext() {
      currentAnswer++;
      if( currentAnswer > answersCount ) currentAnswer = answersCount;
      pager.setCurrentItem( currentAnswer );
   }

   public void moveLast() {
      currentAnswer = answersCount;
      pager.setCurrentItem( currentAnswer );
   }

   // --- History List state.

   public Long getHistoryList_selectedId() {
      return historyList_selectedId;
   }

   public void setHistoryList_selectedId( long historyList_selectedId ) {
      this.historyList_selectedId = historyList_selectedId;
   }

   public Integer getHistoryList_selectedPos() {
      return historyList_selectedPos;
   }

   public void setHistoryList_selectedPos( int historyList_selectedPos ) {
      this.historyList_selectedPos = historyList_selectedPos;
   }

   // --- Quiz List state.

   public Long getQuizList_selectedId() {
      return quizList_selectedId;
   }

   public void setQuizList_selectedId( long quizList_selectedId ) {
      this.quizList_selectedId = quizList_selectedId;
   }

   public Integer getQuizList_selectedPos() {
      return quizList_selectedPos;
   }

   public void setQuizList_selectedPos( int quizList_selectedPos ) {
      this.quizList_selectedPos = quizList_selectedPos;
   }

   // --- Database.

   public List< Quiz > getQuizFromDB() {

      List< Quiz > list = new ArrayList< Quiz >();

      list.add( new Quiz( 1, new Date(), 10, Status.Unfinished ) );
      list.add( new Quiz( 2, new Date(), 20, Status.Finished ) );
      list.add( new Quiz( 3, new Date(), 30, Status.Unfinished ) );
      list.add( new Quiz( 4, new Date(), 40, Status.Finished ) );
      list.add( new Quiz( 5, new Date(), 50, Status.Unfinished ) );
      list.add( new Quiz( 6, new Date(), 60, Status.Finished ) );
      list.add( new Quiz( 7, new Date(), 70, Status.Unfinished ) );

      return list;
   }

   public Quiz getQuizFromDB( long quizId ) {

      Log.d( "xSel", "quizId [" + quizId + "]." );

      Quiz quiz = new Quiz();
      List< Answer > answers = new ArrayList< Answer >();

      Qualifier f = new Qualifier();
      f.setUf( "RJ,SP" );
      f.setBanca( "banca1" );
      f.setOrgao( "orgão1" );
      f.setAno( "2011" );
      f.setCargo( "cargo1" );
      f.setDisciplina( "disciplina1" );
      f.setAssunto( "assunto1" );

      Answer a = new Answer();
      a.setId( 10001 );
      a.setNumber( 1 );

      Question q = new Question();
      q.setId( 101 );
      q.setDescription( "You're at a kickin' house party. Who are you hanging out with?" );
      List< String > o = new ArrayList< String >();
      o.add( "You stick with your friends" );
      o.add( "Mingle of course!" );
      o.add( "Er... House parties? What are those?" );
      o.add( "Make a move on the hottest person there" );
      q.setOptions( o );
      q.setMatch( "4" );
      q.setQualifier( f );

      a.setQuestion( q );
      answers.add( a );

      // ---
      a = new Answer();
      a.setId( 10002 );
      a.setNumber( 2 );

      q = new Question();
      q.setId( 102 );
      q.setDescription( "How would you describe your appearance?" );
      o = new ArrayList< String >();
      o.add( "Too sexy for you!" );
      o.add( "Bring your paper bag, you'll need it." );
      o.add( "I'm attractive" );
      o.add( "I... have a good personality?" );
      q.setOptions( o );
      q.setMatch( "3" );
      q.setQualifier( f );

      a.setQuestion( q );
      a.setAnswer( "2" );
      answers.add( a );

      // ---
      a = new Answer();
      a.setId( 10003 );
      a.setNumber( 3 );

      q = new Question();
      q.setId( 103 );
      q.setDescription( "Which of your physical features would you say people notice most about you?" );
      o = new ArrayList< String >();
      o.add( "My body" );
      o.add( "I told you, I have a GREAT personality" );
      o.add( "My eyes" );
      o.add( "My flabby gut" );
      q.setOptions( o );
      q.setMatch( "3" );
      q.setQualifier( f );

      a.setQuestion( q );
      a.setAnswer( "3" );
      answers.add( a );

      // ---
      a = new Answer();
      a.setId( 10004 );
      a.setNumber( 4 );

      q = new Question();
      q.setId( 104 );
      q.setDescription( "You gave someone your number. When do you think they'll call you?" );
      o = new ArrayList< String >();
      o.add( "Psh... never, as usual!" );
      o.add( "In a day or two" );
      o.add( "Don't know, but I will be right next to the phone waiting" );
      o.add( "Later that night" );
      q.setOptions( o );
      q.setMatch( "4" );
      q.setQualifier( f );

      a.setQuestion( q );
      answers.add( a );

      // ---
      a = new Answer();
      a.setId( 10005 );
      a.setNumber( 5 );

      q = new Question();
      q.setId( 105 );
      q.setDescription( "What kind of dates are you into?" );
      o = new ArrayList< String >();
      o.add( "I'm a woman, and I usually date men." );
      o.add( "I'm a man, and I usually date women." );
      o.add( "I'm a woman, and I'll take what I can get." );
      o.add( "I'm a man, and I'll date anything that walks." );
      q.setOptions( o );
      q.setMatch( "3" );
      q.setQualifier( f );

      a.setQuestion( q );
      a.setAnswer( "2" );
      answers.add( a );

      // ---
      a = new Answer();
      a.setId( 10006 );
      a.setNumber( 6 );

      q = new Question();
      q.setId( 106 );
      q.setDescription( "<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sit amet est ut sapien dignissim euismod sit amet et odio. Integer at enim ante, vitae consectetur nulla. Aenean eleifend sapien iaculis erat faucibus pretium. Pellentesque imperdiet mollis pulvinar. Nullam velit dolor, sodales eget auctor non, laoreet ac felis. Sed dictum suscipit lacus, ut eleifend ligula rhoncus nec. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Vestibulum malesuada mattis erat, quis tempus arcu viverra id. Pellentesque quis sapien massa. In hac habitasse platea dictumst.</p>"
            + //
            "<p>Cras leo tellus, vehicula imperdiet gravida non, blandit in tortor. Sed sem tortor, vulputate vitae adipiscing vel, scelerisque vel elit. Duis purus arcu, iaculis sodales blandit ut, venenatis eget purus. Donec arcu tortor, ultrices dignissim viverra vitae, ornare vel diam. Proin ante urna, porta ac tincidunt eu, accumsan ac justo. Curabitur lorem massa, dignissim quis bibendum vitae, elementum quis odio. In non euismod lorem. Curabitur eu malesuada velit. Vivamus lobortis felis sit amet sapien egestas ut rhoncus sem malesuada. Sed iaculis augue non libero tempus at ultrices nisi euismod. Sed accumsan varius tristique. Pellentesque adipiscing sodales mauris, vulputate lacinia justo tincidunt eu. Integer aliquet suscipit ante, eget pharetra odio tincidunt mollis. Maecenas in aliquam dolor. Praesent tristique libero sed metus luctus facilisis facilisis arcu auctor. Pellentesque fringilla ligula sit amet purus varius placerat.</p>"
            + //
            "<p>Sed lorem est, sollicitudin et blandit eget, egestas in sem. Donec ullamcorper magna non elit luctus consequat. Etiam ac enim augue. Curabitur volutpat molestie augue ac bibendum. Nullam porttitor purus quis neque lobortis a varius arcu convallis. Morbi ultrices, ipsum id lacinia auctor, sem lacus bibendum sapien, non ultrices dolor justo venenatis elit. Sed ac ligula turpis, sed placerat massa. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; In hac habitasse platea dictumst. Proin bibendum, felis vitae scelerisque rutrum, augue purus iaculis diam, vitae volutpat mauris eros nec nulla. Quisque eget pellentesque quam. Praesent at nisi risus. Donec ut tellus ac enim tincidunt lacinia.</p>"
            + //
            "<p>Aenean bibendum facilisis elit at tristique. Duis vel turpis ac velit pretium dapibus. Sed at bibendum tortor. Aenean auctor fringilla vestibulum. Donec id turpis ligula, congue tempor diam. Nulla eu lorem et ante scelerisque tincidunt. Vestibulum lacinia imperdiet odio sit amet rutrum. Phasellus suscipit scelerisque pharetra. Donec non dui mauris, ut rhoncus sem. Vestibulum egestas lorem et nulla iaculis vel volutpat lectus hendrerit. Sed volutpat, dui in accumsan blandit, eros lacus gravida nisi, id sollicitudin arcu turpis sed odio. Maecenas eget orci odio, vel malesuada libero. Nam sit amet orci erat.</p>"
            + //
            "<p>Nunc massa magna, vulputate vel cursus ac, gravida sit amet arcu. Praesent a purus quam, vitae lacinia justo. Suspendisse lorem elit, luctus ac hendrerit a, euismod mattis est. In massa tortor, venenatis vitae euismod consectetur, pretium eu eros. Quisque dolor quam, lobortis non vestibulum sit amet, feugiat in sem. Suspendisse gravida, velit et posuere blandit, dui risus faucibus quam, euismod porta nunc dui a orci. Phasellus suscipit convallis quam. Vestibulum a purus vitae tellus scelerisque congue in quis sapien. Praesent nec libero mi, quis venenatis ligula. Maecenas aliquam risus auctor est consequat facilisis eu sit amet augue. Praesent non tellus odio.?" );
      o = new ArrayList< String >();
      o.add( "This huge text above is intented to test the questionDetails button." );
      o.add( "This button should be ONLY visible in this question." );
      o.add( "It's kinda simple ..." );
      o.add( "Just get the webView size AFTER we do load our html." );
      q.setOptions( o );
      q.setMatch( "1" );
      q.setQualifier( f );

      a.setQuestion( q );
      a.setAnswer( "1" );
      answers.add( a );

      quiz.setAnswers( answers );

      return quiz;
   }
}