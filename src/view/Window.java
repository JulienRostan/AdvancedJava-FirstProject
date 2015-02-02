package view;

import modele.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Window extends JFrame
{
	private JTextField nbiteration = new JTextField("30");
	private JTextField lien = new JTextField("");
	private JButton tracer = new JButton("Tracer route");
	private JLabel result = new JLabel("");

	private JPanel pan = new JPanel();
	private JPanel hpan = new JPanel();
	
	private FlowLayout barre = new FlowLayout();
	
	private BorderLayout panlayout = new BorderLayout();

	private Graphe graphe = new Graphe();
	
	public Window()
	{
		// Paramètres de la fenêtre
	    this.setTitle("Traceroute");
	    this.setSize(800, 600);
	    this.setLocationRelativeTo(null);
	    
	    //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    // Ajout du fond
	    pan.setBackground(Color.WHITE);
	    
	    pan.setLayout(panlayout);
	    hpan.setLayout(barre);

	    nbiteration.setPreferredSize(new Dimension(50, 24));
	    lien.setPreferredSize(new Dimension(200, 24));
	    
	    // Listeners
	    tracer.addActionListener(new Tracer());
	    
	    
	    // Ajout du hpan
	    hpan.add(nbiteration);
	    hpan.add(lien);
	    hpan.add(tracer);
	    
	    // Ajout du pan
	    pan.add(hpan, BorderLayout.NORTH);
	    pan.add(result, BorderLayout.CENTER);
	    
	    this.setContentPane(pan);
	    
	    this.setVisible(true);
	}

	class Tracer implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			String ips, commande, buffer, domain, iteration;

			domain = lien.getText();
			iteration = nbiteration.getText();
			
			buffer = Algorythme.confirmDomain(domain);
			
			if(buffer.compareTo("OK") == 0)
			{
				buffer = Algorythme.confirmIteration(iteration);
				
				if(buffer.compareTo("OK") == 0)
				{
					commande = "tracert -h " + iteration + " " + domain;
					
					buffer = Commande.executeCommand(commande);
					
					ips = Algorythme.obtenirIp(buffer);
					//ips = "1.1.1.1\n1.1.1.2\n1.1.1.3\n1.1.1.4\n1.1.1.2\n1.1.1.5\n1.1.1.6\n1.1.1.7\n";
					
					if(ips.compareTo("") == 0)
					{
						result.setText("Nom de domaine ou adresse IP inconnue");
					}
					else
					{
						graphe.ajouterChemin(ips, domain);
					}
				}
				else
				{
					result.setText(buffer);
				}
			}
			else
			{
				result.setText(buffer);
			}
		}
		
	}
}