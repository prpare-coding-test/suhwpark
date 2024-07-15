package backJoon;

import java.util.*;
import java.io.*;

public class DFS_와_BFS_1260 {
    private static int N, M, V;
    private static int[][] graph = new int[1001][1001];
    private static boolean[] visited = new boolean[1001];
    private static List<Integer> dfsAnswer;
    private static List<Integer> bfsAnswer;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a][b] = 1;
            graph[b][a] = 1;
        }

        dfsAnswer = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            // 출발 지점과 같은 숫자면 탐색을 시작한다!
            if (!visited[i] && i == V) {
                dfs(i);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int v : dfsAnswer) {
            sb.append(v).append(' ');
        }
        sb.append('\n');

        visited = new boolean[1001];
        bfsAnswer = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            if (!visited[i] && i == V) {
                bfs(i);
            }
        }
        for (int v : bfsAnswer) {
            sb.append(v).append(' ');
        }
        sb.append('\n');
        bw.write(sb.toString());
        bw.close();
    }

    /**
     * 방문 하지 않은 노드며, 연결되어있다면 그 노드로 부터 시작하는 다른 노드를 탐색하러 dfs를 실행한다
     * @param index 연결되어있는 노드를 탐색하는 index
     */
    private static void dfs(int index) {
        if (visited[index]) {
            return ;
        }
        visited[index] = true;
        dfsAnswer.add(index);
        for (int i = 1; i <= N; i++) {
            if (graph[index][i] == 1) {
                dfs(i);
            }
        }
    }

    /**
     * dfs와 같은 방법으로 방문 했는지에 대한 여부를 판단하고, 방문하지 않고, 연결되어있는 노드라면, 큐에 넣어준다
     * 큐가 빌때 까지 연결되어있는 노드를 찾는다.
     * @param index 연결되어있는 노드를 탐색하는 index
     */
    private static void bfs(int index) {
        Queue<Integer> q = new LinkedList<>();
        q.add(index);
        visited[index] = true;

        while(!q.isEmpty()) {
            int cur = q.poll();
            bfsAnswer.add(cur);
            for (int i = 1; i <= N; i++) {
                if (!visited[i] && graph[cur][i] == 1) {
                    q.add(i);
                    visited[i] = true;
                }
            }
        }
    }
}
