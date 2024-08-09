package backJoon;

import java.io.*;
import java.util.*;

public class 파일_합치기_3_13975 {
    private static int T, K;
    private static PriorityQueue<Long> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; ++i) {
            K = Integer.parseInt(br.readLine());
            pq = new PriorityQueue<>();
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < K; ++j) {
                pq.add(Long.parseLong(input[j]));
            }
            sb.append(getSize()).append('\n');
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    /**
     * 우선순위 큐에 요소들을 넣는다. (오름차순 기준)
     * q의 요소가 1개 남을 때 까지, q의 2개 요소를 poll해서 더한 후 다시 add 한다
     * 30 30 40 50 -> 40 50 60 -> 60 90 -> 150
     * 최소값을 찾기 가능
     * @return
     */
    private static long getSize() {
        long res = 0;
        while (pq.size() > 1) {
            long first = pq.poll();
            long second = pq.poll();
            res += first + second;
            pq.add(first + second);
        }
        return res;
    }
}
