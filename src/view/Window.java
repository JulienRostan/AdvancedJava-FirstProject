package view;

import modele.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class Window extends JFrame
{
	private JTextField nbiteration = new JTextField("30");
	private JTextField lien = new JTextField("");

	private JButton tracer = new JButton("Tracer route");
	private JButton aleatoire = new JButton("Tracer al�atoirement");
	
	private JLabel it = new JLabel("Nombre de sauts maximum");
	private JLabel li = new JLabel("Entrez un lien ou une adresse IP");
	
	private JLabel result = new JLabel("");

	private JLabel footer = new JLabel("");
	
	private JPanel pan = new JPanel();
	private JPanel header = new JPanel();
	
	private BorderLayout panlayout = new BorderLayout();

	private Graphe graphe = new Graphe();
	
	JFrame test = this;
	
	public Window()
	{
		// Param�tres de la fen�tre
	    this.setTitle("Traceroute");
	    this.setSize(800, 600);
	    this.setLocationRelativeTo(null);
	    
	    //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    // Ajout du fond
	    pan.setBackground(Color.WHITE);
	    
	    pan.setLayout(panlayout);
	    header.setLayout(new GridLayout(2, 3));
	    
	    // Listeners
	    tracer.addActionListener(new Tracer());
	    
	    aleatoire.addActionListener(new TracerAlea());
	    
	    // Ajout du input
	    header.add(it);
	    header.add(li);
	    header.add(aleatoire);
	    
	    header.add(nbiteration);
	    header.add(lien);
	    header.add(tracer);

	    // Ajout du pan
	    pan.add(header, BorderLayout.NORTH);
	    pan.add(result, BorderLayout.WEST);
	    pan.add(footer, BorderLayout.SOUTH);
	    
	    this.setContentPane(pan);
	    
	    this.setVisible(true);
	}

	class Tracer implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			String domain, iteration;

			domain = lien.getText();
			iteration = nbiteration.getText();
			if(domain.compareTo("") != 0)
			{
				footer.setText("Tra�age de " + domain + " en " + iteration + " saut(s) maximum");
				
				result.setText(graphe.tracerGraphe(iteration, domain));
			}
		}
	}

	class TracerAlea implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			int i;
			String domain, iteration;
			Random rand = new Random();
			
			domain = "";
			for(i=0; i<3; i++)
			{
				domain += (rand.nextInt(256) + 1) + ".";
			}
			domain += (rand.nextInt(256) + 1);
			
			iteration = nbiteration.getText();
			
			footer.setText("Tra�age de " + domain + " en " + iteration + " saut(s) maximum");
			
			result.setText(graphe.tracerGraphe(iteration, domain));
		}
	}
}
