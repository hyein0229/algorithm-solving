package baekjoon;

import java.io.*;
import java.util.*;
/*
문제: 23294 웹 브라우저 1
난이도: Gold 4
알고리즘: 구현, 자료 구조, 시뮬레이션, 덱
풀이 방법: 뒤로 가기 공간의 경우 가장 최근의 페이지와 가장 오래된 페이지가 동시에 필요하므로 덱을 사용하여 앞뒤로 데이터를
        뽑을 수 있도록 해야 한다. 따라서, 캐시 공간을 모두 자료 구조로 덱을 사용하여 구현하였다.
        각 실행 명령에 따라 알맞게 덱에 데이터를 삽입하고 빼주면 된다.
        압축 실행의 경우엔 연속으로 중복되는 것은 최근 것만 남겨두어야 하므로 압축된 상태를 저장할 수 있는 새로운 리스트를 생성하여
        뒤로 가기 덱의 앞에서부터 빼면서 이전 페이지와 같다면 넣지 않고 이전과 달라질 때 이전의 데이터를 리스트에 삽입해준다.
        이전 것과 달라지면 이전 것이 연속된 것 중 가장 최근의 것이 되므로 남겨주어야 한다.
 */
public class _23294 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int N = Integer.parseInt(line[0]);
        int Q = Integer.parseInt(line[1]);
        int C = Integer.parseInt(line[2]);
        int[] CAP = new int[N+1];
        int totalSize = 0;
        int bSize = 0; // 뒤로 가기 페이지 크기
        int fSize = 0; // 앞으로 가기 페이지 크기

        line = br.readLine().split(" ");
        for(int i=1; i<=N; i++) {
            CAP[i] = Integer.parseInt(line[i-1]);
        }

        int curPage = -1; // 현재 접속 중인 페이지
        Deque<Integer> backward = new LinkedList<>();
        Deque<Integer> frontward = new LinkedList<>();

        for(int i=0; i<Q; i++) {
            line = br.readLine().split(" ");

            if(line[0].equals("B")) { // 뒤로 가기
                if(!backward.isEmpty()) {
                    frontward.add(curPage);
                    fSize += CAP[curPage];
                    curPage = backward.pollLast();
                    bSize -= CAP[curPage];
                }

            } else if(line[0].equals("F")) { // 앞으로 가기
                if(!frontward.isEmpty()) {
                    backward.add(curPage);
                    bSize += CAP[curPage];
                    curPage = frontward.pollLast();
                    fSize -= CAP[curPage];
                }

            } else if(line[0].equals("A")) { // 윕페이지 접속
                fSize = 0;
                frontward.clear();

                if(curPage != -1) { // 처음 웹페이지 접속이 아니면
                    backward.add(curPage);
                    bSize += CAP[curPage];
                    curPage = Integer.parseInt(line[1]); // 접속할 페이지 갱신
                    totalSize = bSize + CAP[curPage];

                    while(totalSize > C) {
                        int oldPage = backward.pollFirst();
                        bSize -= CAP[oldPage];
                        totalSize -= CAP[oldPage];
                    }
                } else {
                    curPage = Integer.parseInt(line[1]); // 접속
                }

            } else { // 압축 실행
                if(backward.size() >= 2) {
                    List<Integer> compressed = new ArrayList<>();
                    int before = backward.pollFirst();
                    int cur = -1;
                    while(!backward.isEmpty()) {
                        cur = backward.pollFirst();
                        if(before != cur) { // 페이지가 달라질 때 삽입
                            compressed.add(before);
                        }
                        before = cur;
                    }
                    compressed.add(cur);

                    bSize = 0;
                    for(Integer page : compressed) {
                        bSize += CAP[page];
                        backward.add(page);
                    }
                }
            }
        }
        System.out.println(curPage);
        print(backward);
        print(frontward);
    }

    public static void print(Deque<Integer> deque) {
        if(deque.isEmpty()) {
            System.out.println(-1);
        } else {
            while(!deque.isEmpty()) {
                System.out.print(deque.pollLast() + " ");
            }
            System.out.println();
        }
    }
}
