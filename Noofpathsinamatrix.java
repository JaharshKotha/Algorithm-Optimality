/*
 * Complete the function below.
 */

    static int numberOfPaths(int[][] a) {
        int c = a[0].length;
        int r = a.length;
        if(a[0][0] == 0 ||a[r-1][c-1]==0 )
        {
            return 0;
        }

        return countWays(a,r,c);
       
    }

 private static int countWays(int[][] maze, int r,int c) {

        int dp[][] = new int[r][c];
        dp[0][0] = 1;
        for(int i = 1;i<r;i++){
            dp[i][0] = maze[i][0]!=0? dp[i-1][0]:0;
            
        }

     for(int i = 1;i<c;i++){
            
            dp[0][i] = maze[0][i]!=0? dp[0][i-1]:0;
        }

        for(int i=1;i<r;i++){
            for(int j=1;j<c;j++){
                if(maze[i][j]==0)
                    dp[i][j] = 0;
                else
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[r-1][c-1];
    }

