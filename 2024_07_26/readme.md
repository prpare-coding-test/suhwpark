## 경사로_14890
### 소요시간
1시간

### 간단 풀이 방법
col, row 기준으로 경사로를 놓는다
visited 배열을 통해, 경사로를 놓을 수 있는지 확인한다. 만약 놓아져 있다면 false
현재 높이와 내 앞의 높이의 차이를 구하고 오르막인지, 내리막인지 확인한다
1. 오르막길
- 내 위치에서 L만큼 뒤로(--) 같은 높이의 길이 있어야한다
- 인덱스 범위 내에 있어야한다
- 경사로가 놓여져 있으면 안된다

2. 내리막길
- 내 위치에서 L만큼 앞으로(++) 같은 높이의 길이 있어아야한다
- 인덱스 범위 내에 있어야한다
- 경사로가 놓여져 있으면 안된다
만족할 경우 경사로를 놓는다

### pseudo code
```java
if (diff > 1 || diff < -1) {
    return false;
}
if (diff == -1) {
    for (int j = 0; j < L; ++j) {
        if (i - j < 0 || visited[i - j]) {
            return false;
        }
        if (map[i][index] != map[i - j][index]) {
            return false;
        }
        visited[i - j] = true;
    }
}
if (diff == 1) {
    for (int j = 1; j <= L; ++j) {
        if (i + j >= N || visited[i + j]) {
            return false;
        }
        if (map[i][index] - 1 != map[i + j][index]) {
            return false;
        }
        visited[i + j] = true;
    }
}
```

### 메모리 및 시간
- 15056kb
- 120ms

## 절대값_힙_11286
### 소요시간
30분

### 간단 풀이 방법
- 우선순위 큐를 사용한다 (기준은 절대값이 작은 것이 먼저 우선순위를 가지고, 만약 값이 같다면 비교해서 작은 값이 우선)
- cmd '0'이 나오면 q에서 꺼내서 출력한다.

### pseudo code
```java
new PriorityQueue<>((o1, o2) -> {
    int abs1 = Math.abs(o1);
    int abs2 = Math.abs(o2);
    if (abs1 > abs2) {
        return abs1 - abs2;
    } else if (abs1 == abs2) {
        return o1 - o2;
    } else {
        return -1;
    }
});
```

### 메모리 및 시간
- 25476kb
- 336ms

## 카드_정렬하기_1715
### 소요시간
30분

### 간단 풀이 방법
- 우선 순위 큐를 사용하여, 오름차순 순으로 우선권을 가지게 한다
- 큐의 요소가 1개 남을 때까지, answer에 카드의 수를 더해준다

### pseudo code
```java
while(q.size() > 1) {
    long a = q.poll();
    long b = q.poll();
    answer += a + b;
    q.add(a + b);
}
```

### 메모리 및 시간
- 25816kb
- 388ms