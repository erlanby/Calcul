import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Convert converter = new Convert();
        String[] actions = {"+", "-", "/", "*"};
        String[] regexActions = {"\\+", "-", "/", "\\*"};
        Scanner scn = new Scanner(System.in);
        System.out.print("Введите выражение: ");
        String exp = scn.nextLine();
        int actionIndex = -1;
        for (int i = 0; i < actions.length; i++) {
            if (exp.contains(actions[i])) {
                actionIndex = i;
                break;
            }
        }

        if (actionIndex == -1) {
            System.out.println("Некорректное выражение");
            return;
        }

        String[] data = exp.split(regexActions[actionIndex]);
        if (converter.isRoman(data[0]) == converter.isRoman(data[1])) {
            try {
                int a, b;
                boolean isRoman = converter.isRoman(data[0]);
                if (isRoman) {
                    a = converter.romanToInt(data[0]);
                    b = converter.romanToInt(data[1]);

                } else {

                    a = Integer.parseInt(data[0]);   // переделать надо от 1 до 10
                    b = Integer.parseInt(data[1]);
                }
                checkNumber(a);
                checkNumber(b);
                int result;
                switch (actions[actionIndex]) {
                    case "+":
                        result = a + b;
                        break;
                    case "-":
                        result = a - b;
                        break;
                    case "*":
                        result = a * b;
                        break;
                    default:
                        result = a / b;
                        break;
                }

                if (isRoman) {
                    System.out.println(converter.intToRoman(result));
                } else {
                    System.out.println(result);
                }
            } catch (MyException | StringIndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            }

        } else {
            System.out.println("Числа должны быть в одном формате");
        }

    }


    public static void checkNumber(int number) throws MyException {
        if (number > 10 || number <= 0) {
            throw new MyException ("Неправильный формат: на ноль делить нельзя");
        }
    }
}