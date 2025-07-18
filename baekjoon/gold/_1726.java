import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
/**
문제: 1726 로봇
난이도: Gold 3
알고리즘: bfs
풀이 유형: x, y 좌표와 방향까지 고려하여 3차원으로 방문 체크. 나머지는 구현만 신경쓰면 어렵지 않음
**/
public class _1726 {

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int M = Integer.parseInt(line[0]);
        int N = Integer.parseInt(line[1]);
        int[][] map = new int[M][N];
        int[] start = new int[3]; // 출발점
        int[] end = new int[3]; // 도착점

        // 직사각형 입력
        for(int i=0; i<M; i++) {
            line = br.readLine().split(" ");
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(line[j]);
            }
        }

        line = br.readLine().split(" ");
        for(int i=0; i<3; i++) {
            start[i] = Integer.parseInt(line[i]) - 1;
        }

        line = br.readLine().split(" ");
        for(int i=0; i<3; i++) {
            end[i] =  Integer.parseInt(line[i]) - 1;
        }

        boolean[][][] visited = new boolean[M][N][4];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{start[0], start[1], start[2], 0}); // 위치, 방향, 횟수
        visited[start[0]][start[1]][start[2]] = true;

        while(!q.isEmpty()){
            int[] cur = q.poll();

            if(cur[0] == end[0] && cur[1] == end[1] && cur[2] == end[2]){
                System.out.println(cur[3]);
                break;
            }

            // 명령 1
            for(int len=1; len<=3; len++){ // 명령 1
                int nx = cur[0] + len * dx[cur[2]];
                int ny = cur[1] + len * dy[cur[2]];
                if(nx < 0 || nx >= M || ny < 0 || ny >= N){
                    break;
                }

                if(map[nx][ny] == 1) { // 앞으로 갈 수 없음
                    break;
                }

                if(!visited[nx][ny][cur[2]]){
                    q.add(new int[]{nx, ny, cur[2], cur[3]+1});
                    visited[nx][ny][cur[2]] = true;
                }
            }

            // 명령 2
            for(int turn=0; turn<2; turn++){
                int nd = getDir(cur[2], turn);
                if(!visited[cur[0]][cur[1]][nd]){
                    q.add(new int[]{cur[0], cur[1], nd, cur[3]+1});
                    visited[cur[0]][cur[1]][nd] = true;
                }
            }
        }
    }

    public static int getDir(int curD, int turn) {
        if(turn == 0) { // left
            if(curD == 0) return 3;
            if(curD == 1) return 2;
            if(curD == 2) return 0;
            else return 1;
        } else { // right
            if(curD == 0) return 2;
            if(curD == 1) return 3;
            if(curD == 2) return 1;
            else return 0;
        }
    }
}
