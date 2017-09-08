class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        
        List<Integer> res = new ArrayList<Integer>();
        
        int j=0;
        
        int r = matrix.length;
        int cl = matrix[0].length;
        int len = r*cl;
        int c[] = {0};
        if(r==cl && r==1)
        {
          res.add(matrix[0][0]);
            return res;
        }
            
        int rl=r-1,ll=0,ul=0,lol=cl-1;
        
        while(c[0]>=0)
        {
             j=ul;
            ul=ul+1;
            if(c[0] ==len)
            break;
            for(int i=ll;i<rl;i++)
            {
                System.out.print(matrix[i][j]);
                res.add(matrix[i][j]);
            ++c[0];
            }
            j=rl;
            rl=rl-1;
            if(c[0] ==len)
            break;
            for(int i=0;i<lol;i++)
            {
                res.add(matrix[j][i]);
                ++c[0];
            }
            j=lol;
            lol = lol-1;
            if(c[0] ==len)
            break;
            for(int i=rl;i>=ll;--i)
            {
                res.add(matrix[i][j]);
                ++c[0];
            }
            j=ll;
            ll=ll+1;
            if(c[0] ==len)
            break;
            for(int i=lol;i>=ul;--i)
            {
                res.add(matrix[j][i]);
                ++c[0];
            }
            
            
            
        }
        
        return res;
        
        
        
    }
}
