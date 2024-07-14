package backJoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Easy_12100 {
    static int N;
    static int[][] board;
    static int[] dir;
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(st.nextToken());
        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        answer = 0;
        dir = new int[5];

        dfs(0);
        bw.write(new StringBuilder().append(answer).toString());
        bw.close();
        bf.close();
    }

    /**
     * 5번에 대한 상 하 좌 우를 판단하여, 최대값을 저장한다.
     * dir에 각각 상 하 좌 우의 인덱스를 넣는다. 그리고 회전 5번에 대한, 모든 경우의 수를 실행한다.
     * depth가 5가 될때, 상 하 좌 우 회전을 하고, 가장 큰 블록을 구한다.
     *
     * 상, 우 로 회전하는 것은 시작 인덱스를 N - 1로 설정하고, index를 줄여가며 판단한다.
     * @param depth 최대 이동 회전 횟수인 5번인지 판단
     */
    private static void dfs(int depth) {
        if (depth == 5) {
            int[][] copyBoard = new int[N][N];
            for (int i = 0; i < N; i++)  {
                System.arraycopy(board[i], 0, copyBoard[i], 0, N);
            }

            for (int i = 0; i < 5; i++) {
                moveAll(dir[i], copyBoard);
            }

            int num = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    num = Math.max(num, copyBoard[i][j]);
                }
            }
            answer = Math.max(num, answer);
            return;
        }

        // dir에 상 하 좌 우에 대한 값을 넣어주고, depth를 늘린다.
        for (int i = 0; i < 4; i++) {
            dir[depth] = i;
            dfs(depth + 1);
        }
    }

    /**
     * 0 : 상 | 1 : 하 | 2 : 좌 | 3 : 우
     * switch 문을 통해서, 상 하 좌 우를 판단하여 board의 회전을 진행
     * 각 회전의 방향으로, board의 값이 있고 같은 값이면 합쳐준다.
     *
     * @param d 회전 방향
     * @param copyBoard dfs로 브루트 포스를 해야하기에 복사한 배열을 사용한다.
     */
    private static void moveAll(int d, int[][] copyBoard) {
        switch (d) {
            // 상 으로 움직였을 떄
            case 0 :
                for(int i = 0; i < N; i++) {
                    // value 값을 넣어줄 index
                    int index = 0;
                    // board의 값이 0이 아니면, 저장해주는 변수
                    int value = 0;
                    for (int j = 0; j < N; j++) {
                        if (copyBoard[j][i] != 0) {
                            if (copyBoard[j][i] == value) {
                                // 변수가 같다면, value * 2 ㄷㅌ) 2 + 2 = 4 ..
                                copyBoard[index - 1][i] = value * 2;
                                //값이 합쳐 졌으니, 다시 value는 0
                                value = 0;
                                // 현재 자리도 0
                                copyBoard[j][i] = 0;
                            } else {
                                // board의 값이 0이 아닌경우, 회전 방향으로 끝으로 합쳐주어야 하기에 value 저장
                                value = copyBoard[j][i];
                                // 저장한 값은 0으로 설정
                                copyBoard[j][i] = 0;
                                // index의 자리를 value로 채운다.
                                copyBoard[index][i] = value;
                                index++;
                            }
                        }
                    }
                }
                break;
            // 하 로 움직였을 때
            case 1 :
                for (int i = 0; i < N; i++) {
                    int index = N - 1;
                    int value = 0;
                    for (int j = N - 1; j >= 0; j--) {
                        if (copyBoard[j][i] != 0) {
                            if (copyBoard[j][i] == value) {
                                copyBoard[index + 1][i] = value * 2;
                                value = 0;
                                copyBoard[j][i] = 0;
                            } else {
                                value = copyBoard[j][i];
                                copyBoard[j][i] = 0;
                                copyBoard[index][i] = value;
                                index--;
                            }
                        }
                    }
                }
                break;
            // 좌 로 움직였을 때
            case 2 :
                for (int i = 0; i < N; i++) {
                    int index = 0;
                    int value = 0;
                    for (int j = 0; j < N; j++) {
                        if (copyBoard[i][j] != 0) {
                            if (copyBoard[i][j] == value) {
                                copyBoard[i][index - 1] = value * 2;
                                value = 0;
                                copyBoard[i][j] = 0;
                            } else {
                                value = copyBoard[i][j];
                                copyBoard[i][j] = 0;
                                copyBoard[i][index] = value;
                                index++;
                            }
                        }
                    }
                }
                break;
            // 우 로 움직였을 때
            case 3 :
                for (int i = 0; i < N; i++) {
                    int index = N - 1;
                    int value = 0;
                    for (int j = N - 1; j >= 0; j--) {
                        if (copyBoard[i][j] != 0) {
                            if (copyBoard[i][j] == value) {
                                copyBoard[i][index + 1] = value * 2;
                                value = 0;
                                copyBoard[i][j] = 0;
                            } else {
                                value = copyBoard[i][j];
                                copyBoard[i][j] = 0;
                                copyBoard[i][index] = value;
                                index--;
                            }
                        }
                    }
                }
                break;
        }
    }
}
