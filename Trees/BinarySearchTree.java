
public class BinarySearchTree
{
	BSTNode root = null;

	public void insert(BSTNode newNode)
	{
		if (root == null) root = newNode;
		else
		{
			BSTNode runner = root;
			while (true)
			{
				int comp = newNode.item.compareTo(runner.item);
				if (comp <= 0)
				{
					if (runner.left == null)
					{
						runner.left = newNode;
						break;
					}
					else runner = runner.left;
				}
				else if (comp > 0)
				{
					if (runner.right == null)
					{
						runner.right = newNode;
						break;
					}
					else runner = runner.right;
				}
			}
		}
	}
	
	public void recursiveInsert(BSTNode newNode)
	{
		if(root == null)
			root = newNode;
		else
			recInsert(root, newNode);
	}
	
	private void recInsert(BSTNode runner, BSTNode newNode)
	{
		int comp = newNode.item.compareTo(runner.item);
		if(comp <= 0)
		{
			if (runner.left == null)
			{
				runner.left = newNode;
				return;
			}
			else recInsert(runner.left, newNode);
		}
		else if (comp > 0)
		{
			if (runner.right == null)
			{
				runner.right = newNode;
				return;
			}
			else recInsert(runner.right, newNode);
		}
		
	}
	
	public void traverse()
	{
		traverseInOrder(root);
	}
	
	private void traverseInOrder(BSTNode node)
	{
		if(node == null)
			return;
		traverseInOrder(node.left);
		System.out.print(node.item + " ");
		traverseInOrder(node.right);
	}
}

