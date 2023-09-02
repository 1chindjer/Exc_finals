import java.io.*;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Finals {

    static class InvalidInputException extends Exception {
        public InvalidInputException(String message) {
            super(message);
        }
    }

    static class InvalidDateFormatException extends InvalidInputException {
        public InvalidDateFormatException() {
            super("Неверный формат даты. Ожидается dd.mm.yyyy.");
        }
    }

    static class InvalidGenderException extends InvalidInputException {
        public InvalidGenderException() {
            super("Неверный пол. Ожидается 'f' или 'm'.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите данные (Фамилия Имя Отчество датарождения номертелефона пол):");

        String input = scanner.nextLine();
        String[] parts = input.split(" ");

        if (parts.length != 6) {
            System.out.println("Вы ввели " + (parts.length < 6 ? "меньше" : "больше") + " данных, чем требуется.");
            return;
        }

        try {
            String surname = parts[0];
            String name = parts[1];
            String patronymic = parts[2];
            String birthDate = parts[3];
            if (!Pattern.matches("\\d{2}\\.\\d{2}\\.\\d{4}", birthDate)) {
                throw new InvalidDateFormatException();
            }
            long phoneNumber = Long.parseLong(parts[4]);
            char gender = parts[5].charAt(0);
            if (gender != 'f' && gender != 'm') {
                throw new InvalidGenderException();
            }

            File file = new File("Exceptions/Finals/"+surname + ".txt");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                writer.write(surname + name + patronymic + birthDate + " " + phoneNumber + gender);
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат номера телефона.");
        }
    }
}
