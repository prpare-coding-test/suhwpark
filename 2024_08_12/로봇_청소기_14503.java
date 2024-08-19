package backJoon;

import java.io.*;

public class 로봇_청소기_14503 {
    private static int N, M;
    private static int r, c, d;
    private static int[][] map;
    private static int[] dy = {-1, 0, 1, 0};
    private static int[] dx = {0, 1, 0, -1};
    private static int cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        input = br.readLine().split(" ");
        r = Integer.parseInt(input[0]);
        c = Integer.parseInt(input[1]);
        d = Integer.parseInt(input[2]);

        map = new int[N][M];

        for (int i = 0; i < N; ++i) {
            input = br.readLine().split(" ");
            for (int j = 0; j < M; ++j) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }
        cnt = 1;
        clean(r, c, d);

        System.out.println(cnt);
    }

    /**
     * 로봇 청소기가 움직이는 경우
     * 1. 현재 위치가 청소 되어있지 않으면 청소 cnt++
     * 2. 동서남북에서 청소할 수 있는 칸이 없다면, 후진한다. 후진 할 수 없으면 종료
     * 3. 동서남북에서 청소할 수 있는 칸이 있다면, 반시계 90 회전후 1번 실행
     * 4. 북 : 0, 동 : 1, 남 : 2, 3: 서 -> 인데스로 나누어서 진행
     *
     * 반시계 방향을 배열로 관리하기 위한 로직 {북, 서, 남, 동}
     * 북 -> 서
     * 서 -> 남
     * 남 -> 동
     * 동 -> 북
     *
     * 인덱스로 반시계 90도를 회전하는 방법
     * 북 -> 서 : 0 + (-1) = (-1 + 4) % 4 = 3;
     * 서 -> 남 : 3 + (-1) = (2 + 4) % 4 = 2;
     * 남 -> 동 : 2 + (-1) = (1 + 4) % 4 = 1;
     * 동 -> 북 : 1 + (-1) = (0 + 4) % 4 = 0;
     *
     * 이제 구현 ㄱㄱ
     */
    private static void clean(int y, int x, int dir) {
        // 위치 청소
        map[y][x] = -1;

        for (int i = 0; i < 4; i++) {
            dir = (dir + 3) % 4;

            int ny = y + dy[dir];
            int nx = x + dx[dir];

            // 전진해서 다 청소가 되어있느지 확인
            if (ny >= 0 && ny < N && nx >= 0 && nx < M) {
                if (map[ny][nx] == 0) {
                    cnt++;
                    clean(ny, nx, dir);
                    return;
                }
            }
        }

        // 후진할 수 있는지 확인
        int back = (dir + 2) % 4;
        int by = y + dy[back];
        int bx = x + dx[back];

        if (by >= 0 && by < N && bx >= 0 && bx < M && map[by][bx] != 1) {
            clean(by, bx, dir);
        }
    }
}
