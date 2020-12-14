package days;

import java.io.FileNotFoundException;

public class Day14 extends Day {
    long[] values;
    long oneMask;
    long zeroMask;

    public Day14(String filename) throws FileNotFoundException {
        super(filename);
        oneMask = 0L;
        zeroMask = 0L;
        values = new long[largestAddress()];
    }

    public long writeMemory() {
        for (String s : data) {
            if (s.contains("mask")) {
                makeMask(s.split("= ")[1]);
                System.out.println(oneMask);
                System.out.println(zeroMask);
            }
        }
        long sum = 0;
        for (long l : values) {
            sum+=l;
        }
        return sum;
    }

    private int largestAddress() {
        int max = 0;
        for (String s : data) {
            if (s.contains("mem")) {
                String temp = s.split(" = ")[0];
                temp = temp.replace("mem[", "");
                temp = temp.replace("]", "");
                int addr = Integer.parseInt(temp);
                if (addr > max) max = addr;
            }
        }
        return max;
    }

    private void makeMask(String s) {
        for (int power = 35; power > -1; power--) {
            if (s.charAt(35-power) == '1') {
                oneMask+=(1L<<power);
                zeroMask+=(1L<<power);
            }
            else if (s.charAt(35-power) == '0') {
                zeroMask+=(0L<<power);
                oneMask+=(0L<<power);
            }
            else {
                zeroMask+=(1L<<power);
                oneMask+=(0L<<power);
            }
        }
    }
}
