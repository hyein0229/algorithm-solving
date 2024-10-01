package baekjoon;

import java.io.*;
/*
문제: 4014. [모의 SW 역량테스트] 활주로 건설
난이도: D4 예상?
알고리즘: 구현
풀이 방법: i점에서 높이가 1 올라간 경우 i-X부터 i까지 높이가 h[i]-1 로 동일한 구간인지 체크
         반대로, 높이가 1 낮아진 경우엔 i+X까지 h[i]-1로 동일한 구간인지 체크하면 된다.
         이때, 주의할 것은 활주로가 겹쳐서 건설되면 안되므로 visited 배열을 사용해 이미 건설한
         구간은 true 로 체크해주어야 한다.
 */
public class SWEA_4014 {
    static int N, X;
    static int[][] board;
    static int ans = 0;
    static boolean[][] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        String[] line;

        for(int t=1; t<=T; t++) {
            line = br.readLine().split(" ");
            N = Integer.parseInt(line[0]);
            X = Integer.parseInt(line[1]);
            board = new int[N][N];
            ans = 0;

            for (int i = 0; i < N; i++) {
                line = br.readLine().split(" ");
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(line[j]);
                }
            }

            // 가로로 건설
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                boolean check = true;
                int j = 1;
                while (j < N) {
                    // 높이가 동일한 구간
                    if (board[i][j - 1] == board[i][j]) {
                        j++;
                        continue;
                    }

                    // 높이가 1 낮아짐
                    if (board[i][j - 1] - 1 == board[i][j]) {
                        if (rowCheck(i, j)) {
                            j += X;
                        } else {
                            check = false;
                            break;
                        }
                        // 높이가 1 높아짐
                    } else if (board[i][j - 1] + 1 == board[i][j]) {
                        if (rowCheck(i, j)) {
                            j++;
                        } else {
                            check = false;
                            break;
                        }
                    } else { // 높이 2이상 차이
                        check = false;
                        break;
                    }
                }
                if (check) ans++;
            }
            
            // 세로로 건설
            visited = new boolean[N][N];
            for (int j = 0; j < N; j++) {
                boolean check = true;
                int i = 1;
                while (i < N) {
                    // 높이가 동일한 구간
                    if (board[i - 1][j] == board[i][j]) {
                        i++;
                        continue;
                    }

                    // 높이가 1 낮아짐
                    if (board[i - 1][j] - 1 == board[i][j]) {
                        if (colCheck(i, j)) {
                            i += X;
                        } else {
                            check = false;
                            break;
                        }
                        // 높이가 1 높아짐
                    } else if (board[i - 1][j] + 1 == board[i][j]) {
                        if (colCheck(i, j)) {
                            i++;
                        } else {
                            check = false;
                            break;
                        }
                    } else { // 높이 2이상 차이
                        check = false;
                        break;
                    }
                }
                if (check) ans++;
            }
            System.out.println("#" + t + " " + ans);
        }
    }

    public static boolean rowCheck(int x, int y) {
        // 높이 1이 더 높아지면
        if(board[x][y] > board[x][y-1]) {
            if(y-X < 0) { // 왼쪽으로 벗어남
                return false;
            }

            for(int j=y-X; j<y; j++) {
                if(board[x][j] != board[x][y] - 1 || visited[x][j]) {
                    return false;
                }
                visited[x][j] = true;
            }
            // 높이 1이 더 낮아지면
        } else if(board[x][y] < board[x][y-1]) {
            if(y-1+X >= N) return false;

            for(int j=y; j<y+X; j++) {
                if(board[x][j] != board[x][y] || visited[x][j]) {
                    return false;
                }
                visited[x][j] = true;
            }
        }
        return true;
    }

    public static boolean colCheck(int x, int y) {
        // 높이 1이 더 높아지면
        if(board[x][y] > board[x-1][y]) {
            if(x-X < 0) { // 위로 벗어남
                return false;
            }

            for(int i=x-X; i<x; i++) {
                if(board[i][y] != board[x][y] - 1 || visited[i][y]) {
                    return false;
                }
                visited[i][y] = true;
            }
            // 높이 1이 더 낮아지면
        } else if(board[x][y] < board[x-1][y]) {
            if(x-1+X >= N) return false;

            for(int i=x; i<x+X; i++) {
                if(board[i][y] != board[x][y] || visited[i][y]) {
                    return false;
                }
                visited[i][y] = true;
            }
        }
        return true;
    }
}
