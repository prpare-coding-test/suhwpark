import java.util.*;
import java.io.*;

public class 미세먼지_안녕_17144 {
    private static int R, C, T;
    private static int[][] map;
    private static int[] dy = {-1, 1, 0, 0};
    private static int[] dx = {0, 0, -1, 1};
    private static int machine = -1;
    private static class Pos {
        int y;
        int x;
        int v;
        Pos(int y, int x, int v) {
            this.y = y;
            this.x = x;
            this.v = v;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        R = Integer.parseInt(input[0]);
        C = Integer.parseInt(input[1]);
        T = Integer.parseInt(input[2]);

        map = new int[R][C];

        for (int i = 0; i < R; ++i) {
            input = br.readLine().split(" ");
            for (int j = 0; j < C; ++j) {
                map[i][j] = Integer.parseInt(input[j]);
                if (map[i][j] == -1 && machine == -1) {
                    machine = i;
                }
            }
        }

        for (int i = 0; i < T; ++i) {
            spread();
            clean();
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        sb.append(Arrays.stream(map)
                        .flatMapToInt(Arrays::stream)
                        .filter(m -> m > 0)
                        .sum()
                )
                .append('\n');

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void spread() {
        Queue<Pos> q = new LinkedList<>();

        for (int i = 0; i < R; ++i) {
            for (int j = 0; j < C; ++j) {
                if (map[i][j] > 4) {
                    q.add(new Pos(i ,j, map[i][j]));
                }
            }
        }

        while(!q.isEmpty()) {
            Pos p = q.poll();

            int spreadValue = p.v / 5;
            int cnt = 0;
            for (int i = 0; i < 4; ++i) {
                int ny = p.y + dy[i];
                int nx = p.x + dx[i];

                if (ny < 0 || ny >= R || nx < 0 || nx >= C || map[ny][nx] == -1) {
                    continue;
                }

                map[ny][nx] += spreadValue;
                cnt++;
            }
            map[p.y][p.x] -= spreadValue * cnt;
        }
    }
    private static void clean() {
        int topMachine = machine;
        int downMachine = machine + 1;

        //위쪽 기계
        //아래로 당기기
        for (int i = topMachine - 1; i > 0; --i) {
            map[i][0] = map[i - 1][0];
        }

        //왼쪽으로 당기기
        for (int i = 0; i < C - 1; ++i) {
            map[0][i] = map[0][i + 1];
        }

        //위로 당기기
        for (int i = 0; i < topMachine; ++i) {
            map[i][C - 1] = map[i + 1][C - 1];
        }

        //오른쪽으로 당기기
        for (int i = C - 1; i > 1; --i) {
            map[topMachine][i] = map[topMachine][i - 1];
        }

        map[topMachine][1] = 0;

        //아래쪽 기계
        //위로 당기기
        for (int i = downMachine + 1; i < R - 1; ++i) {
            map[i][0] = map[i + 1][0];
        }

        //왼쪽으로 당기기
        for (int i = 0; i < C - 1; ++i) {
            map[R - 1][i] = map[R - 1][i + 1];
        }

        //아래로 당기기
        for (int i = R - 1; i > downMachine; --i) {
            map[i][C - 1] = map[i - 1][C - 1];
        }

        //오른쪽으로 당기기
        for (int i = C - 1; i > 1; --i) {
            map[downMachine][i] = map[downMachine][i - 1];
        }
        map[downMachine][1] = 0;
    }
}
