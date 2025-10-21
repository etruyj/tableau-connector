//===================================================================
// TableauApiUrls.java
//      Description:
//          This class holds all the REST API urls to access Tableau.
//
// Created by etruyj
//===================================================================

package com.socialvagrancy.tableauconnector.api;

public class TableauApiUrls {
    private static String base_url = "https://{{url}}/api/{{api_version}}/";

    public static String authApi(String url, String api_version) {
        return (base_url + "auth/signin")
            .replace("{{url}}", url)
            .replace("{{api_version}}", api_version);
    }

    public static String getViewDataApi(String url, String api_version, String site_id, String view_id) {
        return (base_url + "sites/{{site_id}}/views/{{view_id}}/data")
            .replace("{{url}}", url)
            .replace("{{api_version}}", api_version)
            .replace("{{site_id}}", site_id)
            .replace("{{view_id}}", view_id);
    }

    public static String listWorkbooksApi(String url, String api_version, String site_id) {
        return (base_url + "sites/{{site_id}}/workbooks")
            .replace("{{url}}", url)
            .replace("{{api_version}}", api_version)
            .replace("{{site_id}}", site_id);
    }

    public static String listWorkbookViewsApi(String url, String api_version, String site_id, String workbook_id) {
        return (base_url + "sites/{{site_id}}/workbooks/{{workbook_id}}/views")
            .replace("{{url}}", url)
            .replace("{{api_version}}", api_version)
            .replace("{{site_id}}", site_id)
            .replace("{{workbook_id}}", workbook_id);
    }

    
}
