class Tree {
    public boolean checkSymmetric(TreeNode root) {
        //An helper function that keeps track of my left and right subtrees.
        return help( root,  root);
    }
    
    public static boolean help(TreeNode left,TreeNode right)
    {
        //Base case when we reach end of tree
        if(left == right && left == null)
            return true;
        else if(left == null || right == null)
            return false;
        
        // Check for non symmetry at every point
        if(left.val != right.val)
            return false;
        // Keep check the reflection of the left and right tree at the same time
       return  help(left.left,right.right) && 
        help(left.right,right.left);
        
    }
}
