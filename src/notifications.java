package src;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class notifications {
    static final String table_name = "customers";

    public static JPanel notificationpanel() {
        JPanel notifyPanel = new JPanel(new BorderLayout());

        
        JLabel title = new JLabel("NOTIFICATIONS", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        JPanel titlePanel = new JPanel();
        titlePanel.add(title);

       
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel searchLabel = new JLabel("Search: ");
        JTextField searchField = new JTextField(20);
        searchField.setToolTipText("Search for results for example name=Daniel,City=Durban");
        searchPanel.add(searchLabel);
        searchPanel.add(searchField);

    
        String[] columns = {"id", "first_name", "last_name", "email_address", "business_phone"};
        DefaultTableModel allModel = new DefaultTableModel(columns, 0);
        database.instance().addToDataModel(allModel, table_name, null, columns);
        JTable allClientsTable = new JTable(allModel);
        JScrollPane allScroll = new JScrollPane(allClientsTable);
        allScroll.setBorder(BorderFactory.createTitledBorder("All Clients"));

        DefaultTableModel inactiveModel = new DefaultTableModel(columns, 0);
        database.instance().addToDataModel(inactiveModel, table_name, new String[]{"notes=inactive"}, columns);
        JTable inactiveClientsTable = new JTable(inactiveModel);
        JScrollPane inactiveScroll = new JScrollPane(inactiveClientsTable);
        inactiveScroll.setBorder(BorderFactory.createTitledBorder("Inactive Clients"));

    
        JPanel tablesPanel = new JPanel();
        tablesPanel.setLayout(new GridLayout(2, 1));
        tablesPanel.add(allScroll);
        tablesPanel.add(inactiveScroll);

       
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        JTextField idField = new JTextField(); idField.setEditable(false);
        JTextField firstNameField = new JTextField();
        JTextField lastNameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField phoneField = new JTextField();
        JTextField notesField = new JTextField();

      
        formPanel.add(new JLabel("First Name:"));
        formPanel.add(firstNameField);
        formPanel.add(new JLabel("Last Name:"));
        formPanel.add(lastNameField);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);
        formPanel.add(new JLabel("Business Phone:"));
        formPanel.add(phoneField);
        formPanel.add(new JLabel("Notes:"));
        formPanel.add(notesField);

      
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton createBtn = new JButton("Create");
        JButton updateBtn = new JButton("Update");
        JButton deleteBtn = new JButton("Delete");
        JButton clearBtn = new JButton("Clear");

        buttonPanel.add(createBtn);
        buttonPanel.add(updateBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(clearBtn);

      
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(formPanel, BorderLayout.NORTH);
        centerPanel.add(buttonPanel, BorderLayout.CENTER);


        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.add(titlePanel);
        topPanel.add(searchPanel);


        notifyPanel.add(topPanel, BorderLayout.NORTH);
        notifyPanel.add(tablesPanel, BorderLayout.CENTER);
        notifyPanel.add(centerPanel, BorderLayout.SOUTH);

      
        createBtn.addActionListener(e -> {  //put func here 
            System.out.println("Create client");
        });
        updateBtn.addActionListener(e -> {
            System.out.println("Update client");
        });
    
        deleteBtn.addActionListener(e -> {
            System.out.println("Delete client");
        });
        clearBtn.addActionListener(e -> {
            idField.setText("");
            firstNameField.setText("");
            lastNameField.setText("");
            emailField.setText("");
            phoneField.setText("");
        });

       
        searchField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { filter(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { filter(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { filter(); }

            private void filter() {
                String text = searchField.getText().toLowerCase();
                filterTable(allModel, text);
                filterTable(inactiveModel, text);
            }

            private void filterTable(DefaultTableModel model, String text) {
                for (int i = 0; i < model.getRowCount(); i++) {
                    boolean matches = false;
                    for (int j = 0; j < model.getColumnCount(); j++) {
                        Object cell = model.getValueAt(i, j);
                        if (cell != null && cell.toString().toLowerCase().contains(text)) {
                            matches = true;
                            break;
                        }
                    }
        
                }
            }
        });

        return notifyPanel;
    }
}

