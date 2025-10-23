//===================================================================
// ViewsApi.java
//      Description:
//          This class handles getting information from the tableau 
//          views.
//
// Created by etruyj
//===================================================================

package com.socialvagrancy.tableauconnector.api;

import com.socialvagrancy.tableauconnector.model.TableauCsvDataModel;
import com.socialvagrancy.utils.http.RestClient;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ViewsApi {
    private static final Logger log = LoggerFactory.getLogger(ViewsApi.class);
    private static final int responseStrLogLength = 1000;

    public static TableauCsvDataModel getData(String url, String api_version, String site_id, String view_id, List<String> filters, String token, RestClient api) throws Exception {
        String api_path = TableauApiUrls.getViewDataApi(url, api_version, site_id, view_id);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Accept", "*/*");

        if(filters != null && !filters.isEmpty()) {
            api_path = api_path + "?" + String.join("&", filters);
        }

        log.debug("GET {}", api_path);

        HttpResponse response = api.get(api_path, "x-tableau-auth", token, headers);

        String responseStr = EntityUtils.toString(response.getEntity());

        log.debug("Response [{}]: {}", response.getStatusLine().getStatusCode(), responseStr.length() > responseStrLogLength ? responseStr.substring(0, responseStrLogLength) : responseStr);

        if(response.getStatusLine().getStatusCode() < 300) {
            return TableauCsvDataModel.fromCsvString(responseStr);
        } else {
            throw new Exception("Failed to get data for view [" + view_id + "].");
        }
    }
}
