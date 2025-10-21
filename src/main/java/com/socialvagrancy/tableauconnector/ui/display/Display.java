//===================================================================
// Display.java
//      Description:
//          This class handles the different output calls to diplay
//          results to the shell.
//
// Created by etruyj
//===================================================================

package com.socialvagrancy.tableauconnector.ui.display;

import com.socialvagrancy.tableauconnector.ui.display.serializer.Serializer;
import com.socialvagrancy.utils.ui.display.Print;
import com.socialvagrancy.utils.ui.display.Table;
import com.socialvagrancy.utils.ui.structures.OutputFormat;

import java.util.List; 

public class Display {
    public static void toShell(List output, String output_format) {
        // Only display if there is something to display.
        if(output != null && !output.isEmpty()) {
            List<OutputFormat> to_print = Serializer.serializeForOutput(output);

            if(!to_print.isEmpty()) {
                switch(output_format) {
                    case "shell":
                        Print.shell(to_print);
                        break;
                    case "table":
                    case "csv":
                        Table.printToShell(to_print, output_format);
                        break;
                }
            }
        }
    }
}
