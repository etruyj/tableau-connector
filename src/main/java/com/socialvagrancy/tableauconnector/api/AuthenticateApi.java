//===================================================================
// AuthenticateApi.java
//      Description:
//          This class handles authentication API calls.
//
// Created by etruyj
//===================================================================

package com.socialvagrancy.tableauconnector.api;

import com.google.gson.Gson;

import com.socialvagrancy.tableauconnector.model.TableauAuthModel;
import com.socialvagrancy.utils.http.RestClient;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthenticateApi {
    private static final Logger log = LoggerFactory.getLogger(AuthenticateApi.class);
    private static final Gson gson = new Gson();

    public static TableauAuthModel withPAT(String token_name, String secret, String site, String url, String api_version, RestClient api) throws Exception {
        String api_path = TableauApiUrls.authApi(url, api_version);

        TableauAuthModel body = new TableauAuthModel(token_name, secret, site);

        String payload = gson.toJson(body);

        log.debug("POST {}", api_path);

        HttpResponse response = api.post(api_path, payload);

        String responseStr = EntityUtils.toString(response.getEntity());

        log.debug("Response [{}]", response.getStatusLine().getStatusCode());

        if(response.getStatusLine().getStatusCode() < 300) {
            return gson.fromJson(responseStr, TableauAuthModel.class);
        } else {
            throw new Exception("Failed to authenticate with Tableau Cloud server.");
        }
    }
}
