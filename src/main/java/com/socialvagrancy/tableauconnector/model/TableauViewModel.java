//===================================================================
// TableauViewModel.java
//      Description:
//          This model holds view information returned from the
//          Tableau Cloud REST API.
//
// Created by etruyj
//===================================================================

package com.socialvagrancy.tableauconnector.model;

import com.google.gson.annotations.SerializedName;

public class TableauViewModel {
    private Tags tags;
    private String id;
    private String name;
    @SerializedName("contentUrl")
    private String content_url;
    @SerializedName("createdAt")
    private String created_at;
    @SerializedName("updatedAt")
    private String updated_at;
    @SerializedName("viewUrlName")
    private String view_url_name;

    //===========================================
    // Getters
    //===========================================
    public Tags getTags() { return tags; }
    public String getId() { return id; }
    public String getName() { return name; }
    public String getContentUrl() { return content_url; }
    public String getCreatedAt() { return created_at; }
    public String getUpdatedAt() { return updated_at; }
    public String getViewUrlName() { return view_url_name; }

    //===========================================
    // Setters
    //===========================================
    public void setTags(Tags tags) { this.tags = tags; }
    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setContentUrl(String content_url) { this.content_url = content_url; }
    public void setCreatedAt(String created_at) { this.created_at = created_at; }
    public void setUpdatedAt(String updated_at) { this.updated_at = updated_at; }
    public void setViewUrlName(String view_url_name) { this.view_url_name = view_url_name; }

    //===========================================
    // Nested Classes
    //===========================================

    public static class Tags {
        // Empty object in the response
    }
}
