
class List {
	
	private Node head; //head node
	private Node tail; //tail node
	
	public static class Node {
		
		private Node next; //next node in list
		private Node prev; //previous node in list
		private int counter; //it has the information of how many iterators indicate to the specific node

		public int key; //key
		public Object data; //data

		//constructor
		public Node(int key, Object data) {
			this.key = key;
			this.data = data;
		}
		
		private void linkToNext(Node node) {
			this.next = node; //link to the next node
		}
		
		private void linkToPrev(Node node) {
			this.prev = node; //link to the previous node
		}
		
	} //end class Node

	public static class Iter {

		private Node node; // create node which the iterator will indicate
		
		private boolean typeForwardIterator = false; //if is true, forward iterator 
		private boolean typeBackwardIterator = false; //if is true, backward iterator
		
		//constructor
		private Iter() { }

		public static Iter first(List list) {
			
			Iter forwardIterator = new Iter(); // create forward iterator
			
			forwardIterator.typeForwardIterator = true; //the iterator is forward iterator
			
			if(!list.empty()) {
				forwardIterator.node = list.head.next; //indicates to the first node in the list
				forwardIterator.node.counter++;
			}
			else {
				forwardIterator.node = list.tail; //indicates to the end of the list
			}
			
			return forwardIterator; //return forward iterator
			
		}

		public static Iter last(List list) {
			
			Iter backwardIterator = new Iter(); // create backward iterator
			
			backwardIterator.typeBackwardIterator = true; //the iterator is backward iterator
			
			if(!list.empty()) {
				backwardIterator.node = list.tail.prev; //indicates to the last node in the list
				backwardIterator.node.counter++;
			}
			else {
				backwardIterator.node = list.head; //indicates to the start of the list
			}
			
			return backwardIterator; //return backward iterator
			
		}

		public static Iter at_forward(Iter pos) {
			
			Iter forwardIterator = new Iter(); // create forward iterator
			
			forwardIterator.typeForwardIterator = true; //the iterator is forward iterator
			forwardIterator = pos; //indicates to the point in the list, pointed to by the iterator pos
			forwardIterator.node.counter++;
			
			return forwardIterator; //return forward iterator
			
		}

		public static Iter at_backward(Iter pos) {
			
			Iter backwardIterator = new Iter(); // create backward iterator
			
			backwardIterator.typeBackwardIterator = true; //the iterator is backward iterator
			backwardIterator = pos; //indicates to the point in the list, pointed to by the iterator pos
			backwardIterator.node.counter++;
			
			return backwardIterator; //return backward iterator
			
		}

		public void terminate() {
			
			if(typeForwardIterator == true) { //if iterator is forward iterator
				//then it moves to the next node of the one it points to 
				//until terminated , which means to indicate to the end of the list
				while(next()) {
					next();
				}
			}
			else if(typeBackwardIterator == true) { //if iterator is backward iterator
				//then it moves to the previous node of the one it points to 
				//until terminated , which means to indicate to the start of the list
				while(prev()) {
					prev();
				}
			}
			
			node.counter--; //decrease the point, because the iterator stops to indicate the node
			
		}

		public boolean prev() {
			
			//if iterator is backward iterator and unterminated then it moves to the previous node of the one it points to
			if(typeBackwardIterator == true && node.prev != null) { 
				node.counter--;
				node = node.prev;
				node.counter++;
				return true;
			}
			else //is forward iterator or terminated
				return false;
			
		}

		public boolean next() {
			
			//if iterator is forward iterator and unterminated then it moves to the next node of the one it points to
			if(typeForwardIterator == true && node.next != null) { 
				node.counter--;
				node = node.next;
				node.counter++;
				return true;
			}
			else //is backward iterator or terminated
				return false;
			
		}

		public boolean end() { 
			
			//returns true only if the iterator has terminated
			if((typeForwardIterator == true && node.next == null) ||(typeBackwardIterator == true && node.prev == null)) {
				return true;
			}
			else 
				return false;
			
		}

		public Node key_data() { 
			
			if(end()) //the iterator has terminated 
				return null;
			else {
				Node newNode = new Node(node.key, node.data); //creation new node which has the contents of the node it points to iterator
				return newNode; //return new node
			}
			
		}
		
	} //end class Iter

	public List() {
		
		head = new Node(0,null); //initialize head
		tail = new Node(0,null); //initialize  tail
		head.linkToNext(tail); //head ---> tail
		tail.linkToPrev(head); //head <--- tail
		
	}

	public boolean empty() {
		
		return (head.next == tail); //check if list is empty
		
	}

	public boolean insAfter(Iter iter, int key, Object data) {
		
		Node insertNode = new Node(key, data); //creation of the node to be inserted with elements of the method parameters 

		if(iter == null  || iter.node == tail) //check if iter is null or node indicates the end of the list 
			return false;
		else {
			
			Node tmp = iter.node; //helper node to store the node pointed to by the iterator

			insertNode.linkToPrev(tmp); 	 //tmp <--- insertNode
			insertNode.linkToNext(tmp.next); //insertNode ---> tmp.next
			tmp.linkToNext(insertNode);    	 //tmp ---> insertNode
			insertNode.next.linkToPrev(insertNode); //insertNode <--- insertNode.next
			
			return true;
			
		}
		
	}

	public boolean insBefore(Iter iter, int key, Object data) {
		
		Node insertNode = new Node(key, data);//creation of the node to be inserted with elements of the method parameters 

		if(iter == null  || iter.node == head) //check if iter is null or node indicates the start of the list 
			return false;
		else {
			
			Node tmp = iter.node; //helper node to store the node pointed to by the iterator

			insertNode.linkToPrev(tmp.prev); //tmp.prev <--- insertNode
			insertNode.linkToNext(tmp);		 //insertNode ---> tmp
			tmp.prev.linkToNext(insertNode); //tmp.prev ---> insertNode
			tmp.linkToPrev(insertNode);		 //insertNode <--- tmp
			
			return true;
			
		}
		
	}

	public Node removeAt(Iter iter) {

		if(!(iter.end() == true) && (iter.node.counter == 1)) { //check if iter has terminated and is not the only iterator that points to node him

			Node deleteNode = iter.node; //creation of the node to be deleted with elements of iter 

			deleteNode.prev.linkToNext(deleteNode.next); //deleteNode.prev ---> deleteNode.next
			deleteNode.next.linkToPrev(deleteNode.prev); //deleteNode.prev <--- deleteNode.next
			
			if(iter.typeForwardIterator == true) //if iterator is forward iterator then it moves to the next node of the one it points to
				iter.next();
			else if(iter.typeBackwardIterator == true) //if iterator is backward iterator then it moves to the previous node of the one it points to
				iter.prev();
			
			return deleteNode; //returns the deleted node
		}
		else 
			return null; 
		
	}
	
} //end class List


class ListDemo {
	
	public static void sort(List list) {
		
		List.Iter inI,outI;
	    for(outI = List.Iter.first(list); !outI.end(); outI.next()) { //check item at out
	    	 List.Node tmp = new List.Node(outI.key_data().key,outI.key_data().data); //remove marked item
	    	 inI = outI; //starts shifts at out
	    	 while(inI.key_data().key > 0 && inI.key_data().key >= tmp.key) { //until one is smaller
	    		 //shift item to right
	    		 inI.key_data().key = List.Iter.at_backward(inI).key_data().key;
	    		 inI.key_data().data = List.Iter.at_backward(inI).key_data().data;
	    		 inI = List.Iter.at_backward(inI); //go left on position
	    	 }
	    	 //insert marked item
    		 inI.key_data().key = tmp.key;
    		 inI.key_data().data = tmp.data;
	    } //end for
	    
	} //end sort
	
	public static void print(List list) {
		// forward iterator "iter" eventually terminates (moves after last data node)
		for(List.Iter iter = List.Iter.first(list); !iter.end(); iter.next())
			System.out.print(iter.key_data().key + " ");
		System.out.println("");
	}

	public static List.Node find(int key, List list) {
		// forward iterator "iter" may not terminate (may not reach list's end)
		for(List.Iter iter = List.Iter.first(list); !iter.end(); iter.next())
			if (key == iter.key_data().key) {
				List.Node result = iter.key_data();
				iter.terminate();  // ensure future removability of node
				return result;
			}
		return null;
	}

	public static List reverse(List list) {
		List revList = new List();                  // revList is initialized empty
		List.Iter start = List.Iter.last(revList);  // terminated backward iterator
		for(List.Iter iter = List.Iter.first(list); !iter.end(); iter.next()) {
			List.Node node = iter.key_data();
			revList.insAfter(start, node.key, node.data);
		}
		return revList;
	}

	public static void main(String[] args) { }
	
} //end class ListDemo

