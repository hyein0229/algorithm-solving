package baekjoon;

import java.io.*;
import java.util.*;
/*
문제: 19238 스타트 택시
난이도: Gold 2
알고리즘: 구현, 그래프, 시뮬레이션, BFS
풀이 방법: 시뮬레이션 문제이므로 주어진 문제에 따라 최단거리의 승객을 고르고, 태워 이동시키는 것을 반복하면 된다.
        최단거리의 승객을 고를 때 bfs를 사용하고 승객 이동 시 최단거리 이동을 해야 하므로 이때도 bfs 를 사용한다.
        bfs 만 구현할 줄 안다면 그렇게 어렵지 않은 문제같다.
 */
public class _19238 {

    static class Taxi {
        int x;
        int y;
        long f; // 연료

        public Taxi(int x, int y, long f) {
            this.x = x;
            this.y = y;
            this.f = f;
        }
    }

    static class Person {
        int sx;
        int sy;
        int gx;
        int gy;

        public Person(int sx, int sy, int gx, int gy) {
            this.sx = sx;
            this.sy = sy;
            this.gx = gx;
            this.gy = gy;
        }
    }

    static int N;
    static int M;
    static int[][] map;
    static Taxi taxi;
    static Person[] people;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    static int remain;

    public static void main (String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);
        long fuel = Integer.parseInt(line[2]);
        map = new int[N+1][N+1];
        people = new Person[M+1];
        remain = M;
        // 지도 입력
        for(int i=1; i<=N; i++) {
            line = br.readLine().split(" ");
            for(int j=1; j<=N; j++) {
                map[i][j] = -Integer.parseInt(line[j-1]); // 벽은 -1
            }
        }
        // 택시의 위치
        line = br.readLine().split(" ");
        taxi = new Taxi(Integer.parseInt(line[0]), Integer.parseInt(line[1]), fuel);
        // 손님 출발지, 도착지 입력
        for(int i=1; i<=M; i++) {
            line = br.readLine().split(" ");
            people[i] = new Person(Integer.parseInt(line[0]), Integer.parseInt(line[1]), Integer.parseInt(line[2]), Integer.parseInt(line[3]));
            map[people[i].sx][people[i].sy] = i; // 손님 번호 기록
        }
        // 택시 태우기
        while(taxi.f > 0 && remain > 0) {
            int targetNum = findTarget(); // 손님 고르기
            if(targetNum == -1) {
                break;
            }
            if(!pickUp(targetNum)) { // 손님 목적지에 이동시키기
                break;
            }
            remain--;
        }

        if(remain == 0){
            System.out.println(taxi.f);
        } else {
            System.out.println(-1);
        }

    }

    public static int findTarget() {
        boolean[][] visited = new boolean[N+1][N+1];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{taxi.x, taxi.y, 0});
        visited[taxi.x][taxi.y] = true;

        int minDist = Integer.MAX_VALUE;
        int targetNum = 0;
        int targetX = N+1;
        int targetY = N+1;
        boolean find = false; // 손님을 찾았는지 여부
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int dist = cur[2];

            if(find && minDist < dist) {
                break;
            }

            // 손님이 있다면
            if(map[x][y] >= 1) {
                if(targetX > x || (targetX == x && targetY > y)) {
                    targetNum = map[x][y];
                    targetX = x;
                    targetY = y;
                }
                minDist = dist;
                find = true;
                continue;
            }

            for(int k=0; k<4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                if(nx < 1 || nx > N || ny < 1 || ny > N) {
                    continue;
                }
                // 아직 방문하지 않았고, 벽이 아닌 곳이면
                if(!visited[nx][ny] && map[nx][ny] != -1) {
                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny, dist+1});
                }
            }
        }
        // 손님을 찾지 못한 경우
        if(!find) {
            return -1;
        }
        // 찾은 손님 위치로 택시 이동
        taxi.f -= minDist;
        taxi.x = targetX;
        taxi.y = targetY;
        return targetNum;
    }

    public static boolean pickUp(int num) {
        boolean success = false; // 성공 여부
        boolean[][] visited = new boolean[N+1][N+1];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{taxi.x, taxi.y, 0});
        visited[taxi.x][taxi.y] = true;

        Person target = people[num];
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int dist = cur[2];
            // 연료 초과한 경우 이동 불가
            if(taxi.f < dist) {
                break;
            }
            // 목적지에 도착
            if(x == target.gx && y == target.gy) {
                taxi.x = target.gx;
                taxi.y = target.gy;
                taxi.f -= dist; // 연료 소모
                taxi.f += dist * 2; // 연료 충전
                map[target.sx][target.sy] = 0; // 손님이 있던 곳은 빈칸으로 변경
                success = true;
                break;
            }

            for(int k=0; k<4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                if(nx < 1 || nx > N || ny < 1 || ny > N) {
                    continue;
                }
                // 아직 방문하지 않았고, 벽이 아닌 곳이면
                if(!visited[nx][ny] && map[nx][ny] != -1) {
                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny, dist+1});
                }
            }
        }
        return success;
    }
}
