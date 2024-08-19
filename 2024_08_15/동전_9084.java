import java.util.*;
import java.io.*;

public class 동전_9084 {
    private static int T, N, M;
    private static int[] coins;
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; ++i) {
            N = Integer.parseInt(br.readLine());
            coins = new int[N];

            String[] input = br.readLine().split(" ");
            for (int j = 0; j < N; ++j) {
                coins[j] = Integer.parseInt(input[j]);
            }
            M = Integer.parseInt(br.readLine());
            dp = new int[M + 1];
            sb.append(getWay()).append('\n');
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    /**
     *      1   2   3   4   5   6   7   8   9   10
     * 1    1   1   1   1   1   1   1   1   1   1
     * 2    0   1   1   2   2   3   3   4   4   5
     * 5    0   0   0   0   1   1   2   2   3   4
     * sum  1   2   2   3   4   5   5   7   8   10
     * 위 처럼 동전을 사용할 수 있다.
     *
     * 즉, 점화식 dp[c] += dp[c - arr[i]];
     * @return
     */
    public static int getWay() {
        // 자기 자신을 채우는 경우가 있어야함으로
        dp[0] = 1;
        for (int i = 0; i < coins.length; ++i) {
            int c = coins[i];
            for (int j = c; j <= M; ++j) {
                dp[j] += dp[j - c];
            }
        }
        return dp[M];
    }
}
