public class BinTree 
{

	Node root;
	
	
	void setRoot(Node root)
	{
		this.root = root;
	}
	
	public class Node             // nod class that holds a parent , left and right child, and dataType
	{
		public DataType data;		
		
		public Node left; 
		
		public Node right; 
		
		public Node parent;


		
		public Node(DataType dataClass) 
		{
			data = dataClass;						
			
			left = null;                 //constructor for the Node
			
			right = null;
		}
		
		public void setParent(Node parent) //setter for parent
		{
	        this.parent = parent;
	    }
		
		 public Node parent()   //getter
		 {
		        return parent;
		 }
		 
		 public void setLeft(Node left) //setter for left child
		 {
		        this.left = left;
		 }
		 public Node left()  //getter 
		 {
		        return left;
		 }
		 public void setRight(Node right) //setter for right child
		 {
		        this.right = right;
		 }
		 public Node right() //getter for right child
		 {
		        return right;
		 }
		
		 public String toString() //tostring method
		 {
			 		 
			 return data.letterID + " (" + data.studentID+ ")"  + data.name;
		 }

		 
	}
	
	

	public Node findNode(int ID) 
	{

		Node rootNode = root;   // Begin at the root


		while (rootNode.data.studentID != ID) // if the root id is not equal to what we want, start the search
		{

			if (ID < rootNode.data.studentID)  // to see if it's in the left sub-tree
			{

				rootNode = rootNode.left;    // now root node is equal to the left child

			} 
			else 
			{

				rootNode = rootNode.right;  // now try the right sub-tree

			}

			if (rootNode == null)   // if not in either sub-trees, return null because its not in there
				return null;
		}

		return rootNode;  // return the node
	}
	

	public void addNode(DataType data) 
	{

		Node newNode = new Node(data);     // initializing a new Node 

		if (root == null)      // if the root was null, now the newNode is the root
		{
			root = newNode;
		} 
		else 
		{
			Node rootNode = root;            // set root as the Node
			Node parent;    // parent of the node class

			while (true) 
			{
				parent = rootNode;    // the root is the parent of all parents so we begin here

				// Check if the new NodeClass should go on
				// the left side of the parent NodeClass

				if (data.studentID < rootNode.data.studentID)  // to see if the data is less than the root
				{
					
					rootNode = rootNode.left;        // now root node is equal to it's left child


					if (rootNode == null)     // if left child has no kids
					{

						parent.left = newNode;      // the new Node is to the left of the parent now
						return; 
					}
				} 
				else 
				{ 

					rootNode = rootNode.right;      // if we arrive here, then the data is greater than root, so it goes to the right

					if (rootNode == null) // if the right child has no kids
					{

						parent.right = newNode;   // then new node goes to the right of the parent
						return;
					}
				}
			}
		}
	}


	
	 public boolean delete(int ID)   // deleting based on the id
	 {
		Node parent = root;       // two pointers, parent and current
		
		Node current = root;
		
		boolean isLeftChild = false;    // set is it the left child to false
		
		while(current.data.studentID !=ID)  // while not equal to ID
		{
			parent = current;              // set parent to current
			if(current.data.studentID > ID)         // if the current is greater than the ID
			{
				isLeftChild = true;             // set left child to true
				current = current.left;            //proceed left
			}
			else
			{
				isLeftChild = false;              // set left child to false
				current = current.right;                // set current to right child of current
			}
			if(current == null)          // if current is  null then return false
			{
				return false;
			}
		}
		
		if(current.left == null && current.right == null)  // if this is a leaf
		{
			if(current == root)   // if current is the root
			{
				root = null;       // now the root equals null, because that was the only thing in the tree
			}
			if(isLeftChild == true)    // if left, now the parent's left branch is null
			{
				parent.left = null;
			}
			else
			{
				parent.right = null;     // otherwise the right sub-tree of the parent is null
			}
		}

		else if(current.right == null)    // if the current node's right subtree is null
		{
			if(current == root)      // if current is the root
			{
				root = current.left;    // root is now the left child of current node
			}
			else if(isLeftChild)
			{
				parent.left = current.left;   // if isLeftChild is true, now the parents left child is set to the current's left child
			}
			else
			{
				parent.right = current.left;   //else the parent right sub-tree is now the current's left-subtree
			}
		}
		
		else if(current.left == null)   // if current's left child is null
		{
			if(current == root)    // if current is the root
			{
				root = current.right;    // now root equals the current's right sub-tree
			}
			else if(isLeftChild)   // if leftChild
			{
				parent.left = current.right;  // parent's left subtree is current's right sub now
			}
			else
			{
				parent.right = current.right;  // else parent's right sub-tree is now current's right sub-tree
			}
		}
		
		else if(current.left != null && current.right != null)  // if current has two kids
		{
			
			Node previous = getPrevious(current);   // get previous node from current position
			if(current==root)             // if current was root
			{
				root = previous;            //now root equals previous value
			}
			else if(isLeftChild)   // if it was the left child
			{
				parent.left = previous;    // now parent's left child is previous
			}
			else
			{
				parent.right = previous;   // now parent's right child is previous
			}			
			previous.left = current.left;   //left child of previous is now left child of current
		}		
		return true;		
	}
	 
	 
	 
	 public Node getPrevious(Node deleteNode)  // takes in delete Node
	 {
			Node previous = null;         // previous = null
			Node previousParent = null;     // previous to the parent is null
			Node current = deleteNode.right;      // the current node is equal to the right child of deleted node
			while(current != null)
			{
				previousParent = previous;     
				previous = current;    // set previous to equal current
				current = current.left;    // now current equals the left child of current
			}

			if(previous != deleteNode.right)    
			{
				previousParent.left = previous.right;  //left kid of previous parent is now equal to previous's right child
				previous.right = deleteNode.right;   
			}
			return previous;    // now return previous
		}
	 

	 public void rotateLeft(Node x)  //rotate left
		{
			 Node y = x.right();   //Y is the right child of x
	
		     x.setRight(y.left());   
		     
		     if (y.left() != null)          //if does have a left child then set it to parent of x
		     {
		         y.left().setParent(x);
	
		     }
		     y.setParent(x.parent());
		     if (x.parent() == null) // if x doesnt have a parent
		     {
		         setRoot(y);   //root is now y
		     } 
		     else if (x == x.parent().left()) 
		     {
		         x.parent().setLeft(y);
		     }
		     else 
		     {
		         x.parent().setRight(y);
		     }
		     y.setLeft(x);     //now set left and right values after rotate
		     x.setParent(y);
		}
	 public void rotateRight(Node x)  //rotate right
		{
			 Node y = x.left();   //Y is the left child of x
	
		     x.setLeft(y.right());   
		     
		     if (y.right() != null)          //if does have a right child then set it to parent of x
		     {
		         y.right().setParent(x);
	
		     }
		     y.setParent(x.parent());
		     if (x.parent() == null) // if x doesnt have a parent
		     {
		         setRoot(y);   //root is now y
		     } 
		     else if (x == x.parent().right()) 
		     {
		         x.parent().setRight(y);
		     }
		     else 
		     {
		         x.parent().setLeft(y);
		     }
		     y.setRight(x);     //now set left and right values after rotate
		     x.setParent(y);
		}
	/**	Node rgt;                            // temporary right value
		Node lft = new Node(currNode.data);        // temporary left value that is the current node
		Node rt = new Node (currNode.right.data);   // root that is the right child of the current Node
		if(currNode.right.right != null)               // if the right sub-tree of the right -subtree doesn't equal null
		{
			 rgt = new Node (currNode.right.right.data);   // we set current node to this value, its a lot to type out
		}
		else
		{
			 rgt = new Node (currNode.right.left.data);   // otherwise right is a grand child of the current node
		}
		root = null;           // root is now null
		addNode(rt.data);        // now add the temporary values we have which is right left and root
		addNode(lft.data);
		addNode(rgt.data);*/
	
 
 	/**public void rotateRight(Node currNode)   // time to rotate!
	{
	 
 	    Node oldRoot = root;
        if (root.left != null)
        oldRoot.left = root.left;
        if (root.right != null)
        root = root.right;
        if (root.left != null)
         oldRoot.right = root.left; 
	}
	/**public void rotateLeft(Node currNode)  // now rotate the other way  (to the right)
	{
		Node right2=new Node(currNode.right.data);
		Node rgt = new Node(currNode.right.left.data);   // set these valuse to what they should be
		Node rt = new Node (currNode.data);
	
		root = null;           // root to null
		addNode(rt.data);    // add the three nodes now, because it's balanced
		addNode(rgt.data);
		addNode(right2.data);
	}*/
	
	public static  int RecCountLeft(Node n)    // recursive method for count of left sub-tree
	{
		
		if ( n.left == null)   //if left child is null
		{
			return 0;           //return 0
		}
		
		Node ln = n.left;       
		
	    int count = 1;     // now count  = 1
	    if (ln == null)    // if n is null return 0
	    { 
	    	return 0;
	    }
	    if (ln.left != null)   // if left child isnt null
	    {
	       count += RecCount(ln.left);        // add to the count recursively
	    }
	    if (ln.right != null) //if rigt child isnt null add to count recursively
	    {
	        count += RecCount(ln.right);
	    }
	    return count;   // return the count
	}
	
	public static  int RecCount(Node n)    // recursive count method
	{
	    int count = 1;      
	    if (n == null)      // if n = null return 0
	    {
	    	return 0;
	    }
	    if (n.left != null)    // if left child isnt null add to count
	    {
	       count += RecCount(n.left);
	    }
	    if (n.right != null) 
	    {
	        count += RecCount(n.right);    // if rightchild wasn't empty, add to count
	    }
	    return count;
	}
	
	
	 public void printTree(Node currNode)       // print method for the tree
	 {
			int lvl;
			String form;
			if(currNode !=null)           // making sure current node does not equal null
			{
				lvl=getLevelOfNode(root, currNode.data,1);   // root is height of 1
				form = levelFormat(lvl);
				System.out.println(form+"."+currNode);   //formatting
				printTree(currNode.left);     // print the left child
				printTree(currNode.right);   // now print the right child
			}
	 }

	public String levelFormat(int level)    // takes in the level that the trees are on
	{
			String form = "";
			
			for(int i=0;i<level; i++)   // for loop to stop when i hits max level
			{
				form = form + "\t";     // throw a tab in plus the form
			}
			
			form = form + level;     // now increase form by adding form and level together
			
			return form;    // return form
	}


	 public static int getLevelOfNode( Node n, DataType data,int level) // get level of node
	{
		  if(n == null)   // if n equals null return height of 0
		  {
			  return 0;
		  }
		  
		  if(n.data == data)  // if we find data, return the level it is on
		   return level;
		  
		  int result = getLevelOfNode(n.left,data,level+1);  // result now equals one level higher
		  
		  if(result != 0)
		  { 
		   return result;  // return what we found
		  }
		  result= getLevelOfNode(n.right,data,level+1); // result equals one level higher
		 
		  return result;   // get the result
	}
	 
	 public static void deleteLeaves(Node leaf, Node parent, int LeftorRight)  //delete the leaves
		{
		        if( leaf != null)                            // make sure leaf isnt null
		        { 
		       	
		        	if	(leaf.left == null && leaf.right == null)          // to see if we have hit the leaf of tree
		        	{	
		        		if(LeftorRight == 0)                              //this is the limit now delete left or right
		        		{
		        			parent.left = null;
		        		}
		        		else
		        		{
		        			parent.right = null;
		        		}
		        	}
		        if(leaf.left != null || leaf.right != null)             // delete it if theres a value present
		        {
		            deleteLeaves(leaf.left, leaf, 0);                 // keep calling till levaes are gone
		            deleteLeaves(leaf.right, leaf, 1);
		        }
		    }
		 }

	public static void main(String[] args) //main method for testing
	{
		
		DataType[] data = new DataType[10];  // make an array that holds the 10 different student's data
		data[0] = new DataType();
		data[1] = new DataType();
		data[2] = new DataType();
		data[3] = new DataType();
		data[4] = new DataType();
		data[5] = new DataType();
		data[6] = new DataType();
		data[7] = new DataType();
		data[8] = new DataType();
		data[9] = new DataType();
		BinTree tree = new BinTree();    // create a new  binary tree

		data[0].studentID = 77;        // enter the students id, letter id, name, and gpa, then repeat for students 2-10
		data[0].letterID = 'M';
		data[0].name = "Homer";
		data[0].gpa = 3.5;
		
		data[1].studentID = 87;
		data[1].letterID = 'W';
		data[1].name = "Bart";
		data[1].gpa = 2.0;
		
		data[2].studentID = 69;
		data[2].letterID = 'E';
		data[2].name = "Lisa";
		data[2].gpa = 0.5;
		
		data[3].studentID = 79;
		data[3].letterID = 'O';
		data[3].name = "Marge";
		data[3].gpa = 2.3;
		
		data[4].studentID = 81;
		data[4].letterID = 'Q';
		data[4].name = "Ned";
		data[4].gpa = 1.6;
		
		data[5].studentID = 73;
		data[5].letterID = 'I';
		data[5].name = "Burns";
		data[5].gpa = 1.9;
		
		data[6].studentID = 84;
		data[6].letterID = 'T';
		data[6].name = "Apu";
		data[6].gpa = 2.2;
		
		data[7].studentID = 71;
		data[7].letterID = 'G';
		data[7].name = "Selma";
		data[7].gpa = 2.7;
		
		data[8].studentID = 80;
		data[8].letterID = 'P';
		data[8].name = "Max";
		data[8].gpa = 3.5;
		
		data[9].studentID = 75;
		data[9].letterID = 'K';
		data[9].name = "Nelson";
		data[9].gpa = 3.6;
		
		 tree.addNode(data[0]);     // Then add the 10 nodes to the binary tree
		 tree.addNode(data[1]);
		 tree.addNode(data[2]);
		 tree.addNode(data[3]);
		 tree.addNode(data[4]);
		 tree.addNode(data[5]);
		 tree.addNode(data[6]);
		 tree.addNode(data[7]);
		 tree.addNode(data[8]);
		 tree.addNode(data[9]);
		
	
	tree.printTree(tree.root);       // Print the tree to show it is in the right order and format
	
	System.out.println();
	System.out.println("Student with the ID of 73 will now be deleted:");
	System.out.println(tree.delete(73));         // use delete method
	tree.printTree(tree.root);                 // print tree to show I deleted the correct node
	
	System.out.println();
	System.out.println(" Delete Student with the ID 87");
	System.out.println(tree.delete(87));        // Now I delete the node containing the id of 87 from the tree and print
	tree.printTree(tree.root);
	
	
	System.out.println("Number of Nodes in the left sub-tree:");
	int subtreeCount = RecCountLeft(tree.root);     // using RecCountLeft I get the number of nodes of the left sub-tree of the root
	System.out.println(subtreeCount);
	System.out.println();
	
	
	System.out.println("Printing the tree after the delete leaves:");  // delete leaves method
	BinTree.deleteLeaves(tree.root,null,0);
	tree.printTree(tree.root);
	
	
	System.out.println("Now I'm making a new Bin Tree to show rotations:");
	tree= new BinTree();
	tree.printTree(tree.root);       // new binTree
	System.out.println();
	
	System.out.println("First example, prior to rotation:");   // adding the nodes in the wrong order to create imbalance
	tree.addNode(data[2]);
	tree.addNode(data[0]);
	tree.addNode(data[1]);
	tree.printTree(tree.root);
	System.out.println();
	
	tree.rotateLeft(tree.root);            /// rotate left method is all this example needed, tree is now balanced
	System.out.println("First example, after rotate left:");
	tree.printTree(tree.root);
	System.out.println();
	
	System.out.println("New Bin Tree, to show another rotation example:");   // new bin tree for new example
	tree= new BinTree();
	tree.printTree(tree.root);
	System.out.println();
	
	System.out.println("Second example prior to rotation:");  // add data in an order to create imbalance
	tree.addNode(data[2]);
	tree.addNode(data[1]);
	tree.addNode(data[0]);
	tree.printTree(tree.root);
	System.out.println();
	
	tree.rotateRight(tree.root);
	System.out.println("Second example after rotate right:");   // rotate right to get closer
	tree.printTree(tree.root);
	
	tree.rotateLeft(tree.root);
	
	System.out.println("Second example after rotate left:");  // and then rotate left to fix
	tree.printTree(tree.root);
	System.out.println();

	}
}
