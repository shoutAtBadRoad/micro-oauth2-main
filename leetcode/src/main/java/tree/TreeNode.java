package tree;

public class TreeNode extends Tree{
      public int val;
      public TreeNode left;
      public TreeNode right;

    public TreeNode(int t) {
        super(t);
    }

    public TreeNode() {
        super();
    }

    public static void preOrder(TreeNode node){
            if(node==null)
                  return;
           System.out.println(node.val);
           preOrder(node.left);
           preOrder(node.right);
     }

     public static void inOrder(TreeNode node){
           if(node==null)
                 return;

           inOrder(node.left);
           System.out.println(node.val);
           inOrder(node.right);
     }
 }
