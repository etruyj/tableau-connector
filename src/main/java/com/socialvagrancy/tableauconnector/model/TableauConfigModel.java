//===================================================================
// TableauConfigModel.java
//      Description:
//          This model holds configuration information for connecting
//          to the Tableau Cloud REST API, including authentication
//          credentials, site details, and connection parameters.
//
// Created by etruyj
//===================================================================

package com.socialvagrancy.tableauconnector.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class TableauConfigModel {
    private Auth auth;
    private Site site;
    private Connection connection;

    //===========================================
    // Getters
    //===========================================
    public Auth getAuth() { return auth; }
    public Site getSite() { return site; }
    public Connection getConnection() { return connection; }

    //===========================================
    // Setters
    //===========================================
    public void setAuth(Auth auth) { this.auth = auth; }
    public void setSite(Site site) { this.site = site; }
    public void setConnection(Connection connection) { this.connection = connection; }

    //===========================================
    // Nested Classes
    //===========================================

    public static class Auth {
        @SerializedName("tokenName")
        private String token_name;
        private String secret;
        @SerializedName("expirationDate")
        private String expiration_date;

        //===========================================
        // Getters
        //===========================================
        public String getTokenName() { return token_name; }
        public String getSecret() { return secret; }
        public String getExpirationDate() { return expiration_date; }

        //===========================================
        // Setters
        //===========================================
        public void setTokenName(String token_name) { this.token_name = token_name; }
        public void setSecret(String secret) { this.secret = secret; }
        public void setExpirationDate(String expiration_date) { this.expiration_date = expiration_date; }
    }

    public static class Site {
        private String name;

        //===========================================
        // Getters
        //===========================================
        public String getName() { return name; }

        //===========================================
        // Setters
        //===========================================
        public void setName(String name) { this.name = name; }
    }

    public static class Connection {
        private String url;
        @SerializedName("apiVersion")
        private String api_version;

        //===========================================
        // Getters
        //===========================================
        public String getUrl() { return url; }
        public String getApiVersion() { return api_version; }

        //===========================================
        // Setters
        //===========================================
        public void setUrl(String url) { this.url = url; }
        public void setApiVersion(String api_version) { this.api_version = api_version; }
    }
}
