//===================================================================
// TableauConnector.java
//      Description:
//          This class handles the connection between the script and
//          the Tableau server via the API. 
//
// Created by etruyj
//===================================================================

package com.socialvagrancy.tableauconnector.api;

import com.socialvagrancy.tableauconnector.model.TableauAuthModel;
import com.socialvagrancy.tableauconnector.model.TableauCsvDataModel;
import com.socialvagrancy.tableauconnector.model.ApiViewsResponseModel;
import com.socialvagrancy.tableauconnector.model.ApiWorkbooksResponseModel;
import com.socialvagrancy.utils.http.RestClient;

import java.util.List;

public class TableauConnector {
    private String site_id;
    private String token;
    private String url;
    private String api_version;
    private RestClient api;

    public TableauConnector(String url, String api_version) {
        this.url = url;
        this.api_version = api_version;
        api = new RestClient(false);
    }

    public boolean authenticate(String token_name, String secret, String site) throws Exception {
        TableauAuthModel auth = AuthenticateApi.withPAT(token_name, secret, site, url, api_version, api);

        if(auth != null) {
            token = auth.getCredentials().getToken();
            site_id = auth.getCredentials().getSite().getId();
            
            return true;
        }
        
        return false;
    }

    public TableauCsvDataModel getViewData(String view_id, List<String> filters) throws Exception {
        return ViewsApi.getData(url, api_version, site_id, view_id, filters, token, api);
    }

    public ApiViewsResponseModel listWorkbookViews(String workbook_id) throws Exception {
        return WorkbooksApi.listViews(url, api_version, site_id, workbook_id, token, api);
    }

    public ApiWorkbooksResponseModel listWorkbooks() throws Exception {
        return WorkbooksApi.list(url, api_version, site_id, token, api);
    }
}

