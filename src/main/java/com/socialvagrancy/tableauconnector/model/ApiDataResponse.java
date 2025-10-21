//===================================================================
// ApiDataResponse.java
//      Description:
//          Abstract base class for API responses that contain
//          pagination information from the Tableau Cloud REST API.
//
// Created by etruyj
//===================================================================

package com.socialvagrancy.tableauconnector.model;

import com.google.gson.annotations.SerializedName;

public abstract class ApiDataResponse {
    private Pagination pagination;

    //===========================================
    // Getters
    //===========================================
    public Pagination getPagination() { return pagination; }

    //===========================================
    // Setters
    //===========================================
    public void setPagination(Pagination pagination) { this.pagination = pagination; }

    //===========================================
    // Nested Classes
    //===========================================

    public static class Pagination {
        @SerializedName("pageNumber")
        private String page_number;
        @SerializedName("pageSize")
        private String page_size;
        @SerializedName("totalAvailable")
        private String total_available;

        //===========================================
        // Getters
        //===========================================
        public String getPageNumber() { return page_number; }
        public String getPageSize() { return page_size; }
        public String getTotalAvailable() { return total_available; }

        //===========================================
        // Setters
        //===========================================
        public void setPageNumber(String page_number) { this.page_number = page_number; }
        public void setPageSize(String page_size) { this.page_size = page_size; }
        public void setTotalAvailable(String total_available) { this.total_available = total_available; }
    }
}
