package baekjoon;

import java.io.*;
import java.util.*;

/*
문제: 9017 크로스 컨트리
난이도: Silver 3
알고리즘: 구현
풀이 방법: 점수를 매길 때 여섯 주자가 안되는 팀의 주자들은 제외시켜야 한다. 따라서, 처음 입력 받을 때 먼저 팀별
        주자들을 카운트하여 배열에 저장한다. 그 다음 입력된 순서대로 팀 번호를 순회하면서 해당 팀 번호의 주자가 6명이 될때만 점수를 매기고 기록하는데,
        이때 HashMap 을 사용하여 key: 팀 번호, value: 주자 점수리스트 형식으로 저장한다.
        점수를 다 매기고 HashMap 에 저장한 뒤엔 key 를 순회하면서 각 팀의 주자 점수리스트를 이용하여 조건에 맞는 우승자를 뽑는다.
 */
public class _9017 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int i=0; i<T; i++) {
            int n = Integer.parseInt(br.readLine());
            String[] line = br.readLine().split(" ");

            int[] cnt = new int[n+1]; // 주자 카운트 배열
            HashMap<Integer, List<Integer>> map = new HashMap<>(); // 각 팀별 점수 기록
            // 각 팀의 주자 카운트
            for(int j=0; j<n; j++) {
                cnt[Integer.parseInt(line[j])] += 1;
            }

            int rank = 1; // 점수
            for(int j=0; j<n; j++) {
                int teamNum = Integer.parseInt(line[j]);
                if(cnt[teamNum] == 6) { // 여섯 주자 참가한 팀만 점수 매김
                    if(!map.containsKey(teamNum)) {
                        map.put(teamNum, new ArrayList<>());
                    }
                    if(map.get(teamNum).size() < 5) {
                        map.get(teamNum).add(rank);
                    }
                    rank++;
                }
            }

            int winner = 0;
            int minSum = Integer.MAX_VALUE;
            int fifthRank = Integer.MAX_VALUE;
            for(Integer team : map.keySet()) {
                List<Integer> rankList = map.get(team);
                // 상위 네명의 주자 점수 합 구하기
                int sum = 0;
                for(int j=0; j<4; j++) {
                    sum += rankList.get(j);
                }
                // 점수 합이 작은 것이 우승
                if(minSum > sum) {
                    minSum = sum;
                    winner = team;
                    fifthRank = rankList.get(4);
                    // 같은 경우 다섯번쨰 주자의 등수 비교
                } else if(minSum == sum) {
                    if(fifthRank > rankList.get(4)) {
                        fifthRank = rankList.get(4);
                        winner = team;
                    }
                }
            }
            System.out.println(winner);
        }
    }
}
