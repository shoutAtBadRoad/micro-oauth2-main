package tree;

public class Pro105 {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return build(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    public TreeNode build(int[] pre,int[] in,int a,int b,int c,int d){
        if(b<a || d <c)
            return null;
        TreeNode mid = new TreeNode(pre[a]);

        int tm=c;
        int td = 0;
        for(int i=c;i<=d;i++)
            if(in[i]==pre[a]){
                tm = i;
                break;
            }
        if(tm==c){
            mid.left = null;
            mid.right = build(pre, in, a+1, b, c+1, d);
        }else {
            td = tm-c;
            mid.left = build(pre, in, a+1,a+td,c,tm-1);
            mid.right = build(pre,in,a+td+1,b,tm+1,d);
        }
        return mid;
    }

    public static void main(String[] args) {
        int[] pre = {1,2,3};
        int[] in = {3,2,1};
        Pro105 pro105 = new Pro105();
        TreeNode treeNode = pro105.buildTree(pre, in);
        TreeNode.preOrder(treeNode);
        TreeNode.inOrder(treeNode);
    }
}
