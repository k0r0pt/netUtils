package org.koreops.net.utils;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Utility class pertaining to host reachability.
 *
 * @author Xtreme (k0r0pt) (sudipto.sarkar@visioplanet.org)
 */
public class ReachabilityUtil {
  private static final String numPacketsOption;
  private static final String timeoutOption;
  private static final Logger logger = Logger.getLogger(ReachabilityUtil.class.getName());

  static {
    String osName = System.getProperty("os.name").toLowerCase();
    if (osName.contains("win")) {
      // It's windows.
      numPacketsOption = "-n 1";
      timeoutOption = "-t 3";
    } else if (osName.contains("nux") || osName.contains("bsd") || osName.contains("sunos") || osName.contains("nix") || osName.contains("aix")) {
      numPacketsOption = "-c 1";
      timeoutOption = "-W 3";
    } else if (osName.contains("mac")) {
      numPacketsOption = "-c 1";
      timeoutOption = "-t 3";
    } else {
      numPacketsOption = "";
      timeoutOption = "";
    }

    //TODO - Let's not do SunOS for now, because its timeout comes after host (no option. Just plain number)
  }

  /**
   * Determines if Internet is reachable by pinging Google.
   *
   * @return true if Google is reachable. false otherwise (if Internet is not connected)
   */
  public static boolean isReachable() {
    boolean reachable = false;
    String command = "ping " + numPacketsOption + " " + timeoutOption + " www.google.com";
    try {
      // System.out.println("Executing command: " + command);
      Process p1 = Runtime.getRuntime().exec(command);
      Thread.sleep(1000);
      int returnVal = p1.waitFor();
      reachable = (returnVal == 0);
    } catch (IOException | InterruptedException ex) {
      logger.log(Level.SEVERE, null, ex);
    }
    return reachable;
  }

  /**
   * Determines if specified host is reachable by pinging it.
   *
   * @param host The host to be pinged (IP/hostname)
   * @return     true if the host is reachable
   */
  public static boolean isReachable(String host) {
    boolean reachable = false;
    String command = "ping " + numPacketsOption + " " + timeoutOption + " " + host;
    try {
      // System.out.println("Executing command: " + command);
      Process p1 = Runtime.getRuntime().exec(command);
      Thread.sleep(1000);
      int returnVal = p1.waitFor();
      reachable = (returnVal == 0);
    } catch (IOException | InterruptedException ex) {
      logger.log(Level.SEVERE, null, ex);
    }
    System.out.println(host + ": Reachable: " + reachable);
    return reachable;
  }
}
