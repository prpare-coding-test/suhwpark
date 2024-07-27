## 뿌요_뿌요_11559
### 소요 시간
1시간

### 간단 풀이 방식
- bfs()을 사용하여 푸는 문제
- map 안에서 '.'아닌 요소가 등장했을 때, 4방향을 연결되어있는 요소들을 확인한다
- 요소의 갯수가 4개 이상이라면, 요소들을 pop 해준다 ('.'으로 변경)
- 4개 미만이라면, 다음 pop을 하지 않아도 되기에 플레그를 세워준다
- pop한 요소들을 반영하기 위해서, 행 기준으로 순회를 하면서 map의 가장 밑 부분부터 확인한다
- 행 기준 열에 모든 요소들을 큐에 넣는다. 
- 큐를 순회하면서, map 밑에 부분부터 요소가 없다면 큐에서 꺼내 채워준다.
- 변경된 맵으로 연속 pop의 플레그가 세워질 때까지 진행한다

### pseudo code
```java
// 연속 pop의 갯수를 구하는 코드
while (true) {
    isPuyo = false;

    search();
    changeMap();
    if (!isPuyo) {
        break;
    }
    answer += 1;
}

//4개의 요소를 찾는 코드
while (!q.isEmpty()) {
    Pos p = q.poll();

    for (int i = 0; i < 4; ++i) {
        int ny = p.y + dy[i];
        int nx = p.x + dx[i];

        if (ny < 0 || ny >= 12 || nx < 0 || nx >= 6 || visited[ny][nx]) {
            continue;
        }
        if (map[ny][nx] == p.c) {
            visited[ny][nx] = true;
            q.add(new Pos(ny, nx, p.c));
            cnt.add(new Pos(ny, nx, p.c));
        }
    }
}

//map 변경 코드
for (int i = 11; i >= 0; --i) {
    if (map[i][x] != '.') {
        q.add(new Pos(i, x, map[i][x]));
        map[i][x] = '.';
    }
}
int idx = 11;
while (!q.isEmpty()) {
    Pos p = q.poll();

    map[idx][p.x] = p.c;
    idx--;
}
```

### 메모리 및 소요 시간
- 14288kb
- 128ms

## 톱니바퀴_14891
### 소요시간
1시간

### 간단 풀이 방법
- 주어진 톱니 바퀴를 회전 시키는 문제. 따른 수식 보다 구현 문제
- N번째 톱니 바퀴가 회전을 하게 되면, 회전에 의한 연쇄 작용을 고려해 구현

- 1번 톱니의 index 2 와 2번 톱니의 index 6
- 2번 톱니의 index 2 와 3번 톱니의 index 6
- 3번 톱니의 index 2 와 4번 톱니의 index 6
- 위의 경우의 수를 N번째가 회전하는 경우에 있어, 각각의 상황에서 적용하여 구현
- 회전 후 또 회전하면, N극과 S극의 상관관계가 변하기에 한번에 구해 놓고 회전을 적용한다
- 회전 후 점수를 매겨 출력!

### pseudo code
````java
// 시계 방향 회전 할 때
int end = gear[gear.length 1];
int [] copy
for (int i = 0; i < 7; i++) {
   copy[i + 1] = gear[i];
}
copy[0] = end;

// 시계 반대 방향 회전 할 때
int start = gear[0];
int copy[];
for (int i = 0; i < 7; i++) {
   copy[i] = gear[i + 1];
}
copy[copy.length - 1] = start;

//연쇄 작용 구하기
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
````

### 메모리 및 시간
- 14432kb
- 124ms

## 토마토_7569
### 소요시간
20분(풀어본 문제고,,,, 가장 쉬웠음!)

### 간단 풀이 방법
- 전에 풀었던 토마토 문제에서 높이만 추가되면 되기에 3차원 배열을 사용
- 방향도 h에 관한 dir만 추가해서 6방향을 보면 된다
- 익은 토마토를 큐에 넣고, bfs()을 통해서, 토마도 익음을 전이하면 끝
- 상자의 모든 토마토가 익지 않으면 -1, 다 익었다면 days, 익을게 없다면 0

### pseudo code
```java
while(!q.isEmpty()) {
    Pos p = q.poll();
    day = p.d;

    for (int i = 0; i < 6; ++i) {
        int nh = p.h + dh[i];
        int ny = p.y + dy[i];
        int nx = p.x + dx[i];


        if (nh < 0 || nh >= H || ny < 0 || ny >= N || nx < 0 || nx >= M || box[nh][ny][nx] != 0) {
            continue;
        }
        box[nh][ny][nx] = 1;
        q.add(new Pos(nh, ny, nx, day + 1));
    }
}
```

### 메모리 및 시간
- 129240kb
- 704ms