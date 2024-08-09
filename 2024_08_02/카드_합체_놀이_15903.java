package backJoon;

import java.io.*;
import java.util.*;

public class 카드_합체_놀이_15903 {
    private static int n, m;
    private static PriorityQueue<Long> pq;
    private static StringBuilder sb;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        pq = new PriorityQueue<>();

        input = br.readLine().split(" ");
        for (int i = 0; i < n; ++i) {
            pq.add(Long.parseLong(input[i]));
        }

        combine();

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    /**
     * 우선 순위(오름차순 기준) 큐에 담긴 요소들을 2개 poll 한 후, 합친 결과를 다시 pq에 넣는다
     * 이 과정에서 m만큼 합칠수 있다는 조건에 맞추어 cnt >= m 인 경우 중단한다.
     * 오름차순으로 q에 담기기에, 최솟값을 구할 수 있다.
     */
    private static void combine() {
        sb = new StringBuilder();
        int cnt = 0;
        while (pq.size() > 1 && cnt < m) {
            long c = pq.poll() + pq.poll();
            pq.add(c);
            pq.add(c);
            cnt++;
        }
        long ans = 0L;
        while (!pq.isEmpty()) {
            ans += pq.poll();
        }
        sb.append(ans).append('\n');
    }
}
