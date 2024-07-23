package backJoon;

import java.io.*;
import java.util.*;

public class 톱니바퀴_14891 {
    private static int[][] gear;
    private static int K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        gear = new int[4][8];
        for (int i = 0; i < 4; ++i) {
            String[] line = br.readLine().split("");
            for (int j = 0; j < 8; ++j) {
                gear[i][j] = Integer.parseInt(line[j]);
            }
        }

        K = Integer.parseInt(br.readLine());
        /**
         *  톱니의 index 2,6 을 봐야함
         *  회전 여부의 관한 경우의 수
         *  1번 톱니의 index 2 와 2번 톱니의 index 6
         *  2번 톱니의 index 2 와 3번 톱니의 index 6
         *  3번 톱니의 index 2 와 4번 톱니의 index 6
         *
         *  서로 다른 값이면 반대 방향으로 회전
         *
         *  중요한 것-> 회전 시킬때, gear의 값 변경하는 법
         *  1, 0, 1, 0, 1, 1, 1, 1
         *  #시계방향으로 회전할 경우
         *  1, 1, 0, 1, 0, 1, 1, 1
         *  #시계 반대 방향으로 회전할 경우
         *  0, 1, 0, 1, 1, 1, 1, 1
         *
         *  시계 방향
         *  int end = gear[gear.length - 1];
         *  int [] copy
         *  for (int i = 0; i < 7; i++) {
         *     copy[i + 1] = gear[i];
         *  }
         *  copy[0] = end;
         *
         *  시계 반대 방향
         *  int start = gear[0];
         *  int copy[];
         *  for (int i = 0; i < 7; i++) {
         *     copy[i] = gear[i + 1];
         *  }
         *  copy[copy.length] = start;
         */
        for (int i = 0; i < K; i++) {
            String[] line = br.readLine().split(" ");
            rotate(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
        }

        int answer = 0;
        if (gear[0][0] != 0) {
            answer += 1;
        }
        if (gear[1][0] != 0) {
            answer += 2;
        }
        if (gear[2][0] != 0) {
            answer += 4;
        }
        if (gear[3][0] != 0) {
            answer += 8;
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(new StringBuilder().append(answer).toString());
        bw.close();
        br.close();

    }
    private static void rotate(int gearNum, int dir) {
        List<int[]> cnt = new ArrayList<>();
        if (gearNum == 1) {
            cnt.add(new int[]{1, dir});
            if (gear[0][2] != gear[1][6]) {
                cnt.add(new int[]{2, -dir});
                if (gear[1][2] != gear[2][6]) {
                    cnt.add(new int[]{3, dir});
                    if (gear[2][2] != gear[3][6]) {
                        cnt.add(new int[]{4, -dir});
                    }
                }
            }
        } else if (gearNum == 2) {
            cnt.add(new int[]{2, dir});
            if (gear[0][2] != gear[1][6]) {
                cnt.add(new int[]{1, -dir});
            }
            if (gear[1][2] != gear[2][6]) {
                cnt.add(new int[]{3, -dir});
                if (gear[2][2] != gear[3][6]) {
                    cnt.add(new int[]{4, dir});
                }
            }
        } else if (gearNum == 3) {
            cnt.add(new int[]{3, dir});
            if (gear[1][2] != gear[2][6]) {
                cnt.add(new int[]{2, -dir});
                if (gear[0][2] != gear[1][6]) {
                    cnt.add(new int[]{1, dir});
                }
            }
            if (gear[2][2] != gear[3][6]) {
                cnt.add(new int[]{4, -dir});
            }
        } else {
            cnt.add(new int[]{4, dir});
            if (gear[3][6] != gear[2][2]) {
                cnt.add(new int[]{3, -dir});
                if (gear[2][6] != gear[1][2]) {
                    cnt.add(new int[]{2, dir});
                    if (gear[1][6] != gear[0][2]) {
                        cnt.add(new int[]{1, -dir});
                    }
                }
            }
        }
        for (int[] r : cnt) {
            if (r[1] == -1) {
                antiClockwise(r[0]);
            } else {
                clockwise(r[0]);
            }
        }
    }

    private static void clockwise(int num) {
        int[] copy = new int[8];
        System.arraycopy(gear[num - 1], 0,  copy, 0,8);
        int end = copy[7];
        System.arraycopy(copy, 0, gear[num - 1], 1, 7);
        gear[num - 1][0] = end;

    }

    private static void antiClockwise(int num) {
        int[] copy = new int[8];
        System.arraycopy(gear[num - 1], 0,  copy, 0,8);
        int start = copy[0];
        System.arraycopy(copy, 1, gear[num - 1], 0, 7);
        gear[num - 1][7] = start;
    }
}
