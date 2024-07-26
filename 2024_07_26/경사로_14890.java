package backJoon;

import java.io.*;
import java.util.*;

public class 경사로_14890 {
    private static int N, L;
    private static int[][] map;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; ++i) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < N; ++j) {
                map[i][j] = Integer.parseInt(line[j]);
            }
        }

        int answer = 0;
        for(int i = 0; i < N; ++i) {
            if (isAvailableRow(i)) {
                answer += 1;
            }
            if (isAvailableCol(i)) {
                answer += 1;
            }
        }
        System.out.println(answer);
    }

    /**
     * row 기준으로 경사로를 놓는다
     * visited 배열을 통해, 경사로를 놓을 수 있는지 확인한다. 만약 놓아져 있다면 false
     * 현재 높이와 내 앞의 높이의 차이를 구하고 오르막인지, 내리막인지 확인한다
     *
     * 1. 오르막길
     * - 내 위치에서 L만큼 뒤로(--) 같은 높이의 길이 있어야한다
     * - 인덱스 범위 내에 있어야한다
     * - 경사로가 놓여져 있으면 안된다
     * 2. 내리막길
     * - 내 위치에서 L만큼 앞으로(++) 같은 높이의 길이 있어아야한다
     * - 인덱스 범위 내에 있어야한다
     * - 경사로가 놓여져 있으면 안된다
     * 만족할 경우 경사로를 놓는다
     * 
     * @param index
     * @return
     */
    private static boolean isAvailableRow(int index) {
        visited = new boolean[N];

        for (int i = 0; i < N - 1; ++i) {
            int diff = map[index][i] - map[index][i + 1];

            if (diff > 1 || diff < -1) {
                return false;
            }
            if (diff == -1) {
                for (int j = 0; j < L; ++j) {
                    if (i - j < 0 || visited[i - j]) {
                        return false;
                    }
                    if (map[index][i] != map[index][i - j]) {
                        return false;
                    }
                    visited[i - j] = true;
                }
            }
            if (diff == 1) {
                for (int j = 1; j <= L; ++j) {
                    if (i + j >= N || visited[i + j]) {
                        return false;
                    }
                    if (map[index][i] - 1 != map[index][i + j]) {
                        return false;
                    }
                    visited[i + j] = true;
                }
            }
        }
        return true;
    }

    private static boolean isAvailableCol(int index) {
        visited = new boolean[N];

        for (int i = 0; i < N - 1; ++i) {
            int diff = map[i][index] - map[i + 1][index];

            if (diff > 1 || diff < -1) {
                return false;
            }
            if (diff == -1) {
                for (int j = 0; j < L; ++j) {
                    if (i - j < 0 || visited[i - j]) {
                        return false;
                    }
                    if (map[i][index] != map[i - j][index]) {
                        return false;
                    }
                    visited[i - j] = true;
                }
            }
            if (diff == 1) {
                for (int j = 1; j <= L; ++j) {
                    if (i + j >= N || visited[i + j]) {
                        return false;
                    }
                    if (map[i][index] - 1 != map[i + j][index]) {
                        return false;
                    }
                    visited[i + j] = true;
                }
            }
        }
        return true;
    }

}
