package com.gec.questoesGratis.model;

/**
 * Question Qualifier TO.
 * 
 * @author agodinho
 */
public final class Qualifier {

   protected String banca;
   protected String ano;
   protected String orgao;
   protected String uf;
   protected String cargo;
   protected String disciplina;
   protected String assunto;

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

   public String getUF() {
      return uf;
   }

   public void setUF( String uf ) {
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
      final StringBuffer b = new StringBuffer();
      addStr( b, banca );
      addStr( b, String.valueOf( ano ) );
      addStr( b, orgao );
      addStr( b, uf );
      addStr( b, cargo );
      addStr( b, disciplina );
      addStr( b, assunto );
      return b.toString();
   }

   private void addStr( StringBuffer b, String filter ) {
      if( filter != null ) {
         if( b.length() > 0 )
            b.append( " - " );
         b.append( filter );
      }
   }
}