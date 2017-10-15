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

/**
 * Test class for IpInfoScraper.
 *
 * @author Sudipto Sarkar (k0r0pt) (sudiptosarkar@visioplanet.org).
 * @since 14 Oct, 2017 2:56 PM
 */
public class IpInfoScraperTest {

  // Let's not mock responses from webClient.
  // That way we'll have a pretty good idea that something's wrong
  // when ipinfo changes their page format.

  @Test
  public void testgetNetRanges() throws Exception {
    IpInfoScraper ipInfoScraper = new IpInfoScraper("AS24940");
    Assert.assertNotNull(ipInfoScraper.getNetRanges());
  }

  @Test
  public void testInvalidNetwork() {
    try {
      IpInfoScraper ipInfoScraper = new IpInfoScraper("asdfgh");
      ipInfoScraper.getNetRanges();
    } catch (Exception e) {
      Assert.assertTrue(e.getMessage().startsWith("Invalid netId"));
    }
  }
}
