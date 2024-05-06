import java.util.*;

public class AStar {

    private static class Node {
        String word;
        Node parent;
        int cost;
        int heuristic;

        Node(String word, Node parent, int cost, int heuristic) {
            this.word = word;
            this.parent = parent;
            this.cost = cost;
            this.heuristic = heuristic;
        }

        int getFValue() {
            return cost + heuristic;
        }
    }

    public static List<String> findPath(String start, String end, Set<String> words) {
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(Node::getFValue));
        Map<String, Integer> costs = new HashMap<>();
        queue.add(new Node(start, null, 0, calculateHeuristic(start, end)));
        costs.put(start, 0);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            String currentWord = current.word;

            if (currentWord.equals(end)) {
                List<String> path = new ArrayList<>();
                while (current != null) {
                    path.add(current.word);
                    current = current.parent;
                }
                Collections.reverse(path);

                return path;
            }

            StringBuilder wordBuilder = new StringBuilder(currentWord);
            for (int i = 0; i < wordBuilder.length(); i++) {
                char originalChar = wordBuilder.charAt(i);
                for (char c = 'a'; c <= 'z'; c++) {
                    if (c != originalChar) {
                        wordBuilder.setCharAt(i, c);
                        String newWord = wordBuilder.toString();
                        if (words.contains(newWord)) {
                            int newCost = costs.getOrDefault(currentWord, Integer.MAX_VALUE) + 1;
                            if (newCost < costs.getOrDefault(newWord, Integer.MAX_VALUE)) {
                                costs.put(newWord, newCost);
                                int heuristic = calculateHeuristic(newWord, end);
                                queue.add(new Node(newWord, current, newCost, heuristic));
                            }
                        }
                    }
                }
                wordBuilder.setCharAt(i, originalChar); // revert back for next iteration
            }
        }

        return null; // no path found
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



