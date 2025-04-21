import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class products {
    public static JPanel productpanel(){
        JPanel prodPanel = new JPanel(new BorderLayout());

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel title = new JLabel("PRODUCTS", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        titlePanel.add(title);

        JPanel ButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton button = new JButton("Add New Product 🛒");
        ButtonPanel.add(button);
        String[] columnNames = {"First Name", "Last Name", "Address", "Address line 2","City","Region","Postal Code","Phone Num","Active"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.add(titlePanel);
        topPanel.add(ButtonPanel);
        topPanel.add(Box.createVerticalStrut(10));


        JTable table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        prodPanel.add(topPanel, BorderLayout.NORTH);
      
        
   
        prodPanel.add(scroll,BorderLayout.CENTER);
        return prodPanel;

    }
}