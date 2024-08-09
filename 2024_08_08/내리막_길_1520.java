package backJoon;

import java.io.*;
import java.util.*;

public class 내리막_길_1520 {
    private static int M, N;
    private static int[] dy = {-1, 1, 0, 0};
    private static int[] dx = {0, 0, -1, 1};
    private static int[][] map;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        M = Integer.parseInt(input[0]);
        N = Integer.parseInt(input[1]);

        map = new int[M][N];
        dp = new int[M][N];

        for (int i = 0; i < M; ++i) {
            input = br.readLine().split(" ");
            for (int j = 0; j < N; ++j) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }
        
        for (int i = 0; i < M; ++i) {
            Arrays.fill(dp[i], -1);
        }


        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(new StringBuilder().append(getCount(0, 0)).toString());
        bw.close();
        br.close();
    }

    /**
     * 처음에는 visited 배열을 사용해서, 백트래킹을 사용하여 풀었다. 즉 모든 경우의 수를 다 순회하는 방법으로 짰다.
     * 하지만 시간초과가 났다. 이 방식은 맵이 커지면 생기는 당연한 결과였다.
     * 그래서  dp 배열을 선언하여, 각 좌표로 이동할 수 있는 경로를 체크하고, M, N에 도달했을 때 1을 반환하게 하였다.
     * 그리고 각 dp[y][x] 에 저장하였다.
     *
     * @param y
     * @param x
     * @return
     */
    private static int getCount(int y, int x) {
        if (y == M - 1 && x == N - 1) {
            return 1;
        }

        if (dp[y][x] != -1) {
            return dp[y][x];
        }

        dp[y][x] = 0;
        for (int i = 0; i < 4; ++i) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if (ny < 0 || ny >= M || nx < 0 || nx >= N) {
                continue;
            }
            if (map[y][x] > map[ny][nx] ) {
                dp[y][x] += getCount(ny, nx);
            }
        }
        return dp[y][x];
    }
}
