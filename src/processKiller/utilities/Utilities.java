/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processKiller.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import processKiller.gui.ProcessKillerGUI;

/**
 *
 * @author eliabdallah
 */
public class Utilities {

    public void listRunningProcesses(JTable table) {
        removeJTableRows(table);
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        String line;
        try {
            Process p = Runtime.getRuntime().exec("tasklist.exe /fo csv /nh");
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = input.readLine()) != null) {
                if (!line.trim().equals("")) {
                    String lineSplit[] = line.split(",");
                    Vector<String> row = new Vector<>();
                    for (String split : lineSplit) {
                        row.add(split.replace("\"", ""));
                    }
                    if (!row.isEmpty()) {
                        tableModel.addRow(row);
                    }
                }
            }
            input.close();
        } catch (IOException ex) {
            ProcessKillerGUI.logsTxt.append("Class: Utilities - Function: listRunningProcesses " + ex.toString() + "\n");
        }
    }

    private void removeJTableRows(JTable table) {
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        dtm.setRowCount(0);
    }

    public void tableSorter(JTable table, int index) {
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);
        ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<>();

        sortKeys.add(new RowSorter.SortKey(index, SortOrder.ASCENDING));

        sorter.setSortKeys(sortKeys);
        sorter.sort();
    }
    
    public ArrayList<String> getProcessListWithName(String name, JTable table){
        ArrayList<String> processList = new ArrayList<>();
        
        for (int i = 0; i <= table.getRowCount() - 1; i++){
            if(table.getValueAt(i, 0).equals(name)){
                processList.add((String) table.getValueAt(i, 0) + "|" + String.valueOf(table.getValueAt(i, 1)));
            }
        }
        
        return processList;
    }
}
