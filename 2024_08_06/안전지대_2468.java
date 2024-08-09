package backJoon;

import java.io.*;
import java.util.*;

public class 안전지대_2468 {
    private static int N;
    private static int[][] map;
    private static int[] dy = {-1, 1, 0, 0};
    private static int[] dx = {0, 0, -1, 1};
    private static List<Integer> height;
    private static boolean[][] visited;
    private static int max;
    private static int cnt;
    private static class Pos {
        int y;
        int x;

        public Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        height = new ArrayList<>();
        // map에 있는 높이에 따른 최대 건물 영역을 구해야하기 떄문에 list에 중복없이 넣었다.
        for (int i = 0; i < N; ++i) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < N; ++j) {
                int h = Integer.parseInt(input[j]);
                map[i][j] = h;
                if (!height.contains(h)) {
                    height.add(h);
                }
            }
        }

        for (int h : height) {
            visited = new boolean[N][N];
            cnt = 0;
            for (int i = 0; i < N; ++i) {
                for (int j = 0; j < N; ++j) {
                    if (!visited[i][j] && map[i][j] >= h) {
                        bfs(new Pos(i, j), h);
                    }
                }
            }
            max = Math.max(max, cnt);
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(new StringBuilder().append(max).toString());
        bw.close();
        br.close();
    }

    /**
     * 주어진 높이 이상인 건물을 찾는 bfs
     * 기준 pos에서 4방향으로 연결된 건물을 방문한다
     * 영역을 구하는 문제이기 때문에, bfs가 시작되면 영역을 ++ 해준다.
     * @param pos 기준 좌표
     * @param h 기준 높이
     */
    private static void bfs(Pos pos, int h) {
        Queue<Pos> q = new LinkedList<>();
        q.add(pos);
        visited[pos.y][pos.x] = true;
        cnt++;
        while (!q.isEmpty()) {
            Pos p = q.poll();

            for (int i = 0; i < 4; ++i) {
                int ny = p.y + dy[i];
                int nx = p.x + dx[i];

                if (ny < 0 || ny >= N || nx < 0 || nx >= N || visited[ny][nx] || map[ny][nx] < h) {
                    continue;
                }
                q.add(new Pos(ny, nx));
                visited[ny][nx] = true;
            }
        }
    }
}
