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
 * Test class for CidrUtils.
 *
 * @author Sudipto Sarkar (k0r0pt) (sudiptosarkar@visioplanet.org).
 * @since 14 Oct, 2017 2:05 PM
 */
public class CidrUtilsTest {

  @Test
  public void testGetNetworkAddress() throws Exception {
    CidrUtils cidrUtils = new CidrUtils("192.168.0.0/24");
    Assert.assertEquals("192.168.0.0", cidrUtils.getNetworkAddress());
  }

  @Test
  public void testGetBroadcastAddress() throws Exception {
    CidrUtils cidrUtils = new CidrUtils("192.168.0.0/24");
    Assert.assertEquals("192.168.0.255", cidrUtils.getBroadcastAddress());
  }

  @Test
  public void testIsInRange() throws Exception {
    CidrUtils cidrUtils = new CidrUtils("192.168.0.0/24");
    Assert.assertTrue(cidrUtils.isInRange("192.168.0.56"));
  }

  @Test
  public void testGetBroadcastAdddressForIpV6Addr() throws Exception {
    CidrUtils cidrUtils = new CidrUtils("1200:0000:AB00:1234:0000:2552:7777:1313/24");
    Assert.assertEquals("1200:ff:ffff:ffff:ffff:ffff:ffff:ffff", cidrUtils.getBroadcastAddress());
  }

  @Test
  public  void testInvalidCidrAddr() {
    try {
      CidrUtils cidrUtils = new CidrUtils("192.168.1.1");
    } catch (Exception e) {
      Assert.assertEquals("not an valid CIDR format!", e.getMessage());
    }
  }
}
