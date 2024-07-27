package backJoon;

import java.io.*;
import java.util.*;
public class 덱_10866 {
    private static int N;
    private static ArrayDeque<Integer> dq;
    private static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dq = new ArrayDeque<>();
        sb = new StringBuilder();

        for (int i = 0; i < N; ++i) {
            execute(br.readLine().split(" "));
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    /**
     * deque에 구현되어있는 것을 사용해서 구현!
     * int[] 배열로 한다면
     * 오른쪽 방향으로 이동시
     * rear = (rear + 1) % array.length;
     * front = (front + 1) % array.length;
     *
     * 왼쪽 방향을 이동시
     * rear = (rear - 1 + array.length) % array.length;
     * front = (front - 1 + array.length) % array.length;
     *
     * 로 구현하면된다!
     * 전에 구현해봤기에~ 자바 deque class를 안써봐서 써봤습니당
     * @param input
     */
    private static void execute(String[] input) {
        String command = input[0];

        if (command.equals("push_back")) {
            dq.addLast(Integer.parseInt(input[1]));
            return;
        }
        if (command.equals("push_front")) {
            dq.addFirst(Integer.parseInt(input[1]));
            return;
        }
        if (command.equals("pop_front")) {
            if (dq.isEmpty()) {
                sb.append(-1).append('\n');
                return;
            }
            sb.append(dq.pollFirst()).append('\n');
            return;
        }
        if (command.equals("pop_back")) {
            if (dq.isEmpty()) {
                sb.append(-1).append('\n');
                return;
            }
            sb.append(dq.pollLast()).append('\n');
            return;
        }
        if (command.equals("size")) {
            sb.append(dq.size()).append('\n');
            return;
        }
        if (command.equals("empty")) {
            if(dq.isEmpty()) {
                sb.append(1).append('\n');
                return;
            }
            sb.append(0).append('\n');
            return;
        }
        if (command.equals("front")) {
            if (dq.isEmpty()) {
                sb.append(-1).append('\n');
                return;
            }
            sb.append(dq.getFirst()).append('\n');
            return;
        }
        if (command.equals("back")) {
            if (dq.isEmpty()) {
                sb.append(-1).append('\n');
                return;
            }
            sb.append(dq.getLast()).append('\n');
        }
    }
}
