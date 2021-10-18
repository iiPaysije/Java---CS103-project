package izDrugogDela;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Korisnik
 */  


  public class UnweightedGraph<V> extends AbstractGraph<V> {
		/** Construct an empty graph */
		public UnweightedGraph() {
		}

		/** Construct a graph from edges and vertices stored in arrays */
		public UnweightedGraph(V[] vertices, int[][] edges) {
			super(edges, vertices);
		}

		/** Construct a graph from edges and vertices stored in List */
		public UnweightedGraph(List<V> vertices, List<Edge> edges) {
			super(edges, vertices);
		}

		/** Construct a graph for integer vertices 0, 1, 2 and edge list */
		public UnweightedGraph(List<AbstractGraph.Edge> edges, int numberOfVertices) {
			super(edges, numberOfVertices);
		}

		/** Construct a graph from integer vertices 0, 1, and edge array */
		public UnweightedGraph(int[][] edges, int numberOfVertices) {
			super(edges, numberOfVertices);
		}
	}


/* Klasa UnweightedGraph jednostavno proširuje klasu AbstractGraph sa pet
konstruktora za kreiranje konkretnog grafa, tj. instance tipa Graph. Klasa
UnweightedGraph nasleđuje sve metoda apstraktne klase AbstractGraph, ali ne
uvodi nove metode niti predefiniše bilo koji postojeći metod.  */

