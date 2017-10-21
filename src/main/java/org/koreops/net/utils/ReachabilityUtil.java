/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.koreops.net.utils;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Utility class pertaining to host reachability.
 *
 * @author Sudipto Sarkar (k0r0pt) (sudiptosarkar@visioplanet.org)
 */
public class ReachabilityUtil {
  private static final String numPacketsOption;
  private static final String timeoutOption;
  private static final Logger logger = Logger.getLogger(ReachabilityUtil.class.getName());

  static {
    String osName = System.getProperty("os.name").toLowerCase();
    // Printing OS name to debug Travis CI issue.
    System.out.println("OS Name: " + osName);
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
      Process p1 = Runtime.getRuntime().exec(command);
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
      Process p1 = Runtime.getRuntime().exec(command);
      int returnVal = p1.waitFor();
      reachable = (returnVal == 0);
    } catch (IOException | InterruptedException ex) {
      logger.log(Level.SEVERE, null, ex);
    }
    return reachable;
  }
}
