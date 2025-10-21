//===================================================================
// TableauShell.java
//      Description:
//          This class handles the UIX for shell interaction with the
//          Tableau script. The intent of this script is to provide 
//          programatic access to Tableau Cloud via the API. This shell
//          is more for testing individual commands management calls.
//
// Created by etruyj
//===================================================================

package com.socialvagrancy.tableauconnector.ui;

import com.socialvagrancy.tableauconnector.command.TableauController;
import com.socialvagrancy.tableauconnector.model.TableauConfigModel;
import com.socialvagrancy.tableauconnector.ui.display.Display;
import com.socialvagrancy.utils.io.Configuration;
import com.socialvagrancy.utils.ui.ArgParser;

import java.util.Arrays;
import java.util.List;

public class TableauShell {
    public static void main(String[] args) {
        try {
            // Load Configuraton
            Configuration.load("../tableau.yml", TableauConfigModel.class);
            TableauConfigModel config = Configuration.get();

            // Process Args
            ArgParser aparser = parseArgs(args, config);

            // Process Command
            processCommand(aparser, config);
        } catch(Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private static ArgParser parseArgs(String[] args, TableauConfigModel config) {
        ArgParser aparser = new ArgParser();
        aparser.parse(args);
        return aparser;
    }

    private static void processCommand(ArgParser aparser, TableauConfigModel config) throws Exception {
        TableauController conn = new TableauController(config);
        List output = null;
        String output_format = aparser.get("output-format") != null ? aparser.get("output-format") : "table";

        if(aparser.getBoolean("help")) {
        } else if(aparser.getBoolean("version")) {
        } else {
            switch(aparser.getRequired("command")) {
                case "get-view-data":
                    output = conn.getViewDataForShell(aparser.get("workbook"), aparser.get("workbook-id"), aparser.get("view"), aparser.get("view-id"), Arrays.asList(aparser.get("filters")));
                    break;
                case "list-views":
                    output = conn.listWorkbookViews(aparser.get("workbook"), aparser.get("workbook-id"));
                    break;
                case "list-workbooks":
                    output = conn.listWorkbooks();
                    break;
            }
        }

        Display.toShell(output, output_format);
    }
}
