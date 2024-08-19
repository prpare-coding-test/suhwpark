import java.util.*;
import java.io.*;

public class 회장뽑기_2660 {
    private static int N;
    private static int[][] friend;
    private final static int INF = 987654321;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        friend = new int[N + 1][N + 1];

        for (int i = 0; i <= N; ++i) {
            for (int j = 0; j <= N; ++j) {
                if (i != j) {
                    friend[i][j] = INF;
                }
            }
        }

        while(true) {
            String[] line = br.readLine().split(" ");

            int a = Integer.parseInt(line[0]);
            int b = Integer.parseInt(line[1]);

            if (a == -1 && b == -1) {
                break;
            }
            friend[a][b] = 1;
            friend[b][a] = 1;
        }

        for (int k = 1; k <= N; ++k) {
            for (int i = 1; i <= N; ++i) {
                for (int j = 1; j <= N; ++j) {
                    if (friend[i][j] > friend[i][k] + friend[k][j]) {
                        friend[i][j] = friend[i][k] + friend[k][j];
                    }
                }
            }
        }

        int max = INF;

        int[] score = new int[N + 1];

        for (int i = 1; i <= N; ++i) {
            int s = 0;
            for (int j = 1; j <= N; ++j) {
                if (friend[i][j] != 987654321) {
                    s = Math.max(s, friend[i][j]);
                }
            }

            score[i] = s;
            max = Math.min(max, s);
        }


        StringBuilder sb = new StringBuilder();
        StringBuilder names = new StringBuilder();
        sb.append(max).append(' ');

        int maxNum = 0;
        for (int i = 1; i <= N; ++i) {
            if (max == score[i]) {
                maxNum++;
                names.append(i).append(' ');
            }
        }

        sb.append(maxNum).append('\n').append(names);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
