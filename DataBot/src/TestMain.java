
public class TestMain {
	public static void main(String[] args)
	{
		StanfordParser parser = new StanfordParser("G:\\My Documents\\3rd Year Project\\3YP-RnD\\DataBot\\Slashdot0811.txt");
		parser.Parse();
		System.out.println(parser.getComments());
		System.out.println(parser.getNodes().get(1)[0]);
	}
}
