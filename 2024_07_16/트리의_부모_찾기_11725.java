package backJoon;

import java.util.*;
import java.io.*;
public class 트리의_부모_찾기_11725 {
    private static int N;
    private static int[] parents;
    private static boolean[] visited;
    //처음에 배열로 풀엇다가, 메모리 초과가 나 List로 풀었습니다.
    private static List<List<Integer>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            // 자신과 연결된 노드를 list에 넣어준다. 양방향으로
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        parents = new int[N + 1];
        visited = new boolean[N + 1];

        dfs(1);
        for (int i = 2; i < parents.length; i++) {
            System.out.println(parents[i]);
        }
    }

    /**
     * 인접 행렬과 같이 모든 노드를 순회하면서, 방문하지 않았을 경우 그 노드도 순회하며 N번째의 부모 노드를 기록한다.
     * ex)
     * 1 6
     * 6 3
     * 3 5
     * 4 1
     * 2 4
     * 4 7
     * 라고 할 때
     * 1 -> 6, 4
     * 6 -> 1, 3
     * 3 -> 6, 5
     * 5 -> 3
     * 4 -> 1, 2, 7
     * 2-> 4, 7
     * 7 -> 4
     *
     * 그렇기에 순회하여 방문 후 2번째 부모의 노드를 출력한다면
     * 4 6 1 3 1 4
     * @param index
     */
    private static void dfs(int index) {
        visited[index] = true;
        for (int v : graph.get(index)) {
            if (!visited[v]) {
                parents[v] = index;
                dfs(v);
            }
        }
    }
}
