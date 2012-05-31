
package com.gec.questoesGratis.tools;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;

import com.gec.questoesGratis.tools.Preferences.PrefType;

/**
 * Utility functions, mainly for formatting.
 */
public class Utils {

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

   /**
    * All quiz spreadsheets now contain [Q] to differentiate them in the list.
    */
   public static String formatQuizName( String quizName ) {
      return quizName.replace( "[Q]", "" ).trim();
   }

   /**
    * All polling spreadsheets now contain [P] to differentiate them in the
    * list.
    */
   public static String formatPollingName( String pollingName ) {
      return pollingName.replace( "[P]", "" ).trim();
   }

   public static String getVersion( Context context ) {
      if( version == null ) {
         updateVersion( context );
      }
      return version;
   }

   public static int getVersionCode( Context context ) {
      if( versionCode == -1 ) {
         updateVersion( context );
      }
      return versionCode;
   }

   public static void updateVersion( Context context ) {
      try {
         Utils.version = context.getPackageManager().getPackageInfo( context.getPackageName(), 0 ).versionName;
         Utils.versionCode = context.getPackageManager().getPackageInfo( context.getPackageName(), 0 ).versionCode;
      } catch( NameNotFoundException e ) {
         Utils.version = "Unknown";
         Utils.versionCode = -1;
      }
   }

   /**
    * Extracts LDAP username from user email.
    */
   public static String getLdap( Context context ) {
      return Preferences.getString( PrefType.USER_EMAIL, context ).split( "@" )[ 0 ];
   }

   /**
    * Returns google account registered on this phone or null if account not
    * present.
    */
   public static String getGoogleAccount( Context context ) {
      AccountManager manager = AccountManager.get( context );
      Account[] accounts = manager.getAccountsByType( "com.google" );
      String accountEmail = null;
      for( Account account : accounts ) {
         accountEmail = account.name;
      }
      return accountEmail;
   }
}