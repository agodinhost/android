package com.gec.questoesGratis.tools;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.pm.PackageManager.NameNotFoundException;

import com.gec.questoesGratis.tools.PreferencesX.PrefType;

/**
 * Utility functions.
 */
public final class DialogX {

   private static final SimpleDateFormat sdf         = new SimpleDateFormat( "yyyy-MM-dd'T'hh:ss:mmZ" );

   private static String                 version     = null;
   private static int                    versionCode = -1;

   /**
    * Formats date in more friendly way from server response.
    */
   public static String formatDate( String aeDate ) {
      // Remove the colon, because it's not ISO complaint.
      int lastColon = aeDate.lastIndexOf( ":" );
      aeDate = aeDate.substring( 0, lastColon - 1 ) + aeDate.substring( lastColon + 1, aeDate.length() );
      try {
         Date date = sdf.parse( aeDate );
         return DateFormat.getDateTimeInstance().format( date );
      } catch( ParseException e ) {
         return null;
      }
   }

   public static String getVersion( Context context ) {
      if( version == null )
         updateVersion( context );
      return version;
   }

   public static int getVersionCode( Context context ) {
      if( versionCode == -1 )
         updateVersion( context );
      return versionCode;
   }

   public static void updateVersion( Context context ) {
      try {
         version = context.getPackageManager().getPackageInfo( context.getPackageName(), 0 ).versionName;
         versionCode = context.getPackageManager().getPackageInfo( context.getPackageName(), 0 ).versionCode;
      } catch( NameNotFoundException e ) {
         version = "Unknown";
         versionCode = -1;
      }
   }

   /**
    * Extracts LDAP username from user email.
    */
   public static String getLdap( Context context ) {
      return PreferencesX.getString( PrefType.USER_EMAIL, context ).split( "@" )[ 0 ];
   }

   /**
    * Returns google account registered on this phone or null if account not
    * present.
    */
   public static String getGoogleAccount( Context context ) {
      final AccountManager manager = AccountManager.get( context );
      final Account[] accounts = manager.getAccountsByType( "com.google" );
      String accountEmail = null;
      for( Account account: accounts ) {
         accountEmail = account.name;
      }
      return accountEmail;
   }

   public static void alert( Context context, Integer stringId ) {

      confirmOnClick2 = null;
      new AlertDialog.Builder( context ) //
            .setMessage( stringId ) //
            .setPositiveButton( android.R.string.ok, onClickYes ) //
            .create() //
            .show();
   }

   public static void confirm( Context context, OnClickListener confirmOnClickP, Integer stringId ) {

      confirmOnClick2 = confirmOnClickP;
      new AlertDialog.Builder( context ) //
            .setCancelable( false ) //
            .setIcon( android.R.drawable.ic_dialog_alert ) //
            .setMessage( stringId ) //
            .setPositiveButton( android.R.string.yes, onClickYes ) //
            .setNegativeButton( android.R.string.no, onClickNo ) //
            .create() //
            .show();
   }

   /* @formatter:off */

   private static OnClickListener confirmOnClick2;
   
   private static final OnClickListener onClickYes = new OnClickListener() {
      @Override
      public void onClick( DialogInterface dialog, int which ) {
         dialog.dismiss();
         if( confirmOnClick2 != null )
            confirmOnClick2.onClick( dialog, android.R.string.yes );
      }
   };

   private static final OnClickListener onClickNo = new OnClickListener() {
      @Override
      public void onClick( DialogInterface dialog, int which ) {
         dialog.dismiss();
         if( confirmOnClick2 != null )
            confirmOnClick2.onClick( dialog, android.R.string.no );
      }
   };

   /* @formatter:on */

}