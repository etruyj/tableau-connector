//===================================================================
// GetViewData.java
//      Description:
//          This class handles retreiving data from the tableau view
//          and caching it as a CSV.
//
// Created by etruyj
//===================================================================

package com.socialvagrancy.tableauconnector.command;

import com.socialvagrancy.tableauconnector.api.TableauConnector;
import com.socialvagrancy.tableauconnector.model.TableauCsvDataModel;
import com.socialvagrancy.tableauconnector.model.TableauViewModel;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetViewData {
    private static final Logger log = LoggerFactory.getLogger(GetViewData.class);

    public static TableauCsvDataModel parseInput(String workbook, String workbook_id, String view_name, String view_id, List<String> filters, TableauConnector tableau) throws Exception {
        log.info("Retriving CSV data from from view.");

        if(view_name != null) {
            log.info("Searching for id associated with view: {}", view_id);
            List<TableauViewModel> views = ListWorkbookViews.parseInput(workbook, workbook_id, tableau);
        
            for(TableauViewModel view : views) {
                if(view.getName().equalsIgnoreCase(view_name)) {
                    view_id = view.getId();
                    break;
                }
            }
        } else if(view_id == null) {
            throw new Exception("Missing required field: view or view-id.");
        }

        if(view_id == null) {
            throw new Exception("Unable to find view: " + view_name);
        }

        return asCsv(view_id, filters, tableau);
    }

    public static TableauCsvDataModel asCsv(String view_id, List<String> filters, TableauConnector tableau) throws Exception {
        log.info("Fetching CSV data associated with view [{}] using filters: {}", view_id, String.join("&", filters));

        TableauCsvDataModel csvdata = tableau.getViewData(view_id, filters);

        log.info("Retrieved ({}) rows from the view.", csvdata.getRows().size());

        return csvdata;
    }
}
