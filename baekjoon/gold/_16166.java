package baekjoon;

import java.io.*;
import java.util.*;
/*
문제: 16166 서울의 지하철
난이도: Gold 5
알고리즘: 구현, 자료 구조, 그래프, bfs, 해시를 이용한 집합과 맵
풀이 방법: 역 번호가 2^32-1 이하의 정수로 무작위 입력되어 bfs 사용 시 visited 처리를 어떻게 해주어야 할 지가 가장
        관건이었다. 역의 개수는 총 호선의 개수 최대 10 x 각 호선의 최대 역 개수 10 해서 100까지 역이 존재한다.
        따라서 입력받는 역 번호를 0~99번으로 치환하여 저장해준다. 이렇게 해주면 visited 배열을 N * 100 크기의 2차원으로
        메모리 초과없이 선언해줄 수 있다.
 */
public class _16166 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer>[] stations = new ArrayList[100]; // 역 번호에 해당하는 호선 저장
        ArrayList<Integer>[] lines = new ArrayList[N+1]; // 각 호선 별로 포함하는 역 번호 저장
        Map<Integer, Integer> map = new HashMap<>(); // 입력받은 역 번호를 0~99번으로 치환하여 저장

        for(int i=0; i<100; i++) {
            stations[i] = new ArrayList<>();
        }

        int num = 0; // 원래의 역 번호 -> 0~99번으로 바꾸어줌
        for(int i=1; i<=N; i++) {
            String[] line = br.readLine().split(" ");
            int K = Integer.parseInt(line[0]);
            lines[i] = new ArrayList<>();
            for(int j=1; j<=K; j++) {
                int station = Integer.parseInt(line[j]);
                if(!map.containsKey(station)) {
                    map.put(station, num);
                    stations[num].add(i);
                    lines[i].add(num);
                    num++;
                } else {
                    stations[map.get(station)].add(i);
                    lines[i].add(map.get(station));
                }
            }
        }
        int M = Integer.parseInt(br.readLine());

        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[N+1][100];
        for(Integer line : stations[map.get(0)]) {
            q.add(new int[]{0, map.get(0), line}); // 환승횟수, 역 번호, 호선
            visited[line][map.get(0)] = true;
        }
        boolean isArrived = false;
        int answer = 0;
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int cnt = cur[0];
            int curStation = cur[1];
            int curLine = cur[2];
            // 도착역에 도착했으면
            if(curStation == map.get(M)) {
                isArrived = true;
                answer = cnt;
                break;
            }
            // 같은 라인에 있는 역은 환승 안하고 큐에 넣기
            for(Integer nextStation : lines[curLine]) {
                if(!visited[curLine][nextStation]) {
                    visited[curLine][nextStation] = true;
                    q.add(new int[]{cnt, nextStation, curLine});
                }
            }
            // 현재 역의 다른 호선으로 환승하여 큐에 넣기
            for(Integer nextLine : stations[curStation]) {
                if(!visited[nextLine][curStation]){
                    visited[nextLine][curStation] = true;
                    q.add(new int[]{cnt+1, curStation, nextLine});
                }
            }
        }
        if(!isArrived) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }
}
