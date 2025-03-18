package baekjoon;

import java.io.*;
import java.util.*;
/*
문제: 2653 안정된 집단
난이도: Gold 4
알고리즘: 구현
풀이 방법: 한 집단내에선 서로 좋아하는 것과 싫어하는 것이 모두 같아야 하므로 결국, 입력되는 값이 같다.
        따라서 그룹리스트를 저장할 수 있는 리스트를 생성하고 각 그룹을 순회하면서 해당 멤버의 입력된 값과
        현재 멤버의 입력된 값을 비교하여 같으면 해당 그룹에 추가하고, 만약 어떤 그룹과도 같지 않다면
        새로운 소그룹을 생성하여 현재 멤버를 집어넣는다.
        그 후, 소집단의 크기가 1인 경우가 있다면 안정된 집단이 아니므로 0을 출력,
        안정된 집단이라면 먼저 각 그룹의 번호를 오름차순으로 정렬해준 후 첫번째 인덱스의 번호, 즉 가장 작은
        번호를 기준으로 오름차순으로 그룹리스트를 정렬해주고 정답을 출력한다.
 */
public class _2653 {

    static int n;
    static int[][] relation;
    static List<List<Integer>> groups = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        relation = new int[n][n];

        for(int i=0; i<n; i++) {
            String[] line = br.readLine().split(" ");
            for(int j=0; j<n; j++) {
                relation[i][j] = Integer.parseInt(line[j]);
            }

            boolean belong = false; // 존재하는 소집단에 속하는지 여부
            for(List<Integer> group : groups) {
                int member = group.get(0); // 집단 멤버
                boolean flag = true;
                for(int j=0; j<n; j++) {
                    // 취향이 다르면 한 집단이 될 수 없음
                    if(relation[member][j] != relation[i][j]) {
                        flag = false;
                        break;
                    }
                }
                if(flag) {
                    belong = true;
                    group.add(i); // 집단에 추가
                }
            }
            // 속하지 않으면 새로운 소집단 생성
            if(!belong) {
                List<Integer> newGroup = new ArrayList<>();
                newGroup.add(i);
                groups.add(newGroup);
            }
        }

        boolean stable = true;
        for(List<Integer> group : groups) {
            if(group.size() == 1) {
                stable = false;
                break;
            }
        }

        if(!stable) {
            System.out.println(0);
        } else { // 안정된 집단일 경우
            System.out.println(groups.size());
            for(List<Integer> group : groups) {
                Collections.sort(group); // 소집단 내 번호 오름차순 정렬
            }

            Collections.sort(groups, new Comparator<List<Integer>>() {
                @Override
                public int compare(List<Integer> o1, List<Integer> o2) {
                    return o1.get(0) - o2.get(0); // 소집단 간의 가장 작은 번호의 오름차순
                }
            });

            for(List<Integer> group : groups) {
                for(int i=0; i<group.size(); i++) {
                    System.out.print((group.get(i) + 1) + " ");
                }
                System.out.println();
            }
        }
    }
}
