
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
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.gec.questoesGratis.model.Filter;
import com.gec.questoesGratis.tools.ActivityX;
import com.gec.questoesGratis.widgets.MultiSpinner;

/**
 * Activity / View to create a new quiz.
 */
public final class CreateActivity extends Activity implements OnSeekBarChangeListener {

   private static final ApplicationX xApp        = ApplicationX.getInstance();
   private static final String       ALL_MALE    = xApp.getString( R.string.g_ALL_MALE );
   private static final String       ALL_FEMALE  = xApp.getString( R.string.g_ALL_FEMALE );
   private static final String       CREATE_WAIT = xApp.getString( R.string.create_wait );

   private Handler                   handler;
   private ProgressDialog            pDialog;

   @Override
   public void onCreate( Bundle savedInstanceState ) {
      super.onCreate( savedInstanceState );
      setContentView( R.layout.create );
      new ActivityX( this ).setupActionBar( getString( R.string.g_APP_NAME ) );

      final SeekBar seekbar = (SeekBar) findViewById( R.id.create_total );
      seekbar.setOnSeekBarChangeListener( this );

      setItems( R.id.create_ms_banca, xApp.getBancas(), ALL_FEMALE );
      setItems( R.id.create_ms_ano, xApp.getAnos(), ALL_MALE );
      setItems( R.id.create_ms_orgao, xApp.getOrgaos(), ALL_MALE );
      setItems( R.id.create_ms_uf, xApp.getUFs(), ALL_FEMALE );
      setItems( R.id.create_ms_cargo, xApp.getCargos(), ALL_MALE );
      setItems( R.id.create_ms_disciplina, xApp.getDisciplinas(), ALL_FEMALE );
      setItems( R.id.create_ms_assunto, xApp.getAssuntos(), ALL_MALE );
   }

   @Override
   protected void onDestroy() {
      super.onDestroy();
   }

   public void onClick_PREVIOUS( View view ) {
      startActivity( new Intent( this, MenuActivity.class ) );
   }

   public void onClick_NEXT( View view ) {
      handler = new Handler();
      pDialog = ProgressDialog.show( this, null, CREATE_WAIT, true );
      new Thread( onClick_next ).start();
   }

   private void setItems( int viewId, List< String > options, String allText ) {
      if( options != null && options.size() > 0 ) {
         final MultiSpinner ms = (MultiSpinner) findViewById( viewId );
         ms.setItems( options, allText );
      }
   }

   private Filter getFilter() {
      final Filter filter = new Filter();
      filter.setTotal( getProgress( R.id.create_total ) );
      filter.setBancas( getSelectedItems( R.id.create_ms_banca ) );
      filter.setAnos( getSelectedItems( R.id.create_ms_ano ) );
      filter.setOrgaos( getSelectedItems( R.id.create_ms_orgao ) );
      filter.setUFs( getSelectedItems( R.id.create_ms_uf ) );
      filter.setCargos( getSelectedItems( R.id.create_ms_cargo ) );
      filter.setDisciplinas( getSelectedItems( R.id.create_ms_disciplina ) );
      filter.setAssuntos( getSelectedItems( R.id.create_ms_assunto ) );
      return filter;
   }

   private int getProgress( int viewId ) {
      final SeekBar sb = (SeekBar) findViewById( viewId );
      return sb.getProgress();
   }

   private List< String > getSelectedItems( int viewId ) {
      final MultiSpinner ms = (MultiSpinner) findViewById( viewId );
      return ms.getSelectedItems();
   }

   @Override
   public void onProgressChanged( SeekBar sb, int progress, boolean fromUser ) {
      // TODO: update progress value ...
   }

   @Override
   public void onStartTrackingTouch( SeekBar sb ) {
      // Nothing.
   }

   @Override
   public void onStopTrackingTouch( SeekBar sb ) {
      // Nothing.
   }

   /* @formatter:off */
   private void alert( Integer stringId ) {
      final AlertDialog dialog = new AlertDialog //
         .Builder( CreateActivity.this ) //
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

   private final Runnable noData = new Runnable() { //
      @Override
      public void run() {
         alert( R.string.create_no_data );
      }
   };
   
   private final Runnable onClick_next = new Runnable() { //
      @Override
      public void run() {
         xApp.setFilter( getFilter() );
         if( xApp.getQuestionsCount() > 0 ) {
            xApp.createQuiz( );
            pDialog.dismiss();
            startActivity( new Intent( CreateActivity.this, AnswerPagerActivity.class ) );
         } else {
            pDialog.dismiss();
            handler.post( noData );
         }
      }
   };
   /* @formatter:on */
}