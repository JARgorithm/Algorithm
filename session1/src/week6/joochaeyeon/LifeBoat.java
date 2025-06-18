package week6.joochaeyeon;

import java.util.Arrays;

public class LifeBoat {
    public int lifeBoat(int[] people, int limit) {
        int answer = 0;
        Arrays.sort(people);
        int left=0;
        int right=people.length-1;

        while(left<=right){
            if(people[left]+people[right] <=limit){
                left++;
            }
            right--;
            answer++;
        }



        return answer;
    }
}