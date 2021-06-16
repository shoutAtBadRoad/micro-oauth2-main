package tree;

import java.util.*;

public class Pro107 {

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        Queue<TreeNode> que = new LinkedList<>();
        Queue<TreeNode> que2 = new LinkedList<>();
        List<List<Integer>> result = new ArrayList<>();
        Stack<List<Integer>> stack = new Stack<>();
        if(root!=null){
            que.add(root);
        }
        while(!que.isEmpty() || !que2.isEmpty()){
            List<Integer> r = new ArrayList<>();
            while(!que.isEmpty()){
                TreeNode node = que.poll();
                r.add(node.val);
                if(node.left!=null)
                    que2.add(node.left);
                if(node.right!=null)
                    que2.add(node.right);
            }
            if(r.size()!=0)
                stack.add(r);
            r = new ArrayList<>();
            while(!que2.isEmpty()){
                TreeNode node = que2.poll();
                r.add(node.val);
                if(node.left!=null)
                    que.add(node.left);
                if(node.right!=null)
                    que.add(node.right);
            }
            if(r.size()!=0)
                stack.add(r);
        }
        while(!stack.empty()){
            result.add(stack.pop());
        }
        return result;
    }

}
