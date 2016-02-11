import javax.swing.*;
import java.awt.*;

public class DobbelsteenMVC extends JApplet
{
	DobbelsteenModel model;             // het model
	TekstView tekstView;                // view
	DobbelsteenView dobbelsteenView;    // view
    StatsView statsView;                // view
	DobbelsteenController controller;   // Controller
	
	public void init()
	{
		resize(250,200);
        
		// Maak het model
		model = new DobbelsteenModel();
        
        // Maak de controller en geef hem het model
		controller = new DobbelsteenController(model);
        controller.setBackground(Color.cyan);
        getContentPane().add(controller,BorderLayout.NORTH);
        
        // Maak de views
        dobbelsteenView = new DobbelsteenView(Color.white);
        dobbelsteenView.setBackground(Color.black);
        getContentPane().add(dobbelsteenView,BorderLayout.CENTER);
        statsView = new StatsView();
        statsView.setBackground(Color.black);
        getContentPane().add(statsView,BorderLayout.EAST);
        tekstView = new TekstView();
        tekstView.setBackground(Color.green);
        getContentPane().add(tekstView,BorderLayout.SOUTH);
        
        // Registreer de views bij het model
        model.addActionListener(statsView);
        model.addActionListener(tekstView);
        model.addActionListener(dobbelsteenView);
        
        // Eerste worp
        model.gooi();
	}
}
