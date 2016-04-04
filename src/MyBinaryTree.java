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
}
