package baekjoon;

import java.io.*;
import java.util.*;
/*
문제: 14676 영우는 사기꾼?
난이도: Gold 3
알고리즘: 구현, 그래프
풀이 방법: 시간초과 문제가 있었지만 구현이 그렇게 어렵진 않았다.
        건설 명령이 있을 땐 건설할 건물에 영향을 주는 건물들이 전부 건설되어 있는지 확인하여 그렇다면 건설한다.
        파괴 명령이 있으면 파괴할 건물이 이미 건설되어 있는지 확인하고 건설되어 있으면 파괴, 아니면 Lier 출력한다.
        처음에 Map 에 key : 건물, value: 영향을 받는 건물 로 하여 각 건물마다 미리 건설되어 있어야 하는 건물들을 저장해놓고
        건설 시에 해당 건물들을 for문으로 건설되어 있는지 확인하였는데 87%에서 시간초과 일어났다.
        건물은 중복 건설이 될 수 있으므로 건물을 건설할 때마다 계속하여 건물을 건설할 수 있는지 for문으로 체크한다면 비효율적이다.
        따라서, boolean 형 now 배열을 만들어 현재 건물을 건설할 수 있는 상태인지를 저장할 수 있도록 하여
        이미 건설 여부가 true 가 된 건물들에 대해서는 중복 체크하지 않도록 하여 시간을 줄였다.
        그리고 Map2를 만들어 각 건물마다 영향을 주는 건물들도 저장할 수 있도록 한 다음 파괴 시에
        해당 건물이 영향을 주는 건물들의 now 값을 false 로 변경하여 나중에 건설 여부를 다시 검사할 수 있도록 하였다.
 */
public class _14676 {

    static int N, M, K;
    static Map<Integer, List<Integer>> map1 = new HashMap<>();
    static Map<Integer, List<Integer>> map2 = new HashMap<>();
    static int[] building;
    static boolean[] now;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);
        K = Integer.parseInt(line[2]);
        building = new int[N+1];
        now = new boolean[N+1];

        for(int i=1; i<=N; i++) {
            map1.put(i, new ArrayList<>());
            map2.put(i, new ArrayList<>());
        }

        for(int i=0; i<M; i++) {
            line = br.readLine().split(" ");
            int X = Integer.parseInt(line[0]);
            int Y = Integer.parseInt(line[1]);
            map1.get(Y).add(X);
            map2.get(X).add(Y); // X -> Y 영향
        }

        boolean answer = true;
        for(int i=0; i<K; i++) {
            line = br.readLine().split(" ");
            int op = Integer.parseInt(line[0]);
            int target = Integer.parseInt(line[1]);

            if(op == 1) { // 건설
                if(now[target]) {
                    building[target]++; // 건설 가능
                } else {
                    boolean possible = true;
                    for(Integer num : map1.get(target)) { // 건물들이 먼저 건설되어 있는지 확인
                        if(building[num] == 0) {
                            possible = false;
                            break;
                        }
                    }

                    if(possible) {
                        building[target]++; // 건설 가능
                        now[target] = true;
                    } else {
                        answer = false;
                        break;
                    }
                }

            } else { // 파괴
                if(building[target] > 0) { // 건설되어 있으면 파괴
                    building[target]--;
                    if(building[target] == 0) { // 파괴 후 건물이 하나도 없다면
                        for(Integer num : map2.get(target)) {
                            now[num] = false;
                        }
                    }
                } else {
                    answer = false;
                    break;
                }
            }
        }

        if(answer) {
            System.out.println("King-God-Emperor");
        } else {
            System.out.println("Lier!");
        }
    }
}
