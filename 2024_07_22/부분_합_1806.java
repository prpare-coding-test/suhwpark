import java.io.*;
import java.util.*;

public class 부분_합_1806 {
    private static int N, S;
    private static int[] A;
    private static int[] prefix;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        A = new int[N];
        String[] input = br.readLine().split(" ");
        for (int i = 0; i < N; ++i) {
            A[i] = Integer.parseInt(input[i]);
        }

        prefix = new int[N + 1];
        prefix[0] = 0;
        for (int i = 1; i <= N; ++i) {
            prefix[i] = prefix[i - 1] + A[i - 1];
        }

        /**
         * 첫 번재 시도
         * 부분합을 사용해서, 투포인터로 검색하면서 찾는 것인데 예제는 잘 되었지만 제출 후 오답
         * S 이상인데... S 와 같을 때라고 풀어서...
         *
         * 두 번째 시도
         * [0, 5, 6, 9, 14, 24, 31, 35, 44, 46, 54] 예제의 부분합을 이렇게 구해서
         * oob를 막기 위해 i = 2, j = 1로 설정해서 구했다
         * 하지만 계속해서 정답이 60퍼에서 넘어가지 않았다.....
         *
         * 마지막 시도
         * 생각해보니 2 10 10 10 이렇게 되면, 길이는 1이지만
         * 2번째의 방식으로 하게되면, 2가 무적권으로 정답이 될것이다.....
         * 길이가 1일 때의 예외 처리를 잘해야겠다는 생각 뿐....
         */
        int i = 1;
        int j = 1;
        int answer = Integer.MAX_VALUE;
        /**
         * 예제에 관해서
         * [X, 5, 1, 3, 5, 10, 7, 4, 9, 2, 8] 에 대한 부분 합 배열에서 index 5, 3에 관한 합을 구하자면
         * [0, 5, 6, 9, 14, 24, 31, 35, 44, 46, 54]
         *                   5
         *           3
         * index 5까지의 합 24, index 3까지의 합 9
         * 두 부분합이 공유하는 합은 index 2까지이다
         * 즉, 24 - 6 = 18이 된다
         * 이 방식으로 S >= 부분 합 이 될때, 최솟값의 길이를 구한다.
         * 
         */
        while (i < N + 1 && j < N + 1) {
            if (prefix[i] - prefix[j - 1] < S) {
                i++;
                continue;
            }
            answer = Math.min(answer, i - j + 1);
            j++;
            i = j;
        }
        if (answer == Integer.MAX_VALUE) {
            System.out.println(0);
            return;
        }
        System.out.println(answer);
    }
}
