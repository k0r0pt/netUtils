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

import org.apache.commons.codec.binary.Base64;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test class for Credentials.
 *
 * @author Sudipto Sarkar (k0r0pt) (sudiptosarkar@visioplanet.org).
 * @since 14 Oct, 2017 7:05 PM
 */
public class CredentialsTest {
  private final String username = "someUser";
  private final String password = "superPass";

  @Test
  public void testGetters() {
    Credentials credentials = new Credentials(username, password);
    Assert.assertEquals(username, credentials.getUsername());
    Assert.assertEquals(password, credentials.getPassword());
  }

  @Test
  public void testGetBase64Login() {
    Credentials credentials = new Credentials(username, password);
    Assert.assertEquals(new String(Base64.encodeBase64((this.username + ":" + this.password).getBytes())), credentials.getBase64Login());
  }

  @Test
  public void testSomething() {
    Credentials credentials = new Credentials(username, password);
    UsernamePasswordCredentials usernamePasswordCredentials = new UsernamePasswordCredentials(username, password);
    Assert.assertEquals(usernamePasswordCredentials, credentials.getUsernamePasswordCredentials());
  }

  @Test
  public void testEqualsAllNull() {
    Credentials credentials = new Credentials(null, null);
    Assert.assertTrue(credentials.equals(credentials));
  }

  @Test
  public void testEqualsNullUsername() {
    Credentials credentials = new Credentials(null, password);
    Assert.assertTrue(credentials.equals(credentials));
  }

  @Test
  public void testEqualsNullPassword() {
    Credentials credentials = new Credentials(username, null);
    Assert.assertTrue(credentials.equals(credentials));
  }

  @Test
  public void testEquals() {
    Credentials credentials = new Credentials(username, password);
    Assert.assertTrue(credentials.equals(credentials));
  }

  @Test
  public void testEqualsNonCredential() {
    Credentials credentials = new Credentials(username, password);
    Assert.assertFalse(credentials.equals("Hello There!"));
  }

  @Test
  public void testEqualsWithNull() {
    Credentials credentials = new Credentials(username, password);
    Assert.assertFalse(credentials.equals(null));
  }
}
