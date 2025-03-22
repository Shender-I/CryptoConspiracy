import javax.swing.*;
import java.awt.*;

public class CryptoConspiracyGUI {
    private final CryptoEngine engine;
    private final ConspiracyGenerator generator;

    public CryptoConspiracyGUI() {
        engine = new CryptoEngine();
        generator = new ConspiracyGenerator();

        // Создаем окно
        JFrame frame = new JFrame("Crypto Conspiracy");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());

        // Панель для ввода
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Выбор метода шифрования
        inputPanel.add(new JLabel("Метод шифрования:"));
        String[] methods = {"Шифр Цезаря", "Перестановочный шифр", "Шифр Виженера"};
        JComboBox<String> methodComboBox = new JComboBox<>(methods);
        inputPanel.add(methodComboBox);

        // Выбор действия
        inputPanel.add(new JLabel("Действие:"));
        String[] actions = {"Зашифровать", "Расшифровать"};
        JComboBox<String> actionComboBox = new JComboBox<>(actions);
        inputPanel.add(actionComboBox);

        // Поле для ввода текста
        inputPanel.add(new JLabel("Сообщение:"));
        JTextField inputField = new JTextField();
        inputPanel.add(inputField);

        // Поле для ключевого слова (для Виженера)
        inputPanel.add(new JLabel("Ключевое слово (для Виженера):"));
        JTextField keyField = new JTextField();
        keyField.setEnabled(false); // По умолчанию отключено
        methodComboBox.addActionListener(_ -> keyField.setEnabled(methodComboBox.getSelectedIndex() == 2));
        inputPanel.add(keyField);

        // Кнопка
        JButton processButton = new JButton("Обработать");
        inputPanel.add(new JLabel(""));
        inputPanel.add(processButton);

        // Область для вывода
        JTextArea outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Добавляем элементы в окно
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Обработчик кнопки
        processButton.addActionListener(_ -> {
            String input = inputField.getText();
            if (input.isEmpty()) {
                outputArea.setText("Ошибка: Введите сообщение!");
                return;
            }

            Message message = new Message(input);
            String result = "";
            int method = methodComboBox.getSelectedIndex();
            int action = actionComboBox.getSelectedIndex();

            try {
                if (method == 0) { // Шифр Цезаря
                    int shift = 3;
                    if (action == 0) {
                        result = engine.caesarEncrypt(message.getOriginalText(), shift);
                    } else {
                        result = engine.caesarDecrypt(message.getOriginalText(), shift);
                    }
                } else if (method == 1) { // Перестановочный шифр
                    if (action == 0) {
                        result = engine.swapEncrypt(message.getOriginalText());
                    } else {
                        result = engine.swapDecrypt(message.getOriginalText());
                    }
                } else if (method == 2) { // Шифр Виженера
                    String key = keyField.getText();
                    if (key.isEmpty()) {
                        outputArea.setText("Ошибка: Введите ключевое слово для шифра Виженера!");
                        return;
                    }
                    if (action == 0) {
                        result = engine.vigenereEncrypt(message.getOriginalText(), key);
                    } else {
                        result = engine.vigenereDecrypt(message.getOriginalText(), key);
                    }
                }

                message.setProcessedText(result);
                if (action == 0) {
                    outputArea.setText("Результат: " + message.getProcessedText() + "\n" + generator.addConspiracy(message.getProcessedText()));
                } else {
                    outputArea.setText("Результат: " + message.getProcessedText());
                }
            } catch (Exception ex) {
                outputArea.setText("Ошибка: " + ex.getMessage());
            }
        });

        // Показываем окно
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CryptoConspiracyGUI::new);
    }
}