package backJoon;

import java.io.*;
import java.util.*;
public class 카드_정렬하기_1715 {
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());


        //제일 작은 요소를 poll하는 큐
        PriorityQueue<Long> q = new PriorityQueue<>();

        for (int i = 0; i < N; ++i) {
            q.add(Long.parseLong(br.readLine()));
        }

        long answer = 0;
        // 요소가 한개 남을때 까지 계속 값을 더해준다.
        while(q.size() > 1) {
            long a = q.poll();
            long b = q.poll();
            answer += a + b;
            q.add(a + b);
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(new StringBuilder().append(answer).append('\n').toString());
        bw.close();
        br.close();
    }
}
