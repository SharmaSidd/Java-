package tree;

import java.util.List;

@SuppressWarnings("unchecked")
public class NonEmptyTree<K extends Comparable<K>, V> implements Tree<K, V> {

	private Tree<K, V> rightTree, leftTree;
	private K key;
	private V value;

	/**
	 * constructor for a non empty tree
	 * 
	 * @param keyToAdd
	 * @param valueToAdd
	 * @param left
	 * @param right
	 */
	public NonEmptyTree(K keyToAdd, V valueToAdd, EmptyTree<K, V> left, 
			EmptyTree<K, V> right) {
		this.key = keyToAdd;
		this.value = valueToAdd;
		this.leftTree = left;
		this.rightTree = right;

	}

	/**
	 * this method adds the given key to its current object tree with value.
	 * if the key already exists, it just replaces it. Throws an exception if
	 * the values passed are invalid.
	 */
	public NonEmptyTree<K, V> addKeyWithValue(K keyToAdd, V valueToAdd) {
		/*
		 * checking if the passed value and key is valid
		 */
		if (keyToAdd == null || valueToAdd == null) {
			throw new NullPointerException();
		} else {
			/*
			 * if the key is equal to the given key, value is changes,otherwise
			 * it checks the left child and the right child in a recursive loop
			 */
			if (this.key.compareTo(keyToAdd) == 0) {
				this.value = valueToAdd;
			} else if (this.key.compareTo(keyToAdd) < 0) {
				rightTree =this.rightTree.addKeyWithValue(keyToAdd,valueToAdd);
			} else {
				leftTree = this.leftTree.addKeyWithValue(keyToAdd,valueToAdd);
			}
			return this;
		}
	}

	/**
	 * this method returns the number of value and key pairs in a tree. There's
	 * a "+1" in the beginning because of the root.
	 */
	public int size() {
		return (1 + leftTree.size() + rightTree.size());
	}

	/**
	 * This method finds the given key in the tree and the returns a reference
	 * to the value associated with it.It uses the binary search tree property
	 * of finding the value. If the key given key is more than the key of the
	 * tree, then it goes to the right tree to find the value, otherwise, it
	 * goes to the left. Once the value is found by comparing keys, the value
	 * is returned.
	 * 
	 * @param lookUpKey
	 */
	public V lookup(K lookUpKey) {
		/*
		 * base case where if the key matches with the given key,then the value
		 * is returned.
		 */
		if (this.key.compareTo(lookUpKey) == 0) {
			return value;
		} else if (this.key.compareTo(lookUpKey) < 0) {
			return this.rightTree.lookup(lookUpKey);
		} else {
			return this.leftTree.lookup(lookUpKey);
		}
	}

	/**
	 * this method traces the path from the root to that element and stores it
	 * in a list. If the given key is null, an exception is thrown. If the key
	 * is not found, then the method does nothing. It calls a helper recursive
	 * method.
	 * 
	 * @param key
	 * @param list
	 */
	public void pathFromRoot(K key, List<K> list) {
		/*
		 * throws an exception if the given key is invalid
		 */
		if (key == null) {
			throw new NullPointerException();
		}
		if (lookup(key) == null) {
			// Do Nothing
		} else {
			/*
			 * clear the list so as to add the new elements in the list.
			 */
			list.clear();
			this.pathFromRootHelper(key, list);
		}
	}

	/**
	 * This recursive method uses the binary search tree property. If the key 
	 * is less than the given key, then the key is added in the list and then 
	 * the right child is called in the recursive method & the same is for the
	 * left child. When the key is found i.e, the base case then the key is 
	 * just added to the list.
	 * 
	 * @param key
	 * @param list
	 */
	public void pathFromRootHelper(K key, List<K> list) {
		/*
		 * if the key is equal to the given key, value is added,otherwise
		 * it checks the left child and the right child in a recursive loop
		 */
		if (this.key.compareTo(key) == 0) {
			list.add(key);
		} else if (this.key.compareTo(key) < 0) {
			list.add(this.key);
			this.rightTree.pathFromRootHelper(key, list);
		} else {
			list.add(this.key);
			this.leftTree.pathFromRootHelper(key, list);
		}
	}

	/**
	 * this method compares two trees if they have the same number of keys and
	 * the same keys with the same values. If the given tree is null, then an
	 * exception is throw. We first check the size of the two trees. If the siz
	 * not equal, then obviously, the two trees are not same. It uses a helper
	 * recursive method.
	 * 
	 */
	public boolean haveSameKeys(Tree<K, V> otherTree) {
		if (otherTree == null) {
			throw new NullPointerException();
		}
		if (this.size() != otherTree.size()) {
			return false;
		}
		return haveSameKeysHelper(otherTree);
	}

	/**
	 * if the key is not found in the tree, then false is returned. It uses the
	 * binary search tree property by searching the right child first and then
	 * the left child.
	 */
	public boolean haveSameKeysHelper(Tree<K, V> otherTree) {
		if (otherTree.lookup(key) == null) {
			return false;
		}
		rightTree.haveSameKeysHelper(otherTree);
		leftTree.haveSameKeysHelper(otherTree);
		return true;
	}

	/**
	 * we first check the validity of the level then the helper recursive 
	 * method is called.
	 * 
	 * @param level
	 */
	public int numElementsAtLevel(int level) {
	
		return numElementsAtLevelHelper(level, 1);
	}

	/**
	 * level is the place to reach and the atLevel is the place it is at.
	 * if at any time, atLevel is more than the level to reach, then 0 is 
	 * returned. Otherwise, the atLevel keeps increasing till it reaches the 
	 * desired level, while doing so, it counts the number of elements
	 * each level has.
	 * @level
	 * @atLevel
	 */
	public int numElementsAtLevelHelper(int level, int atLevel) {
		int number = 0;
		if(level <= 0)
			return 0;
		else {
			if(level < atLevel)
				return 0;
			
			if(level > atLevel) {
					number+=rightTree.numElementsAtLevelHelper
							(level, atLevel + 1);
					
					number+=leftTree.numElementsAtLevelHelper
							(level, atLevel + 1);
				}
			else {
					number+=1;
				}		
		}
		return number;
	}


	/**
	 * this method returns the reference to the key that is maximum in its
	 * current object tree. If the tree is empty, an exception is thrown.The
	 * maximum element of the tree is always in the right side of the tree
	 * according to the binary search tree property.
	 */
	public K max() throws EmptyTreeException {
		try {
			return rightTree.max();
		} catch (EmptyTreeException e) {
			return key;
		}
	}

	/**
	 * this method returns the reference to the key that is minimum in its
	 * current object tree. If the tree is empty, an exception is thrown.The
	 * minimum element of the tree is always in the left side of the tree
	 * according to the binary search tree property.
	 */
	public K min() throws EmptyTreeException {
		try {
			return leftTree.min();
		} catch (EmptyTreeException e) {
			return key;
		}
	}

	/**
	 * this method removes the key from the tree along with the associated
	 * value. This method uses the binary search tree property. If the key is
	 * less than the given key then the right child is traversed else the left
	 * child is traversed. In the try catch block, the value is found by 
	 * finding the maximum key at the left tree and is then deleted.
	 * 
	 * @param keyToDelete
	 */
	public Tree<K, V> deleteKeyAndValue(K keyToDelete) {

		if (this.key.compareTo(keyToDelete) == 0) {
			try {
				this.value = lookup(leftTree.max());
				this.key = leftTree.max();
				leftTree = leftTree.deleteKeyAndValue(leftTree.max());
			} catch (EmptyTreeException e) {
				return rightTree;
			}
		} else if (this.key.compareTo(keyToDelete) < 0) {
			this.rightTree = this.rightTree.deleteKeyAndValue(keyToDelete);
		} else {
			this.leftTree = this.leftTree.deleteKeyAndValue(keyToDelete);
		}
		return this;
	}

	/**
	 * This method converts the values and keys in a string form by overriding 
	 * the toString method of the JVM. A string buffer is used and then the 
	 * data is appended in a particular order with slashes to separate the
	 * values and keys; and a space is left after every pair.
	 */
	public String toString() {
		StringBuffer output = new StringBuffer("");
		String leftString = leftTree.toString();
		String rightString = rightTree.toString();
		
		if (leftString.equals("")) {
			output.append(leftString + key + "/" + value);
		} else {
			output.append(leftString + " " + key + "/" + value);
		}
		if (rightString.equals("")) {
			output.append(rightString);
		} else {
			output.append(" " + rightString);
		}
		return output.toString();
	}


}
