## ListOfUniqueNumbers_13144
### 소요 시간
1시간..

### 간단 풀이 방법
풀이 방식
ex) 1 2 3 1 2
중복된 숫자 없이 연속해서 뽑을 수 있는 경우의 수를 구하는 문제
차례대로 뽑는다고 하면 (시작 인덱스 l로 하자, 마지막 인덱스 r)
1 (l = 0, r = 0)
1 2 (l = 0, r = 1)
1 2 3 (l = 0, r = 2)
즉, 3가지 (r - l + 1)

2 (l = 1, r = 1)
2 3 (l = 1, r = 2)
2 3 1 (l = 1, r = 3)
즉, 3가지 (3 - 1 + 1)

3 (l = 2, r = 2)
3 1 (l = 2, r = 3)
3 1 2 (l = 2, r = 4)
즉, 3가지 4 - 2 + 1

1 (l = 3, r = 3)
1 2 (l = 3, r = 4)
즉, 2가지 4 - 3 + 1

2 (l = 4, r = 4)
즉, 1가지 4 - 4 + 1
총 열두 가지

위에서 본 결과 시작 인덱스와 끝 인덱스를 알면 중복없이 연속된 수를 고르는 경우의 수를 구할 수 있다
그렇기에 투포인터를 사용하여 끝 인덱스 - 시작 인덱스 + 1 로 하였다
또한 시작 인덱스가 갱신될 때, cnt배열을 초기화해주어야 다시 방문 가능하다
r = l로 초기화 하지 않는 이유는 시작 인덱스는 중복이 되어야 갱신되기 때문이다

### pseudo code
```java
while (l < N) {
    while (r < N && cnt[nums[r]] == 0) {
        cnt[nums[r]] += 1;
        r++;
    }
    ans += r - l;
    cnt[nums[l]] -= 1;
    l++;
}
```

### 메모리 및 시간
- 27296kb
- 320ms

## 가장_긴_짝수_연속한_부분_수열_22862
### 소요 시간
1시간 반

### 간단 풀이 방법
input 배열을 2로 나누어 짝수 홀수를 판단하기 위해 0 1 로 저장한다
K만큼 삭제할 수 있기 때문에 cnt 변수를 선언해서 홀수면 cnt++해준다
요소를 삭제하면서 가장 긴 짝수 배열 길이를 구한다
만약 시작 값이 홀수라면 cnt--하고 시작 인덱스를 증가시킨다

### pseudo code
```java
while (r < N) {
    if (cnt < K) {
        if (arr[r] != 0) {
            cnt++;
        }
        r++;
        maxLen = Math.max(maxLen, r - l - cnt);
    } else if (arr[r] == 0) {
        r++;
        maxLen = Math.max(maxLen, r - l - cnt);
    } else {
        // 카운트를 다 썻을 경우, 시작 지점이 홀수면 cnt--하고 시작 인덱스를 늘려준다
        if (arr[l] != 0) {
            cnt--;
        }
        l++;
    }
}
```

### 메모리 및 시간
- 109712kb
- 504ms

## 회전_초밥_2531
### 소요 시간
30분 (근데 시간이 너무 느리게 나온거 같다...)

### 간단 풀이 방법
모든 연속 초밥 선택 경우를 순회한다
set 자료형에 초밥을 넣는다 (이미 보너스 초밥은 들어가 있다)
그리고 가장 큰 경우의 수를 판단한다

### pseudo code
```java
for (int i = 0; i < N; ++i) {
    Set<Integer> s = new HashSet<>();
    s.add(c);

    for (int j = 0; j < k; ++j) {
        int index = i + j;
        if (i + j >= N) {
            index = i + j - N;
        }
        s.add(sushi[index]);
    }
    res = Math.max(res, s.size());
}
```

### issue
아니 !!
```java
int index = i + j;
if (i + j >= N) {
    index = i + j - N;
}
s.add(sushi[index]);
```
위의 방식이 sushi[(i + j) % N] 보다 빠르단다;;;
처음에 나누기 방식으로 하니... 시간초과... 이게 뭥미

### 메모리 및 시간
- 300036kb
- 2908ms (완전 간당간당이였던거같다.....ㅜ set은 안되나...)