
package com.gec.questoesGratis.tools;

import java.text.MessageFormat;

import android.util.Log;

/**
 * Android Log X helper class.
 * 
 * @author agodinho
 */
public final class LogX {

   private final String  tag;
   private final boolean debug;
   private final boolean error;
   private final boolean info;
   private final boolean warn;
   private final boolean verbose;

   /**
    * Constructor.
    * @param clazz the class tag name.
    */
   public LogX( Class< ? > clazz ) {
      tag = clazz.getSimpleName();
      debug = Log.isLoggable( tag, Log.DEBUG );
      error = Log.isLoggable( tag, Log.ERROR );
      info = Log.isLoggable( tag, Log.INFO );
      warn = Log.isLoggable( tag, Log.WARN );
      verbose = Log.isLoggable( tag, Log.VERBOSE );
   }

   /**
    * Log debug message.
    * @param message the debug message to log.
    * @param args optional message format arguments to apply in the message.
    */
   public void d( String message, Object... args ) {

      if( debug ) {
         if( args == null ) {
            Log.d( tag, message );
         } else {
            String format = MessageFormat.format( message, args );
            Log.d( tag, format );
         }
      }
   }

   /**
    * Log debug message.
    * @param t the throwable to log.
    * @param message the debug message to log.
    * @param args optional message format arguments to apply in the message.
    */
   public void d( Throwable t, String message, Object... args ) {

      if( debug ) {
         if( args == null ) {
            Log.d( tag, message, t );
         } else {
            String format = MessageFormat.format( message, args );
            Log.d( tag, format, t );
         }
      }
   }

   /**
    * Log debug message.
    * @param t the throwable to log.
    */
   public void d( Throwable t ) {

      if( debug ) {
         Log.d( tag, null, t );
      }
   }

   /**
    * Log error message.
    * @param message the error message to log.
    * @param args optional message format arguments to apply in the message.
    */
   public void e( String message, Object... args ) {

      if( error ) {
         if( args == null ) {
            Log.e( tag, message );
         } else {
            String format = MessageFormat.format( message, args );
            Log.e( tag, format );
         }
      }
   }

   /**
    * Log error message.
    * @param t the throwable to log.
    * @param message the error message to log.
    * @param args optional message format arguments to apply in the message.
    */
   public void e( Throwable t, String message, Object... args ) {

      if( error ) {
         if( args == null ) {
            Log.e( tag, message, t );
         } else {
            String format = MessageFormat.format( message, args );
            Log.e( tag, format, t );
         }
      }
   }

   /**
    * Log error message.
    * @param t the throwable to log.
    */
   public void e( Throwable t ) {

      if( error ) {
         Log.e( tag, null, t );
      }
   }

   /**
    * Log info message.
    * @param message the info message to log.
    * @param args optional message format arguments to apply in the message.
    */
   public void i( String message, Object... args ) {

      if( info ) {
         if( args == null ) {
            Log.i( tag, message );
         } else {
            String format = MessageFormat.format( message, args );
            Log.i( tag, format );
         }
      }
   }

   /**
    * Log info message.
    * @param t the throwable to log.
    * @param message the info message to log.
    * @param args optional message format arguments to apply in the message.
    */
   public void i( Throwable t, String message, Object... args ) {

      if( info ) {
         if( args == null ) {
            Log.i( tag, message, t );
         } else {
            String format = MessageFormat.format( message, args );
            Log.i( tag, format, t );
         }
      }
   }

   /**
    * Log info message.
    * @param t the throwable to log.
    */
   public void i( Throwable t ) {

      if( info ) {
         Log.i( tag, null, t );
      }
   }

   /**
    * Log warning message.
    * @param message the warning message to log.
    * @param args optional message format arguments to apply in the message.
    */
   public void w( String message, Object... args ) {

      if( warn ) {
         if( args == null ) {
            Log.w( tag, message );
         } else {
            String format = MessageFormat.format( message, args );
            Log.w( tag, format );
         }
      }
   }

   /**
    * Log warning message.
    * @param t the throwable to log.
    * @param message the warning message to log.
    * @param args optional message format arguments to apply in the message.
    */
   public void w( Throwable t, String message, Object... args ) {

      if( warn ) {
         if( args == null ) {
            Log.w( tag, message, t );
         } else {
            String format = MessageFormat.format( message, args );
            Log.w( tag, format, t );
         }
      }
   }

   /**
    * Log warning message.
    * @param t the throwable to log.
    */
   public void w( Throwable t ) {

      if( warn ) {
         Log.w( tag, null, t );
      }
   }

   /**
    * Log verbose message.
    * @param message the verbose message to log.
    * @param args optional message format arguments to apply in the message.
    */
   public void v( String message, Object... args ) {

      if( verbose ) {
         if( args == null ) {
            Log.v( tag, message );
         } else {
            String format = MessageFormat.format( message, args );
            Log.v( tag, format );
         }
      }
   }

   /**
    * Log verbose message.
    * @param t the throwable to log.
    * @param message the verbose message to log.
    * @param args optional message format arguments to apply in the message.
    */
   public void v( Throwable t, String message, Object... args ) {

      if( verbose ) {
         if( args == null ) {
            Log.v( tag, message, t );
         } else {
            String format = MessageFormat.format( message, args );
            Log.v( tag, format, t );
         }
      }
   }

   /**
    * Log verbose message.
    * @param t the throwable to log.
    */
   public void v( Throwable t ) {

      if( verbose ) {
         Log.v( tag, null, t );
      }
   }
}