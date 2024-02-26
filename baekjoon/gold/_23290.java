package baekjoon;

import java.io.*;
import java.util.*;
/*
문제: 23290 마법사 상어와 복제
난이도: Gold 1
알고리즘: 구현, 시뮬레이션
풀이 방법: 빡구현 문제로 문제 설명을 이해하는 것이 중요하다. 아래는 주의할 점
        1. 물고기 이동 시 이동할 수 없다면 원래 있던 물고기 상태로 똑같이 복제해야 함 -> 따라서, 같은 상태의 물고기가 두개
        2. 상어가 이동 시 왔던 곳을 재방문할 수 있다.
            2-1. 무조건 3칸 연속 이동이므로 재방문이 될 경우가 있다는 것에 주의
            2-2. 처음 방문이라면 물고기를 카운트하고 방문 체크를 한 후 dfs가 끝났을 때 방문 체크도 백트래킹
            2-3. 재방문이라면 물고기를 카운트하지 않고 방문 체크 백트래킹은 없다. (이미 이전에 방문 체크가 된것이므로)
 */
public class _23290 {

    static int M, S;
    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[][] smell = new int[5][5]; // 냄새 기록
    static List<Integer>[][] board = new List[5][5]; // 물고기 기록
    static int sx, sy;
    static int maxFishCnt;
    static int[] sharkDirect = new int[3];
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        M = Integer.parseInt(line[0]);
        S = Integer.parseInt(line[1]);

        for(int i=1; i<5; i++) {
            for(int j=1; j<5; j++) {
                board[i][j] = new ArrayList<>();
            }
        }
        // 물고기 정보 입력
        for(int i=0; i<M; i++) {
            line = br.readLine().split(" ");
            int x = Integer.parseInt(line[0]);
            int y = Integer.parseInt(line[1]);
            int d = Integer.parseInt(line[2]) - 1;
            board[x][y].add(d);
        }
        // 상어 위치 입력
        line = br.readLine().split(" ");
        sx = Integer.parseInt(line[0]);
        sy = Integer.parseInt(line[1]);

        // S번의 연습 수행
        for(int i=0; i<S; i++) {
            List<Integer>[][] newBoard = moveFishes(); // 물고기 이동
            moveShark(newBoard); // 상어 이동
            // 냄새 사라짐
            for(int j=1; j<5; j++) {
                for(int k=1; k<5; k++) {
                    if(smell[j][k] > 0) smell[j][k]--;
                }
            }
            // 복제
            for(int j=1; j<5; j++) {
                for(int k=1; k<5; k++) {
                    for(Integer fish : newBoard[j][k]) {
                        board[j][k].add(fish);
                    }
                }
            }
        }

        int totalCnt = 0;
        for(int i=1; i<5; i++) {
            for(int j=1; j<5; j++) {
                totalCnt += board[i][j].size();
            }
        }
        System.out.println(totalCnt);
    }

    public static List[][] moveFishes() {
        List<Integer>[][] newBoard = new List[5][5]; // 이동한 후의 물고기 위치 저장
        for(int i=1; i<5; i++) {
            for(int j=1; j<5; j++) {
                newBoard[i][j] = new ArrayList<>();
            }
        }

        for(int i=1; i<5; i++) {
            for(int j=1; j<5; j++) {
                for(Integer d : board[i][j]) {
                    int curD = d; // 현재 물고기 방향
                    for(int k=0; k<8; k++) {
                        int nx = i + dx[curD];
                        int ny = j + dy[curD];
                        if(nx >= 1 && nx <= 4 && ny >= 1 && ny <= 4) { // 격자 범위 체크
                            if(!(nx == sx && ny == sy) && smell[nx][ny] == 0) { // 상어가 없고, 냄새가 없으면
                                newBoard[nx][ny].add(curD); // 이동
                                break;
                            }
                        }
                        if(k == 7) { // 이동이 불가한 경우엔 이동 x
                            newBoard[i][j].add(d);
                            break;
                        }
                        curD = (curD == 0) ? 7 : curD - 1; // 반시계 방향 회전
                    }
                }
            }
        }
        return newBoard;
    }

    public static void moveShark(List<Integer>[][] newBoard) {
        int[] arr = new int[3]; // 상어 이동 방향 기록할 배열
        visited = new boolean[5][5]; // 방문 체크
        maxFishCnt = Integer.MIN_VALUE; // 최대로 제외되는 물고기 개수
        dfs(0, sx, sy, arr, newBoard, 0);
        // 상어 이동
        for(int i=0; i<3; i++) {
            sx += dx[sharkDirect[i]];
            sy += dy[sharkDirect[i]];
            if(!newBoard[sx][sy].isEmpty()) { // 물고기가 있으면 제외하고 냄새를 남김
                smell[sx][sy] = 3;
                newBoard[sx][sy].clear();
            }
        }
    }

    public static void dfs(int cnt, int x, int y, int[] arr, List[][] newBoard, int fishCnt) {
        // 3번 모두 이동하면
        if(cnt == 3) {
            // 제외되는 물고기 수가 더 많으면 갱신
            if(maxFishCnt < fishCnt) {
                maxFishCnt = fishCnt;
                for(int i=0; i<3; i++) {
                    sharkDirect[i] = arr[i];
                }
            } else if(maxFishCnt == fishCnt) { // 같다면 사전 순 앞서는 것으로 갱신
                int t1 = transition(arr);
                int t2 = transition(sharkDirect);
                if(t1 < t2) { // 사전 순으로 앞서면 갱신
                    for(int i=0; i<3; i++) {
                        sharkDirect[i] = arr[i];
                    }
                }
            }
            return;
        }

        // 상하좌우 이동
        for(int k=0; k<7; k+=2) {
            int nx = x + dx[k];
            int ny = y + dy[k];
            if(nx < 1 || nx > 4 || ny < 1 || ny > 4) {
                continue;
            }

            if(!visited[nx][ny]) { // 처음 방문이면 물고기 카운트
                arr[cnt] = k;
                visited[nx][ny] = true;
                dfs(cnt+1, nx, ny, arr, newBoard, fishCnt + newBoard[nx][ny].size()); // 해당 방향으로 이동
                arr[cnt] = 0; // 백트래킹
                visited[nx][ny] = false;
            } else { // 재방문이면 물고기를 카운트하지 않음
                arr[cnt] = k;
                dfs(cnt+1, nx, ny, arr, newBoard, fishCnt); // 해당 방향으로 이동
                arr[cnt] = 0;
            }
        }
    }

    public static int transition(int[] directs) {
        String str = "";
        for(int i=0; i<3; i++) {
            int d = directs[i];
            switch (d) {
                case 0 : // 좌
                    str += "2";
                    break;
                case 2: // 상
                    str += "1";
                    break;
                case 4: // 우
                    str += "4";
                    break;
                case 6: // 하
                    str += "3";
                    break;
            }
        }
        return Integer.parseInt(str);
    }

}
