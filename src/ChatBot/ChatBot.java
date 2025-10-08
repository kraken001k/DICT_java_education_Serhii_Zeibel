package ChatBot;

import java.time.Year;
import java.util.Scanner;

public class ChatBot {
    public static void main(String[] args) {
        String botName = "Test_Bot";
        int birthYear = Year.now().getValue();

        System.out.println("Hello! My name is " + botName + ".");
        System.out.println("I was created in " + birthYear + ".");
        System.out.println("Please, remind me your name.");

        Scanner scanner = new Scanner(System.in);
        String userName = scanner.nextLine();
        System.out.println("What a great name you have, " + userName + "!");
        System.out.println();

        // Етап 3 — здогадуємося про вік
        System.out.println("Let me guess your age.");
        System.out.println("Enter remainders of dividing your age by 3, 5 and 7.");
        int rem3 = scanner.nextInt();
        int rem5 = scanner.nextInt();
        int rem7 = scanner.nextInt();
        int age = (rem3 * 70 + rem5 * 21 + rem7 * 15) % 105;
        System.out.println("Your age is " + age + "; that's a good time to start programming!");
        System.out.println();

        // Етап 4 — рахуємо від 0 до введеного числа (включно)
        System.out.println("Now I will prove to you that I can count to any number you want!");
        int n = scanner.nextInt(); // позитивне число
        for (int i = 0; i <= n; i++) {
            System.out.println(i + "!");
        }
        System.out.println();

        // Етап 5 — тест з варіантами та перевіркою
        System.out.println("Let's test your programming knowledge.");
        System.out.println("Which of the following keywords is used to define a constant in JavaScript?");
        System.out.println("1. var");
        System.out.println("2. let");
        System.out.println("3. const");
        System.out.println("4. function");

        int correct = 3;
        while (true) {
            int answer = scanner.nextInt();
            if (answer == correct) {
                System.out.println("Congratulations! That is correct.");
                break;
            } else {
                System.out.println("That answer is not correct");
                System.out.println("Please, try again.");
            }
        }

        System.out.println("Goodbye, have a nice day!");
    }
}
