   /* 
    
    class Node 
       int data;
       Node left;
       Node right;
   */
   void levelOrder(Node root) {
      
     
       ArrayList<ArrayList<Integer>> r = new ArrayList<ArrayList<Integer>>();
         tem(0,root,r);
     for(int i=0;i<r.size();i++)
     {
         ArrayList<Integer> tem = r.get(i);
        for(int j=0;j<tem.size();j++)
        {
            System.out.print(tem.get(j)+" ");
        }
     }
            
    }

ArrayList<ArrayList<Integer>> tem(int l,Node root, ArrayList<ArrayList<Integer>> r)
{

    if(root!=null)
    {
        if(l>=r.size())
        {
            ArrayList<Integer> tem = new ArrayList<Integer>();
            tem.add(root.data);
            r.add(tem);
            
        }
        else
        {
            ArrayList<Integer> tem = r.get(l);
             tem.add(root.data);
             
        }
    tem(l+1,root.left,r);
    
    tem(l+1,root.right,r);
    }
    return r;
    
}
