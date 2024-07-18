package backJoon;

import java.util.*;
import java.io.*;
public class 회사에_있는_사람_7785 {
    private static int N;
    private static Map<String, Boolean> company;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(st.nextToken());

        //enter, leave로 판단하여 input의 상태값을 설정
        company = new HashMap<>();
        for (int i = 0; i < N; ++i) {
            String[] line = bf.readLine().split(" ");
            if (line[1].equals("enter")) {
                company.put(line[0], true);
            } else {
                company.put(line[0], false);
            }
        }

        List<String> answer = new ArrayList<>();
        //map의 키를 순회하면서, 키 값이 true인 경우만 list에 넣는다
        for (String key : company.keySet()) {
            if (company.get(key)) {
                answer.add(key);
            }
        }

        //사전 역순으로 정렬
        answer.sort(Comparator.reverseOrder());
        StringBuilder sb = new StringBuilder();
        for (String name : answer) {
            sb.append(name).append('\n');
        }
        bw.write(sb.toString());
        bw.close();
        bf.close();
    }
}
