package src;
import javax.swing.*;

public class Main{
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("Daniel and Pavan's GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(730,720);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        JTabbedPane tabPanel = new JTabbedPane(); // makes tabbed pane
        

        // Tab 1 - Home
        
       
           JPanel home = new JPanel();
           home.add(new JLabel("Welcome"));
            

           tabPanel.addTab(" Home", home);

            


        //////////////////////////////////////////////////////////

        // Tab 2 - Employees
        JPanel employee = employees.employeepanel();
    
       tabPanel.addTab(" Employees", employee);
            
            
           
            

         //////////////////////////////////////////////////////////
         
         // Tab 3 - Products
         JPanel product = products.productpanel();
    
         tabPanel.addTab(" Products", product);

         //////////////////////////////////////////////////////////
         frame.add(tabPanel);
         frame.setVisible(true);
    }
}
