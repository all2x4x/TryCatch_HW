public class TryCatch {

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.println("Введите данные (Фамилия Имя Отчество дата_рождения номер_телефона пол):");

        String input = scanner.nextLine().trim();
        String[] data = input.split("\\s+");

        if (data.length != 6) {
            System.out.println("Ошибка: Ожидалось 6 данных");
            return;
        }

        if (!isValidPhoneNumber(data[4])) {
            System.out.println("Ошибка: Номер телефона должен быть целым беззнаковым числом.");
            return;
        }

        char gender = data[5].charAt(0);
        if (gender != 'f' && gender != 'm') {
            System.out.println("Ошибка: Пол должен быть 'f' или 'm'.");
            return;
        }

        String userDataString = String.join(" ", data);
        try {
            writeToFile(data[0], userDataString);
            System.out.println("Данные успешно записаны!");
        } catch (java.io.IOException e) {
            System.out.println("Ошибка при работе с файлом: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static boolean isValidPhoneNumber(String phone) {
        try {
            Long.parseLong(phone);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Вы неверно ввели номер");
            return false;
        }
    }

    private static void writeToFile(String surname, String userDataString) throws java.io.IOException {
        String fileName = surname + ".txt";
        try (java.io.BufferedWriter writer = new java.io.BufferedWriter(new java.io.FileWriter(fileName, true))) {
            writer.write(userDataString);
            writer.newLine();
        }
    }
}
