package Services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ServicesStarting {
    public static void main(String[] args) {
         startService("IEDMSInterfaceService");
        // serviceStarter.stopService();
    }

    public static void startService(String nameService) {
        try {
            // Команда для запуска службы
            String[] command = {"sc", "\\\\banksy-sc", "start", nameService};

            // Создание процесса и выполнение команды
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            Process process = processBuilder.start();

            // Чтение вывода команды
            printProcessOutput(process);

            // Ожидание завершения процесса и получение кода возврата
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println("Служба успешно запущена.");
            } else {
                System.out.println("Не удалось запустить службу.");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void stopService(String nameService) {
        try {
            // Команда для остановки службы
            String[] command = {"sc", "\\\\banksy-sc", "stop", nameService};

            // Создание процесса и выполнение команды
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            Process process = processBuilder.start();

            // Чтение вывода команды
            printProcessOutput(process);

            // Ожидание завершения процесса и получение кода возврата
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println("Служба успешно остановлена.");
            } else {
                System.out.println("Не удалось остановить службу.");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void printProcessOutput(Process process) {
        try {
            // Получение вывода процесса
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}