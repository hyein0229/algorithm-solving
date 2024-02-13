package baekjoon;

import java.io.*;
/*
문제: 20061 모노미노도미노 2
난이도: Gold 2
알고리즘: 구현, 시뮬레이션
풀이 방법: 주의할 것은 행이나 열에 타일들이 가득 찬 경우 모두 없애고 한 칸씩 이동하는 부분에서
        이동한 후의 상태에서 또 타일들이 가득 찬 행, 열이 있을 수 있으므로 한 번 이동시킨 후 다시 처음부터
        행, 열을 탐색하여 또 있는지 확인해 주어야 하는 것이다.
 */
public class _20061 {

    static int N;
    static int[][] green = new int[6][4];
    static int[][] blue = new int[4][6];
    static int score = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for(int i=0; i<N; i++) {
            String[] line = br.readLine().split(" ");
            int t = Integer.parseInt(line[0]);
            int x = Integer.parseInt(line[1]);
            int y = Integer.parseInt(line[2]);
            setBlocks(t, x, y);

            removeGreenRow();
            removeBlueCol();

            removelightGreen();
            removelightBlue();
        }

        System.out.println(score);
        System.out.println(countTiles());

    }

    public static void setBlocks(int t, int x, int y) {
        // 초록색 영역에 블록 배치하기
        int row = 0;
        if(t == 1) {
            for(int i=0; i<6; i++) {
                if(green[i][y] != 0) {
                    break;
                }
                row = i;
            }
            green[row][y] = 1;

        } else if(t == 2) {
            for(int i=0; i<6; i++) {
                if(green[i][y] != 0 || green[i][y+1] != 0) {
                    break;
                }
                row = i;
            }
            green[row][y] = 1;
            green[row][y+1] = 1;

        }else {
            for(int i=1; i<6; i++) {
                if(green[i-1][y] != 0 || green[i][y] != 0) {
                    break;
                }
                row = i;
            }
            green[row-1][y] = 1;
            green[row][y] = 1;
        }

        // 파란색 영역에 블록 배치하기
        int col = 0;
        if(t == 1) {
            for(int j=0; j<6; j++) {
                if(blue[x][j] != 0) {
                    break;
                }
                col = j;
            }
            blue[x][col] = 1;

        } else if(t == 2) {
            for(int j=1; j<6; j++) {
                if(blue[x][j] != 0 || blue[x][j-1] != 0) {
                    break;
                }
                col = j;
            }
            blue[x][col] = 1;
            blue[x][col-1] = 1;

        } else {
            for(int j=0; j<6; j++) {
                if(blue[x][j] != 0 || blue[x+1][j] != 0) {
                    break;
                }
                col = j;
            }
            blue[x][col] = 1;
            blue[x+1][col] = 1;
        }
    }

    public static void removeGreenRow() {

        boolean isContinued = true;
        while(isContinued) {
            isContinued = false;
            for(int i=5; i>=2; i--) {
                boolean flag = true;
                for(int j=0; j<4; j++) { // 행이 타일로 차 있는지 확인
                    if(green[i][j] == 0) {
                        flag = false;
                        break;
                    }
                }
                // 행에 있는 타일 사라짐
                if(flag) {
                    isContinued = true;
                    score++;
                    for(int j=0; j<4; j++) {
                        green[i][j] = 0;
                    }
                    for(int k=i-1; k>=0; k--) { // 한 칸씩 아래로 땡김
                        for(int j=0; j<4; j++) {
                            green[k+1][j] = green[k][j];
                            green[k][j] = 0;
                        }
                    }
                    break;
                }
            }
        }
    }

    public static void removeBlueCol() {

        boolean isContinued = true;
        while(isContinued) {
            isContinued = false;
            for(int j=5; j>=2; j--) {
                boolean flag = true;
                for(int i=0; i<4; i++) {
                    if(blue[i][j] == 0) { // 열이 타일로 차 있는지 확인
                        flag = false;
                        break;
                    }
                }
                // 열에 있는 타일 사라짐
                if(flag) {
                    isContinued = true;
                    score++;
                    for(int i=0; i<4; i++) {
                        blue[i][j] = 0;
                    }
                    for(int k=j-1; k>=0; k--) { // 오른쪽으로 한 칸씩 땡김
                        for(int i=0; i<4; i++) {
                            blue[i][k+1] = blue[i][k];
                            blue[i][k] = 0;
                        }
                    }
                    break;
                }
            }
        }
    }

    public static void removelightGreen() {
        int rowCnt = 0;
        for(int i=0; i<2; i++) {
            for(int j=0; j<4; j++) {
                if(green[i][j] == 1) {
                    rowCnt++;
                    break;
                }
            }
        }

        if(rowCnt > 0) {
            for(int i=5; i>=2; i--) {
                for(int j=0; j<4; j++) {
                    green[i][j] = green[i-rowCnt][j];
                    green[i-rowCnt][j] = 0;
                }
            }
        }
    }

    public static void removelightBlue() {
        int colCnt = 0;
        for(int j=0; j<2; j++) {
            for(int i=0; i<4; i++) {
                if(blue[i][j] == 1) {
                    colCnt++;
                    break;
                }
            }
        }

        if(colCnt > 0) {
            for(int j=5; j>=2; j--) {
                for(int i=0; i<4; i++) {
                    blue[i][j] = blue[i][j-colCnt];
                    blue[i][j-colCnt] = 0;
                }
            }
        }
    }

    public static int countTiles() {
        int cnt = 0;
        for(int i=2; i<6; i++) {
            for(int j=0; j<4; j++) {
                if(green[i][j] == 1) {
                    cnt++;
                }
            }
        }
        for(int j=2; j<6; j++) {
            for(int i=0; i<4; i++) {
                if(blue[i][j] == 1) {
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
