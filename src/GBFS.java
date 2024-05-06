import java.util.*;

public class GBFS {

    private static class Node {
        String word;
        Node parent;
        int heuristic; // Heuristic value for GBFS

        Node(String word, Node parent, int heuristic) {
            this.word = word;
            this.parent = parent;
            this.heuristic = heuristic;
        }
    }

    public static List<String> findPath(String start, String end, Set<String> words) {
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(node -> node.heuristic));
        Set<String> visited = new HashSet<>();
        queue.add(new Node(start, null, calculateHeuristic(start, end)));
        visited.add(start);
    
        int nodesVisited = 0; // Add a counter for nodes visited
    
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            String currentWord = current.word;
    
            nodesVisited++; // Increment the counter each time a node is visited
    
            if (currentWord.equals(end)) {
                List<String> path = new ArrayList<>();
                while (current != null) {
                    path.add(current.word);
                    current = current.parent;
                }
                Collections.reverse(path);
    
                path.add("Nodes visited: " + nodesVisited); // Add the total nodes visited to the path
    
                return path;
            }
    
            StringBuilder wordBuilder = new StringBuilder(currentWord);
            for (int i = 0; i < wordBuilder.length(); i++) {
                char originalChar = wordBuilder.charAt(i);
                for (char c = 'a'; c <= 'z'; c++) {
                    if (c != originalChar) {
                        wordBuilder.setCharAt(i, c);
                        String newWord = wordBuilder.toString();
                        if (words.contains(newWord) && !visited.contains(newWord)) {
                            int heuristic = calculateHeuristic(newWord, end);
                            queue.add(new Node(newWord, current, heuristic));
                            visited.add(newWord);
                        }
                    }
                }
                wordBuilder.setCharAt(i, originalChar); 
            }
        }
    
        // If no path found, return the total nodes visited
        List<String> result = new ArrayList<>();
        result.add("No path found.");
        result.add("Nodes visited: " + nodesVisited);
        return result;
    }

    private static int calculateHeuristic(String current, String end) {
        int heuristic = 0;
        for (int i = 0; i < current.length(); i++) {
            if (current.charAt(i) != end.charAt(i)) {
                heuristic++;
            }
        }
        return heuristic;
    }
}


