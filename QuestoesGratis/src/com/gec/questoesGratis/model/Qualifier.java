
package com.gec.questoesGratis.model;

import java.io.Serializable;

/**
 * Question Qualifier TO.
 * 
 * @author agodinho
 */
public final class Qualifier implements Serializable {

   private static final long serialVersionUID = -5113532576775005824L;

   protected String          banca;
   protected String          ano;
   protected String          orgao;
   protected String          uf;
   protected String          cargo;
   protected String          disciplina;
   protected String          assunto;

   public String getBanca() {
      return banca;
   }

   public void setBanca( String banca ) {
      this.banca = banca;
   }

   public String getAno() {
      return ano;
   }

   public void setAno( String ano ) {
      this.ano = ano;
   }

   public String getOrgao() {
      return orgao;
   }

   public void setOrgao( String orgao ) {
      this.orgao = orgao;
   }

   public String getUf() {
      return uf;
   }

   public void setUf( String uf ) {
      this.uf = uf;
   }

   public String getCargo() {
      return cargo;
   }

   public void setCargo( String cargo ) {
      this.cargo = cargo;
   }

   public String getDisciplina() {
      return disciplina;
   }

   public void setDisciplina( String disciplina ) {
      this.disciplina = disciplina;
   }

   public String getAssunto() {
      return assunto;
   }

   public void setAssunto( String assunto ) {
      this.assunto = assunto;
   }

   public String getDescription() {
      String d = "";
      d = addStr( d, banca, "TODAS" );
      d = addStr( d, String.valueOf( ano ), "TODOS" );
      d = addStr( d, orgao, "TODOS" );
      d = addStr( d, uf, "TODAS" );
      d = addStr( d, cargo, "TODOS" );
      d = addStr( d, disciplina, "TODAS" );
      d = addStr( d, assunto, "TODOS" );
      return d;
   }

   private String addStr( String str, String filter, String all ) {
      if( filter != null && !all.equals( filter ) ) {
         if( str.length() > 0 ) str += " - ";
         str += filter;
      }
      return str;
   }
}