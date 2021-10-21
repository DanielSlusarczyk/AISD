package pl.edu.pw.ee;

import pl.edu.pw.ee.services.Sorting;

public class InsertionSort implements Sorting {

    @Override
    public void sort(double[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Nums array cannot be null");
        }

        if (nums.length != 1) {
            int i = 1;
            int j = i - 1;

            while (i < nums.length) {
                if (nums[j] > nums[i]) {
                    double tmp = nums[i];
                    while (j >= 0 && nums[j] >= tmp) {
                        nums[j + 1] = nums[j];
                        j--;
                    }
                    nums[j + 1] = tmp;
                }
                i++;
                j = i - 1;
            }
        }
    }
}
