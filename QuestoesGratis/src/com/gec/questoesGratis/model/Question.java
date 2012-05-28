package com.gec.questoesGratis.model;

import java.util.List;

public class Question {

   private int          questionId;

   private String       uf;
   private String       banca;
   private String       orgao;
   private String       cargo;
   private String       ano;
   private String       disciplina;
   private String       assunto;

   private int          questionNumber;
   private String       question;
   private List<String> options;
   private String       rightOption;
   private String       userOption;

   public int getQuestionId() {
      return questionId;
   }

   public void setQuestionId(int questionId) {
      this.questionId = questionId;
   }

   public String getUf() {
      return uf;
   }

   public void setUf(String uf) {
      this.uf = uf;
   }

   public String getBanca() {
      return banca;
   }

   public void setBanca(String banca) {
      this.banca = banca;
   }

   public String getOrgao() {
      return orgao;
   }

   public void setOrgao(String orgao) {
      this.orgao = orgao;
   }

   public String getCargo() {
      return cargo;
   }

   public void setCargo(String cargo) {
      this.cargo = cargo;
   }

   public String getAno() {
      return ano;
   }

   public void setAno(String ano) {
      this.ano = ano;
   }

   public String getDisciplina() {
      return disciplina;
   }

   public void setDisciplina(String disciplina) {
      this.disciplina = disciplina;
   }

   public String getAssunto() {
      return assunto;
   }

   public void setAssunto(String assunto) {
      this.assunto = assunto;
   }

   public int getQuestionNumber() {
      return questionNumber;
   }

   public void setQuestionNumber(int questionNumber) {
      this.questionNumber = questionNumber;
   }

   public String getQuestion() {
      return question;
   }

   public void setQuestion(String question) {
      this.question = question;
   }

   public List<String> getOptions() {
      return options;
   }

   public void setOptions(List<String> options) {
      this.options = options;
   }

   public String getOption(int index) {
      String option = null;
      try {
         option = options.get(index);
      } catch (NullPointerException e) {
         // just ignore.
      } catch (IndexOutOfBoundsException e) {
         // just ignore.
      }
      return option;
   }

   public String getRightOption() {
      return rightOption;
   }

   public void setRightOption(String rightOption) {
      this.rightOption = rightOption;
   }

   public String getUserOption() {
      return userOption;
   }

   public void setUserOption(String userOption) {
      this.userOption = userOption;
   }

   public String getQuestionNumberString() {
      return "Questão " + questionNumber;
   }

   public String getDescription() {
      String d = "";
      d = addFilter(d, uf, "TODAS");
      d = addFilter(d, banca, "TODAS");
      d = addFilter(d, orgao, "TODOS");
      d = addFilter(d, cargo, "TODOS");
      d = addFilter(d, ano, "TODOS");
      d = addFilter(d, disciplina, "TODAS");
      d = addFilter(d, assunto, "TODOS");
      return d;
   }

   public String getCompleteDescription() {
      return "Questão " + String.valueOf(questionNumber) + ": " + getDescription();
   }

   private String addFilter(String str, String filter, String all) {
      if (filter != null && !all.equals(filter)) {
         if (str.length() > 0)
            str += " - ";
         str += filter;
      }
      return str;
   }

   public Boolean getStatus() {
      Boolean status = null;
      if (rightOption != null && userOption != null) {
         status = userOption.equalsIgnoreCase(rightOption);
      }
      return status;
   }
}