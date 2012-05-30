import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOCase;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.IOFileFilter;

/**
 * SICOBE Parse Sicobe logs.
 * 
 * The code here is a discussion in technical fora. You may study the code, any
 * use beyond study is not acceptable under reasonable risk management and would
 * be non-compliance Section 404 Sarbanes-Oxley Act.
 */
public class QGImport {

   static Pattern          ptBeginline       = Pattern.compile("\\d+\\Q-\\E\\d+\\Q-\\E\\d+\\s+\\d+:\\d+:\\d+\\Q,\\E\\d+\\s+");

   static Pattern          ptNextedException = Pattern.compile("\\d+\\Qnested exception is:\\E");

   static FileOutputStream fos;

   static final String     dp                = "\\" + new Character('"');

   private static String getNext(Scanner scanner) {
      String out = scanner.hasNext() ? scanner.next() : "";
      if (out == null) {
         out = "";
      }
      out = out.replaceAll("\\n", "");
      out = out.replaceAll(dp, "'");
      out = out.trim();
      return out;
   }

   private static String getNextLine(Scanner scanner) {
      String out = scanner.hasNext() ? scanner.nextLine() : "";
      if (out == null) {
         out = "";
      }
      out = out.replaceAll("\\n", "");
      out = out.replaceAll(dp, "'");
      out = out.trim();
      return out;
   }

   private static void save(String level, String clazz, String clazzMessage, String exception, String exceptionMessage, String at) throws IOException {
      // write our file.
      fos.write('"');
      fos.write(level.getBytes());
      fos.write("\",\"".getBytes());
      fos.write(clazz.getBytes());
      fos.write("\",\"".getBytes());
      fos.write(clazzMessage.getBytes());
      fos.write("\",\"".getBytes());
      fos.write(exception.getBytes());
      fos.write("\",\"".getBytes());
      fos.write(exceptionMessage.getBytes());
      fos.write("\",\"".getBytes());
      fos.write(at.getBytes());
      fos.write('\"');
      fos.write("\n".getBytes());
   }

   private static String getNext(Scanner scan, String lookup) {
      String message = "";
      while (scan.hasNext() && message.equals("")) {
         String entry = getNext(scan);
         if (entry.contains(lookup)) {
            message = entry;
         } else {
            Matcher m1 = ptBeginline.matcher(entry);
            if (m1.find()) {
               message = entry;
            }
         }
      }
      return message;
   }

   private static void parse(Scanner scan, String line) throws IOException {

      Matcher m1 = ptBeginline.matcher(line);
      if (m1.find()) {
         Scanner scLine = new Scanner(line);
         String date = getNext(scLine);
         String time = getNext(scLine);
         String thread = getNext(scLine);
         String level = getNext(scLine);
         String clazz = getNext(scLine);
         String divider = getNext(scLine);
         String clazzMessage = getNextLine(scLine);
         scLine.close();

         String line2 = getNext(scan);
         Matcher m2 = ptBeginline.matcher(line2);
         if (m2.find()) {
            if ("ERROR".equalsIgnoreCase(level)) {
               clazzMessage = clazzMessage.replaceAll(":", "");
               save(level, clazz, "", clazzMessage, "", "");
            } else {
               save(level, clazz, clazzMessage, "", "", "");
            }
            parse(scan, line2);
         } else {
            Scanner scLine2 = new Scanner(line2);
            String exception = getNext(scLine2);
            exception = exception.replaceAll(":", "");
            String exceptionMessage = getNextLine(scLine2);
            scLine2.close();

            String line3 = getNext(scan, "com.sicpa.tt009");

            Matcher m3 = ptBeginline.matcher(line3);
            if (m3.find()) {
               save(level, clazz, clazzMessage, exception, exceptionMessage, "");
               parse(scan, line3);
            } else {
               Scanner scLine3 = new Scanner(line3);
               String atWord = getNext(scLine3);
               String at = getNextLine(scLine3);
               scLine3.close();

               save(level, clazz, clazzMessage, exception, exceptionMessage, at);
            }
         }
      }
   }

   // replace all LF with </br>
   private static String replaceLineFeed(String text){
      return null;
   }
   
   
   private static void parse(File file) throws IOException {
      try {
         Scanner scan = new Scanner(file);
         scan.useDelimiter("\n");
         while (scan.hasNext()) {
            String line = scan.next();
            parse(scan, line);
         }
         scan.close();
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      }
   }

   private static IOFileFilter createFilter(String prefix, String suffix) {
      IOFileFilter filter = FileFilterUtils.and( //
            FileFilterUtils.prefixFileFilter(prefix, IOCase.INSENSITIVE));
      // FileFilterUtils.suffixFileFilter(suffix, IOCase.INSENSITIVE));
      return filter;
   }

   /**
    * args: prefix suffix inputFolder outputFile.
    * 
    * localhost log teste.txt "C:\\Documents and Settings\\AGodinho\\Desktop\\logs\\sicobe-logs"
    * 
    * @throws IOException
    */
   public static void main(String[] args) throws IOException {

      if (args.length < 3) {
         System.err.println("java QGImport inputFolder prefix suffix outputFolder");
         System.exit(0);
      }

      final File inputFolder = new File(args[3]);
      if (!inputFolder.exists() || !inputFolder.isDirectory()) {
         throw new RuntimeException("Invalid input folder.");
      }

      final IOFileFilter filter = createFilter(args[0], args[1]);
      if (filter == null) {
         throw new RuntimeException("Invalid wildcard.");
      }

      // create the output file;
      File outputFile = new File(inputFolder, args[2]);
      fos = new FileOutputStream(outputFile);

      final Collection<File> files = FileUtils.listFiles(inputFolder, filter, null);
      for (File file : files) {
         System.out.print("Parsing file: ");
         System.out.print(file.getAbsolutePath());
         System.out.print(" ...");
         parse(file);
         System.out.println(" DONE.");
      }

      fos.flush();
      fos.close();
   }
}