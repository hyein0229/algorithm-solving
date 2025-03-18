import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
문제: 9282 초콜릿과 건포도
난이도: D4
알고리즘: 메모이제이션(dp), 누적합, 재귀
풀이 방법: 기본적인 구현 방법은 잘라야 하는 초콜릿 블록의 위치와 크기를 파라미터로 넘겨주면서 재귀적으로 함수를 호출하며 최솟값을 리턴하는 것이다.
        이때, 시간 초과를 방지하기 위해선 현재 건포도 양의 총합을 구할 때 반복문이 아닌 누적합을 사용하고,
        재귀를 사용할 때 중복되는 계산을 막기 위해 메모이제이션을 사용해야 한다.
        메모이제이션을 사용할 때 구한 답을 dp 에 갱신해주지 않는 실수 조심하기
 */
public class SWEA_9282 {

    static final int MAX_SIZE = 50;
    static int N, M;
    static int[][] arr = new int[MAX_SIZE+1][MAX_SIZE+1];
    static int[][] sum = new int[MAX_SIZE+1][MAX_SIZE+1];
    static int[][][][] dp;
    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line;
        int T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++)
        {
            line = br.readLine().split(" ");
            N = Integer.parseInt(line[0]);
            M = Integer.parseInt(line[1]);
            dp = new int[N+1][M+1][N+1][M+1];

            // 건포도 입력
            for(int i=1; i<=N; i++) {
                line = br.readLine().split(" ");
                for(int j=1; j<=M; j++) {
                    arr[i][j] = Integer.parseInt(line[j-1]);
                }
            }

            // 누적합
            for(int i=1; i<=N; i++) {
                for(int j=1; j<=M; j++){
                    sum[i][j] = sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1] + arr[i][j];
                }
            }

            int ans = cut(1, 1, M, N);
            System.out.println("#" + test_case + " " + ans);
        }
    }

    public static int cut(int x, int y, int width, int height) {
        int ans = Integer.MAX_VALUE; // 지불해야 하는 건포도의 최소 양
        if(width * height == 1)
            return 0;

        if(dp[x][y][height][width] != 0)
            return dp[x][y][height][width];

        // 초콜릿에 있는 건포도 합 구하기
        int ex = x + height - 1;
        int ey = y + width - 1;
        int total = sum[ex][ey] - sum[ex][ey-width] - sum[ex-height][ey] + sum[x-1][y-1];

        //  가로 일직선 자르기
        for(int i=1; i<height; i++) {
            ans = Math.min(ans, total + cut(x, y, width, i) + cut(x+i, y, width, height-i));
        }
        //  세로 일직선 자르기
        for(int i=1; i<width; i++) {
            ans = Math.min(ans, total + cut(x, y, i, height) + cut(x, y+i, width-i, height));
        }

        return dp[x][y][height][width] = ans;
    }
}
