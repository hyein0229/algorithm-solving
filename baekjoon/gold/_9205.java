package baekjoon;

import java.io.*;
import java.util.*;
/*
문제: 9205 맥주 마시면서 걸어가기
난이도: Gold 5
알고리즘: BFS
풀이 방법: 현재 위치에서 남은 맥주를 마시면서 도착할 수 있는 모든 위치를 큐에 삽입한다.
        모든 편의점 좌표를 순회하면서 현재 위치에서 갈 수 있는지를 확인하고 갈 수 있다면 해당 편의점을
        방문 체크하고 병 20개를 다시 채워 큐에 넣는다. 주의할 것은 입력되는 편의점 좌표가
        항상 현재 위치와 가까운 순으로 입력된다는 조건이 없기 때문에 항상 모든 편의점을 순회해야 한다.
        그 다음은 락 페스티벌까지 갈 수 있는지 확인하고 갈 수 있다면 큐에 집어 넣는다.
 */
public class _9205 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for(int i=0; i<t; i++) {
            int n = Integer.parseInt(br.readLine());
            int[][] arr = new int[n][2]; // 편의점
            // 상근이네 집 좌표
            String[] line = br.readLine().split(" ");
            int hx = Integer.parseInt(line[0]);
            int hy = Integer.parseInt(line[1]);
            // 편의점 좌표
            for(int j=0; j<n; j++) {
                line = br.readLine().split(" ");
                int x = Integer.parseInt(line[0]);
                int y = Integer.parseInt(line[1]);
                arr[j][0] = x;
                arr[j][1] = y;
            }
            // 락 페스티벌 좌표
            line = br.readLine().split(" ");
            int fx = Integer.parseInt(line[0]);
            int fy = Integer.parseInt(line[1]);

            boolean possible = false;
            boolean[] visited = new boolean[n];
            Queue<int[]> q = new LinkedList<>();
            q.add(new int[]{hx, hy, 20});

            while(!q.isEmpty()) {
                int[] cur = q.poll();
                int x = cur[0];
                int y = cur[1];
                int bottle = cur[2]; // 현재 남아있는 맥주병 개수
                if(x == fx && y == fy) { // 도착
                    possible = true;
                    break;
                }

                // 편의점까지 갈 수 있는지 확인
                for(int j=0; j<n; j++) {
                    if(!visited[j]) {
                        int dist = Math.abs(arr[j][0] - x) + Math.abs(arr[j][1] - y);
                        if(Math.ceil(dist / (double)50) <= bottle) { // 걸어가는 동안 맥주가 남아있다면
                            visited[j] = true;
                            q.add(new int[]{arr[j][0], arr[j][1], 20});
                        }
                    }
                }
                // 락 페스티벌까지 갈 수 있는지 확인
                int dist = Math.abs(fx - x) + Math.abs(fy - y);
                if(Math.ceil(dist / (double)50) <= bottle) {
                    q.add(new int[]{fx, fy, bottle - (int)Math.ceil(dist / (double)50)});
                }
            }

            if(possible) System.out.println("happy");
            else System.out.println("sad");
        }
    }
}
