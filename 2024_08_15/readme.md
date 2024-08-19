## 동전_9084
### 소요 시간
40분

### 간단 풀이 방법
     1   2   3   4   5   6   7   8   9   10
1    1   1   1   1   1   1   1   1   1   1
2    0   1   1   2   2   3   3   4   4   5
5    0   0   0   0   1   1   2   2   3   4
sum  1   2   2   3   4   5   5   7   8   10
위 처럼 동전을 사용할 수 있다.
즉, 점화식 dp[c] += dp[c - arr[i]];

### pseudo code
```java
for (int j = c; j <= M; ++j) {
    dp[j] += dp[j - c];
}
```

### 시간 및 메모리
- 14052kb
- 96ms

## 동전_2_2294
### 소요시간
40분

### 간단 풀이 방법
1 5 12로 15를 만드는 방법
사용한 동전의 갯수
   1 2 3 4 5 6 7 8 9 10 11 12 13 14 15
 1 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15
 5 1 2 3 4 1 2 3 4 5 2  3  4  5  6  3
12 1 2 3 4 1 2 3 4 5 2  3  1  2  3  3
위와 같이 만들 수 있다.
즉, 점화식은 dp[c] = Math.min(dp[c], dp[c - arr[i]] + 1)

### pseudo code
```java
for (int j = c; j < k + 1; ++j) {
    dp[j] = Math.min(dp[j], dp[j - c] + 1);
}
```

### 시간 및 메모리
- 14332kb
- 124ms

## 미세먼지_안녕_17144
### 소요 시간
1시간 (공기청정기... 위치를 잘못 초기화함..)

### 간단 풀이 방식
map을 순회하면서, 바이러스 크키가 5 이상인 좌표를 큐에 넣는다
큐를 순회하면서, 바이러스를 확산시킨다
공기 청정기를 가동시킨다
위의 과정을 T초 만큼 반복한다
바이러스의 총합을 구하자

### pseudo code
```java
private static void spread() {
    Queue<Pos> q = new LinkedList<>();

    for (int i = 0; i < R; ++i) {
        for (int j = 0; j < C; ++j) {
            if (map[i][j] > 4) {
                q.add(new Pos(i ,j, map[i][j]));
            }
        }
    }

    while(!q.isEmpty()) {
        Pos p = q.poll();

        int spreadValue = p.v / 5;
        int cnt = 0;
        for (int i = 0; i < 4; ++i) {
            int ny = p.y + dy[i];
            int nx = p.x + dx[i];

            if (ny < 0 || ny >= R || nx < 0 || nx >= C || map[ny][nx] == -1) {
                continue;
            }

            map[ny][nx] += spreadValue;
            cnt++;
        }
        map[p.y][p.x] -= spreadValue * cnt;
    }
}

private static void clean() {
    int topMachine = machine;
    int downMachine = machine + 1;

    //위쪽 기계
    //아래로 당기기
    for (int i = topMachine - 1; i > 0; --i) {
        map[i][0] = map[i - 1][0];
    }

    //왼쪽으로 당기기
    for (int i = 0; i < C - 1; ++i) {
        map[0][i] = map[0][i + 1];
    }

    //위로 당기기
    for (int i = 0; i < topMachine; ++i) {
        map[i][C - 1] = map[i + 1][C - 1];
    }

    //오른쪽으로 당기기
    for (int i = C - 1; i > 1; --i) {
        map[topMachine][i] = map[topMachine][i - 1];
    }

    map[topMachine][1] = 0;

    //아래쪽 기계
    //위로 당기기
    for (int i = downMachine + 1; i < R - 1; ++i) {
        map[i][0] = map[i + 1][0];
    }

    //왼쪽으로 당기기
    for (int i = 0; i < C - 1; ++i) {
        map[R - 1][i] = map[R - 1][i + 1];
    }

    //아래로 당기기
    for (int i = R - 1; i > downMachine; --i) {
        map[i][C - 1] = map[i - 1][C - 1];
    }

    //오른쪽으로 당기기
    for (int i = C - 1; i > 1; --i) {
        map[downMachine][i] = map[downMachine][i - 1];
    }
    map[downMachine][1] = 0;
}
```

### 시간 및 메모리
- 140160kb
- 440ms