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

/**
 * Stores the login credentials for a host.
 *
 * @author Sudipto Sarkar (k0r0pt) (sudiptosarkar@visioplanet.org).
 * @since 26 Sep, 2017 8:09 PM
 */
public class Credentials extends Object {
  private String username;
  private String password;

  /**
   * Constructor for Credentials.
   *
   * @param username The username needed to login to the host
   * @param password The password needed to login to the host
   */
  public Credentials(String username, String password) {
    this.username = username;
    this.password = password;
  }

  /**
   * Getter for username.
   *
   * @return The username needed to login to the host
   */
  public String getUsername() {
    return username;
  }

  /**
   * Getter for password.
   *
   * @return The password needed to login to the host
   */
  public String getPassword() {
    return password;
  }

  /**
   * Returns a UsernamePasswordCredentials (apache http client) instance created from username and password to be used during logins.
   *
   * @return a UsernamePasswordCredentials (apache http client) instance to be used during logins
   */
  public UsernamePasswordCredentials getUsernamePasswordCredentials() {
    return new UsernamePasswordCredentials(this.username, this.password);
  }

  /**
   * Returns a base64 encoded HTTP Basic Authentication String, to be used when passing the credentials as HTTP Basic Authentication credentials.
   *
   * @return A Base64 encoded HTTP Basic Authentication String
   */
  public String getBase64Login() {
    return new String(Base64.encodeBase64((this.username + ":" + this.password).getBytes()));
  }

  @Override
  public boolean equals(Object credentials) {
    if ((credentials == null) || !(credentials instanceof Credentials)) {
      return false;
    }

    if (this.username == null && ((Credentials) credentials).username == null
        && this.password == null && ((Credentials) credentials).password == null) {
      return true;
    } else if (this.username == null && ((Credentials) credentials).username == null && this.password.equals(((Credentials) credentials).password)) {
      return true;
    } else if (this.username.equals(((Credentials) credentials).username) && this.password == null && ((Credentials) credentials).password == null) {
      return  true;
    }

    return (this.username.equals(((Credentials) credentials).username) && this.password.equals(((Credentials) credentials).password));
  }
}
