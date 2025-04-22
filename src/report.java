package src;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class report {
    public static JPanel reportpanel() {
        JPanel reportPanel = new JPanel(new BorderLayout());

      
        JLabel title = new JLabel("REPORT", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        JPanel titlePanel = new JPanel();
        titlePanel.add(title);
        reportPanel.add(titlePanel, BorderLayout.NORTH);

        
        String[] columns = {"Warehouse Name", "Category Name", "Product Count"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);
        reportPanel.add(scroll, BorderLayout.CENTER);

        
        reportPanel.putClientProperty("reportTableModel", model); 

        return reportPanel;
    }


    public static void refreshReport(DefaultTableModel model) {
        //MAKE REPORT STUFF - QUERIES

        model.setRowCount(0); 

        
        model.addRow(new Object[]{"Main Warehouse", "Beverages", 42});
        model.addRow(new Object[]{"East Depot", "Confectionery", 17});
        model.addRow(new Object[]{"South Storage", "Condiments", 25});
    }
}



