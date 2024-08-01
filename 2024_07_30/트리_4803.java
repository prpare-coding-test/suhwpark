package backJoon;

import java.util.*;
import java.io.*;

public class 트리_4803 {
    private static int n, m;
    private static List<Integer>[] tree;
    private static boolean visited[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int testCase = 1;

        while(true) {
            String[] input = br.readLine().split(" ");
            n = Integer.parseInt(input[0]);
            m = Integer.parseInt(input[1]);

            if (n == 0 && m == 0) {
                break;
            }

            int ans = 0;
            tree = new List[n + 1];
            visited = new boolean[n + 1];

            for (int i = 1; i <= n; ++i) {
                tree[i] = new ArrayList<>();
            }

            //일단 인잡행렬을 사용하여, 노드를 연결해준다.
            for (int i = 1; i <= m; ++i) {
                input = br.readLine().split(" ");
                int a = Integer.parseInt(input[0]);
                int b = Integer.parseInt(input[1]);

                tree[a].add(b);
                tree[b].add(a);
            }

            //각각의 노드를 순회하면서, 방문하지 않았다면, 그 노드와 연결되어있는 모든 노드를 탐색
            for (int i = 1; i <= n; ++i) {
                if (!visited[i]) {
                    visited[i] = true;
                    if (dfs(-1, i)) {
                        ans += 1;
                    }
                }
            }

            sb.append("Case ").append(testCase).append(": ");
            if (ans == 0) {
                sb.append("No trees.\n");
            } else if (ans == 1) {
                sb.append("There is one tree.\n");
            } else {
                sb.append("A forest of ").append(ans).append(" trees.\n");
            }
            testCase += 1;
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    /**
     * 인접 행렬을 순회하면서, 방문하지 않은 모든 노드에 관해 탐색을한다.
     * 탐색을 완료했다면 true 반환하여 트리를 확인한다.
     * @param root
     * @param num
     * @return
     */
    private static boolean dfs(int root, int num) {
        for (int v : tree[num]) {
            if (v == root) {
                continue;
            }
            if (visited[v]) {
                return false;
            }
            visited[v] = true;
            // 연결되어 있는 num를 root로 설정하고, num번째 graph 요소의 인접행렬에서 다시 연결된 노드를 찾는방식
            if (!dfs(num, v)) {
                return false;
            }
        }
        return true;
    }
}
