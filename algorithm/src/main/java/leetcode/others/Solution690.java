package leetcode.others;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * description:
 *
 * @author fancying
 * create: 2021-03-28 15:34
 **/
public class Solution690 {

    public static void main(String[] args) {

    }


class Employee {
    public int id;
    public int importance;
    public List<Integer> subordinates;
};


    public int getImportance(List<Employee> employees, int id) {
        Map<Integer, Employee> employeeMap = employees.stream().collect(Collectors.toMap(employee -> employee.id, employee -> employee));
        int r = 0;
        List<Employee> subordinates = Collections.singletonList(employeeMap.get(id));
        List<Employee> tmp = null;
        while (! subordinates.isEmpty()) {
            tmp = new ArrayList<>();
            for (Employee e :subordinates){
                r += e.importance;
                for(int s : e.subordinates){
                    tmp.add(employeeMap.get(s));
                }
            }
           subordinates = tmp;
        }

        return r;
    }


    @Test
    public void testM(){
        long start = System.currentTimeMillis();




        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

}