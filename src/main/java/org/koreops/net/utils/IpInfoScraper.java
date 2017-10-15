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

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.koreops.net.exceptions.NetException;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Scraper utility for ipinfo.io.
 *
 * @author Sudipto Sarkar (k0r0pt) (sudiptosarkar@visioplanet.org).
 * @since 14 Oct, 2017 1:24 PM
 */
public class IpInfoScraper {

  private static final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_45);
  private static final Logger logger = Logger.getLogger(IpInfoScraper.class.getName());
  private static final String host = "http://ipinfo.io/";

  static {
    webClient.getOptions().setThrowExceptionOnFailingStatusCode(true);
    webClient.getOptions().setThrowExceptionOnScriptError(false);
  }

  private final String netId;

  public IpInfoScraper(String netId) {
    this.netId = netId;
  }

  /**
   * Gets the actual CIDR IP ranges.
   *
   * @return Array of String IP addresses
   */
  public String[] getNetRanges() throws NetException {
    String[] args;
    int netSize = 0;
    final String sizeKeyword = "Show all ";
    try {
      HtmlPage page = webClient.getPage(host + netId);
      for (HtmlAnchor anchor : page.getAnchors()) {
        String anchorText = anchor.getTextContent();
        if (anchorText.contains(sizeKeyword)) {
          anchorText = anchorText.substring(anchorText.indexOf(sizeKeyword) + sizeKeyword.length(), anchorText.length());
          netSize = Integer.valueOf(anchorText.substring(0, anchorText.indexOf(" ")));
        }
      }
      //page.getAnchorByName("Show all " + netSize + " IP address blocks").click();
      Document doc = Jsoup.parse(page.asXml());
      Elements es = doc.select("table#block-table tr td a");
      int i = 0;
      args = new String[netSize + 1];
      for (Element e : es) {
        if (e.attr("href").contains(netId)) {
          args[i] = e.text();
          i++;
        }
      }
    } catch (IOException | FailingHttpStatusCodeException | ElementNotFoundException e) {
      logger.log(Level.SEVERE, "Invalid netId", e);
      throw new NetException("Invalid netId: " + e.getMessage());
    }
    return args;
  }
}