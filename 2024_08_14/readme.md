## 선_긋기_2170
### 소요 시간
45분

### 간단 풀이 방법
선을 그어야하는 좌표를 리스트에 담는다.
리스트를 시작 좌표가 작은 기준을 기준으로 sort(시작 값이 같다면, 끝 좌표가 작은 기준)
리스트를 순회하면서 선을 긋는다.
max(선의 끝 좌표)보다 현재 좌표 시작점 보다 작다면, 선의 길이를 구하고, min max 값을 갱신
max보다 현재 좌표 끝점 보다 작다면, max 값만 갱신
마지막에 길이를 더해준다.

### pseudo code
```java
Pos first = p.get(0);
int min = first.s;
int max = first.e;
p.remove(first);
int len = 0;
// 3가지
// 1. 선 안에 포함 될때 : len 변화 x
// 2. 현재 시작점이 이전 선에 포함 될때 : max값 변경
// 3. 현재 선과 이전 선이 겹치지 않는다면 : len += max - min
for (Pos pos : p) {
    if (max < pos.s) {
        len += max - min;
        min = pos.s;
        max = pos.e;
        continue;
    }
    if (pos.e > max) {
        max = pos.e;
    }
}
len += max - min;
```

### 메모리 및 시간
- 330368kb
- 1496ms

## 소문난_칠공주_1941
### 소요 시간
3시간(음... 칠공주 경우의 수를 판단하는게 너무 어렵)
bfs + dfs + 백트래킹 사용하는 건 알았는데... 억울...

### 간단 풀이 방법
5 * 5 맵이기 때문에 25로 순회를한다
start index 기준으로 5로 나눈 값과 나머지 값으로 visited 를 체크한다 (즉 칠공주를 나열하는 것)
7공주 경우의 수 설정하고 길이가 7이 된다면, bfs를 통해 이다연 파가 4명 이상인지 확인한다.

### pseudo code
```java
private static void prince(int start, int depth) {
    if (depth == 7) {
        if (bfs()) {
            ans++;
            return;
        }
        return;
    }

    for (int i = start; i < 25; ++i) {
        int y = i / 5;
        int x = i % 5;
        visited[y][x] = true;
        //인덱스 기준으로 경우의 수를 찾기 위한 재귀
        prince(i + 1, depth + 1);
        // 백트래킹
        visited[y][x] = false;
    }
}

private static boolean bfs() {
    boolean[][] copyVisited = new boolean[5][5];

    for (int i = 0; i < 5; ++i) {
        System.arraycopy(visited[i], 0, copyVisited[i], 0, 5);
    }
    int y = 0;
    int x = 0;
    //visited 시작점을 찾는다!
    for (int i = 0; i < 5; ++i) {
        for (int j = 0; j < 5; ++j) {
            if (copyVisited[i][j]) {
                y = i;
                x = j;
                break ;
            }
        }
    }

    Queue<Pos> q = new LinkedList<>();
    q.add(new Pos(y, x));

    int cnt = 0;
    int s = 0;

    while(!q.isEmpty()) {
        Pos p = q.poll();

        for (int i = 0; i < 4; ++i) {
            int ny = p.y + dy[i];
            int nx = p.x + dx[i];

            if (ny < 0 || ny >= 5 || nx < 0 || nx >= 5) {
                continue;
            }

            // dfs로 정한 7공주의 경로만을 탐색한다.
            if (copyVisited[ny][nx]) {
                // 이다연 파 검색
                if (map[ny][nx] == 'S') {
                    s++;
                }
                cnt++;
                q.add(new Pos(ny, nx));
                copyVisited[ny][nx] = false;
            }
        }
    }

    // 길이가 7이고, 이다연 파가 4명이 넘는다면 true
    if (cnt == 7 && s > 3) {
        return true;
    }
    return false;
}
```

### 메모리 및 시간
- 161828kb
- 344ms

## 멀티탭_스케줄링_1700
### 소요시간
2시간.... 

### 간단 풀이 방법
문제를 풀기 위한 조건
1. 이미 멀티탭에서 사용중 -> 현상태 유지
2. 앞으로 다시 쓰지 않는 전기 용품이 있다면, 전기 용품을 멀티탭에서 제고하고 새로운 전기 용품을 꽂는다
3. 현재 멀티탭에서 사용하고 있는 것들 중에 다시 쓰지 않을 전기 용품이 없다면, 지금 시점으로부터 가장 늦게 사용될 전기 용품을 제거하고 새로운 전기 용품을 꽂는다

### pseudo code
```java
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
```

### 메모리 및 시간
- 14308kb
- 128ms