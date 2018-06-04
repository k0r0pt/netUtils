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

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * This class parses the MASScan's JSON output file for hosts on specified port.
 *
 * @author Sudipto Sarkar (k0r0pt) (sudiptosarkar@visioplanet.org).
 * @since 24 Sep, 2017 6:41 PM
 */
public interface MasscanJsonParser {

  /**
   * Utility method to parse hosts in a Masscan JSON output file.
   *
   * @param hostsFile      The Masscan JSON output file
   * @param port           The port that needs to be targeted (Only the hosts with that port open will be used)
   * @return               A String array of the hosts with the targeted port open
   * @throws IOException   In case of any problems with reading the JSON file
   */
  static String[] parseHosts(String hostsFile, String port) throws IOException {
    Set<String> hosts = new HashSet<>();
    int portNum = 80;
    if (port != null) {
      portNum = Integer.valueOf(port);
    }
    BufferedReader fileReader = new BufferedReader(new FileReader(hostsFile));
    String line;
    JSONObject portObj;
    while ((line = fileReader.readLine()) != null) {
      if (!line.startsWith("{finished") && StringUtils.isNotEmpty(line) && !"[".equals(line) && !"]".equals(line)) {
        JSONObject object = new JSONObject(line);
        portObj = (JSONObject) object.getJSONArray("ports").get(0);
        if (((portObj.has("port")) && (portNum == portObj.getInt("port")))
            && ((portObj.has("status")) && ("open".equals(portObj.get("status"))))
            && ((portObj.has("proto")) && ("tcp".equals(portObj.get("proto"))))) {
          hosts.add(object.getString("ip"));
        }
      }
    }
    return hosts.toArray(new String[]{});
  }
}
