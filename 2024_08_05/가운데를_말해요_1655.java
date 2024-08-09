package backJoon;

import java.util.*;
import java.io.*;

public class 가운데를_말해요_1655 {
    private static int N;
    private static PriorityQueue<Integer> minQ;
    private static PriorityQueue<Integer> maxQ;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        minQ = new PriorityQueue<>();
        maxQ = new PriorityQueue<>((o1, o2) -> o2 -o1);
        StringBuilder sb = new StringBuilder();

        /**
         * 가운데를 말하는게... 1 2 5 가 들어오면 2
         * 근데 만약 1 2 5 -99 가 들어오게 되면... -99 1 2 5 의 중간 값은 1
         * 처음에는 list에 요소가 추가 될때 마다 정렬해서, 판단했다. -> 무조건 시간 초과 N * N...
         * 그래서 우선순위 큐를 두개 사용해서, 매번 중간 수를 갱신해 줌 -> 요소가 짝수일 때 고려 하지 않아도 됌
         * 요소를 추가할 때마다, 각 큐의 최대 최소 값을 swap해준다
         *
         * 예시!! (두 힙의 크기를 비교하여 사이즈 같으면 최대 힙에 넣어준다, 최대힙의 값을 peek해서 출력하기 위해)
         * value 1, 5, 2, 10, -99, 7, 5
         * 1 : [1] [] -> 1
         * 사이즈가 같기 때문에 최대힙에 넣어준다.
         * 2 : [1] [5] -> 1
         * 사이즈가 다르기 때문에 최소힙에 넣어준다.
         * 3 : [1, 2] [5] -> 2
         * 사이즈가 같기 때문에 최대힙에 넣어준다.
         * 4: [1, 2] [5, 10] -> 2
         * 사이즈가 다르기 때문에 최소힙에 넣어준다.
         * 5 : [-99, 1, 2] [5, 10] -> 2
         * 6 : [-99, 1, 2] [5, 7, 10] -> 2
         * 7 : [-99, 1, 2, 5] [5, 7, 10] -> 5
         *
         * 만약 maxQ.peek() > minQ.peek() 두 값을 swap해 주면서 중간 값을 갱신하게 된다.
         */
        for (int i = 0; i < N; ++i) {
            int num = Integer.parseInt(br.readLine());

            if (maxQ.size() == minQ.size()) {
                maxQ.add(num);
                if (!minQ.isEmpty() && minQ.peek() < maxQ.peek()) {
                    minQ.add(maxQ.poll());
                    maxQ.add(minQ.poll());
                }
            } else {
                minQ.add(num);
                if (maxQ.peek() > minQ.peek()) {
                    minQ.add(maxQ.poll());
                    maxQ.add(minQ.poll());
                }
            }
            sb.append(maxQ.peek()).append('\n');
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.append('\n').toString());
        bw.close();
        br.close();
    }
}
