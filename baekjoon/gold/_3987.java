package baekjoon;

import java.io.*;
/*
문제: 3987 보이저 1호
난이도: Gold 5
알고리즘: 구현, 그래프 , 시뮬레이션
풀이 방법: 주어진 시작 지점에서 상하좌우 4가지 방향으로 시그널을 보내는 모든 경우에 대해 탐색을 하면 된다.
        재귀를 사용하여 인자로 받은 x, y 좌표와 현재 방향에 따라 이동한 다음 좌표에 대해 경계를 벗어나거나 블랙홀이면
        탐색을 멈추고, 아니면 재귀를 다시 호출하여 한 칸 더 전파를 진행한다. 이때, / \ 라면 방향 전환을 해준 후 인자로 넘겨준다.
        무한 전파되는 상황을 체크하기 위해 3차원 배열 visited 를 생성하여 특정 위치에 같은 방향으로 재방문하는 경우
        무한 전파되는 것으로 판단하도록 하였다.
        주의할 것은 정답에서 방향이 여러 가지일 때 U, R, D, L 순서에서 앞서는 것을 출력해야 한다는 점이다...
 */
public class _3987 {

    static int N, M;
    static char[][] arr;
    static boolean[][][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1}; // 상 우 하 좌
    static int time;
    static int maxTime = Integer.MIN_VALUE;
    static int ansD = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        arr = new char[N][M];

        for(int i=0; i<N; i++) {
            String row = br.readLine();
            for(int j=0; j<M; j++) {
                arr[i][j] = row.charAt(j);
            }
        }

        input = br.readLine().split(" ");
        int pr = Integer.parseInt(input[0])-1;
        int pc = Integer.parseInt(input[1])-1;

        for(int k=0; k<4; k++) {
            time = 0;
            visited = new boolean[N][M][4];
            signal(pr, pc, k);

            if(time > maxTime) {
                maxTime = time;
                ansD = k;
                if(maxTime == Integer.MAX_VALUE) break; // 무한 전파인 경우 탈출
            }
        }

        String strDirect = toString(ansD); // 방향 문자로 바꾸기
        System.out.println(strDirect);
        if(maxTime == Integer.MAX_VALUE) {
            System.out.println("Voyager");
        } else {
            System.out.println(maxTime);
        }
    }

    public static void signal(int x, int y, int direct) {

        // 이미 방문했다면
        if(visited[x][y][direct]) {
            time = Integer.MAX_VALUE;
            return;
        }

        visited[x][y][direct] = true;
        time++;

        // 현재 방향으로 갔을 때 다음 위치
        int nx = x + dx[direct];
        int ny = y + dy[direct];

        // 항성계를 벗어나거나 블랙홀이면
        if(nx < 0 || nx >= N || ny < 0 || ny >= M || arr[nx][ny] == 'C') {
            return;
        }

        // 방향 바꾸기
        int nd = direct; // 현 방향
        if(arr[nx][ny] == '\\') {
            if(direct == 0) nd = 3;
            else if(direct == 1) nd = 2;
            else if(direct == 2) nd = 1;
            else nd = 0;
        } else if(arr[nx][ny] == '/') {
            nd = (direct == 0 || direct == 2) ? direct + 1 : direct - 1;
        }

        signal(nx, ny, nd);
    }

    public static String toString(int direct){
        String strDirect = "";
        switch(direct) {
            case 0:
                strDirect = "U";
                break;
            case 1:
                strDirect = "R";
                break;
            case 2:
                strDirect = "D";
                break;
            case 3:
                strDirect = "L";
                break;
        }
        return strDirect;
    }

}
