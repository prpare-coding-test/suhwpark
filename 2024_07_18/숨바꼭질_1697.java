package backJoon;

import java.io.*;
import java.util.*;

public class 숨바꼭질_1697 {
    private static int N, M;
    private static int time;
    private static boolean[] visited;
    private static class Pos{
        int index;
        int time;

        Pos(int i, int t) {
            this.index = i;
            this.time = t;
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        //bfs를 사용하여, 가장 빠르게 M에 닿는 것을 찾는다.
        visited = new boolean[100001];

        bfs(N);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(new StringBuilder().append(time).toString());
        bw.close();
        br.close();
    }

    /**
     * x - 1, x + 1, 2 * x로 이동 위치를 판단하고, 범위 안에 들어가 있고 방문하지 않은 곳이면
     * Queue에 넣어 또 다음 위치를 판단한다.
     * 가장 빠르게 M에 도착했을 때 시간을 출력하고 Queue 순회를 멈춘다.(어짜피 가장 빨리 닿기 떄문)
     * @param n 첫 수빈이 위치
     */
    private static void bfs(int n) {
        Queue<Pos> q = new LinkedList<>();
        visited[n] = true;
        q.add(new Pos(n, 0));

        while(!q.isEmpty()) {
            Pos p = q.poll();

            if (p.index == M) {
                time = p.time;
                break ;
            }
            int before = p.index - 1;
            int next = p.index + 1;
            int space = p.index * 2;

            if (isInRange(before) && !visited[before]) {
                visited[before] = true;
                q.add(new Pos(before, p.time + 1));
            }
            if (isInRange(next) && !visited[next]) {
                visited[next] = true;
                q.add(new Pos(next, p.time + 1));
            }
            if (isInRange(space) && !visited[space]) {
                visited[space] = true;
                q.add(new Pos(space, p.time + 1));
            }
        }
    }

    private static boolean isInRange(int i) {
        return i >= 0 && i <= 100000;
    }
}
