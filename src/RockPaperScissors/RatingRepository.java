package RockPaperScissors;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public record RatingRepository(String path) {

    public int loadScore(String name) {
        File f = new File(path);
        if (!f.exists()) return 0;
        try (Scanner fs = new Scanner(f)) {
            while (fs.hasNextLine()) {
                String line = fs.nextLine().trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split("\\s+");
                if (parts.length >= 2 && parts[0].equals(name)) {
                    try {
                        return Integer.parseInt(parts[1]);
                    } catch (NumberFormatException ignored) {
                    }
                }
            }
        } catch (FileNotFoundException ignored) {
        }
        return 0;
    }
}
