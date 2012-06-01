package com.gec.questoesGratis.model;

import java.io.Serializable;
import java.util.List;

/**
 * Filter TO.
 * 
 * @author agodinho
 */
public final class Filter implements Serializable {

   public static enum Ignore {
      NONE, //
      ANSWERED, //
      RIGHT, //
      WRONG;
   }

   private Integer        total;
   private Ignore         ignore;

   private List< String > ufs;
   private List< String > bancas;
   private List< String > orgaos;
   private List< String > cargos;
   private List< String > anos;
   private List< String > disciplinas;
   private List< String > assuntos;

   public Integer getTotal() {
      return total;
   }

   public void setTotal( Integer total ) {
      this.total = total;
   }

   public Ignore getIgnore() {
      return ignore;
   }

   public void setIgnore( Ignore ignore ) {
      this.ignore = ignore;
   }

   public List< String > getUFs() {
      return ufs;
   }

   public void setUFs( List< String > ufs ) {
      this.ufs = ufs;
   }

   public List< String > getBancas() {
      return bancas;
   }

   public void setBancas( List< String > bancas ) {
      this.bancas = bancas;
   }

   public List< String > getOrgaos() {
      return orgaos;
   }

   public void setOrgaos( List< String > orgaos ) {
      this.orgaos = orgaos;
   }

   public List< String > getCargos() {
      return cargos;
   }

   public void setCargos( List< String > cargos ) {
      this.cargos = cargos;
   }

   public List< String > getAnos() {
      return anos;
   }

   public void setAnos( List< String > anos ) {
      this.anos = anos;
   }

   public List< String > getDisciplinas() {
      return disciplinas;
   }

   public void setDisciplinas( List< String > disciplinas ) {
      this.disciplinas = disciplinas;
   }

   public List< String > getAssuntos() {
      return assuntos;
   }

   public void setAssuntos( List< String > assuntos ) {
      this.assuntos = assuntos;
   }

   //TODO: rever ...
   public String getDescription() {
      String d = "description ...";
      /*
      String d = "";
      d = addStr( d, banca, "TODAS" );
      d = addStr( d, String.valueOf( ano ), "TODOS" );
      d = addStr( d, orgao, "TODOS" );
      d = addStr( d, uf, "TODAS" );
      d = addStr( d, cargo, "TODOS" );
      d = addStr( d, disciplina, "TODAS" );
      d = addStr( d, assunto, "TODOS" );
      */
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