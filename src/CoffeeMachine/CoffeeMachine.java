package CoffeeMachine;

public class CoffeeMachine {

    // ресурси за замовчуванням
    private int water = 400;  // ml
    private int milk  = 540;  // ml
    private int beans = 120;  // g
    private int cups  = 9;    // pcs
    private int money = 550;  // грн

    // стан у автомата
    private enum State { CHOOSING_ACTION, CHOOSING_COFFEE, FILL_WATER, FILL_MILK, FILL_BEANS, FILL_CUPS, OFF }
    private State state = State.CHOOSING_ACTION;

    // підказка для поточного кроку
    public String getPrompt() {
        return switch (state) {
            case CHOOSING_ACTION -> "Write action (buy, fill, take, remaining, exit):";
            case CHOOSING_COFFEE -> "What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back – to main menu:";
            case FILL_WATER      -> "Write how many ml of water do you want to add:";
            case FILL_MILK       -> "Write how many ml of milk do you want to add:";
            case FILL_BEANS      -> "Write how many grams of coffee beans do you want to add:";
            case FILL_CUPS       -> "Write how many disposable cups of coffee do you want to add:";
            case OFF             -> "";
        };
    }

    public boolean isOff() { return state == State.OFF; }

    // бізнес-логіка
    private String lackingResourceFor(Recipe r) {
        if (water < r.water()) return "water";
        if (milk  < r.milk())  return "milk";
        if (beans < r.beans()) return "coffee beans";
        if (cups  < 1)         return "disposable cups";
        return null;
    }

    private void make(Recipe r) {
        water -= r.water();
        milk  -= r.milk();
        beans -= r.beans();
        cups  -= 1;
        money += r.price();
    }

    private String showRemaining() {
        return STR."""
            The coffee machine has:
            \{water} of water
            \{milk} of milk
            \{beans} of coffee beans
            \{cups} of disposable cups
            \{money} of money""";
    }

    // ЄДИНА точка входу для обробки введення користувача
    public String handle(String input) {
        input = input.trim();
        StringBuilder out = new StringBuilder();

        switch (state) {
            case CHOOSING_ACTION -> {
                if (input.equalsIgnoreCase("buy")) {
                    state = State.CHOOSING_COFFEE;
                } else if (input.equalsIgnoreCase("fill")) {
                    state = State.FILL_WATER;
                } else if (input.equalsIgnoreCase("take")) {
                    out.append("I gave you ").append(money);
                    money = 0;
                } else if (input.equalsIgnoreCase("remaining")) {
                    out.append(showRemaining());
                } else if (input.equalsIgnoreCase("exit")) {
                    state = State.OFF;
                } else {
                }
            }
            case CHOOSING_COFFEE -> {
                if ("back".equalsIgnoreCase(input)) {
                    state = State.CHOOSING_ACTION;
                    break;
                }
                Drinks d = Drinks.fromChoice(input);
                if (d != null) {
                    String lack = lackingResourceFor(d);
                    if (lack == null) {
                        out.append("I have enough resources, making you a coffee!");
                        make(d);
                    } else {
                        out.append("Sorry, not enough ").append(lack).append("!");
                    }
                    state = State.CHOOSING_ACTION;
                }
            }
            case FILL_WATER -> {
                water += Integer.parseInt(input);
                state = State.FILL_MILK;
            }
            case FILL_MILK -> {
                milk += Integer.parseInt(input);
                state = State.FILL_BEANS;
            }
            case FILL_BEANS -> {
                beans += Integer.parseInt(input);
                state = State.FILL_CUPS;
            }
            case FILL_CUPS -> {
                cups += Integer.parseInt(input);
                state = State.CHOOSING_ACTION;
            }
            case OFF -> {  }
        }
        return out.toString();
    }
}
