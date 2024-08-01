package backJoon;

import java.util.*;
import java.io.*;
public class 회전_초밥_2531 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] sushi = new int[N];
        for (int i = 0; i < N; ++i) {
            sushi[i] = Integer.parseInt(br.readLine());
        }
        int res = Integer.MIN_VALUE;

        // 모든 k 만큼 먹는 경우의 수를 구한 후 가장 큰 값을 구한다.
        for (int i = 0; i < N; ++i) {
            Set<Integer> s = new HashSet<>();
            s.add(c);

            for (int j = 0; j < k; ++j) {
                int index = i + j;
                if (i + j >= N) {
                    index = i + j - N;
                }
                s.add(sushi[index]);
            }
            res = Math.max(res, s.size());
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(new StringBuilder().append(res).toString());
        bw.close();
        br.close();
    }
}
