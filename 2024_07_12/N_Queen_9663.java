package backJoon;

import java.io.*;
import java.util.*;

public class N_Queen_9663 {
    static int N;
    static int[] board;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        N = Integer.parseInt(st.nextToken());
        board = new int[N];
        answer = 0;

        dfs(0);

        bw.write(new StringBuilder().append(answer).toString());
        bw.close();
        bf.close();
    }

    /**
     * 체스판에서 퀸이 서로 공격할 수 없게 놓는 경우의 수를 구하는 문제
     * 즉, 모든 경우를 다 확인해야한다
     * 2차원 배열로 풀수도 있지만.  N * N 의 체스판이기 때문에, 일차원 배열로 board[i] 값을 x값, index를 y값으로 확인하여 퀸을 놓을 수 있는 지 없는지를 판단한다.
     * 체스판을 순회하면서 모든 자리에 퀸이 들어갈 수 있는지 확인하고, 있다면 true를 반환하고 다음 depth로 넘어간다.
     * 조합아는 경우의 수가 N과 같다면 모든 경우의 수를 확인한것이기에 퀸을 놓고 다음 경우의 수를 계산한다.
     * @param depth
     */
    private static void dfs(int depth) {
        if (depth == N) {
            answer += 1;
            return ;
        }

        for (int i = 0; i < N; i++) {
            board[depth] = i;
            if (isInBoard(depth)) {
                dfs(depth + 1);
            }
        }
    }

    private static boolean isInBoard(int col) {
        for (int i = 0; i < col; i++) {
            // queen이 대각선이나, 상 하 방면에 있다면 놓을 수 없다.
            if (board[i] == board[col] || col - i == Math.abs(board[col] - board[i])) {
                return false;
            }
        }
        return true;
    }
}
