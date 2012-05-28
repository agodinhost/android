package com.gec.questoesGratis.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Application;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.gec.questoesGratis.model.History;
import com.gec.questoesGratis.model.Question;
import com.gec.questoesGratis.model.Quiz;

public class XSelection extends Application {

   private Quiz      quiz;
   private int       currentQuestion;
   private int       questionsCount = -1;
   private ViewPager pager;

   private Long      historyList_selectedId;
   private Integer   historyList_selectedPos;

   private Long      quizList_selectedId;
   private Integer   quizList_selectedPos;

   public void setPager(ViewPager pager) {
      this.pager = pager;
      pager.setCurrentItem(currentQuestion);
   }

   public Quiz getQuiz() {
      return quiz;
   }

   public void setQuiz(Quiz quiz) {
      this.quiz = quiz;
      currentQuestion = 0;
      List<Question> questions = quiz.getQuestions();
      questionsCount = questions == null ? -1 : questions.size();
   }

   public void setQuestions(List<Question> questions) {
      quiz.setQuestions(questions);
      currentQuestion = 0;
      questionsCount = questions == null ? -1 : questions.size();
   }

   public Question getQuestion() {
      Question q = null;
      if (questionsCount > 0)
         q = quiz.getQuestions().get(currentQuestion);
      return q;
   }

   public Question getQuestion(int index) {
      Question q = null;
      if (questionsCount > 0)
         q = quiz.getQuestions().get(index);
      return q;
   }

   public int getQuestionsCount() {
      return questionsCount;
   }

   public int getCurrentQuestion() {
      return currentQuestion;
   }

   public void moveFirst() {
      currentQuestion = 0;
      pager.setCurrentItem(currentQuestion);
   }

   public void movePrevious() {
      currentQuestion--;
      if (currentQuestion < 0)
         currentQuestion = 0;
      pager.setCurrentItem(currentQuestion);
   }

   public void moveNext() {
      currentQuestion++;
      if (currentQuestion > questionsCount)
         currentQuestion = questionsCount;
      pager.setCurrentItem(currentQuestion);
   }

   public void moveLast() {
      currentQuestion = questionsCount;
      pager.setCurrentItem(currentQuestion);
   }

   // --- History List state.

   public Long getHistoryList_selectedId() {
      return historyList_selectedId;
   }

   public void setHistoryList_selectedId(long historyList_selectedId) {
      this.historyList_selectedId = historyList_selectedId;
   }

   public Integer getHistoryList_selectedPos() {
      return historyList_selectedPos;
   }

   public void setHistoryList_selectedPos(int historyList_selectedPos) {
      this.historyList_selectedPos = historyList_selectedPos;
   }

   // --- Quiz List state.

   public Long getQuizList_selectedId() {
      return quizList_selectedId;
   }

   public void setQuizList_selectedId(long quizList_selectedId) {
      this.quizList_selectedId = quizList_selectedId;
   }

   public Integer getQuizList_selectedPos() {
      return quizList_selectedPos;
   }

   public void setQuizList_selectedPos(int quizList_selectedPos) {
      this.quizList_selectedPos = quizList_selectedPos;
   }

   // --- Database.

   public List<History> getHistoryFromDB() {

      List<History> list = new ArrayList<History>();

      list.add(new History(1, new Date(), 10, 0));
      list.add(new History(2, new Date(), 20, 1));
      list.add(new History(3, new Date(), 30, 0));
      list.add(new History(4, new Date(), 40, 1));
      list.add(new History(5, new Date(), 50, 0));
      list.add(new History(6, new Date(), 60, 1));
      list.add(new History(7, new Date(), 70, 0));

      return list;
   }

   public Quiz getQuizFromDB(long quizId) {

      Log.d("xSel", "quizId [" + quizId + "].");

      Quiz quiz = new Quiz();
      List<Question> questions = new ArrayList<Question>();

      Question q = new Question();
      List<String> o = new ArrayList<String>();

      q.setUf("RJ,SP");
      q.setBanca("banca1");
      q.setOrgao("orgão1");
      q.setAno("2011");
      q.setCargo("cargo1");
      q.setDisciplina("disciplina1");
      q.setAssunto("assunto1");

      q.setQuestionId(100);
      q.setQuestionNumber(1);
      q.setQuestion("You're at a kickin' house party. Who are you hanging out with?");
      o.add("You stick with your friends");
      o.add("Mingle of course!");
      o.add("Er... House parties? What are those?");
      o.add("Make a move on the hottest person there");
      q.setOptions(o);
      q.setRightOption("4");
      questions.add(q);

      // ---
      q = new Question();
      o = new ArrayList<String>();

      q.setUf("RJ,SP");
      q.setBanca("banca1");
      q.setOrgao("orgão1");
      q.setAno("2011");
      q.setCargo("cargo1");
      q.setDisciplina("disciplina1");
      q.setAssunto("assunto1");

      q.setQuestionId(101);
      q.setQuestionNumber(2);
      q.setQuestion("How would you describe your appearance?");
      o = new ArrayList<String>();
      o.add("Too sexy for you!");
      o.add("Bring your paper bag, you'll need it.");
      o.add("I'm attractive");
      o.add("I... have a good personality?");
      q.setOptions(o);
      q.setRightOption("3");
      q.setUserOption("2");
      questions.add(q);

      // ---
      q = new Question();
      o = new ArrayList<String>();

      q.setUf("RJ,SP");
      q.setBanca("banca1");
      q.setOrgao("orgão1");
      q.setAno("2011");
      q.setCargo("cargo1");
      q.setDisciplina("disciplina1");
      q.setAssunto("assunto1");

      q.setQuestionId(102);
      q.setQuestionNumber(3);
      q.setQuestion("Which of your physical features would you say people notice most about you?");
      o = new ArrayList<String>();
      o.add("My body");
      o.add("I told you, I have a GREAT personality");
      o.add("My eyes");
      o.add("My flabby gut");
      q.setOptions(o);
      q.setRightOption("3");
      q.setUserOption("3");
      questions.add(q);

      // ---
      q = new Question();
      o = new ArrayList<String>();

      q.setUf("RJ,SP");
      q.setBanca("banca1");
      q.setOrgao("orgão1");
      q.setAno("2011");
      q.setCargo("cargo1");
      q.setDisciplina("disciplina1");
      q.setAssunto("assunto1");

      q.setQuestionId(105);
      q.setQuestionNumber(4);
      q.setQuestion("You gave someone your number. When do you think they'll call you?");
      o = new ArrayList<String>();
      o.add("Psh... never, as usual!");
      o.add("In a day or two");
      o.add("Don't know, but I will be right next to the phone waiting");
      o.add("Later that night");
      q.setOptions(o);
      q.setRightOption("4");
      questions.add(q);

      // ---
      q = new Question();
      o = new ArrayList<String>();

      q.setUf("RJ,SP");
      q.setBanca("banca1");
      q.setOrgao("orgão1");
      q.setAno("2011");
      q.setCargo("cargo1");
      q.setDisciplina("disciplina1");
      q.setAssunto("assunto1");

      q.setQuestionId(106);
      q.setQuestionNumber(5);
      q.setQuestion("What kind of dates are you into?");
      o = new ArrayList<String>();
      o.add("I'm a woman, and I usually date men.");
      o.add("I'm a man, and I usually date women.");
      o.add("I'm a woman, and I'll take what I can get.");
      o.add("I'm a man, and I'll date anything that walks.");
      q.setOptions(o);
      q.setRightOption("3");
      q.setUserOption("2");
      questions.add(q);

      // ---
      q = new Question();
      o = new ArrayList<String>();

      q.setUf("RJ,SP");
      q.setBanca("banca1");
      q.setOrgao("orgão1");
      q.setAno("2011");
      q.setCargo("cargo1");
      q.setDisciplina("disciplina1");
      q.setAssunto("assunto1");

      q.setQuestionId(106);
      q.setQuestionNumber(6);
      q.setQuestion("<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sit amet est ut sapien dignissim euismod sit amet et odio. Integer at enim ante, vitae consectetur nulla. Aenean eleifend sapien iaculis erat faucibus pretium. Pellentesque imperdiet mollis pulvinar. Nullam velit dolor, sodales eget auctor non, laoreet ac felis. Sed dictum suscipit lacus, ut eleifend ligula rhoncus nec. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Vestibulum malesuada mattis erat, quis tempus arcu viverra id. Pellentesque quis sapien massa. In hac habitasse platea dictumst.</p>"
            + //
            "<p>Cras leo tellus, vehicula imperdiet gravida non, blandit in tortor. Sed sem tortor, vulputate vitae adipiscing vel, scelerisque vel elit. Duis purus arcu, iaculis sodales blandit ut, venenatis eget purus. Donec arcu tortor, ultrices dignissim viverra vitae, ornare vel diam. Proin ante urna, porta ac tincidunt eu, accumsan ac justo. Curabitur lorem massa, dignissim quis bibendum vitae, elementum quis odio. In non euismod lorem. Curabitur eu malesuada velit. Vivamus lobortis felis sit amet sapien egestas ut rhoncus sem malesuada. Sed iaculis augue non libero tempus at ultrices nisi euismod. Sed accumsan varius tristique. Pellentesque adipiscing sodales mauris, vulputate lacinia justo tincidunt eu. Integer aliquet suscipit ante, eget pharetra odio tincidunt mollis. Maecenas in aliquam dolor. Praesent tristique libero sed metus luctus facilisis facilisis arcu auctor. Pellentesque fringilla ligula sit amet purus varius placerat.</p>"
            + //
            "<p>Sed lorem est, sollicitudin et blandit eget, egestas in sem. Donec ullamcorper magna non elit luctus consequat. Etiam ac enim augue. Curabitur volutpat molestie augue ac bibendum. Nullam porttitor purus quis neque lobortis a varius arcu convallis. Morbi ultrices, ipsum id lacinia auctor, sem lacus bibendum sapien, non ultrices dolor justo venenatis elit. Sed ac ligula turpis, sed placerat massa. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; In hac habitasse platea dictumst. Proin bibendum, felis vitae scelerisque rutrum, augue purus iaculis diam, vitae volutpat mauris eros nec nulla. Quisque eget pellentesque quam. Praesent at nisi risus. Donec ut tellus ac enim tincidunt lacinia.</p>"
            + //
            "<p>Aenean bibendum facilisis elit at tristique. Duis vel turpis ac velit pretium dapibus. Sed at bibendum tortor. Aenean auctor fringilla vestibulum. Donec id turpis ligula, congue tempor diam. Nulla eu lorem et ante scelerisque tincidunt. Vestibulum lacinia imperdiet odio sit amet rutrum. Phasellus suscipit scelerisque pharetra. Donec non dui mauris, ut rhoncus sem. Vestibulum egestas lorem et nulla iaculis vel volutpat lectus hendrerit. Sed volutpat, dui in accumsan blandit, eros lacus gravida nisi, id sollicitudin arcu turpis sed odio. Maecenas eget orci odio, vel malesuada libero. Nam sit amet orci erat.</p>"
            + //
            "<p>Nunc massa magna, vulputate vel cursus ac, gravida sit amet arcu. Praesent a purus quam, vitae lacinia justo. Suspendisse lorem elit, luctus ac hendrerit a, euismod mattis est. In massa tortor, venenatis vitae euismod consectetur, pretium eu eros. Quisque dolor quam, lobortis non vestibulum sit amet, feugiat in sem. Suspendisse gravida, velit et posuere blandit, dui risus faucibus quam, euismod porta nunc dui a orci. Phasellus suscipit convallis quam. Vestibulum a purus vitae tellus scelerisque congue in quis sapien. Praesent nec libero mi, quis venenatis ligula. Maecenas aliquam risus auctor est consequat facilisis eu sit amet augue. Praesent non tellus odio.?");
      o = new ArrayList<String>();
      o.add("This huge text above is intented to test the questionDetails button.");
      o.add("This button should be ONLY visible in this question.");
      o.add("It's kinda simple ...");
      o.add("Just get the webView size AFTER we do load our html.");
      q.setOptions(o);
      q.setRightOption("1");
      q.setUserOption("1");
      questions.add(q);

      quiz.setQuestions(questions);

      return quiz;
   }
}