import java.util.Map;
import java.util.Set;
import java.util.Queue;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.HashMap;

/**
 * Your implementation of various different graph algorithms.
 *
 * @author Abhinav Tirath
 * @userid atirath6
 * @GTID 903108718
 * @version 1.0
 */
public class GraphAlgorithms {

    /**
     * Performs a breadth first search (bfs) on the input graph, starting at
     * {@code start} which represents the starting vertex.
     *
     * When exploring a vertex, make sure to explore in the order that the
     * adjacency list returns the neighbors to you. Failure to do so may cause
     * you to lose points.
     *
     * You may import/use {@code java.util.Set}, {@code java.util.List},
     * {@code java.util.Queue}, and any classes that implement the
     * aforementioned interfaces, as long as it is efficient.
     *
     * The only instance of {@code java.util.Map} that you may use is the
     * adjacency list from {@code graph}. DO NOT create new instances of Map
     * for BFS (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @throws IllegalArgumentException if any input
     *  is null, or if {@code start} doesn't exist in the graph
     * @param <T> the generic typing of the data
     * @param start the vertex to begin the bfs on
     * @param graph the graph to search through
     * @return list of vertices in visited order
     */
    public static <T> List<Vertex<T>> breadthFirstSearch(Vertex<T> start,
                                            Graph<T> graph) {
        if (graph == null) {
            throw new IllegalArgumentException("Graph cannot be null");
        }
        if (start == null) {
            throw new IllegalArgumentException("Start cannot be null");
        } else if (!graph.getVertices().contains(start)) {
            throw new IllegalArgumentException("The starting vertex must be"
                    + " in the graph.");
        }

        ArrayList<Vertex<T>> toReturn = new ArrayList<Vertex<T>>();
        Queue<Vertex<T>> queue = new LinkedList<Vertex<T>>();
        Set<Vertex<T>> set = new HashSet<Vertex<T>>();

        queue.add(start);

        while (!queue.isEmpty() && toReturn.size() < graph.getVertices().size()) {
            Vertex<T> vertex = queue.remove();
            if (!set.contains(vertex)) {
                set.add(vertex);
                toReturn.add(vertex);
                for (Edge edge : graph.getAdjList().get(vertex)) {
                    Vertex<T> curr = edge.getV();
                    if (!set.contains(curr)) {
                        queue.add(curr);
                    }
                }
            }
        }

        return toReturn;
    }


    /**
     * Performs a depth first search (dfs) on the input graph, starting at
     * {@code start} which represents the starting vertex.
     *
     * When deciding which neighbors to visit next from a vertex, visit the
     * vertices in the order presented in that entry of the adjacency list.
     *
     * *NOTE* You MUST implement this method recursively, or else you will lose
     * most if not all points for this method.
     *
     * You may import/use {@code java.util.Set}, {@code java.util.List}, and
     * any classes that implement the aforementioned interfaces, as long as it
     * is efficient.
     *
     * The only instance of {@code java.util.Map} that you may use is the
     * adjacency list from {@code graph}. DO NOT create new instances of Map
     * for DFS (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @throws IllegalArgumentException if any input
     *  is null, or if {@code start} doesn't exist in the graph
     * @param <T> the generic typing of the data
     * @param start the vertex to begin the dfs on
     * @param graph the graph to search through
     * @return list of vertices in visited order
     */
    public static <T> List<Vertex<T>> depthFirstSearch(Vertex<T> start,
                                            Graph<T> graph) {
        if (graph == null) {
            throw new IllegalArgumentException("Graph cannot be null");
        }
        if (start == null) {
            throw new IllegalArgumentException("Start cannot be null");
        } else if (!graph.getVertices().contains(start)) {
            throw new IllegalArgumentException("The starting vertex must be"
                    + " in the graph.");
        }

        ArrayList<Vertex<T>> toReturn = new ArrayList<Vertex<T>>();
        Set<Vertex<T>> set = new HashSet<Vertex<T>>();

        return dFSHelper(start, graph, toReturn, set);
    }

    /**
     * Helps implement Depth First Search
     * @param start Vertex to start from
     * @param graph Graph we are looking at
     * @param toReturn ArrayList we are storing our values in
     * @param set visited vertices
     * @param <T> T
     * @return ArrayList we are storing our values in
     */
    private static <T> List<Vertex<T>> dFSHelper(Vertex<T> start,
                                                 Graph<T> graph,
                                  ArrayList<Vertex<T>> toReturn,
                                  Set<Vertex<T>> set) {

        set.add(start);
        toReturn.add(start);

        for (Edge edge: graph.getAdjList().get(start)) {
            Vertex<T> curr = edge.getV();
            if (!set.contains(curr)) {
                dFSHelper(curr, graph, toReturn, set);
            }
        }
        return toReturn;

    }


    /**
     * Finds the single-source shortest distance between the start vertex and
     * all vertices given a weighted graph (you may assume non-negative edge
     * weights).
     *
     * Return a map of the shortest distances such that the key of each entry
     * is a node in the graph and the value for the key is the shortest distance
     * to that node from start, or Integer.MAX_VALUE (representing infinity)
     * if no path exists.
     *
     * You may import/use {@code java.util.PriorityQueue},
     * {@code java.util.Map}, and {@code java.util.Set} and any class that
     * implements the aforementioned interfaces, as long as it's efficient.
     *
     * You should implement the version of Dijkstra's where you terminate the
     * algorithm once either all vertices have been visited or the PQ becomes
     * empty.
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @throws IllegalArgumentException if any input is null, or if start
     *  doesn't exist in the graph.
     * @param <T> the generic typing of the data
     * @param start index representing which vertex to start at (source)
     * @param graph the graph we are applying Dijkstra's to
     * @return a map of the shortest distances from start to every other node
     *         in the graph
     */
    public static <T> Map<Vertex<T>, Integer> dijkstras(Vertex<T> start,
                                                      Graph<T> graph) {
        if (graph == null) {
            throw new IllegalArgumentException("Graph cannot be null");
        }
        if (start == null) {
            throw new IllegalArgumentException("Start cannot be null");
        } else if (!graph.getVertices().contains(start)) {
            throw new IllegalArgumentException("The starting vertex must be"
                    + " in the graph.");
        }

        HashMap<Vertex<T>, Integer> toReturn = new HashMap<>();

        for (Vertex<T> vertex: graph.getVertices()) {
            toReturn.put(vertex, Integer.MAX_VALUE);
        }
        toReturn.put(start, 0);


        PriorityQueue<Edge<T>> priorityQueue = new PriorityQueue<>();

        priorityQueue.add(new Edge<>(start, start, 0));

        Set<Vertex<T>> set = new HashSet<Vertex<T>>();

        while (!priorityQueue.isEmpty()
                && set.size() != graph.getVertices().size()) {

            Edge<T> curr = priorityQueue.remove();
            Vertex<T> addingVertex = curr.getV();

            if (!set.contains(addingVertex)) {
                toReturn.put(addingVertex, curr.getWeight());
                set.add(addingVertex);

                for (Edge e: graph.getAdjList().get(addingVertex)) {
                    if (!set.contains(e.getV())) {
                        int i = e.getWeight();
                        int j = toReturn.get(addingVertex);
                        Edge<T> newWeight = new Edge<>(e.getU(), e.getV(),
                                e.getWeight()
                                        + toReturn.get(addingVertex));
                        priorityQueue.add(newWeight);
                    }

                }
            }
        }

        return toReturn;

    }


    /**
     * Runs Prim's algorithm on the given graph and return the Minimal
     * Spanning Tree (MST) in the form of a set of Edges. If the graph is
     * disconnected and therefore no valid MST exists, return null.
     *
     * You may assume that the passed in graph is undirected. In this framework,
     * this means that if (u, v, 3) is in the graph, then the opposite edge
     * (v, u, 3) will also be in the graph, though as a separate Edge object.
     *
     * The returned set of edges should form an undirected graph. This means
     * that every time you add an edge to your return set, you should add the
     * opposite edge to the set as well. This is for testing purposes.
     *
     * You may assume that there will only be one valid MST that can be formed.
     *
     * You should NOT allow self-loops into the MST.
     *
     * You may import/use {@code java.util.PriorityQueue},
     * {@code java.util.Set}, and any class that implements the aforementioned
     * interface, as long as it's efficient.
     *
     * The only instance of {@code java.util.Map} that you may use is the
     * adjacency list from {@code graph}. DO NOT create new instances of Map
     * for this method (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @throws IllegalArgumentException if any input
     *  is null, or if {@code start} doesn't exist in the graph
     * @param <T> the generic typing of the data
     * @param start the vertex to begin Prims on
     * @param graph the graph we are applying Prims to
     * @return the MST of the graph or null if there is no valid MST
     */
    public static <T> Set<Edge<T>> prims(Vertex<T> start, Graph<T> graph) {
        if (graph == null) {
            throw new IllegalArgumentException("Graph cannot be null");
        }
        if (start == null) {
            throw new IllegalArgumentException("Start cannot be null");
        } else if (!graph.getVertices().contains(start)) {
            throw new IllegalArgumentException("The starting vertex must be"
                    + " in the graph.");
        }

        HashSet<Edge<T>> edges = new HashSet<>();

        PriorityQueue<Edge<T>> priorityQueue = new PriorityQueue<>();
        for (Edge e: graph.getAdjList().get(start)) {
            priorityQueue.add(e);
        }

        HashSet<Vertex<T>> visited = new HashSet<>();
        visited.add(start);

        while (!priorityQueue.isEmpty()
                && (edges.size() != (2 * (graph.getVertices().size() - 1)))) {

            Edge<T> curr = priorityQueue.remove();
            Vertex<T> addingVertex = curr.getV();

            if (!visited.contains(addingVertex)) {
                edges.add(curr);
                edges.add(new Edge<T>(curr.getV(), curr.getU(),
                        curr.getWeight()));
                visited.add(addingVertex);
                for (Edge e: graph.getAdjList().get(addingVertex)) {
                    if (!visited.contains(e.getV())) {
                        priorityQueue.add(e);
                    }
                }

            }
        }

        if ((edges.size() != (2 * (graph.getVertices().size() - 1)))) {
            return null;
        }
        return edges;

    }
}