//===================================================================
// SerializeCsvData.java
//      Description:
//          This serializes the CSV data object into a list of output format
//          for print to shell.
//
// Created by etruyj
//===================================================================

package com.socialvagrancy.tableauconnector.ui.display.serializer;

import com.socialvagrancy.tableauconnector.model.TableauCsvDataModel;
import com.socialvagrancy.utils.ui.structures.OutputFormat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SerializeCsvData {
    public static List<OutputFormat> forOutput(List<TableauCsvDataModel> csv_data_list) {
        List<OutputFormat> output = new ArrayList<OutputFormat>();
        OutputFormat field = null;

        for(TableauCsvDataModel csv_data : csv_data_list) {
            if(csv_data.isEmpty()) {
                continue;
            }

            // Get all rows as maps for easier access
            List<Map<String, String>> rows = csv_data.getRowsAsMaps();

            // Iterate through each row
            for(int i = 0; i < rows.size(); i++) {
                Map<String, String> row = rows.get(i);

                // Add a row separator for readability (except for first row)
                if(i > 0) {
                    field = new OutputFormat();
                    field.key = "---";
                    field.value = "---";
                    output.add(field);
                }

                // Add each column value as a key-value pair
                for(String column_name : csv_data.getHeaders()) {
                    field = new OutputFormat();
                    field.key = column_name;
                    field.value = row.get(column_name);
                    output.add(field);
                }
            }
        }

        return output;
    }
}
