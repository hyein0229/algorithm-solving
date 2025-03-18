package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

/*
문제: 1946 신입 사원
난이도: Silver 1
알고리즘: 그리디, 정렬
풀이 방법: 먼저, 서류 등수를 오름차순으로 정렬한다. 정렬을 하면 앞의 지원자보다 뒤의 지원자는 서류 등수가 낮으므로
        무조건 면접 등수가 높아야 통과할 수 있다. 따라서, 앞에서부터 차례대로 순회하여 면접 통과 등수를 갱신하면서
        해당 등수보다 낮은 경우에 탈락을 시킨다.
 */
public class _1946 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int i=0; i<T; i++){
            int N = Integer.parseInt(br.readLine()); // 지원자 수
            int[][] arr = new int[N][2]; // 지원자 정보
            for(int j=0; j<N; j++) {
                String[] person = br.readLine().split(" ");
                arr[j][0] = Integer.parseInt(person[0]);
                arr[j][1] = Integer.parseInt(person[1]);
            }

            Arrays.sort(arr, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[0] - o2[0]; // 서류 등수 오름차순
                }
            });

            int pass = arr[0][1]; // 서류 1등의 면접 점수
            int cnt = N;
            for(int j=1; j<N; j++) {
                // 서류 등수가 더 높은 지원자의 면접 등수보다 더 낮은 등수라면 탈락
                if(pass < arr[j][1]) {
                    cnt--;
                    continue;
                }
                pass = arr[j][1]; // 탈락되지 않은 지원자의 면접 점수
            }
            System.out.println(cnt);
        }
    }
}
