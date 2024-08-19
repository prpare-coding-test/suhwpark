import java.util.*;
import java.io.*;

public class 멀티버스_2_18869 {
    private static int M, N;
    private static List<Integer>[] universe;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        M = Integer.parseInt(input[0]);
        N = Integer.parseInt(input[1]);
        universe = new List[M];

        for (int i = 0; i < M; ++i) {
            //현재 행성 리스트
            List<Integer> space = new ArrayList<>();
            //중복을 없애기 위한 리스트
            Set<Integer> set = new HashSet<>();
            // 행성의 순서를 저장하는 리스트 배열
            universe[i] = new ArrayList<>();

            input = br.readLine().split(" ");
            for (int j = 0; j < N; ++j) {
                int p = Integer.parseInt(input[j]);
                set.add(p);
                space.add(p);
            }

            //set을 리스트로 변환
            List<Integer> clone = new ArrayList<>(set);
            //정렬
            clone.sort((o1, o2) -> o1 - o2);

            for (int j = 0; j < N; ++j) {
                //collections에서 제공하는 이분탐색 사용
                int idx = Collections.binarySearch(clone, space.get(j));
                //행성의 j번째를 인덱스로 변환
                space.set(j, idx);
            }
            //인덱스 리스트 배열에 set
            universe[i] = space;
        }
        int ans = 0;
        for (int i = 0; i < M; ++i) {
            for (int j = i + 1; j < M; ++j) {
                // 인덱스 순서를 배열로 만들어서 판단. [0, 1, 2] == [0, 1, 2]
                if (Arrays.equals(universe[i].toArray(), universe[j].toArray())) {
                    ans++;
                }
            }
        }


        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        bw.write(new StringBuilder().append(ans).toString());
        bw.close();
        br.close();
    }
}
