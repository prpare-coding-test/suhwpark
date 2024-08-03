package backJoon;

import java.io.*;
import java.util.*;

public class 트럭_13335 {
    private static int n, w, L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        String[] input = br.readLine().split(" ");

        Queue<Integer> bridge = new LinkedList<>();
        Queue<Integer> truck = new LinkedList<>();

        for (int i = 0; i < n; ++i) {
            truck.add(Integer.parseInt(input[i]));
        }
        //1초 당 다리의 1길이 만큼 가기 때문
        for (int i = 0; i < w; ++i) {
            bridge.add(0);
        }

        int time = 0;
        int bridgeWeight = 0;
        // 다리를 건널때, 다리에 다음 트럭을 놓을 수 있는지 판단하고
        // 놓을 수 있다면, 다리에 놓고 그렇지 않으면, 0을 add하여 트럭을 움직인다.
        // 시간이 지나면서, 트럭이 이동하고, 다리의 길이 만큼 움직였다면, 다리 무게에서 트럭 무게를 뺴준다
        // 그러므로 다리의 길이를 0으로 add하기 때문에, 각각의 트럭이 움직이는 거리를 구할 수 있다.
        while(!bridge.isEmpty()) {
            time += 1;
            bridgeWeight -= bridge.poll();
            if (!truck.isEmpty()) {
                int truckWeight = truck.peek();
                if (truckWeight + bridgeWeight <= L) {
                    bridgeWeight += truckWeight;
                    bridge.add(truck.poll());
                } else {
                    bridge.add(0);
                }
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        bw.write(new StringBuilder().append(time).append('\n').toString());
        bw.close();
        br.close();
    }
}
