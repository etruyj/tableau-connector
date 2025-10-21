//===================================================================
// ApiWorkbooksResponseModel.java
//      Description:
//          This model holds the response from the Tableau Cloud
//          REST API when querying for workbooks. Extends ApiDataResponse
//          to include pagination information.
//
// Created by etruyj
//===================================================================

package com.socialvagrancy.tableauconnector.model;

import java.util.List;

public class ApiWorkbooksResponseModel extends ApiDataResponse {
    private Workbooks workbooks;

    //===========================================
    // Getters
    //===========================================
    public Workbooks getWorkbooks() { return workbooks; }

    //===========================================
    // Setters
    //===========================================
    public void setWorkbooks(Workbooks workbooks) { this.workbooks = workbooks; }

    //===========================================
    // Nested Classes
    //===========================================

    public static class Workbooks {
        private List<TableauWorkbookModel> workbook;

        //===========================================
        // Getters
        //===========================================
        public List<TableauWorkbookModel> getWorkbook() { return workbook; }

        //===========================================
        // Setters
        //===========================================
        public void setWorkbook(List<TableauWorkbookModel> workbook) { this.workbook = workbook; }
    }
}
