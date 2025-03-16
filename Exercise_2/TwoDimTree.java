
//class Node
class Node {
	
	public int key; //node's key
	public Node leftChild; //node's left child       
	public Node rightChild; //node's right child 
	public Node root; //points to the root of a Binary Search Tree

	//constructor
	public Node(int key) 
	{
		this.key = key; 
		leftChild = null;
		rightChild = null;
		root = null;
	}

}//end class Node


//class TwoDimTree
public class TwoDimTree {	
	
	private Node root; //the root of a Binary Search Tree

	//constructor
	public TwoDimTree() 
	{ 
		this.root = null; //initialize empty Binary Search Tree
	}

	public void insert(int x, int y)
	{	 
		if(root == null) { //Binary Search Tree is empty
			Node newNode = new Node(x); //creation new node with x-key
			root = newNode; //the root of Binary Search Tree is node with x-key
			//the root of the Binary Search Tree pointed to by the node with x-key is the node with y-key
			newNode.root = new Node(y); 
		}
		else { //Binary Search Tree is not empty
			if(!findX(x)) { //the node with the x-key not exist 
				Node newNode = new Node(x); //creation new node with x-key
				Node current = root; //start at root
				Node parent;
				while(true) { //exits internally
					parent = current;
					if(x < current.key) { //go left
						current = current.leftChild;
						if(current == null) { //if end of the line,
							//insert on left
							parent.leftChild = newNode;
							//the root of the Binary Search Tree pointed to by the node with x-key is the node with y-key
							newNode.root = new Node(y); 
							return;
						}
					} //end if go left
					else { //go right
						current = current.rightChild; 
						if(current == null) { //if end of the line,
							//insert on right
							parent.rightChild = newNode;
							//the root of the Binary Search Tree pointed to by the node with x-key is the node with y-key
							newNode.root = new Node(y);
							return;
						}
					} //end if go right
				} 
			}
			else { //the node with the x-key exist 
				Node node = new Node(y); //creation new node with y-key 
				//node current is the root of the Binary Search Tree pointed to by the node with x-key
				Node current = nodeX.root; 
				Node parent;
				while(true) { //exits internally
					parent = current;
					if(y < current.key) { //go left
						current = current.leftChild;
						if(current == null) { //if end of the line,
							//insert on left
							parent.leftChild = node;
							//the root of the Binary Search Tree pointed to by the node with x-key is the node with y-key
							node.root = new Node(y);
							return;
						} 
					} //end if go left
					else { //go right
						current = current.rightChild;
						if(current == null) { //if end of the line,
							//insert on right
							parent.rightChild = node;
							//the root of the Binary Search Tree pointed to by the node with x-key is the node with y-key
							node.root = new Node(y);
							return;
						}
					} //end if go right
				} //end while	
			}
		} 
	} 

	public boolean find(int x, int y)
	{ 
		if(findX(x)) { //node with x-key exist
			//node current is the root of the Binary Search Tree pointed to by the node with x-key
			Node current = nodeX.root; 
			if(current.key == y) //root of key of the Binary Search Tree pointed to by the node with x-key is y 
				return true;
			while(current.key != y) { //loop until node current of key of the Binary Search Tree pointed to by the node with x-key is y
				if(y < current.key) { //go left
					current = current.leftChild;
				}
				else { //go right
					current = current.rightChild;
				}
				if(current == null) //node current not exist
					return false;
			}
			return true;
		} //node with x-key not exist
		else
			return false;
	}    

	public boolean remove(int x, int y) 
	{	
		if(find(x,y)) { //node with x-key and node with y-key exist 
				//node current is the root of the Binary Search Tree pointed to by the node with x-key
				Node current = nodeX.root;
				//node parent is the root of the Binary Search Tree pointed to by the node with x-key
				Node parent = nodeX.root;
				boolean isLeftChild = true;
				while(current.key != y) { //search for node
					parent = current;
					if(y < current.key) { //go left
						isLeftChild = true;
						current = current.leftChild;
					}
					else { //go right
						isLeftChild = false;
						current = current.rightChild;
					}
					if(current == null) //end of the line,
						return false; //did not find it 
				} //end while
				//if no children, simply delete it
				if(current.leftChild == null && current.rightChild == null) {
					if(current == nodeX.root){ //if node current is root of node with x-key
						//node currentX is the root of the Binary Search Tree
						Node currentX = root; 
						//node parentX is the root of the Binary Search Tree
						Node parentX = root;
						boolean isLeftChildX = true;
						while(currentX.key != x) { //search for node
							parentX = currentX;
							if(x < currentX.key) { //go left
								isLeftChildX = true;
								currentX = currentX.leftChild;
							}
							else { //go right
								isLeftChildX = false;
								currentX = currentX.rightChild;
							}
							if(currentX == null) //end of the line,
								return false; //did not find it 
						}//end while
						//if no children, simply delete it
						if(currentX.leftChild == null && currentX.rightChild == null) {
							if(currentX == root) //if root,
								root = null; //tree is empty
							//disconnect from parent
							else if(isLeftChildX)
								parentX.leftChild = null;
							else
								parentX.rightChild = null;
						}
						//if no right child, replace with left subtree
						else if(currentX.rightChild == null) {
							if(currentX == root)
								root = currentX.leftChild;
							else if(isLeftChildX)
								parentX.leftChild = currentX.leftChild;
							else
								parentX.rightChild = currentX.leftChild;
						}
						//if no left child, replace with right subtree
						else if(currentX.leftChild == null){
							if(currentX == root)
								root = currentX.rightChild;
							else if(isLeftChild)
								parentX.leftChild = currentX.rightChild;
							else
								parentX.rightChild = currentX.rightChild;
						}
						else { //two children, so replace with inorder successor
							//get successor of node to delete (current)
							Node successor = getSuccessor(currentX);
							//connect parent of current to successor instead
							if(currentX == root) 
								root = successor;
							else if(isLeftChildX)
								parentX.leftChild = successor;
							else
								parentX.rightChild = successor;
							successor.leftChild = currentX.leftChild;
						} //end else two children
						//(successor cannot have a left child)
					}
					//disconnect from parent
					else if(isLeftChild)
						parent.leftChild = null; 
					else
						parent.rightChild = null;
				}
				//if no right child, replace with left subtree
				else if(current.rightChild == null) {
					if(current == nodeX.root)
						nodeX.root = current.leftChild;
					else if(isLeftChild)
						parent.leftChild = current.leftChild;
					else
						parent.rightChild = current.leftChild;
				}
				//if no left child, replace with right subtree
				else if(current.leftChild == null){ 
					if(current == nodeX.root)
						nodeX.root = current.rightChild;
					else if(isLeftChild)
						parent.leftChild = current.rightChild;
					else
						parent.rightChild = current.rightChild;
				}
				else { //two children, so replace with inorder successor
					//get successor of node to delete (current)
					Node successor = getSuccessor(current);
					//connect parent of current to successor instead
					if(current == nodeX.root) 
						nodeX.root = successor;
					else if(isLeftChild)
						parent.leftChild = successor;
					else
						parent.rightChild = successor;
					successor.leftChild = current.leftChild;
				} //end else two children
				//(successor cannot have a left child)
				return true; //success
		} 
		else //node with x-key and node with y-key do not exist
			return false; 
	}
	
	//private Node node; //node of the Binary Search Tree
	public void printRange(int x1, int x2, int y1, int y2) 
	{   
		//node = root; //node with x-key is root	         
		if (root == null) //Binary Search Tree is empty
	        return;
	    Node current = root; //node current is the root of the Binary Search Tree
	    while (current != null) { //loop until node current of key of the Binary Search Tree is null
	        if(current.leftChild == null) { //node current has not left child
	        	//checks if the x-key of the node is within the allowed limits
                if (current.key <= x2 && current.key >= x1){
                    System.out.print("*The x-key: " + current.key + "\n"); //display the x-key of the node
                }
                //node nodeY is the root of the Binary Search Tree pointed to by the node current
                Node nodeY = current.root;
                while(nodeY != null) {
                	if(nodeY.leftChild == null) { //node nodeY has not left child
                		//checks if the y-key of the node is within the allowed limits
		                if(nodeY.key <= y2 && nodeY.key >= y1) {
		                	System.out.print("The y-key: " + nodeY.key + "\n"); //display the y-key of the node
		                }
		                nodeY = nodeY.rightChild; //go right
                	}
                	//node nodeY has left child
                	else {
        	            Node predecessorY  = nodeY.leftChild; //node predecessorY is the nodeY left child
        	            while (predecessorY.rightChild != null && predecessorY.rightChild != nodeY)
        	            	predecessorY = predecessorY .rightChild;
        	            if (predecessorY.rightChild == null){
        	            	predecessorY.rightChild = nodeY;
        	            	nodeY = nodeY.leftChild; //go left
        	            }
        	            else {
        	            	predecessorY.rightChild = null; //node predecessorY is the nodeY right child
        	            	//checks if the y-key of the node is within the allowed limits
        		            if (nodeY.key <= y2 && nodeY.key >= y1){
        		            	System.out.print("The y-key: " + nodeY.key + "\n"); //display the y-key of the node
        		            }
        		            nodeY = nodeY.rightChild; //go right
        	            } 
                	}   
                }
	            current = current.rightChild; //go right
	        }
	        //node current has left child
	        else {
	            Node predecessor  = current.leftChild; //node predecessor is the current left child
	            while (predecessor.rightChild != null && predecessor.rightChild != current)
	            	predecessor = predecessor .rightChild;
	            if (predecessor.rightChild == null){
	            	predecessor.rightChild = current;
	                current = current.leftChild; //go left
	            }
	            else {
	            	predecessor.rightChild = null; //node predecessor is the current right child
	            	//checks if the x-key of the node is within the allowed limits
	                if (current.key <= x2 && current.key >= x1){
	                	System.out.print("*The x-key: " + current.key + "\n"); //display the x-key of the node
	                }
	                //node nodeY is the root of the Binary Search Tree pointed to by the node current
	                Node nodeY = current.root;
	                while (nodeY != null) {
	                	if (nodeY.leftChild == null) { //node nodeY has not left child
	                		//checks if the y-key of the node is within the allowed limits
			                if(nodeY.key <= y2 && nodeY.key >= y1) {
			                	System.out.print("The y-key: " + nodeY.key + "\n"); //display the y-key of the node
			                }
			                nodeY = nodeY.rightChild; //go right
	                	}
	                	//node nodeY has left child
	                	else {
	        	            Node predecessorY  = nodeY.leftChild; //node predecessorY is the nodeY left child
	        	            while (predecessorY.rightChild != null && predecessorY.rightChild != nodeY)
	        	            	predecessorY = predecessorY .rightChild;
	        	            if (predecessorY.rightChild == null){
	        	            	predecessorY.rightChild = nodeY;
	        	            	nodeY = nodeY.leftChild; //go left
	        	            }
	        	            else {
	        	            	predecessorY.rightChild = null; //node predecessorY is the nodeY right child
	        	            	//checks if the y-key of the node is within the allowed limits
	        		            if (nodeY.key <= y2 && nodeY.key >= y1){
	        		            	System.out.print("The y-key: " + nodeY.key + "\n"); //display the y-key of the node
	        		            }
	        		            nodeY = nodeY.rightChild; //go right
	        	            } 
	                	}   
	                }
	                current = current.rightChild; //go right
	            }	
	        }
	    } //end while
	}
	
	public Integer findLowestCommonAncestor(int x1, int x2)
	{
        if ((root == null) || (!findX(x1) || !findX(x2))) //Binary Search Tree is empty or there is no node with x1-key or x2-key
            return null;
        if(x1 == x2) //the keys of the two nodes are the same
        	return x1; 
        if(root.key == x1 || root.key == x2) //one of the two keys of the nodes is the key of root
        	return root.key;
        //different cases depending on the keys the nodes have
        if(root.key == x2 && root.key > x1)
        	return x2;
        if(root.key == x1 && root.key < x2)
        	return x1;
        if(root.key > x1 && root.key < x2)
        	return root.key;
        if(root.key > x1 && root.key > x2) {
        	root = root.leftChild;
        	return findLowestCommonAncestor(x1,x2);
        }
        if(root.key < x1 && root.key < x2) {
        	root = root.rightChild;
        	return findLowestCommonAncestor(x1,x2);
        }
        return root.key;
	}
	
	//-*Auxiliary Methods*-
	private Node nodeX = null; //keep the value of the node with x-key
	//method that finds if the node with x-key exists 
	private boolean findX(int x)
	{ 
		if(root != null) { //Binary Search Tree is not empty
			Node current = root; //start at root
			while(current.key != x) { //loop until node current of key is x
				if(x < current.key) { //go left
					current = current.leftChild;
				}
				else { //go right
					current = current.rightChild;
				}
				if(current == null) { //node current not exist
					nodeX = null; //node with x-key is null
					return false;
				}
			}
			//node with x-key exist
			nodeX = current; //node with x-key is current
			return true;
		}
		else //Binary Search Tree is empty
			return false;
	}
	
	//method that returns node with next-highest value after delNode
	//goes to right child, then right child's left descendants
	private Node getSuccessor(Node delNode)
	{
		Node successorParent = delNode;
		Node successor = delNode;
		Node current = delNode.rightChild; //go to right child
		while(current != null) { //until no more
			//left children,
			successorParent = successor;
			successor = current;
			current = current.leftChild; //go to left child
		}
		//if successor not
		if(successor != delNode.rightChild) { //right child
			//make connections
			successorParent.leftChild = successor.rightChild;
			successor.rightChild = delNode.rightChild;
		}
		return successor;
	}

	
	public static void main(String[] args) { }
	
}//end class TwoDimTree

