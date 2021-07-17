// Class: A1DynamicMem
// Implements DynamicMem
// Does not implement defragment (which is for A2).

public class A1DynamicMem extends DynamicMem {
      
    public A1DynamicMem() {
        super();
    }

    public A1DynamicMem(int size) {
        super(size);
    }

    public A1DynamicMem(int size, int dict_type) {
        super(size, dict_type);
    }

    public void Defragment() {
        return ;
    }

    // In A1, you need to implement the Allocate and Free functions for the class A1DynamicMem
    // Test your memory allocator thoroughly using Doubly Linked lists only (A1List.java).

    public int Allocate(int blockSize) {
        Dictionary a=freeBlk.Find(blockSize,false);
        if (blockSize<=0){return -1;}
        if (a==null){return -1;}
        else{
            int b=a.address;
            if (a.size==blockSize){
                allocBlk.Insert(a.address,a.size,a.address);
                freeBlk.Delete(a);
            }else{
                allocBlk.Insert(a.address,blockSize,a.address);
                freeBlk.Insert((a.address+blockSize),((a.size)-blockSize),((a.size)-blockSize));
                freeBlk.Delete(a);
            }
            return b;
        }
    } 
    
    public int Free(int startAddr) {
        Dictionary a=allocBlk.Find(startAddr,true);
        if (a==null){return -1;}
        freeBlk.Insert(a.address,a.size,a.size);
        allocBlk.Delete(a);
        return 0;
    }
}