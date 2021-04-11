package leetcode.topological;


import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * description:
 *
 * @author fancying
 * create: 2021-03-28 15:34
 **/
public class Solution207 {

    public static void main(String[] args) {
        int numCourses = 2;
        int [][] prerequisites = {{1,0},{0,1}};

        System.out.println(canFinish(numCourses, prerequisites));
    }


    /**
    * 拓扑排序：
    */
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> edges = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            edges.add(new LinkedList<>());
        }

        int[] inDegree = new int[numCourses];
        Arrays.fill(inDegree,0);

        for (int[] prerequisite : prerequisites) {
            inDegree[prerequisite[0]]++;
            edges.get(prerequisite[1]).add(prerequisite[0]);
        }

        Queue<Integer> studiedCourse = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                studiedCourse.add(i);
            }
        }
        int num = 0;
        while (! studiedCourse.isEmpty()) {
            int course = studiedCourse.poll();
            num++;
            for (Integer i : edges.get(course)) {
                inDegree[i]--;
                if (inDegree[i] == 0) {
                    studiedCourse.add(i);
                }
            }
        }

        return num == numCourses;
    }

}