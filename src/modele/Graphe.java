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

	public void ajouterChemin(String ips, String domain)
	{

		int i, j;
		ArrayList<String> listeIps = new ArrayList<String>();
		String buffer;
		
		buffer = "";
		j = 0;
		for(i=0; i<ips.length(); i++)
		{
			if(ips.charAt(i) == '\n')
			{
				listeIps.add(j, buffer);
				buffer = "";
				j++;
			}
			else
			{
				buffer += ips.charAt(i);
			}
		}
		if(listeIps.get(0).compareTo(buffer) == 0)
		{
			listeIps.add(j, domain);
		}
		else
		{
			j--;
			if(graph.getNode(domain) == null)
			{
				graph.addNode(domain).addAttribute("ui.label", domain);
			}
		}
		
		listeIps.remove(0);
		listeIps.add(0, "moi");
		
		for(i=1; i<=j; i++)
		{
			if(graph.getNode(listeIps.get(i)) == null)
			{
				graph.addNode(listeIps.get(i)).addAttribute("ui.label", listeIps.get(i));
			}
		}
		
		for(i=0; i<j; i++)
		{
			if(listeIps.get(i).compareTo(listeIps.get(i + 1)) != 0)
			{
				if(graph.getEdge(listeIps.get(i) + listeIps.get(i + 1)) == null
						&& graph.getEdge(listeIps.get(i + 1) + listeIps.get(i)) == null)
				{
					graph.addEdge(listeIps.get(i) + listeIps.get(i + 1), listeIps.get(i), listeIps.get(i + 1));
				}
			}
		}
	}

}
