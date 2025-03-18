import java.util.*;
/*
문제: 과제 진행하기
난이도: level 2
알고리즘: 구현, 그리디
풀이 방법: 먼저, PriorityQueue를 사용하여 과제를 시작 시간 오름차순으로 정렬한다.
        그 다음, pq에서 과제를 하나씩 꺼내면서 수행하는데 이때 이전에 꺼낸 과제를 저장해두고
        이전 과제 시작 시간과 현재 과제 시작 시간 사이의 시간을 구한 다음 주어진 조건에 따라 수행한다.
        1. 이전 과제를 다 끝낼 수 있는 경우 2. 다 못끝내는 경우 대기 Stack에 해당 과제 삽입
        만약, 1번에서 과제를 다 끝낸 후에도 남은 시간이 있다면 그 시간 동안
        대기 Stack 에서 peek 한 과제를 수행한다.
*/
class Solution {
    
    class HomeWork implements Comparable<HomeWork>{
        String name;
        int start;
        int playtime;
        
        public HomeWork(String name, int start, int playtime) {
            this.name = name;
            this.start = start;
            this.playtime = playtime;
        }
        
        @Override
        public int compareTo(HomeWork o) {
            return this.start - o.start; // 시작 시간이 빠른 순
        }
    }
    
    public String[] solution(String[][] plans) {
        int n = plans.length;
        String[] answer = new String[n];
        
        PriorityQueue<HomeWork> pq = new PriorityQueue<>();
        Stack<HomeWork> waiting = new Stack<>();
        List<HomeWork> done = new ArrayList<>();
        
        for(int i=0; i<n; i++) {
            String name = plans[i][0];
            String start = plans[i][1];
            int playTime = Integer.parseInt(plans[i][2]);
            int h = Integer.parseInt(start.split(":")[0]);
            int m = Integer.parseInt(start.split(":")[1]);
            pq.add(new HomeWork(name, (60*h) + m, playTime));
        }
        
        HomeWork now = pq.poll();
        while(!pq.isEmpty()) {
            HomeWork next = pq.poll(); // 그 다음 시작해야할 과제
            int left = next.start - now.start; // 이전 과제 시작 시간과 현재 과제 시작 시간 사이 시간
            
            // 현재 과제를 끝낼 수 있음
            if(left >= now.playtime) {
                left -= now.playtime;
                done.add(now); // 끝낸 목록에 삽입
                
                if(left > 0) { // 시간이 남는다면
                    while(!waiting.isEmpty() && left != 0) { // 남은 시간을 다 쓸 때까지
                        HomeWork hw = waiting.peek(); // 가장 최근에 멈춘 과제
                        if(hw.playtime > left) { // 다 못끝내는 경우
                            hw.playtime -= left;
                            left = 0;
                        } else {
                            left -= hw.playtime; 
                            done.add(waiting.pop()); // 과제를 다 끝냄, 끝낸 목록에 삽입
                        }
                    }
                } 
                
            } else if(left < now.playtime){ // 과제를 다 끝내지 못하고 멈춤
                now.playtime -= left; // 수행한 시간만큼만 감소
                waiting.add(now); // 대기 스택에 삽입
            } 
            now = next;
        }
        done.add(now); // 마지막 과제 넣기
        
        while(!waiting.isEmpty()) { // 멈춘 과제까지 다 수행
            done.add(waiting.pop());
        }
    
        for(int i=0; i<n; i++) {
            answer[i] = done.get(i).name;
        }
    
        return answer;
    }
}
