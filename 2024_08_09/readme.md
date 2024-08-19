## 사다리_조작_15684
### 소요 시간
1시간 30분

### 간단 풀이 방법
각 좌표에서 왼쪽에서 오른쪽으로 이어지면 map[i][j]의 값을 1
오른쪽에서 왼쪽으로 갈수 있다면 map[i][j + 1]의 값을 2로 설정
dfs를 사용하여, 사다리가 이어지지 않은 부분에 사다리를 만들면서, 순회한다
만약 놓을 수 있는 사다리를 다 놓았다면, i에서 i로 사다리를 타고 내려갈 수 있는 지 확인한다

### pseudo code
```java
private static void dfs(int y, int cnt) {
    if (isFinished) {
        return;
    }
    if (ans == cnt) {
        if (checkLadder()) {
            isFinished = true;
        }
        return;
    }

    for (int i = y; i < H + 1; ++i) {
        for (int j = 1; j < N; ++j) {
            if (ladder[i][j] == 0 && ladder[i][j + 1] == 0) {
                ladder[i][j] = 1;
                ladder[i][j + 1] = 2;
                dfs(i, cnt + 1);
                ladder[i][j] = 0;
                ladder[i][j + 1] = 0;
            }
        }
    }
}
```

### 메모리 및 시간
- 16932kb
- 404ms

## 드래곤_커브_15685
### 소요시간
2시간... (구글링 했습니다)

### 간단 풀이 방법
0세대 : 0
1세대 : 0 1
2세대 : 0 1 2 1
3세대 : 0 1 2 1 2 3 2 1
현재 세대 = 이전 세대 * 2
또한 이전 세대의 방향들은 현재세대의 앞부분의 방향 + 뒤집은 이전세대 방향들 + 1이다
즉, 2세대 (1 2 1 0) + 1 3세대의 2 3 2 1
커브를 그리고, 사각형인 것을 확인한다.

### pseudo code
```java
private static void draw(int x, int y, int d, int g) {
    List<Integer> list = new ArrayList<>();

    list.add(d);

    for (int i = 1; i <= g; ++i) {
        for (int j = list.size() - 1; j >= 0; --j) {
            list.add((list.get(j) + 1) % 4);
        }
    }

    map[y][x] = true;
    for (Integer dir : list) {
        y += dy[dir];
        x += dx[dir];
        map[y][x] = true;
    }
}
```

### 메모리 및 시간
- 14412kb
- 108ms

## 공유기_설치_2110
### 소요 시간
1시간

### 간단 풀이 방식
이분탐색을 사용하여, 인덱스 1부터 mid값 까지 놓을 수 있는 공유기 대수를 구한다
mid 거리에 대해 설치 가능한 공유기 갯수가 C개에 미치지 못하면 거리를 좁혀야한다.
설치 가능한 공유기 갯수가 C개 보다 크거나 같으면 거리를 벌리면서 최소 거리가 가질 수 있는 최대 거리를 구한다

### pesudo code
```java
private static int getMaxDistance() {
    int left = 1;
    int right = h[N - 1] - h[0] + 1;

    while (left < right) {
        int mid = (left + right) / 2;

        /*
        mid 거리에 대해 설치 가능한 공유기 갯수가 C개에 미치지 못하면
        거리를 좁혀야한다.
            */
        if (install(mid) < C) {
            right = mid;
        } else {
            /*
            설치 가능한 공유기 갯수가 C개 보다 크거나 같으면
            거리르 벌리면서 최소 거리가 가질 수 있는 최대 거리를 구한다
                */
            left = mid + 1;
        }
    }
    // 탐색 값을 초과하는 첫번째 값이 left가 되기 때문에, 1을 뺴준다.
    return left - 1;
}

private static int install(int mid) {
    int cnt = 1;
    int last = h[0];

    for (int i = 1; i < N; ++i) {
        int l = h[i];

        if (l - last >= mid) {
            cnt++;
            last = l;
        }
    }
    return cnt;
}
```

### 메모리 및 시간
- 28976kb
- 252ms