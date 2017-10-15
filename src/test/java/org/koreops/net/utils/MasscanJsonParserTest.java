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

import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

/**
 * Test class for MasscanJsonParser.
 *
 * @author Sudipto Sarkar (k0r0pt) (sudiptosarkar@visioplanet.org).
 * @since 14 Oct, 2017 7:15 PM
 */
public class MasscanJsonParserTest {

  @Test
  public void testParseHosts() throws Exception {
    final String hostsFile = "src/test/resources/sampleMasscan.json";
    final File file = new File(hostsFile);
    final String port = "80";
    BufferedReader reader = new BufferedReader(new FileReader(file));
    Set<String> hosts = new HashSet<>();
    final String lineStarter = "{   \"ip\": \"";
    String line;
    while ((line = reader.readLine()) != null) {
      if (line.startsWith(lineStarter) && line.contains("\"ports\": [ {\"port\": " + port + ",")) {
        line = line.substring(lineStarter.length());
        hosts.add(line.substring(0, line.indexOf("\"")));
      }
    }
    reader.close();

    Assert.assertArrayEquals(hosts.toArray(new String[]{}), MasscanJsonParser.parseHosts(hostsFile, port));
  }
}
