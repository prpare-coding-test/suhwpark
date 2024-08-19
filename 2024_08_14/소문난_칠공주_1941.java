import java.util.*;
import java.io.*;

public class 소문난_칠공주_1941 {
    private static char[][] map;
    private static boolean[][] visited;
    private static int[] dy = {-1, 1, 0, 0};
    private static int[] dx = {0, 0, -1, 1};
    private static int ans;
    private static class Pos {
        int y;
        int x;
        Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new char[5][5];
        for (int i = 0; i < 5; ++i) {
            map[i] = br.readLine().toCharArray();
        }
        visited = new boolean[5][5];
        ans = 0;

        // dfs를 통해서 모든 7공주의 경우의 수를 구한다
        prince(0, 0);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        bw.write(new StringBuilder().append(ans).append('\n').toString());
        bw.close();
        br.close();
    }

    /**
     * 5 * 5 맵이기 때문에 25로 순회를한다
     * start index 기준으로 5로 나눈 값과 나머지 값으로 visited 를 체크한다 (즉 칠공주를 나열하는 것)
     * 7공주 경우의 수 설정하고 길이가 7이 된다면, bfs를 통해 이다연 파가 4명 이상인지 확인한다.
     * @param start 시작 인덱스
     * @param depth 7공주의 길이
     */
    private static void prince(int start, int depth) {
        if (depth == 7) {
            if (bfs()) {
                ans++;
                return;
            }
            return;
        }

        for (int i = start; i < 25; ++i) {
            int y = i / 5;
            int x = i % 5;
            visited[y][x] = true;
            //인덱스 기준으로 경우의 수를 찾기 위한 재귀
            prince(i + 1, depth + 1);
            // 백트래킹
            visited[y][x] = false;
        }
    }

    private static boolean bfs() {
        boolean[][] copyVisited = new boolean[5][5];

        for (int i = 0; i < 5; ++i) {
            System.arraycopy(visited[i], 0, copyVisited[i], 0, 5);
        }
        int y = 0;
        int x = 0;
        //visited 시작점을 찾는다!
        for (int i = 0; i < 5; ++i) {
            for (int j = 0; j < 5; ++j) {
                if (copyVisited[i][j]) {
                    y = i;
                    x = j;
                    break ;
                }
            }
        }

        Queue<Pos> q = new LinkedList<>();
        q.add(new Pos(y, x));

        int cnt = 0;
        int s = 0;

        while(!q.isEmpty()) {
            Pos p = q.poll();

            for (int i = 0; i < 4; ++i) {
                int ny = p.y + dy[i];
                int nx = p.x + dx[i];

                if (ny < 0 || ny >= 5 || nx < 0 || nx >= 5) {
                    continue;
                }

                // dfs로 정한 7공주의 경로만을 탐색한다.
                if (copyVisited[ny][nx]) {
                    // 이다연 파 검색
                    if (map[ny][nx] == 'S') {
                        s++;
                    }
                    cnt++;
                    q.add(new Pos(ny, nx));
                    copyVisited[ny][nx] = false;
                }
            }
        }

        // 길이가 7이고, 이다연 파가 4명이 넘는다면 true
        if (cnt == 7 && s > 3) {
            return true;
        }
        return false;
    }

}