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
    private String response;

    private GsonBuilder gsonBuilder = new GsonBuilder();
    private Gson gson = gsonBuilder.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY).create();

    public void parse(Map<String, Object> resultMap, HttpServletResponse resp) throws IOException {
        resp.setContentType(APPLICATION_JSON);
        resp.setCharacterEncoding(UTF8);

        if (resultMap.containsKey("jTableError")) {
            parseError(resultMap);

        } else if (resultMap.containsKey("data")) {
            parseData(resultMap.get("data"));

        } else if (resultMap.containsKey("datum")) {
            parseDatum(resultMap.get("datum"));

        } else if (resultMap.containsKey("OK")) {
            parseOK();

        } else if (resultMap.containsKey("options")) {
            parseOptions(resultMap.get("options"));
        }

        LOG.info(JSON_RESPONSE + response);
        resp.getWriter().write(response);

    }

    /**
     * Error for JTable: Operation with the database failed ("ERROR" in response)
     * @param resultMap
     */
    private void parseError(Map<String, Object> resultMap) {
        response = "{\"Result\":\"ERROR\",\"Message\":\"" + resultMap.get("jTableError") + "\"}";
    }

    /**
     * JSON for JTable: List of items fetched from the db ("Records" in response)
     * @param data
     */
    private void parseData(Object data) {
        String json = gson.toJson(data);
        // if there only one object, add brackets like it's a list (JTable requirements)
        if (json.endsWith("}")) {
            json = "[" + json + "]";
        }
        response = "{\"Result\":\"OK\",\"Records\":" + json + "}";
    }

    /**
     * JSON for JTable: One item fetched from the db ("Record" in response)
     * @param datum
     */
    private void parseDatum(Object datum) {
        response = "{\"Result\":\"OK\",\"Record\":" + gson.toJson(datum) + "}";
    }

    /**
     * JSON for JTable: Operation with the db (deletion) is successful ("OK" in response)
     */
    private void parseOK() {
        response = "{\"Result\":\"OK\"}";
    }

    /**
     * JSON for JTable: List of options fetched from the db ("Options" in response)
     * @param options
     */
    private void parseOptions(Object options) {
        response = "{\"Result\":\"OK\",\"Options\":" + options + "}";
    }
}
