package modele;

import java.util.ArrayList;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

public class Graphe
{
	Graph graph;
	
	public Graphe()
	{
		graph = new SingleGraph("Network");
		graph.addNode("moi").addAttribute("ui.label", "moi");
		graph.display();
	}
	
	public void ajouterChemin(ArrayList<TracerouteItem> listeIps, String domain)
	{
		int i, j, k = 0;
		boolean ok = true;
		boolean okdomain = false;
		String ip;
		
		ip = "";
		
		// Ajout des noeuds sauf le dernier
		for(i=1; i<listeIps.size() - 1; i++)
		{
			ip = listeIps.get(i).getAddress();
			if(graph.getNode(ip) == null)
			{
				graph.addNode(ip).addAttribute("ui.label", ip);
				graph.getNode(ip).addAttribute("ui.style", "fill-color: rgb(100,100,255);");
			}
		}
		
		if(listeIps.size() > 1)
		{
			// Si on a atteind le domaine
			if(listeIps.get(0).getAddress().compareTo(listeIps.get(listeIps.size() - 1).getAddress()) == 0)
			{
				okdomain = true;
			}
			if(okdomain)
			{
				// On l'ajoute en vert
				if(graph.getNode(domain) == null)
				{
					graph.addNode(domain).addAttribute("ui.label", domain);
				}
				graph.getNode(domain).addAttribute("ui.style", "fill-color: rgb(100,255,100);");
			}
			else
			{
				// On l'ajoute en rouge
				if(graph.getNode(domain) == null)
				{
					graph.addNode(domain).addAttribute("ui.label", domain);
					graph.getNode(domain).addAttribute("ui.style", "fill-color: rgb(255,100,100);");
				}
				ip = listeIps.get(i).getAddress();
				if(graph.getNode(ip) == null)
				{
					graph.addNode(ip).addAttribute("ui.label", ip);
					graph.getNode(ip).addAttribute("ui.style", "fill-color: rgb(100,100,255);");
				}
			}
		
			// On ajoute le premier arc
			ip = listeIps.get(1).getAddress();
			
			if(graph.getEdge(ip + "moi") == null
					&& graph.getEdge("moi" + ip) == null)
			{
				graph.addEdge(ip + "moi", ip, "moi");
			}
			
			j = listeIps.get(1).getLine();
			
			for(i=1; i<listeIps.size() - 1; i++)
			{
				if(listeIps.get(i + 1).getLine() != j)
				{
					if(ok == true)
					{
						if(i + 1 != listeIps.size() - 1 || okdomain == false)
						{
							if(graph.getEdge(listeIps.get(i).getAddress() + listeIps.get(i + 1).getAddress()) == null
									&& graph.getEdge(listeIps.get(i + 1).getAddress() + listeIps.get(i).getAddress()) == null)
							{
								graph.addEdge(listeIps.get(i).getAddress() + listeIps.get(i + 1).getAddress(),
										listeIps.get(i).getAddress(), listeIps.get(i + 1).getAddress());
							}
						}
						else
						{
							if(graph.getEdge(listeIps.get(i).getAddress() + domain) == null
									&& graph.getEdge(domain + listeIps.get(i).getAddress()) == null)
							{
								graph.addEdge(listeIps.get(i).getAddress() + domain,
										listeIps.get(i).getAddress(), domain);
							}
						}
						j = listeIps.get(i).getLine();
						k = i;
					}
					else
					{
						for(j=k + 1; j<=i; j++)
						{
							if(graph.getEdge(listeIps.get(j).getAddress() + listeIps.get(i + 1).getAddress()) == null
									&& graph.getEdge(listeIps.get(i + 1).getAddress() + listeIps.get(j).getAddress()) == null)
							{
								graph.addEdge(listeIps.get(j).getAddress() + listeIps.get(i + 1).getAddress(),
										listeIps.get(j).getAddress(), listeIps.get(i + 1).getAddress());
							}
						}
						ok = false;
					}
				}
				else
				{
					if(graph.getEdge(listeIps.get(i - 1).getAddress() + listeIps.get(i + 1).getAddress()) == null
							&& graph.getEdge(listeIps.get(i + 1).getAddress() + listeIps.get(i - 1).getAddress()) == null)
					{
						graph.addEdge(listeIps.get(i - 1).getAddress() + listeIps.get(i + 1).getAddress(),
								listeIps.get(i - 1).getAddress(), listeIps.get(i + 1).getAddress());
					}
					ok = true;
				}
			}
		}
		else
		{
			// On l'ajoute en rouge
			if(graph.getNode(domain) == null)
			{
				graph.addNode(domain).addAttribute("ui.label", domain);
				graph.getNode(domain).addAttribute("ui.style", "fill-color: rgb(255,100,100);");
			}
		}
	}

	public String tracerGraphe(String iteration, String domain)
	{
		String buffer;

        Traceroute tr;
        if(Algorythme.getOS(System.getProperty("os.name")).compareTo("Windows") == 0)
        {
        	tr = new WindowsTraceroute();
        }
        else
        {
        	tr = new LinuxTraceroute();
        }
        
		buffer = Algorythme.confirmIteration(iteration);

		if(buffer.compareTo("OK") == 0)
		{
			buffer = Algorythme.confirmDomain(domain);

			if(buffer.compareTo("OK") == 0)
			{
		        ArrayList<TracerouteItem> l = tr.traceroute("-h " + iteration + " " + domain);
		        
				if(l.size() <= 0)
				{
					return "Nom de domaine ou adresse IP inconnue";
				}
				else
				{
					buffer = "";
					for(int i=0; i<l.size(); i++)
					{
						buffer += l.get(i).getAddress() + "\n";
					}
					ajouterChemin(l, domain);
					buffer = Algorythme.toHTML(buffer);
					return buffer;
				}
			}
		}
		return buffer;
	}
}
