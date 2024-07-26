package backJoon;

import java.io.*;
import java.util.*;

public class 절대값_힙_11286 {
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        // 절대값이 작은걸 먼저 넣는 비교식을 작성한다
        // 절대값이 작으면 절대값이 작은 값은 우선순위로 넣는다
        // 만약 같다면, 두 값중 작은 값을 넣는다.
        PriorityQueue<Integer> q = new PriorityQueue<>((o1, o2) -> {
            int abs1 = Math.abs(o1);
            int abs2 = Math.abs(o2);
            if (abs1 > abs2) {
                return abs1 - abs2;
            } else if (abs1 == abs2) {
                return o1 - o2;
            } else {
                return -1;
            }
        });
        StringBuilder sb = new StringBuilder();

        // 0이면 출력
        // 0이 아니면 q.add
        for (int i = 0; i < N; ++i) {
            int value = Integer.parseInt(br.readLine());
            if (value == 0) {
                if (!q.isEmpty()) {
                    sb.append(q.poll()).append('\n');
                } else {
                    sb.append(0).append('\n');
                }
                continue;
            }
            q.add(value);
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
