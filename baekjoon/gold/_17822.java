package baekjoon;

import java.io.*;
import java.util.*;
/*
문제: 17822 원판 돌리기
난이도: Gold 2
알고리즘: 구현, 시뮬레이션
풀이 방법: 주어진 조건에 따라 구현만 하면 되지만 자잘하게 실수할 부분이 많은 문제같다.
        원판을 회전시킬 때 한 칸씩 k번 당겨준 게 아니라 k칸을 한 번에 옮겨주다보니
        한 칸씩 당길 땐 하나의 배열값만 tmp 에 저장해주면 되는데
        k칸씩 당길 땐 해당 위치의 값이 이미 다른 것으로 복사될 수 있기 때문에 아예 다 복사해놓은 임시 배열을 만드는게 낫다.
        그리고, 그리고 인접한 수를 탐색할 때 지워진 수 0을 포함하지 않는 것도 조심해야 한다.
 */
public class Main {
    static int N, M, T;
    static int[][] disc;
    static boolean[][] visited;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        disc = new int[N+1][M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                disc[i+1][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<T; i++) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            spin(X, d, k); // 1. 원판 회전시키기

            // 2-1. 인접하면서 같은 수 찾기
            visited = new boolean[N+1][M];
            boolean flag = false;
            for(int x=1; x<=N; x++) {
                for(int y=0; y<M; y++) {
                    if(!visited[x][y] && disc[x][y] > 0) {
                        int num = disc[x][y];
                        boolean result = dfs(x, y, num);
                        if(result) { // 있다면
                            flag = true;
                        } else {
                            disc[x][y] = num;
                        }
                    }
                }
            }
            // 2-2. 없다면 원판에 적힌 수 평균 구해서 빼고 더하기
            if(!flag) {
                double avg = average();
                for(int x=1; x<=N; x++) {
                    for(int y=0; y<M; y++) {
                        if(disc[x][y] == 0) { // 지워진 수이면 넘어감
                            continue;
                        }
                        if(disc[x][y] > avg) {
                            disc[x][y]--;
                        } else if(disc[x][y] < avg) {
                            disc[x][y]++;
                        }
                    }
                }
            }
        }

        int answer = 0;
        for(int x=1; x<=N; x++) {
            for(int y=0; y<M; y++) {
                answer += disc[x][y];
            }
        }
        System.out.println(answer);
    }

    public static void spin(int x, int d, int k) {

        int cur = x;
        while(cur <= N) {
            // 원래의 상태를 복사
            int[] tmp = new int[M];
            for(int y=0; y<M; y++){
                tmp[y] = disc[cur][y];
            }
            if(d == 0) { // 시계 방향 회전
                for(int y=M-1; y>=0; y--) {
                    disc[cur][(y+k) % M] = tmp[y];
                }
            } else  { // 반시계 방향 회전
                for(int y=0; y<M; y++) {
                    if(y-k >= 0) {
                        disc[cur][y-k] = tmp[y];
                    } else {
                        disc[cur][M-Math.abs(y-k)] = tmp[y];
                    }
                }
            }
            cur += x;
        }
    }

    public static boolean dfs(int x, int y, int num) {
        boolean flag = false;
        visited[x][y] = true;
        disc[x][y] = 0; // 수 지우기

        for(int i=0; i<4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx < 0 || nx > N){ // 원판 범위를 넘어가면
                continue;
            }
            if(ny < 0) {
                ny = M-1;
            } else if(ny >= M) {
                ny = 0;
            }

            if(!visited[nx][ny] && disc[nx][ny] == num) {
                flag = true;
                dfs(nx, ny, num);
            }
        }
        return flag;
    }

    public static double average() {
        double sum = 0;
        int cnt = 0;
        for(int i=1; i<=N; i++) {
            for(int j=0; j<M; j++) {
                if(disc[i][j] > 0) {
                    cnt++;
                }
                sum += disc[i][j];
            }
        }
        return sum / (double)cnt;
    }
}
