package my.code;

import my.code.utils.FilePasswordRepository;
import my.code.utils.ZipPasswordCracker;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        String zipFile = "secured.zip";
        String passwordFile = "passwords.txt";
        String outputFolder = "output_folder";

        FilePasswordRepository dictionary = new FilePasswordRepository(passwordFile);
        List<String> passwords;
        try {
            passwords = dictionary.getPasswords();
        } catch (IOException e) {
            System.err.println("Ошибка чтения словаря: " + e.getMessage());
            return;
        }

        if (passwords.isEmpty()) {
            System.out.println("Словарь паролей пуст.");
            return;
        }

        ZipPasswordCracker cracker = new ZipPasswordCracker(zipFile, outputFolder);
        String foundPassword = cracker.crackPassword(passwords);

        if (foundPassword != null) {
            System.out.println("Пароль найден: " + foundPassword);
            try {
                dictionary.savePassword(foundPassword);
            } catch (IOException e) {
                System.err.println("Ошибка записи правильного пароля: " + e.getMessage());
            }
        } else {
            System.out.println("Пароль не найден.");
        }
    }
}