package days;

public class Day25 {
    long cardPublic;
    long doorPublic;

    public Day25(long card, long door) {
        cardPublic = card;
        doorPublic = door;
    }

    public long findEncryptionKey() {
        long loops = 0;
        long value = 1;
        while (true) {
            value*=7;
            value = value%20201227;
            loops++;
            if (value == cardPublic) {
                return getKey(loops, doorPublic);
            }
            if (value == doorPublic) {
                return getKey(loops, cardPublic);
            }
        }
    }

    private long getKey(long loops, long otherPublic) {
        long value = 1;
        for (int i = 0; i < loops; i++) {
            value*=otherPublic;
            value = value%20201227;
        }
        return value;
    }
}
