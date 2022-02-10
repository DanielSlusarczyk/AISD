package pl.edu.pw.ee;

public class ChristmasBonus {

    public int findMaxLength(int[] schedule, int changes) {
        validateInput(schedule, changes);
        if (schedule.length == 0 || schedule.length == 1) {
            return schedule.length;
        }

        int[] frequnecy = new int[findMaxElement(schedule) + 1];
        int startIndex = 0;
        int maxFrequency = 0;
        int result = 0;

        for (int endIndex = 0; endIndex < schedule.length; endIndex++) {
            frequnecy[schedule[endIndex]]++;
            maxFrequency = Math.max(frequnecy[schedule[endIndex]], maxFrequency);

            int lengthOfSubarray = endIndex - startIndex + 1;
            while (lengthOfSubarray - maxFrequency > changes) {
                frequnecy[schedule[startIndex]]--;
                startIndex++;
                lengthOfSubarray--;
            }
            result = Math.max(lengthOfSubarray, result);
        }
        return result;
    }

    private int findMaxElement(int[] array) {
        int maxValue = array[0];
        for (int current : array) {
            maxValue = Math.max(maxValue, current);
        }
        return maxValue;
    }

    private void validateInput(int[] schedule, int changes) {
        if (schedule == null) {
            throw new IllegalArgumentException("The schedule is null");
        }
        if (schedule.length > 99999) {
            throw new IllegalArgumentException("The length of the array is greater than 99 999");
        }
        if (changes < 0 || changes > schedule.length) {
            throw new IllegalArgumentException("The number of changes is incorrect");
        }
        for (int block : schedule) {
            if (block < 1 || block > 99999) {
                throw new IllegalArgumentException("The number of block is incorrect");
            }
        }
    }
}
