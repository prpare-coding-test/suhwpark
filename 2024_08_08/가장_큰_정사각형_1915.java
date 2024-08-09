package backJoon;

import java.io.*;
import java.util.*;

public class 가장_큰_정사각형_1915 {
    private static int n, m;
    private static int map[][];
    private static int dp[][];
    private static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        map = new int[n][m];
        for (int i = 0; i < n; ++i) {
            input = br.readLine().split("");
            for (int j = 0; j < m; ++j) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }

        dp = new int[n][m];

        /**
         * map을 순회하면서, 각 위치에서 만들수 있는 가장 큰 정사각형의 한 변의 길이를 메모한다.
         * 0 1
         * 1 1
         * 의 현위치가 (1, 1)이라고 한다면, 현 위치의 대각선, 왼쪽, 위쪽의 가장 작은 값을 구하고 +1을 해주면 내 위치에서 구할 수 있는
         * 가장 큰 정사각형의 한변의 길이를 구할 수 있다
         * 1 1
         * 1 1
         * 의 board 라면 dp는
         * 1 1
         * 1 2
         * 이렇게 될것이다
         * 1 1 1
         * 1 1 1
         * 1 1 1
         * 의 board 라면
         * 1 1 1
         * 1 2 2
         * 1 2 3
         * 이렇게 되어 한변의 길이가 3인 정사각형을 구할 수 있다!
         */
        for (int i = 0 ; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                dp[i][j] = map[i][j];

                if (i != 0 && j != 0 && dp[i][j] != 0) {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                }

                max = Math.max(max, dp[i][j]);
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(new StringBuilder().append(max * max).append('\n').toString());
        bw.close();
        br.close();
    }
}
