package com.victor.design.app;

public class GPAHelper {

    public static int sumArray(int[] a) {
        int total = 0;
        for (int i : a) {
            total += i;
        }
        return total;
    }

    public static boolean validateGrades(String[] grades) {
        for (String g : grades) {
            if (!(g.equalsIgnoreCase("A") || g.equalsIgnoreCase("B") || g.equalsIgnoreCase("C")
                    || g.equalsIgnoreCase("D") || g.equalsIgnoreCase("E") || g.equalsIgnoreCase("F")))
                return false;
        }
        return true;
    }

    public static int getGrade(String s) {
        if (s.equalsIgnoreCase("A"))
            return 5;
        else if (s.equalsIgnoreCase("B"))
            return 4;
        else if (s.equalsIgnoreCase("C"))
            return 3;
        else if (s.equalsIgnoreCase("D"))
            return 2;
        else if (s.equalsIgnoreCase("E"))
            return 1;
        else if (s.equalsIgnoreCase("F"))
            return 0;
        return 0;
    }

    public static double calculate(int g[], int[] u) {
        /*
        * g = Grade i.e 0 - 5
        * u = Course Unit
        * */

        double totalCourseUnit = sumArray(u);
        double totalWeight = 0;
        for (int i = 0; i < g.length; i++) {
            totalWeight += (g[i] * u[i]);
        }

        return (totalWeight / totalCourseUnit);
    }
}
