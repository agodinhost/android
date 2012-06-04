package com.gec.questoesGratis.model;

import java.util.List;

/**
 * Filter TO.
 * 
 * @author agodinho
 */
public final class Filter {

   public static enum Ignore {
      NONE, //
      ANSWERED, //
      RIGHT, //
      WRONG;
   }

   private Integer        total;
   private Ignore         ignore;

   private List< String > bancas;
   private List< String > anos;
   private List< String > orgaos;
   private List< String > ufs;
   private List< String > cargos;
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

   public List< String > getBancas() {
      return bancas;
   }

   public void setBancas( List< String > bancas ) {
      this.bancas = bancas;
   }

   public List< String > getAnos() {
      return anos;
   }

   public void setAnos( List< String > anos ) {
      this.anos = anos;
   }

   public List< String > getOrgaos() {
      return orgaos;
   }

   public void setOrgaos( List< String > orgaos ) {
      this.orgaos = orgaos;
   }

   public List< String > getUFs() {
      return ufs;
   }

   public void setUFs( List< String > ufs ) {
      this.ufs = ufs;
   }

   public List< String > getCargos() {
      return cargos;
   }

   public void setCargos( List< String > cargos ) {
      this.cargos = cargos;
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

   public String getDescription() {
      final StringBuffer b = new StringBuffer();
      addStr( b, bancas, "Bancas", "TODAS" );
      addStr( b, anos, "Anos", "TODOS" );
      addStr( b, orgaos, "Orgãos", "TODOS" );
      addStr( b, ufs, "UFs", "TODAS" );
      addStr( b, cargos, "Cargos", "TODOS" );
      addStr( b, disciplinas, "Disciplinas", "TODAS" );
      addStr( b, assuntos, "Assuntos", "TODOS" );
      return b.toString();
   }

   private static void addStr( StringBuffer b, List< String > list, String title, String all ) {
      if( b.length() > 0 )
         b.append( " - " );
      b.append( title ).append( ": " );
      if( list == null )
         b.append( all );
      else
         b.append( addList( list ) );
   }

   private static StringBuffer addList( List< String > list ) {
      final StringBuffer b = new StringBuffer();
      for( String string: list ) {
         if( b.length() > 0 )
            b.append( "," );
         b.append( string );
      }
      return b;
   }
}