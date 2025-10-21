//===================================================================
// ApiViewsResponseModel.java
//      Description:
//          This model holds the response from the Tableau Cloud
//          REST API when querying for views. Extends ApiDataResponse
//          to include pagination information.
//
// Created by etruyj
//===================================================================

package com.socialvagrancy.tableauconnector.model;

import java.util.List;

public class ApiViewsResponseModel extends ApiDataResponse {
    private Views views;

    //===========================================
    // Getters
    //===========================================
    public Views getViews() { return views; }

    //===========================================
    // Setters
    //===========================================
    public void setViews(Views views) { this.views = views; }

    //===========================================
    // Nested Classes
    //===========================================

    public static class Views {
        private List<TableauViewModel> view;

        //===========================================
        // Getters
        //===========================================
        public List<TableauViewModel> getView() { return view; }

        //===========================================
        // Setters
        //===========================================
        public void setView(List<TableauViewModel> view) { this.view = view; }
    }
}
