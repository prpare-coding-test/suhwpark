package backJoon;

import java.util.*;
import java.io.*;

public class 최소_비용_구하기_2_11779 {
    private static int n, m;
    private static int s, e;
    private static List<Node>[] graph;
    private static int[] dist;
    private static int[] route;
    private static class Node {
        int end;
        int cost;

        public Node(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        graph = new List[n + 1];

        for (int i = 1; i <= n; ++i) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; ++i) {
            String[] input = br.readLine().split(" ");
            int start = Integer.parseInt(input[0]);
            int end = Integer.parseInt(input[1]);
            int cost = Integer.parseInt(input[2]);

            graph[start].add(new Node(end, cost));
        }
        String[] input = br.readLine().split(" ");
        s = Integer.parseInt(input[0]);
        e = Integer.parseInt(input[1]);
        dist = new int[n + 1];
        route = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        getMinDistance();

        StringBuilder sb = new StringBuilder();
        sb.append(dist[e]).append('\n');

        //종점에서 부터 시작 지점까지 역추적 하여 구한다.
        List<Integer> city = new ArrayList<>();
        int cur = e;
        while (cur != 0) {
            city.add(cur);
            cur = route[cur];
        }

        sb.append(city.size()).append('\n');
        for (int i = city.size() - 1; i >= 0; --i) {
            sb.append(city.get(i)).append(" ");
        }

        sb.append('\n');
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.close();
        br.close();

    }

    /**
     * 마을을 그래프로 생각하여, dist에 저장된 값과 비교하여 작을때만 pq에 추가한다
     * 루트 배열에 방문한 위치를 저장한다 (나중에 역추적하기 위해)
     */
    private static void getMinDistance() {

        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        dist[s] = 0;
        pq.add(new Node(s, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int now = cur.end;

            if (dist[now] < cur.cost) {
                continue;
            }
            for (Node n : graph[now]) {
                if (dist[n.end] > dist[now] + n.cost) {
                    dist[n.end] = dist[now] + n.cost;
                    pq.add(new Node(n.end, dist[n.end]));
                    route[n.end] = now;
                }
            }
        }
    }
}
