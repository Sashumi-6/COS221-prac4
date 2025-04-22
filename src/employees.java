package src;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class employees {
    public static JPanel employeepanel() {
        JPanel employeePanel = new JPanel(new BorderLayout());

        // JPanel creations
        JPanel topPanel = new JPanel();
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // JPanel option setting
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.add(titlePanel);

        // create title
        JLabel title = new JLabel("EMPLOYEES", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));

        // create searchbar text field
        JTextField searchbar = new JTextField("", 30);
        searchbar.setMaximumSize(new Dimension(120, 30));
        searchbar.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchbar.setToolTipText("To Filter results input like [name,city]");

        // create search button
        JButton searchButton = new JButton("🔎");
        searchButton.addActionListener(e -> {
            ////SEARCH QUERY GOES HERE
        });
        searchButton.setMaximumSize(new Dimension(100,30));

        String[] columnNames = {"first_name", "last_name", "company", "address", "city", "state_province", "country_region", "zip_postal_code", "home_phone", "business_phone"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        database.instance().addEmplToDataModel(model, columnNames);


        JTable table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // adding elememts
        titlePanel.add(title);
        searchPanel.add(searchbar);
        searchPanel.add(searchButton);
        topPanel.add(searchPanel);
        topPanel.add(Box.createVerticalStrut(10));
        employeePanel.add(topPanel, BorderLayout.NORTH);
        employeePanel.add(scroll, BorderLayout.CENTER);

        return employeePanel;
    }
}
