import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class StatsView extends JPanel implements ActionListener
{
    private JTextField steenRoodVeld = new JTextField();
    DobbelsteenModel d;

    public StatsView()
    {
        this.setLayout(new GridLayout(0,2));
        this.add(steenRoodVeld);
    }

    public void actionPerformed( ActionEvent e )
    {
        d = (DobbelsteenModel) e.getSource();

        if(d.getWaarde() == 6) {
            steenRoodVeld.setText("6: " + 1);
        }
    }
}
