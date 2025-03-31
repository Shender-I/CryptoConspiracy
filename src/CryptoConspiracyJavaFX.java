import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class CryptoConspiracyJavaFX extends Application {
    private final CryptoEngine engine = new CryptoEngine();
    private final ConspiracyGenerator generator = new ConspiracyGenerator();
    private TextArea outputArea; // Поле для области вывода

    @Override
    public void start(Stage primaryStage) {
        // Основной контейнер
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));
        root.getStyleClass().add("root");

        // Панель ввода
        GridPane inputPanel = new GridPane();
        inputPanel.setHgap(10);
        inputPanel.setVgap(10);
        inputPanel.setPadding(new Insets(10));
        inputPanel.getStyleClass().add("grid-pane");

        // Элементы управления
        Label methodLabel = new Label("Метод шифрования:");
        methodLabel.getStyleClass().add("label");

        ComboBox<String> methodComboBox = new ComboBox<>();
        methodComboBox.getItems().addAll("Шифр Цезаря", "Перестановочный шифр", "Шифр Виженера");
        methodComboBox.setValue("Шифр Цезаря");
        methodComboBox.getStyleClass().add("combo-box");

        Label actionLabel = new Label("Действие:");
        actionLabel.getStyleClass().add("label");

        ComboBox<String> actionComboBox = new ComboBox<>();
        actionComboBox.getItems().addAll("Зашифровать", "Расшифровать");
        actionComboBox.setValue("Зашифровать");
        actionComboBox.getStyleClass().add("combo-box");

        Label inputLabel = new Label("Сообщение:");
        inputLabel.getStyleClass().add("label");
        inputLabel.setId("inputLabel"); // Добавляем ID для стилизации

        TextField inputField = new TextField();
        inputField.getStyleClass().add("text-field");

        Label keyLabel = new Label("Ключевое слово (для Виженера):");
        keyLabel.getStyleClass().add("label");

        TextField keyField = new TextField();
        keyField.setDisable(true);
        keyField.getStyleClass().add("text-field");
        methodComboBox.setOnAction(_ -> keyField.setDisable(!methodComboBox.getValue().equals("Шифр Виженера")));

        Button processButton = new Button("Обработать");
        processButton.getStyleClass().add("button");
        processButton.setId("processButton"); // Добавляем ID для стилизации

        // Кнопка "Копировать"
        Button copyButton = new Button("Копировать");
        copyButton.getStyleClass().add("button");
        copyButton.setId("copyButton"); // Добавляем ID для стилизации
        copyButton.setOnAction(_ -> {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(outputArea.getText());
            clipboard.setContent(content);
        });

        // Добавляем элементы в GridPane
        inputPanel.add(methodLabel, 0, 0);
        inputPanel.add(methodComboBox, 1, 0);
        inputPanel.add(actionLabel, 0, 1);
        inputPanel.add(actionComboBox, 1, 1);
        inputPanel.add(inputLabel, 0, 2);
        inputPanel.add(inputField, 1, 2);
        inputPanel.add(keyLabel, 0, 3);
        inputPanel.add(keyField, 1, 3);
        inputPanel.add(copyButton, 0, 4);
        inputPanel.add(processButton, 1, 4);

        // Область вывода
        outputArea = new TextArea();
        outputArea.setEditable(false);
        outputArea.setWrapText(true);
        outputArea.getStyleClass().add("text-area");

        // Обработчик кнопки "Обработать"
        processButton.setOnAction(_ -> {
            String input = inputField.getText();
            if (input.isEmpty()) {
                outputArea.setText("Ошибка: Введите сообщение!");
                return;
            }

            Message message = new Message(input);
            String result = "";
            String method = methodComboBox.getValue();
            String action = actionComboBox.getValue();

            try {
                switch (method) {
                    case "Шифр Цезаря" -> {
                        int shift = 3;
                        if (action.equals("Зашифровать")) {
                            result = engine.caesarEncrypt(message.getOriginalText(), shift);
                        } else {
                            result = engine.caesarDecrypt(message.getOriginalText(), shift);
                        }
                    }
                    case "Перестановочный шифр" -> {
                        if (action.equals("Зашифровать")) {
                            result = engine.swapEncrypt(message.getOriginalText());
                        } else {
                            result = engine.swapDecrypt(message.getOriginalText());
                        }
                    }
                    case "Шифр Виженера" -> {
                        String key = keyField.getText();
                        if (key.isEmpty()) {
                            outputArea.setText("Ошибка: Введите ключевое слово для шифра Виженера!");
                            return;
                        }
                        if (action.equals("Зашифровать")) {
                            result = engine.vigenereEncrypt(message.getOriginalText(), key);
                        } else {
                            result = engine.vigenereDecrypt(message.getOriginalText(), key);
                        }
                    }
                }

                message.setProcessedText(result);
                if (action.equals("Зашифровать")) {
                    outputArea.setText("Результат: " + message.getProcessedText() + "\n" + generator.addConspiracy(message.getProcessedText()));
                } else {
                    outputArea.setText("Результат: " + message.getProcessedText());
                }
            } catch (Exception ex) {
                outputArea.setText("Ошибка: " + ex.getMessage());
            }
        });

        // Добавляем элементы в BorderPane
        root.setTop(inputPanel);
        root.setCenter(outputArea);
        BorderPane.setMargin(outputArea, new Insets(10, 0, 0, 0));

        // Настройка сцены
        Scene scene = new Scene(root, 500, 400);
        try {
            scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        } catch (Exception e) {
            System.out.println("Не удалось загрузить CSS: " + e.getMessage());
        }
        primaryStage.setTitle("Crypto Conspiracy");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}