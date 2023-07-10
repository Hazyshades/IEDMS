package Logs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class LogReader {
    public static void validateLog () {
        // UNC путь к лог-файлу на удаленном компьютере
        String remotePath = "\\\\banksy-sc\\c$\\Program Files (x86)\\Digital Design\\IEDMS Interface Service\\logs\\IEDMS.InterfaceService.log";

        // Текст, с которого начинается копирование
        String startText = "Запуск обработки входящего сообщения";
        // Текст, при котором копирование завершается
        String endText = "Завершение обработки входящего сообщения";

        // Открывает и читаем лог
        try {
            Path path = FileSystems.getDefault().getPath(remotePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(path)));

            String line;
            boolean copy = false;
            StringBuilder output = new StringBuilder();
            //ищем совпадение по тексту из StartText
            while ((line = reader.readLine()) != null) {
                //копируем текст от StartText до EndText
                if (line.contains(startText)) {
                    copy = true;
                }
                if (copy) {
                    output.append(line).append(System.lineSeparator());
                }
                if (line.contains(endText)) {
                    break;
                }
            }

            reader.close();

            // Вывод результат
            System.out.println(output.toString());

            // Удаляем лог файл
           // Files.deleteIfExists(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}