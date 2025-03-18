import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 문제: 1101 카드 정리 1
 * 난이도: Gold 4
 * 알고리즘: 그리디, 브루트포스
 * 풀이: 모든 박스에 대해 하나를 조커 박스로 지정한 후 각 경우에 대 이동 횟수 구하고 그중 최솟값 갱신
 *      이동해야 하는 경우
 *      1. 카드 색상이 1가지인데 이전 박스에 이미 있는 색상인 경우
 *      2. 카드 색상이 2개 이상이면 무조건 이동
 *      조커박스가 아닌 박스에 대해 가지고 있는 카드 색상을 모두 구한 후
 *      개수에 따라 이동
 */
public class _1101 {
    static int N, M;
    static int[][] boxes;
    static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        boxes = new int[N][M];

        // 각 박스의 카드 정보
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                boxes[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int joker = 0; joker < N; joker++) {
            boolean[] used = new boolean[M];
            int cnt = 0;

            for (int i = 0; i < N; i++) {
                if (joker == i) continue; // 조커 박스면 제외

                List<Integer> colorCards = new ArrayList<Integer>();
                for (int j = 0; j < M; j++) {
                    if (boxes[i][j] > 0) {
                        colorCards.add(j);
                    }
                }

                int colorCnt = colorCards.size();
                if(colorCnt == 0) continue; // 빈 박스
                if (colorCnt == 1) {
                    int colorIdx = colorCards.get(0);
                    if (used[colorIdx]) { // 이미 이전 박스에 있는 색상
                        cnt++;
                    } else {
                        used[colorIdx] = true;
                    }
                } else if (colorCnt > 1) { // 2개 이상이면 이동
                    cnt++;
                }
            }
            ans = Math.min(ans, cnt);
        }
        System.out.println(ans);
    }
}
