package backJoon;

import java.util.*;
import java.io.*;

public class N_과_M_15649 {
    static int N, M;
    static List<String> answer;
    static boolean[] visited;
    static int[] result;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N];
        result = new int[M];
        answer = new ArrayList<>();
        dfs(0);

        for (String ans : answer) {
            bw.write(ans);
        }
        bw.close();
        bf.close();
    }

    /**
     * 1부터 N 까지의 M개 배열을 가능한 조합의 수를 구한다.
     * 1부터 4까지라면, 1 2, 1 3, 1 4....의 모든 경우의 수를 정해야하기 때문에 dfs()의 완전탐색을 사용한다
     * 또한 첫번쨰의 원소를 가지고 조합을 구했다면, 중복은 허용하지 않기 때문에 백트래킹 개념을 사용하여, visited 배열을 만들어 중복을 허용하지 않는다
     * M의 숫자만큼 depth가 늘어난다면, M개만큼의 조합을 찾았기에 StringBuilder에 넣어준다
     *
     * @param depth 가능한 조합의 갯수가 되는 것을 판단하는 변수
     */
    private static void dfs(int depth) {
        StringBuilder sb = new StringBuilder();
        if (depth == M) {
            for (int val : result) {
                sb.append(val).append(' ');
            }
            sb.append('\n');
            answer.add(sb.toString());
            return;
        }
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                result[depth] = i + 1;
                dfs(depth + 1);
                visited[i] = false;
            }
        }

    }
}
