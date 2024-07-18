package backJoon;

import java.util.*;
import java.io.*;

public class 연결_요소의_개수_11724 {
    private static int N, M;
    //인접 행렬을 사용하여 푸는 문제
    private static int[][] graph = new int[1001][1001];
    private static boolean[] visited = new boolean[1001];

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            //1 5, 5 1은 서로 같은 것이기 떄문에 값이 있는 1로 처리한다
            graph[a][b] = 1;
            graph[b][a] = 1;
        }

        int answer = 0;
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                dfs(i);
                // (1, 5) (5, 1) 이 하나여도 연결되어 있는 요소기에 ++해준다.
                answer += 1;
            }
        }

        StringBuilder sb = new StringBuilder();
        bw.write(sb.append(answer).toString());
        bw.close();
        bf.close();
    }

    /**
     * visited 배열을 사용하여, 내가 방문한 노드인지 판단 후 방문하지 않았다면 진행한다
     * 파라미터 column 을 visited 처리를 한다
     * 그래프를 순회하면서, 내 column 에서 연결되어있는 row을 찾고, 그 row를 다시 column으로 하여 dfs를 진행
     * 즉, 방문 여부를 체크하면서, 연결되어있는 모든 요소를 찾는 것
     *
     * @param index 열에 대한 행을 판단하기 위하여, 사용하는 변수 즉 (1, 5) (1, 6) ... 어느곳이 연결되어있는지 확인하는 것
     */
    private static void dfs(int index) {
        if (visited[index]) {
            return ;
        }
        visited[index] = true;
        for (int i = 1; i <= N; i++) {
            if (graph[index][i] == 1) {
                dfs(i);
            }
        }
    }
}
