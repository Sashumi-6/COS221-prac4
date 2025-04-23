package src;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("unused")
public class products {
    private static final String table_name = "products";
    static String[] columnNames = {
        "supplier_ids",
        "product_name",
        "list_price",
        "quantity_per_unit",
        "category"
    };
    static DefaultTableModel  model = new DefaultTableModel(columnNames, 0 );
    static JTable  table = new JTable(model);
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
           
        

        
            JComboBox<String> categoryBox = new JComboBox<>();
        
            database db = database.instance();
            db.populateComboBox(supplierBox, "suppliers", "company");
            db.populateComboBox(categoryBox, "products" , "category");
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
                //TODO Order of params is cooked
                String[] params = {"(SELECT id FROM suppliers WHERE company = '" + supplierBox.getSelectedItem() +"')","'" + nameField.getText() + "'",quantityField.getText(),priceField.getText(), "'" + categoryBox.getSelectedItem().toString() + "'"};
                database.instance().InsertUwU(model, table_name, params, columnNames);
                JOptionPane.showMessageDialog(dialog, "Saved product! ");
                showProducts();
               
            });
        
            cancelBtn.addActionListener(ev -> dialog.dispose());
        
            dialog.setVisible(true);
            
        });
        
        ButtonPanel.add(button);
        
        
        model.setColumnIdentifiers(columnNames);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.add(titlePanel);
        topPanel.add(ButtonPanel);
        topPanel.add(Box.createVerticalStrut(10));


      
        JScrollPane scroll = new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        prodPanel.add(topPanel, BorderLayout.NORTH);
      
        
   
        prodPanel.add(scroll,BorderLayout.CENTER);
        return prodPanel;

    }

    public static void showProducts(){
        database.instance().addToDataModel(model, table_name , null, columnNames);
    }
}