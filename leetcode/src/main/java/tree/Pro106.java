package tree;

public class Pro106 {

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return build(postorder,inorder,0,postorder.length-1,0,inorder.length-1);
    }

    public TreeNode build(int[] post,int[] in,int a,int b,int c,int d){
        if(b<a || d <c)
            return null;
        TreeNode mid = new TreeNode(post[b]);

        int tm=c;
        int td = 0;
        for(int i=c;i<=d;i++)
            if(in[i]==post[b]){
                tm = i;
                break;
            }
        if(tm==c){
            mid.left = null;
            mid.right = build(post, in, a, b-1, c+1, d);
        }else {
            td = d-tm;
            mid.left = build(post, in, a,b-td-1,c,tm-1);
            mid.right = build(post,in,b-td,b-1,tm+1,d);
        }
        return mid;
    }

    public static void main(String[] args) {
        int[] pre = {9,3,15,20,7};
        int[] in = {9,15,7,20,3};
        Pro106 pro106 = new Pro106();
        TreeNode treeNode = pro106.buildTree(pre, in);
        TreeNode.preOrder(treeNode);
        TreeNode.inOrder(treeNode);
    }
}
