package modele;

public class Algorythme
{
	public static String obtenirIp(String commande)
	{
		String ips = "";
		int i, j, k;
		j = 0;
		for(i=commande.length() - 1; i>=0; i--)
		{
			if(commande.charAt(i) == '\n')
			{
				if(j > 1)
				{
					i--;
					if(commande.charAt(i) != '\n')
					{
						if(commande.charAt(i) < '0' || commande.charAt(i) > '9')
						{
							i--;
						}
						if(commande.charAt(i) < '0' || commande.charAt(i) > '9')
						{
							i--;
						}
						if(commande.charAt(i) >= '0' && commande.charAt(i) <= '9')
						{
							for(k=0; k<4; k++)
							{
								while(commande.charAt(i) >= '0' && commande.charAt(i) <= '9')
								{
									ips += commande.charAt(i);
									i--;
								}
								if(k != 3)
								{
									ips += ".";
								}
								else
								{
									ips += "\n";
								}
								i--;
							}
						}
					}
					else
					{
						i--;
						while(commande.charAt(i) != '\n')
						{
							i--;
						}
						
						if(commande.charAt(i) < '0' || commande.charAt(i) > '9')
						{
							i--;
						}
						if(commande.charAt(i) < '0' || commande.charAt(i) > '9')
						{
							i--;
						}
						if(commande.charAt(i) >= '0' && commande.charAt(i) <= '9')
						{
							for(k=0; k<4; k++)
							{
								while(commande.charAt(i) >= '0' && commande.charAt(i) <= '9')
								{
									ips += commande.charAt(i);
									i--;
								}
								if(k != 3)
								{
									ips += ".";
								}
								else
								{
									ips += "\n";
								}
								i--;
							}
						}
						i = 0;
					}
				}
				else
				{
					j++;
				}
			}
		}
		commande = ips;
		ips = "";
		for(i=commande.length() - 2; i>=0; i--)
		{
			ips += commande.charAt(i);
		}
		return ips;
	}
	
	public static String toHTML(String s)
	{
		String html = "<html>";
		for(int i=0; i<s.length(); i++)
		{
			if(s.charAt(i) == '\n')
			{
				html += "<br>";
			}
			else
			{
				html += s.charAt(i);
			}
		}
		html += "</html>";
		return html;
	}

	public static String confirmDomain(String domain)
	{
		int i;
		if(domain.compareTo("") != 0)
		{
			for(i=0; i<domain.length(); i++)
			{
				if(domain.charAt(i) == ' ')
				{
					return "pas d'espace !";
				}
			}
			return "OK";
		}
		return "entrez un nom de domaine";
	}

	public static String confirmIteration(String iteration)
	{
		int i;
		if(iteration.compareTo("") != 0)
		{
			for(i=0; i<iteration.length(); i++)
			{
				if(iteration.charAt(i) < '0' && iteration.charAt(i) > '9')
				{
					return "que des chiffres !";
				}
			}
			return "OK";
		}
		return "entrez un nombre d'iteration";
	}
}
