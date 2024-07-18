## 트리의_부모_찾기_11725
### 소요시간
45분

### 간단 풀이 방법
- 처음에 인접 행렬과 같이 배열로 불었다가 메모리 초과가 나서, list로 변경해서 해결함
- 인접 행렬과 같이 모든 노드를 순회하면서, 방문하지 않았을 경우 그 노드도 순회하며 N번째의 부모 노드를 기록한다.
- ex)
- 1 6
- 6 3
- 3 5
- 4 1
- 2 4
- 4 7
- 라고 할 때
- 1 -> 6, 4
- 6 -> 1, 3
- 3 -> 6, 5
- 5 -> 3
- 4 -> 1, 2, 7
- 2-> 4, 7
- 7 -> 4
- 그렇기에 순회하여 방문 후 2번째 부모의 노드를 출력한다면
- 4 6 1 3 1 4

### pseudo code
```java
private static void dfs(int index) {
    visited[index] = true;
    for (int v : graph.get(index)) {
        if (!visited[v]) {
            parents[v] = index;
            dfs(v);
        }
    }
}
```

### 메모리 및 시간
- 81928kb
- 1168ms

## 트리_순회_1991
### 소요시간
1시간 : 순회 방식을 아에 몰랐기 떄문에, 구글링 후 연습

### 간단 풀이 방법
- node를 생성
- tree를 직접 구현해서, input 값에 맞는 node를 순서대로 생성
- 전위, 후위, 중위 탐색에 관한 메서드를 구현

### pseudo code
```java
static class Tree {
    public Node root;

    public void createNode(char data, char leftData, char rightData) {
        if (root == null) {
            root = new Node(data);
            root.left = leftData != '.' ? new Node(leftData) : null;
            root.right = rightData != '.' ? new Node(rightData) : null;
        } else {
            searchNode(root, data, leftData, rightData);
        }
    }

    public void searchNode(Node node, char data, char leftData, char rightData) {
        if (node == null) {
            return;
        } else if (node.data == data) {
            node.left = leftData != '.' ? new Node(leftData) : null;
            node.right = rightData != '.' ? new Node(rightData) : null;
        } else {
            searchNode(node.left, data, leftData, rightData);  // 오른쪽 재귀 탐색
            searchNode(node.right, data, leftData, rightData);  // 왼쪽 재귀 탐색
        }
    }

    // 전위순회 Preorder : Root -> Left -> Right
    public void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.data);
            if (node.left != null) {
                preOrder(node.left);
            }
            if (node.right != null) {
                preOrder(node.right);
            }
        }
    }

    // 중위순회 Inorder : Left -> Root -> Right
    public void inOrder(Node node) {
        if (node != null) {
            if (node.left != null) {
                inOrder(node.left);
            }
            System.out.print(node.data);
            if (node.right != null) {
                inOrder(node.right);
            }
        }
    }

    // 후위순회 Postorder : Left -> Right -> Root
    public void postOrder(Node node) {
        if (node != null) {
            if (node.left != null) {
                postOrder(node.left);
            }
            if (node.right != null) {
                postOrder(node.right);
            }
            System.out.print(node.data);
        }
    }
}
```

### 메모리 및 시간
- 14356kb
- 108ms

## 로프_2217
### 소요시간
10분

### 간단 풀이 방법
- rope를 내림 차순으로 정렬한 후, 순회하면서 로프 갯수만큼 병렬적으로 무게를 할당한다.
- 그중 최대값을 구한다

### pseudo code
```java
for (int i = 0; i < N; i++) {
    answer = Math.max(answer, rope.get(i) * (i + 1));
}
```

## 보물_1026
### 소요시간
10분

### 간단 풀이 방법
- 주어진 A 배열을 내림 차순, B 배열을 오름 차순으로 정렬
- 점화식인 A[i] * B[i]를 진행
- 출력

### pseudo code
```java
A.sort((o1, o2) -> o1 - o2);
B.sort((o1, o2) -> o2 - o1);

int answer = 0;
for (int i = 0; i < N; i++) {
    answer += A.get(i) * B.get(i);
}
```

### 메모리 및 시간
- 14352kb
- 128ms