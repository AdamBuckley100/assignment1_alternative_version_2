import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyBinaryTree
{
	ArrayList<String> binaryTreeInArray = new ArrayList<String>();

	public MyBinaryTree(String key)
	{
		binaryTreeInArray.add(key);
	}

	public MyBinaryTree merge(MyBinaryTree tree, String theNewStringKey)
	{
		
		binaryTreeInArray.add(0, theNewStringKey);
		
		binaryTreeInArray.addAll(tree.getArrayList());
		
		return this;
	}
	
	public ArrayList<String> getArrayList()
	{
		return binaryTreeInArray;
	}
}
