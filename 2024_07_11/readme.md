## 치킨 배달 15686
### 소요시간
1시간

### 간단 풀이 방법
- 완전 탐색으로 주어진 치킨 가게 중 M개를 선택하여, 그 중 집과의 거리가 최솟값을 구한다.
- 모든 치킨 가게 중 M개를 방문하기 위해, visited 배열을 만들어 방문 표시를 하고 dfs로 탐색한다.
- dfs 탐색할 때, storePos를 넣어서 중복되지 않게 해결한다
- 한번 방문하고 나오면, 다시금 방문하지 않았다고 표시를 하여 모든 경우의 수를 탐색할 수 있다.
- 모든 경우의 수에서 Min 값을 찾아 문제 해결한다.

### pseudo code
```java
private static void dfs(int storePos, int depth) {
        if (depth == M) {
            int result = 0;
            for (int i = 0; i < house.size(); i++) {
                int min = Integer.MAX_VALUE;

                for (int j = 0; j < store.size(); j++) {
                    if (visited[j]) {
                        int distance = Math.abs(house.get(i).y - store.get(j).y) +
                                Math.abs(house.get(i).x - store.get(j).x);
                        min = Math.min(min, distance);
                    }
                }
                result += min;
            }
            answer = Math.min(answer, result);
            return ;
        }

        for (int i = storePos; i < store.size(); i++) {
            visited[i] = true;
            dfs(i + 1, depth + 1);
            visited[i] = false;
        }
    }
```

### 메모리 및 시간
- 18136kb
- 204ms

## Easy(2048) 12100
### 소요시간
1시간 30분

### 간단 풀이 방법
- 5번에 대한 상 하 좌 우를 판단하여, 최대값을 저장한다.
- 방향 dir 베열에 각각 상 하 좌 우의 인덱스를 넣는다. 그리고 회전 5번에 대한, 모든 경우의 수를 실행한다.
- depth가 5가 될때, 5회전을 하고, 가장 큰 블록을 구한다.

### pseudo code
```java
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

for (int i = 0; i < 4; i++) {
    dir[depth] = i;
    dfs(depth + 1);
}

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
```

### 메모리 및 시간
- 19248kb
- 188ms