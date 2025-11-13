package CreditCalculator;

public class CreditCalculator {

    static void main() {
        CalculatorLogic calculator = new CalculatorLogic();

        String type      = System.getProperty("type");
        String principal = System.getProperty("principal");
        String payment   = System.getProperty("payment");
        String periods   = System.getProperty("periods");
        String interest  = System.getProperty("interest");

        calculator.run(type, principal, payment, periods, interest);
    }
}
