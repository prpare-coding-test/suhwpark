package backJoon;

import java.io.*;
import java.util.*;
public class 바이러스_2606 {
    private static int N, M;
    private static int[][] graph = new int[101][101];
    private static boolean[] visited = new boolean[101];
    private static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(bf.readLine());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a][b] = 1;
            graph[b][a] = 1;
        }

        dfs(1);
        //총 감염된 컴퓨터 개체는 answer개 이지만 1번 컴퓨터를 제외해야한다.
        bw.write(new StringBuilder().append(answer - 1).toString());
        bw.close();
    }

    /**
     * 인접 행렬을 사용하여, 감염된 컴퓨터를 찾는다
     * 자신과 연결되어있는 노드를 찾은 후 dfs 사용하여 또 연결되 노드들을 찾아간다.
     * 연결 되어있음을 알기 위해 answer += 1을 해준다.
     * @param index
     */
    private static void dfs(int index) {
        if (visited[index]) {
            return ;
        }
        visited[index] = true;
        answer += 1;
        for (int i = 1; i <= N; i++) {
            if (!visited[i] && graph[index][i] == 1) {
                dfs(i);
            }
        }
    }
}
