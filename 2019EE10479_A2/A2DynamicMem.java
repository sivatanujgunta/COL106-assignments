// Class: A2DynamicMem
// Implements Degragment in A2. No other changes should be needed for other functions.

public class A2DynamicMem extends A1DynamicMem {
      
    public A2DynamicMem() {  super(); }

    public A2DynamicMem(int size) { super(size); }

    public A2DynamicMem(int size, int dict_type) { super(size, dict_type); }

    // In A2, you need to test your implementation using BSTrees and AVLTrees. 
    // No changes should be required in the A1DynamicMem functions. 
    // They should work seamlessly with the newly supplied implementation of BSTrees and AVLTrees
    // For A2, implement the Defragment function for the class A2DynamicMem and test using BSTrees and AVLTrees. 

    public void Defragment() {
        Dictionary a=new BSTree();
        Dictionary b=freeBlk.getFirst();
        while (b!=null){
            a.Insert(b.address,b.size,b.address);
            b=b.getNext();
        }
        Dictionary c=a.getFirst();
        while (c!=null){
            int p=c.address + c.size;
            Dictionary d=c.getNext();
            if (d==null){break;}
            int q=d.address;
            if (p==q){
                int n=c.address;
                int m=c.size+d.size;
                Dictionary x=new BSTree(d.address,d.size,d.size);
                freeBlk.Delete(x);
                a.Delete(d);
                x.address=n;
                x.size=c.size;
                x.key=c.size;
                freeBlk.Delete(x);
                freeBlk.Insert(n,m,m);
                c.size=m;
            }
            else {c=c.getNext();}
        }
        a=null;
        return ;
    }
}