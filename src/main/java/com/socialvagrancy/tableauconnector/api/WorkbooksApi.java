//===================================================================
// WorkbooksApi.java
//      Description:
//          This class handles authentication API calls.
//
// Created by etruyj
//===================================================================

package com.socialvagrancy.tableauconnector.api;

import com.google.gson.Gson;

import com.socialvagrancy.tableauconnector.model.ApiViewsResponseModel;
import com.socialvagrancy.tableauconnector.model.ApiWorkbooksResponseModel;
import com.socialvagrancy.utils.http.RestClient;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorkbooksApi {
    private static final Logger log = LoggerFactory.getLogger(WorkbooksApi.class);
    private static final Gson gson = new Gson();
    private static final int responseBodyLogLength = 1000;

    public static ApiWorkbooksResponseModel list(String url, String api_version, String site_id, String token, RestClient api) throws Exception {
        String api_path = TableauApiUrls.listWorkbooksApi(url, api_version, site_id);

        log.debug("GET {}", api_path);

        HttpResponse response = api.get(api_path, "x-tableau-auth", token);

        String responseStr = EntityUtils.toString(response.getEntity());

        log.debug("Response [{}] {}", response.getStatusLine().getStatusCode(), responseStr.length() > responseBodyLogLength ? responseStr.substring(0, responseBodyLogLength) : responseStr);

        if(response.getStatusLine().getStatusCode() < 300) {
            return gson.fromJson(responseStr, ApiWorkbooksResponseModel.class);
        } else {
            throw new Exception("Failed to list workbooks from Tableau Cloud server.");
        }
    }

    public static ApiViewsResponseModel listViews(String url, String api_version, String site_id, String workbook_id, String token, RestClient api) throws Exception {
        String api_path = TableauApiUrls.listWorkbookViewsApi(url, api_version, site_id, workbook_id);

        log.debug("GET {}", api_path);

        HttpResponse response = api.get(api_path, "x-tableau-auth", token);

        String responseStr = EntityUtils.toString(response.getEntity());

        log.debug("Response [{}] {}", response.getStatusLine().getStatusCode(), responseStr.length() > responseBodyLogLength ? responseStr.substring(0, responseBodyLogLength) : responseStr);

        if(response.getStatusLine().getStatusCode() < 300) {
            return gson.fromJson(responseStr, ApiViewsResponseModel.class);
        } else {
            throw new Exception("Failed to list views for workbook [" + workbook_id + "] from Tableau Cloud server.");
        }
    }
}
