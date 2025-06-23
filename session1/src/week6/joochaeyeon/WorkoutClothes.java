package week6.joochaeyeon;

import java.util.HashSet;
import java.util.Set;

public class WorkoutClothes {
    public int workoutClothes(int n, int[] lost, int[] reserve) {
        Set<Integer> lostSet = new HashSet<>();
        Set<Integer> reserveSet = new HashSet<>();

        for(int l : lost)
            lostSet.add(l);

        for(int r: reserve){
            if(lostSet.contains(r)){
                lostSet.remove(r);
            }
            else{
                reserveSet.add(r);
            }
        }

        for(int l : new HashSet<>(lostSet)){
            if(reserveSet.contains(l-1)){
                reserveSet.remove(l-1);
                lostSet.remove(l);
            }
            else if(reserveSet.contains(l+1)){
                reserveSet.remove(l+1);
                lostSet.remove(l);
            }
        }
        return n-lostSet.size();
    }
}

