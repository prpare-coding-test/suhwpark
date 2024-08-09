package backJoon;

import java.util.*;
import java.io.*;

public class 상범빌딩_6593 {
    private static int L, R, C;
    private static char[][][] building;
    private static int[] dy = {-1, 1, 0, 0, 0, 0};
    private static int[] dx = {0, 0, -1, 1, 0, 0};
    private static int[] dh = {0, 0, 0, 0, -1, 1};
    private static boolean[][][] visited;
    private static Queue<Pos> q;
    private static StringBuilder sb;
    private static class Pos{
        int h, y, x, t;

        public Pos(int h, int y, int x, int t) {
            this.h = h;
            this.y = y;
            this.x = x;
            this.t = t;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        while (true) {
            String s = br.readLine();
            if (s.equals("0 0 0")) {
                break;
            }

            String[] input = s.split(" ");
            L = Integer.parseInt(input[0]);
            R = Integer.parseInt(input[1]);
            C = Integer.parseInt(input[2]);

            building = new char[L][R][C];
            visited = new boolean[L][R][C];
            q = new LinkedList<>();
            for (int i = 0; i < L; ++i) {
                for (int j = 0; j < R; ++j) {
                    char[] line = br.readLine().toCharArray();
                    for (int k = 0; k < C; ++k) {
                        building[i][j][k] = line[k];
                        if (line[k] == 'S') {
                            q.add(new Pos(i, j, k, 0));
                            visited[i][j][k] = true;
                        }
                    }
                }
                br.readLine();
            }
            bfs();
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    /**
     * 2차원 맵에서 탈출을 찾는것이 아니기 때문에, 3차원 배열을 선언해서 bfs를 진행
     * 만약 E라면 탈출 한것으로, Pos class가 가지고 있는 time을 반환한다
     * 만약 탈출을 하지 못하면 trapped를 반환
     *
     */
    private static void bfs() {
        int time = 0;

        while(!q.isEmpty()) {
            Pos p = q.poll();
            time = p.t;

            if (building[p.h][p.y][p.x] == 'E') {
                sb.append("Escaped in ").append(time).append(" minute(s).").append('\n');
                return;
            }
            for (int i = 0; i < 6; ++i) {
                int nh = p.h + dh[i];
                int ny = p.y + dy[i];
                int nx = p.x + dx[i];

                if (nh < 0 || nh >= L || ny < 0 || ny >= R || nx < 0 || nx >= C ||
                        visited[nh][ny][nx] || building[nh][ny][nx] == '#') {
                    continue;
                }
                q.add(new Pos(nh, ny, nx, time + 1));
                visited[nh][ny][nx] = true;
            }
        }
        sb.append("Trapped!").append('\n');
    }
}
