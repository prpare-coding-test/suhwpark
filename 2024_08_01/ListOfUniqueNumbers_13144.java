package backJoon;

import java.lang.reflect.Array;
import java.util.*;
import java.io.*;
public class ListOfUniqueNumbers_13144 {
    private static int N;

    /**
     * 풀이 방식
     * ex) 1 2 3 1 2
     * 중복된 숫자 없이 연속해서 뽑을 수 있는 경우의 수를 구하는 문제
     * 차례대로 뽑는다고 하면 (시작 인덱스 l로 하자, 마지막 인덱스 r)
     * 1 (l = 0, r = 0)
     * 1 2 (l = 0, r = 1)
     * 1 2 3 (l = 0, r = 2)
     * 즉, 3가지 (r - l + 1)
     *
     * 2 (l = 1, r = 1)
     * 2 3 (l = 1, r = 2)
     * 2 3 1 (l = 1, r = 3)
     * 즉, 3가지 (3 - 1 + 1)
     *
     * 3 (l = 2, r = 2)
     * 3 1 (l = 2, r = 3)
     * 3 1 2 (l = 2, r = 4)
     * 즉, 3가지 4 - 2 + 1
     *
     * 1 (l = 3, r = 3)
     * 1 2 (l = 3, r = 4)
     * 즉, 2가지 4 - 3 + 1
     *
     * 2 (l = 4, r = 4)
     * 즉, 1가지 4 - 4 + 1
     * 총 열두 가지
     *
     * 위에서 본 결과 시작 인덱스와 끝 인덱스를 알면 중복없이 연속된 수를 고르는 경우의 수를 구할 수 있다
     * 그렇기에 투포인터를 사용하여 끝 인덱스 - 시작 인덱스 + 1 로 하였다
     * 또한 시작 인덱스가 갱신될 때, cnt배열을 초기화해주어야 다시 방문 가능하다
     * r = l로 초기화 하지 않는 이유는 시작 인덱스는 중복이 되어야 갱신되기 때문이다
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        int[] nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int r = 0, l = 0;
        long ans = 0L;
        int[] cnt = new int[N + 1];

        while (l < N) {
            while (r < N && cnt[nums[r]] == 0) {
                cnt[nums[r]] += 1;
                r++;
            }
            ans += r - l;
            cnt[nums[l]] -= 1;
            l++;
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(new StringBuilder().append(ans).toString());
        bw.close();
        br.close();
    }
}
