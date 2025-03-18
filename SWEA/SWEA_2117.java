import java.io.BufferedReader;
import java.io.InputStreamReader;
/*
문제: 2117. [모의 SW 역량테스트] 홈 방범 서비스
난이도: D3-D4 추측
알고리즘: 완전 탐색
풀이 방법: for문 반복문을 사용하여 각 좌표에서 k의 크기에 따라 서비스 가능한 집의 개수를 카운트해주는 방식으로 풀이하였다.
        k는 무조건 클 수록 많은 집을 포함시킬 수 있고 최소 손해를 구하는 것이 아니라 손해가 0 미만만 아니면 되므로
        k를 N+1 부터 1까지 역순으로 탐색하면서 손해가 안날 때 그 지점이 해당 좌표에서 최대 집의 개수가 되므로 바로 break 하도록 하였다.
        모든 좌표에 대해 수행하면서 최대 집의 개수를 갱신해준다.
 */
public class SWEA_2117 {
    static final int MAX_SIZE = 20;
    static int N, M;
    static int[][] city = new int[MAX_SIZE][MAX_SIZE];
    static int ans = 0;

    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line;
        int T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++)
        {
            line = br.readLine().split(" ");
            N = Integer.parseInt(line[0]); // 도시의 크기
            M = Integer.parseInt(line[1]); // 하나의 집이 지불할 수 있는 비용
            ans = 0;

            for(int i=0; i<N; i++) {
                line = br.readLine().split(" ");
                for(int j=0; j<N; j++) {
                    city[i][j] = Integer.parseInt(line[j]);
                }
            }

            go();
            System.out.println("#" + test_case + " " + ans);

        }
    }

    public static void go() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                for(int k=N+1; k>=1; k--) {
                    int cnt = countHouse(i, j, k);
                    if(cnt > 0) {
                        ans = Math.max(ans, cnt); // 가장 많은 서비스를 받는 집 개수 갱신
                        break;
                    }
                    if(cnt == N*N) return;
                }
            }
        }
    }

    public static int countHouse(int x, int y, int k) {
        int houseCnt = 0;
        for(int i=x-k+1; i<x+k; i++) {
            for(int j=y-k+1; j<y+k; j++) {
                if(i < 0 || i >= N || j < 0 || j >= N) {
                    continue;
                }

                if(Math.abs(x - i) + Math.abs(y - j) < k && city[i][j] == 1) {
                    houseCnt++;
                }
            }
        }
        // 손해가 안나는 경우에 집의 개수 리턴
        if(M * houseCnt >= k*k + (k-1)*(k-1)) {
            return houseCnt;
        }
        return -1;
    }
}
