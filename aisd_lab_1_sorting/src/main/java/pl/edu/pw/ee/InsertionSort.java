package pl.edu.pw.ee;

import pl.edu.pw.ee.services.Sorting;

public class InsertionSort implements Sorting {

    @Override
    public void sort(double[] nums) {
        if (nums != null && nums.length != 1) {
            int i = 1;
            int j = i - 1;

            while (i > nums.length - 1) {
                while (j >= 0) {
                    if (nums[i] > nums[j]) {
                        double tmp = nums[j];
                        nums[j] = nums[i];
                        nums[i] = tmp;
                    }
                    j--;
                }
                i++;
                j = i - 1;
            }
        }

    }

}
