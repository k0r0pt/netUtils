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

import java.util.Map;

/**
 * Bean class for storing various parameters that will be used for cracking the authentication.
 *
 * @author Sudipto Sarkar (k0r0pt) (sudiptosarkar@visioplanet.org).
 * @since 26 Sep, 2017 9:06 PM
 */
public class AuthCrackParams {

  private Credentials credentials;
  private Map<String, String> cookies;
  private Map<String, String> headers;
  private Map<String, String> data;

  /**
   * Constructor for AuthCrackParams.
   *
   * @param credentials  The instance of Credentials pertaining to this host
   * @param cookies      The cookies returned by server, that will be needed in requests
   * @param headers      The headers that will be needed in requests
   * @param data         Form data that will be needed in requests
   */
  public AuthCrackParams(Credentials credentials, Map<String, String> cookies, Map<String, String> headers, Map<String, String> data) {
    this.credentials = credentials;
    this.cookies = cookies;
    this.headers = headers;
    this.data = data;
  }

  /**
   * Getter for credentials.
   *
   * @return The Credentials instance for this host
   */
  public Credentials getCredentials() {
    return credentials;
  }

  /**
   * Getter for credentials.
   *
   * @return The cookies that need to be passed in requests
   */
  public Map<String, String> getCookies() {
    return cookies;
  }

  /**
   * Getter for headers.
   *
   * @return The headers that need to be passed in requests
   */
  public Map<String, String> getHeaders() {
    return headers;
  }

  /**
   * Getter for data.
   *
   * @return The form data that need to be passed in requests
   */
  public Map<String, String> getData() {
    return data;
  }
}
