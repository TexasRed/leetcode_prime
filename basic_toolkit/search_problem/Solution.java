package water_jug;

import java.util.*;

public class Solution {

	boolean isGoal(Node node) {
		return(node.y == 2);
	}

	Vector<Node> getSuccessors(Node parent) {
		int x = parent.x, y = parent.y;
		Vector<Node> successors = new Vector<Node>();
		if (x < 4 && y > 0) { /* transfer amount z from y to x */
			int z = Math.min(y, 4-x);
			successors.add(new Node(x+z, y-z, parent)); }
		if (y < 3 && x > 0) { /* transfer amount z from x to y */
			int z = Math.min(x, 3-y);
			successors.add(new Node(x-z, y+z, parent)); }
		if (x > 0) { /* empty x */
			successors.add(new Node(0, y, parent)); }
		if (y > 0) { /* empty y */
			successors.add(new Node(x, 0, parent)); }
		if (x < 4) { /* fill x from tap */
			successors.add(new Node(4, y, parent)); }
		if (y < 3) { /* fill y from tap */
			successors.add(new Node(x, 3, parent)); }
		return(successors);
	}
	
	void run() {
		Vector<Node> open = new Vector<Node>();
		open.add(new Node(0, 0, null));

		while (open.size() > 0) {
			Node node = open.remove(0);
			if (isGoal(node)) {
				System.out.println("Solution: " + node.getPath()); }
			else {
				Vector<Node> successors = getSuccessors(node);
				for (int i = 0; i < successors.size(); i++) {
					Node child = successors.get(i);
					if (!node.getPath().contains((Object)child)) {
						open.add(child); }
				}
			}
		}
	}

	public static void main(String args[]) { // do the search
		new Solution().run();
	}
}


class Node {
	int x = 0, y = 0; /* state variables */
	Node parent = null; /* parent link */

	Node (int x, int y, Node parent) {
		this.x = x;
		this.y = y;
		this.parent = parent;
	}

	public String toString() {
		return(x + " " + y);
	}
	@Override
	public boolean equals(Object node) { /* argument has to be an Object */
		return(((Node)node).x == x && ((Node)node).y == y);
	}

	@Override
	public int hashCode() {
		// implement a good hash function here.
		return (((this.x + this.y) << 5) - 1) % 97;
	}
	
	// We can also getPath from node, add to linkedlist
	Vector<Node> getPath(Vector<Node> v) {
		v.insertElementAt(this, 0);
		if (parent != null) v = parent.getPath(v);
		return(v);
	}

	Vector<Node> getPath() { return(getPath(new Vector<Node>())); }
}
