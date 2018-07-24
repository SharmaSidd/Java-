package graph;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Collection;
import java.util.Collections;

/*
 * This class has the implementation for a directed graph with Vertices of 
 * any type(V) and cost as integers. There are three maps:-
 * 
 * 1st map(integer as keys, V type as values)- keys are working as index in the
 * map and the corresponding values are for the vertices.(mapVertex)
 * 
 * 2nd map(V as keys, reference to another map for every key as values)- every
 * vertex of the graph will have its own map.(connection)
 * 
 * 3rd map(V as keys, integer as values)- this map is made for every vertex in
 * the 2nd map. This map has the vertex's neighbors or vertices which have an
 * incoming edge from the vertex with its cost associated to the edge(mapEdges).
 */

public class Graph<V> {
	LinkedHashMap<Integer, V> mapVertex;
	LinkedHashMap<V, LinkedHashMap<V, Integer>> connection;
	int count;
	Comparator<V> comparator;

	/*
	 * initialization block for the graph. Also, comparator is passed so as it
	 * can be used in other methods
	 */
	public Graph(Comparator<V> comparator) {
		mapVertex = new LinkedHashMap<Integer, V>();
		connection = new LinkedHashMap<V, LinkedHashMap<V, Integer>>();
		count = 0;
		this.comparator = comparator;

	}

	/*
	 * copy constructor for the graph, copies data from otherGraph element by
	 * element - deep copy
	 */
	public Graph(Graph<V> otherGraph) {
		mapVertex = new LinkedHashMap<Integer, V>();
		connection = new LinkedHashMap<V, LinkedHashMap<V, Integer>>();
		count = otherGraph.count;
		comparator = otherGraph.comparator;

		Iterator<V> iter = otherGraph.connection.keySet().iterator();
		while (iter.hasNext()) {
			V move = iter.next();
			LinkedHashMap<V, Integer> mapEdges = otherGraph.connection.
					get(move);
			connection.put(move, mapEdges);
		}

		Iterator<Integer> iter1 = otherGraph.mapVertex.keySet().iterator();
		while (iter1.hasNext()) {
			int move1 = iter1.next();
			mapVertex.put(move1, otherGraph.mapVertex.get(move1));
		}
	}

	/*
	 * this method adds the vertex to the graph. According to my implementation,
	 * the vertex would be added in the main vertex Map and then in the
	 * connecting map. Also, another map would be created for the edges & cost
	 * (values) related to that vertex every time a vertex or a connection is
	 * made with respect to each of its neighbors(keys). The counter is
	 * increased so as we know the number of vertex added in the graphs.If the
	 * graph already has that vertex, then false is returned.
	 */
	public boolean addVertex(V vertex) {
		// vertex is null
		if (vertex == null) {
			throw new NullPointerException();
		}
		if (!mapVertex.containsValue(vertex)) {
			mapVertex.put(count, vertex);
			LinkedHashMap<V, Integer> mapEdges = new LinkedHashMap<V,Integer>();
			connection.put(vertex, mapEdges);
			count++;
			return true;
		}
		return false;
	}

	/*
	 * This method checks if the graph contains the provided vertex. It returns
	 * false if it doesn't. We check the mapVertex for this, because if the
	 * vertex is not in this map, then the vertex won't be anywhere in the map.
	 */
	public boolean isVertex(V vertex) {
		if (vertex == null) {
			throw new NullPointerException();
		}
		return mapVertex.containsValue(vertex);
	}

	/*
	 * This method returns all the vertices in the graph as a collection backed
	 * by the map, so if there are any changes in the map, there are changes
	 * made in this collection also.
	 */
	public Collection<V> getVertices() {
		return mapVertex.values();
	}

	/*
	 * This method removes the given vertex from the map completely. If the map
	 * doesn't have the vertex, then false is returned.First, the vertex is
	 * removed from the vertex map. Then it is removed from the connection map
	 * which automatically removes the incoming edges & cost with it. For the
	 * removal of outgoing edges & cost, the edges map is checked to see if
	 * there are any footprints of the given vertex. If there are, they are
	 * removed. After all this, the method returns true.
	 */
	public boolean removeVertex(V vertex) {
		// vertex is null
		if (vertex == null) {
			throw new NullPointerException();
		}
		if (!mapVertex.containsValue(vertex) && (!(connection.
				containsKey(vertex)))) {
			return false;
		}

		connection.remove(vertex);
		Iterator<V> iter = connection.keySet().iterator();
		while (iter.hasNext()) {
			V move = iter.next();
			LinkedHashMap<V, Integer> mapEdges = connection.get(move);
			if (mapEdges.containsKey(vertex)) {
				mapEdges.remove(vertex);
			}
		}

		Iterator<Integer> mapIter = mapVertex.keySet().iterator();
		while (mapIter.hasNext()) {
			int move = mapIter.next();
			if (this.comparator.compare(vertex, mapVertex.get(move)) == 0) {
				this.mapVertex.remove(move);
				return true;
			}
		}
		return false;
	}

	/*
	 * This method adds an edge from the source to the destination given with
	 * the cost also provided for the edge. If the cost if invalid, false is
	 * returned If the graph doesn't contain either the source or the
	 * destination vertex, then the method first adds them in the graph & then
	 * performs the function of adding an edge between them by adding them in
	 * the Edges map with respect to the destination vertex.
	 */
	public boolean addEdgeBetweenVertices(V source, V dest, int cost) {
		// either vertices are null
		if (source == null || dest == null) {
			throw new NullPointerException();
		}

		if (!mapVertex.containsValue(source)) {
			mapVertex.put(++count, source);
			LinkedHashMap<V, Integer> mapEdges = new LinkedHashMap<V,Integer>();
			connection.put(source, mapEdges);
		}

		if (!mapVertex.containsValue(dest)) {
			mapVertex.put(++count, dest);
			LinkedHashMap<V, Integer> mapEdges = new LinkedHashMap<V,Integer>();
			connection.put(dest, mapEdges);
		}

		Iterator<V> iter = connection.keySet().iterator();
		if ((cost >= 0) && connection.containsKey(dest) && connection.
				containsKey(source)) {
			while (iter.hasNext()) {
				V move = iter.next();
				if (source.equals(move)) {
					LinkedHashMap<V, Integer> mapEdges = connection.get(move);
					mapEdges.put(dest, cost);
					return true;
				}
			}
		}
		return false;
	}

	/*
	 * this method returns the cost associated with the edge going from the
	 * source to the destination provided. If either vertices are not in the
	 * graph, or of the edge doesn't exist or if the cost is invalid,then -1 is
	 * returned. The cost is saved in the Edges map as values for each neighbor
	 * vertex.
	 */
	public int getEdgeCost(V source, V dest) {
		// either vertices are null
		if (source == null || dest == null) {
			throw new NullPointerException();
		}
		if (connection.containsKey(source) && connection.containsKey(dest)) {
			LinkedHashMap<V, Integer> mapEdges = connection.get(source);
			if (mapEdges.containsKey(dest))
				return mapEdges.get(dest);
		}
		return -1;
	}

	/*
	 * This method removes the edge and the cost associated from source to
	 * destination vertices passed. If the graph has both of the vertices and
	 * the cost is valid, then we iterate through all the costs and edges
	 * associated with the source until we find the destination vertex. When 
	 * destination vertex is found, the edge is removed.
	 */
	public boolean removeEdgeBetweenVertices(V source, V dest) {
		// either vertices are null
		if (source == null || dest == null) {
			throw new NullPointerException();
		}
		if (connection.containsKey(source) && connection.containsKey(dest) && 
				getEdgeCost(source, dest) != -1) {
			LinkedHashMap<V, Integer> mapEdges = connection.get(source);
			Iterator<V> iter = mapEdges.keySet().iterator();
			while (iter.hasNext()) {
				V move = iter.next();
				if (comparator.compare(dest, move) == 0) {
					int val = mapEdges.get(move);
					mapEdges.remove(dest, val);
					return true;
				}
			}
		}
		return false;
	}

	/*
	 * This method returns a collection of neighboring vertices for the given
	 * vertex. Neighboring vertices are those that associate with the given
	 * vertex either with incoming or with outgoing edge. To do this,set of the
	 * keys for that specific Edges map is returned.
	 */
	public Collection<V> getNeighbors(V vertex) {
		// vertex is null
		if (vertex == null) {
			throw new NullPointerException();
		}
		if (connection.containsKey(vertex)) {
			LinkedHashMap<V, Integer> mapEdges = connection.get(vertex);
			return mapEdges.keySet();
		}
		return Collections.emptySet();
	}

	/*
	 * This method returns a set of neighbors for the given vertex. Neighbors
	 * are vertices that have an outgoing vertex from the given vertex. We
	 * iterate through the connection map to find the given vertex and then
	 * return out all if its neighbors in its map as keyset.
	 */
	public Collection<V> getPredecessors(V vertex) {
		// vertex is null
		if (vertex == null) {
			throw new NullPointerException();
		}
		HashSet<V> returnSet = new HashSet<V>();
		Iterator<V> iter = connection.keySet().iterator();
		while (iter.hasNext()) {
			V move = iter.next();
			if (connection.get(move).containsKey(vertex))
				returnSet.add(move);
		}
		return returnSet;

	}

	/*
	 * this method removes the higher vertex to merge the two vertices with its
	 * outgoing and incoming edges and cost. If either of the vertices is null
	 * or the graph doesn't contain the vertices or if the cost associated is
	 * invalid, then false is returned.
	 */
	public boolean contractEdgeBetweenVertices(V vertex1, V vertex2) {

		// either vertices are null
		if (vertex1 == null || vertex2 == null) {
			throw new NullPointerException();
		}

		// either values are not contained
		if (!mapVertex.containsValue(vertex2) || !mapVertex.containsValue
				(vertex1)) {
			return false;
		}

		// either costs are invalid
		if (getEdgeCost(vertex1, vertex2) == -1 || getEdgeCost(vertex2, 
				vertex1) == 0) {
			return false;
		}

		removeEdgeBetweenVertices(vertex1, vertex2);
		removeEdgeBetweenVertices(vertex2, vertex1);

		// vertex1 is greater than vertex2
		// cost of the edge
		if ((comparator.compare(vertex1, vertex2) > 0)) {
			Iterator<V> iter = connection.get(vertex1).keySet().iterator();
			while (iter.hasNext()) {
				V val = iter.next();
				if (connection.get(vertex2).containsKey(val)) {
					// cost associated with vertex1 is greater
					if (getEdgeCost(vertex2, val) < getEdgeCost(vertex1, val)){
						// lower cost is replaced with higher
						connection.get(vertex2).replace(val, getEdgeCost
								(vertex1, val));
					}
				} else {
					// lower cost is replaced with higher
					connection.get(vertex2).put(val, getEdgeCost(vertex1,val));
				}
			}

			// changes in the outgoing edges
			Iterator<V> iter1 = connection.keySet().iterator();
			while (iter1.hasNext()) {
				V move = iter1.next();
				LinkedHashMap<V, Integer> mapEdges = connection.get(move);
				// if only vertex1 is present
				if (mapEdges.containsKey(vertex1) && !mapEdges.containsKey
						(vertex2)) {
					addEdgeBetweenVertices(move, vertex2, getEdgeCost(move, 
							vertex1));
				}
				// if only vertex2 is present
				else if (mapEdges.containsKey(vertex2) && !mapEdges.containsKey
						(vertex1)) {
					// Do nothing
				}
				// if both are present
				else if (mapEdges.containsKey(vertex2) && mapEdges.containsKey
						(vertex1)) {
					// if cost associated with vertex1 is higher
					if (getEdgeCost(move, vertex1) > getEdgeCost(move, 
							vertex2)) {
						addEdgeBetweenVertices(move, vertex2, getEdgeCost(move,
								vertex1));
					} else {
						// if cost associated with vertex2 is higher
						addEdgeBetweenVertices(move, vertex2, getEdgeCost(move,
								vertex2));
					}
				}
			}
			// changes in the incoming edges
			Iterator<V> iterator = getPredecessors(vertex1).iterator();
			while (iterator.hasNext()) {
				V forward = iterator.next();
				addEdgeBetweenVertices(forward, vertex2, getEdgeCost(forward,
						vertex1));
			}
			// lastly, remove the vertex that is small
			removeVertex(vertex1);

			return true;
		}

		// vertex2 is greater than vertex1
		else {
			// cost of the edge
			Iterator<V> iter = connection.get(vertex2).keySet().iterator();
			while (iter.hasNext()) {
				V val = iter.next();
				if (connection.get(vertex1).containsKey(val)) {
					// cost associated with vertex1 is greater
					if (getEdgeCost(vertex2, val) < getEdgeCost(vertex1, val)){
						// lower cost is replaced with higher
						connection.get(vertex1).replace(val, getEdgeCost
								(vertex1, val));
					}
				} else {
					// lower cost is replaced with higher
					connection.get(vertex1).put(val, getEdgeCost(vertex2, 
							val));
				}
			}
			// changes in the outgoing edges
			Iterator<V> iter1 = connection.keySet().iterator();
			while (iter1.hasNext()) {
				V move = iter1.next();
				LinkedHashMap<V, Integer> mapEdges = connection.get(move);
				// if only vertex1 is present
				if (mapEdges.containsKey(vertex1) && !mapEdges.containsKey
						(vertex2)) {
					addEdgeBetweenVertices(move, vertex1, getEdgeCost(move,
							vertex1));
				}
				// if only vertex2 is present
				else if (mapEdges.containsKey(vertex2) && !mapEdges.containsKey
						(vertex1)) {
					// Do nothing
				}
				// if only vertices are present
				else if (mapEdges.containsKey(vertex2) && mapEdges.containsKey
						(vertex1)) {
					// if cost associated with vertex1 is higher
					if (getEdgeCost(move, vertex1) > getEdgeCost(move, 
							vertex2)) {
						addEdgeBetweenVertices(move, vertex1, getEdgeCost(move,
								vertex1));
					} else {
						// if cost associated with vertex2 is higher
						addEdgeBetweenVertices(move, vertex1, getEdgeCost(move,
								vertex2));
					}
				}
			}
			// changes in the incoming edges
			Iterator<V> iterator = getPredecessors(vertex2).iterator();
			while (iterator.hasNext()) {
				V forward = iterator.next();
				addEdgeBetweenVertices(forward, vertex1, getEdgeCost(forward,
						vertex2));
			}
			// lastly, remove the vertex that is small
			removeVertex(vertex2);
			return true;
		}
	}

}
