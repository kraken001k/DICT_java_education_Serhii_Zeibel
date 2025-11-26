package CoffeeMachine;

public enum Drinks implements Recipe {
    ESPRESSO(250, 0,   16, 4),
    LATTE   (350, 75,  20, 7),
    CAPPUCCINO(200,100,12, 6);

    private final int water, milk, beans, price;

    Drinks(int water, int milk, int beans, int price) {
        this.water = water; this.milk = milk; this.beans = beans; this.price = price;
    }

    public int water() { return water; }
    public int milk()  { return milk; }
    public int beans() { return beans; }
    public int price() { return price; }

    public static Drinks fromChoice(String choice) {
        return switch (choice) {
            case "1" -> ESPRESSO;
            case "2" -> LATTE;
            case "3" -> CAPPUCCINO;
            default  -> null;
        };
    }
}
