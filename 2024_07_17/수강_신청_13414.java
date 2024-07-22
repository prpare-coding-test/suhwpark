package backJoon;

import java.util.*;
import java.io.*;
public class 수강_신청_13414 {
    private static int N, M;
    private static LinkedHashSet<String> list;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        //map으로 entryKey를 사용하기 싫어서 linkedHashSet을 사용해봤다
        list = new LinkedHashSet<>();

        //만약 set에 수강신청 번호가 들어가 있다면, 삭제하고 다시 넣어준다
        for (int i = 0; i < M; ++i) {
            String num = bf.readLine();
            if (list.contains(num)) {
                list.remove(num);
            }
            list.add(num);
        }

        StringBuilder sb = new StringBuilder();
        int i = 0;
        //N개 만큼 출력
        for (String num : list) {
            if (i == N) {
                break ;
            }
            sb.append(num).append('\n');
            i++;
        }
        bw.write(sb.toString());
        bw.close();
        bf.close();
    }
}