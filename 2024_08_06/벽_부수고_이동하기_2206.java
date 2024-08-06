package backJoon;

import java.util.*;
import java.io.*;

public class 벽_부수고_이동하기_2206 {
    private static int N, M;
    private static int[][] map;
    private static boolean[][][] visited;
    private static int[] dy = {-1, 1, 0, 0,};
    private static int[] dx = {0, 0, -1, 1};
    private static Queue<Node> q;
    private static StringBuilder sb;
    private static class Node {
        int y;
        int x;
        int t;
        int skill;

        public Node (int y, int x, int t, int s) {
            this.y = y;
            this.x = x;
            this.t = t;
            this.skill = s;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        map = new int[N][M];
        visited = new boolean[N][M][2];
        for (int i = 0; i < N; ++i) {
            input = br.readLine().split("");
            for (int j = 0; j < M; ++j) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }
        q = new LinkedList<>();

        q.add(new Node(0, 0, 1, 1));
        visited[0][0][0] = true;
        sb = new StringBuilder();
        bfs();

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    /**
     * 벽을 부순 경우와 부수지 않은 경우 visited 배열의 마지막로 구분한다
     * 만약 벽을 만났을 경우, Node의 skill을 0으로 만들고, 부순 방문 배열에 체크한다
     * 그리고 종착지에 도착했을 때, 시간을 반환한다
     * 만약 도착하지 못했을 경우 -1 반환
     */
    private static void bfs() {
        int time;

        while (!q.isEmpty()) {
            Node n = q.poll();
            time = n.t;

            if (n.y == N - 1 && n.x == M - 1) {
                sb.append(time).append('\n');
                return;
            }
            for (int i = 0; i < 4; ++i) {
                int ny = n.y + dy[i];
                int nx = n.x + dx[i];

                if (ny < 0 || ny >= N || nx < 0 || nx >= M) {
                    continue;
                }
                if (map[ny][nx] == 1) {
                    if (n.skill == 1) {
                        q.add(new Node(ny, nx, time + 1, 0));
                        visited[ny][nx][1] = true;
                    }
                } else {
                    if (n.skill == 1 && !visited[ny][nx][0]) {
                        q.add(new Node(ny, nx, time + 1, n.skill));
                        visited[ny][nx][0] = true;
                    } else if (n.skill == 0 && !visited[ny][nx][1])
                    q.add(new Node(ny, nx, time + 1, n.skill));
                    visited[ny][nx][1] = true;
                }
            }
        }
        sb.append(-1).append('\n');
    }
}
