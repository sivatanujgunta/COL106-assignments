// Implements Dictionary using Doubly Linked List (DLL)
// Implement the following functions using the specifications provided in the class List

public class A1List extends List {

    private A1List  next; // Next Node
    private A1List prev;  // Previous Node 

    public A1List(int address, int size, int key) { 
        super(address, size, key);
    }
    
    public A1List(){
        super(-1,-1,-1);
        // This acts as a head Sentinel

        A1List tailSentinel = new A1List(-1,-1,-1); // Intiate the tail sentinel
        
        this.next = tailSentinel;
        tailSentinel.prev = this;
    }

    public A1List Insert(int address, int size, int key)
    {
        A1List a = new A1List(address,size,key);
        try{
            a.next=this.next;
            this.next.prev=a;
            this.next=a;
            a.prev=this;
        }catch(NullPointerException e){
            return null;
        }
        return a;
    }

    public boolean Delete(Dictionary d) 
    {
        A1List a=null;
        A1List b=this;
        while (b.prev!=null){
            if (b==d && b.key==d.key){
                b.prev.next=b.next;
                b.next.prev=b.prev;
                b.next=null;
                b.prev=null;
                return true;
            }
            b=b.prev;
        }
        A1List c=this;
        while (c.next!=null){
            if (c==d){
                c.prev.next=c.next;
                c.next.prev=c.prev;
                c.prev=null;
                c.next=null;
                return true;
            }
            c=c.next;
        }
        return false;
    }

    public A1List Find(int k, boolean exact)
    { 
        if (exact==true){
            A1List b=this;
            while (b!=null){
                if (b.key==k){
                    return b;
                }
                b=b.prev;
            }
            A1List c=this;
            while (c!=null){
                if (c.key==k){
                    return c;
                }
                c=c.next;
            }
        }else{
            A1List b=this;
            while (b.prev!=null && b.key<k){
                b=b.prev;
            }
            if (b.prev!=null){
                return b;
            }
            A1List c=this;
            while (c.next!=null && c.key<k){
                c=c.next;
            }
            if (c.next!=null){
                return c;
            }
        }
        return null;
    }

    public A1List getFirst()
    {
        if ((this.prev==null &&this.next.next==null)||(this.prev.prev==null&&this.next==null)){
            return null;
        }
        A1List a=this;
        while(a.prev!=null){
            a=a.prev;
        }
        return a.next;
    }
    
    public A1List getNext() 
    {
        if (this!=null){
            return this.next;
        }
        return null;
    }

    public boolean sanity()
    {
        A1List slow=this;
        A1List fast=this;
        while (slow!=null&&fast!=null&&fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            if (slow==fast){
                return false;
            }
        }
        slow=this;
        fast=this;
        while (slow!=null&&fast!=null&&fast.prev!=null){
            slow=slow.prev;
            fast=fast.prev.prev;
            if (slow==fast){
                return false;
            }
        }
        A1List temp=this;
        while (temp!=null){
            if (temp.next.prev!=temp){return false;}
            temp=temp.prev;
        }
        temp=this;
        while (temp!=null){
            if (temp.prev.next!=temp){return false;}
            temp=temp.next;
        }
        return true;
    }

}


