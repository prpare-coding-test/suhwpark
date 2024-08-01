## 트리_4803
### 소요시간
1시간 (트리 잘 모르곘다 증말, 구글링을 해버렸어요,,,, )

### 간단 풀이 방법
인접 행렬을 선언하여 노드들을 연결 시켜준다. ex) 3,4 : 3 -> 4, 4 -> 3
인접 행렬의 각 요소들을 순회하면서, 방문하지 않았다면 탐색을 시작한다 (dfs)
탐색 노드의 인접행렬을 순회한다
각 요소가 root와 같으면 다음 요소를 확인
요소를 방문했다면 false
방문하지 않았다면, 방문 표시한 후 다시 그 요소를 root로 탐색한다.
각 요소의 탐색이 실패하면 false
모든 요소에 대한 탐색이 끝나면 true -> 사이클이 없는 트리기 때문에 정답을 ++

### pseudo code
```java
private static boolean dfs(int root, int num) {
    for (int v : tree[num]) {
        if (v == root) {
            continue;
        }
        if (visited[v]) {
            return false;
        }
        visited[v] = true;
        
        if (!dfs(num, v)) {
            return false;
        }
    }
    return true;
}
```

### 메모리 및 시간
- 76708kb
- 572ms

## 트리와_쿼리_15681
### 소요시간
1시간

### 간단 풀이 방법
input값에 대한 인접행렬을 구성한다 ex) 3,4 : 3 -> 4, 4 -> 3
메모제이션을 하기때문에 dp 배열을 선언한다
루트 노드의 값으로 모든 노드를 탐색한다 (dfs)
각 노드를 탐색할 때, dp[root]의 값이 0이 아니면, 이미 방문하고 노드의 갯수를 알기 때문에 dp[root]를 반환
값이 0이라면 방문 표시를 해주고, 그 노드의 인접행렬을 순회하면서 다시 연결된 노드를 dfs로 탐색한다.
탐색이 끝나면 dp[root]의 값을 cnt로 설정한다.
쿼리로 들어온 값만큼 dp 배열의 값을 출력한다.

### pseudo code
```java
private static int dfs(int root) {
    if (dp[root] != 0) {
        return dp[root];
    }
    visited[root] = true;
    int cnt = 1;

    for (int value : tree[root]) {
        if (visited[value]) {
            continue;
        }
        cnt += dfs(value);
    }

    dp[root] = cnt;

    return dp[root];
}
```

### 메모리 및 시간
- 78324kb
- 884ms

## 듣보잡_1764
### 소요시간
10분

### 간단 풀이 방법
듣지 못한 명단을 dictionary로 만든다 -> HashMap 사용
보지 못한 명단을 순회하면서, dictionary에 key값으로 있는 지 확인하고 만약 있다면, 듣보잡 명단에 추가
사전순으로 정렬한 후 출력

### pseuco code
```java
for (int i = 0; i < M; ++i) {
    String name = br.readLine();
    if (dic.containsKey(name)) {
        list.add(name);
    }
}
```

### 메모리 및 시간
- 26196kb
- 320ms