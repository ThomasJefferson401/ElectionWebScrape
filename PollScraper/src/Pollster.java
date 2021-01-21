import java.io.IOException;
import java.util.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
public class Pollster {
	
	public static void main(String[]args)
	{
		Document doc;
		String url = "https://projects.fivethirtyeight.com/polls/president-general/";
		try 
		{
			doc = Jsoup.connect(url).get();
			Polls[] all = define(doc);
			ArrayList<String> allStates = new ArrayList<String>();
			
			for (Polls i : all)
			{
				int count = 0;
				
				for(int p = 0; p < allStates.size(); p ++)
				{
					if(i.getState().equals(allStates.get(p)))
					{
						count ++;
					}
					
				}
				
				
				if(count == 0)
				{
					allStates.add(i.getState());
				}
			}
			
			ArrayList<Integer>[] statePolls = new ArrayList[allStates.size()];
			ArrayList<String>[] stateWinners = new ArrayList[allStates.size()];
			
			  
	        // initializing 
	        for (int i = 0; i < statePolls.length; i++) 
	        { 
	            statePolls[i] = new ArrayList<Integer>(); 
	            stateWinners[i] = new ArrayList<String>();
	        } 
	        
	        for(int i = 0; i < all.length; i ++)
	        {
	        	for(int j = 0; j < allStates.size(); j ++)
	        	{
	        		if(allStates.get(j).equals(all[i].getState()))
	        		{
	        			statePolls[j].add(all[i].getLead());
	        			if(all[i].getLead() == 0)
	        			{
	        				stateWinners[j].add("Tie");
	        			}
	        			else
	        			{
	        				stateWinners[j].add(all[i].getWinner());
	        			}
	        			
	        		}
	        	}
	        }
	        
	        double[] avg = new double[stateWinners.length];
			
			for(int i = 0; i < stateWinners.length; i ++)
			{
				int trumpCount = 0;
				int bidenCount = 0;
				
				
				for(int j = 0; j < stateWinners[i].size(); j ++)
				{
					if(stateWinners[i].get(j).equals("trump"))
					{
						trumpCount += statePolls[i].get(j);
					}
					else if(stateWinners[i].get(j).equals("biden"))
					{
						bidenCount += statePolls[i].get(j);
					}
				}
				
				
				double totalCount = (double)(bidenCount - trumpCount)/stateWinners[i].size();
				
				avg[i] = totalCount;
			}
			
			int[] electoral = {9,3,11,6,55,9,7,3,3,29,16,4,4,20,11,6,6,8,8,2,1,1,
					10,11,16,10,6,10,3,5,0,6,4,14,5,29,15,3,18,7,7,20,4,9,3,11,38,6,3,13,12,
					5,10,3};
			
			String[] totStates = {"ala", "alaska", "ariz", "ark", "calif", "colo", "conn", "del",
					"dc", "fla", "ga", "hawaii", "idaho", "ill", "ind", "iowa", "kan",
					"ky", "la", "maine", "me1", "me2", "md", "mass", "mich", "minn",
					"miss", "mo", "mont", "neb", "ne2", "nev", "nh", "nj", "nm", "ny", "nc", 
					"nd", "ohio", "okla", "ore", "pa", "ri", "sc", "sd", "tenn", "texas", "utah", "vt",
					"va", "wash", "wva", "wis", "wyo"}; 
			//System.out.println(electoral.length + " " + allStates.size());
			
			int sum = 0;
			for(int i = 0; i < electoral.length; i ++)
			{
				sum += electoral[i];
			}
			System.out.println(sum);
			
			int trumpElect = 0;
			int bidenElect = 0;
				
			for(int i = 0; i < allStates.size(); i ++)
			{
				
				System.out.print(allStates.get(i) + " for ");
				int index = 0;
//				
				for(int j = 0; j < totStates.length; j ++)
				{
					if(allStates.get(i).equals(totStates[j]))
					{
						index = j;
					}
				}
				if(avg[i] >= 0)
				{
					System.out.print("Biden " + avg[i] + " " + electoral[index]);
					bidenElect += electoral[index];
				}
				else
				{
					System.out.print("Trump " + Math.abs(avg[i]) + " " + electoral[index]);
					trumpElect += electoral[index];
				}
				
					
				System.out.println("");
				
			}
			
			System.out.println("               ___________________________");
			System.out.println("* * * * * * *  ___________________________|");
			System.out.println(" * * * * * * * ___________________________|");
			System.out.println("* * * * * * *  ___________________________|");
			System.out.println(" * * * * * * * ___________________________|");
			System.out.println("* * * * * * *  ___________________________|");
			System.out.println(" * * * * * * * ___________________________|");
			System.out.println("* * * * * * *  ___________________________|");
			System.out.println("__________________________________________|");
			System.out.println("__________________________________________|");
			System.out.println("__________________________________________|");
			System.out.println("__________________________________________|");
			System.out.println("__________________________________________|");
			System.out.println("__________________________________________|");
			
			System.out.println("");
			
			if(trumpElect > bidenElect)
			{
				System.out.println("Trump by " + (trumpElect - bidenElect) + " electoral votes");
			}
			else if(trumpElect < bidenElect)
			{
				System.out.println("Biden by " + (bidenElect - trumpElect) + " electoral votes");
			}
				
		}
		catch (IOException e) 
		{
		e.printStackTrace();
		}
		
	}
	/*
	 * _______________________________________________________________
	 */
	
	public static ArrayList<Integer> nums(ArrayList<String> margin)
	{
		ArrayList<Integer> intmargin = new ArrayList<Integer>();
		for( String i : margin)
		{
			if(i.equals("even"))
			{
				intmargin.add(0);
			}
			else
			{
				intmargin.add(Integer.parseInt(i.substring(1)));
			}
			
		}
		
		return intmargin;
		
	}
	
	
	/*
	 * _______________________________________________________________
	 */
	
	
	public static ArrayList<String> names(ArrayList<String> state)
	{
		for (int i = 0; i < state.size(); i ++)
		{
			String word = "";
					for(int j = 0; j < state.get(i).length(); j ++)
					{
						if(state.get(i).charAt(j) != '.' && state.get(i).charAt(j) != '-')
						{
							word += state.get(i).charAt(j);
						}
					}
			state.set(i, word);
		}
		
		return state;
	}
	
	/*
	 * _______________________________________________________________
	 */
	
	public static Polls[] define(Document doc)
	{
		ArrayList<String> winner = new ArrayList<String>();
		ArrayList<String> state = new ArrayList<String>();
		ArrayList<String> margin = new ArrayList<String>();
		
		
		for (Element row : doc.select("table.polls-table tr"))
		{
			winner.add(row.select("td.leader.hide-mobile").text().toLowerCase());
			margin.add(row.select("td.net.hide-mobile").text().toLowerCase());
			
			
			if(row.select("td.race.hide-mobile").text().toLowerCase().equals(""))
			{
				state.add("national");
			}
			else
			{
				state.add(row.select("td.race.hide-mobile").text().toLowerCase());
			}
			
		}
		
		
		for (int i = 0; i < winner.size(); i ++)
		{
			if(state.get(i).equals("national"))
			{
				state.remove(i);
				winner.remove(i);
				margin.remove(i);
				i--;
			}
			
		
		}
		
		ArrayList<Integer> intmargin; 
		intmargin= nums(margin);
		
		state = names(state);
		
		
		
		Polls[] all = new Polls[winner.size()];
			
		for (int i = 0; i < winner.size(); i ++)
		{
			all[i] = new Polls(state.get(i), winner.get(i), intmargin.get(i));
			
		}
	
		return all;
	}

}
