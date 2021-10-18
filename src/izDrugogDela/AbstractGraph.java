package izDrugogDela;

import java.util.ArrayList;
import java.util.List;

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
public abstract class AbstractGraph<V> implements Graph<V> {
		protected List<V> vertices = new ArrayList<>(); // Store vertices
		protected List<List<Integer>> neighbors = new ArrayList<>(); // Adjacency
																					// lists

		/** Construct an empty graph */
		protected AbstractGraph() {
		}

		/** Construct a graph from edges and vertices stored in arrays
     * @param edges
     * @param vertices */
		protected AbstractGraph(int[][] edges, V[] vertices) {
			for (int i = 0; i < vertices.length; i++)
				this.vertices.add(vertices[i]);

			createAdjacencyLists(edges, vertices.length);
		}

		/** Construct a graph from edges and vertices stored in List */
		protected AbstractGraph(List<Edge> edges, List<V> vertices) {
			for (int i = 0; i < vertices.size(); i++)
				this.vertices.add(vertices.get(i));

			createAdjacencyLists(edges, vertices.size());
		}

		/** Construct a graph for integer vertices 0, 1, 2 and edge list */
		@SuppressWarnings("unchecked")
		protected AbstractGraph(List<Edge> edges, int numberOfVertices) {
			for (int i = 0; i < numberOfVertices; i++)
				vertices.add((V) (new Integer(i))); // vertices is {0, 1, ...}

			createAdjacencyLists(edges, numberOfVertices);
		}

		/** Construct a graph from integer vertices 0, 1, and edge array */
		@SuppressWarnings("unchecked")
		protected AbstractGraph(int[][] edges, int numberOfVertices) {
			for (int i = 0; i < numberOfVertices; i++)
				vertices.add((V) (new Integer(i))); // vertices is {0, 1, ...}

			createAdjacencyLists(edges, numberOfVertices);
		}

		/** Create adjacency lists for each vertex */
		private void createAdjacencyLists(int[][] edges, int numberOfVertices) {
			// Create a linked list
			for (int i = 0; i < numberOfVertices; i++) {
				neighbors.add(new ArrayList<Integer>());
			}

			for (int i = 0; i < edges.length; i++) {
				int u = edges[i][0];
				int v = edges[i][1];
				neighbors.get(u).add(v);
			}
		}

		/** Create adjacency lists for each vertex */
		private void createAdjacencyLists(List<Edge> edges, int numberOfVertices) {
			// Create a linked list for each vertex
			for (int i = 0; i < numberOfVertices; i++) {
				neighbors.add(new ArrayList<Integer>());
			}

			for (Edge edge : edges) {
				neighbors.get(edge.u).add(edge.v);
			}
		}

		@Override
		/** Return the number of vertices in the graph */
		public int getSize() {
			return vertices.size();
		}

		@Override
		/** Return the vertices in the graph */
		public List<V> getVertices() {
			return vertices;
		}

		@Override
		/** Return the object for the specified vertex */
		public V getVertex(int index) {
			return vertices.get(index);
		}

		@Override
		/** Return the index for the specified vertex object */
		public int getIndex(V v) {
			return vertices.indexOf(v);
		}

		@Override
		/** Return the neighbors of the specified vertex */
		public List<Integer> getNeighbors(int index) {
			return neighbors.get(index);
		}

		@Override
		/** Return the degree for a specified vertex */
		public int getDegree(int v) {
			return neighbors.get(v).size();
		}

		@Override
		/** Print the edges */
		public void printEdges() {
			for (int u = 0; u < neighbors.size(); u++) {
				System.out.print(getVertex(u) + " (" + u + "): ");
				for (int j = 0; j < neighbors.get(u).size(); j++) {
					System.out.print("(" + u + ", " + neighbors.get(u).get(j)
							+ ") ");
				}
				System.out.println();
			}
		}

		@Override
		/** Clear graph */
		public void clear() {
			vertices.clear();
			neighbors.clear();
		}

		@Override
		/** Add a vertex to the graph */
		public void addVertex(V vertex) {
			vertices.add(vertex);
			neighbors.add(new ArrayList<Integer>());
		}

		@Override
		/** Add an edge to the graph */
		public void addEdge(int u, int v) {
			neighbors.get(u).add(v);
			neighbors.get(v).add(u);
		}

		/** Edge inner class inside the AbstractGraph class */
		public static class Edge {
			public int u; // Starting vertex of the edge
			public int v; // Ending vertex of the edge

			/** Construct an edge for (u, v)
                     * @param u
                     * @param v */
			public Edge(int u, int v) {
				this.u = u;
				this.v = v;
			}
		}

		@Override
		/** Obtain a DFS tree starting from vertex v */
		/** To be discussed in Section 27.6 */
		public Tree dfs(int v) {
			List<Integer> searchOrder = new ArrayList<Integer>();
			int[] parent = new int[vertices.size()];
			for (int i = 0; i < parent.length; i++)
				parent[i] = -1; // Initialize parent[i] to -1

			// Mark visited vertices
			boolean[] isVisited = new boolean[vertices.size()];

			// Recursively search
			dfs(v, parent, searchOrder, isVisited);

			// Return a search tree
			return new Tree(v, parent, searchOrder);
		}

		/** Recursive method for DFS search */
		private void dfs(int v, int[] parent, List<Integer> searchOrder,
				boolean[] isVisited) {
			// Store the visited vertex
			searchOrder.add(v);
			isVisited[v] = true; // Vertex v visited

			for (int i : neighbors.get(v)) {
				if (!isVisited[i]) {
					parent[i] = v; // The parent of vertex i is v
					dfs(i, parent, searchOrder, isVisited); // Recursive search
				}
			}
		}

		@Override
		/** Starting bfs search from vertex v */
		/** To be discussed in Section 27.7 */
		public Tree bfs(int v) {
			List<Integer> searchOrder = new ArrayList<>();
			int[] parent = new int[vertices.size()];
			for (int i = 0; i < parent.length; i++)
				parent[i] = -1; // Initialize parent[i] to -1

			java.util.LinkedList<Integer> queue = new java.util.LinkedList<>(); // list
																						// used
																						// as
																						// a
																						// queue
			boolean[] isVisited = new boolean[vertices.size()];
			queue.offer(v); // Enqueue v
			isVisited[v] = true; // Mark it visited

			while (!queue.isEmpty()) {
				int u = queue.poll(); // Dequeue to u
				searchOrder.add(u); // u searched
				for (int w : neighbors.get(u)) {
					if (!isVisited[w]) {
						queue.offer(w); // Enqueue w
						parent[w] = u; // The parent of w is u
						isVisited[w] = true; // Mark it visited
					}
				}
			}

			return new Tree(v, parent, searchOrder);
		}

		/** Tree inner class inside the AbstractGraph class */
		/** To be discussed in Section 27.5 */
		public class Tree {
			public int root; // The root of the tree
			public int[] parent; // Store the parent of each vertex
			public List<Integer> searchOrder; // Store the search order

			/** Construct a tree with root, parent, and searchOrder
                     * @param root
                     * @param parent
                     * @param searchOrder */
			public Tree(int root, int[] parent, List<Integer> searchOrder) {
				this.root = root;
				this.parent = parent;
				this.searchOrder = searchOrder;
			}

			/** Return the root of the tree
                     * @return  */
			public int getRoot() {
				return root;
			}

			/** Return the parent of vertex v
                     * @param v
                     * @return  */
			public int getParent(int v) {
				return parent[v];
			}

			/** Return an array representing search order
                     * @return  */
			public List<Integer> getSearchOrder() {
				return searchOrder;
			}

			/** Return number of vertices found
                     * @return  */
			public int getNumberOfVerticesFound() {
				return searchOrder.size();
			}

			/** Return the path of vertices from a vertex to the root
                     * @param index
                     * @return  */
			public List<V> getPath(int index) {
				ArrayList<V> path = new ArrayList<>();

				do {
					path.add(vertices.get(index));
					index = parent[index];
				} while (index != -1);

				return path;
			}

			/** Print a path from the root to vertex v
                     * @param index */
			public void printPath(int index) {
				List<V> path = getPath(index);
				System.out.print("A path from " + vertices.get(root) + " to "
						+ vertices.get(index) + ": ");
				for (int i = path.size() - 1; i >= 0; i--)
					System.out.print(path.get(i) + " ");
			}

			/** Print the whole tree */
			public void printTree() {
				System.out.println("Root is: " + vertices.get(root));
				System.out.print("Edges: ");
				for (int i = 0; i < parent.length; i++) {
					if (parent[i] != -1) {
						// Display an edge
						System.out.print("(" + vertices.get(parent[i]) + ", "
								+ vertices.get(i) + ") ");
					}
				}
				System.out.println();
			}
		}
	}

//  POJASNJENJA KODA:


/* Klasa Tree definiše sedam metoda. Metod getRoot() vraća koren obuhvatnog
stabla. Pozivom metoda getSearchOrder() dobija se sekvenca posećenih čvorova
u redosledu kako su oni i posećeni. Metod getParent(v) pronalazi roditelja čvora
v, tj. čvora preko kojeg se u pretrazi došlo do čvora c. Metod
getNumberOfVerticesFound() vraća broj posećenih čvorova. Ukoliko je broj
posećenih čvorova manji od broja čvorova u grafu može se zaključiti da taj graf nije
povezan. Metod getPath(index) vraća listu čvorova na putanji od odgovarajućeg
čvora, definisanog indeksom, do korena ( root) obuhvatnog stabla. Metod
printPath(v) prikazuje putanju od korena ( root) do čvora v. Korišćenjem metoda
printTree() moguće je prikazati sve grane obuhvatnog stabla. */

/* Metod dfs(int v) vraća instancu klase Tree sa čvorom v kao korenom obuhvatnog
stabla. Metod pakuje pretražene čvorove u listu searchOrder, roditelj svakog čvora
se čuva u nizu parent, a koristi niz isVisited da se označi da li je čvor već posećen
ili ne. Metod dfs(int v) poziva pomoćni metod dfs(v, parent, searchOrder,
isVisited) da bi se obavila pretraga po dubini. U rekurzivnom pomoćnom metodu,
pretraga počinje od čvora v. Čvor v se dodaje u niz searchOrder i markira se kao
posećen. Za svaki neposećeni sused čvora v, metod se rekurzivno poziva da bi se
nastavila pretraga po dubini. Kada se poseti čvor i roditelj čvora i se čuva u nizu
parent[i]. Metod dfs se završava tek kada su obiđeni svi čvorovi povezanog grafa.
  */

/*  Metod kao rezultat vraća instancu klase Tree sa čvorom v kao korenom stabla.
Metod čuva posećene čvorove u listi searchOrder, roditelje svakog čvora u nizu
parent, koristi povezanu listu za red, i koristi niz isVisited da se označi da li
je čvor posećen ili ne. Obilazak započinjemo iz proizvoljnog čvora v. Čvor v se
dodaje u red, i označava kao posećen. Metod zatim ispituje svaki čvor u iz reda
čekanja i dodaje ga u niz searchOrder prilikom posete. Metod dodaje u red svakog
neposećenog suseda w čvora u, postavlja njegovog roditelja na u, i označava ga
kao posećenog */ 

