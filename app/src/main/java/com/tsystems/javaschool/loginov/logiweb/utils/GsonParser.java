package com.tsystems.javaschool.loginov.logiweb.utils;

import com.google.gson.Gson;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Utility class which provides GSON parsing for jTable.
 */
public class GsonParser {
    private static Logger logger = Logger.getLogger(GsonParser.class);
    private Gson gson = new Gson();

    public void parse(Map<String, Object> resultMap, HttpServletResponse resp) throws IOException {

        // Error for JTable: Operation with the database failed ("ERROR" in response)
        if (resultMap.containsKey("jTableError")) {
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            // JSON object for JTable to parse
            String response = "{\"Result\":\"ERROR\",\"Message\":\"" + resultMap.get("jTableError") + "\"}";
            resp.getWriter().write(response);
            logger.info("JSON response = " + response);
            return;
        }

        // JSON for JTable: List of items fetched from the db ("Records" in response)
        if (resultMap.containsKey("data")) {
            Object data = resultMap.get("data");
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            // Create a JSON object for JTable to parse
            String response = "{\"Result\":\"OK\",\"Records\":" + gson.toJson(data) + "}";
            logger.info("JSON response = " + response);
            resp.getWriter().write(response);
            return;
        }

        // JSON for JTable: One item fetched from the db ("Record" in response)
        if (resultMap.containsKey("datum")) {
            Object datum = resultMap.get("datum");
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            // Create a JSON object for JTable to parse
            String response = "{\"Result\":\"OK\",\"Record\":" + gson.toJson(datum) + "}";
            logger.info("JSON response = " + response);
            resp.getWriter().write(response);
            return;
        }

        // JSON for JTable: Operation with the db (deletion) is successful ("OK" in response)
        if (resultMap.containsKey("OK")) {
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            // JSON object for JTable to parse
            String response = "{\"Result\":\"OK\"}";
            resp.getWriter().write(response);
            logger.info("JSON response = " + response);
            return;
        }

        // JSON for JTable: List of options fetched from the db ("Options" in response)
        if (resultMap.containsKey("options")) {
            Object options = resultMap.get("options");
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            // Create a JSON object for JTable to parse
            String response = "{\"Result\":\"OK\",\"Options\":" + options + "}";
            logger.info("JSON response = " + response);
            resp.getWriter().write(response);
            return;
        }

    }
}
