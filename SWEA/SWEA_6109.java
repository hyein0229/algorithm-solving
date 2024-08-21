import java.io.BufferedReader;
import java.io.InputStreamReader;
/*
문제: 6109. 추억의 2048게임
난이도: D4
알고리즘: 구현
풀이 방법: 비교해야 하는 위치를 end 변수에 저장한다. 다음 조건에 맞추어 타일을 움직이고 end를 조정한다.
        1. end 위치의 타일 숫자와 같다면 end 위치의 타일을 x2 해주고 원래 위치를 0으로 바꾼 후 end +1 한다.
        2. 숫자가 같지 않다면 end+1 위치에 타일을 가져와야 하므로 end+1 이 본인이 아니면 이동하고 end+1 한다.
            (칸이 붙어있다면 end+1이 본인이 되므로 가만히 두면 된다.)
        3. end 위치가 빈 곳이면 타일만 가져온다.
 */
public class SWEA_6109 {
    static int N;
    static String S;
    static int[][] board;
    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line;
        int T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++)
        {
            line = br.readLine().split(" ");
            N = Integer.parseInt(line[0]);
            S = line[1];
            board = new int[N][N];

            for(int i=0; i<N; i++) {
                line = br.readLine().split(" ");
                for(int j=0; j<N; j++) {
                    board[i][j] = Integer.parseInt(line[j]);
                }
            }
            simulate(S);
            System.out.println("#" + test_case);
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    System.out.print(board[i][j] + " ");
                }
                System.out.println();
            }
        }
    }

    public static void simulate(String s) {
        if(s.equals("up")) {
            for(int j=0; j<N; j++) {
                int end = 0;
                for(int i=0; i<N; i++) {
                    if(i > 0 && board[i][j] > 0) {

                        if(board[end][j] == 0){
                            board[end][j] = board[i][j];
                            board[i][j] = 0;
                            continue;
                        }

                        // 숫자가 같은 타일이면 합치기
                        if(board[end][j] == board[i][j]) {
                            board[end][j] *= 2;
                            board[i][j] = 0;
                        }

                       else if(end+1 < i) {
                           board[end + 1][j] = board[i][j];
                           board[i][j] = 0;
                       }
                       end++;
                    }
                }
            }

        } else if(s.equals("down")) {
            for(int j=0; j<N; j++) {
                int end = N-1;
                for(int i=N-1; i>=0; i--) {
                    if(i < N-1 && board[i][j] > 0) {

                        if(board[end][j] == 0){
                            board[end][j] = board[i][j];
                            board[i][j] = 0;
                            continue;
                        }

                        if(board[end][j] == board[i][j]) {
                            board[end][j] *= 2;
                            board[i][j] = 0;
                        }
                        else if(end-1 > i) {
                            board[end - 1][j] = board[i][j];
                            board[i][j] = 0;
                        }
                        end--;
                    }
                }
            }

        } else if(s.equals("right")) {
            for(int i=0; i<N; i++) {
                int end = N-1;
                for(int j=N-1; j>=0; j--) {
                    if(j < N-1 && board[i][j] > 0) {

                        if(board[i][end] == 0){
                            board[i][end] = board[i][j];
                            board[i][j] = 0;
                            continue;
                        }

                        if(board[i][end] == board[i][j]) {
                            board[i][end] *= 2;
                            board[i][j] = 0;
                        } else if(end-1 > j){
                            board[i][end-1] = board[i][j];
                            board[i][j] = 0;
                        }
                        end--;
                    }
                }
            }

        } else  {
            for(int i=0; i<N; i++) {
                int end = 0;
                for(int j=0; j<N; j++) {
                    // 타일이 있다면
                    if(j > 0 && board[i][j] > 0) {

                        if(board[i][end] == 0){
                            board[i][end] = board[i][j];
                            board[i][j] = 0;
                            continue;
                        }

                        if(board[i][end] == board[i][j]) {
                            board[i][end] *= 2;
                            board[i][j] = 0;
                        } else if(end+1 < j){
                            board[i][end+1] = board[i][j];
                            board[i][j] = 0;
                        }
                        end++;
                    }
                }
            }
        }
    }
}
