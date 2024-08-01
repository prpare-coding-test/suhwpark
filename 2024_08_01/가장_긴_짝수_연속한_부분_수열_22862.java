package backJoon;

import java.io.*;
import java.util.*;

public class 가장_긴_짝수_연속한_부분_수열_22862 {
    private static int N, K;
    private static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);
        //짝수는 0, 홀수는 1로 배열을 만든다
        arr = Arrays
                .stream(br.readLine().split(" "))
                .mapToInt(s -> {
                    int n = Integer.parseInt(s);
                    if (n % 2 == 0) {
                        return 0;
                    }
                    return 1;
                })
                .toArray();


        int maxLen = 0;
        int l = 0, r = 0;
        // K개 만큼 삭제하기 위한 변수
        int cnt = 0;
        // 길이를 구할 때 K만큼 삭제할 수 있기에, 홀수가 등장하면 cnt를 늘려준다
        while (r < N) {
            if (cnt < K) {
                if (arr[r] != 0) {
                    cnt++;
                }
                r++;
                maxLen = Math.max(maxLen, r - l - cnt);
            } else if (arr[r] == 0) {
                r++;
                maxLen = Math.max(maxLen, r - l - cnt);
            } else {
                // 카운트를 다 썻을 경우, 시작 지점이 홀수면 cnt--하고 시작 인덱스를 늘려준다
                if (arr[l] != 0) {
                    cnt--;
                }
                l++;
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(new StringBuilder().append(maxLen).toString());
        bw.close();
        br.close();
    }
}
