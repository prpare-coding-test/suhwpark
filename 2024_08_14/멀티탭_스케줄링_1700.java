import java.io.*;
import java.util.*;
public class 멀티탭_스케줄링_1700 {
    private static int N, K;
    private static List<Integer> name;
    private static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);

        name = new ArrayList<>();
        input = br.readLine().split(" ");
        for (int i = 0; i < K; ++i) {
            name.add(Integer.parseInt(input[i]));
        }

        /**
         * 조건
         * 1. 이미 멀티탭에서 사용중 -> 현상태 유지
         * 2. 앞으로 다시 쓰지 않는 전기 용품이 있다면, 전기 용품을 멀티탭에서 제고하고 새로운 전기 용품을 꽂는다
         * 3. 현재 멀티탭에서 사용하고 있는 것들 중에 다시 쓰지 않을 전기 용품이 없다면, 지금 시점으로부터 가장 늦게 사용될 전기 용품을 제거하고 새로운 전기 용품을 꽂는다
         */
        ans = 0;

        solve();

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(new StringBuilder().append(ans).toString());
        bw.close();
        br.close();#
    }

    private static void solve() {
        List<Integer> multiTap = new ArrayList<>();

        while(!name.isEmpty()) {
            int order = name.remove(0);
            //이미 멀티탭에 기기가 꽂혀 있는 경우
            if (multiTap.contains(order)) {
                continue;
            }
            //멀티탭에 공간이 있고, 기기가 없는 경우
            if (multiTap.size() < N) {
                multiTap.add(order);
            }
            // 멀티탭에 공간이 없을 경우
            else {
                // 기기 순서에 중복이 되는 지를 확인하는 변수
                boolean flag = false;
                //
                int idx = -1;
                int maxIdx = -1;
                ans++;
                // 멀티탭을 순회하면서, 조건 2,3을 설정
                for (int i = 0; i < multiTap.size(); ++i) {
                    // 만약 다시 쓰지 않을 기기라면, 플래그를 세워주고 그 기기를 뽑는다
                    if (!name.contains(multiTap.get(i))) {
                        flag = true;
                        multiTap.remove(i);
                        multiTap.add(order);
                        break;
                    } else {
                        // 가장 마지막에 사용될 기기를 찾고 idx를 저장
                        if (name.indexOf(multiTap.get(i)) > idx) {
                            idx = name.indexOf(multiTap.get(i));
                            maxIdx = i;
                        }
                    }
                }
                // 다시 써야하는 기기라면, 가장 늦게 사용하는 기기를 지운다.
                if (!flag) {
                    multiTap.remove(maxIdx);
                    multiTap.add(order);
                }
            }
        }
    }
}
