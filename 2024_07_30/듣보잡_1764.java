package backJoon;

import java.io.*;
import java.util.*;

public class 듣보잡_1764 {
    private static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        //맵에 듣지 못한 친구를 넣는다.
        Map<String, Integer> dic = new HashMap<>();

        for (int i = 0; i < N; ++i) {
            String name = br.readLine();
            dic.put(name, dic.getOrDefault(name, 1));
        }

        List<String> list = new ArrayList<>();

        //보지도 못한 친구들의 이름을 맵에서 key로 가지고 있다면, 중복이기 때문에 list에 넣는다
        for (int i = 0; i < M; ++i) {
            String name = br.readLine();
            if (dic.containsKey(name)) {
                list.add(name);
            }
        }

        StringBuilder sb = new StringBuilder();
        //사전 순으로 정렬
        list.sort(Comparator.naturalOrder());
        sb.append(list.size()).append('\n');
        for (String name : list) {
            sb.append(name).append('\n');
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
