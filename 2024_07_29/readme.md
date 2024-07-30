## 주사위_굴리기_14499
### 소요 시간
2시간 (주사위 돌리는 규칙 찾는데 30분,, 디버깅 1시간 반...)

### 간단 풀이 방법
각 면마다 움직이는 방향의 규칙을 알아내서 사용
top이 기준이라면
동, 서 : top의 윗면과 아랫면은 고정 -> 나머지의 값들은 뱡향에 따라 한칸씩 이동
남, 북 : top의 오른쪽과 왼쪽면은 고정 -> 나머지의 값들은 뱡향에 따라 한칸씩 이동
map 안에서 주사위를 굴릴 수 있다면 실행.
만약 주사위 밑면과 닿은 map의 요소가 0이라면 map요소가 밑면이 된다
그렇지 않다면, 주사위 밑면의 값이 map의 요소가 된다. map의 요소는 0
글고.... 동 서 남 북의 dir 배열을 잘 선언해야한다.... 그거 때매 1시간 반 노가다..

### pseudo code
```java
int tmp = dice[3];
if (dir == 1) {
    dice[3] = dice[2];
    dice[2] = dice[6];
    dice[6] = dice[4];
    dice[4] = tmp;
}
if (dir == 2) {
    dice[3] = dice[4];
    dice[4] = dice[6];
    dice[6] = dice[2];
    dice[2] = tmp;
}
if (dir == 3) {
    dice[3] = dice[5];
    dice[5] = dice[6];
    dice[6] = dice[1];
    dice[1] = tmp;
}
if (dir == 4) {
    dice[3] = dice[1];
    dice[1] = dice[6];
    dice[6] = dice[5];
    dice[5] = tmp;
}
```

### 메모리 및 시간
- 14456kb
- 136ms

## 트럭_13335
### 소요시간
30분

### 간단 풀이 방법
다리를 건널때, 다리에 다음 트럭을 놓을 수 있는지 판단하고
놓을 수 있다면, 다리에 놓고 그렇지 않으면, 0을 add하여 트럭을 움직인다.
시간이 지나면서, 트럭이 이동하고, 다리의 길이 만큼 움직였다면, 다리 무게에서 트럭 무게를 뺴준다
그러므로 다리의 길이를 0으로 add하기 때문에, 각각의 트럭이 움직이는 거리를 구할 수 있다.

### pseudo code
```java
while(!bridge.isEmpty()) {
    time += 1;
    bridgeWeight -= bridge.poll();
    if (!truck.isEmpty()) {
        int truckWeight = truck.peek();
        if (truckWeight + bridgeWeight <= L) {
            bridgeWeight += truckWeight;
            bridge.add(truck.poll());
        } else {
            bridge.add(0);
        }
    }
}
```

### 메모리 및 시간
- 15320kb
- 156ms

## 구슬찾기_2617
### 소요 시간
1시간 반

### 간단 풀이 방식
들어오는 input 값에 따라, 무거운 리스트와 가벼운 리스트에 추가한다 (인접 행렬로 만들기 위해)
그렇다면 하나의 구슬에 대한 무거운 구슬, 가벼운 구슬의 대한 정보를 찾을 수 있다
bfs를 사용하여, 자신보다 무거운 구슬의 갯수, 가벼운 구슬의 갯수를 구한다
그 값들이 N / 2 + 1 보다 클 경우 중간 구슬이 될 수 없기 때문에, 정답을 ++ 한다
글고 출력 끝

### psuedo code
```java
while (!q.isEmpty()) {
    int curr = q.poll();
    for (int value : list[curr]) {
        if (!visited[value]) {
            visited[value] = true;
            q.add(value);
            res++;
        }
    }
}
return res;
```
### 메모리 및 시간
- 17192kb
- 212ms