package backJoon;

import java.util.*;
import java.io.*;

public class 나는야_포켓몬_마스터_이다솜_1620 {
    private static int N, M;
    private static Map<String, Integer> dictionary;
    private static Map<Integer, String> numDictionary;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        //두가지 유형으로 dictionary를 생성
        dictionary = new HashMap<>();
        numDictionary = new HashMap<>();

        //StringTokenizer의 st.nextToken()으로 사용했더니 런타임 에러;;;
        //하나의 인자면 그냥 readLine()을 쓰자
        for (int i = 0; i < N; ++i) {
            String s = bf.readLine();
            dictionary.put(s, i + 1);
            numDictionary.put(i + 1, s);
        }

        StringBuilder sb = new StringBuilder();
        // 숫자인지 이름인지 판별하여 value를 get
        for (int i = 0; i < M; ++i) {
            String question = bf.readLine();
            if (Character.isDigit(question.charAt(0))) {
                sb.append(numDictionary.get(Integer.parseInt(question))).append('\n');
            } else {
                sb.append(dictionary.get(question)).append('\n');
            }
        }
        bw.write(sb.toString());
        bw.close();
        bf.close();
    }
}
