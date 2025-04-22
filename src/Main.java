package src;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Main{
    public static void main(String[] args) {
        //Initilise the database connection
        database.instance();

        JFrame frame = new JFrame();
        frame.setTitle("Daniel and Pavan's GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(730, 720);
        frame.setLocationRelativeTo(null); // move before visible

        JTabbedPane tabPanel = new JTabbedPane();

        // Tab 1 - Home
        JPanel home = new JPanel();
        home.add(new JLabel("Welcome"));
        tabPanel.addTab(" Home", home);

        // Tab 2 - Employees
        JPanel employee = employees.employeepanel();
        tabPanel.addTab(" Employees", employee);

        // Tab 3 - Products
        JPanel product = products.productpanel();
        tabPanel.addTab(" Products", product);

        // Tab 4 - Report
    JPanel reports = report.reportpanel();
    tabPanel.addTab(" Report", reports);

    // Tab 5 - Notifications
    JPanel notify = notifications.notificationpanel();
    tabPanel.addTab(" Notifications", notify);



    tabPanel.addChangeListener(e -> { //CHECK WHEN TABS CHANGE
    int selectedIndex = tabPanel.getSelectedIndex();
    String selectedTitle = tabPanel.getTitleAt(selectedIndex).trim();
    if (selectedTitle.equals("Report")) {
        DefaultTableModel model = (DefaultTableModel)
            ((JPanel) tabPanel.getComponentAt(selectedIndex))
                .getClientProperty("reportTableModel");
        report.refreshReport(model);
    }
    });


   
        frame.add(tabPanel);

    
        frame.setVisible(true);
    }
}
