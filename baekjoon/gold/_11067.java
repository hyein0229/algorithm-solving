package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
문제: 11067 모노톤길
난이도: Gold 5
알고리즘: 구현, 정렬
풀이 방법: 개인적 체감으로는 많이 어려웠던 것 같다.. 배열을 일단 정렬해야한다는 것까진 생각했지만 그 이후가 힘들었다.
        카페의 방문 순서는 항상 가장 가까운 거리에 있는 카페를 방문해야 한다.
        또한, x 좌표가 달라질 경우엔 수평 이동을 해야 하므로 반드시 y 좌표는 같아야 한다는 것을 생각해야 한다.

        1. 현재 위치와 같은 x 축에 있는 카페들 중 가까운 카페부터 순서대로 방문 -> y 좌표 정렬 (오름차순일수도 내림차순일수도 있음)
        2. 현재 x 축의 카페들을 모두 방문했다면 y 축이 같은 카페들 중 가장 가까운 카페 방문 -> x 좌표 오름차순 정렬

        어려웠던 것은 1번의 y 좌표 정렬을 어떻게 판단할 것인가 였는데 그것이 쉽게 떠오르지 않았다.
        풀이 방법은 처음엔 x 좌표 오름차순, x 좌표가 같다면 y 좌표를 오름차순으로 정렬한다.
        이 문제는 무조건 수평 또는 수직 이동이므로 x 좌표가 달라질 때 y 좌표가 같은 경우가 반드시!!!!! 존재한다는 것을 기억해야 한다.
        x 좌표가 같은 경우에 임시 리스트에 카페들을 저장한다. 그러다가 x 좌표가 달라지는 경우 마지막으로 배치된 카페의 y 좌표와
        임시 리스트의 첫번째 카페의 y 좌표를 비교한다. (3, 3) 인 카페가 마지막 배치되고 x 축이 5인 카페들을 배치해야할 때
        처음에 카페의 y 좌표를 오름차순 정렬해주었으므로 임시 리스트는 (5, -1), (5, 1), (5, 3) 순 으로 정렬이 되어있을 것이다.
        이 경우 (3, 3) 과 (5, -1)의 y 좌표가 다르므로 수평 이동이 불가하다. 따라서 임시 리스트의 카페들을 y 축 기준 내림차순 정렬하여
        (3, 3), (5, 3), (5, 1), (5, -1) 로 만들어 주어야 한다.
        이렇게 x 좌표가 달라질 때 마지막 배치된 카페와 y 좌표를 비교하여 다른 경우 내림차순 정렬로 바꾸어 주어 배치하면 된다.
 */
public class _11067 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int i=0; i<T; i++) {
            int n = Integer.parseInt(br.readLine());
            // n개의 카페 좌표 입력
            int[][] arr = new int[n][2];
            for(int j=0; j<n; j++) {
                String[] pos = br.readLine().split(" ");
                arr[j][0] = Integer.parseInt(pos[0]);
                arr[j][1] = Integer.parseInt(pos[1]);
            }
            String[] goal = br.readLine().split(" "); // 출력해야 하는 카페 번호 입력

            // x 좌표 오름차순 정렬
            Arrays.sort(arr, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    // x 좌표 오름차순 정렬, x 좌표가 같으면 y 좌표를 오름차순으로 정렬
                    return (o1[0] == o2[0]) ? o1[1] - o2[1] : o1[0] - o2[0];
                }
            });

            List<int[]> cafeList = new ArrayList<>(); // 배치된 카페 리스트
            List<int[]> sameX = new ArrayList<>(); // x 좌표가 같은 카페를 저장하는 임시 리스트
            cafeList.add(new int[]{-1, 0}); // 가상의 카페

            for(int j=0; j<n; j++) {
                // 이전과 x 좌표가 같은 카페라면
                if(j > 0 && arr[j-1][0] == arr[j][0]) {
                    sameX.add(arr[j]);

                } else { // x 좌표가 달라질 때
                    // 현재까지 x 좌표가 같았던 카페들을 배치하기
                    // 배치 완료된 마지막 번호 카페의 y 좌표와 배치해야 할 sameX 리스트의 첫번째 카페의 y 좌표 비교
                    if(!sameX.isEmpty() && cafeList.get(cafeList.size()-1)[1] != sameX.get(0)[1]) { // y좌표가 다르다면
                        // y 좌표 내림차순으로 뒤집기
                        reverse(sameX);
                    }
                    cafeList.addAll(sameX);
                    sameX.clear();
                    sameX.add(arr[j]);
                }
            }

            // 마지막에 x 좌표가 계속 같다면 sameX 리스트의 카페들이 배치 안되므로 그럴 경우 처리
            if(cafeList.size() != n+1) {
                if(cafeList.get(cafeList.size()-1)[1] != sameX.get(0)[1]) {
                    reverse(sameX);
                }
                cafeList.addAll(sameX);
            }
            // 카페 번호에 맞는 좌표 출력하기
            for(int j=1; j<goal.length; j++) {
                int cafeNum = Integer.parseInt(goal[j]);
                int[] cafePos = cafeList.get(cafeNum);
                System.out.println(cafePos[0] + " " + cafePos[1]);
            }
        }
    }

    // 카페들을 y 좌표 기준 내림차순으로 정렬
    public static void reverse(List<int []> list) {
        Collections.sort(list, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1] - o1[1];
            }
        });

    }
}
