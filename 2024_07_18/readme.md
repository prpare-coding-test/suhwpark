## 숨바꼭질_1697
### 소요시간
10분

### 간단 풀이 방법
- bfs를 사용하여, 짝궁에게 닿을 시간을 구한다.
- x - 1, x + 1, 2 * x로 이동 위치를 판단하고, 범위 안에 들어가 있고 방문하지 않은 곳이면 Queue에 넣어 또 다음 위치를 판단한다.
- 가장 빠르게 M에 도착했을 때 시간을 출력하고 Queue 순회를 멈춘다.(어짜피 가장 빨리 닿기 떄문)

### pseudo code
```java
private static void bfs(int n) {
    Queue<Pos> q = new LinkedList<>();
    visited[n] = true;
    q.add(new Pos(n, 0));

    while(!q.isEmpty()) {
        Pos p = q.poll();

        if (p.index == M) {
            time = p.time;
            break ;
        }
        int before = p.index - 1;
        int next = p.index + 1;
        int space = p.index * 2;

        if (isInRange(before) && !visited[before]) {
            visited[before] = true;
            q.add(new Pos(before, p.time + 1));
        }
        if (isInRange(next) && !visited[next]) {
            visited[next] = true;
            q.add(new Pos(next, p.time + 1));
        }
        if (isInRange(space) && !visited[space]) {
            visited[space] = true;
            q.add(new Pos(space, p.time + 1));
        }
    }
}
```

### 메모리 및 시간
- 18892kb
- 156ms

## 유기농_배추추_1012
### 소요시간
20분

### 간단 풀이 방법
- bfs를 사용하야, 인근의 배추의 영역을 구한다.
- 방문배열을 사용하여, 방문한 배추면 방문하지 않는다.
- 맵을 순회하면서, 방문하지 않은 배추의 인근 배추들을 방문하여 지렁이가 필요한 갯수 도출

### pseudo code
```java
private static void bfs(int y, int x) {
    Queue<Pos> q = new LinkedList<>();
    q.add(new Pos(y, x));

    visited[y][x] = true;

    while(!q.isEmpty()) {
        Pos p = q.poll();

        for (int i = 0; i < 4; ++i) {
            int ny = p.y + dy[i];
            int nx = p.x + dx[i];

            if (!isInRange(ny, nx) || map[ny][nx] != 1 || visited[ny][nx]) {
                continue;
            }
            q.add(new Pos(ny, nx));
            visited[ny][nx] = true;
        }
    }
}
```

### 메모리 및 시간
- 16028kb
- 164ms

## 적록_약색_10026
### 소요시간
40분 (package까지 붙여서 실행했더니... 런타임;;;)

### 간단 풀이 방법
- 색약 지도와 일반 지도를 만든다 (G -> R)
- 각각 bfs를 통해서, 색갈의 영역을 구한다.
- 각 지도에 방문 배열을 만들고, 방문하지 않은 색갈을 기준으로 4방향 구역을 찾는다.

### pseudo code
```java
private static void bfs(int y, int x, char c, boolean isNormal) {
    Queue<Pos> q = new LinkedList<>();
    q.add(new Pos(y, x));

    if (!isNormal) {
        abnormalVisited[y][x] = true;
    } else {
        normalVisited[y][x] = true;
    }

    while (!q.isEmpty()) {
        Pos p = q.poll();

        for (int i = 0; i < 4; ++i) {
            int ny = p.y + dy[i];
            int nx = p.x + dx[i];

            if (!isNormal) {
                if (!isInRange(ny, nx) || abnormalVisited[ny][nx] || abnormalMap[ny][nx] != c) {
                    continue;
                }
                abnormalVisited[ny][nx] = true;
                q.add(new Pos(ny, nx));
            } else {
                if (!isInRange(ny, nx) || normalVisited[ny][nx] || normalMap[ny][nx] != c) {
                    continue;
                }
                normalVisited[ny][nx] = true;
                q.add(new Pos(ny, nx));
            }

        }
    }
}
```

### 메모리 및 시간
- 15212kb
- 148ms