
public class Node 
{

	// instances is an integer value which is the number of instances of that letter in
	// the string in the .txt file in the "TheText" class.
	int instances;
	// letter is the actual letter itself. it will be a simple letter in form.
	String letter;
	
	// (below) Both global variables can contain null values because
	// not all nodes have children nodes.
	Node leftChild;
	Node rightChild;
	
	Node(int instances, String letter)
	{
		this.instances = instances;
		this.letter = letter;
	}
	
	public String toString()
	{
		return letter + "has a instance no. of " + instances;
	}
}
