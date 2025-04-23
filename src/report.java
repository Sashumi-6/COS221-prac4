package src;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;

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


}



