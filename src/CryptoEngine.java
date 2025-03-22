public class CryptoEngine {
    // Шифр Цезаря
    public String caesarEncrypt(String text, int shift) {
        StringBuilder encrypted = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                if (c >= 'A' && c <= 'Z') {
                    char base = 'A';
                    encrypted.append((char) (base + (c - base + shift) % 26));
                } else if (c >= 'a' && c <= 'z') {
                    char base = 'a';
                    encrypted.append((char) (base + (c - base + shift) % 26));
                } else if (c >= 'А' && c <= 'Я') {
                    char base = 'А';
                    encrypted.append((char) (base + (c - base + shift) % 32));
                } else if (c >= 'а' && c <= 'я') {
                    char base = 'а';
                    encrypted.append((char) (base + (c - base + shift) % 32));
                } else {
                    encrypted.append(c);
                }
            } else {
                encrypted.append(c);
            }
        }
        return encrypted.toString();
    }

    public String caesarDecrypt(String text, int shift) {
        StringBuilder decrypted = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                if (c >= 'A' && c <= 'Z') {
                    char base = 'A';
                    decrypted.append((char) (base + (c - base - shift + 26) % 26));
                } else if (c >= 'a' && c <= 'z') {
                    char base = 'a';
                    decrypted.append((char) (base + (c - base - shift + 26) % 26));
                } else if (c >= 'А' && c <= 'Я') {
                    char base = 'А';
                    decrypted.append((char) (base + (c - base - shift + 32) % 32));
                } else if (c >= 'а' && c <= 'я') {
                    char base = 'а';
                    decrypted.append((char) (base + (c - base - shift + 32) % 32));
                } else {
                    decrypted.append(c);
                }
            } else {
                decrypted.append(c);
            }
        }
        return decrypted.toString();
    }

    // Перестановочный шифр
    public String swapEncrypt(String text) {
        char[] chars = text.toCharArray();
        for (int i = 0; i < chars.length - 1; i += 2) {
            char temp = chars[i];
            chars[i] = chars[i + 1];
            chars[i + 1] = temp;
        }
        return new String(chars);
    }

    public String swapDecrypt(String text) {
        return swapEncrypt(text);
    }

    // Шифр Виженера
    public String vigenereEncrypt(String text, String key) {
        StringBuilder encrypted = new StringBuilder();
        key = key.toLowerCase();
        int keyIndex = 0;

        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char keyChar = key.charAt(keyIndex % key.length());
                int shift = (keyChar >= 'а' && keyChar <= 'я') ? (keyChar - 'а') : (keyChar - 'a');

                if (c >= 'A' && c <= 'Z') {
                    char base = 'A';
                    encrypted.append((char) (base + (c - base + shift) % 26));
                } else if (c >= 'a' && c <= 'z') {
                    char base = 'a';
                    encrypted.append((char) (base + (c - base + shift) % 26));
                } else if (c >= 'А' && c <= 'Я') {
                    char base = 'А';
                    encrypted.append((char) (base + (c - base + shift) % 32));
                } else if (c >= 'а' && c <= 'я') {
                    char base = 'а';
                    encrypted.append((char) (base + (c - base + shift) % 32));
                } else {
                    encrypted.append(c);
                }
                keyIndex++;
            } else {
                encrypted.append(c);
            }
        }
        return encrypted.toString();
    }

    public String vigenereDecrypt(String text, String key) {
        StringBuilder decrypted = new StringBuilder();
        key = key.toLowerCase();
        int keyIndex = 0;

        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char keyChar = key.charAt(keyIndex % key.length());
                int shift = (keyChar >= 'а' && keyChar <= 'я') ? (keyChar - 'а') : (keyChar - 'a');

                if (c >= 'A' && c <= 'Z') {
                    char base = 'A';
                    decrypted.append((char) (base + (c - base - shift + 26) % 26));
                } else if (c >= 'a' && c <= 'z') {
                    char base = 'a';
                    decrypted.append((char) (base + (c - base - shift + 26) % 26));
                } else if (c >= 'А' && c <= 'Я') {
                    char base = 'А';
                    decrypted.append((char) (base + (c - base - shift + 32) % 32));
                } else if (c >= 'а' && c <= 'я') {
                    char base = 'а';
                    decrypted.append((char) (base + (c - base - shift + 32) % 32));
                } else {
                    decrypted.append(c);
                }
                keyIndex++;
            } else {
                decrypted.append(c);
            }
        }
        return decrypted.toString();
    }
}