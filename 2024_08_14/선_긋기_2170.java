import java.util.*;
import java.io.*;

public class 선_긋기_2170 {
    private static int N;
    private static class Pos {
        int s;
        int e;

        Pos(int s, int e) {
            this.s = s;
            this.e = e;
        }

    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        List<Pos> p = new ArrayList<>();
        for (int i = 0; i < N; ++i) {
            String[] input = br.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);
            p.add(new Pos(a, b));
        }

        p.sort((o1, o2) -> {
            if (o1.s == o2.s) {
                return o1.e - o2.e;
            }
            return o1.s - o2.s;
        });

        Pos first = p.get(0);
        int min = first.s;
        int max = first.e;
        p.remove(first);
        int len = 0;
        // 3가지
        // 1. 선 안에 포함 될때 : len 변화 x
        // 2. 현재 시작점이 이전 선에 포함 될때 : max값 변경
        // 3. 현재 선과 이전 선이 겹치지 않는다면 : len += max - min
        for (Pos pos : p) {
            if (max < pos.s) {
                len += max - min;
                min = pos.s;
                max = pos.e;
                continue;
            }
            if (pos.e > max) {
                max = pos.e;
            }
        }
        len += max - min;

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        bw.write(new StringBuilder().append(len).append('\n').toString());
        bw.close();
        br.close();
    }
}
