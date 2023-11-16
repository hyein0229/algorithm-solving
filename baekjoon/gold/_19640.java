package baekjoon;

import java.io.*;
import java.util.*;

/*
문제: 19640 화장실의 규칙
난이도: Gold 4
알고리즘: 구현, 자료구조, 시뮬레이션, 우선순위 큐
풀이 방법: 일렬의 사람들을 최대 M개의 줄로 한 명씩 배치한다. (N < M 인 경우 M개의 줄이 모두 쓰이지 않을 수 있다)
        각 줄의 선두끼리 주어진 규칙에 따라 근무 일수, 화장실이 급한 정도, 줄 번호를 비교하여 다음 사용할 사람을 정해주어야 하는데
        이때 우선순위 큐를 이용하여 알아서 우선순위를 비교하여 가장 우선순위가 높은 사람을 추출할 수 있도록 한다.
        추출된 사람의 번호가 데카의 번호인 K+1 이면 탐색을 종료한다.
 */
public class _19640 {

    static class Person {
        int day;
        int hurry;
        int lineNum;
        int num;

        public Person(int day, int hurry, int lineNum, int num)  {
            this.day = day;
            this.hurry = hurry;
            this.lineNum = lineNum;
            this.num = num;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int N = Integer.parseInt(line[0]);
        int M = Integer.parseInt(line[1]);
        int K = Integer.parseInt(line[2]);

        // M 개의 줄로 사람 나누기
        Map<Integer, List<Person>> linePeople = new HashMap<>();
        for(int i=0; i<N; i++) {
            String[] p = br.readLine().split(" ");
            int d = Integer.parseInt(p[0]); // 근무 일수
            int h = Integer.parseInt(p[1]); // 화장실 급한 정도
            int lineNum = (i % M) + 1; // 줄 번호
            int n = i+1; // 몇번째 사람인지, 첫번째 사람 1번부터 시작

            if(!linePeople.containsKey(lineNum)) {
                linePeople.put(lineNum, new ArrayList<>());
            }
            linePeople.get(lineNum).add(new Person(d, h, lineNum, n)); // 해당 줄에 사람 배치
        }

        // 우선순위 큐
        PriorityQueue<Person> waitingPeople = new PriorityQueue<>(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                if(o1.day == o2.day) { // 근무 일수가 같은 경우
                    if(o1.hurry == o2.hurry) { // 급한 정도가 같은 경우
                        return o1.lineNum - o2.lineNum; // 줄 번호가 작은 순
                    }
                    return o2.hurry - o1.hurry; // 급한 정도가 높은 순
                }
                return o2.day - o1.day; // 근무 일수가 높은 순
            }
        });

        // 각 줄 별 선두를 우선순위 큐에 삽입
        for(int i=1; i<=linePeople.keySet().size(); i++) {
            if(!linePeople.get(i).isEmpty()) {
                waitingPeople.add(linePeople.get(i).remove(0));
            }
        }

        int cnt = 0; // 사용한 사람 수 카운트
        while(!waitingPeople.isEmpty()) {
            Person person = waitingPeople.poll();
            cnt++;

            // 데카의 차례라면 종료
            if(person.num == K+1) {
                break;
            }
            // 현재 사람이 빠지고 새로운 선두를 큐에 삽입
            if(!linePeople.get(person.lineNum).isEmpty()) {
                waitingPeople.add(linePeople.get(person.lineNum).remove(0));
            }
        }
        System.out.println(cnt-1); // 데카의 차례까지 세었으므로 앞 사원들의 수는 -1
    }
}
