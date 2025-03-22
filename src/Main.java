import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CryptoEngine engine = new CryptoEngine();
        ConspiracyGenerator generator = new ConspiracyGenerator();

        // Выбор метода шифрования
        System.out.println("Выберите метод шифрования:");
        System.out.println("1. Шифр Цезаря");
        System.out.println("2. Перестановочный шифр");
        System.out.println("3. Шифр Виженера");
        int methodChoice = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера

        // Выбор действия
        System.out.println("Выберите действие:");
        System.out.println("1. Зашифровать");
        System.out.println("2. Расшифровать");
        int actionChoice = scanner.nextInt();
        scanner.nextLine();

        // Ввод текста
        System.out.println("Введите сообщение:");
        String input = scanner.nextLine();
        Message message = new Message(input);

        String result = "";
        if (methodChoice == 1) { // Шифр Цезаря
            int shift = 3;
            if (actionChoice == 1) {
                result = engine.caesarEncrypt(message.getOriginalText(), shift);
            } else if (actionChoice == 2) {
                result = engine.caesarDecrypt(message.getOriginalText(), shift);
            }
        } else if (methodChoice == 2) { // Перестановочный шифр
            if (actionChoice == 1) {
                result = engine.swapEncrypt(message.getOriginalText());
            } else if (actionChoice == 2) {
                result = engine.swapDecrypt(message.getOriginalText());
            }
        } else if (methodChoice == 3) { // Шифр Виженера
            System.out.println("Введите ключевое слово:");
            String key = scanner.nextLine();
            if (actionChoice == 1) {
                result = engine.vigenereEncrypt(message.getOriginalText(), key);
            } else if (actionChoice == 2) {
                result = engine.vigenereDecrypt(message.getOriginalText(), key);
            }
        }

        message.setProcessedText(result);
        System.out.println("Результат: " + message.getProcessedText());
        if (actionChoice == 1) { // Добавляем шутку только при шифровании
            System.out.println(generator.addConspiracy(message.getProcessedText()));
        }

        scanner.close();
    }
}