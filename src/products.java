package src;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
public class products {
    public static JPanel productpanel(){
        JPanel prodPanel = new JPanel(new BorderLayout());

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel title = new JLabel("PRODUCTS", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        titlePanel.add(title);

        JPanel ButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton button = new JButton("Add New Product 🛒");
        button.addActionListener(e -> {
            
             
           
            JDialog dialog = new JDialog((Frame) null, "Add New Product", true);
            dialog.setSize(400, 300);
            dialog.setLayout(new GridLayout(6, 2, 10, 10));
            dialog.setLocationRelativeTo(null);
        
          
            JTextField nameField = new JTextField();
            JTextField quantityField = new JTextField();
            JTextField priceField = new JTextField();
        
          
            JComboBox<String> supplierBox = new JComboBox<>();
            ResultSet res = database.instance().getProducts();
            try {
                while(res.next()){
                    supplierBox.addItem(res.getString(1));
                }
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                System.out.println("errr");
            }

        
            JComboBox<String> categoryBox = new JComboBox<>(new String[] {
                "1 - Beverages", "2 - Condiments", "3 - Confectionery"
            });
        
           
            dialog.add(new JLabel("Product Name:"));
            dialog.add(nameField);
        
            dialog.add(new JLabel("Quantity per Unit:"));
            dialog.add(quantityField);
        
            dialog.add(new JLabel("Unit Price:"));
            dialog.add(priceField);
        
            dialog.add(new JLabel("Supplier:"));
            dialog.add(supplierBox);
        
            dialog.add(new JLabel("Category:"));
            dialog.add(categoryBox);
        
       
            JButton saveBtn = new JButton("Save");
            JButton cancelBtn = new JButton("Cancel");
            dialog.add(saveBtn);
            dialog.add(cancelBtn);
        
         
            saveBtn.addActionListener(ev -> {
                //INSERT SHI
                JOptionPane.showMessageDialog(dialog, "Saved product! ");
                dialog.dispose();
            });
        
            cancelBtn.addActionListener(ev -> dialog.dispose());
        
            dialog.setVisible(true);
            
        });
        
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