package izDrugogDela;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Korisnik
 * @param <V>
 */
public interface Graph<V> {
		/** Return the number of vertices in the graph
     * @return  */
		public int getSize();

		/** Return the vertices in the graph
     * @return  */
		public java.util.List<V> getVertices();

		/** Return the object for the specified vertex index
     * @param index
     * @return  */
		public V getVertex(int index);

		/** Return the index for the specified vertex object
     * @param v
     * @return  */
		public int getIndex(V v);

		/** Return the neighbors of vertex with the specified index
     * @param index
     * @return  */
		public java.util.List<Integer> getNeighbors(int index);

		/** Return the degree for a specified vertex
     * @param v
     * @return  */
		public int getDegree(int v);

		/** Print the edges */
		public void printEdges();

		/** Clear graph */
		public void clear();

		/** Add a vertex to the graph
     * @param vertex */
		public void addVertex(V vertex);

		/** Add an edge to the graph
     * @param u
     * @param v */
		public void addEdge(int u, int v);

		/** Obtain a depth-first search tree
     * @param v
     * @return  */
		public AbstractGraph<V>.Tree dfs(int v);

		/** Obtain a breadth-first search tree
     * @param v
     * @return  */
		public AbstractGraph<V>.Tree bfs(int v);
	}
