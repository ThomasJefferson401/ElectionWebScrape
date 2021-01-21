
public class Polls {
	
	private int lead;
	private String state;
	private String winner;
	
	Polls(String s, String w, int l)
	{
	
		lead = l;
		state = s;
		winner = w;
		
	}
	
	public String getWinner() { return winner;}
	public String getState() { return state;}
	public int getLead() { return lead;}

}
