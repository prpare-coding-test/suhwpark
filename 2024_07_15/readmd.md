## 연결_요소와_개수_11724
### 소요시간
1시간 (인접행렬의 개념을 몰라 찾고 이해하느라 좀 걸렸음.)

### 간단 풀이 방법
- visited 배열을 사용하여, 내가 방문한 노드인지 판단 후 방문하지 않았다면 진행한다
- 파라미터 column 을 visited 처리를 한다
- 그래프를 순회하면서, 내 column 에서 연결되어있는 row을 찾고, 그 row를 다시 column으로 하여 dfs를 진행
- 즉, 방문 여부를 체크하면서, 연결되어있는 모든 노드를 찾는 방식

### pseudo code
```java
private static void dfs(int index) {
    if (visited[index]) {
        return ;
    }
    visited[index] = true;
    for (int i = 1; i <= N; i++) {
        if (graph[index][i] == 1) {
            dfs(i);
        }
    }
}
```

### 메모리 및 시간
- 116996kb
- 472ms

## DFS_와_BFS_1260
### 소요시간
10분

### 간단 풀이 방법
dfs
- 방문 하지 않은 노드며, 연결되어있다면 그 노드로 부터 시작하는 다른 노드를 탐색하러 dfs를 실행한다
- 재귀를 통해 깊이 우선 탐색을 한다

bfs
- dfs와 같은 방법으로 방문 했는지에 대한 여부를 판단하고, 방문하지 않고, 연결되어있는 노드라면, 큐에 넣어준다
- 큐가 빌때 까지 연결되어있는 노드를 찾는다.
- 반복문을 통해 너비 우선 탐색을 한다

### pseudo code
```java
private static void dfs(int index) {
    if (visited[index]) {
        return ;
    }
    visited[index] = true;
    dfsAnswer.add(index);
    for (int i = 1; i <= N; i++) {
        if (graph[index][i] == 1) {
            dfs(i);
        }
    }
}

private static void bfs(int index) {
    Queue<Integer> q = new LinkedList<>();
    q.add(index);
    visited[index] = true;

    while(!q.isEmpty()) {
        int cur = q.poll();
        bfsAnswer.add(cur);
        for (int i = 1; i <= N; i++) {
            if (!visited[i] && graph[cur][i] == 1) {
                q.add(i);
                visited[i] = true;
            }
        }
    }
}
```

### 메모리 및 시간
- 116996kb
- 472ms

## 바이러스_2606
### 소요시간
10분

### 간단 풀이 방법
- 인접 행렬을 사용하여, 감염된 컴퓨터를 찾는다
- 자신과 연결되어있는 노드를 찾은 후 dfs 사용하여 또 연결되 노드들을 찾아간다.
- 연결 되어있음을 알기 위해 answer += 1을 해준다.
- 1번 컴퓨터을 제외한 감염된 컴퓨터를 찾아야하기에 answer - 1로 출력

### pseudo code
```java
private static void dfs(int index) {
    if (visited[index]) {
        return ;
    }
    visited[index] = true;
    answer += 1;
    for (int i = 1; i <= N; i++) {
        if (!visited[i] && graph[index][i] == 1) {
            dfs(i);
        }
    }
}
```

### 메모리 및 시간
- 14320kb
- 100ms
