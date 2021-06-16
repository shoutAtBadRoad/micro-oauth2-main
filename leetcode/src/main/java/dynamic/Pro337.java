package dynamic;


import org.omg.PortableInterceptor.INACTIVE;
import tree.Tree;
import tree.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class Pro337 {
    public Map<Tree,Integer> f = new HashMap<>();
    public Map<Tree,Integer> g = new HashMap<>();
    public int result = 0;
    public int rob(Tree root) {
        if(root==null){
            return 0;
        }
        rob(root.left);
        rob(root.right);
        Integer integer = 0,integer1=0;
        if(g.containsKey(root.left))
        integer = g.get(root.left);
        if(g.containsKey(root.right))
        integer1 = g.get(root.right);

        int f1 = 0,f2 = 0;
        if(f.containsKey(root.left))
            f1 = f.get(root.left);
        if(f.containsKey(root.right))
            f2 = f.get(root.right);
        int max = Math.max(integer + integer1, f1 + f2);
        max = Math.max(max ,integer1+f1);
        max = Math.max(max ,integer+f2);
        f.put(root,root.val+integer+integer1);
        g.put(root, max);
        result = Math.max(root.val+integer+integer1,max);
        return result;
    }

    public static void main(String[] args) {
        Tree tree = new Tree(4);
        int[] f = {1,2,3,5};
        for(int i:f)    tree.insert(i);
        tree.first(tree);
        Pro337 pro337 = new Pro337();
        System.out.println(pro337.rob(tree));
    }
}
