## 파일_합치기_3_13975
### 소요 시간
45분

### 간단 풀이 방식
우선순위 큐에 요소들을 넣는다. (오름차순 기준)
q의 요소가 1개 남을 때 까지, q의 2개 요소를 poll해서 더한 후 다시 add 한다
30 30 40 50 -> 40 50 60 -> 60 90 -> 150
최소값을 찾기 가능

### issue
요소들의 합을 구하기 때문에 int 범위를 넘어갈 수 있으니, long 으로 선언

### pseudo code
```java
private static long getSize() {
    long res = 0;
    while (pq.size() > 1) {
        long first = pq.poll();
        long second = pq.poll();
        res += first + second;
        pq.add(first + second);
    }
    return res;
}
```

### 메모리 및 시간
- 374572kb
- 2308ms (왤케 오래 걸리지...?)

## 공주님의_정원_2457
### 소요 시간
2시간...
우선순위 큐로 풀려고 하루 왠종일 하다가... 그냥 배열 순회하는 방식으로 고쳐서 해결...
우선순위 큐로 풀고싶다..

### 간단 풀이 방식
우선 각 input 값을 배열에 넣는다 (1 1 12 1 이라면 101 1201로 넣는다. 계산하기 편하게)
배열을 시작일 기준으로 오름차순으로 정렬한다. 만약 시작일이 같다면 마감일이 빠른 기준으로 정렬한다
시작일을 0으로 설정, 마감일을 1201로 설정

시작일이 1201이 넘지 않는 조건으로 순회한다
배열을 순회하면서 시작일을 갱신해 준다. 
설정 시작일 보다 느리다면 순회를 하지 않는다.
시작일 보다 빠른 날짜라면, 가장 긴 마감일을 max로 설정

순회를 다 돌았고, 꽃을 심을 수 있는 조건을 만족한다면, 시작일을 max 값으로 설정하고 다시 꽃을 심을 조건을 찾으러 간다

조건 순회가 끝났다면, max일이 1201 이상인지 확인한다
만약 max가 미만이라면, 주어진 조건에 꽃을 심을 수 없기 때문에 0 출력
이상이라면 꽃 개수 출력

### pseudo code
```java
private static void getFlowers() {
    int max = 0, ans = 0, idx = 0;
    int start = 301;

    while (start < 1201) {
        boolean isFind = false;

        for (int i = idx; i < N; ++i) {
            //시작일보다 늦게 시작한다면 의미 없음
            if (days[i].s > start) {
                break;
            }
            //가장 꽃이 지는 긴 날짜를 찾는다
            if (days[i].e > max) {
                max = days[i].e;
                isFind = true;
                idx = i + 1;
            }
        }
        //찾았다면 max 갱신하고 ans 증가
        if (isFind) {
            ans++;
            start = max;
        } else {
            break;
        }
    }

    // 12월01일 보다 꽃이 지속되지 않으면 조건에 불만족
    if (max < 1201) {
        sb.append(0).append('\n');
        return;
    }
    sb.append(ans).append('\n');
}
```

### 메모리 및 시간
- 61720kb
- 520ms

## 카드_합체_놀이_15903
### 소요시간
5분

### 간단 풀이 방식
우선 순위(오름차순 기준) 큐에 담긴 요소들을 2개 poll 한 후, 합친 결과를 다시 pq에 넣는다
이 과정에서 m만큼 합칠수 있다는 조건에 맞추어 cnt >= m 인 경우 중단한다.
오름차순으로 q에 담기기에, 최솟값을 구할 수 있다.

### issue
더한 후 최솟값을 구하는 것이기에 long 사용

### pseudo code
```java
private static void combine() {
    sb = new StringBuilder();
    int cnt = 0;
    while (pq.size() > 1 && cnt < m) {
        long c = pq.poll() + pq.poll();
        pq.add(c);
        pq.add(c);
        cnt++;
    }
    long ans = 0L;
    while (!pq.isEmpty()) {
        ans += pq.poll();
    }
    sb.append(ans).append('\n');
}
```

### 메모리 및 시간
- 15412kb
- 156ms