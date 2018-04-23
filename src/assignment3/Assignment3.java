/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Data Mining Assignment 3
 *
 * @author Tiffany Tran and Miranda Brawner
 *
 */
public class Assignment3 {

    public static void main(String[] args) {
        Record r1 = new Record(0.2, 0.5, 4.8, 3.0);
        Record r2 = new Record(0.7, 2.5, 4.0, 3.2);
        Record r3 = new Record(2.6, 5.0, 5.2, 1.0);
        Record r4 = new Record(1.25, 5.5, 6.2, 8.0);
        Record r5 = new Record(4.0, 8.0, 6.0, 5.0);
        Record r6 = new Record(1.0, 7.0, 2.0, 7.0);
        Record r7 = new Record(2.0, 5.3, 3.0, 2.0);
        Record r8 = new Record(3.0, 2.5, 4.5, 1.5);
        Record r9 = new Record(1.75, 0.2, 6.0, 2.75);
        Record r10 = new Record(4.5, 3.0, 5.0, 6.0);
        ArrayList<Record> t = new ArrayList<>();
        t.add(r1);
        t.add(r2);
        t.add(r3);
        t.add(r4);
        t.add(r5);
        t.add(r6);
        t.add(r7);
        t.add(r8);
        t.add(r9);
        t.add(r10);

        for (int i = 0; i < t.size(); i++) {
            System.out.println("r" + (i + 1));
            fuzzy(t.get(i));
        }

//        t.forEach((r) -> {
//            
//            fuzzy(r);
//        });
    }

    /* Calculate the degrees of membership for a record. Retrieve the minimum
    value from each degree of membership for each p...*/
    public static void fuzzy(Record r) {
        double[] R = radiation(r);
        double[] V = vibration(r);
        double[] M = magnetic(r);
        double[] F = fume(r);

        /* Calculate the low rules for GPX: min(low(R), med(V), low(M)) = 1
        There is only one low rule therefore this is the max value. */
        double low = Math.min(Math.min(R[0], V[1]), M[1]);

        /* Calculate the medium rules for GPX:
            Rule 2) min(med(R), med(M)) = 2
            Rule 4) min(low(R), med(V), med(M), med(F)) = 2 
            Calculate the maximum between the two rules for the GPX medium value. */
        double medium = Math.max(Math.min(R[1], M[1]),
                Math.min(Math.min(Math.min(R[0], V[1]), M[1]), F[1]));

        /* Calculate the high rules for GPX: 
            Rule 3) min(high(R), high(M), high(F)) = 3
            Rule 4) min(low(R), high(M), low(F)) = 3
            Calculate the maxmimum between the two rules for the GPX high value.*/
        double high = Math.max(Math.min(Math.min(R[2], M[2]), F[2]),
                Math.min(Math.min(R[0], M[2]), F[1]));
        System.out.println("GPX Low: " + low);
        System.out.println("GPX Medium :  " + medium);
        System.out.println("GPX High : " + high);

    }

    /* Return the degree of membership for Radiation (R) in a record.
    The degree of membership are saved in an int array called result with
    a size of 3. result[0] holds the value for low. result[1] holds the
    value for medium. result[2] holds the value for high.*/
    public static double[] radiation(Record r) {
        double[] result = new double[3];
        double x = r.radiation;

        if (x >= 0.0 && x <= 0.5) { //low
            result[0] = 1.0;
        }
        if (x >= 0.5 && x <= 1.0) { //low
            result[0] = -2.0 * x + 2.0;
        }
        if (x >= 0.5 && x <= 1.5) { //medium
            result[1] = x - 0.5;
        }
        if (x >= 1.5 && x <= 2.0) { //medium
            result[1] = 1.0;
        }
        if (x >= 2.0 && x <= 3.5) { //medium
            result[1] = ((-1.0 / 1.5) * x) + (7.0 / 3.0);
        }
        if (x >= 2.5 && x <= 3.0) { //high
            result[2] = 2.0 * x - 5.0;
        }
        if (x >= 3.0) { //high
            result[2] = 1.0;
        }
        return result;
    }

    /* Return the degree of membership for Vibration (V) for a record.
    The degree of membership are saved in an int array called result with
    a size of 3. result[0] holds the value for low. result[1] holds the
    value for medium. result[2] holds the value for high.*/
    public static double[] vibration(Record r) {
        double[] result = new double[3];
        double x = r.vibration;

        if (x >= 0.0 && x <= 1.0) { //low
            result[0] = x;
        }
        if (x >= 1.0 && x <= 2.0) { //low
            result[0] = -1.0 * x + 2.0;
        }
        if (x >= 2.0 && x <= 3.0) { //medium
            result[1] = x - 2.0;
        }
        if (x >= 3.0 && x <= 4.0) { //medium
            result[1] = 1.0;
        }
        if (x >= 4.0 && x <= 7.0) { //medium
            result[1] = ((-1.0 / 3.0) * x) + (7.0 / 3.0);
        }
        if (x >= 4.0 && x <= 6.0) { //high
            result[2] = (1.0 / 2.0) * x - 2.0;
        }
        if (x >= 6.0) { //high
            result[2] = 1;
        }
        return result;
    }

    /* Return the degree of membership for Magnetic (M) for a record.
    The degree of membership are saved in an int array called result with
    a size of 3. result[0] holds the value for low. result[1] holds the
    value for medium. result[2] holds the value for high.*/
    public static double[] magnetic(Record r) {
        double[] result = new double[3];
        double x = r.magnetic;

        if (x >= 1.0 && x <= 5.5) { //low
            result[0] = ((-1.0 / 4.5) * x) + (5.5 / 4.5);
        }
        if (x >= 1.5 && x <= 3.0) { //medium
            result[1] = ((1.0 / 1.5) * x) - 1.0;
        }
        if (x >= 3.0 && x <= 4.0) { //medium
            result[1] = 1.0;
        }
        if (x >= 4.0 && x <= 6.5) { //medium
            result[1] = ((-1.0 / 2.5) * x) + 2.6;
        }
        if (x >= 3.5 && x <= 6.0) { //high
            result[2] = (1.0 / 2.5) * x - 1.4;
        }
        if (x >= 6.0) { //high
            result[2] = 1;
        }
        if (x >= 0.0 && x <= 1.0) { //low
            result[0] = 1;
        }
        if (x >= 1.0 && x <= 5.5) { //low
            result[0] = ((-1.0 / 4.5) * x) + (5.5 / 4.5);
        }
        if (x >= 1.5 && x <= 3.0) { //medium
            result[1] = ((1.0 / 1.5) * x) - 1.0;
        }
        if (x >= 3.0 && x <= 4.0) { //medium
            result[1] = 1.0;
        }
        if (x >= 4.0 && x <= 6.5) { //medium
            result[1] = ((-1.0 / 2.5) * x) + 2.6;
        }
        if (x >= 3.5 && x <= 6.0) { //high
            result[2] = (1.0 / 2.5) * x - 1.4;
        }
        if (x >= 6.0) { //high
            result[2] = 1;
        }
        return result;
    }

    /* Return the degree of membership for Fume (F) for a record.
    The degree of membership are saved in an int array called result with
    a size of 3. result[0] holds the value for low. result[1] holds the
    value for medium. result[2] holds the value for high.*/
    public static double[] fume(Record r) {
        double[] result = new double[3];
        double x = r.magnetic;

        if (x >= 0.0 && x <= 0.5) { //low
            result[0] = (1.0 / 2.0) * x;
        }
        if (x >= 0.5 && x <= 3.5) { //low
            result[0] = ((-1.0 / 3.0) * x) + 1.16666666666;
        }
        if (x >= 1.5 && x <= 3.5) { //medium
            result[1] = ((1.0 / 2.0) * x) - (3.0 / 4.0);
        }
        if (x >= 3.5 && x <= 7.0) { //medium
            result[1] = ((-1.0 / 3.5) * x) + 2.0;
        }
        if (x >= 2.0 && x <= 7.3) { //high
            result[2] = (1.0 / 5.3) * x - (2.0 / 5.3);
        }
        if (x >= 7.3) { //high
            result[2] = 1;
        }
        return result;
    }

    public static class Record {

        public double radiation;
        public double vibration;
        public double magnetic;
        public double fume;

        private Record() {
            radiation = 0.0;
            vibration = 0.0;
            magnetic = 0.0;
            fume = 0.0;
        }

        private Record(double radiation, double vibration,
                double magnetic, double fume) {
            this.radiation = radiation;
            this.vibration = vibration;
            this.magnetic = magnetic;
            this.fume = fume;

        }

    }
}
