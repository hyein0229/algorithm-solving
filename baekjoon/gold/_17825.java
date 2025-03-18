package baekjoon;

import java.io.*;
import java.util.*;
/*
문제: 17825 주사위 윷놀이
난이도: Gold 2
알고리즘: 구현, 브루트포스, 시뮬레이션, 백트래킹
풀이 방법: 칸의 숫자가 겹치는 것들이 있기 때문에 파란색 화살표를 지나온 곳인지, 빨간색을 따라온 곳인지를 구분하기 위해
        board 를 2차원 배열로 선언하였다. board[i][0]을 빨간색 경로의 칸, board[i][1]을 파란색 경로의 칸으로 하여
        위치한 말의 번호를 저장해주도록 한다.
        또 주의해야 할 것이 40번 칸에서는 경로가 겹쳐지기 때문에 이동을 마친 칸이 40인 경우엔
        board[40][0] 와 board[40][1]가 모두 0인지 확인하여 말이 없는지 확인해주어야 한다.

        주의!!
        처음에 move 함수안에서 pos 값을 바꿀 때 map 에서 꺼낸 말의 정보 curPos 값을 직접 바꾸도록 하였는데,
        이 경우 해당 주소값의 값을 바꾼 것이기 때문에 map 의 value 도 이미 같이 바뀌고 있는 것이다.
        curPos 는 map 의 value 와 같은 메모리 주소를 가지게 되기 때문이다.
        따라서 이동이 불가하면 value 의 값을 바꾸면 안되므로 curPos 값을 갱신하지 말고 다른 변수를 사용해야 한다.
        이것 때문에 계속 삽질을 했다.
 */
public class _17825 {

    static int[] arr = new int[10];
    static int[][] board = new int[42][2];
    static int maxScore = Integer.MIN_VALUE;
    static Map<Integer, int[]> mals = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");

        for(int i=0; i<10; i++) {
            arr[i] = Integer.parseInt(line[i]);
        }

        // 4개의 말 위치 저장
        for(int i=1; i<=4; i++) {
            mals.put(i, new int[]{1, 0}); // 현재 위치와 파란색 화살표를 지난 경로인지 여부 삽입
        }
        dfs(0, 0);
        System.out.println(maxScore);
    }

    public static void dfs(int turn, int totalScore) {
        // 10번의 턴을 마침
        if(turn == 10) {
            maxScore = Math.max(totalScore, maxScore);
            return;
        }

        int[][] tmp = new int[42][2];
        for(int i=1; i<=41; i++) {
            tmp[i][0] = board[i][0];
            tmp[i][1] = board[i][1];
        }

        for(int mal=1; mal<=4; mal++) {
            int[] curPos = mals.get(mal);
            int cur = curPos[0];
            int blue = curPos[1];

            if (cur != 41) { // 도착한 말이 아니면
                int score = move(mal, arr[turn]);
                if(score > 0) { // 이동 가능
                    // 도착 칸이면 점수 포함 x
                    if(score == 41) {
                        score = 0;
                    }
                    dfs(turn+1, totalScore + score);
                    // 원 상태로 복구
                    mals.put(mal, new int[]{cur, blue});
                    for (int i=1; i<=41; i++) {
                        board[i][0] = tmp[i][0];
                        board[i][1] = tmp[i][1];
                    }
                }
            }
        }
    }

    public static int move(int mal, int step) {
        int[] curPos = mals.get(mal);
        int pos = curPos[0];
        int blue = curPos[1];
        // 파란색 칸에서 출발이라면
        if(blue == 0 && (pos % 10 == 0 && pos < 40)) {
            if(pos == 10) {
                pos += 3;
            } else if(pos == 20) {
                pos += 2;
            } else if(pos == 30) {
                pos -= 2;
            }
            step--;
            blue = 1; // 파란색 경로를 지남
        }

        for(int i=0; i<step; i++) {
            // 도착 칸으로 이동한 경우
            if(pos == 40) {
                pos = 41;
                break;

            } else if(pos == 1) { // 시작 칸이면
                pos = 2;
                continue;
            }

            if(blue == 1) { // 파란색 화살표를 지나온 경로인 경우
                if(pos % 5 == 0) {
                    pos += 5;
                } else if(pos == 19 || pos == 26 || pos == 24) {
                    pos = 25;
                } else if(pos > 25){
                    pos -= 1;
                } else if(pos > 20) {
                    pos += 2;
                } else {
                    pos += 3;
                }

            } else { // 빨간색만 따라가는 경우
                pos += 2;
            }
        }
        // 말을 고를 수 있는지 확인
        if(pos == 40) {
            if(board[pos][0] == 0 && board[pos][1] == 0) { // 40인 경우엔 두 경로가 합쳐지므로 둘 다 확인
                board[curPos[0]][curPos[1]] = 0;
                board[pos][blue] = mal;
                mals.put(mal, new int[]{pos, blue});
                return pos; // 점수
            }

        } else if(pos == 41 || board[pos][blue] == 0) { // 도착했거나 이동을 마치는 칸에 말이 없으면
            board[curPos[0]][curPos[1]] = 0;
            board[pos][blue] = mal;
            mals.put(mal, new int[]{pos, blue});
            return pos;

        }
        return 0; // 이동 불가
    }
}
