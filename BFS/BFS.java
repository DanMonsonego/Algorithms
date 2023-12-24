package BFS;

import java.util.*;

public class BFS {
    public static class Graph {
        Map<Character, List<Character>> adjList;

        public Graph(Map<Character, List<Character>> adjList) {
            this.adjList = adjList;
        }
    }

    public static Map<Character, Integer> bfs(Graph graph, char start) {
        Map<Character, Integer> distance = new HashMap<>();
        Map<Character, Character> predecessor = new HashMap<>();
        Map<Character, String> color = new HashMap<>();

        for (char vertex : graph.adjList.keySet()) {
            distance.put(vertex, Integer.MAX_VALUE);
            predecessor.put(vertex, null);
            color.put(vertex, "WHITE");
        }

        distance.put(start, 0);
        color.put(start, "GRAY");
        Queue<Character> queue = new LinkedList<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            char u = queue.poll();

            for (char v : graph.adjList.getOrDefault(u, new ArrayList<>())) {
                if (color.get(v).equals("WHITE")) {
                    color.put(v, "GRAY");
                    distance.put(v, distance.get(u) + 1);
                    predecessor.put(v, u);
                    queue.add(v);
                }
            }

            color.put(u, "BLACK"); // Mark the vertex u as fully explored
        }

        return distance;
    }

    public static void printTable(Map<Character, Integer> distance, Map<Character, Character> predecessor, Map<Character, String> color) {
        List<Character> nodes = new ArrayList<>(color.keySet());
        Collections.sort(nodes);
        List<String> distances = new ArrayList<>();
        List<String> predecessors = new ArrayList<>();
        List<String> colors = new ArrayList<>();

        for (char node : nodes) {
            distances.add(distance.get(node) != Integer.MAX_VALUE ? String.valueOf(distance.get(node)) : "∞");
            predecessors.add(predecessor.get(node) != null ? String.valueOf(predecessor.get(node)) : "NIL");
            colors.add(color.get(node));
        }

        // Print header
        System.out.println(String.format("%-10s%-10s%-10s%-10s", "node", "d", "π", "color"));
        System.out.println("-".repeat(40));

        // Print rows
        for (int i = 0; i < nodes.size(); i++) {
            System.out.println(String.format("%-10s%-10s%-10s%-10s", nodes.get(i), distances.get(i), predecessors.get(i), colors.get(i)));
        }
    }

    public static void main(String[] args) {
        Map<Character, List<Character>> graph = new HashMap<>();
        graph.put('S', Arrays.asList('A', 'B', 'C'));
        graph.put('A', Arrays.asList('B', 'D'));
        graph.put('B', Arrays.asList('C', 'D'));
        graph.put('C', Arrays.asList('D', 'E'));
        graph.put('D', Arrays.asList('F', 'G'));
        graph.put('E', Arrays.asList('D', 'G'));
        graph.put('F', Arrays.asList('A'));
        graph.put('G', Arrays.asList('F'));

        Graph g = new Graph(graph);

        char startVertex = 'S'; // Change this to the desired starting vertex

        Map<Character, Integer> distance = bfs(g, startVertex);
        Map<Character, Character> predecessor = new HashMap<>();
        Map<Character, String> color = new HashMap<>();

        // Populate predecessor and color maps
        for (char vertex : graph.keySet()) {
            predecessor.put(vertex, null);
            color.put(vertex, "WHITE");
        }

        distance.forEach((k, v) -> {
            if (v != Integer.MAX_VALUE) {
                color.put(k, "BLACK");
                char pred = ' ';
                for (char key : graph.keySet()) {
                    if (graph.get(key).contains(k)) {
                        pred = key;
                        break;
                    }
                }
                predecessor.put(k, pred);
            }
        });

        printTable(distance, predecessor, color);
    }
}
