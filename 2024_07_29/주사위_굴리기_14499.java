package backJoon;

import java.io.*;
import java.util.*;

public class 주사위_굴리기_14499 {
    private static int N, M, X, Y, K;
    private static int[][] map;
    private static StringBuilder sb;
    private static int[] dy = {0, 0, -1, 1};
    private static int[] dx = {1, -1, 0, 0};
    private static int[] dice;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; ++i) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < M; ++j) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }

        String[] test = br.readLine().split(" ");
        sb = new StringBuilder();
        dice = new int[7];

        for (int i = 0; i < K; ++i) {
            move(Integer.parseInt(test[i]));
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    /**
     * 각 면마다 움직이는 방향의 규칙을 알아내서 사용
     * top이 기준이라면
     * 동, 서 : top의 윗면과 아랫면은 고정 -> 나머지의 값들은 뱡향에 따라 한칸씩 이동
     * 남, 북 : top의 오른쪽과 왼쪽면은 고정 -> 나머지의 값들은 뱡향에 따라 한칸씩 이동
     * int 형 배열로 인덱스 별로
     *    1
     *  2 3 4
     *    5
     *    6
     * 으로 정해서 사용
     * 각각의 index를 구해 주사위가 map안에서 굴려진다면, 굴리기 실행
     *
     * 주사위를 굴린 후의 모습
     *  기본
     *    2
     *  4 1 3
     *    5
     *    6
     *
     *   동쪽
     *    2
     *  6 4 1
     *    5
     *    3
     *
     *    서
     *    2
     *  1 3 6
     *    5
     *    4
     *
     *    남
     *    6
     *  4 2 3
     *    1
     *    5
     *
     *    북
     *    1
     *  4 5 3
     *    6
     *    2
     * @param dir test의 방향
     */
    private static void move(int dir) {
        int y = Y + dy[dir - 1];
        int x = X + dx[dir - 1];

        if (x < 0 || x >= M || y < 0 || y >= N) {
            return;
        }
        rollTheDice(dir, y, x);
        Y = y;
        X = x;
    }

    private static void rollTheDice(int dir, int y, int x) {
        int tmp = dice[3];
        if (dir == 1) {
            dice[3] = dice[2];
            dice[2] = dice[6];
            dice[6] = dice[4];
            dice[4] = tmp;
        }
        if (dir == 2) {
            dice[3] = dice[4];
            dice[4] = dice[6];
            dice[6] = dice[2];
            dice[2] = tmp;
        }
        if (dir == 3) {
            dice[3] = dice[5];
            dice[5] = dice[6];
            dice[6] = dice[1];
            dice[1] = tmp;
        }
        if (dir == 4) {
            dice[3] = dice[1];
            dice[1] = dice[6];
            dice[6] = dice[5];
            dice[5] = tmp;
        }
        sb.append(dice[3]).append('\n');
        if (map[y][x] == 0) {
            map[y][x] = dice[6];
        } else {
            dice[6] = map[y][x];
            map[y][x] = 0;
        }

    }
}
