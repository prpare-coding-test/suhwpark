## N과 M 15649
### 소요시간
20분

### 간단 풀이 방법
- 1부터 N 까지의 M개 배열을 가능한 조합의 수를 구한다.
- 1부터 4까지라면, 1 2, 1 3, 1 4....의 모든 경우의 수를 정해야하기 때문에 dfs()의 완전탐색을 사용한다
- 또한 첫번쨰의 원소를 가지고 조합을 구했다면, 중복은 허용하지 않기 때문에 백트래킹 개념을 사용하여, visited 배열을 만들어 중복을 허용하지 않는다
- M의 숫자만큼 depth가 늘어난다면, M개만큼의 조합을 찾았기에 StringBuilder에 넣어준다.
- 한번에 정답을 출력한다.

### pseudo code
```java
StringBuilder sb = new StringBuilder();
if (depth == M) {
    for (int val : result) {
        sb.append(val).append(' ');
    }
    sb.append('\n');
    answer.add(sb.toString());
    return;
}
for (int i = 0; i < N; i++) {
    if (!visited[i]) {
        visited[i] = true;
        result[depth] = i + 1;
        dfs(depth + 1);
        visited[i] = false;
    }
}
```

### 메모리 및 시간
- 33012kb
- 260ms

## N Queen
### 소요 시간
10분 -> 수도없이 풀어본 문제... 다시 풀면 못풀줄 알았는데 역시 기출을 돌리는 게 좋다!

### 간단 풀이 방법
- 체스판에서 퀸이 서로 공격할 수 없게 놓는 경우의 수를 구하는 문제
- 즉, 모든 경우를 다 확인해야한다
- 2차원 배열로 풀수도 있지만.  N * N 의 체스판이기 때문에, 일차원 배열로 board[i] 값을 x값, index를 y값으로 확인하여 퀸을 놓을 수 있는 지 없는지를 판단한다.
- 체스판을 순회하면서 모든 자리에 퀸이 들어갈 수 있는지 확인하고, 있다면 true를 반환하고 다음 depth로 넘어간다.
- 조합아는 경우의 수가 N과 같다면 모든 경우의 수를 확인한것이기에 퀸을 놓고 다음 경우의 수를 계산한다.

### pseudo code
```java
if (depth == N) {
    answer += 1;
    return ;
}

for (int i = 0; i < N; i++) {
    board[depth] = i;
    if (isInBoard(depth)) {
        dfs(depth + 1);
    }
}

// 범위 확인 method

for (int i = 0; i < col; i++) {
    // queen이 대각선이나, 상 하 방면에 있다면 놓을 수 없다.
    if (board[i] == board[col] || col - i == Math.abs(board[col] - board[i])) {
        return false;
    }
}
return true;
```

### 메모리 및 시간
- 14472kb
- 5092ms