/* Hidden stub code will pass a root argument to the function below. Complete the function to solve the challenge. Hint: you may want to write one or more helper functions.  

The Node class is defined as follows:
    class Node {
        int data;
        Node left;
        Node right;
     }
*/
    boolean checkBST(Node root) {
        
        return help(root,Integer.MAX_VALUE,Integer.MIN_VALUE);
    }
    
    boolean help(Node root,int h,int l) {
        if(root == null)
            return true;
        else if(root.data >= h || root.data <=l)
            return false;
        else
        {
            return help(root.left,root.data,l) && help(root.right,h,root.data);
          
        }
        
        
    }
    
