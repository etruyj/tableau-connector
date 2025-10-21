//===================================================================
// TableauCsvDataModel.java
//      Description:
//          This model holds CSV data returned from Tableau Cloud
//          REST API view data endpoints. Designed to be flexible
//          to work with any view structure without knowing the
//          schema ahead of time.
//
// Created by etruyj
//===================================================================

package com.socialvagrancy.tableauconnector.model;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableauCsvDataModel {
    private String[] headers;
    private List<String[]> rows;

    //===========================================
    // Constructor
    //===========================================

    public TableauCsvDataModel() {
        this.rows = new ArrayList<>();
    }

    //===========================================
    // Factory Method
    //===========================================

    /**
     * Parses CSV data from a string and returns a TableauCsvDataModel.
     *
     * @param csv_data The CSV data as a string
     * @return TableauCsvDataModel with parsed headers and rows
     * @throws IOException If there's an error parsing the CSV
     */
    public static TableauCsvDataModel fromCsvString(String csv_data) throws IOException {
        TableauCsvDataModel model = new TableauCsvDataModel();

        if (csv_data == null || csv_data.trim().isEmpty()) {
            model.headers = new String[0];
            return model;
        }

        CSVParser parser = CSVParser.parse(csv_data, CSVFormat.DEFAULT.withFirstRecordAsHeader());

        // Get headers
        model.headers = parser.getHeaderNames().toArray(new String[0]);

        // Get rows
        for (CSVRecord record : parser) {
            String[] row = new String[model.headers.length];
            for (int i = 0; i < model.headers.length; i++) {
                row[i] = record.get(i);
            }
            model.rows.add(row);
        }

        parser.close();
        return model;
    }

    //===========================================
    // Getters
    //===========================================

    public String[] getHeaders() { return headers; }
    public List<String[]> getRows() { return rows; }

    //===========================================
    // Setters
    //===========================================

    public void setHeaders(String[] headers) { this.headers = headers; }
    public void setRows(List<String[]> rows) { this.rows = rows; }

    //===========================================
    // Helper Methods
    //===========================================

    /**
     * Get the index of a column by its name.
     *
     * @param column_name The name of the column
     * @return The index of the column, or -1 if not found
     */
    public int getColumnIndex(String column_name) {
        if (headers == null || column_name == null) {
            return -1;
        }

        for (int i = 0; i < headers.length; i++) {
            if (headers[i].equals(column_name)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Get a value from a specific row and column by column name.
     *
     * @param row_index The index of the row
     * @param column_name The name of the column
     * @return The value at the specified position, or null if not found
     */
    public String getValue(int row_index, String column_name) {
        int column_index = getColumnIndex(column_name);

        if (column_index == -1 || row_index < 0 || row_index >= rows.size()) {
            return null;
        }

        String[] row = rows.get(row_index);
        if (column_index >= row.length) {
            return null;
        }

        return row[column_index];
    }

    /**
     * Get a value from a specific row and column by column index.
     *
     * @param row_index The index of the row
     * @param column_index The index of the column
     * @return The value at the specified position, or null if not found
     */
    public String getValue(int row_index, int column_index) {
        if (row_index < 0 || row_index >= rows.size() || column_index < 0) {
            return null;
        }

        String[] row = rows.get(row_index);
        if (column_index >= row.length) {
            return null;
        }

        return row[column_index];
    }

    /**
     * Get all values from a specific column.
     *
     * @param column_name The name of the column
     * @return List of values in the column, or empty list if column not found
     */
    public List<String> getColumn(String column_name) {
        List<String> column_values = new ArrayList<>();
        int column_index = getColumnIndex(column_name);

        if (column_index == -1) {
            return column_values;
        }

        for (String[] row : rows) {
            if (column_index < row.length) {
                column_values.add(row[column_index]);
            } else {
                column_values.add(null);
            }
        }

        return column_values;
    }

    /**
     * Get a row as a Map with column names as keys.
     *
     * @param row_index The index of the row
     * @return Map of column names to values, or null if row not found
     */
    public Map<String, String> getRowAsMap(int row_index) {
        if (row_index < 0 || row_index >= rows.size() || headers == null) {
            return null;
        }

        Map<String, String> row_map = new HashMap<>();
        String[] row = rows.get(row_index);

        for (int i = 0; i < headers.length; i++) {
            if (i < row.length) {
                row_map.put(headers[i], row[i]);
            } else {
                row_map.put(headers[i], null);
            }
        }

        return row_map;
    }

    /**
     * Get all rows as a list of Maps.
     *
     * @return List of Maps, each representing a row
     */
    public List<Map<String, String>> getRowsAsMaps() {
        List<Map<String, String>> rows_as_maps = new ArrayList<>();

        for (int i = 0; i < rows.size(); i++) {
            rows_as_maps.add(getRowAsMap(i));
        }

        return rows_as_maps;
    }

    /**
     * Get the number of rows (excluding header).
     *
     * @return The number of data rows
     */
    public int getRowCount() {
        return rows != null ? rows.size() : 0;
    }

    /**
     * Get the number of columns.
     *
     * @return The number of columns
     */
    public int getColumnCount() {
        return headers != null ? headers.length : 0;
    }

    /**
     * Check if the dataset is empty.
     *
     * @return true if there are no rows, false otherwise
     */
    public boolean isEmpty() {
        return rows == null || rows.isEmpty();
    }

    /**
     * Check if a column exists.
     *
     * @param column_name The name of the column to check
     * @return true if the column exists, false otherwise
     */
    public boolean hasColumn(String column_name) {
        return getColumnIndex(column_name) != -1;
    }
}
