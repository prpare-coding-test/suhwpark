## 안전지대_2468
### 소요 시간
30분 (아주 쉬웠어영)

### 간단 풀이 방식
맵을 구성하면서, 건물들의 높이를 list에 저장한다(중복 허용하지 않는다)
건물의 높이 list을 순회하면서, 각 높이 이상의 건물을 상하좌우로 구한다 (bfs로 탐색한다)
각 영역의 max 값을 도출한다.

### pseudo code
```java
private static void bfs(Pos pos, int h) {
    Queue<Pos> q = new LinkedList<>();
    q.add(pos);
    visited[pos.y][pos.x] = true;
    cnt++;
    while (!q.isEmpty()) {
        Pos p = q.poll();

        for (int i = 0; i < 4; ++i) {
            int ny = p.y + dy[i];
            int nx = p.x + dx[i];

            if (ny < 0 || ny >= N || nx < 0 || nx >= N || visited[ny][nx] || map[ny][nx] < h) {
                continue;
            }
            q.add(new Pos(ny, nx));
            visited[ny][nx] = true;
        }
    }
}
```

### 메모리 및 시간
- 57168kb
- 272ms

## 상범빌딩_6593
### 소요 시간
45분

### 간단 풀이 방식
기존 bfs 방식에서 위 아래의 방향이 추가되는 것뿐 다를께없다
'S'의 시작점을 시작으로 위 아래 동 서 남 북의 6방향을 확인하여 'E'에 도달하는 지 확인한다
만약 도달한다면, 시간을 그렇지 않다면 trapped를 반환한다

### pseudo code
```java
private static void bfs() {
    int time = 0;

    while(!q.isEmpty()) {
        Pos p = q.poll();
        time = p.t;

        if (building[p.h][p.y][p.x] == 'E') {
            sb.append("Escaped in ").append(time).append(" minute(s).").append('\n');
            return;
        }
        for (int i = 0; i < 6; ++i) {
            int nh = p.h + dh[i];
            int ny = p.y + dy[i];
            int nx = p.x + dx[i];

            if (nh < 0 || nh >= L || ny < 0 || ny >= R || nx < 0 || nx >= C ||
                    visited[nh][ny][nx] || building[nh][ny][nx] == '#') {
                continue;
            }
            q.add(new Pos(nh, ny, nx, time + 1));
            visited[nh][ny][nx] = true;
        }
    }
    sb.append("Trapped!").append('\n');
}
```

### 메모리 및 시간
- 18624kb
- 188ms

## 벽_부수고_이동하기_2206
### 소요 시간
30분

### 간단 풀이 방식
기존의 bfs 방식을 사용하지만, 벽을 부술 경우와 벽을 부수지 않은 경우 방문 배열을 나누어서 체크한다
도착 지점에 도달할 경우 가장 빠른 시간이기 때문에, 시간을 반환한다
그렇지 않다면 -1을 반환한다

### pseudo code
```java
private static void bfs() {
    int time;

    while (!q.isEmpty()) {
        Node n = q.poll();
        time = n.t;

        if (n.y == N - 1 && n.x == M - 1) {
            sb.append(time).append('\n');
            return;
        }
        for (int i = 0; i < 4; ++i) {
            int ny = n.y + dy[i];
            int nx = n.x + dx[i];

            if (ny < 0 || ny >= N || nx < 0 || nx >= M) {
                continue;
            }
            if (map[ny][nx] == 1) {
                if (n.skill == 1) {
                    q.add(new Node(ny, nx, time + 1, 0));
                    visited[ny][nx][1] = true;
                }
            } else {
                if (n.skill == 1 && !visited[ny][nx][0]) {
                    q.add(new Node(ny, nx, time + 1, n.skill));
                    visited[ny][nx][0] = true;
                } else if (n.skill == 0 && !visited[ny][nx][1])
                q.add(new Node(ny, nx, time + 1, n.skill));
                visited[ny][nx][1] = true;
            }
        }
    }
    sb.append(-1).append('\n');
}
```

### 메모리 및 시간
- 237444kb
- 1416ms~