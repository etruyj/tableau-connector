//===================================================================
// SerializeView.java
//      Description:
//          This serializes the view object into a list of output format
//          for print to shell.
//
// Created by etruyj
//===================================================================

package com.socialvagrancy.tableauconnector.ui.display.serializer;

import com.socialvagrancy.tableauconnector.model.TableauViewModel;
import com.socialvagrancy.utils.ui.structures.OutputFormat;

import java.util.ArrayList;
import java.util.List;

public class SerializeView {
    public static List<OutputFormat> forOutput(List<TableauViewModel> views) {
        List<OutputFormat> output = new ArrayList<OutputFormat>();
        OutputFormat field = null;

        for(TableauViewModel view : views) {
            field = new OutputFormat();
            field.key = "id";
            field.value = view.getId();
            output.add(field);

            field = new OutputFormat();
            field.key = "name";
            field.value = view.getName();
            output.add(field);

            field = new OutputFormat();
            field.key = "creation_date";
            field.value = view.getCreatedAt();
            output.add(field);

            field = new OutputFormat();
            field.key = "last_updated";
            field.value = view.getUpdatedAt();
            output.add(field);

        }

        return output;
    }
}
