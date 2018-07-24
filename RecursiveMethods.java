package recursiveMethods;

import java.util.LinkedList;
import java.util.List;

public class RecursiveMethods 
{
	public static <T> void replaceRange(List<T> list, int from, int to, T elt) 
	{
		if (elt == null) 
		{
			throw new NullPointerException();
		}
		
		if ((to >= from) && (from < list.size() && to < list.size() && 
				(from >= 0 && to >= 0))) 
		{
			list.set(to, elt);
			replaceRange(list, from, --to, elt);
		}
	}

	public static <T> boolean areReversed(List<T> list1, List<T> list2) 
	{

		if ((list1.size() == 0) && (list2.size() == 0)) 
		{
			return true;
		}
		
		if (((list1.size() == 1) && (list2.size() == 1)) && 
				(list1.get(0) == list2.get(0))) 
		{
			return true;
		}
		
		if ((list1.size() != list2.size())) 
		{
			return false;
		}

		List<T> listNew = new LinkedList<T>();
		reverse(list2, listNew, list2.size() - 1);

		return areReversedHelper(list1, listNew, list1.size() - 1);
	}

	public static <T extends Comparable<T>> int posOfMaxElt(List<T> list) 
	{
		if (list.size() == 0) {
			return -1;
		}
		
		return posOfMaxEltHelper(list, list.size(), 0);
	}

	public static <T> List<T> changeAllFromTo(List<T> list, T oldElt, T newElt)
	{

		if ((oldElt == null) || (newElt == null) || (list == null)) 
		{
			throw new NullPointerException();
		}
		
		List<T> list2 = new LinkedList<T>();
		copyElementToList(list, list2, 0, list.size());

		changeAllFromToHelper(list2, oldElt, newElt, list2.size() - 1);
		return list2;
	}

	private static <T> void copyElementToList(List<T> list1, List<T> list2, 
			int start, int size) 
	{

		if (start < size) 
		{
			list2.add(list1.get(start));
			copyElementToList(list1, list2, start + 1, size);
		}
	}

	private static <T> boolean areReversedHelper(List<T> list, List<T> list1, 
			int size) 
	{

		if (size >= 0) 
		{
			if (list.get(size).equals(list1.get(size))) 
			{
				return areReversedHelper(list, list1, --size);
			} 
			else
				return false;
		}

		return true;

	}

	private static <T> List<T> reverse(List<T> list, List<T> listNew, int i) 
	{
		if (i >= 0) 
		{
			listNew.add(list.get(i));
			reverse(list, listNew, --i);
		}

		return listNew;
	}

	private static <T> void changeAllFromToHelper(List<T> list, T oldElt, 
			T newElt, int size) 
	{
		if (size >= 0) 
		{
			if (list.get(size).equals(oldElt)) 
			{
				list.set(size, newElt);
				changeAllFromToHelper(list, oldElt, newElt, --size);
			} else 
			{
				changeAllFromToHelper(list, oldElt, newElt, --size);
			}
		}
	}

	private static <T extends Comparable<T>> int posOfMaxEltHelper(List<T> list,
			int size, int position) 
	{
		if (size == list.size() - 1) 
		{
			position = size;
		}
		if (size == 0) 
		{
			return position;
		} else 
		{
			if (list.get(position).compareTo(list.get(size - 1)) < 0) 
			{
				position = size - 1;
			}
			return posOfMaxEltHelper(list, size - 1, position);
		}
	}

}
