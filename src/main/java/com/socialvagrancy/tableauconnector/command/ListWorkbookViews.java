//===================================================================
// ListWorkbookViews.java
//      Description:
//          This class handles queries to the Tableau API required to
//          list workbooks.
//
// Created by etruyj
//===================================================================

package com.socialvagrancy.tableauconnector.command;

import com.socialvagrancy.tableauconnector.api.TableauConnector;
import com.socialvagrancy.tableauconnector.model.ApiViewsResponseModel;
import com.socialvagrancy.tableauconnector.model.TableauViewModel;
import com.socialvagrancy.tableauconnector.model.TableauWorkbookModel;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ListWorkbookViews {
    private static final Logger log = LoggerFactory.getLogger(ListWorkbookViews.class);

    public static List<TableauViewModel> parseInput(String workbook_name, String workbook_id, TableauConnector tableau) throws Exception {
        if(workbook_name != null) {
            log.info("Searching for workbook id for workbook: {}", workbook_name); 
            List<TableauWorkbookModel> workbooks = ListWorkbooks.all(tableau);

            for(TableauWorkbookModel workbook : workbooks) {
                if(workbook.getName().equalsIgnoreCase(workbook_name)) {
                    workbook_id = workbook.getId();
                    break;
                }
            }
        } else if(workbook_id == null) {
            throw new Exception("Missing required field: workbook or workbook-id");
        }

        if(workbook_id == null) {
            log.error("Failed to find workbook: {}", workbook_name);
            throw new Exception("Failed to list workbook views for workbook");
        }

        return all(workbook_id, tableau);
    }

    public static List<TableauViewModel> all(String workbook_id, TableauConnector tableau) throws Exception {
        log.info("Listing all views associated with workbook [{}] in Tableau account.", workbook_id);
        List<TableauViewModel> views = new ArrayList<TableauViewModel>();
        ApiViewsResponseModel response;

        int page = 1;

        while(true) {
            log.debug("Querying Tableau for page {} of workbook views.", String.valueOf(page));

            response = tableau.listWorkbookViews(workbook_id);
            views.addAll(response.getViews().getView());

            if(response.getPagination() == null) {
                log.debug("Results are not paginated.");
                break;
            }

            if(Integer.valueOf(response.getPagination().getTotalAvailable()) < (Integer.valueOf(response.getPagination().getPageNumber()) * Integer.valueOf(response.getPagination().getPageSize()))) {
                log.debug("Reached the end of paginated results."); 
                break;
            } else {
                log.debug("Incrementing pagination.");
                page++;
            }
        }

        log.info("Retrieved ({}) views.", views.size());

        return views;
    }
}
