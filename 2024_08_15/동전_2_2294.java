import java.util.*;
import java.io.*;

public class 동전_2_2294 {
    private static int n, k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        n = Integer.parseInt(input[0]);
        k = Integer.parseInt(input[1]);

        int[] coins = new int[n];
        for (int i = 0; i < n; ++i) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[k + 1];
        Arrays.fill(dp, 100001);

        /**
         * 1 5 12로 15를 만드는 방법
         * 사용한 동전의 갯수
         *    1 2 3 4 5 6 7 8 9 10 11 12 13 14 15
         *  1 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15
         *  5 1 2 3 4 1 2 3 4 5 2  3  4  5  6  3
         * 12 1 2 3 4 1 2 3 4 5 2  3  1  2  3  3
         * 위와 같이 만들 수 있다.
         *
         * 즉, 점화식은 dp[c] = Math.min(dp[c], dp[c - arr[i]] + 1)
         */

        dp[0] = 0;
        for (int c : coins) {
            for (int j = c; j < k + 1; ++j) {
                dp[j] = Math.min(dp[j], dp[j - c] + 1);
            }
        }

        StringBuilder sb = new StringBuilder();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        if (dp[k] == 100001) {
             dp[k] = -1;
        }
        bw.write(sb.append(dp[k]).append('\n').toString());
        bw.close();
        br.close();
    }
}
