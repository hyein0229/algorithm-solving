/*
문제: 135807 숫자 카드 나누기
난이도: Level 2
알고리즘: 시뮬레이션
풀이 방법: 각각 배열에서의 최대 공약수를 구한 후, 구한 최대 공약수로 나머지 다른 배열의 숫자들을 모두 나눌 수 없          는지 확인하고 둘 중 최대값이 정답이 된다. 최대 공약수만 구해서 확인해주어도 되는 이유는??
        만약 배열 A의 최대 공약수로 배열 B의 숫자 중 나눌 수 있는 것이 있다면 
        --> 최대 공약수의 약수로로 당연히 나눌 수 있다.
        최대 공약수로는 나눌 수 없지만 최대 공약수의 약수로는 나누어지는 숫자가 있어도??
        --> 어차피 나눌 수 없는 가장 큰 양의 정수를 찾으므로 고려하지 않아도 된다! 
*/

class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;
        int len = arrayA.length;
        
        // 각 배열에서의 최대 공약수 구하기
        int gcdA = arrayA[0];
        int gcdB = arrayB[0];
        for(int i=1; i<len; i++){
            gcdA = gcd(arrayA[i], gcdA);
            gcdB = gcd(arrayB[i], gcdB);
        }
        
        //나머지 배열을 나눌 수 있는지 없는지 확인
        if(!div(arrayB, gcdA)){
            answer = Math.max(answer, gcdA);
        }
        
        if(!div(arrayA, gcdB)){
            answer = Math.max(answer, gcdB);
        }
        
        return answer;
    }
    
    public static int gcd(int a, int b){
        if(b==0) return a;
        else return gcd(b, a % b);
    }
    
    public static boolean div(int[] array, int gcd){
        for(int num : array){
            if(num % gcd == 0) return true; // 하나라도 나눌 수 있으면 true 반환
        }
        return false;
    }
}
