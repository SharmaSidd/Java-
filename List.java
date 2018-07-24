package list;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class List<T extends Comparable<T>> implements Iterable<T> {
	private Node<T> head;

	// you may ADD TO this inner class, but not CHANGE what's already here!
	private static class Node<D extends Comparable<D>> {
		private D data;
		private Node<D> next;
	}

	/**
	 * this is an initialization constructor for an empty list
	 */
	public List() 
	{
		head = null;	
	}

	/**
	 * a copy constructor for list where the elements are exactly the same as
	 * that of otherList
	 * 
	 * @param otherList
	 */
	public List(List<T> otherList) 
	{
		Node<T> currentOtherList = otherList.head;
		Node<T> current = head;

		while (currentOtherList != null) 
		{
			/*
			 * if the list is empty, then the data should be directly added
			 */
			if (head == null) 
			{
				Node<T> temp = new Node<T>();
				head = temp;
				current = head;
				temp.data = currentOtherList.data;
				temp.next = null;
				currentOtherList = currentOtherList.next;

			} 
			else 
			{
				Node<T> temp = new Node<T>();
				current.next = temp;
				temp.data = currentOtherList.data;
				current = current.next;
				currentOtherList = currentOtherList.next;
			}
		}

	}

	/**
	 * this method sorts the input data in ascending order irrespective of the
	 * entry in the list.
	 * 
	 * @param newElt
	 */
	public void sortedOrderInsert(T newElt) 
	{
		Node<T> temp = new Node<T>();
		temp.data = newElt;

		/*
		 * if the list is empty, then data should be directly added
		 */
		if (head == null) 
		{
			head = temp;
		}

		/*
		 * if the list is not empty, we call the compareTo method to determine
		 * the position of the incoming element.If the new element is less than
		 * the current element.
		 */
		else if (head.data.compareTo(newElt) >= 0) 
		{
			temp.next = head;
			head = temp;
		}

		/*
		 * again calling the compareTo method to determine the position of the
		 * incoming element. If the new element is bigger than the current
		 * element.
		 */
		else 
		{
			Node<T> ahead = head.next;
			Node<T> current = head;
			while (ahead != null) 
			{
				if (ahead.data.compareTo(newElt) >= 0)
					break;
				current = ahead;
				ahead = ahead.next;
			}

			temp.next = current.next;
			current.next = temp;
		}
	}

	/**
	 * this method checks the size of the list using a counter
	 * 
	 * @return
	 */
	public int size() 
	{
		int count = 0;
		Node<T> current = head;
		while (current != null) 
		{
			current = current.next;
			count++;
		}

		return count;
	}

	/**
	 * this method returns the element at the provided index of the list.If the
	 * list is empty or the index is invalid, then the method throws an
	 * exception.
	 * 
	 * @param index
	 * @return
	 * @throws IndexOutOfBoundsException
	 */
	public T getElementAtIndex(int index) throws IndexOutOfBoundsException 
	{

		/*
		 * if the list is empty
		 */
		if (head == null) 
		{
			throw new IndexOutOfBoundsException();
		}

		/*
		 * if the index is not valid
		 */
		if (index >= size() || index < 0) 
		{
			throw new IndexOutOfBoundsException();
		}

		/*
		 * moving through the list and stopping at given index to return the
		 * element at that position.
		 */
		Node<T> current = head;
		for (int i = 0; i < index; i++) 
		{
			current = current.next;
		}
		return current.data;
	}

	/**
	 * this method checks if the provided element is in the list.
	 * 
	 * @param element
	 * @return
	 */

	public T contains(T element) 
	{
		Node<T> current = head;
		T el = null;
		boolean found = false;

		/*
		 * if the list is empty
		 */
		if (head == null) 
		{
			return null;
		}

		/*
		 * moving through the list, if the element is found,value is stored
		 * in 'el' and the 'found' pointer (boolean type) is set to true.
		 */
		while (current != null) 
		{
			if (current.data.compareTo(element) == 0) 
			{
				el = element;
				found = true;
			}
			current = current.next;

		}
		// if the element is not found i.e when found is false
		if (found == false) 
		{
			return null;
		} else 
		{
			return el;
		}

	}

	/**
	 * this method returns a string representation of the list by invoking the
	 * toString() method on each element.
	 */
	public String toString() 
	{
		/*
		 * if the list is empty
		 */
		if (head == null) 
		{
			return "";
		}

		StringBuffer retStr = new StringBuffer();
		Node<T> current = head;
		while (current.next != null) 
		{
			retStr.append(current.data.toString() + " ");
			current = current.next;

		}
		retStr.append(current.data.toString());
		return retStr.toString();

	}

	/**
	 * This method returns the index of the element provided in the list. It
	 * uses the compareTo() method to compare the element provided and the
	 * incoming element.If there are more than 1 elements in the list, the one
	 * closes to the beginning of the list is returned.
	 * 
	 * @param element
	 * @return
	 */
	public int indexOf(T element) 
	{
		Node<T> current = head;
		int index = 0;
		while (current != null) 
		{
			/*
			 * if the element is same as the provided, then the index is
			 * returned.
			 */
			if (current.data.compareTo(element) == 0) 
			{
				return index;
			} else 
			{
				current = current.next;
				index++;
			}
		}
		return -1;
	}

	/**
	 * This method returns the index of the element provided in the list. It
	 * uses the compareTo() method to compare the element provided and the
	 * incoming element.If there are more than 1 elements in the list, the one
	 * closes to the end of the list is returned.
	 * 
	 * @param element
	 * @return
	 */
	public int lastIndexOf(T element) 
	{
		Node<T> current = head;
		int point = 0;
		int index = 0;

		/*
		 * if the element is same as the input element, then the index is
		 * increased.
		 */
		while (current != null) 
		{
			if (current.data.compareTo(element) == 0) 
			{
				index++;
			}
			current = current.next;

		}

		/*
		 * if the element is not in the list,then -1 is returned.
		 */
		if (index == 0) 
		{
			return -1;
		}

		/*
		 * if there is only one occurrence of that element, then the indexOf
		 * method is called.
		 */
		else if (index == 1) 
		{
			return indexOf(element);
		}

		else 
		{
			/*
			 * if there is more than one occurrence,counter is used to return
			 * the index and a pointer is increased to check for the last
			 * occurring element, if not then zero is returned.
			 */
			current = head;
			int count = 0;
			while (current != null) 
			{

				if (current.data.compareTo(element) == 0 && ++point == index)
				{
					return count;
				}
				current = current.next;
				count++;
			}
		}
		return 0;
	}

	/**
	 * this method removes the element provided from the list.
	 * 
	 * @param element
	 * @return
	 */
	public boolean removeElt(T element) 
	{
		Node<T> current = head;
		Node<T> prev = null;

		while (current != null) 
		{
			/*
			 * if the element is found, the head is moved in front & the prev
			 * skips the element in between thus, removing it from the list.
			 */
			if (element.compareTo(current.data) == 0) 
			{
				if (current == head) 
				{
					head = head.next;
				} else 
				{
					prev.next = current.next;
				}
				return true;
			} else 
			{
				prev = current;
			}
			current = current.next;
		}
		return false;
	}

	/**
	 * this method removes the element with respect to the index provided. If
	 * the provided index is invalid then an exception is thrown.
	 * 
	 * @param index
	 * @throws IndexOutOfBoundsException
	 */
	public void removeElementWithIndex(int index) throws 
												IndexOutOfBoundsException 
	{

		Node<T> cur = head;
		int length = size();
		/*
		 * if the index is invalid
		 */
		if (index < 0 || index > size()) 
		{
			throw new IndexOutOfBoundsException();
		}

		/*
		 * if there is only one element in the list, then an empty list is
		 * created.
		 */
		else if (length == 1) 
		{
			head = null;

			/*
			 * if the first element has to be removed
			 */
		} else if (index == 0) 
		{
			head = head.next;
			length--;
		} else 
		{
			for (int i = 1; i < index; i++) 
			{
				cur = cur.next;
			}
			// ahead is the position after the element
			Node<T> ahead = cur.next.next;
			cur.next = ahead;
			length--;
		}
	}

	/**
	 * this method clears the list by just setting the head as null.
	 */
	public void clear() 
	{
		head = null;
	}

	/**
	 * this method creates a sublist on the basis of the index provided. the
	 * starting point is provided as fromIndex and the ending is provided as
	 * toIndex.If any of the index's provided is invalid, then an exception is
	 * thrown.
	 * 
	 * @param fromIndex
	 * @param toIndex
	 * @return
	 * @throws IndexOutOfBoundsException
	 */
	public List<T> subList(int fromIndex, int toIndex) throws 
									IndexOutOfBoundsException 
	{
		List<T> listSub = new List<T>();
		Node<T> current = head;
		int count = 0;
		/*
		 * clears the list so that if the method is invoked again, the list is
		 * empty
		 */
		listSub.clear();

		/*
		 * if the fromIndex value is more than the toIndex, then an empty list
		 * is made.
		 */
		if (fromIndex > toIndex) 
		{
			listSub = new List<T>();
		}
		/*
		 * if the index is invalid.
		 */
		else if (fromIndex > size() || fromIndex < 0 || toIndex > size() ||
																toIndex < 0) 
		{
			throw new IndexOutOfBoundsException();
		} else 
		{

			/*
			 * the sortedOrderInsert method is called to insert the data in the
			 * sublist if all the requirements are met.
			 */
			while (current != null) 
			{
				if (count >= fromIndex && count <= toIndex) 
				{
					listSub.sortedOrderInsert(current.data);
				}
				current = current.next;
				count++;
			}
		}
		return listSub;
	}

	/**
	 * this method just returns the iterator made in an inner class.
	 */
	public Iterator<T> iterator() 
	{
		return new LinkedListIterator();
	}

	/**
	 * this iterator inner class is called by the method above.This method has
	 * three methods, to check if there is a next element,what is the next 
	 * element and to delete the element last returned.
	 * @author raghavgupta
	 *
	 */
	private class LinkedListIterator implements Iterator<T> 
	{
		int count;
		boolean deleted;

		public LinkedListIterator() 
		{
			count = 0;
			deleted = true;
		}
		
		/**
		 * this method checks is the list has a next element or not.
		 */
		public boolean hasNext() 
		{
			return count < size();
		}
		
		/**
		 * this method returns the next element. it first checks if there is 
		 * a next element or not.if there isn't , it throws an exception.
		 */
		public T next() throws NoSuchElementException 
		{
			if (!hasNext()) 
			{
				throw new NoSuchElementException();
			}
			/*
			 * uses the getElementAtIndex method to return the next element.
			 */
			T val = getElementAtIndex(count);
			deleted = false;
			count++;
			return val;
		}

		/**
		 * this method removes the element which is returned by the next()
		 * method.If the element is removed, it throws an exception.
		 */
		public void remove() throws IllegalStateException 
		{
			if (deleted) 
			{
				throw new IllegalStateException();
			}

			/**
			 * if there is only one element.
			 */
			if (count == 1) 
			{
				head = head.next;
				count = count - 1;
				deleted = true;
			} 
			/*
			 * a temporary node is created, the element reference is skipped 
			 * and so that element is deleted.
			 */
			else {
				Node<T> temp = head;
				int a = 0;

				while (a < count - 2) 
				{
					temp = temp.next;
					a++;
				}

				temp.next = temp.next.next;
				count--;
				deleted = true;
			}
		}
	}

	/**
	 * this method return a comparator which compares two lists by calling
	 * the comparator from the inner class.
	 * @return
	 */
	public Comparator<List<T>> lengthComparator() 
	{
		return new lengthComparator();
	}

	/**
	 * this inner class is called by the comparator method. This class has a 
	 * method which compares two lists based on their sizes.
	 * @author raghavgupta
	 *
	 */
	public class lengthComparator implements Comparator<List<T>> 
	{

		public int compare(List<T> list1, List<T> list2) 
		{
			return list1.size() - list2.size();
		}

	}

	/**
	 * this method return a comparator which compares two lists by calling
	 * the comparator from the inner class.
	 * @return
	 */
	public Comparator<List<T>> orderComparator() 
	{
		return new orderComparator();
	}

	/**
	 * this inner class is called by the comparator method. This class has a 
	 * method which compares two lists based on each of the element.
	 * @author raghavgupta
	 *
	 */
	public class orderComparator implements Comparator<List<T>> 
	{
		public int compare(List<T> list1, List<T> list2) 
		{
			int count = -1;
			/*
			 * is the size of the two lists is same, then the two lists are
			 * compared element by element. If the elements are exactly the 
			 * same, then zero is returned.
			 */
			if (list1.size() == list2.size()) 
			{
				for (int i = 0; i < list1.size(); i++) 
				{
					if (list1.getElementAtIndex(i).compareTo(list2.
							getElementAtIndex(i)) == 0) 
					{
						count = 0;
					}
					/*
					 * if the element of the first list is bigger than that
					 * of the second list, then 1 is returned.
					 */
					else if (list1.getElementAtIndex(i).compareTo(list2.
							getElementAtIndex(i)) > 0) 
					{
						count = 1;
					} 
				}
				/*
				 *if the sizes are different
				 */
			} else 
			{
				return list1.size() - list2.size();
			}
			return count;
		}
	}
}
