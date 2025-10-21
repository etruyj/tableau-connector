//===================================================================
// TableauConnector.java
//      Description:
//          This is the entry point to the script. All commands and
//          required dependencies are initialized and called form here.
//          Any UI or other features that need to integrate with this
//          script will do so from here.
//
// Created by etruyj
//===================================================================

package com.socialvagrancy.tableauconnector.command;

import com.socialvagrancy.tableauconnector.api.TableauConnector;
import com.socialvagrancy.tableauconnector.model.TableauConfigModel;
import com.socialvagrancy.tableauconnector.model.TableauCsvDataModel;
import com.socialvagrancy.tableauconnector.model.TableauViewModel;
import com.socialvagrancy.tableauconnector.model.TableauWorkbookModel;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TableauController {
    private static final Logger log = LoggerFactory.getLogger(TableauController.class);

    private TableauConnector tableau;
    private String pat_token_name;
    private String pat_secret;
    private String pat_site;
    private String pat_expiration;
    private int authRetries;

    public TableauController(TableauConfigModel config) throws Exception {
        tableau = new TableauConnector(config.getConnection().getUrl(), config.getConnection().getApiVersion());
        pat_token_name = config.getAuth().getTokenName();
        pat_secret = config.getAuth().getSecret();
        pat_site = config.getSite().getName();
        pat_expiration = config.getAuth().getExpirationDate();

        authenticateWithTableau();
    
        authRetries = 1; // How many times to retry authentication if the token expires.
    }

    public void authenticateWithTableau() throws Exception {
        Authenticate.withTableau(pat_token_name, pat_secret, pat_site, pat_expiration, tableau);
    }

    public TableauCsvDataModel getViewData(String workbook, String workbook_id, String view, String view_id, List<String> filters) throws Exception {
        return GetViewData.parseInput(workbook, workbook_id, view, view_id, filters, tableau);
    }
    
    public List<TableauCsvDataModel> getViewDataForShell(String workbook, String workbook_id, String view, String view_id, List<String> filters) throws Exception {
        // Convert response to a list to let it get processed by the TableauShell script.
        List<TableauCsvDataModel> report = new ArrayList<TableauCsvDataModel>();
        report.add(getViewData(workbook, workbook_id, view, view_id, filters));
        return report;
    }

    public List<TableauWorkbookModel> listWorkbooks() throws Exception {
        return ListWorkbooks.all(tableau);
    }

    public List<TableauViewModel> listWorkbookViews(String workbook, String workbook_id) throws Exception {
        return ListWorkbookViews.parseInput(workbook, workbook_id, tableau);
    }
}
