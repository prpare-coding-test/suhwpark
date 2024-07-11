package backJoon;

import java.util.*;
import java.io.*;

public class 치킨_배달_15686 {
    static int N, M;
    static int[][] map;
    static boolean[] visited;
    static List<Pos> store;
    static List<Pos> house;
    static int answer;
    static class Pos {
        int y;
        int x;

        Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        store = new ArrayList<>();
        house = new ArrayList<>();

        for (int i = 0 ; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                // 치킨 가게인 경우, list 포함
                if (map[i][j] == 2) {
                    store.add(new Pos(i, j));
                }
                // 집일 경우, list에 포함
                if (map[i][j] == 1) {
                    house.add(new Pos(i, j));
                }
            }
        }

        // M개의 치킨 가게를 방문하기 위한 용도
        // 브루트 포스로 M개를 방문해야할 경우의 수를 다 구하기 위한 배열
        visited = new boolean[store.size()];
        answer = Integer.MAX_VALUE;
        dfs(0, 0);
        bw.write(new StringBuilder().append(answer).toString());
        bw.close();
        bf.close();
    }

    /**
     * 치킨 가게중 M개를 선택하는 모든 경우의 수를 확인하여, 거리의 최솟값을 구하는 method
     * depth가 M이 되기 전까지, 모든 치킨 가게를 순회하면서 dfs를 실행한다. 이때, method를 들어가기 전 방문 배열에 N번째 치킨 가게를 방문했다고 true로 표시한다
     * 그리고 순회할 때 다음 순서에는 방문하지 않은 것으로 표시해야하기 때문에, false로 해준다. (모든 경우의 수를 확인하는 백트래킹)
     * M개를 선택한 경우일 경우, 집들을 순회하면서, 방문한 치킨 가게에 한정하여 거리를 구하고, 최솟값을 찾아 결과에 더해준다
     * 재귀를 빠져나올때, 정답과 비교해 가장 작은 값을 찾는다
     * @param storePos
     * @param depth
     */
    private static void dfs(int storePos, int depth) {
        if (depth == M) {
            int result = 0;
            for (int i = 0; i < house.size(); i++) {
                int min = Integer.MAX_VALUE;

                for (int j = 0; j < store.size(); j++) {
                    if (visited[j]) {
                        int distance = Math.abs(house.get(i).y - store.get(j).y) +
                                Math.abs(house.get(i).x - store.get(j).x);
                        min = Math.min(min, distance);
                    }
                }
                result += min;
            }
            answer = Math.min(answer, result);
            return ;
        }

        for (int i = storePos; i < store.size(); i++) {
            visited[i] = true;
            dfs(i + 1, depth + 1);
            visited[i] = false;
        }
    }
}
