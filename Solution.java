// User function Template for Java

class Solution {
    static int optimalSearchTree(int keys[], int freq[], int n) {
        int[][] cost = new int[n][n];
        int[][] freqSum = new int[n][n];

        for (int i = 0; i < n; i++) {
            freqSum[i][i] = freq[i];
            for (int j = i + 1; j < n; j++) {
                freqSum[i][j] = freqSum[i][j - 1] + freq[j];
            }
        }

        for (int i = 0; i < n; i++) {
            cost[i][i] = freq[i];
        }

        for (int l = 2; l <= n; l++) {
            for (int i = 0; i <= n - l; i++) {
                int j = i + l - 1;
                cost[i][j] = Integer.MAX_VALUE;

                for (int r = i; r <= j; r++) {
                    int lCost;
                    if (r > i) {
                        lCost = cost[i][r - 1];
                    } else {
                        lCost = 0;
                    }

                    int rCost;
                    if (r < j) {
                        rCost = cost[r + 1][j];
                    } else {
                        rCost = 0;
                    }

                    int totalCost = lCost + rCost + freqSum[i][j];

                    if (totalCost < cost[i][j]) {
                        cost[i][j] = totalCost;
                    }
                }
            }
        }

        return cost[0][n - 1];
    }
}

