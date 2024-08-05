## 가운데를_말해요_1655
### 소요 시간
1시간 + 구글링(1시간)....
새로운 유형보면 뻗는편..

### issue
- 첫 풀이 방식 : input 값을 받아서 리스트에 넣은 후 계속 정렬해주었다...
- 당연히 시간 초과  N (for순회) * N (정렬)
- 그래서 구글... 링...

### 간단 풀이 방식
가운데를 말하는게... 1 2 5 가 들어오면 2
근데 만약 1 2 5 -99 가 들어오게 되면... -99 1 2 5 의 중간 값은 1
처음에는 list에 요소가 추가 될때 마다 정렬해서, 판단했다. -> 무조건 시간 초과 N * N...
그래서 우선순위 큐를 두개 사용해서, 매번 중간 수를 갱신해 줌 -> 요소가 짝수일 때 고려 하지 않아도 됌
요소를 추가할 때마다, 각 큐의 최대 최소 값을 swap해준다
예시!! (두 힙의 크기를 비교하여 사이즈 같으면 최대 힙에 넣어준다, 최대힙의 값을 peek해서 출력하기 위해)
value 1, 5, 2, 10, -99, 7, 5
1 : [1] [] -> 1
사이즈가 같기 때문에 최대힙에 넣어준다.
2 : [1] [5] -> 1
사이즈가 다르기 때문에 최소힙에 넣어준다.
3 : [1, 2] [5] -> 2
사이즈가 같기 때문에 최대힙에 넣어준다.
4: [1, 2] [5, 10] -> 2
사이즈가 다르기 때문에 최소힙에 넣어준다.
5 : [-99, 1, 2] [5, 10] -> 2
6 : [-99, 1, 2] [5, 7, 10] -> 2
7 : [-99, 1, 2, 5] [5, 7, 10] -> 5
만약 maxQ.peek() > minQ.peek() 두 값을 swap해 주면서 중간 값을 갱신하게 된다.

### pseudo code
```java
for (int i = 0; i < N; ++i) {
    int num = Integer.parseInt(br.readLine());

    if (maxQ.size() == minQ.size()) {
        maxQ.add(num);
        if (!minQ.isEmpty() && minQ.peek() < maxQ.peek()) {
            minQ.add(maxQ.poll());
            maxQ.add(minQ.poll());
        }
    } else {
        minQ.add(num);
        if (maxQ.peek() > minQ.peek()) {
            minQ.add(maxQ.poll());
            maxQ.add(minQ.poll());
        }
    }
    sb.append(maxQ.peek()).append('\n');
}
```

### 메모리 및 시간
- 33556kb
- 436ms

## 최단경로_1753
### 소요 시간
1시간 + 30분(구글링.... 다익스트라 개념을 몰랐습니다...)

### 간단 풀이 방법
정점에서 간선으로 가기 위한 최솟값을 구하는 거기 때문에
pq의 기준을 cost로 잡고 가장 비용이 적게 드는 것부터 poll()한다
방문한 간선이 있다면, 방문하지 않는다 -> 중복으로 체크하지 않기 위해 -> 왜냐면 꺼낸 값이 가장 최소기 때문
시작 지점을  pq에 넣어준다
pq가 빌때까지, 방문하지 않은 간선일 경우, 그 간선을 정점으로 연결된 간선들을 순회하면서, 현재의 값과 cost 값이 Integer.MAX_VALUE보다 작다면
최솟값을 갱신! -> 다시 큐에 넣어 그 간선과 연결된 간선을 확인한다.

### pseudo code
```java
private static void getMinDistance() {
    pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
    boolean[] check = new boolean[V + 1];
    pq.add(new Pos(K, 0));
    dist[K] = 0;

    while (!pq.isEmpty()) {
        Pos pos = pq.poll();
        int cur = pos.v;

        if (check[cur]) {
            continue;
        }
        check[cur] = true;

        for (Pos p : graph[cur]) {
            if (dist[p.v] > dist[cur] + p.cost) {
                dist[p.v] = dist[cur] + p.cost;
                pq.add(new Pos(p.v, dist[p.v]));
            }
        }
    }
}
```

### 메모리 및 시간
- 129968kb
- 752ms

## 최소_비용_구하기_2_11779
### 소요 시간
1시간 45분 (마을 찾는데 한세월..)

### 간단 풀이 방식
최단 경로 문제와 같이 다익스트라 개념을 사용하여 최소값을 도출한다.
dist 배열에 최솟값을 갱신한다.
총 사용되는 비용을 출력한다.
route라는 배열에 버스의 출발 지점을 기록해놓는다.
최솟값을 구했을때, route에서 도착지점 기준으로 역조회해서 시작지점을 찾는다.

### pesudo code
```java
// 최솟값 갱신
private static void getMinDistance() {
    PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
    dist[s] = 0;
    pq.add(new Node(s, 0));

    while (!pq.isEmpty()) {
        Node cur = pq.poll();
        int now = cur.end;

        if (dist[now] < cur.cost) {
            continue;
        }
        for (Node n : graph[now]) {
            if (dist[n.end] > dist[now] + n.cost) {
                dist[n.end] = dist[now] + n.cost;
                pq.add(new Node(n.end, dist[n.end]));
                route[n.end] = now;
            }
        }
    }
}

// 지나간 마을 찾기
int cur = e;
while (cur != 0) {
    city.add(cur);
    cur = route[cur];
}
```

### 메모리 및 시간
- 60892kb
- 596ms