import java.io.*;
import java.util.*;

public class 수_고르기_2230 {
    private static int N, M;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];

        for (int i = 0; i < N; ++i) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);

        int answer = Integer.MAX_VALUE;
        /**
         * 첫 번쨰 시도
         * 이렇게 계산 하면 될줄 알았다...
         * 하지만 A[n] - 최솟값 제일 작은 차이라고 확언할 수 없다.
         * M = 2
         * 1 2 4 5
         * 이것일 때 답은 3으로 나오기 때문 사실 정답은 4 -2 = 2 여야한다.
         */
//        for (int i = 0; i < arr.length; ++i) {
//            if (arr[i] - arr[0] >= M) {
//                answer = arr[i] - arr[0];
//                break;
//            }
//        }
        /**
         * 두번째 시도
         * 투 포인터 개념을 사용하였다.
         * index i를 1번으로 지정하고, j는 0번으로 지정하여, arr[i] - arr[j] >= M 까지 i의 인덱스를 증가시킨다
         * ex) 1 1 1 1 1 1 2 4 5 8 9  (M = 5)
         * i는 arr[i] = 8 까지 증감 cuz, 8 - 1 > M
         * 이때 answer = 7
         * 1 1 1 1 1 1 2 4 5 8 9 (1번째 j 증감)
         *   j               i
         * ...
         * 1 1 1 1 1 1 2 4 5 8 9 (6번째 j 증감)
         *             j     i
         * answer = 6 (8 - 6)
         * 1 1 1 1 1 1 2 4 5 8 9 (7번째 j 증감)
         *               j   i
         * i++
         * 1 1 1 1 1 1 2 4 5 8 9 (6번째 j 증감)
         *               j     i
         * 9 - 4 = 5 (정답)
         * 이런 식으로 index를 두개 놓고 M보다 클때까지의 기준을 설정한후 최솟값을 움직이면서 정답을 도출하기로했다.
         */
        int i = 1;
        int j = 0;
        while (i < N) {
            if (arr[i] - arr[j] < M) {
                i++;
                continue;
            }
            if (arr[i] - arr[j] == M) {
                answer = M;
                break ;
            }
            answer = Math.min(answer, arr[i] - arr[j]);
            j++;
        }

        System.out.println(answer);
    }
}
