package backJoon;

import java.io.*;
import java.util.*;

public class AC_5430 {
    private static int K;
    private static ArrayDeque<Integer> dq;
    private static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        K = Integer.parseInt(br.readLine());

        sb = new StringBuilder();
        for (int i = 0; i < K; ++i) {
            dq = new ArrayDeque<>();
            String cmd = br.readLine();
            int size = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine(), "[],");

            for (int j = 0; j < size; ++j) {
                dq.add(Integer.parseInt(st.nextToken()));
            }

            execute(cmd);
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    /**
     * cmd를 실행할 때, R이 나오게되면, 덱의 특성상 pollLast()를 사용해주면 뒤에서 부터 뺄 수 있기에, 덱을 역 전환 하지 않고 사용할 수 있다
     * 위의 개념을 생각지 못하게 풀어서, 처음에는 뒤집어 주다가 시간 초과가 났다
     *
     * D가 나오면, poll을 하는데, 만약 요소가 없다면 error를 밷는다
     * 모든 cmd를 검사하고 남은 덱의 요소들을 toString() sb에 담아 준다 (역방향이면 pollLast, 정방향이면 pollFirst)
     * @param cmd
     */
    private static void execute(String cmd) {
        boolean isReverse = false;
        for (char c : cmd.toCharArray()) {
            if (c == 'R') {
                isReverse = !isReverse;
                continue;
            }

            if (isReverse) {
                if (dq.pollLast() == null) {
                    sb.append("error\n");
                    return;
                }
            } else {
                if (dq.pollFirst() == null) {
                    sb.append("error\n");
                    return;
                }
            }
        }

        sb.append('[');
        if (dq.size() > 0) {
            if (isReverse) {
                sb.append(dq.pollLast());

                while (!dq.isEmpty()) {
                    sb.append(',').append(dq.pollLast());
                }
            } else {
                sb.append(dq.pollFirst());
                while (!dq.isEmpty()) {
                    sb.append(',').append(dq.pollFirst());
                }
            }
        }
        sb.append(']').append('\n');
    }
}
