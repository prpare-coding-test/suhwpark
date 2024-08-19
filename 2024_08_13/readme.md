## 회장뽑기_2660
### 소요 시간
1시간 반

### 간단 풀이 방법
예시를 표로 표현하면, 아래와 같음
[*, 1, 2, 3, 4, 5]
[1, 0, 1, 2, 2, 3]
[2, 1, 0, 1, 1, 2]
[3, 2, 1, 0, 1, 1]
[4, 2, 1, 1, 0, 1]
[5, 3, 2, 1, 1, 0]

여기서 친구 점수를 정리하자면
[3, 2, 2, 2, 3]

플로이드 와샬 알고리즘으로 풀었다
-> 모든 지점에서 다른 모든 지점까지의 최단 경로를 모두 구해야 하는 경우에서 사용 가능
```java
if (arr[i][j] > arr[i][k] + arr[k][j]) {
    arr[i][j] = arr[i][k] + arr[k][j];
}
```
위의 점화식을 통해 최단 거리를 구한다

### pseudo code
```java
for (int k = 1; k <= N; ++k) {
    for (int i = 1; i <= N; ++i) {
        for (int j = 1; j <= N; ++j) {
            if (friend[i][j] > friend[i][k] + friend[k][j]) {
                friend[i][j] = friend[i][k] + friend[k][j];
            }
        }
    }
}

int max = INF;

int[] score = new int[N + 1];

for (int i = 1; i <= N; ++i) {
    int s = 0;
    for (int j = 1; j <= N; ++j) {
        if (friend[i][j] != 987654321) {
            s = Math.max(s, friend[i][j]);
        }
    }

    score[i] = s;
    max = Math.min(max, s);
}

```

### 메모리 및 시간
- 14120kb
- 100ms

## 멀티버스_2_18869
### 소요 시간
1시간

### 간단 풀이 방식
전체 우주 리스트 배열을 선언한 후, 각 행성을 정렬한후 정렬 전 행성을 인덱스로 set 한다
30 10 20 -> [2, 0, 1]
우주 리스트 배열을 순회하면서, 각 행성들의 순서를 비교하여 같다면 ans++
[2, 0, 1] == [2, 0 ,1] ans++

### pseudo code 
```java
for (int i = 0; i < M; ++i) {
    //현재 행성 리스트
    List<Integer> space = new ArrayList<>();
    //중복을 없애기 위한 리스트
    Set<Integer> set = new HashSet<>();
    // 행성의 순서를 저장하는 리스트 배열
    universe[i] = new ArrayList<>();

    input = br.readLine().split(" ");
    for (int j = 0; j < N; ++j) {
        int p = Integer.parseInt(input[j]);
        set.add(p);
        space.add(p);
    }

    //set을 리스트로 변환
    List<Integer> clone = new ArrayList<>(set);
    //정렬
    clone.sort((o1, o2) -> o1 - o2);

    for (int j = 0; j < N; ++j) {
        //collections에서 제공하는 이분탐색 사용
        int idx = Collections.binarySearch(clone, space.get(j));
        //행성의 j번째를 인덱스로 변환
        space.set(j, idx);
    }
    //인덱스 리스트 배열에 set
    universe[i] = space;
}
```

### 메모리 및 시간
- 348848kb
- 1636ms

## 세_용액_2473
### 소요 시간
45분

### 간단 풀이 방법
용액을 정렬한 후, 이분탐색을 통해서 3개를 뽑은후 0보다 클경우 right--, 0보다 작을 경우 left++
모든 용액을 순회한 후 pick 출력

### pseudo code
```java
private static void findLiquid(int index) {
    //인덱스 다음
    int left = index + 1;
    //끝 인덱스
    int right = N - 1;

    while (left < right) {
        long sum = liquid[left] + liquid[right] + liquid[index];
        long abs = Math.abs(sum);
        if (abs < max) {
            pick[0] = liquid[index];
            pick[1] = liquid[left];
            pick[2] = liquid[right];

            max = abs;
        }

        //0보다 크면, 큰 수를 옮긴다.
        if (sum > 0) {
            right--;
        } else {
            //작은 수를 옮긴다
            left++;
        }
    }
}
```

### 메모리 및 시간
- 16036kb
- 192ms