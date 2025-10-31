package Hangman;

import java.util.*;

public class hangman {

    // Мій список можливих слів для гри
    private static final String[] WORDS = {"python", "java", "javascript", "kotlin"};
    private static final Random RNG = new Random();

    public static void main(String[] args) {
        System.out.println("HANGMAN");

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Type \"play\" to play the game, \"exit\" to quit: > ");
            String cmd = sc.nextLine().trim().toLowerCase();

            if ("play".equals(cmd)) {
                // Запускаю одну гру
                playGame(sc);
            } else if ("exit".equals(cmd)) {
                // Вихід із програми
                break;
            } else {
                // Будь-що інше — показую меню ще раз
                continue;
            }
        }
    }

    private static void playGame(Scanner sc) {
        // Випадково вибираю слово зі свого списку
        String secret = WORDS[RNG.nextInt(WORDS.length)];

        // Створюю маску слова з дефісів (поки все приховано)
        char[] mask = new char[secret.length()];
        Arrays.fill(mask, '-');

        // Тут я зберігаю всі введені літери (і правильні, і неправильні)
        Set<Character> tried = new HashSet<>();
        // Даю 8 життів (помилок)
        int lives = 8;

        System.out.println(new String(mask));

        while (lives > 0) {
            System.out.print("Input a letter: > ");
            String input = sc.nextLine().trim();

            // Перевіряю, що введено рівно одну літеру
            if (input.length() != 1) {
                System.out.println("You should input a single letter");
                System.out.println(new String(mask));
                continue; // життя не знімаю
            }

            char ch = input.charAt(0);

            // Перевіряю, що це мала англійська літера
            if (ch < 'a' || ch > 'z') {
                System.out.println("Please enter a lowercase English letter");
                System.out.println(new String(mask));
                continue; // життя не знімаю
            }

            // ВАРІАНТ B: штраф за будь-який повтор
            if (tried.contains(ch)) {
                System.out.println("You've already guessed this letter");
                lives--; // знімаю життя за повтор незалежно від правильності
                System.out.println(new String(mask));
                continue;
            }

            // Додаю літеру до списку використаних
            tried.add(ch);

            // Якщо літера є у слові — відкриваю всі її входження
            if (secret.indexOf(ch) >= 0) {
                boolean opened = false;
                for (int i = 0; i < secret.length(); i++) {
                    if (secret.charAt(i) == ch) {
                        mask[i] = ch;
                        opened = true;
                    }
                }
                // Відкриття букв не знімає життя
                System.out.println(new String(mask));

                // Якщо гравець відкрив усе слово — він виграв
                if (new String(mask).equals(secret)) {
                    System.out.println("You guessed the word " + secret + "!");
                    System.out.println("You survived!");
                    return; // повертаюся до головного меню
                }
            } else {
                // Якщо літери немає у слові — повідомляю та зменшую життя
                System.out.println("That letter doesn't appear in the word");
                lives--;
                System.out.println();
                System.out.println(new String(mask));
            }
        }

        // Якщо життя закінчилися — гравець програв
        System.out.println("You lost!");
    }
}
