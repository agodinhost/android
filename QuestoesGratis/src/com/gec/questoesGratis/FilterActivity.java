
package com.gec.questoesGratis;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.gec.questoesGratis.dao.DBHelper;
import com.gec.questoesGratis.model.Filter;
import com.gec.questoesGratis.tools.ActivityHelper;
import com.gec.questoesGratis.widgets.MultiSpinner;

/**
 * Activity / View to create a new quiz.
 */
public class FilterActivity extends Activity {

   private ApplicationX   xApp;
   private Handler        handler;
   private ProgressDialog pd;

   @Override
   public void onCreate( Bundle savedInstanceState ) {
      super.onCreate( savedInstanceState );
      setContentView( R.layout.filter );
      new ActivityHelper( this ).setupActionBar( getString( R.string.app_name ) );

      xApp = (ApplicationX) getApplication();
      final DBHelper dbHelper = xApp.getDbHelper();
      final String TODOS = getString( R.string.g_all_M );
      final String TODAS = getString( R.string.g_all_W );

      setItems( R.id.filter_ms_banca, dbHelper.getBancas(), TODAS );
      setItems( R.id.filter_ms_ano, dbHelper.getAnos(), TODOS );
      setItems( R.id.filter_ms_orgao, dbHelper.getOrgaos(), TODOS );
      setItems( R.id.filter_ms_uf, dbHelper.getUFs(), TODAS );
      setItems( R.id.filter_ms_cargo, dbHelper.getCargos(), TODOS );
      setItems( R.id.filter_ms_disciplina, dbHelper.getDisciplinas(), TODAS );
      setItems( R.id.filter_ms_assunto, dbHelper.getAssuntos(), TODOS );
   }

   @Override
   protected void onDestroy() {
      super.onDestroy();
   }

   public void onClick_previous( View view ) {
      Intent intent = new Intent( FilterActivity.this, MenuActivity.class );
      startActivity( intent );
   }

   /* @formatter:off */
   private final Runnable noSelection = new Runnable() { //
      @Override
      public void run() {
         alert( R.string.g_no_selection );
      }
   };
   
   private final Runnable onClick_next = new Runnable() { //
      @Override
      public void run() {

         final DBHelper dbHelper = xApp.getDbHelper();
         final Filter filter = getFilter();
         final int count = dbHelper.getQuestionsCount( filter );
         if( count > 0 ) {
            xApp.setQuiz( dbHelper.createQuiz( filter ) );
            pd.dismiss();

            Intent intent = new Intent( FilterActivity.this, PagerActivity.class );
            startActivity( intent );

         } else {
            pd.dismiss();
            handler.post( noSelection );
         }
      }
   };
   /* @formatter:on */

   public void onClick_next( View view ) {

      handler = new Handler();
      pd = ProgressDialog.show( FilterActivity.this, "", "Aguarde, criando novo questionario ...", true );
      new Thread( onClick_next ).start();
   }

   private void setItems( int viewId, List< String > options, String allText ) {

      if( options != null && options.size() > 0 ) {
         MultiSpinner ms = (MultiSpinner) findViewById( viewId );
         ms.setItems( options, allText );
      }
   }

   //TODO: get from filter.
   private static final int qty = 10;

   private Filter getFilter() {

      Filter filter = new Filter();
      filter.setTotal( qty );
      filter.setBancas( getSelectedItems( R.id.filter_ms_banca ) );
      filter.setAnos( getSelectedItems( R.id.filter_ms_ano ) );
      filter.setOrgaos( getSelectedItems( R.id.filter_ms_orgao ) );
      filter.setUFs( getSelectedItems( R.id.filter_ms_uf ) );
      filter.setCargos( getSelectedItems( R.id.filter_ms_cargo ) );
      filter.setDisciplinas( getSelectedItems( R.id.filter_ms_disciplina ) );
      filter.setAssuntos( getSelectedItems( R.id.filter_ms_assunto ) );
      return filter;
   }

   private List< String > getSelectedItems( int viewId ) {

      MultiSpinner ms = (MultiSpinner) findViewById( viewId );
      return ms.getSelectedItems();
   }

   /* @formatter:off */
   private void alert( Integer stringId ) {

      final AlertDialog dialog = new AlertDialog //
         .Builder( FilterActivity.this ) //
         .setMessage( stringId ) //
         .setPositiveButton( android.R.string.ok, new OnClickListener() {
            @Override
            public void onClick( DialogInterface dialog, int which ) {
               dialog.dismiss(); 
            }
         } ) //
         .create();

      dialog.show();
   }
   /* @formatter:on */

   /*
   @formatter:off
   private void setItems( int viewId, int stringId, String allText ) {

      List< String > options = getListString( stringId );
      if( options != null && options.size() > 0 ) {
         MultiSpinner ms = (MultiSpinner) findViewById( viewId );
         ms.setItems( options, allText );
      }
   }

   private String[] getArrayString( int stringId, String delimiter ) {

      final String str = getString( stringId );
      final String[] options;
      if( str != null && str.length() > 0 ) {
         options = str.split( delimiter );
      } else {
         options = null;
      }
      return options;
   }

   private String[] getArrayString( int stringId ) {
      return getArrayString( stringId, P_DELIMITER );
   }

   private List< String > getListString( int stringId, String delimiter ) {

      String[] a = getArrayString( stringId, delimiter );
      List< String > l = Arrays.asList( a );
      return l;
   }

   private List< String > getListString( int stringId ) {
      return getListString( stringId, P_DELIMITER );
   }

   private static final String P_DELIMITER = "\\|";
   @formatter:on
   */

}