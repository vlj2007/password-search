package my.code.utils;


import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;

import java.io.IOException;
import java.util.List;


/**
 * Класс для перебора паролей по архиву и распаковки.
 * Определяет метод для перебора паролей по архиву и распаковки.
 *
 * @author Vlj2007
 * @version 0.1
 */

public class ZipPasswordCracker {
    private final String zipFilePath;
    private final String outputFolder;

    public ZipPasswordCracker(String zipFilePath, String outputFolder) {
        this.zipFilePath = zipFilePath;
        this.outputFolder = outputFolder;
    }

    /**
     * Перебирает пароли из списка и пытается распаковать архив.
     * @param passwords список паролей
     * @return найденный пароль либо null если не найден
     */
    public String crackPassword(List<String> passwords) {
        for (String password : passwords) {
            try (ZipFile zipFile = new ZipFile(zipFilePath, password.toCharArray())) {
                zipFile.extractAll(outputFolder);
                return password;
            } catch (ZipException e) {
                System.out.println("Неверный пароль: " + password);
            } catch (IOException e) {
                System.err.println("Ошибка доступа к архиву: " + e.getMessage());
            }
        }
        return null;
    }
}
