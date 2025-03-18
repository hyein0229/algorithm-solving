package baekjoon;

import java.io.*;
import java.util.*;
/*
문제: 5014 스타트링크
난이도: Silver 1
알고리즘: BFS
풀이 방법: 적어도 몇번 -> 최소 버튼을 누르는 횟수를 구해야 하므로 BFS 를 사용하여 풀었다.
        큐에 [현재 층, 누른 횟수] 를 저장하고 현재 층에서 U층, D층 위 아래의 방문하지 않은 층을
        이동하면서 해당 층이 G층이 될 때까지 반복한다.
 */
public class _5014 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int F = Integer.parseInt(line[0]);
        int S = Integer.parseInt(line[1]);
        int G = Integer.parseInt(line[2]);
        int U = Integer.parseInt(line[3]);// 위로 이동
        int D = Integer.parseInt(line[4]); // 아래 이동
        int answer = -1;

        boolean[] visited = new boolean[F+1];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{S, 0});
        visited[S] = true;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int floor = cur[0];
            int cnt = cur[1];
            if(floor == G) { // G층에 도착했다면
                answer = cnt;
                break;
            }
            // 위로 U층 갈 수 있다면 이동
            if(floor + U <= F && !visited[floor + U]) {
                visited[floor + U] = true;
                q.add(new int[]{floor + U, cnt+1});
            }
            // 아래로 D층 갈 수 있다면 이동
            if(floor - D >= 1 && !visited[floor - D]) {
                visited[floor - D] = true;
                q.add(new int[]{floor - D, cnt+1});
            }
        }

        if(answer == -1) System.out.println("use the stairs");
        else System.out.println(answer);
    }
}
