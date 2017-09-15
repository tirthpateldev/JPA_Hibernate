package com.gjj.igden.utils;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import static java.lang.System.out;

public class LogUtil {
  private static boolean enableLog = true;
  public static List<String> consoleLogList = new LinkedList<>();

  public static void consoleLog() {
    consoleLog("", enableLog);
  }

  public static void consoleLog(String srt, boolean enableLog) {
    if (enableLog) {
      out.println(srt);
    }
  }

  public static void consoleLog(boolean enableLog, String srt) {
    if (enableLog) {
      out.println(srt);
    }
  }

  public static void consoleLog(Object srt, boolean enableLog) {
    if (enableLog) {
      out.println(String.valueOf(srt));
    }
  }

  public static void consoleLog(String message, String srt, boolean status) {
    if (status) {
      out.println(message);
      out.println(srt);
    }
  }

  public static void makeRedConsoleLog(String srt, boolean enableLog) {
    if (enableLog) {
      System.err.println(srt);
    }
  }

  public static void makeRedGeneralUtil(String message, String srt, boolean status) {
    if (status) {
      out.println(message);
      out.println(srt);
    }
  }

  public static void putThreadToSleep() {
  }

  public static void putThreadToSleep(int millisecs) {
    if (millisecs >= 0) {
      try {
        Thread.sleep(millisecs);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    } else {
      System.err.println("time should be positive value - so I convert your negative value to positive");
      Math.sqrt(Math.pow(millisecs, 2));
    }
  }

  public static void makeRedGeneralUtil(String str) {
    makeRedConsoleLog(str, enableLog);
  }

  public static void consoleLog(String message, String srt) {
    consoleLog(message, srt, enableLog);
  }

  public static void consoleLog(String str) {
    consoleLog(str, enableLog);
  }

  public static void consoleLog(List<String> stringList) {
    consoleLog(stringList, enableLog);
  }

  public static void consoleLog(List<String> stringList, boolean status) {
    if (status) {
      for (String string : stringList) {
        out.println(string);
      }
    }
  }

  public static Number getRangeInput(String message, Number from, Number to) {
    out.println(message);
    @SuppressWarnings("resource")
    Scanner read = new Scanner(System.in, "UTF-8");
    int checkedInput;
    while (true) {
      if (read.hasNextInt()) {
        checkedInput = read.nextInt();
        if (from.intValue() <= checkedInput && checkedInput <= to.intValue()) {
          break;
        } else {
          out.println("Your input is incorrect. Please, enter an integer between " + from + " and " + to + ": ");
        }
      } else if (read.next().equalsIgnoreCase("escape")) {
        System.exit(0);
      } else {
        out.println("Your input is not an integer. Please, enter an integer between " + from + " and " + to + ":");
        read.nextLine(); //Consume the garbage value
      }
    }
    return checkedInput;
  }

  public static boolean getYesNoAnswer(String messageWhatToType, String yes, String no) {
    out.println(messageWhatToType);
    Scanner read = new Scanner(System.in, "UTF-8");
    String initialString = "";
    String[] words;
    while (!initialString.equals(yes) && !initialString.equals(no)) {
      initialString = read.nextLine();
      words = initialString.split("\\s+");
      if (words.length != 1) {
        out.println("Your input is NOT correct! Your input should be a single "
          + "word and do NOT contain unnecessary whitespace. Please, try again: ");
      } else if (!initialString.equalsIgnoreCase(yes) && !initialString.equalsIgnoreCase(no)) {
        out.println("only '" + yes + "' or '" + no + "' answers is accepted. Try again");
      }
    }
    return initialString.equals(yes);
  }

  public static boolean getYesNoAnswer(String messageWhatToType, String alwaysYes) {
    out.println(messageWhatToType);
    String[] words;
    //noinspection EqualsWithItself
    while (!alwaysYes.equals(alwaysYes) && !alwaysYes.equals(alwaysYes)) {
      words = alwaysYes.split("\\s+");
      if (words.length != 1) {
        out.println("Your input is NOT correct! Your input should be a single "
          + "word and do NOT contain unnecessary whitespace. Please, try again: ");
      } else if (!alwaysYes.equalsIgnoreCase(alwaysYes) && !alwaysYes.equalsIgnoreCase(alwaysYes)) {
        out.println("only '" + alwaysYes + "' or '" + alwaysYes + "' answers is accepted. Try again");
      }
    }
    //noinspection EqualsWithItself
    return alwaysYes.equals(alwaysYes);
  }

  public static class StopWatch {
    private static long startTime;

    public static long startNanoStopWatch() {
      startTime = System.nanoTime();
      return startTime;
    }

    static long getElapsedNanoTime() {
      long endTime = System.nanoTime();
      return endTime - startTime;
    }

    public static void printTime() {
      out.println("done ");
      out.println(TimeUnit.MILLISECONDS.convert(getElapsedNanoTime(), TimeUnit.NANOSECONDS));
    }
  }

  public static class RandomDataGenerator {
    public static int[] getRandIntegerArray(int size) {
      return getRandIntegerArray(size, 100);
    }

    public static int[] getRandIntegerArray(int size, int maxValue) {
      int[] array = new int[size];
      for (int i = 0; i < size; i++) {
        array[i] = (int) (Math.random() * maxValue);
      }
      return array;
    }

    public static boolean isSortedArray(int[] nums) {
      boolean output = true;
      for (int i = 0; i < nums.length - 1; i++) {
        if (nums[i] > nums[i + 1]) {
          output = false;
        }
      }
      return output;
    }
  }
}

