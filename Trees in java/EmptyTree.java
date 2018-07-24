package tree;

import java.util.List;

@SuppressWarnings({"unchecked", "rawtypes"})
public final class EmptyTree<K extends Comparable<K>, V> implements Tree<K, V> {
	
	private static EmptyTree Singleton = new EmptyTree();

  public static EmptyTree getInstance() {
    return Singleton;
  }
  
  /**
   * if we add an element to the tree, then the tree is no longer empty and
   * so, a NonEmptyTree is called.
   */
  public NonEmptyTree<K, V> addKeyWithValue(K keyToAdd, V valueToAdd) {
    return new NonEmptyTree(keyToAdd,valueToAdd,this,this);
  }
  
  
  private EmptyTree() {		
	}

  /**
   * since the tree is empty, the size of the tree would be 0.
   */
  public int size() {
    return 0;
  }

  /**
   * since the tree is empty, there is nothing to be searched.
   */
  public V lookup(K lookUpKey) {
    return null;
  }

  /**
   * there is no root, no path is found and also, there is no key.
   */
  public void pathFromRoot(K key, List<K> list) {
    return;
  }

  /**
   * if two empty lists have the same size, then they are same because there
   * are no keys to be compared.
   */
  public boolean haveSameKeys(Tree<K, V> otherTree) {
    if(this.size() != otherTree.size()){
    	return false;
    }
    else{
    	return true;
    }
  }

  /**
   * there are no levels, hence 0 is returned.
   */
  public int numElementsAtLevel(int level) {
    return 0;
  }

  /**
   * since there are no elements, there is no maximum , and so an exception is 
   * thrown.
   */
  public K max() throws EmptyTreeException {
    throw new EmptyTreeException();
  }

  /**
   * since there are no elements, there is no minimum, and so an exception is 
   * thrown.
   */
  public K min() throws EmptyTreeException {
	  throw new EmptyTreeException();
  }

  /**
   * since the tree is empty, there are no elements to be deleted.
   */
  public Tree<K, V> deleteKeyAndValue(K keyToDelete) {
    return this;
  }

  /**
   * string representation would have nothing since the tree is empty.
   */
  public String toString() {
    return"";
  }

@Override
public void pathFromRootHelper(K key, List<K> list) {	
}

@Override
public boolean haveSameKeysHelper(Tree<K, V> otherTree) {
	return false;
}

@Override
public int numElementsAtLevelHelper(int level, int atLevel) {
	
	return 0;
}



}
