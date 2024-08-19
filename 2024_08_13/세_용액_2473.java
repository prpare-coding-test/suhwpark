import java.util.*;
import java.io.*;

public class 세_용액_2473 {
    private static int N;
    private static long[] liquid;
    private static  long[] pick = new long[3];
    private static long max = 3000000000L;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        liquid = new long[N];
        String[] input = br.readLine().split(" ");
        for (int i = 0; i < N; ++i) {
            liquid[i] = Long.parseLong(input[i]);
        }

        //이분 탐색을 위한 정렬
        Arrays.sort(liquid);

        //0에 가까운 것을 찾음
        for (int i = 0; i < N - 2; ++i) {
            findLiquid(i);
        }

        Arrays.sort(pick);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; ++i) {
            sb.append(pick[i]).append(' ');
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void findLiquid(int index) {
        //인덱스 다음
        int left = index + 1;
        //끝 인덱스
        int right = N - 1;

        while (left < right) {
            long sum = liquid[left] + liquid[right] + liquid[index];
            long abs = Math.abs(sum);
            if (abs < max) {
                pick[0] = liquid[index];
                pick[1] = liquid[left];
                pick[2] = liquid[right];

                max = abs;
            }

            //0보다 크면, 큰 수를 옮긴다.
            if (sum > 0) {
                right--;
            } else {
                //작은 수를 옮긴다
                left++;
            }
        }
    }
}