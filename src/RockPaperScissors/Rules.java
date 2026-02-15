package RockPaperScissors;

import java.util.Random;

public interface Rules {
    int compare(String user, String cpu);   // 1 win, 0 draw, -1 lose
    boolean isValid(String move);
    String randomMove(Random rnd);
}
