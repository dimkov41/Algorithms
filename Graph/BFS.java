import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author Radostin Dimkov 28.05.19
 */
public class BFS {
    private static boolean[] visited = new boolean[10];
    private static List<int[]> graph = new ArrayList<>();
    private static Deque<Integer> queue = new ArrayDeque<>();

    public static void main(String[] args) {
        graph.add(new int[]{6, 3});
        graph.add(new int[]{6, 3, 5, 2, 4});
        graph.add(new int[]{1,5,4});
        graph.add(new int[]{0,5,1});
        graph.add(new int[]{2,6,1});
        graph.add(new int[]{3,1,2});
        graph.add(new int[]{0, 4, 1});

        for (int i = 0; i < graph.size(); i++) {
            BFS(i);
        }
    }

    private static void BFS(int node){
        queue.add(node);

        while (!queue.isEmpty()){

            int lastNode = queue.poll();
            if(!visited[lastNode]){
                visited[lastNode] = true;
                System.out.print(lastNode + " ");

                for (int child : graph.get(lastNode)) {
                    visited[child] = true;
                    System.out.print(child + " ");
                }
            }
        }
    }
}
