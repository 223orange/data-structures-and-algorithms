package com.algorithms;

/**
 * Implementation of max sub array algorithm from the book "Introduction to algorithms 3rd edition"
 *
 */
public class MaxSubArray {

    public static int[] findMaxCrossSubarray ( int[] array, int low, int mid, int high){
        int left_sum = Integer.MIN_VALUE;
        int sum = 0;
        int max_left = 0;
        for (int i = mid; i >= low; i--) {
            sum = sum + array[i];
            if (sum > left_sum) {
                left_sum = sum;
                max_left = i;
            }
        }
        int right_sum = Integer.MIN_VALUE;
        sum = 0;
        int max_right = 0;
        for (int j = mid + 1; j <= high; j++) {
            sum = sum + array[j];
            if (sum > right_sum) {
                right_sum = sum;
                max_right = j;
            }
        }
        return new int[]{max_left, max_right, left_sum + right_sum};
    }
    public static int[] findMaxSubarray ( int[] array, int low, int high){
        if (high == low) {
            return new int[]{low, high, array[low]};
        } else {
            int mid = (low + high) / 2;
            int[] left_subarray = findMaxSubarray(array, low, mid);
            int[] right_subarray = findMaxSubarray(array, mid + 1, high);
            int[] cross_subarray = findMaxCrossSubarray(array, low, mid, high);
            int left_sum = left_subarray[2];
            int right_sum = right_subarray[2];
            int cross_sum = cross_subarray[2];
            if (left_sum >= right_sum && left_sum >= cross_sum) {
                return left_subarray;
            } else if (right_sum >= left_sum && right_sum >= cross_sum) {
                return right_subarray;
            } else {
                return cross_subarray;
            }
        }
    }
    public static void main(String[] args) {
        int[] A = new int[]{13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};
        int[] result = findMaxSubarray(A, 0, 15);
        System.out.printf("Index From: %s\nIndex To: %s\nSum: %s\n", result[0], result[1], result[2]);
    }
}
