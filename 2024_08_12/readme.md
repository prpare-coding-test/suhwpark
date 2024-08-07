## 로봇_청소기_14503
### 소요시간
1시간

### 간단 풀이 방법
로봇 청소기가 움직이는 경우
1. 현재 위치가 청소 되어있지 않으면 청소 cnt++
2. 동서남북에서 청소할 수 있는 칸이 없다면, 후진한다. 후진 할 수 없으면 종료
3. 동서남북에서 청소할 수 있는 칸이 있다면, 반시계 90 회전후 1번 실행
4. 북 : 0, 동 : 1, 남 : 2, 3: 서 -> 인데스로 나누어서 진행
반시계 방향을 배열로 관리하기 위한 로직 {북, 서, 남, 동}
북 -> 서
서 -> 남
남 -> 동
동 -> 북

인덱스로 반시계 90도를 회전하는 방법
북 -> 서 : 0 + (-1) = (-1 + 4) % 4 = 3;
서 -> 남 : 3 + (-1) = (2 + 4) % 4 = 2;
남 -> 동 : 2 + (-1) = (1 + 4) % 4 = 1;
동 -> 북 : 1 + (-1) = (0 + 4) % 4 = 0;

헤드에서 후진하는 인덱스 구하기
dir = (dir + 2) % 4;
dfs()로 구현

### pseudo code
```java
private static void clean(int y, int x, int dir) {
    // 위치 청소
    map[y][x] = -1;

    for (int i = 0; i < 4; i++) {
        dir = (dir + 3) % 4;

        int ny = y + dy[dir];
        int nx = x + dx[dir];

        // 전진해서 다 청소가 되어있느지 확인
        if (ny >= 0 && ny < N && nx >= 0 && nx < M) {
            if (map[ny][nx] == 0) {
                cnt++;
                clean(ny, nx, dir);
                return;
            }
        }
    }

    // 후진할 수 있는지 확인
    int back = (dir + 2) % 4;
    int by = y + dy[back];
    int bx = x + dx[back];

    if (by >= 0 && by < N && bx >= 0 && bx < M && map[by][bx] != 1) {
        clean(by, bx, dir);
    }
}
```

### 메모리 및 시간
- 14360kb
- 108ms

## 뱀_3190
### 소요 시간
45분

### 간단 풀이 방법
각 map을 0으로 초기화
사과일 경우 1로 표시
map에 시간과 방향을 저장한다.
뱀의 이동 경로를 순서대로 저장하기 위해, List 선언 후, 뱀이 이동할때마다 경로 추가
사과를 먹게 되면, 뱀의 머리를 리스트에 저장
만약 먹지 못한다면, 꼬리를 잘라야함으로, list.remove(0)을 함
만약 map에 시간에 따른 방향 전환이 있다면, 방향 전환을 해준다. (왼쪽 오른쪽 90도 이기에, 왼쪽 -1 오른쪽 +1 % 4 해서 판단)
다음 순회 때, 뱀이 자신의 몸을 먹는지 확인한다 (리스트를 순회하면서, 움직인 자리와 몸통이 만나게 되면 return)
벽을 만나면 return

### pseudo code
```java
while(true) {
    time++;

    int ny = y + dy[dir];
    int nx = x + dx[dir];

    if (ny < 0 || ny >= N || nx < 0 || nx >= N) {
        return ;
    }

    for (int i = 0; i < snake.size(); ++i) {
        Pos p = snake.get(i);
        if (ny == p.y && nx == p.x) {
            return;
        }
    }

    if (map[ny][nx] == 1) {
        map[ny][nx] = 0;
        snake.add(new Pos(ny, nx));
    } else {
        snake.add(new Pos(ny, nx));
        snake.remove(0);
    }

    if (info.containsKey(time)) {
        if (info.get(time).equals("D")) {
            dir = (dir + 1) % 4;
        } else {
            dir = (dir + 3) % 4;
        }
    }
    y = ny;
    x = nx;
}
```

### 메모리 및 시간
- 14848kb
- 144ms

## 테트로미노_14500
### 소요 시간
1시간

### 간단 풀이 방법
각 모양에 대한 dy, dx값을 구해서 map을 순회하면서 각 모양을 대입하면서 모양이 map 안에 있는지 확인한 후 있다면, 각 모양의 합을 구한다.
max 갱신

### pseudo code
```java
private static void solve() {
    for (int i = 0; i < N; ++i) {
        for (int j = 0; j < M; ++j) {
            getOne(i, j);
            getTwo(i, j);
            getThree(i, j);
            getFour(i, j);
            getFive(i, j);
        }
    }
}

private static void getFive(int y, int x) {
    // ㅜ 모양
    sum(y, x, new int[]{0, 0, 0, 1}, new int[]{0, 1, 2, 1});
    // ㅓ 오양
    sum(y, x, new int[]{0, 1, 2, 1}, new int[]{0, 0, 0, -1});
    // ㅗ 모양
    sum(y, x, new int[]{0, 1, 1, 1}, new int[]{0, 0, 1, -1});
    // ㅏ 모양
    sum(y, x, new int[]{0, 1, 2, 1}, new int[]{0, 0, 0, 1});
}

//뱀 모양일 경우
private static void getFour(int y, int x) {
    //뱀 모양일 경우
    sum(y, x, new int[]{0, 1, 1, 2}, new int[]{0, 0, 1, 1});
    // 시계방향 90도 회전
    sum(y, x, new int[]{0, 0, 1, 1}, new int[]{0, 1, 0, -1});
    //뱀 비대칭
    sum(y, x, new int[]{0, 1, 1, 2}, new int[]{0, 0, -1, -1});
    // 시계방향 90도 회전
    sum(y, x, new int[]{0, 0, 1, 1}, new int[]{0, -1, 0, 1});

}

//ㄴ자 모양 경우
private static void getThree(int y, int x) {

    //ㄴ 자 모양일 경우
    sum(y, x, new int[]{0, 1, 2, 2}, new int[]{0, 0, 0, 1});
    //위 모양을 시계 방향으로 90도 돌린 경우
    sum(y, x, new int[]{0, 0, 0, 1}, new int[]{0, 1, 2, 0});
    //위 모양을 시계 방향을 90도 돌린 경우
    sum(y, x, new int[]{0, 0, 1, 2}, new int[]{0, 1, 1, 1});
    //위 모양을 시계 방향을 90도 돌린 경우
    sum(y, x, new int[]{0, 1, 1, 1}, new int[]{0, 0, -1, -2});
    //ㄴ자 대칭 모양
    sum(y, x, new int[]{0, 1, 2, 2}, new int[]{0, 0, 0, -1});
    //위 모양을 시계 방향을 90도 돌린 경우
    sum(y, x, new int[]{0, 1, 1, 1}, new int[]{0, 0, 1, 2});
    //위 모양을 시계 방향을 90도 돌린 경우
    sum(y, x, new int[]{0, 1, 2, 0}, new int[]{0, 0, 0, 1});
    //위 모양을 시계 방향을 90도 돌린 경우
    sum(y, x, new int[]{0, 1, 0, 0}, new int[]{0, 0, -1, -2});
}

//정사각형 경우
private static void getTwo(int y, int x) {
    int[] dy = {0, 0, 1, 1};
    int[] dx = {0, 1, 0, 1};

    sum(y, x, dy, dx);
}

//긴 막대기 경우
private static void getOne(int y, int x) {
    int[] dy = {0, 0, 0, 0};
    int[] dx = {0, 1, 2, 3};

    sum(y, x, dy, dx);
    sum(y, x, dx, dy);
}

private static void sum(int y, int x, int[] dy, int[] dx) {
    int sum = 0;
    for (int i = 0; i < 4; ++i) {
        int ny = y + dy[i];
        int nx = x + dx[i];

        if (ny < 0 || ny >= N || nx < 0 || nx >= M) {
            return;
        }
        sum += map[ny][nx];
    }
    max = Math.max(sum, max);
}
```

### 메모리 및 시간
- 261232kb
- 796ms (아마 dy, dx를 그냥 다 구현해서 그런듯... 싶다)