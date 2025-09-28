package my.code.api;

import java.io.IOException;
import java.util.List;

/**
 * Интерфейс для хранения и получения паролей.
 */

public interface PasswordRepository {
    /**
     * Получить список паролей.
     * @return список паролей.
     * @throws IOException при ошибке чтения данныз
     */
    List<String> getPasswords() throws IOException;

    /**
     * Сохранить правельный пароль
     * @param password пароль для сохранения
     * @throws IOException при ошибке записи
     */
    void savePassword(String password) throws IOException;
}
