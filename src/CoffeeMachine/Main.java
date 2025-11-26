package CoffeeMachine;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CoffeeMachine m = new CoffeeMachine();
        Scanner sc = new Scanner(System.in);

        if (!m.isOff() && !m.getPrompt().isEmpty()) {
            System.out.println(m.getPrompt());
            System.out.print("> ");
        }

        while (!m.isOff() && sc.hasNextLine()) {
            String line = sc.nextLine();

            String out = m.handle(line);
            if (!out.isEmpty()) System.out.println(out);

            if (m.isOff()) break;

            String prompt = m.getPrompt();
            if (!prompt.isEmpty()) {
                System.out.println(prompt);
                System.out.print("> ");
            }
        }
        sc.close();
    }
}
