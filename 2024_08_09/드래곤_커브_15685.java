import java.util.*;
import java.io.*;

public class 드래곤_커브_15685 {
    private static int N;
    private static boolean[][] map;
    private static int[] dy = {0, -1, 0, 1};
    private static int[] dx = {1, 0, -1, 0};
    public static void main(String[] args) throws IOException {
        /**
         * 0세대 : 0
         * 1세대 : 0 1
         * 2세대 : 0 1 2 1
         * 3세대 : 0 1 2 1 2 3 2 1
         *
         * 현재 세대 = 이전 세대 * 2
         * 또한 이전 세대의 방향들은 현재세대의 앞부분의 방향 + 뒤집은 이전세대 방향들 + 1이다
         * 즉, 2세대 (1 2 1 0) + 1 3세대의 2 3 2 1
         * 커브를 그리고, 사각형인 것을 확인한다.
         */

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new boolean[101][101];
        for (int i = 0; i < N; ++i) {
            String[] input = br.readLine().split(" ");
            int x = Integer.parseInt(input[0]);
            int y = Integer.parseInt(input[1]);
            int d = Integer.parseInt(input[2]);
            int g = Integer.parseInt(input[3]);
            draw(x, y, d, g);
        }

        int ans = 0;
        for (int i = 0; i < 100; ++i) {
            for (int j = 0; j < 100; ++j) {
                if (map[i][j] && map[i][j + 1] && map[i + 1][j] && map[i + 1][j + 1]) {
                    ans++;
                }
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(new StringBuilder().append(ans).append('\n').toString());
        bw.close();
        br.close();
    }

    private static void draw(int x, int y, int d, int g) {
        List<Integer> list = new ArrayList<>();

        list.add(d);

        for (int i = 1; i <= g; ++i) {
            for (int j = list.size() - 1; j >= 0; --j) {
                list.add((list.get(j) + 1) % 4);
            }
        }

        map[y][x] = true;
        for (Integer dir : list) {
            y += dy[dir];
            x += dx[dir];
            map[y][x] = true;
        }
    }

}