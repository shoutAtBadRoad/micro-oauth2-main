package tree;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

public class Btree<T> implements DataStruct<T>{

    public T val;
    public Btree<T> left;
    public Btree<T> right;

    Btree(T t){
        this.val = t;
        this.left = null;
        this.right = null;
    }

    @Override
    public void insert(T t) {
        if(contain(t)){
            return;
        }
        Btree<T> tree = this;
        while(true){
            if(tree.compare(t)==1){
                if(tree.left!=null)
                    tree = tree.left;
                else {
                    tree.left = new Btree<T>(t);
                    return;
                }
            }else{
                if(tree.right!=null)
                    tree = tree.right;
                else {
                    tree.right = new Btree<T>(t);
                    return;
                }
            }
        }
    }

    public static void first(Btree t){
        if(t==null){
            return;
        }
        System.out.println(t.val.toString());
        first(t.left);
        first(t.right);
    }

    public Btree<T> remove(T t) {
        if(contain(t)){
            Btree<T> tBtree = remove(this,t);
            System.out.println("成功删除");
            return tBtree;
        }else{
            System.out.println("不存在");
            return null;
        }
    }

    private Btree<T> remove(Btree<T> root,T target){
        if(!contain(target))
            return root;
//        root = this;
        Btree<T> root2 = root;
        Btree<T> t = null;
        while (root!=null){
            if(root.compare(target)==0){
                t = root;
                break;
            }else if(root.compare(target)==1){
                root = root.left;
            }else{
                root = root.right;
            }
        }
        /**
         * 去除根节点
         */
        if(t==root2){
            if(t.right!=null){
                if(t.left==null){
                    return t.right;
                }
                Btree<T> left = root2.left;
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
            Btree<T> parent = root2.parent(t.val);
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
        Btree<T> p = root2.parent(t.val);
        if(p.left==t){
            p.left = this.remove(t,t.val);
        }else {
            p.right = this.remove(t,t.val);
        }
        return root2;
    }

    private Btree<T> parent(T t){
        Btree<T> parent = this;
        Btree<T> son;
        if(parent.compare(t)==1){
            son = parent.left;
        }else{
            son = parent.right;
        }
        while(son!=null){
            if(son.compare(t)==0){
                break;
            }else if(son.compare(t)==-1){
                parent = son;
                son = son.right;
            }else{
                parent = son;
                son = son.left;
            }
        }
        return parent;
    }

    @Override
    public boolean contain(T t) {
        Btree<T> root = this;
        while (root!=null){
            if(root.compare(t)==0){
                return true;
            }else if(root.compare(t)==1){
                root = root.left;
            }else{
                root = root.right;
            }
        }
        return false;
    }

    @Override
    public int compare(T t) {
        Class clazz = t.getClass();
        if(t instanceof Integer || t instanceof Double || t instanceof Long){
            if(((Number) this.val).intValue() > ((Number) t).intValue()){
                return 1;
            }else if(((Number) this.val).intValue() < ((Number) t).intValue()){
                return -1;
            }else{
                return 0;
            }
        }
        try {
            Method m = clazz.getDeclaredMethod("compare", clazz);
            return (int) m.invoke(this.val,t);

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void main(String[] args) {
        Btree<Integer> btree = new Btree<>(100);
        Random random = new Random();
        for(int i=0;i<5;i++){
            btree.insert(random.nextInt(200));
        }
        btree = btree.remove(btree,btree.val);
        first(btree);
    }
}
