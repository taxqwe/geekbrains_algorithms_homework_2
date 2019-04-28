package nikolay;

import java.util.*;
import java.util.function.IntFunction;

public class Main {

    public static void main(String[] args) {
        compareSortingTime(20, 10000);
    }

    private static Comparator getIntegerComparator() {
        return (Comparator<Integer>) (o1, o2) -> {
            if (o1 < o2) {
                return -1;
            } else if (o1 > o2) {
                return 1;
            } else {
                return 0;
            }
        };
    }

    private static void compareSortingTime(int times, int arraySize) {
        for (int i = 0; i < times; i++) {
            Integer[] preInitializedArray = getNewRandomIntegerArray(arraySize);
            CustomArrayList<Integer> list1 = new CustomArrayList<>(preInitializedArray);
            CustomArrayList<Integer> list2 = new CustomArrayList<>(preInitializedArray);

            long checkpointStart = System.currentTimeMillis(); // beginning
            long checkpointMiddle;                             // first is sorted
            long checkpointEnd;                                // second is sorted

            list1.instertionSorting(getIntegerComparator());

            checkpointMiddle = System.currentTimeMillis();

            list2.selectionSorting(getIntegerComparator());

            checkpointEnd = System.currentTimeMillis();

            SortingTimeExperimentResult.addResult(checkpointMiddle - checkpointStart, checkpointEnd - checkpointMiddle);
        }

        int insertionSum = 0;
        int selectionSum = 0;

        System.out.println("EXPERIMENTS RESULT:");
        System.out.println("#                   inserting                   selection:");
        for (SortingTimeExperimentResult result: SortingTimeExperimentResult.getAllExperiments()) {
            System.out.println(result.getExperimentNumber() + "                   "
                    + result.getFirstListSortingTime() + "                              "
                    + result.getSecondListSortingTime());
            insertionSum += result.getFirstListSortingTime();
            selectionSum += result.getSecondListSortingTime();
        }

        System.out.println("\n\naverage time: inserting " + insertionSum/times + " | selecting " + selectionSum/times);

    }

    private static Integer[] getNewRandomIntegerArray(int size) {
        Integer[] preInitializedArray = new Integer[size];
        Arrays.setAll(preInitializedArray, new IntFunction<>() {
            Random ran = new Random();

            @Override
            public Integer apply(int value) {
                int randomInt = 0 + ran.nextInt(1000000 - 0 + 1);
                return randomInt;
            }
        });
        return preInitializedArray;
    };

    private static class SortingTimeExperimentResult {
        private static List<SortingTimeExperimentResult> allExperiments = new ArrayList<>();

        private int experimentNumber;

        private static int experimentsTotal = 0;

        private long firstListSortingTime;

        private long secondListSortingTime;


        private SortingTimeExperimentResult(long firstListTime, long secondListTime) {
            firstListSortingTime = firstListTime;
            secondListSortingTime = secondListTime;
            experimentNumber = ++experimentsTotal;
        }

        private static void addResult(long firstListTime, long secondListTime) {
            allExperiments.add(new SortingTimeExperimentResult(firstListTime, secondListTime));
        }

        public static List<SortingTimeExperimentResult> getAllExperiments() {
            return allExperiments;
        }

        public int getExperimentNumber() {
            return experimentNumber;
        }

        public long getFirstListSortingTime() {
            return firstListSortingTime;
        }

        public long getSecondListSortingTime() {
            return secondListSortingTime;
        }
    }

}
