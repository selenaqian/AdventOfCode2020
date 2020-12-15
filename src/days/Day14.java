package days;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day14 extends Day {
    long[] values;
    long oneMask;
    long zeroMask;
    Map<Long, Long> valueMap; //maps addresses to values

    public Day14(String filename) throws FileNotFoundException {
        super(filename);
        oneMask = 0L;
        zeroMask = 0L;
        values = new long[largestAddress()+1];
        valueMap = new HashMap<>();
    }

    public long writeMemory() {
        for (String s : data) {
            if (s.contains("mask")) {
                makeMask(s.split("= ")[1]);
            }
            else {
                String[] split = s.split("] = ");
                long val = Long.parseLong(split[1]);
                int index = Integer.parseInt(split[0].replace("mem[", ""));
                values[index] = (val & zeroMask) | oneMask;
            }
        }
        long sum = 0L;
        for (int i=0; i < values.length; i++) {
            sum+=values[i];
        }
        return sum;
    }

    public long writeMemory2() {
        String mask = "";
        for (String s : data) {
            if (s.contains("mask")) {
                mask = s.split("= ")[1];
            }
            else {
                String address = s.split("] = ")[0];
                Long value = Long.parseLong(s.split("] = ")[1]);
                address = address.replace("mem[", "");
                address = String.format("%36s", Long.toBinaryString(Long.parseLong(address))).replace(" ", "0");
                for (int i = 0; i < address.length(); i++) {
                    if (mask.charAt(i) == '1') {
                        address = address.substring(0, i) + '1' + address.substring(i+1);
                    }
                    else if (mask.charAt(i) == 'X') {
                        address = address.substring(0, i) + 'X' + address.substring(i+1);
                    }
                }
                List<Long> addresses = findAddresses(address, new ArrayList<>());
                for (long addr : addresses) {
                    valueMap.put(addr, value);
                }
            }
        }
        long sum = 0L;
        for (long address : valueMap.keySet()) {
            sum+=valueMap.get(address);
        }
        return sum;
    }

    private List<Long> findAddresses(String address, List<Long> allAddresses) {
        if (allAddresses == null) allAddresses = new ArrayList<>();
        if (!address.contains("X")) allAddresses.add(Long.parseLong(address));
        else {
            int index = address.indexOf('X');
            String one = address.substring(0, index) + '1' + address.substring(index+1);
            String zero = address.substring(0, index) + '0' + address.substring(index+1);
            findAddresses(one, allAddresses);
            findAddresses(zero, allAddresses);
        }
        return allAddresses;
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
        oneMask = 0L;
        zeroMask = 0L;
        for (int power = 35; power > -1; power--) {
            if (s.charAt(35-power) == '1') {
                oneMask+=(1L<<power);
                zeroMask+=(1L<<power);
            }
            else if (s.charAt(35-power) == 'X') {
                zeroMask+=(1L<<power);
            }
        }
    }
}
