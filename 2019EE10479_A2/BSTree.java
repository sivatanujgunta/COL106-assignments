// Class: Implementation of BST in A2
// Implement the following functions according to the specifications provided in Tree.java

public class BSTree extends Tree {

    private BSTree left, right;     // Children.
    private BSTree parent;          // Parent pointer.
        
    public BSTree(){  
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node!.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.
    }    

    public BSTree(int address, int size, int key){
        super(address, size, key); 
    }

    private BSTree getRoot(BSTree a){
        if (a==null){return null;}
        while (a.parent!=null){a=a.parent;}
        return a;
    }

    public BSTree Insert(int address, int size, int key) 
    {
        BSTree a=getRoot(this);
        BSTree n=new BSTree(address,size,key);
        if (a==null){return null;}
        if (a.right==null){
            a.right=n;
            n.parent=a;
            return n;
        }
        BSTree b=null;
        a=a.right;
        while (a!=null){
            b=a;
            if (key<a.key){a=a.left;}
            else if (key>a.key){a=a.right;}
            else {
                if (address<a.address){a=a.left;}
                else {a=a.right;}
            }
        }
        n.parent=b;
        if (b!=null){
            if (key<b.key){b.left=n;}
            else if (key>b.key){b.right=n;}
            else {
                if (address<b.address){b.left=n;}
                else{b.right=n;}
            }
        }
        return n;
    }

    private void getDel(BSTree a){
        if (a.left==null && a.right==null){
            if (a.parent.left==a){
                a.parent.left=null;
            }else{a.parent.right=null;}
            a.parent=null;
            return;
        }else if (a.left==null && a.right!=null){
            if (a.parent.left==a){
                a.parent.left=a.right;
                a.right.parent=a.parent;
                a.parent=null;
                a.right=null;
            }else {
                a.parent.right=a.right;
                a.right.parent=a.parent;
                a.parent=null;
                a.right=null;
            }
            return;
        }else if (a.left!=null && a.right==null){
           if (a.parent.left==a){
                a.parent.left=a.left;
                a.left.parent=a.parent;
                a.parent=null;
                a.left=null;
            }else {
                a.parent.right=a.left;
                a.left.parent=a.parent;
                a.parent=null;
                a.left=null;
            }
            return;
        }else {
            BSTree b=a.getNext();
            a.key=b.key;
            a.size=b.size;
            a.address=b.address;
            getDel(b);
        }

    }

    public boolean Delete(Dictionary e)
    { 
        BSTree a=getRoot(this);
        if (e==null){return false;}
        if (e.key==-1&&e.address==-1&&e.size==-1){return false;}
        if (a==null){return false;}
        a=a.right;
        while (a!=null){
            if (e.key<a.key){
                a=a.left;
            }else if (e.key>a.key){
                a=a.right;
            }else {
                if (e.address<a.address){a=a.left;}
                else if (e.address>a.address){a=a.right;}
                else {
                    getDel(a);
                    return true;
                }
            }
        }
        return false;
    }
        
    public BSTree Find(int key, boolean exact)
    { 
        if (exact==true){
            BSTree a=getRoot(this);
            BSTree b=null;
            if (a==null){return null;}
            a=a.right;
            while (a!=null){
                if (key<a.key){
                    a=a.left;
                }else if (key>a.key){
                    a=a.right;
                }else {
                    if (b==null || b.address>a.address){
                        b=a;
                    }
                    a=a.left;
                }
            }
            return b;
        }else {
            BSTree a=this.getFirst();
            while (a!=null && a.key<key){
                a=a.getNext();
            }
            return a;
        }
    }

    public BSTree getFirst()
    { 
        BSTree a=getRoot(this);
        if (a==null || a.right==null){return null;}
        a=a.right;
        while (a.left!=null){
            a=a.left;
        }
        return a;
    }

    public BSTree getNext()
    { 
        if (this==null){return null;}
        if (this.right!=null){
            BSTree a=this.right;
            while (a.left!=null){a=a.left;}
            return a;
        }else{
            BSTree a=this;
            BSTree b=this.parent;
            if (b==null){return null;}
            while (b.parent!=null && a==b.right){
                a=b;
                b=b.parent;
            }
            if (b.key==-1 && b.address==-1 && b.size==-1){return null;}
            return b;
        }
    }

    public boolean sanity()
    { 
        BSTree b=getRoot(this);
        if (b==null){return true;}
        if (b.left!=null){return false;}
        if (b.key!=-1 || b.address!=-1 || b.size!=-1){return false;}
        BSTree a=b.getFirst();
        while (a!=null){
            if (a.parent!=null && a.parent.right!=a && a.parent.left!=a){return false;}
            if (a.left!=null && a.left.parent!=a){return false;}
            if (a.right!=null && a.right.parent!=a){return false;}
            if (a.left!=null){
                if (a.key<a.left.key){return false;}
                else if (a.key==a.left.key && a.address<a.left.address){return false;}
            }
            if (a.right!=null){
                if (a.key>a.right.key){return false;}
                else if (a.key==a.right.key && a.address>a.right.address){return false;}
            }
            a=a.getNext();
        }
        return true;
    }
}


 


