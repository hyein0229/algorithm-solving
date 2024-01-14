package baekjoon;

import java.io.*;
import java.util.*;
/*
문제: 15685 드래곤 커브
난이도: Gold 3
알고리즘: 구현, 시뮬레이션
풀이 방법: 드래곤 커브시 선분 1씩 좌표 이동을 할 때 규칙을 찾는 것이 중요하다. 직접 그려보면 쉽게 찾을 수 있다.
        테스트 케이스 1의 2번째 예제의 이동 방향을 보면 다음과 같다.
        0세대 -> 1
        1세대 -> 1 / 2
        2세대 -> 1 2 / 3 2
        3세대 -> 1 2 3 2 / 3 0 3 2
        이전 세대까지의 이동 방향을 뒤에서부터 차례대로 반시계방향으로 바꾸어 이동한 것과 결과가 같다.
        즉, 방향 번호를 1 증가시킨다. (4는 없으므로 3에선 다시 0이 된다)
        따라서, 이전 세대까지 이동방향을 리스트에 저장해놓고 다음 세대는 리스트 뒤에서부터 방향에 +1 을 하여 삽입해면 된다.
        모두 구한 뒤엔 이동방향에 따라 좌표를 이동시켜 해당 좌표를 방문 체크해준다.
 */
public class _15685 {

    static boolean[][] board = new boolean[101][101];
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for(int i=0; i<N; i++) {
            String[] line = br.readLine().split(" ");
            int x = Integer.parseInt(line[0]);
            int y = Integer.parseInt(line[1]);
            int d = Integer.parseInt(line[2]);
            int g = Integer.parseInt(line[3]);
            dragonCurve(x, y, d, g);
        }

        int answer = 0;
        for(int i=0; i<100; i++) {
            for(int j=0; j<100; j++) {
                if(board[i][j] && board[i+1][j] && board[i][j+1] && board[i+1][j+1]){
                    answer++;
                }
            }
        }
        System.out.println(answer);
    }

    public static void dragonCurve(int x, int y, int d, int g) {
        List<Integer> dList = new ArrayList<>();
        dList.add(d);
        board[y][x] = true; // 0세대 시작점

        // g 세대까지 커브 반복
        for(int i=1; i<=g; i++) {
            for(int j=dList.size()-1; j>=0; j--) {
                dList.add((dList.get(j) + 1) % 4);
            }
        }
        for(Integer dir : dList) {
            // 다음 좌표로 이동
            x += dx[dir];
            y += dy[dir];
            board[y][x] = true;
        }
    }
}
