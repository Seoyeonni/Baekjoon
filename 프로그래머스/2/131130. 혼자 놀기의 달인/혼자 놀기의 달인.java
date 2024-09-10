import java.util.*;

class Solution {
    public int solution(int[] cards) {
        int n = cards.length;
        boolean[] visited = new boolean[n];
        ArrayList<Integer> groupSizes = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                int count = 0;
                int current = i;
                
                while (!visited[current]) {
                    visited[current] = true;
                    count++;
                    current = cards[current] - 1;
                }
                
                groupSizes.add(count);
            }
        }
        
        Collections.sort(groupSizes, Collections.reverseOrder());
        
        if (groupSizes.size() < 2) {
            return 0;
        }
        
        return groupSizes.get(0) * groupSizes.get(1);
    }
}