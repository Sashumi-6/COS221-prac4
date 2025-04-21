package src;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Main{
    public static void main(String[] args) {
        database instance = database.instance();

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
                
            

            tabPanel.addTab("🏠Home", home);

            


        //////////////////////////////////////////////////////////

    
            
            frame.add(tabPanel);
           


        
    }
}
