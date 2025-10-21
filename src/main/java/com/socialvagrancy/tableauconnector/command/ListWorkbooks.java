//===================================================================
// ListWorkbooks.java
//      Description:
//          This class handles queries to the Tableau API required to
//          list workbooks.
//
// Created by etruyj
//===================================================================

package com.socialvagrancy.tableauconnector.command;

import com.socialvagrancy.tableauconnector.api.TableauConnector;
import com.socialvagrancy.tableauconnector.model.ApiWorkbooksResponseModel;
import com.socialvagrancy.tableauconnector.model.TableauWorkbookModel;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ListWorkbooks {
    private static final Logger log = LoggerFactory.getLogger(ListWorkbooks.class);

    public static List<TableauWorkbookModel> all(TableauConnector tableau) throws Exception {
        log.info("Listing all workbooks in Tableau account.");
        List<TableauWorkbookModel> workbooks = new ArrayList<TableauWorkbookModel>();
        ApiWorkbooksResponseModel response;

        int page = 1;

        while(true) {
            log.debug("Querying Tableau for page {} of workbooks.", String.valueOf(page));

            response = tableau.listWorkbooks();
            workbooks.addAll(response.getWorkbooks().getWorkbook());

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

        log.info("Retrieved ({}) workbooks.", workbooks.size());

        return workbooks;
    }
}
