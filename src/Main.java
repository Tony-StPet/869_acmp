//Байдарочный поход (Время: 0,25 сек. Память: 16 Мб Сложность: 35%)
//
// Компания из N человек собирается пойти в байдарочный поход, i-ый человек характеризуется своей массой Mi кг.
// На лодочной базе имеется в наличии неограниченное количество одинаковых байдарок. Каждая байдарка может вмещать одного или двух людей.
// Байдарки имеют грузоподъемность D кг. Какое наименьшее количество байдарок придется арендовать компании, чтобы всем отправиться в поход?
//
//Входные данные В первой строке входного файла INPUT.TXT содержится пара натуральных чисел N, D (1 ≤ N ≤ 15000; 1 ≤ D ≤ 15000).
// Во второй строке содержится последовательность натуральных чисел M1, M2, ... , MN (1 ≤ Mi ≤ D).
//
//Выходные данные
//В выходной файл OUTPUT.TXT выведите искомое наименьшее количество необходимых байдарок.


import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите  N человек в компании:  ");
        int company = scanner.nextInt();
        List<Integer> list = inpupList(company);
        printList(list);
        bubbleSort(list);
        printList(list);
        printList(processList(list));



    }

    public static List<Integer> inpupList(int size){

        List<Integer> companyList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < size; i++) {
            System.out.printf("[%d] = ", i+1);
            companyList.add(scanner.nextInt());
        }
        return companyList;
    }

    public static void printList(List<Integer> list) {
        System.out.println("Список байдарочников (вес):");
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("[%d] = %d%n", i+1, list.get(i));
        }
    }

    // Алгоритм сортировки пузырьком списка элементов
    public static void bubbleSort(List<Integer> list) {
        for (int i = 0; i < list.size()-1; i++) {
            int end = list.size() - i;
            for (int j = 0; j < end-1; j++) {
                if (list.get(j) > (list.get(j+1))) {

                    int tmp = list.get(j+1);
                    list.set(j+1, list.get(j));
                    list.set(j,tmp);
                }
            }
        }
        System.out.println("Сортировка прошла успешно от мин к макс");
    }

    public static List<Integer> processList(List<Integer> inputList) {
        List<Integer> resultList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите грузоподъемность лодки: ");
        int tareWeight = scanner.nextInt();
        int addedToResultCount = 0; // Счетчик добавлений в resultList

        // Перебираем элементы входного списка
        for (int i = inputList.size() - 1; i >= 0; i--) {
            int currentElement = inputList.get(i);
            boolean foundPair = false;
            for (int j = i - 1; j >= 0; j--) {
                if (inputList.size() < 2) {
                    // Если в списке осталось 2 элемента, проверяем их сумму
                    if (inputList.size() == 2 && inputList.get(0) + inputList.get(1) <= tareWeight) {
                        resultList.add(inputList.get(0));
                        resultList.add(inputList.get(1));
                        addedToResultCount ++;
                        inputList.clear(); // Очищаем inputList, чтобы выйти из цикла
                        break;
                    } else if (inputList.size() == 2) {
                        resultList.add(0);
                        resultList.add(inputList.get(0));
                        resultList.add(0);
                        resultList.add(inputList.get(1));
                        addedToResultCount +=2;
                        inputList.clear(); // Очищаем inputList, чтобы выйти из цикла
                        break;
                    } else {
                        break;
                    }
                }
                if (currentElement + inputList.get(j) <= tareWeight) {
                    resultList.add(currentElement);
                    resultList.add(inputList.get(j));
                    inputList.remove(i);
                    inputList.remove(j);
                    addedToResultCount ++;
                    foundPair = true;
                    j--;
                    i--;
                    break;
                }
            }

            if (!foundPair) {
                if (inputList.size() > 0) {
                    resultList.add(0);
                    resultList.add(currentElement);
                    addedToResultCount ++;
                    inputList.remove(i);
                }
            }
        }
        System.out.println("Количество лодок: " + addedToResultCount);
        return resultList;
    }






}

