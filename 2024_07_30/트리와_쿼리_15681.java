package backJoon;

import java.util.*;
import java.io.*;
public class 트리와_쿼리_15681 {
    private static int N, R, Q;
    private static List<Integer>[] tree;
    private static int[] dp;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        tree = new List[N + 1];

        for (int i = 1; i <= N; ++i) {
            tree[i] = new ArrayList<>();
        }

        //인접 행렬 구성
        for (int i = 1; i <= N - 1; ++i) {
            String[] input = br.readLine().split(" ");
            int u = Integer.parseInt(input[0]);
            int v = Integer.parseInt(input[1]);

            tree[u].add(v);
            tree[v].add(u);
        }
        dp = new int[N + 1];
        visited = new boolean[N + 1];

        //root 노드 부터 연결된 노드를 다 탐색한다.
        //탐색 하려는 노드에 연결된 노드들을 다 dp에 저장한다
        dfs(R);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; ++i) {
            int q = Integer.parseInt(br.readLine());
            //dp에서 연결된 노드의 갯수를 출력
            sb.append(dp[q]).append('\n');
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    /**
     * dp[root]의 값이 0이 아니라면, 방문하고 노드를 검색했기 떄문에 dp[root]의 값을 리턴
     * dp[root]의 값이 0이 라면, 방문 표시를 하고 노드를 탐색한다
     * 인접행렬에 연결해 놓은 값들의 방문 여부를 확인하고, 방문하지 않았다면 dp[value]의 값을 cnt에 더해준다
     * -> 각 연결된 갯수기 때문
     * 탐색이 끝났다면 dp[root] = cnt
     * @param root
     * @return
     */
    private static int dfs(int root) {
        if (dp[root] != 0) {
            return dp[root];
        }
        visited[root] = true;
        int cnt = 1;

        for (int value : tree[root]) {
            if (visited[value]) {
                continue;
            }
            cnt += dfs(value);
        }

        dp[root] = cnt;

        return dp[root];
    }
}

