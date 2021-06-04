package model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Topics {
    Sport,
    Movies,
    Health,
    Cooking;

    private static final List<Topics> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static Topics randomTopic() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

    public static boolean equals(Topics topic1, Topics topic2) {
        if(topic1.name().compareTo(topic2.name()) == 0) {
            return true;
        }
        return false;
    }


}
