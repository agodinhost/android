package com.gec.questoesGratis.model;

import java.io.Serializable;
import java.util.List;

/**
 * Filter TO.
 * 
 * @author agodinho
 */
public final class Filter implements Serializable {

   private static final long serialVersionUID = -1326426722493503294L;

   public enum IgnoreQuestions {
      None, //
      AlreadyAnswered, //
      AlreadyAnswered_Right, //
      AlreadyAnswered_Wrong;
   }

   private IgnoreQuestions ignore;

   private List< String >  ufs;
   private List< String >  bancas;
   private List< String >  orgaos;
   private List< String >  cargos;
   private List< String >  anos;
   private List< String >  disciplinas;
   private List< String >  assuntos;

   public IgnoreQuestions getIgnore() {
      return ignore;
   }

   public void setIgnore( IgnoreQuestions ignore ) {
      this.ignore = ignore;
   }

   public List< String > getUfs() {
      return ufs;
   }

   public void setUfs( List< String > ufs ) {
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
}