
package com.gec.questoesGratis;

import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.gec.questoesGratis.tools.ActivityHelper;
import com.gec.questoesGratis.widgets.MultiSpinner;

/**
 * Activity / View to create a new quiz.
 */
public class FilterActivity extends Activity {

   private ApplicationX xApp;

   @Override
   public void onCreate( Bundle savedInstanceState ) {
      super.onCreate( savedInstanceState );
      setContentView( R.layout.filter );
      new ActivityHelper( this ).setupActionBar( getString( R.string.app_name ) );

      xApp = (ApplicationX) getApplication();

      final String TODOS = getString( R.string.g_all_M );
      final String TODAS = getString( R.string.g_all_W );

      setItems( R.string.filter_uf_options, R.id.filter_ms_uf, TODOS );
      setItems( R.string.filter_banca_options, R.id.filter_ms_banca, TODAS );
      setItems( R.string.filter_orgao_options, R.id.filter_ms_orgao, TODOS );
      setItems( R.string.filter_cargo_options, R.id.filter_ms_cargo, TODOS );
      setItems( R.string.filter_ano_options, R.id.filter_ms_ano, TODOS );
      setItems( R.string.filter_disciplina_options, R.id.filter_ms_disciplina, TODAS );
      setItems( R.string.filter_assunto_options, R.id.filter_ms_assunto, TODOS );
   }

   @Override
   protected void onDestroy() {
      super.onDestroy();
   }

   public void onClick_previous( View view ) {
      Intent intent = new Intent( FilterActivity.this, MenuActivity.class );
      startActivity( intent );
   }

   public void onClick_next( View view ) {

      // TODO: checar os resultados do filtro, pode não retornar nada ...

      xApp.setQuiz( xApp.getQuizFromDB( 1 ) );

      Intent intent = new Intent( FilterActivity.this, PagerActivity.class );
      startActivity( intent );
   }

   public void setItems( int stringId, int viewId, String allText ) {

      List< String > options = getListString( stringId );
      if( options != null && options.size() > 0 ) {
         MultiSpinner ms = (MultiSpinner) findViewById( viewId );
         ms.setItems( options, allText );
      }
   }

   public String[] getArrayString( int stringId, String delimiter ) {

      final String str = getString( stringId );
      final String[] options;
      if( str != null && str.length() > 0 ) {
         options = str.split( delimiter );
      } else {
         options = null;
      }
      return options;
   }

   public String[] getArrayString( int stringId ) {
      return getArrayString( stringId, P_DELIMITER );
   }

   public List< String > getListString( int stringId, String delimiter ) {

      String[] a = getArrayString( stringId, delimiter );
      List< String > l = Arrays.asList( a );
      return l;
   }

   public List< String > getListString( int stringId ) {
      return getListString( stringId, P_DELIMITER );
   }

   private static final String P_DELIMITER = "\\|";

}