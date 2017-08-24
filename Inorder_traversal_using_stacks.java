public class Solution {
	public ArrayList<Integer> inorderTraversal(TreeNode a) {
	    Stack<TreeNode> st=new Stack<TreeNode>();
	    ArrayList<Integer> ar=new ArrayList<Integer>();
	    while(a!=null)
	    {
    	    st.push(a);
    	    a=a.left;
	    }
	    while(!st.isEmpty())
	    {
	        TreeNode t=st.pop();
	        ar.add(t.val);
	        if(t.right!=null)
	        a=t.right;
	        while(a!=null)
	        {
	            st.push(a);
	            a=a.left;
	        }
	    }
	    return ar;
	}
}
