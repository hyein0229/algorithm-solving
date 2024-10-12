package baekjoon;

import java.io.*;
import java.util.*;
/*
문제: 16934 게임 닉네임
난이도: Gold 3
알고리즘: 트라이
풀이 방법: 트라이를 살짝 변형하여 풀이
        "유저가 가입한 시점까지 같은 닉네임으로 가입한 사람의 수 x" 를 구하기 위해 각 트라이 Node 에 cnt 필드를 두어 insert 시 카운트 증가
        search 함수에서 현재 입력된 닉네임과 이미 있는 닉네임을 비교하여 가장 마지막으로 일치하는 인덱스 값을 리턴하도록 함.
        이때, 마지막 일치하는 인덱스 값이 현재 닉네임의 마지막 문자의 인덱스라면
        가능한 별칭이 없으므로 "유저가 가입한 시점까지 같은 닉네임으로 가입한 사람의 수 x" 도 같이 배열로 묶어 같이 리턴
 */
public class _16934 {

    static class Node {
        Map<Character, Node> child;
        boolean endOfWord;
        int cnt;

        public Node () {
            child = new HashMap<>();
            endOfWord = false;
            cnt = 0;
        }
    }

    static class Trie {
        Node root = new Node();

        public void insert(String str) {
            Node cur = root; // 루트 부터 시작

            for(int i=0; i<str.length(); i++) {
                if(!cur.child.containsKey(str.charAt(i))) {
                    cur.child.put(str.charAt(i), new Node());
                }
                cur = cur.child.get(str.charAt(i));
            }

            cur.endOfWord = true;
            cur.cnt++;
        }

        public int[] search(String str) {
            Node cur = root;
            int[] result = {-1, -1};
            for(int i=0; i<str.length(); i++) {
                if(!cur.child.containsKey(str.charAt(i))) {
                    result[0] = i-1;
                    break;
                }
                cur = cur.child.get(str.charAt(i));
                result[0] = i;
            }

            // 일치하는 닉네임 찾음
            if(result[0] == str.length()-1) {
                result[1] = cur.cnt+1;
            }
            return result;
        }
    }

    static int N;
    static Trie trie;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        trie = new Trie();

        for(int i=0; i<N; i++) {
            String nickName = br.readLine(); // 유저 닉네임
            int[] result = trie.search(nickName);

            // 별칭 출력
            if(result[0] < nickName.length()-1) {
                System.out.println(nickName.substring(0, result[0]+2));
            } else if(result[0] == nickName.length()-1) {
                if(result[1] == -1) {
                    System.out.println(nickName.substring(0, result[0]+2));
                } else if(result[1] >= 2){
                    System.out.println(nickName + result[1]);
                } else {
                    System.out.println(nickName);
                }
            }
            trie.insert(nickName);
        }
    }
}
