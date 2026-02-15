package RockPaperScissors;

import java.util.*;

public class Game {
    private final RatingRepository ratingRepo;
    private final Scanner sc = new Scanner(System.in);
    private final Random rnd = new Random();

    public Game(RatingRepository ratingRepo) { this.ratingRepo = ratingRepo; }

    public void run() {
        // 1) имя
        System.out.print("Enter your name: ");
        prompt();
        String name = sc.nextLine().trim();
        System.out.printf("Hello, %s%n", name);

        // 2) стартовый рейтинг
        int score = ratingRepo.loadScore(name);

        // 3) список опций (одной строкой, через запятую). Пусто -> дефолт.
        prompt();
        String line = sc.nextLine();
        List<String> opts = parseOptionsLower(line); // всё в lowercase
        DynamicRules rules = new DynamicRules(opts);
        System.out.println("Okay, let's start");

        // 4) игровой цикл
        while (true) {
            prompt();
            String in = sc.nextLine();
            if (in == null) break;
            in = in.trim().toLowerCase();

            if (in.equals("!exit")) {
                System.out.println("Bye!");
                break;
            }
            if (in.equals("!rating")) {
                System.out.printf("Your rating: %d%n", score);
                continue;
            }
            if (!rules.isValid(in)) {
                System.out.println("Invalid input");
                continue;
            }

            String cpu = rules.randomMove(rnd);
            int res = rules.compare(in, cpu); // 1 win, 0 draw, -1 lose
            if (res > 0) {
                System.out.printf("Well done. The computer chose %s and failed%n", cpu);
                score += 100;
            } else if (res == 0) {
                System.out.printf("There is a draw (%s)%n", cpu);
                score += 50;
            } else {
                System.out.printf("Sorry, but the computer chose %s%n", cpu);
            }
        }
    }

    private static List<String> parseOptionsLower(String line) {
        line = (line == null) ? "" : line.trim();
        if (line.isEmpty()) return Arrays.asList("rock", "paper", "scissors");

        String[] parts = line.split(",");
        List<String> list = new ArrayList<>();
        for (String p : parts) {
            String s = p.trim().toLowerCase();
            if (!s.isEmpty()) list.add(s);
        }
        return list;
    }

    private static void prompt() { System.out.print("> "); }
}
