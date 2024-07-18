package backJoon;

import java.util.*;
import java.io.*;

public class 로프_2217 {
    private static int N;
    private static List<Integer> rope = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            rope.add(Integer.parseInt(st.nextToken()));
        }

        // 내림 차순으로 정렬
        rope.sort((o1, o2) -> o2 - o1);

        //병렬적으로 들수 있는 무게중 최대값을 도출
        int answer = 0;
        for (int i = 0; i < N; i++) {
            answer = Math.max(answer, rope.get(i) * (i + 1));
        }
        System.out.println(answer);
    }
}
