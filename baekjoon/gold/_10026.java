package baekjoon;

import java.io.*;
import java.util.*;
/*
문제: 10026 적록색약
난이도: Gold 5
알고리즘: bfs, 그래프
풀이 방법: bfs 를 사용하여 R, G, B 색 구역의 개수를 각각 카운트해주고 map 에 저장하여 각 개수를 더해준다.
        적록색약인 경우엔 입력받은 그림에서 G를 R로 바꾸어 통일해주고 똑같은 방식으로 bfs를 진행하여
        R, B 각각 구역의 개수를 더해주었다.
 */
public class _10026 {
    static int N;
    static char[][] board;
    static boolean[][] visited;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static Map<Character, Integer> countMap = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new char[N][N];
        visited = new boolean[N][N];
        int ans1, ans2;
        // 카운트를 위한 초기화
        countMap.put('R', 0);
        countMap.put('G', 0);
        countMap.put('B', 0);

        // 그림 입력
        for(int i=0; i<N; i++) {
            String row = br.readLine();
            for(int j=0; j<N; j++) {
                board[i][j] = row.charAt(j);
            }
        }
        // 적록색약이 아닌 사람이 봤을 때 구역의 수 구하기
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(!visited[i][j]) {
                    bfs(i, j);
                }
            }
        }
        ans1 = countMap.get('R') + countMap.get('G') + countMap.get('B');

        // 다시 bfs 탐색을 위한 초기화
        countMap.put('R', 0);
        countMap.put('B', 0);
        visited = new boolean[N][N];
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(board[i][j] == 'G') { // 초록색을 빨간색으로 통일
                    board[i][j] = 'R';
                }
            }
        }

        // 적록색약인 사람이 봤을 때 구역의 수 구하기
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(!visited[i][j]) {
                    bfs(i, j);
                }
            }
        }
        ans2 = countMap.get('R') + countMap.get('B');
        System.out.println(ans1 + " " + ans2);
    }

    public static void bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        visited[x][y] = true;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];

            for(int k=0; k<4; k++) {
                int nx = curX + dx[k];
                int ny = curY + dy[k];
                if(nx < 0 || nx >= N || ny < 0 || ny >= N) {
                    continue;
                }

                if(!visited[nx][ny] && board[nx][ny] == board[x][y]) {
                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny});
                }
            }
        }
        countMap.put(board[x][y], countMap.get(board[x][y])+1);
    }
}
