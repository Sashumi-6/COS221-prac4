package src;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class employees {
    public static JPanel employeepanel() {
        JPanel employpanel = new JPanel(new BorderLayout());


        JLabel title = new JLabel("EMPLOYEES", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));

        JTextField searchbar = new JTextField("", 30);
        searchbar.setMaximumSize(new Dimension(120, 30));
        searchbar.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchbar.setToolTipText("To Filter results input like [name,city]");

        JButton searchButton = new JButton("🔎");
    
        searchButton.setMaximumSize(new Dimension(100,30));
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        searchPanel.add(searchbar);
        searchPanel.add(searchButton);

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));;
        titlePanel.add(title);


        
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.add(titlePanel);
        
        topPanel.add(searchPanel);
        topPanel.add(Box.createVerticalStrut(10));

       
        employpanel.add(topPanel, BorderLayout.NORTH);

        
        String[] columnNames = {"First Name", "Last Name", "Address", "Address line 2","City","Region","Postal Code","Phone Num","Active"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);

        JTable table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        employpanel.add(scroll, BorderLayout.CENTER);

        return employpanel;
    }
}
