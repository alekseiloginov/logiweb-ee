package com.tsystems.javaschool.loginov.logiweb.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Utility class which provides GSON parsing for jTable.
 */
@Component
public class GsonParser {
    private static final Logger LOG = Logger.getLogger(GsonParser.class);
    private static final String APPLICATION_JSON = "application/json";
    private static final String UTF8 = "UTF-8";
    private static final String JSON_RESPONSE = "JSON response = ";

    GsonBuilder gsonBuilder = new GsonBuilder();
    Gson gson = gsonBuilder.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY).create();

    public void parse(Map<String, Object> resultMap, HttpServletResponse resp) throws IOException {

        // Error for JTable: Operation with the database failed ("ERROR" in response)
        if (resultMap.containsKey("jTableError")) {
            resp.setContentType(APPLICATION_JSON);
            resp.setCharacterEncoding(UTF8);
            // JSON object for JTable to parse
            String response = "{\"Result\":\"ERROR\",\"Message\":\"" + resultMap.get("jTableError") + "\"}";
            resp.getWriter().write(response);
            LOG.info(JSON_RESPONSE + response);
            return;
        }

        // JSON for JTable: List of items fetched from the db ("Records" in response)
        if (resultMap.containsKey("data")) {
            Object data = resultMap.get("data");
            resp.setContentType(APPLICATION_JSON);
            resp.setCharacterEncoding(UTF8);
            // Create a JSON object for JTable to parse
            String json = gson.toJson(data);
            // if there only one object, add brackets like it's a list (JTable requirements)
            if (json.endsWith("}")) {
                json = "[" + json + "]";
            }
            String response = "{\"Result\":\"OK\",\"Records\":" + json + "}";
            LOG.info(JSON_RESPONSE + response);
            resp.getWriter().write(response);
            return;
        }

        // JSON for JTable: One item fetched from the db ("Record" in response)
        if (resultMap.containsKey("datum")) {
            Object datum = resultMap.get("datum");
            resp.setContentType(APPLICATION_JSON);
            resp.setCharacterEncoding(UTF8);
            // Create a JSON object for JTable to parse
            String response = "{\"Result\":\"OK\",\"Record\":" + gson.toJson(datum) + "}";
            LOG.info(JSON_RESPONSE + response);
            resp.getWriter().write(response);
            return;
        }

        // JSON for JTable: Operation with the db (deletion) is successful ("OK" in response)
        if (resultMap.containsKey("OK")) {
            resp.setContentType(APPLICATION_JSON);
            resp.setCharacterEncoding(UTF8);
            // JSON object for JTable to parse
            String response = "{\"Result\":\"OK\"}";
            resp.getWriter().write(response);
            LOG.info(JSON_RESPONSE + response);
            return;
        }

        // JSON for JTable: List of options fetched from the db ("Options" in response)
        if (resultMap.containsKey("options")) {
            Object options = resultMap.get("options");
            resp.setContentType(APPLICATION_JSON);
            resp.setCharacterEncoding(UTF8);
            // Create a JSON object for JTable to parse
            String response = "{\"Result\":\"OK\",\"Options\":" + options + "}";
            LOG.info(JSON_RESPONSE + response);
            resp.getWriter().write(response);
        }

    }
}
