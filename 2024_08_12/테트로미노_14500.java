package backJoon;

import java.io.*;
import java.util.*;
public class 테트로미노_14500 {
    private static int N, M;
    private static int[][] map;
    private static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        map = new int[N][M];

        for (int i = 0; i < N; ++i) {
            input = br.readLine().split(" ");
            for (int j = 0; j < M; ++j) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }

        solve();
        System.out.println(max);
    }

    //각 모양에 대한 dy, dx값을 구해서 map을 순회하면서 각 모양을 대입하면서 모양이 map 안에 있는지 확인한 후 있다면, 각 모양의 합을 구한다.
    // max 갱신
    private static void solve() {
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                getOne(i, j);
                getTwo(i, j);
                getThree(i, j);
                getFour(i, j);
                getFive(i, j);
            }
        }
    }

    private static void getFive(int y, int x) {
        // ㅜ 모양
        sum(y, x, new int[]{0, 0, 0, 1}, new int[]{0, 1, 2, 1});
        // ㅓ 오양
        sum(y, x, new int[]{0, 1, 2, 1}, new int[]{0, 0, 0, -1});
        // ㅗ 모양
        sum(y, x, new int[]{0, 1, 1, 1}, new int[]{0, 0, 1, -1});
        // ㅏ 모양
        sum(y, x, new int[]{0, 1, 2, 1}, new int[]{0, 0, 0, 1});
    }

    //뱀 모양일 경우
    private static void getFour(int y, int x) {
        //뱀 모양일 경우
        sum(y, x, new int[]{0, 1, 1, 2}, new int[]{0, 0, 1, 1});
        // 시계방향 90도 회전
        sum(y, x, new int[]{0, 0, 1, 1}, new int[]{0, 1, 0, -1});
        //뱀 비대칭
        sum(y, x, new int[]{0, 1, 1, 2}, new int[]{0, 0, -1, -1});
        // 시계방향 90도 회전
        sum(y, x, new int[]{0, 0, 1, 1}, new int[]{0, -1, 0, 1});

    }

    //ㄴ자 모양 경우
    private static void getThree(int y, int x) {

        //ㄴ 자 모양일 경우
        sum(y, x, new int[]{0, 1, 2, 2}, new int[]{0, 0, 0, 1});
        //위 모양을 시계 방향으로 90도 돌린 경우
        sum(y, x, new int[]{0, 0, 0, 1}, new int[]{0, 1, 2, 0});
        //위 모양을 시계 방향을 90도 돌린 경우
        sum(y, x, new int[]{0, 0, 1, 2}, new int[]{0, 1, 1, 1});
        //위 모양을 시계 방향을 90도 돌린 경우
        sum(y, x, new int[]{0, 1, 1, 1}, new int[]{0, 0, -1, -2});
        //ㄴ자 대칭 모양
        sum(y, x, new int[]{0, 1, 2, 2}, new int[]{0, 0, 0, -1});
        //위 모양을 시계 방향을 90도 돌린 경우
        sum(y, x, new int[]{0, 1, 1, 1}, new int[]{0, 0, 1, 2});
        //위 모양을 시계 방향을 90도 돌린 경우
        sum(y, x, new int[]{0, 1, 2, 0}, new int[]{0, 0, 0, 1});
        //위 모양을 시계 방향을 90도 돌린 경우
        sum(y, x, new int[]{0, 1, 0, 0}, new int[]{0, 0, -1, -2});
    }

    //정사각형 경우
    private static void getTwo(int y, int x) {
        int[] dy = {0, 0, 1, 1};
        int[] dx = {0, 1, 0, 1};

        sum(y, x, dy, dx);
    }

    //긴 막대기 경우
    private static void getOne(int y, int x) {
        int[] dy = {0, 0, 0, 0};
        int[] dx = {0, 1, 2, 3};

        sum(y, x, dy, dx);
        sum(y, x, dx, dy);
    }

    private static void sum(int y, int x, int[] dy, int[] dx) {
        int sum = 0;
        for (int i = 0; i < 4; ++i) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if (ny < 0 || ny >= N || nx < 0 || nx >= M) {
                return;
            }
            sum += map[ny][nx];
        }
        max = Math.max(sum, max);
    }
}
