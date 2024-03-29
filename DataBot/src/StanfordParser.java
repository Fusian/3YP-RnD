import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.graph.util.Pair;


public class StanfordParser {

	private String filePath;
	private String fileComments = "" ;
	private ArrayList<long[]> edges = new ArrayList<long[]>();
	private Graph<Long, Long> graphRep = new SparseMultigraph<Long, Long>();
	
	
	StanfordParser(String filePath)
	{
		this.filePath = filePath;
	}
	
	public boolean Parse() 
	{
		boolean error = false;
		try
		{
			FileInputStream fstream = new FileInputStream(filePath);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			String tempArr[];
			long insertArr[] = new long[2];
			
			//Code from http://www.roseindia.net/java/beginners/java-write-to-file.shtml
			while ((strLine = br.readLine()) != null)   {
				if(strLine.startsWith("#") == true )
				{
					fileComments += strLine + "\n";
				}
				else
				{
					tempArr = strLine.split("\t");
					for(int i = 0; i < insertArr.length; i++)
					{
						insertArr[i] = Long.parseLong(tempArr[i]);
					}
					edges.add(insertArr);
				}
			}
			in.close();
		}
		catch (FileNotFoundException e){
			System.err.println("File not found!");
			error = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("I/O Error!");
			error = true;
		}
		return error;
	}
	
	public ArrayList<long[]> getDataSet()
	{
		return edges;
	}
	
	public String getComments() 
	{
		return fileComments;
	}
	
	public Graph<Long, Long> toGraph()
	{
		//Add vertices to graph first
		for(long[] arr : edges)
		{
			graphRep.addVertex(arr[0]);
		}
		
		//Add edges to graph
		Long edgeName = (long) 0;
		EdgeType edgeType = EdgeType.DIRECTED;
		Pair<Long> vPair;
		
		for(long[] arr: edges)
		{
			vPair = new Pair<Long>(arr[0], arr[1]);
			edgeName++;
			graphRep.addEdge(edgeName, vPair, edgeType);
			
		}
		
		return graphRep;
	}
}
