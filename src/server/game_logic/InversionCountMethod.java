package server.game_logic;

import java.util.Arrays;

//Optimal solution to check number of inversions using merge-sort nlogn instead of n^2
class InversionCountMethod {
    private static int mergeAndCount(int[] flatBoard, int l, int m, int r)
    {
        // Left subarray
        int[] left = Arrays.copyOfRange(flatBoard, l, m + 1);

        // Right subarray
        int[] right = Arrays.copyOfRange(flatBoard, m + 1, r + 1);

        int i = 0, j = 0, k = l, swaps = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j])
                flatBoard[k++] = left[i++];
            else {
                flatBoard[k++] = right[j++];
                swaps += (m + 1) - (l + i);
            }
        }

        // Fill from the rest of the left subarray
        while (i < left.length)
            flatBoard[k++] = left[i++];

        // Fill from the rest of the right subarray
        while (j < right.length)
            flatBoard[k++] = right[j++];

        return swaps;
    }

    // Merge sort function
    protected static int mergeSortAndCount(int[] arr, int l, int r)
    {
        // Keeps track of the inversion count at a
        // particular node of the recursion tree
        int count = 0;

        if (l < r) {
            int m = (l + r) / 2;

            // Total inversion count = left subarray count
            // + right subarray count + merge count

            // Left subarray count
            count += mergeSortAndCount(arr, l, m);

            // Right subarray count
            count += mergeSortAndCount(arr, m + 1, r);

            // Merge count
            count += mergeAndCount(arr, l, m, r);
        }

        return count;
    }
}
