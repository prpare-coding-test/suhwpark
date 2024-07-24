package backJoon;

import java.io.*;
import java.util.*;

public class 회전하는_큐_1021 {
    private static int N, M;
    private static List<Integer> values;
    private static LinkedList<Integer> dq = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        for (int i = 1; i <= N; ++i) {
            dq.add(i);
        }
        values = new ArrayList<>();
        input = br.readLine().split(" ");
        for (int i = 0; i < M; ++i) {
            values.add(Integer.parseInt(input[i]));
        }

        int answer = 0;
        /**
         * 중간 인덱스와 찾아야할 값의 인덱스를 비교한 후,
         * 중앙 보다 타겟 인덱스가 작을 경우 타겟 인덱스까지 이동
         * 전체 크기 - 타켓 인덱스 만큼 이동
         * 그리고 poll();
         *
         * indexOf()을 사용하기 위해, LinkedList 사용
         */
        for (int v : values) {
            int half;
            int target = dq.indexOf(v);
            if (dq.size() % 2 == 0) {
                half = dq.size() / 2 - 1;
            } else {
                half = dq.size() / 2;
            }

            if (target <= half) {
                for (int i = 0; i < target; ++i) {
                    int temp = dq.pollFirst();
                    dq.addLast(temp);
                    answer += 1;
                }
            } else {
                for (int i = 0; i < dq.size() - target; ++i) {
                    int temp = dq.pollLast();
                    dq.addFirst(temp);
                    answer += 1;
                }
            }
            dq.pollFirst();
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(new StringBuilder().append(answer).toString());
        bw.close();
        br.close();
    }
}