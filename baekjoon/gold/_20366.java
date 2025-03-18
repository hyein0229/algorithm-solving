package baekjoon;

import java.io.*;
import java.util.Arrays;
/*
문제: 20366 같이 눈사람 만들래?
난이도: Gold 3
알고리즘: 정렬, 투포인터
풀이 방법: 먼저, 눈덩이의 지름을 오름차순 정렬한다.
        그 다음, 이중포문으로 만들 수 있는 하나의 눈사람을 먼저 만드는데, 이때 i, j의 간격은
        2 이상으로 하여 그 사이의 눈덩이들을 투포인터를 이용하여 나머지 눈사람을 만들 수 있도록 하였다.
        이중포문으로 만든 눈사람을 눈사람1, 나머지 눈사람을 눈사람2로 하였을 때
        눈사람1이 눈사람2보다 크다면 차이를 줄이기 위해 눈사람2를 커지게 하기 위해서 왼쪽 포인터를 증가시키고
        작다면 오른쪽 포인터를 감소시켜 크기를 줄이도록 하면서 최소 차이를 구하면 된다.
 */
public class _20366 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        String[] line = br.readLine().split(" ");
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(line[i]);
        }
        Arrays.sort(arr);
        System.out.println(getMinDiff(N, arr));
    }

    public static int getMinDiff(int N, int[] arr) {
        int minDiff = Integer.MAX_VALUE;
        for(int i=0; i<N-3; i++) {
            for(int j=i+3; j<N; j++) {
                int snowMan1 = arr[i] + arr[j];
                int left = i+1;
                int right = j-1;
                while(left < right) {
                    int snowMan2 = arr[left] + arr[right];
                    if(snowMan1 == snowMan2) { // 둘의 키가 같으면
                        return 0;
                    }
                    minDiff = Math.min(minDiff, Math.abs(snowMan1 - snowMan2));
                    if(snowMan1 > snowMan2) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return minDiff;
    }
}
