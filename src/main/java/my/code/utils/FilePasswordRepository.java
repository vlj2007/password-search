package my.code.utils;

import my.code.api.PasswordRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс для получения пароля и сохранения в словарь (файл).
 * Определяет метод для чтения и записи паролей в словарь (файл).
 *
 * @author Vlj2007
 * @version 0.1
 */

public class FilePasswordRepository implements PasswordRepository {

    /**
     * получения пароля и сохранения в словарь с заданными параметрами.
     *
     * @param filename название файла
     * @throws IOException если ошибка ввода и вывода.
     */

    private final String fileName;

    public FilePasswordRepository(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<String> getPasswords() throws IOException {
        List<String> passwords = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String trimmed = line.trim();
                if (!trimmed.isEmpty() && !trimmed.startsWith("#")) {
                    passwords.add(trimmed);
                }
            }
        }
        return passwords;
    }

    @Override
    public void savePassword(String password) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            bw.write(password);
            bw.newLine();
        }
    }
}
