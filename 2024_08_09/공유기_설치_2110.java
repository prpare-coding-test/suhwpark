import java.io.*;
import java.util.*;

public class 공유기_설치_2110 {
    private static int N, C;
    private static int[] h;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        C = Integer.parseInt(input[1]);

        h = new int[N];

        for (int i = 0; i < N; ++i) {
            h[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(h);

        StringBuilder sb = new StringBuilder();
        sb.append(getMaxDistance());

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static int getMaxDistance() {
        int left = 1;
        int right = h[N - 1] - h[0] + 1;

        while (left < right) {
            int mid = (left + right) / 2;

            /*
            mid 거리에 대해 설치 가능한 공유기 갯수가 C개에 미치지 못하면
            거리를 좁혀야한다.
             */
            if (install(mid) < C) {
                right = mid;
            } else {
                /*
                설치 가능한 공유기 갯수가 C개 보다 크거나 같으면
                거리르 벌리면서 최소 거리가 가질 수 있는 최대 거리를 구한다
                 */
                left = mid + 1;
            }
        }
        // 탐색 값을 초과하는 첫번째 값이 left가 되기 때문에, 1을 뺴준다.
        return left - 1;
    }

    private static int install(int mid) {
        int cnt = 1;
        int last = h[0];

        for (int i = 1; i < N; ++i) {
            int l = h[i];

            if (l - last >= mid) {
                cnt++;
                last = l;
            }
        }
        return cnt;
    }
}
