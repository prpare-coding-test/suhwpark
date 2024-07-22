package backJoon;

import java.util.*;
import java.io.*;

public class 유기농_배추추_1012 {
    private static int T, M, N, K;
    private static StringBuilder sb;
    private static int worm;
    private static int[][] map;
    private static boolean[][] visited;
    private static int[] dy = {1, -1, 0, 0};
    private static int[] dx = {0, 0, 1, -1};
    private static class Pos {
        int y;
        int x;

        Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());
        sb = new StringBuilder();
        for (int i = 0; i < T; ++i) {
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            map = new int[N][M];
            visited = new boolean[N][M];
            for (int j = 0; j < K; ++j) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                map[b][a] = 1;
            }
            getWorm();
            sb.append(worm).append('\n');
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    /**
     * test case가 주어지기 떄문에 함수를 분리해서 사용
     * 방문하지 않고, 배추가 있는 곳이라면, 4방향으로 연결된 배추를 찾으러 bfs 실행
     */
    private static void getWorm() {
        worm = 0;
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                if (!visited[i][j] && map[i][j] == 1) {
                    bfs(i, j);
                    worm += 1;
                }
            }
        }
    }

    /**
     * 기본적인 bfs를 통해서, 연결되어있는 배추들을 방문한다.
     *
     * @param y 배추의 y점
     * @param x 배추의 x점
     */
    private static void bfs(int y, int x) {
        Queue<Pos> q = new LinkedList<>();
        q.add(new Pos(y, x));

        visited[y][x] = true;

        while(!q.isEmpty()) {
            Pos p = q.poll();

            for (int i = 0; i < 4; ++i) {
                int ny = p.y + dy[i];
                int nx = p.x + dx[i];

                if (!isInRange(ny, nx) || map[ny][nx] != 1 || visited[ny][nx]) {
                    continue;
                }
                q.add(new Pos(ny, nx));
                visited[ny][nx] = true;
            }
        }
    }

    private static boolean isInRange(int y, int x) {
        return (y >= 0 && y < N) && (x >= 0 && x < M);
    }
}
