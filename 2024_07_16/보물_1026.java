package backJoon;

import java.util.*;
import java.io.*;

public class 보물_1026 {
    private static int N;
    private static List<Integer> A;
    private static List<Integer> B;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(bf.readLine());
        A = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            A.add(Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(bf.readLine());
        B = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            B.add(Integer.parseInt(st.nextToken()));
        }

        A.sort((o1, o2) -> o1 - o2);
        B.sort((o1, o2) -> o2 - o1);

        /**
         * A는 오름 차순, B는 내림 차순으로 정렬하여 주어진 점화식을 진행하면 최솟값 도출 가능
         */
        int answer = 0;
        for (int i = 0; i < N; i++) {
            answer += A.get(i) * B.get(i);
        }
        System.out.println(answer);
    }
}
