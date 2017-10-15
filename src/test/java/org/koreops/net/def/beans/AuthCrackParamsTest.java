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

import java.util.HashMap;
import java.util.Map;

/**
 * Test class for AuthCrackParams.
 *
 * @author Sudipto Sarkar (k0r0pt) (sudiptosarkar@visioplanet.org).
 * @since 14 Oct, 2017 6:17 PM
 */
public class AuthCrackParamsTest {

  @Test
  public void testGetters() {
    final String username = "username";
    final String password = "password";
    final Map<String, String> cookies = new HashMap<>();
    cookies.put("cookieName", "cookieValue");
    final Map<String, String> headers = new HashMap<>();
    headers.put("headerName", "headerValue");
    final Map<String, String> data = new HashMap<>();
    data.put("dataName", "dataValue");
    Credentials credentials = new Credentials("username", "password");
    AuthCrackParams params = new AuthCrackParams(credentials, cookies, headers, data);
    Assert.assertEquals(credentials, params.getCredentials());
    Assert.assertEquals(cookies, params.getCookies());
    Assert.assertEquals(headers, params.getHeaders());
    Assert.assertEquals(data, params.getData());
  }
}
