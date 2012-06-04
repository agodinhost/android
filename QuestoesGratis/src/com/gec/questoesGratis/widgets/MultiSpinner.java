
package com.gec.questoesGratis.widgets;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public final class MultiSpinner extends Spinner implements OnMultiChoiceClickListener, OnCancelListener {

   private List< String >       items;
   private boolean[]            selected;
   private String               allText;
   private MultiSpinnerListener listener;

   public MultiSpinner( Context context ) {
      super( context );
   }

   public MultiSpinner( Context context, AttributeSet attrs ) {
      super( context, attrs );
   }

   public MultiSpinner( Context context, AttributeSet attrs, int defStyle ) {
      super( context, attrs, defStyle );
   }

   @Override
   public void onClick( DialogInterface dialog, int which, boolean isChecked ) {
      selected[ which ] = isChecked;
   }

   @Override
   public void onCancel( DialogInterface dialog ) {

      // refresh text on spinner.
      final StringBuffer b = new StringBuffer();
      boolean anyUnselected = false;
      for( int i = 0; i < items.size(); i++ )
         if( selected[ i ] ) {
            b.append( items.get( i ) );
            b.append( ", " );
         } else {
            anyUnselected = true;
         }

      String text;
      if( anyUnselected ) {
         text = b.toString();
         if( text.length() > 2 )
            text = text.substring( 0, text.length() - 2 );
      } else {
         text = allText;
      }

      ArrayAdapter< String > adapter = new ArrayAdapter< String >( //
            getContext(), //
            android.R.layout.simple_spinner_item, //
            new String[] { text } //
      );
      setAdapter( adapter );

      if( listener != null )
         listener.onItemsSelected( selected );
   }

   @Override
   public boolean performClick() {

      if( items == null || items.size() < 1 )
         return false;

      AlertDialog.Builder builder = new AlertDialog.Builder( getContext() );
      builder.setMultiChoiceItems( items.toArray( new CharSequence[ items.size() ] ), selected, this );
      builder.setPositiveButton( android.R.string.ok, new DialogInterface.OnClickListener() {

         @Override
         public void onClick( DialogInterface dialog, int which ) {
            dialog.cancel();
         }
      } );
      builder.setOnCancelListener( this );
      builder.show();
      return true;
   }

   public void setItems( List< String > items, String allText ) {
      setItems( items, allText, null );
   }

   public void setItems( List< String > items, String allText, MultiSpinnerListener listener ) {
      this.items = items;
      this.allText = allText;
      this.listener = listener;

      // all selected by default.
      selected = new boolean[ items.size() ];
      for( int i = 0; i < selected.length; i++ )
         selected[ i ] = true;

      // all text on the spinner.
      ArrayAdapter< String > adapter = new ArrayAdapter< String >( //
            getContext(), //
            android.R.layout.simple_spinner_item, //
            new String[] { allText } //
      );
      setAdapter( adapter );
   }

   /**
    * Return a list with all selected items OR:
    * null - if ALL items were selected;
    * empty list - if no items were selected;
    * normal list - normal selection.
    */
   public List< String > getSelectedItems() {

      final int sl = selected.length;
      final List< String > list = new ArrayList< String >( sl );

      for( int i = 0; i < sl; i++ )
         if( selected[ i ] )
            list.add( items.get( i ) );

      if( list.size() == sl )
         return null;

      return list;
   }

   public interface MultiSpinnerListener {

      public void onItemsSelected( boolean[] selected );
   }
}