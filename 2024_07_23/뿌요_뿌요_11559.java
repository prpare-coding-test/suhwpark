package backJoon;

import java.io.*;
import java.util.*;

public class 뿌요_뿌요_11559 {
    private static char[][] map;
    private static boolean isPuyo;
    private static boolean[][] visited;
    private static int[] dy = {1, -1, 0, 0};
    private static int[] dx = {0, 0, 1, -1};
    private static class Pos {
        int y;
        int x;
        char c;
        public Pos(int y, int x, char c) {
            this.y = y;
            this.x = x;
            this.c = c;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new char[12][6];

        for (int i = 0; i < 12; ++i) {
            char[] input = br.readLine().toCharArray();
            for (int j = 0; j < 6; ++j) {
                map[i][j] = input[j];
            }
        }
        visited = new boolean[12][6];
        int answer = 0;

        //계속해서 연쇄 작용이 일어날때만, cnt해주기 위해 isPuyo가 false면 탐색 그만
        while (true) {
            isPuyo = false;

            search();
            changeMap();
            if (!isPuyo) {
                break;
            }
            answer += 1;
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(new StringBuilder().append(answer).toString());
        bw.close();
        br.close();
    }

    private static void search() {
        // 맵이 바뀌고 방문 요청은 초기화
        visited = new boolean[12][6];

        for (int i = 0; i < 12; ++i) {
            for (int j = 0; j < 6; ++j) {
                if (map[i][j] != '.' && !visited[i][j]) {
                    bfs(new Pos(i, j, map[i][j]));
                }
            }
        }
    }

    private static void bfs(Pos pos) {
        Queue<Pos> q = new LinkedList<>();
        q.add(pos);
        visited[pos.y][pos.x] = true;
        List<Pos> cnt = new ArrayList<>();
        cnt.add(pos);

        while (!q.isEmpty()) {
            Pos p = q.poll();

            for (int i = 0; i < 4; ++i) {
                int ny = p.y + dy[i];
                int nx = p.x + dx[i];

                if (ny < 0 || ny >= 12 || nx < 0 || nx >= 6 || visited[ny][nx]) {
                    continue;
                }
                if (map[ny][nx] == p.c) {
                    visited[ny][nx] = true;
                    q.add(new Pos(ny, nx, p.c));
                    cnt.add(new Pos(ny, nx, p.c));
                }
            }
        }

        // 터트리는 갯수 제한이 3개 이상임으로 3개가 되지 않는다면 터지지 않음
        if (cnt.size() > 3) {
            for (Pos p : cnt) {
                map[p.y][p.x] = '.';
            }
            isPuyo = true;
        }
    }

    private static void changeMap() {
        for (int i = 0; i < 6; ++i) {
            onFloor(i);
        }
    }
    // 인덱스 끝부터 '.'이 있다면 뿌요뿌요가 진행 됬기 때문에, 위에 있는 요소들은 내려주는 함수
    private static void onFloor(int x) {
        //q밖에 생각이 안났다.
        Queue<Pos> q = new LinkedList<>();

        for (int i = 11; i >= 0; --i) {
            if (map[i][x] != '.') {
                q.add(new Pos(i, x, map[i][x]));
                map[i][x] = '.';
            }
        }
        int idx = 11;
        while (!q.isEmpty()) {
            Pos p = q.poll();

            map[idx][p.x] = p.c;
            idx--;
        }
    }
}
