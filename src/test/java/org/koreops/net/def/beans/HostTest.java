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

package org.koreops.net.def.beans;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test class for Host.
 *
 * @author Sudipto Sarkar (k0r0pt) (sudiptosarkar@visioplanet.org).
 * @since 14 Oct, 2017 7:02 PM
 */
public class HostTest {

  @Test
  public void testGetters() {
    String ip = "8.8.8.8";
    boolean formAuth = false;
    Host host = new Host(ip, formAuth);
    Assert.assertEquals(ip, host.getIp());
    Assert.assertEquals(formAuth, host.isFormAuth());
  }
}
