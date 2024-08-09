package backJoon;

import java.util.*;
import java.io.*;

public class 공주님의_정원_2457 {
    private static int N;
    private static Day[] days;
    private static StringBuilder sb;

    static class Day{
        int s;
        int e;

        Day(int s, int e) {
            this.s = s;
            this.e = e;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        days = new Day[N];

        for (int i = 0; i < N; ++i) {
            String[] input = br.readLine().split(" ");
            int s = Integer.parseInt(input[0]) * 100 + Integer.parseInt(input[1]);
            int e = Integer.parseInt(input[2]) * 100 + Integer.parseInt(input[3]);
            days[i] = new Day(s, e);
        }
        //시작일 기준으로 정렬하되, 시작일이 같으면 마감일이 빠른 기준
        Arrays.sort(days, (o1, o2) -> {
            if (o1.s == o2.s) {
                return o1.e - o2.e;
            }
            return o1.s - o2.s;
        });

        sb = new StringBuilder();
        getFlowers();

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void getFlowers() {
        int max = 0, ans = 0, idx = 0;
        int start = 301;

        while (start < 1201) {
            boolean isFind = false;

            for (int i = idx; i < N; ++i) {
                //시작일보다 늦게 시작한다면 의미 없음
                if (days[i].s > start) {
                    break;
                }
                //가장 꽃이 지는 긴 날짜를 찾는다
                if (days[i].e > max) {
                    max = days[i].e;
                    isFind = true;
                    idx = i + 1;
                }
            }
            //찾았다면 max 갱신하고 ans 증가
            if (isFind) {
                ans++;
                start = max;
            } else {
                break;
            }
        }

        // 12월01일 보다 꽃이 지속되지 않으면 조건에 불만족
        if (max < 1201) {
            sb.append(0).append('\n');
            return;
        }
        sb.append(ans).append('\n');
    }

}
