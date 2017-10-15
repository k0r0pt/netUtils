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

/**
 * Bean class for storing Host details.
 *
 * @author Sudipto Sarkar (k0r0pt) (sudiptosarkar@visioplanet.org).
 * @since 25 Sep, 2017 7:33 PM
 */
public class Host {
  private String ip;
  private boolean formAuth;

  /**
   * Constructor for Host definition.
   *
   * @param ip        The IP Address of said host
   * @param formAuth  Specifies if the host has form authentication (or HTTP basic authentication)
   */
  public Host(String ip, boolean formAuth) {
    this.ip = ip;
    this.formAuth = formAuth;
  }

  /**
   * Returns a marker specifying if the host has form authentication or basic authentication.
   *
   * @return true if the host has form authentication, false if it has basic authentication
   */
  public boolean isFormAuth() {
    return formAuth;
  }

  /**
   * Returns the IP address of the host.
   *
   * @return  The IP address of the host.
   */
  public String getIp() {
    return ip;
  }
}
