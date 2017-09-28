/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isSymmetric(TreeNode root) {
        
        return help( root,  root);
    }
    
    public static boolean help(TreeNode left,TreeNode right)
    {
        if(left == right && left == null)
            return true;
        else if(left == null || right == null)
            return false;
        
        
        if(left.val != right.val)
            return false;
        
       return  help(left.left,right.right) && 
        help(left.right,right.left);
        
    }
}
