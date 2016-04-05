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

	// this method allows the actual tree to go from many trees (singular.. initially) and changes it into
	// exactly 1 binary tree
	public MyBinaryTree merge(MyBinaryTree tree, String theNewStringKey)
	{
		binaryTreeInArray.add(0, theNewStringKey);

		//binaryTreeInArray.addAll(tree.getArrayList());

		if (binaryTreeInArray.size() == tree.getArrayList().size())
		{
			binaryTreeInArray.addAll(tree.getArrayList());
		}
		else
		{
			if (binaryTreeInArray.size() > tree.getArrayList().size())
			{
				int numDif = binaryTreeInArray.size() - tree.getArrayList().size();

				for(int i = 0 ; i < numDif ; i++)
				{
					binaryTreeInArray.add(null);
				}
				binaryTreeInArray.addAll(tree.getArrayList());
			}
			else if (tree.getArrayList().size() > binaryTreeInArray.size())
			{
				int theNumDif = tree.getArrayList().size() - binaryTreeInArray.size();

				binaryTreeInArray.addAll(tree.getArrayList());

				for(int i = 0 ; i < theNumDif ; i++)
				{
					binaryTreeInArray.add(null);
				}
			}
		}
		return this;
	}

	public ArrayList<String> getArrayList()
	{
		return binaryTreeInArray;
	}
	
	// Traverse the tree to produce a table of characters with their Huffman code.
	public void traverseTheTree(int index)
	{
		System.out.println(binaryTreeInArray.get(index));
		
		int indexLeft = 2 * index + 1;
		int indexRight = 2 * index + 2;
		
		traverseTheTree(indexLeft);
		traverseTheTree(indexRight);
		// one step down right is a 1 and one step down left is a 0.
		
		// going thru one single tree... so look at the one arraylist....
		
		// start at root.. root usually has two children but not neccesarily !!!
		
		// get EACH letter's huffman code, put it into a hashmap and give back the hashmap...?
		
		// so start by getting the LEAF NODE's huffman coding value.. so do it like VERY HARD CODING-LIKE
		// with go left until you reach a node with no children? (via arraylist or a non hard codey way?)
	}
}
