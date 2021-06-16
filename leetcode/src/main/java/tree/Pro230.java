package tree;

public class Pro230 {

    public int result;

    public int kthSmallest(Tree root, int k) {
        search(root,k,false,0);
        return result;
    }

    public int search(Tree node,int k,boolean b,int v){
        if(node==null){
            return 0;
        }
        if(b){
            int left = search(node.left,k, true,v);
            if((left+1+v)==k){
                result = node.val;
                return -1;
            }else if(left==-1){
                return -1;
            }
            int right = search(node.right,k,true,left+v+1);
            if(right==-1)
                return -1;
            return left+right+1;
        }else{
            int left = search(node.left,k, false,0);
            if((left+1)==k){
                result = node.val;
                return -1;
            }else if(left==-1){
                return -1;
            }
            int right = search(node.right,k,true,left+v+1);
            if(right==-1)
                return -1;
            return right+left+1;
        }
    }

    public static void main(String[] args) {
        int[] nodes = {5,3,6,2,4,1};
        Tree root = new TreeNode(5);
        for (int i=1;i<nodes.length;i++) root.insert(nodes[i]);
        Tree.preOrder(root);

        Pro230 pro230 = new Pro230();
        for(int i=0;i<=6;i++)
        System.out.println(pro230.kthSmallest(root, i));
    }
}
