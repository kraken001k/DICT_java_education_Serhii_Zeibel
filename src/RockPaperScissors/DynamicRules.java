package RockPaperScissors;

import java.util.*;

public record DynamicRules(List<String> options) implements Rules {
    public DynamicRules(List<String> options) {
        // убираем дубликаты, сохраняем порядок (всё уже в lowercase)
        LinkedHashSet<String> set = new LinkedHashSet<>(options);
        List<String> cleaned = new ArrayList<>(set);
        if (cleaned.size() < 3) cleaned = Arrays.asList("rock", "paper", "scissors");
        this.options = Collections.unmodifiableList(cleaned);
    }

    @Override
    public int compare(String user, String cpu) {
        if (user.equals(cpu)) return 0;

        int n = options.size();
        int uIdx = options.indexOf(user);

        // элементы после user, затем элементы до user
        List<String> rotated = new ArrayList<>(n - 1);
        for (int i = uIdx + 1; i < n; i++) rotated.add(options.get(i));
        for (int i = 0; i < uIdx; i++) rotated.add(options.get(i));

        int half = rotated.size() / 2;            // «сильнее» user — первые half
        List<String> stronger = rotated.subList(0, half);

        return stronger.contains(cpu) ? -1 : 1;   // иначе user сильнее
    }

    @Override
    public boolean isValid(String move) {
        return options.contains(move);
    }

    @Override
    public String randomMove(Random rnd) {
        return options.get(rnd.nextInt(options.size()));
    }

}
