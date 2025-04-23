package src;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

@SuppressWarnings("unused")
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

    
        String[] columns = {"id", "first_name", "last_name", "email_address", "business_phone", "notes"};
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

       
        JPanel formPanel = new JPanel(new GridLayout(3, 4, 10, 10));
        JTextField idField = new JTextField();
        JTextField firstNameField = new JTextField();
        JTextField lastNameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField phoneField = new JTextField();
        JTextField notesField = new JTextField();

      
        formPanel.add(new JLabel("Identification Number:"));
        formPanel.add(idField);
        formPanel.add(new JLabel("Business Phone:"));
        formPanel.add(phoneField);
        formPanel.add(new JLabel("First Name:"));
        formPanel.add(firstNameField);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);
        formPanel.add(new JLabel("Last Name:"));
        formPanel.add(lastNameField);
        formPanel.add(new JLabel("Notes:"));
        formPanel.add(notesField);

      
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton createBtn = new JButton("Create");
        JButton updateBtn = new JButton("Update");
        JButton deleteBtn = new JButton("Delete");
        JButton clearBtn = new JButton("Clear");

        createBtn.setToolTipText("If you select create, No matter what you insert into the id field it doesn't matter");

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
            System.out.println("Creating client...");
            String[] params = {
                firstNameField.getText(), lastNameField.getText(), emailField.getText(),
                phoneField.getText(), notesField.getText()
            };
            for (int i = 0 ; i < params.length ; i++) {
                if (!params[i].isEmpty()) params[i] = "'" + params[i] + "'";
                System.out.println(params[i]);
            }

            database.instance().InsertUwU(allModel, table_name, params, "first_name", "last_name", "email_address", "business_phone", "notes");
            database.instance().addToDataModel(allModel, table_name, null, columns);
            database.instance().addToDataModel(inactiveModel, table_name, new String[]{"notes=inactive"}, columns);
        });
        updateBtn.addActionListener(e -> {
            System.out.println("Updating client(s)...");
            String[] params = {
                idField.getText(), firstNameField.getText(), lastNameField.getText(),
                emailField.getText(), phoneField.getText(), notesField.getText()
            };  
            for (int i = 0 ; i < params.length ; i++) {
                if (!params[i].isEmpty()) {
                    if (!params[i].equalsIgnoreCase("null")) params[i] = "'" + params[i] + "'";
                }
                System.out.println(params[i]);
            }

            database.instance().UpdateUwU(allModel, table_name, params, columns);
            database.instance().addToDataModel(allModel, table_name, null, columns);
            database.instance().addToDataModel(inactiveModel, table_name, new String[]{"notes=inactive"}, columns);
        });
    
        deleteBtn.addActionListener(e -> {
            System.out.println("Deleting client(s)...");
            String[] params = {
                idField.getText(), firstNameField.getText(), lastNameField.getText(),
                emailField.getText(), phoneField.getText(), notesField.getText()
            };
            int nonEmpty = 0;
            for (String p : params) { if (!p.isEmpty()) nonEmpty++; }
            
            if (nonEmpty > 0) {
                String[] nonEmptyCols = new String[nonEmpty];
                String[] nonEmptyParams = new String[nonEmpty];
                for (int i = 0, j = 0 ; i < nonEmpty ; i++) {
                    if (!params[i].isEmpty()) {
                        nonEmptyCols[j] = columns[i];
                        nonEmptyParams[j++] = params[i];
                    }
                }

                database.instance().DeletetUwU(allModel, table_name, nonEmptyParams, nonEmptyCols);
                database.instance().addToDataModel(allModel, table_name, null, columns);
                database.instance().addToDataModel(inactiveModel, table_name, new String[]{"notes=inactive"}, columns);
            }
        });
        clearBtn.addActionListener(e -> {
            idField.setText("");
            firstNameField.setText("");
            lastNameField.setText("");
            emailField.setText("");
            phoneField.setText("");
        });

       
        searchField.addActionListener(e -> {
            String searchParams = searchField.getText();
            if (!searchParams.equals("") && searchParams.contains("=")) {
                String params_for_inactive_str = searchParams + ",notes=inactive";

                String[] params = searchParams.split(","),
                params_for_inactive = params_for_inactive_str.split(",");

                database.instance().addToDataModel(allModel, table_name, params, columns);
                database.instance().addToDataModel(inactiveModel, table_name, params_for_inactive, columns);
            } else {
                database.instance().addToDataModel(allModel, table_name, null, columns);
                database.instance().addToDataModel(inactiveModel, table_name, new String[]{"notes=inactive"}, columns);
            }
        });

        return notifyPanel;
    }
}

