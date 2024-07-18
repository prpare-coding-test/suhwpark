## 회사에_있는_사람_7785
### 소요시간
20분

### 간단 풀이 방법
- enter, leave 상태 값에 따라서, Map<String, Boolean> key, value값으로 설정한다
- map의 key를 순회하면서, Boolean값이 true인 사람만 list에 추가
- list를 사전 역순으로 정렬
- 출력

### pseudo code
```java
for (int i = 0; i < N; ++i) {
    String[] line = bf.readLine().split(" ");
    if (line[1].equals("enter")) {
        company.put(line[0], true);
    } else {
        company.put(line[0], false);
    }
}

List<String> answer = new ArrayList<>();
for (String key : company.keySet()) {
    if (company.get(key)) {
        answer.add(key);
    }
}
```

### 메모리 및 시간
- 57888kb
- 888ms

## 나는야_포켓몬_마스터_이다솜_1620
### 소요시간
15분

### 간단 풀이 방법
- Map<String, Integer>과 Map<Integer, String>의 두가지의 dictionary를 생성
- 주어진 도감을 각 map에 추가
- 주어진 문제를 순회하면서, 숫자면 key, 이름이면 value를 출력한다

### pseudo code
```java
for (int i = 0; i < N; ++i) {
    String s = bf.readLine();
    dictionary.put(s, i + 1);
    numDictionary.put(i + 1, s);
}

StringBuilder sb = new StringBuilder();
for (int i = 0; i < M; ++i) {
    String question = bf.readLine();
    if (Character.isDigit(question.charAt(0))) {
        sb.append(numDictionary.get(Integer.parseInt(question))).append('\n');
    } else {
        sb.append(dictionary.get(question)).append('\n');
    }
}
```

### issue
- StringTokenizer를 남발하면.... Runtime Error가 날 수 도 있다..
### 메모리 및 소요시간
- 55252kb
- 568ms

## 수강_신청_13414
### 소요 시간
30분

### 간단 풀이 방법
- 주어진 학번을 LinkedHashSet에 넣어주면서, 만약 값이 이미 list에 있다면 remove하고 다시 add하여 순서를 변경한다
- 그리고 갯수 만큼 출력!

### pseudo code
```java
for (int i = 0; i < M; ++i) {
    String num = bf.readLine();
    if (list.contains(num)) {
        list.remove(num);
    }
    list.add(num);
}
```

### 메모리 및 시간
- 66256kb
- 780ms