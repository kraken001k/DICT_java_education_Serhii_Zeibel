package RockPaperScissors;

public class RockPaperScissors {
    static void main() {
        RatingRepository repo = new RatingRepository("rating.txt");
        new Game(repo).run();
    }
}
