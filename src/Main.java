import java.util.*;

class Main {
    public static void main(String[] args) {
        int[] arrA = {1, 3, 5, 7, 9, 2, 2};
        int[] arrB = {2, 4, 5, 6, 8, 1, 2};

        int[] commonElements = findCommonElements(arrA, arrB);
        System.out.println(Arrays.toString(commonElements));

        quickSort(commonElements, 0, commonElements.length - 1);
        System.out.println(Arrays.toString(commonElements));
    }

    /**
     * Функція знаходження елементів, які присутні в обох масивах
     * та повертає масив з такими елементами в одному екземплярі.
     * Бінарний пошук.
     */
    public static int[] findCommonElements(int[] arrA, int[] arrB) {
        List<Integer> result = new ArrayList<>();
        Arrays.sort(arrA);

        for (int element : arrB) {
            if (binarySearch(arrA, element) != -1 && !result.contains(element)) {
                result.add(element);
            }
        }

        return result.stream().mapToInt(i -> i).toArray();
    }

    // Бінарний пошук елемента

    public static int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    // Сортування Хоара.

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int partitionIndex = partition(arr, low, high);
            quickSort(arr, low, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, high);
        }
    }

    /**
     * Функція, яка розбиває масив на дві частини таким чином,
     * щоб у першій частині були елементи, менші за опорний елемент,
     * а в другій частині - елементи, більші за опорний елемент.
     */
    public static int partition(int[] arr, int low, int high) {
        int middleElement = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] <= middleElement) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }
}
