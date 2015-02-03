package modele;

public class Algorythme
{
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
				if(iteration.charAt(i) < '0' || iteration.charAt(i) > '9')
				{
					return "que des chiffres !";
				}
			}
			return "OK";
		}
		return "entrez un nombre d'iteration";
	}

	public static String getOS(String name)
	{
		int i;
		String OS;
		i = 0;
		OS = "";
		
		while(name.charAt(i) != ' ')
		{
			OS += name.charAt(i);
			i++;
		}
		return OS;
	}
}
