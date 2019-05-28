import java.util.ArrayList;
import java.util.List;

/**
 * @author Radostin Dimkov 28.05.19
 */
public class DFS {
    private static boolean[] visited = new boolean[10];
    private static List<int[]> graph = new ArrayList<>();

    public static void main(String[] args) {
        graph.add(new int[]{6, 3});
        graph.add(new int[]{6, 3, 5, 2, 4});
        graph.add(new int[]{1,5,4});
        graph.add(new int[]{0,5,1});
        graph.add(new int[]{2,6,1});
        graph.add(new int[]{3,1,2});
        graph.add(new int[]{0, 4, 1});

        for (int i = 0; i < graph.size(); i++) {
            DFS(i);
        }
    }

    private static void DFS(int node){

        if(!visited[node]){
            visited[node] = true;

            for (int i : graph.get(node)) {
                DFS(i);
            }

            System.out.print(node + " ");
        }
    }
}
