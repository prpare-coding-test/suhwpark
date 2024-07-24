## 덱_10866
### 소요 시간
30분

### 간단 풀이 방법
deque에 구현되어있는 것을 사용해서 구현!
int[] 배열로 한다면
오른쪽 방향으로 이동시
rear = (rear + 1) % array.length;
front = (front + 1) % array.length;
왼쪽 방향을 이동시
rear = (rear - 1 + array.length) % array.length;
front = (front - 1 + array.length) % array.length; 로 구현하면된다!
전에 구현해봤기에~ 자바 deque class를 안써봐서 써봤습니당

### 메모리 및 시간
- 19184kb
- 176ms

## 회전하는_큐_1021
### 소요 시간
30분

### 간단 풀이 방법
중간 인덱스와 찾아야할 값의 인덱스를 비교한 후,
중앙 보다 타겟 인덱스가 작을 경우 타겟 인덱스까지 이동
전체 크기 - 타켓 인덱스 만큼 이동
그리고 poll();
indexOf()을 사용하기 위해, LinkedList 사용

### pseudo code
```java
for (int v : values) {
    int half;
    int target = dq.indexOf(v);
    if (dq.size() % 2 == 0) {
        half = dq.size() / 2 - 1;
    } else {
        half = dq.size() / 2;
    }

    if (target <= half) {
        for (int i = 0; i < target; ++i) {
            int temp = dq.pollFirst();
            dq.addLast(temp);
            answer += 1;
        }
    } else {
        for (int i = 0; i < dq.size() - target; ++i) {
            int temp = dq.pollLast();
            dq.addFirst(temp);
            answer += 1;
        }
    }
    dq.pollFirst();
}
```

### 메모리 및 시간
- 14348kb
- 128ms

## AC_5430
### 소요시간
1시간 (list reverse하다....)

### 간단 풀이 방법
md를 실행할 때, R이 나오게되면, 덱의 특성상 pollLast()를 사용해주면 뒤에서 부터 뺄 수 있기에, 덱을 역 전환 하지 않고 사용할 수 있다
위의 개념을 생각지 못하게 풀어서, 처음에는 뒤집어 주다가 시간 초과가 났다
D가 나오면, poll을 하는데, 만약 요소가 없다면 error를 밷는다
모든 cmd를 검사하고 남은 덱의 요소들을 toString() sb에 담아 준다 (역방향이면 pollLast, 정방향이면 pollFirst)

### pseudo code
```java
if (dq.size() > 0) {
    if (isReverse) {
        sb.append(dq.pollLast());

        while (!dq.isEmpty()) {
            sb.append(',').append(dq.pollLast());
        }
    } else {
        sb.append(dq.pollFirst());
        while (!dq.isEmpty()) {
            sb.append(',').append(dq.pollFirst());
        }
    }
}
```

### 메모리 및 시간
- 83924kb
- 768ms