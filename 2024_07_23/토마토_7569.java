package backJoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 토마토_7569 {
    private static int N, M, H;
    private static int[][][] box;
    private static int[] dy = {-1, 1, 0, 0, 0, 0};
    private static int[] dx = {0, 0, -1, 1, 0, 0};
    private static int[] dh = {0, 0, 0, 0, -1, 1};
    private static Queue<Pos> q;
    private static class Pos{
        int h;
        int y;
        int x;
        int d;
        public Pos(int h, int y, int x, int d){
            this.h = h;
            this.y = y;
            this.x = x;
            this.d = d;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        box = new int[H][N][M];
        q = new LinkedList<>();
        /**
         * bfs()을 통해서 저장된 익은 토마토를 순회하면서, 6방향에 익지 않은 토마토에게 영향을 준다
         * Q가 비워지면 토마토가 다 익은 것이기 때문에, 모든 바구니를 순회하면서, 익지 않은 토마토가 있는지 확인하고 결과를 return
         */
        for (int i = 0; i < H; ++i) {
            for (int j = 0; j < N; ++j) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < M; ++k) {
                    box[i][j][k] = Integer.parseInt(st.nextToken());
                    if (box[i][j][k] == 1) {
                        q.add(new Pos(i, j, k, 0));
                    }
                }
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(new StringBuilder().append(bfs()).toString());
        bw.close();
        br.close();
    }

    private static int bfs() {
        int day = 0;

        while(!q.isEmpty()) {
            Pos p = q.poll();
            day = p.d;

            for (int i = 0; i < 6; ++i) {
                int nh = p.h + dh[i];
                int ny = p.y + dy[i];
                int nx = p.x + dx[i];


                if (nh < 0 || nh >= H || ny < 0 || ny >= N || nx < 0 || nx >= M || box[nh][ny][nx] != 0) {
                    continue;
                }
                box[nh][ny][nx] = 1;
                q.add(new Pos(nh, ny, nx, day + 1));
            }
        }

        if (!checkBox()) {
            return -1;
        }
        return day;
    }

    private static boolean checkBox() {
        for (int i = 0; i < H; ++i) {
            for (int j = 0; j < N; ++j) {
                for (int k = 0; k < M; ++k) {
                    if (box[i][j][k] == 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
