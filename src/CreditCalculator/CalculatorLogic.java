package CreditCalculator;

public class CalculatorLogic {

    public void run(String type,
                    String principalStr,
                    String paymentStr,
                    String periodsStr,
                    String interestStr) {

        if ((!"annuity".equals(type) && !"diff".equals(type))) {
            printIncorrect();
            return;
        }

        // відсотки обов'язкові завжди
        if (interestStr == null) {
            printIncorrect();
            return;
        }

        Double principal = parsePositive(principalStr);
        Double payment   = parsePositive(paymentStr);
        Double periods   = parsePositive(periodsStr);
        Double interest  = parsePositive(interestStr);

        // якщо щось передано, але не вдалося коректно розпарити (>0)
        if ((principalStr != null && principal == null)
                || (paymentStr != null && payment == null)
                || (periodsStr != null && periods == null)
                || interest == null) {
            printIncorrect();
            return;
        }

        if ("diff".equals(type)) {
            if (principal == null || periods == null || payment != null) {
                printIncorrect();
                return;
            }
            calculateDifferential(principal, periods.intValue(), interest);
        } else { // annuity
            int missing = 0;
            if (principal == null) missing++;
            if (payment == null)   missing++;
            if (periods == null)   missing++;

            // має бути рівно один невідомий параметр
            if (missing != 1) {
                printIncorrect();
                return;
            }

            if (payment == null) {
                // вважаємо щомісячний ануїтетний платіж
                calculateAnnuityPayment(principal, periods.intValue(), interest);
            } else if (principal == null) {
                // вважаємо суму кредиту
                calculatePrincipal(payment, periods.intValue(), interest);
            } else {
                // рахуємо кількість місяців
                calculatePeriods(principal, payment, interest);
            }
        }
    }

    // допоміжні методи

    // Парсим позитивне число, інакше повертаємо null
    private Double parsePositive(String value) {
        if (value == null) return null;
        try {
            double v = Double.parseDouble(value);
            if (v > 0) return v;
        } catch (NumberFormatException ignored) { }
        return null;
    }

    // Диференційовані платежі (type = diff)
    private void calculateDifferential(double principal, int periods, double interest) {
        double i = interest / (12 * 100);  // місячна ставка
        int totalPaid = 0;

        for (int m = 1; m <= periods; m++) {
            double dm = principal / periods
                    + i * (principal - (principal * (m - 1) / periods));
            int payment = (int) Math.ceil(dm);
            totalPaid += payment;
            System.out.printf("Month %d: payment is %d%n", m, payment);
        }

        int overpayment = totalPaid - (int) Math.round(principal);
        System.out.printf("Overpayment = %d%n", overpayment);
    }

    // Розрахунок ануїтетного платежу (monthly payment)
    private void calculateAnnuityPayment(double principal, int periods, double interest) {
        double i = interest / (12 * 100);
        double annuity = principal * (i * Math.pow(1 + i, periods)
                / (Math.pow(1 + i, periods) - 1));

        int payment = (int) Math.ceil(annuity);
        System.out.printf("Your annuity payment = %d!%n", payment);
    }

    // Розрахунок основної суми кредиту (principal)
    private void calculatePrincipal(double payment, int periods, double interest) {
        double i = interest / (12 * 100);
        double principal = payment / (i * Math.pow(1 + i, periods)
                / (Math.pow(1 + i, periods) - 1));

        long principalRounded = Math.round(principal);
        System.out.printf("Your loan principal = %d!%n", principalRounded);
    }

    // Розрахунок кількості місяців (periods)
    private void calculatePeriods(double principal, double payment, double interest) {
        double i = interest / (12 * 100);
        double n = Math.log(payment / (payment - i * principal))
                / Math.log(1 + i);
        int months = (int) Math.ceil(n);

        int years = months / 12;
        int remainingMonths = months % 12;

        StringBuilder sb = new StringBuilder("It will take ");
        if (years > 0) {
            sb.append(years).append(years == 1 ? " year" : " years");
        }
        if (years > 0 && remainingMonths > 0) {
            sb.append(" and ");
        }
        if (remainingMonths > 0) {
            sb.append(remainingMonths)
                    .append(remainingMonths == 1 ? " month" : " months");
        }
        sb.append(" to repay this loan!");
        System.out.println(sb);
    }

    // Виведення повідомлення про помилку параметрів
    private void printIncorrect() {
        System.out.println("Incorrect parameters");
    }
}
