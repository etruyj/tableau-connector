//===================================================================
// Serializer.java
//      Description:
//          This class handles the serialization of List<> to
//          List<OutputFormat>
//
// Created by etruyj
//===================================================================

package com.socialvagrancy.tableauconnector.ui.display.serializer;

import com.socialvagrancy.tableauconnector.model.TableauCsvDataModel;
import com.socialvagrancy.tableauconnector.model.TableauViewModel;
import com.socialvagrancy.tableauconnector.model.TableauWorkbookModel;
import com.socialvagrancy.utils.ui.structures.OutputFormat;

import java.util.ArrayList;
import java.util.List;

public class Serializer {
    public static List<OutputFormat> serializeForOutput(List output) {
        if(output.size() > 0) {
            if(output.get(0) instanceof TableauWorkbookModel) {
                return SerializeWorkbook.forOutput(output);
            } else if(output.get(0) instanceof TableauViewModel) {
                return SerializeView.forOutput(output);
            } else if(output.get(0) instanceof TableauCsvDataModel) {
                return SerializeCsvData.forOutput(output);
            } else {
                return new ArrayList();
            }
        } else {
            return new ArrayList();
        }

    }
}
