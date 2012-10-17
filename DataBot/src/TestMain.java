import edu.uci.ics.jung.graph.Graph;


public class TestMain {
	public static void main(String[] args)
	{
		StanfordParser parser = new StanfordParser("G:\\My Documents\\3rd Year Project\\3YP-RnD\\DataBot\\Slashdot0811.txt");
		parser.Parse();
		System.out.println(parser.getComments());
		/*System.out.println(parser.getDataSet().get(1)[0]);*/
		
		Graph<Long, Long> g = parser.toGraph();
		System.out.println("We have " + g.getEdgeCount() + " edges");
		
		
	}
}
