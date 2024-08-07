package backJoon;

import java.io.*;
import java.util.*;

public class 뱀_3190 {
    private static int N, K, L;
    private static int[][] map;
    // 동 남 서 북 으로 인덱스가 늘어나면 오른쪽으로 90 변경
    private static int[] dy = {0, 1, 0, -1};
    private static int[] dx = {1, 0, -1, 0};
    private static Map<Integer, String> info;
    private static List<Pos> snake;
    private static int time = 0;
    private static class Pos {
        int y;
        int x;

        public Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        K = Integer.parseInt(br.readLine());

        for (int i = 0; i < K; ++i) {
            String[] input = br.readLine().split(" ");
            int y = Integer.parseInt(input[0]) - 1;
            int x = Integer.parseInt(input[1]) - 1;
            map[y][x] = 1;
        }

        L = Integer.parseInt(br.readLine());
        info = new HashMap<>();

        for (int i = 0; i < L; ++i) {
            String[] input = br.readLine().split(" ");
            info.put(Integer.parseInt(input[0]), input[1]);
        }

        snake = new ArrayList<>();
        snake.add(new Pos(0, 0));
        game();

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(new StringBuilder().append(time).append('\n').toString());
        bw.close();
        br.close();
    }

    private static void game() {
        // 대가리 좌표
        int y = 0;
        int x = 0;

        int dir = 0;

        while(true) {
            time++;

            int ny = y + dy[dir];
            int nx = x + dx[dir];

            if (ny < 0 || ny >= N || nx < 0 || nx >= N) {
                return ;
            }

            for (int i = 0; i < snake.size(); ++i) {
                Pos p = snake.get(i);
                if (ny == p.y && nx == p.x) {
                    return;
                }
            }

            if (map[ny][nx] == 1) {
                map[ny][nx] = 0;
                snake.add(new Pos(ny, nx));
            } else {
                snake.add(new Pos(ny, nx));
                snake.remove(0);
            }

            if (info.containsKey(time)) {
                if (info.get(time).equals("D")) {
                    dir = (dir + 1) % 4;
                } else {
                    dir = (dir + 3) % 4;
                }
            }
            y = ny;
            x = nx;
        }
    }
}
