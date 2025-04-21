package src;
import javax.swing.*;

public class notifications {
    public static JPanel notificationPanel(){
    JPanel notiPanel = new JPanel();
        
    notiPanel.add(new JLabel(
                    "New Tab Added"));

    
    return notiPanel;
   

    }
}
