package backJoon;

import java.util.*;
import java.io.*;

public class 구슬찾기_2617 {
    private static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        //각각 자신보다 무겁고 가볍고를 저장하기 위한 List선언
        List<Integer>[] lighter = new List[N + 1];
        List<Integer>[] heavier = new List[N + 1];

        for (int i = 0; i <= N; ++i) {
            lighter[i] = new ArrayList<>();
            heavier[i] = new ArrayList<>();
        }
        //2, 1 로 들어왔다면, 1은 2보다 가볍다 2는 1보다 무겁다를 저장
        for (int i = 0; i < M; ++i) {
            String[] input = br.readLine().split(" ");
            lighter[Integer.parseInt(input[0])].add(Integer.parseInt(input[1]));
            heavier[Integer.parseInt(input[1])].add(Integer.parseInt(input[0]));
        }

        // 각 요소마다 무거운 갯수와 가벼운 갯수를 구하기위해 배열 선언
        int[] lightCnt = new int[N + 1];
        int[] heavyCnt = new int[N + 1];

        int result = 0;
        //갯수의 반 이상이라면 중간에 있는 것이 아니다
        int limit = (N / 2) + 1;

        for (int i = 1; i <=N; ++i) {
            heavyCnt[i] = bfs(heavier, i);
            lightCnt[i] = bfs(lighter, i);

            if (heavyCnt[i] >= limit || lightCnt[i] >= limit) {
                result++;
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(new StringBuilder().append(result).append('\n').toString());
        bw.close();
        br.close();
    }

    /**
     * 방문 배열을 선언한다
     * 각 무거운 인접행렬과 가벼운 인접행렬을 순회하면서 방문하고 큐에 넣는다
     * 큐의 값에서 연결된 또 다른 가볍거나 무거운 갯수를 구한다
     * 3 2 2 1 이라면 1보다 큰 것은 2 3 이 된다
     * 
     * @param list
     * @param i
     * @return
     */
    private static int bfs(List<Integer>[] list, int i) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];

        q.add(i);
        int res = 0;
        while (!q.isEmpty()) {
            int curr = q.poll();
            for (int value : list[curr]) {
                if (!visited[value]) {
                    visited[value] = true;
                    q.add(value);
                    res++;
                }
            }
        }
        return res;
    }
}
