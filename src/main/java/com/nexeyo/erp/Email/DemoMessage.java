package com.nexeyo.erp.Email;

import lombok.Data;

@Data
public class DemoMessage {
    private String content="<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "  <meta charset=\"UTF-8\">\n" +
            "  <title>Welcome to ERPNEX</title>\n" +
            "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
            "</head>\n" +
            "<body>\n" +
            "  <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px; margin: 0 auto; background-color: #f7f7f7;\">\n" +
            "    <tr>\n" +
            "      <td align=\"center\" valign=\"top\" style=\"padding: 20px;\">\n" +
            "        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"background-color: #ffffff; border-radius: 5px; box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);\">\n" +
            "          <tr>\n" +
            "            <td align=\"center\" style=\"padding: 20px;\">\n" +
            "              <h1 style=\"color: #3366cc;\">Welcome to ERPNEX</h1>\n" +
            "              <p style=\"color: #666;\">Thank you for joining ERPNEX. We're excited to have you on board!</p>\n" +
            "              <p style=\"color: #666;\">Explore our powerful features and streamline your business operations.</p>\n" +
            "              <a href=\"https://erpnex.com/auth/jwt/login?returnTo=%2Fdashboard\" style=\"display: inline-block; padding: 10px 20px; background-color: #3366cc; color: #ffffff; text-decoration: none; border-radius: 3px;\">Get Started</a>\n" +
            "            </td>\n" +
            "          </tr>\n" +
            "        </table>\n" +
            "      </td>\n" +
            "    </tr>\n" +
            "  </table>\n" +
            "</body>\n" +
            "</html>\n";
}
