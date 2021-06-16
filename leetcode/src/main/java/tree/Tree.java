package tree;

import java.util.Random;

/**
 * @author JYP
 * @date 2021/6/8
 **/

public class Tree {

    public int val;
    public Tree left;
    public Tree right;

    public Tree(int t){
        val = t;
        left = null;
        right = null;
    }
    Tree(){

    }

    public boolean compare(int t){
        return val >= t;
    }

    public boolean insert(Tree tree){
        Tree t = this;
        while(true){
            if(t.compare(tree.val)){
                if(t.left!=null)
                    t = t.left;
                else {
                    t.left = tree;
                    return true;
                }
            }else{
                if(t.right!=null)
                    t = t.right;
                else {
                    t.right = tree;
                    return true;
                }
            }
        }
    }

    public boolean insert(int val){
        return this.insert(new Tree(val));
    }

    public static Tree remove(Tree root,int v){
        Tree t = root.exist(v);
        if(t==null){
            return root;
        }
        /**
         * 去除根节点
         */
        if(t==root){
            if(t.right!=null){
                if(t.left==null){
                    return t.right;
                }
                Tree left = root.left;
                while(left.right!=null){
                    left = left.right;
                }
                left.right = t.right.left;
                t.right.left = t.left;
                return t.right;
            }else{
                return t.left;
            }
        }
        /**
         * 去除叶子节点
         */
        if(t.left==null && t.right==null){
            Tree parent = root.parent(t);
            if(parent.left == t){
                parent.left = null;
            }else {
                parent.right = null;
            }
            return root;
        }
        /**
         * 一般节点
         */
        Tree p = root.parent(t);
        if(p.left==t){
            p.left = Tree.remove(t,t.val);
        }else {
            p.right = Tree.remove(t,t.val);
        }
        return root;
    }

    public Tree parent(Tree t){
        Tree parent = this;
        Tree son;
        if(t.val > parent.val){
            son = parent.left;
        }else{
            son = parent.right;
        }
        while(son!=null){
            if(t.val == son.val){
                break;
            }else if(t.val > son.val){
                parent = son;
                son = son.right;
            }else{
                parent = son;
                son = son.left;
            }
        }
        return parent;
    }

    public void  first(Tree tree){
        if(tree==null){
            return;
        }
        System.out.println(tree.val);
        first(tree.left);
        first(tree.right);
    }

    public Tree max(){
        return null;
    }

    public Tree min(){
        return null;
    }

    public static int height(Tree tree){
        if(tree==null){
            return 0;
        }
        int left = Tree.height(tree.left);
        int right = Tree.height(tree.right);
        return Math.max(left, right)+1;
    }

    public Tree exist(int v){
        Tree t = this;
        while(t!=null){
            if(t.val==v){
                return t;
            }else if(t.val > v) {
                t = t.left;
            }else {
                t = t.right;
            }
        }
        return null;
    }

    public static void preOrder(Tree node){
        if(node==null)
            return;
        System.out.println(node.val);
        preOrder(node.left);
        preOrder(node.right);
    }

    public static void inOrder(Tree node){
        if(node==null)
            return;

        inOrder(node.left);
        System.out.println(node.val);
        inOrder(node.right);
    }

    public static void main(String[] args) {
        Tree tree = new Tree(100);
        Random random = new Random();
        for(int i=0;i<4;i++){
            tree.insert(random.nextInt(200));
        }
        tree.first(tree);
        tree = Tree.remove(tree,tree.val);
        tree.first(tree);
        System.out.println("tree's height is " + Tree.height(tree));
    }
}

