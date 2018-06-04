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
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.koreops.net.exceptions.NetException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
    List<String> args;
    try {
      HtmlPage page = webClient.getPage(host + netId);
      Document doc = Jsoup.parse(page.asXml());
      Elements es = doc.select("table#block-table tbody tr td a");
      args = new ArrayList<>();
      for (Element e : es) {
        if (e.attr("href").contains(netId)) {
          args.add(e.text());
        }
      }
    } catch (IOException | FailingHttpStatusCodeException | ElementNotFoundException e) {
      logger.log(Level.SEVERE, "Invalid netId", e);
      throw new NetException("Invalid netId: " + e.getMessage());
    }
    return args.toArray(new String[args.size()]);
  }

  /**
   * Prints masscan command for specified netId, which can be copied and run to get all hosts with ports 80 and 8080 in listen state.
   * @param isp           The isp that is being scanned. If empty, the value `Scan` will be used. This is for the output Json file.
   * @throws NetException In case of any exception related to the scrape.
   */
  public void printMasscanCommand(String isp) throws NetException {
    if (StringUtils.isEmpty(isp)) {
      isp = "Scan";
    }
    StringBuilder ms = new StringBuilder("\n\n\nmasscan -p80,8080 --rate 1000000 --banners  -oJ " + isp + ".json ");
    String[] netRanges = this.getNetRanges();

    for (String ip : netRanges) {
      ms.append(ip).append(" ");
    }

    System.out.println(ms.toString());
  }
}
