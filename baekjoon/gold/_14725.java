package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
/*
문제: 14725 개미굴
난이도: Gold 3
알고리즘: 트라이
풀이 방법: 전형적인 트라이 문제, 주의할 점은 하나의 굴에서 여러 개로 나뉠 때 먹이 종류별 '한번'만 나올 수 있다는 점
        이것 때문에 트라이를 어떻게 변형시켜야 할까 생각했는데 아니었다..
 */
public class _14725 {

    static class TrieNode {
        TreeMap<String, TrieNode> childNodes;

        public TrieNode() {
            childNodes = new TreeMap<>();
        }
    }

    static int N, K;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String[] line;

        TrieNode root = new TrieNode();
        TrieNode node;
        for(int i=0; i<N; i++) {
            line = br.readLine().split(" ");
            K = Integer.parseInt(line[0]);

            // 루트부터 시작
            node = root;
            for(int j=1; j<=K; j++) {
                if(node.childNodes.containsKey(line[j])) {
                    node = node.childNodes.get(line[j]);
                } else {
                    node.childNodes.put(line[j], new TrieNode());
                    node = node.childNodes.get(line[j]);
                }
            }
        }

        printAns(0, root);
    }

    public static void printAns(int level, TrieNode node) {
        for(String key : node.childNodes.keySet()) {
            for(int i=0; i<level; i++) { // 각 층 구분
                System.out.print("--");
            }
            System.out.println(key);
            printAns(level+1, node.childNodes.get(key));
        }
    }
}
