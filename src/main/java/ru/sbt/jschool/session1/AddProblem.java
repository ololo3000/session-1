package ru.sbt.jschool.session1;

import java.util.ArrayList;
import java.util.HashMap;

public class AddProblem {

    public long sumOfBinary(String b1, String b2) {
        b1 = new StringBuilder(b1).reverse().toString();
        b2 = new StringBuilder(b2).reverse().toString();

        long result = 0;
        short n = 0;

        for (int i = 0; i < Math.max(b1.length(), b2.length()); i++) {
            if (i < b1.length() && b1.charAt(i) == '1') {
                ++n;
            }

            if (i < b2.length() && b2.charAt(i) == '1') {
                ++n;
            }

            if (n % 2 != 0) {
                result += Math.pow(10, i);
            }

            if (n < 2) {
                n = 0;
            } else {
                n = 1;
            }

        }
        if (n == 1 ) {
            result += Math.pow(10, b1.length());
        }

        return result;
    }


    public long[] intersection(long[] arr1, long[] arr2) {
        ArrayList<Long> result =  new ArrayList<>();
        HashMap<Long, Integer> map = new HashMap<Long, Integer>();
        for (long l : arr1) {
            if (!map.containsKey(l)) {
                map.put(l , 1);
            }
        }

        for (long l : arr2) {
            if (map.containsKey(l)) {
                result.add(l);
            }
        }

        long[] out = new long[result.size()];
        for(int i = 0; i < result.size(); i++) {
            out[i] = result.get(i);
        }

        return out;
    }

    public long binaryToDec(String binary) {
        binary = new StringBuilder(binary).reverse().toString();
        int i = 0;
        long result = 0;
        for (char ch : binary.toCharArray()) {
            if (ch == '1') {
                result = result + (int)Math.pow(2, i);
            }
            i++;
        }

        return result;
    }
}
