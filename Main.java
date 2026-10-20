package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.print("Выберите задание для проверки:\n1. Задание с List задачами.\n2. Задание анаграммами.\n3. Задание с часто-встреч. словами\n4. Задание со списками чисел.\nВаш выбор: ");
        while (true) {
            Scanner in = new Scanner(System.in);
            int user_num = in.nextInt();

            if (user_num == 1) {
                exercise1();
            } else if (user_num == 2) {
                exercise2();
            } else if (user_num == 3) {
                exercise3();
            } else if (user_num == 4) {
                exercise4();
            } else {
                System.out.print("[!] Такого задания нет, введите ещё раз: ");
                continue;
            }
            break;
        }
    }

    public static void exercise1() {
        LinkedList<String> exercises = new LinkedList<>();

        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.print("   Выберите опцию:\n1. Добавить задачу в конец\n2. Удалить первую задачу\n3. Удалить задачу по индексу\n4. Показать все задачи.\n5. Выход из программы.\n   Ваш выбор: ");

            int first_num = in.nextInt();
            in.nextLine(); // "Непонятное очищение буфера сканнера" (Дабы опция 1 работала и не выдавала ошибку)

            if (first_num == 1) {
                System.out.print("[!] Введите новый элемент: ");
                String text = in.nextLine();
                exercises.add(text);
                System.out.println("[!] Задача " + exercises.size() + " добавлена.");

            } else if (first_num == 2) {
                if (exercises.isEmpty()) {
                    System.out.println("[!] Список пуст.");

                } else {
                    exercises.removeFirst();
                    System.out.println("[!] Первая задача удалена.");
                }

            } else if (first_num == 3) {
                System.out.print("[!] Введите номер для удаления: ");
                int second_num = in.nextInt();
                if (second_num > 0 & second_num <= exercises.size()) {
                    exercises.remove(second_num - 1);
                    System.out.println("[!] Задача " + second_num + " удалена.");
                } else {
                    System.out.println("[!] Неверный номер.");
                }

            } else if (first_num == 4) {
                System.out.println("[!] Список задач: " + exercises);

            } else if (first_num == 5) {
                System.out.println("[!] Завершение работы.");
                break;

            } else {
                System.out.println("[!] Неверная команда.");
            }
        }
    }

    public static void exercise2(){
        // Базовый ввод строки для работы
        Scanner in = new Scanner(System.in);
        System.out.print("Введите слова через ( ): ");
        String user_input = in.nextLine();

        // Дробление строки на массив слов
        String[] list_of_words = user_input.split(" ");

        // Создание словаря для сравнения анаграмм
        Map<String, List<String>> anagrams = new HashMap<>();

        // Цикл заполнения словаря анаграммами
        for (String word : list_of_words) {
            String[] letters = word.split("");
            Arrays.sort(letters);
            String key = String.join("", letters);

            if (anagrams.containsKey(key)) {
                // Получаем существующий список и добавляем в него элемент
                anagrams.get(key).add(word);
            } else {
                // Создаем новый список, добавляем слово и кладем в Map
                List<String> list = new ArrayList<>();
                list.add(word);
                anagrams.put(key, list);
            }
        }
        // Вывод получившегося списка списков
        System.out.println(anagrams.values());
    }

    public static void exercise3() {
        String text = "java is great java is powerful " + "python is great too but java is java";
        String[] words = text.split(" ");
        // Счётчик количества повторов ниже
        HashMap<String, Integer> recurring = new HashMap<>();

        // Структура работы счётчика
        for (String word : words) {
            if (recurring.containsKey(word)) {
                recurring.put(word, recurring.get(word) + 1);
            } else {
                recurring.put(word, 1);
            }
        }

        // Конвертируем итог счётчика в List для сортировки
        List<Map.Entry<String, Integer>> list_of_counts = new ArrayList<>(recurring.entrySet());

        // Сортируем по значению (по убыванию при помощи reversed)
        list_of_counts.reversed().sort(Map.Entry.comparingByValue());

        // Делаем предварительный запрос на количество строк вывода
        Scanner in = new Scanner(System.in);
        System.out.print("Кол-во запрашиваемого из топа (не более " + list_of_counts.size() + "): ");
        int N = in.nextInt();

        // Создаём LinkedHashMap для сохранения порядка сортировки + ограничиваем вывод
        LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();
        if (N <= list_of_counts.size()) {
            for (int i = 0; i < N; i++) {
                Map.Entry<String, Integer> entry = list_of_counts.get(i);
                sortedMap.put(entry.getKey(), entry.getValue());
            }

            // Выводим отсортированные данные
            for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue() + " раз");
            }
        } else {
            // Вариант для гениев...
            System.out.println("[!] Запрашиваемое число больше имеющего в списке топа.");
        }


    }

    public static void exercise4() {
        // Базовый массив
        ArrayList<Integer> numbers1 = new ArrayList<>(List.of(4, 2, 7, 2, 9, 4, 1, 7, 3, 9));
        // Массив без повторений
        LinkedList<Integer> numbers2 = new LinkedList<>();
        // Словарь — Счётчик повторений значений
        LinkedHashMap<Integer, Integer> recurring = new LinkedHashMap<>();
        // Словарь — Регистратор метрик массива без "avg", ибо оно float
        LinkedHashMap<String, Integer> metrics = new LinkedHashMap<>();
        // Стартовые значения метрик
        metrics.put("Min", numbers1.getFirst());
        metrics.put("Max", numbers1.getFirst());
        metrics.put("Sum", 0);

        System.out.println("Базовый массив: " + numbers1);

        // Цикл перебора базового массива
        for (int i = 0; i < numbers1.size(); i++) {
            // Параметр неимения повторений в массивах
            boolean non_recurring = true;
            // Цикл проверки на дубликаты в обновлённом массиве
            for (Integer numb2 : numbers2) {
                if (numbers1.get(i).equals(numb2)) {
                    // Если нашёл "equals", переключает параметр
                    non_recurring = false;

                    // Структура для записи количества повторений
                    if (recurring.containsKey(i)) {
                        recurring.put(numbers1.get(i), recurring.get(i) + 1);
                    } else {
                        recurring.put(numbers1.get(i), 2);
                    }

                    break;
                }
            }

            // Начисление к итоговой сумме
            metrics.put("Sum", metrics.get("Sum") + numbers1.get(i));

            // Регистрация минимального числа
            if (numbers1.get(i) < metrics.get("Min")) {
                metrics.put("Min", numbers1.get(i));
            }

            // Регистрация максимального числа
            if (numbers1.get(i) > metrics.get("Max")) {
                metrics.put("Max", numbers1.get(i));
            }

            // Если повторений нет, добавляет проверенный элемент
            if (non_recurring) {
                numbers2.add(numbers1.get(i));
            }
        }

        System.out.println("Обновлённый массив: " + numbers2);

        // Вывод количества повторений
        for (Map.Entry<Integer, Integer> entry : recurring.entrySet()) {
            System.out.println(entry.getKey() + " – " + entry.getValue() + " раза");
        }

        // Вывод метрических данных
        for (Map.Entry<String, Integer> entry : metrics.entrySet()) {
            System.out.print(entry.getKey() + " – " + entry.getValue() + ", ");
        }

        // Вычисление конечного среднеарифметического, его вывод
        System.out.println("Avg – " + (float) metrics.get("Sum") / numbers1.size());
    }
}