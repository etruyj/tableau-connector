//===================================================================
// TableauWorkbookModel.java
//      Description:
//          This model holds workbook information returned from the
//          Tableau Cloud REST API.
//
// Created by etruyj
//===================================================================

package com.socialvagrancy.tableauconnector.model;

import com.google.gson.annotations.SerializedName;

public class TableauWorkbookModel {
    private Project project;
    private Location location;
    private Owner owner;
    private Tags tags;
    @SerializedName("dataAccelerationConfig")
    private DataAccelerationConfig data_acceleration_config;
    private String id;
    private String name;
    private String description;
    @SerializedName("contentUrl")
    private String content_url;
    @SerializedName("webpageUrl")
    private String webpage_url;
    @SerializedName("showTabs")
    private String show_tabs;
    private String size;
    @SerializedName("createdAt")
    private String created_at;
    @SerializedName("updatedAt")
    private String updated_at;
    @SerializedName("encryptExtracts")
    private String encrypt_extracts;
    @SerializedName("defaultViewId")
    private String default_view_id;

    //===========================================
    // Getters
    //===========================================
    public Project getProject() { return project; }
    public Location getLocation() { return location; }
    public Owner getOwner() { return owner; }
    public Tags getTags() { return tags; }
    public DataAccelerationConfig getDataAccelerationConfig() { return data_acceleration_config; }
    public String getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getContentUrl() { return content_url; }
    public String getWebpageUrl() { return webpage_url; }
    public String getShowTabs() { return show_tabs; }
    public String getSize() { return size; }
    public String getCreatedAt() { return created_at; }
    public String getUpdatedAt() { return updated_at; }
    public String getEncryptExtracts() { return encrypt_extracts; }
    public String getDefaultViewId() { return default_view_id; }

    //===========================================
    // Setters
    //===========================================
    public void setProject(Project project) { this.project = project; }
    public void setLocation(Location location) { this.location = location; }
    public void setOwner(Owner owner) { this.owner = owner; }
    public void setTags(Tags tags) { this.tags = tags; }
    public void setDataAccelerationConfig(DataAccelerationConfig data_acceleration_config) { this.data_acceleration_config = data_acceleration_config; }
    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setContentUrl(String content_url) { this.content_url = content_url; }
    public void setWebpageUrl(String webpage_url) { this.webpage_url = webpage_url; }
    public void setShowTabs(String show_tabs) { this.show_tabs = show_tabs; }
    public void setSize(String size) { this.size = size; }
    public void setCreatedAt(String created_at) { this.created_at = created_at; }
    public void setUpdatedAt(String updated_at) { this.updated_at = updated_at; }
    public void setEncryptExtracts(String encrypt_extracts) { this.encrypt_extracts = encrypt_extracts; }
    public void setDefaultViewId(String default_view_id) { this.default_view_id = default_view_id; }

    //===========================================
    // Nested Classes
    //===========================================

    public static class Project {
        private String id;
        private String name;

        //===========================================
        // Getters
        //===========================================
        public String getId() { return id; }
        public String getName() { return name; }

        //===========================================
        // Setters
        //===========================================
        public void setId(String id) { this.id = id; }
        public void setName(String name) { this.name = name; }
    }

    public static class Location {
        private String id;
        private String type;
        private String name;

        //===========================================
        // Getters
        //===========================================
        public String getId() { return id; }
        public String getType() { return type; }
        public String getName() { return name; }

        //===========================================
        // Setters
        //===========================================
        public void setId(String id) { this.id = id; }
        public void setType(String type) { this.type = type; }
        public void setName(String name) { this.name = name; }
    }

    public static class Owner {
        private String id;
        private String name;

        //===========================================
        // Getters
        //===========================================
        public String getId() { return id; }
        public String getName() { return name; }

        //===========================================
        // Setters
        //===========================================
        public void setId(String id) { this.id = id; }
        public void setName(String name) { this.name = name; }
    }

    public static class Tags {
        // Empty object in the response
    }

    public static class DataAccelerationConfig {
        // Empty object in the response
    }
}
