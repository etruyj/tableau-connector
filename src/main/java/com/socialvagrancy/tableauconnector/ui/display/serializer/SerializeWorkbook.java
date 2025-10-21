//===================================================================
// SerializeWorkbook.java
//      Description:
//          This serializes the workbook object into a list of output format
//          for print to shell.
//
// Created by etruyj
//===================================================================

package com.socialvagrancy.tableauconnector.ui.display.serializer;

import com.socialvagrancy.tableauconnector.model.TableauWorkbookModel;
import com.socialvagrancy.utils.ui.structures.OutputFormat;

import java.util.ArrayList;
import java.util.List;

public class SerializeWorkbook {
    public static List<OutputFormat> forOutput(List<TableauWorkbookModel> workbooks) {
        List<OutputFormat> output = new ArrayList<OutputFormat>();
        OutputFormat field = null;

        for(TableauWorkbookModel workbook : workbooks) {
            field = new OutputFormat();
            field.key = "id";
            field.value = workbook.getId();
            output.add(field);

            field = new OutputFormat();
            field.key = "name";
            field.value = workbook.getName();
            output.add(field);

            field = new OutputFormat();
            field.key = "owner";
            field.value = workbook.getOwner().getName();
            output.add(field);

            field = new OutputFormat();
            field.key = "project";
            field.value = workbook.getProject().getName();
            output.add(field);

            field = new OutputFormat();
            field.key = "location";
            field.value = workbook.getLocation().getName();
            output.add(field);

            field = new OutputFormat();
            field.key = "creation_date";
            field.value = workbook.getCreatedAt();
            output.add(field);

            field = new OutputFormat();
            field.key = "last_updated";
            field.value = workbook.getUpdatedAt();
            output.add(field);

        }

        return output;
    }
}
