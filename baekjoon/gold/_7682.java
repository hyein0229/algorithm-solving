package baekjoon;

import java.io.*;
/*
문제: 7682 틱택토
난이도: Gold 5
알고리즘: 구현
풀이 방법: x와 o를 번갈아 한번씩 놓으므로 x는 o보다 하나 더 많거나 개수가 같은 두 가지의 경우만 있다.
        1. x가 o보다 하나 많은 경우 => x 가 빙고가 되어 o를 놓지 않고 끝남, 막판까지 아무도 빙고가 나지 않고 무승부
        2. x와 o의 개수가 같을 때 => o 가 빙고가 되어 x를 놓지 않고 끝남
        따라서, 먼저 개수를 비교한 다음 각 경우에 따라 빙고 여부를 확인해주면 된다.
        1, 2 번 이외의 경우는 모두 invalid 의 경우이다.
 */
public class _7682 {

    static char[][] board = new char[3][3];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            String line = br.readLine();
            if(line.equals("end")) {
                break;
            }

            int x = 0; // x의 개수
            int o = 0; // o의 개수
            for(int i=0; i<3; i++) {
                for(int j=0; j<3; j++) {
                    board[i][j] = line.charAt(j + i*3);
                    if(board[i][j] == 'X'){
                        x++;
                    } else if(board[i][j] == 'O'){
                        o++;
                    }
                }
            }

            // x와 o의 개수가 같음 => o가 이겨야 함
            if(x == o) {
                if(!check('X') && check('O')) {
                    System.out.println("valid");
                } else {
                    System.out.println("invalid");
                }
                // x가 o보다 하나 많은 경우 => x가 이기거나 무승부
            } else if(x == o+1) {
                if(check('X') && !check('O')){
                    System.out.println("valid");
                } else if(x + o == 9 && !check('O')) { // 무승부
                    System.out.println("valid");
                }else {
                    System.out.println("invalid");
                }
            } else {
                System.out.println("invalid");
            }
        }

    }

    public static boolean check(char c) {
        // 가로, 세로 빙고 확인
        for(int i=0; i<3; i++) {
            int rowCnt = 0;
            int colCnt = 0;
            for(int j=0; j<3; j++) {
                if(board[i][j] == c) rowCnt++;
                if(board[j][i] == c) colCnt++;
            }
            if(rowCnt == 3 || colCnt == 3) {
                return true;
            }
        }
        // 대각선 빙고 확인
        if(board[0][0] == c && board[1][1] == c && board[2][2] == c) {
            return true;
        }
        if(board[0][2] == c && board[1][1] == c && board[2][0] == c) {
            return true;
        }
        return false;
    }
}
