import java.util.*;
import java.io.*;

public class 사다리_조작_15684 {
    private static int N, M, H;
    private static int[][] ladder;
    private static boolean isFinished = false;
    private static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        H = Integer.parseInt(input[2]);

        ladder = new int[H + 1][N + 1];

        for (int i = 0; i < M; ++i) {
            input = br.readLine().split(" ");
            int y = Integer.parseInt(input[0]);
            int x = Integer.parseInt(input[1]);
            ladder[y][x] = 1;
            ladder[y][x + 1] = 2;
        }

        for (int i = 0; i <= 3; ++i) {
            ans = i;
            dfs(1, 0);
            if (isFinished) {
                break;
            }
        }

        StringBuilder sb = new StringBuilder();
        if (isFinished) {
            sb.append(ans).append('\n');
        } else {
            sb.append(-1).append('\n');
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void dfs(int y, int cnt) {
        if (isFinished) {
            return;
        }
        if (ans == cnt) {
            if (checkLadder()) {
                isFinished = true;
            }
            return;
        }

        for (int i = y; i < H + 1; ++i) {
            for (int j = 1; j < N; ++j) {
                if (ladder[i][j] == 0 && ladder[i][j + 1] == 0) {
                    ladder[i][j] = 1;
                    ladder[i][j + 1] = 2;
                    dfs(i, cnt + 1);
                    ladder[i][j] = 0;
                    ladder[i][j + 1] = 0;
                }
            }
        }
    }

    private static boolean checkLadder() {
        for (int i = 1; i <= N; ++i) {
            int y = 1;
            int x = i;
            for (int j = 0; j < H; ++j) {
                if (ladder[y][x] == 1) {
                    x++;
                } else if (ladder[y][x] == 2) {
                    x--;
                }
                y++;
            }
            if (x != i) {
                return false;
            }
        }
        return true;
    }
}
