package backJoon;

import java.io.*;
import java.util.*;
public class 최단경로_1753 {
    private static int V, E, K;
    private static List<Pos>[] graph;
    private static PriorityQueue<Pos> pq;
    private static int[] dist;
    private static class Pos {
        int v;
        int cost;

        public Pos(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        V = Integer.parseInt(input[0]);
        E = Integer.parseInt(input[1]);
        K = Integer.parseInt(br.readLine());
        //간선 그래프 형성
        graph = new List[V + 1];

        for (int i = 1; i <= V; ++i) {
            graph[i] = new ArrayList<>();
        }
        //각 시작 지점에 연결 지점을 연결하고 거리 비용도 넣어준다
        for (int i = 0; i < E; ++i) {
            input = br.readLine().split(" ");
            int u = Integer.parseInt(input[0]);
            int v = Integer.parseInt(input[1]);
            int w = Integer.parseInt(input[2]);

            graph[u].add(new Pos(v, w));
        }

        //각 정점에서 간선으로 가는 최소 값들을 저장하기 위한 것
        dist = new int[V + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        //최솟값을 구하러 간다
        getMinDistance();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= V; ++i) {
            if (dist[i] == Integer.MAX_VALUE) {
                sb.append("INF").append('\n');
                continue;
            }
            sb.append(dist[i]).append('\n');
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    /**
     * 정점에서 간선으로 가기 위한 최솟값을 구하는 거기 때문에
     * pq의 기준을 cost로 잡고 가장 비용이 적게 드는 것부터 poll()한다
     * 방문한 간선이 있다면, 방문하지 않는다 -> 중복으로 체크하지 않기 위해 -> 왜냐면 꺼낸 값이 가장 최소기 때문
     * 시작 지점을  pq에 넣어준다
     *
     * pq가 빌때까지, 방문하지 않은 간선일 경우, 그 간선을 정점으로 연결된 간선들을 순회하면서, 현재의 값과 cost 값이 Integer.MAX_VALUE보다 작다면
     * 최솟값을 갱신! -> 다시 큐에 넣어 그 간선과 연결된 간선을 확인한다.
     */
    private static void getMinDistance() {
        pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        boolean[] check = new boolean[V + 1];
        pq.add(new Pos(K, 0));
        dist[K] = 0;

        while (!pq.isEmpty()) {
            Pos pos = pq.poll();
            int cur = pos.v;

            if (check[cur]) {
                continue;
            }
            check[cur] = true;

            for (Pos p : graph[cur]) {
                if (dist[p.v] > dist[cur] + p.cost) {
                    dist[p.v] = dist[cur] + p.cost;
                    pq.add(new Pos(p.v, dist[p.v]));
                }
            }
        }
    }
}
