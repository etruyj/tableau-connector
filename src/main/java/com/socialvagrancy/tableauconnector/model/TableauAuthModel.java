//===================================================================
// TableauAuthModel.java
//      Description:
//          This model holds the authentication response from the
//          Tableau Cloud REST API sign-in endpoint, including the
//          authentication token and session details.
//
// Created by etruyj
//===================================================================

package com.socialvagrancy.tableauconnector.model;

import com.google.gson.annotations.SerializedName;

public class TableauAuthModel {
    private Credentials credentials;

    //===========================================
    // Constructors
    //===========================================

    public TableauAuthModel() {}

    public TableauAuthModel(String personal_access_token_name, String personal_access_token_secret, String content_url) {
        this.credentials = new Credentials();
        this.credentials.setPersonalAccessTokenName(personal_access_token_name);
        this.credentials.setPersonalAccessTokenSecret(personal_access_token_secret);

        Site site = new Site();
        site.setContentUrl(content_url);
        this.credentials.setSite(site);
    }

    //===========================================
    // Getters
    //===========================================
    public Credentials getCredentials() { return credentials; }

    //===========================================
    // Setters
    //===========================================
    public void setCredentials(Credentials credentials) { this.credentials = credentials; }

    //===========================================
    // Nested Classes
    //===========================================

    public static class Credentials {
        private Site site;
        private User user;
        private String token;
        @SerializedName("estimatedTimeToExpiration")
        private String estimated_time_to_expiration;
        @SerializedName("personalAccessTokenName")
        private String personal_access_token_name;
        @SerializedName("personalAccessTokenSecret")
        private String personal_access_token_secret;

        //===========================================
        // Getters
        //===========================================
        public Site getSite() { return site; }
        public User getUser() { return user; }
        public String getToken() { return token; }
        public String getEstimatedTimeToExpiration() { return estimated_time_to_expiration; }
        public String getPersonalAccessTokenName() { return personal_access_token_name; }
        public String getPersonalAccessTokenSecret() { return personal_access_token_secret; }

        //===========================================
        // Setters
        //===========================================
        public void setSite(Site site) { this.site = site; }
        public void setUser(User user) { this.user = user; }
        public void setToken(String token) { this.token = token; }
        public void setEstimatedTimeToExpiration(String estimated_time_to_expiration) { this.estimated_time_to_expiration = estimated_time_to_expiration; }
        public void setPersonalAccessTokenName(String personal_access_token_name) { this.personal_access_token_name = personal_access_token_name; }
        public void setPersonalAccessTokenSecret(String personal_access_token_secret) { this.personal_access_token_secret = personal_access_token_secret; }
    }

    public static class Site {
        private String id;
        @SerializedName("contentUrl")
        private String content_url;

        //===========================================
        // Getters
        //===========================================
        public String getId() { return id; }
        public String getContentUrl() { return content_url; }

        //===========================================
        // Setters
        //===========================================
        public void setId(String id) { this.id = id; }
        public void setContentUrl(String content_url) { this.content_url = content_url; }
    }

    public static class User {
        private String id;

        //===========================================
        // Getters
        //===========================================
        public String getId() { return id; }

        //===========================================
        // Setters
        //===========================================
        public void setId(String id) { this.id = id; }
    }
}
