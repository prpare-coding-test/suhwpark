package backJoon;

import java.io.*;
import java.util.*;

public class 팰린드롬_10942 {
    private static int N, M;
    private static int[] arr;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N + 1];
        String[] input = br.readLine().split(" ");
        for (int i = 1; i <= N; ++i) {
            arr[i] = Integer.parseInt(input[i - 1]);
        }
        dp = new int[N + 1][N + 1];
        solve();
        M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; ++i) {
            input = br.readLine().split(" ");
            int s = Integer.parseInt(input[0]);
            int e = Integer.parseInt(input[1]);
            sb.append(dp[s][e]).append('\n');
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void solve() {
        // 길이가 1이면 모든 배열은 펠린드롬이다
        // 1 2 3 4 5 이런거 다...
        for (int i = 1; i <= N; ++i) {
            dp[i][i] = 1;
        }

        // 길이가 2일 때 각 요소가 같은 경우 펠린드롬이다
        // 1 1, 2 2, 3 3, 4 4,
        for (int i = 1; i < N; ++i) {
            if (arr[i] == arr[i + 1]) {
                dp[i][i + 1] =  1;
            }
        }

        //길이가 3이상 일 경우
        // 인덱스 시작과 끝 값이 같아야한다. 1, ..... ,1
        // 인덱스 시작 + 1 부터 인덱스 끝 - 1 이 펠린드롬이여야한다.
        // 1, 2, 1, 2, 1 이런 식의 배열
        for (int i = 2; i < N; ++i) {
            for (int j = 1; j  <= N - i; ++j) {
                if (arr[j] == arr[j + i] && dp[j + 1][j + i - 1] == 1) {
                    dp[j][j + i] = 1;
                }
            }
        }
    }
}
