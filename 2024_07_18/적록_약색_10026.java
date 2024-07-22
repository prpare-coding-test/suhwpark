package backJoon;

import java.util.*;
import java.io.*;

public class 적록_약색_10026 {
    private static int N;
    private static char[][] normalMap;
    private static char[][] abnormalMap;
    private static boolean[][] normalVisited;
    private static boolean[][] abnormalVisited;
    private static int[] dy = {1, -1, 0, 0};
    private static int[] dx = {0, 0, 1, -1};

    private static class Pos {
        int y;
        int x;

        Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }

    }

    private static int normalArea;
    private static int abnormalArea;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        normalMap = new char[N][N];
        abnormalMap = new char[N][N];

        for (int i = 0; i < N; ++i) {
            char[] color = br.readLine().toCharArray();
            for (int j = 0; j < N; ++j) {
                normalMap[i][j] = color[j];
                if (color[j] == 'G') {
                    abnormalMap[i][j] = 'R';
                } else {
                    abnormalMap[i][j] = color[j];
                }
            }
        }
        normalVisited = new boolean[N][N];
        abnormalVisited = new boolean[N][N];

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                if (!normalVisited[i][j]) {
                    bfs(i, j, normalMap[i][j], true);
                    normalArea += 1;
                }
                if (!abnormalVisited[i][j]) {
                    bfs(i, j, abnormalMap[i][j], false);
                    abnormalArea += 1;
                }
            }
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(new StringBuilder().append(normalArea).append(' ').append(abnormalArea).append('\n').toString());
        bw.close();
        br.close();
    }

    /**
     * 색약과 그렇지 않은 것을 따로 구해준다.
     * bfs를 통해서 같은 색갈의 영역을 방문해준다.
     * 
     * @param y 들어온 색갈의 y점
     * @param x 들어온 색갈의 x점
     * @param c 들어온 색갈
     * @param isNormal 정상적인 맵인지, 색약인지
     */
    private static void bfs(int y, int x, char c, boolean isNormal) {
        Queue<Pos> q = new LinkedList<>();
        q.add(new Pos(y, x));

        if (!isNormal) {
            abnormalVisited[y][x] = true;
        } else {
            normalVisited[y][x] = true;
        }

        while (!q.isEmpty()) {
            Pos p = q.poll();

            for (int i = 0; i < 4; ++i) {
                int ny = p.y + dy[i];
                int nx = p.x + dx[i];

                if (!isNormal) {
                    if (!isInRange(ny, nx) || abnormalVisited[ny][nx] || abnormalMap[ny][nx] != c) {
                        continue;
                    }
                    abnormalVisited[ny][nx] = true;
                    q.add(new Pos(ny, nx));
                } else {
                    if (!isInRange(ny, nx) || normalVisited[ny][nx] || normalMap[ny][nx] != c) {
                        continue;
                    }
                    normalVisited[ny][nx] = true;
                    q.add(new Pos(ny, nx));
                }

            }
        }
    }

    private static boolean isInRange(int y, int x) {
        return (y >= 0 && y < N) && (x >= 0 && x < N);
    }
}
