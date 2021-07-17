// Class: Height balanced AVL Tree
// Binary Search Tree

public class AVLTree extends BSTree {
    
    private AVLTree left, right;     // Children. 
    private AVLTree parent;          // Parent pointer. 
    private int height;  // The height of the subtree
        
    public AVLTree() { 
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node !.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.
        
    }

    public AVLTree(int address, int size, int key) { 
        super(address, size, key);
        this.height = 0;
    }

    // Implement the following functions for AVL Trees.
    // You need not implement all the functions. 
    // Some of the functions may be directly inherited from the BSTree class and nothing needs to be done for those.
    // Remove the functions, to not override the inherited functions.
    
    private AVLTree getRoot(AVLTree a){
        if (a==null){return null;}
        while (a.parent!=null){a=a.parent;}
        return a;
    }

    private boolean htcheck(AVLTree a){
    	if (a==null){return false;}
    	if (a.parent==null){return true;}
    	if (a.left==null){
    		return (a.right==null || a.right.height==0);
    	}
    	if (a.right==null){
    		return (a.left==null || a.left.height==0);
    	}
    	int n=a.left.height-a.right.height;
    	if (n<=1 && n>=-1){
    		return true;
    	}return false;
    }

    private AVLTree rightrotate(AVLTree a){
    	if(a==null){return a;}
    	AVLTree p=a.parent;
    	AVLTree b=a.left;
    	if (b==null){return a;}
    	AVLTree t2=b.right;
    	b.parent=p;
    	if (p!=null){
    		if (p.left==a){p.left=b;}
    		else{p.right=b;}
    	}
    	b.right=a;
    	a.parent=b;
    	a.left=t2;
    	if (t2!=null){
			t2.parent=a;
    	}
    	return b;
    }

    private AVLTree leftrotate(AVLTree a){
    	if(a==null){return a;}
    	AVLTree p=a.parent;
    	AVLTree b=a.right;
		if (b==null){return a;}
    	AVLTree t2=b.left;
		b.parent=p;
    	if (p!=null){
    		if(p.left==a){p.left=b;}
    		else{p.right=b;}
    	}
    	b.left=a;
    	a.parent=b;
    	a.right=t2;
    	if (t2!=null){
    		t2.parent=a;
    	}
    	return b;
    }

	private AVLTree getMax(AVLTree a){
    	if(a==null){return null;}
    	if(a.left==null){return a.right;}
    	if(a.right==null){return a.left;}
    	if(a.left.height>a.right.height){return a.left;}
    	else {return a.right;}
    }

    private boolean checker(AVLTree a){
    	AVLTree b=getMax(a);
    	if(b==null){return false;}
    	if(a.height==b.height){
    		return true;
    	}return false;
    }

    public AVLTree Insert(int address, int size, int key) 
    { 
    	AVLTree a=getRoot(this);
        AVLTree n=new AVLTree(address,size,key);
        if (a==null){return null;}
        if (a.right==null){
            a.right=n;
            n.parent=a;
            return n;
        }
        AVLTree b=null;
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
        AVLTree x=n;
        AVLTree y=x.parent;
        if(y==null){return n;}
        AVLTree z=y.parent;
        if (z==null){return n;}
        if (y.height==0){
        	y.height=1;
        }else{
        	return n;
        }
        if (z.height==1){
        	z.height+=1;
        }else{return n;}
        while (htcheck(z) && z!=null){
        	x=y;
        	y=z;
        	z=z.parent;
        	if(z==null){return n;}
        	if (checker(z)){
				z.height+=1;
        	}else{return n;}
        }
        if (z==null){return n;}
        if (z.left==y){
        	if (y.left==x){
        		z.height-=2;
        		rightrotate(z);
        	}else {
        		x.height+=1;
        		y.height-=1;
        		z.height-=2;
        		leftrotate(y);
        		rightrotate(z);
        	}
        }else {
        	if (y.right==x){
        		z.height-=2;
        		leftrotate(z);
        	}else {
        		x.height+=1;
        		y.height-=1;
        		z.height-=2;
        		rightrotate(y);
        		leftrotate(z);
        	}
        }
        return n;
    }

    private AVLTree getDel(AVLTree a){
        if(a==null){return null;}
    	AVLTree p=a.parent;
        if (a.left==null && a.right==null){
            if (p!=null && a.parent.left==a){
                a.parent.left=null;
            }else if(p!=null){a.parent.right=null;}
            a.parent=null;
            return p;
        }else if (a.left!=null && a.right==null){
           if (p!=null && a.parent.left==a){
                a.parent.left=a.left;
                a.left.parent=a.parent;
                a.parent=null;
                a.left=null;
            }else if(p!=null){
                a.parent.right=a.left;
                a.left.parent=a.parent;
                a.parent=null;
                a.left=null;
            }
            return p;
        }else {
            AVLTree b=a.getNext();
            if(b==null){return null;}
            a.key=b.key;
            a.size=b.size;
            a.address=b.address;
            return getDel(b);
        }

    }

    private int ht(AVLTree a){
        if(a==null){return -1;}
        int n=-1;
        int m=-1;
        if(a.left!=null){
            n=a.left.height;
        }
        if(a.right!=null){
            m=a.right.height;
        }
        return (m>n?m:n)+1;
    }
    private void setbal(AVLTree p){
    	if(p==null){return;}
    	if(p!=null){p.height=ht(p);}
    	while(htcheck(p)){
    		p=p.parent;
            if(p!=null){
                p.height=ht(p);
            }
    	}
    	if(p==null){return;}
    	AVLTree z=p;
    	AVLTree y=getMax(z);
    	AVLTree x=getMax(y);
    	if(y==null){return;}
    	if(x==null){return;}
    	if(z.left==y){
    		if(y.left==x){
    			AVLTree t4=z.right;
    			AVLTree t3=y.right;
    			if(t3!=null && t4!=null && t4.height==t3.height){
    				setbal(rightrotate(z).parent);
    			}else{
    				rightrotate(z);
    			}
    			z.height=ht(z);
                x.height=ht(x);
                y.height=ht(y);
    		}else{
    			leftrotate(y);
    			setbal(rightrotate(z).parent);
                z.height=ht(z);
                y.height=ht(y);
                x.height=ht(x);
    		}
    	}else{
    		if(y.right==x){
    			AVLTree t1=z.left;
    			AVLTree t2=y.left;
    			if(t1!=null && t2!=null && t1.height==t2.height){
    				setbal(leftrotate(z).parent);
    			}else{
    				rightrotate(z);
    			}
                z.height=ht(z);
                x.height=ht(x);
                y.height=ht(y);
    		}else{
    			rightrotate(y);
    			setbal(leftrotate(z));
                z.height=ht(z);
                y.height=ht(y);
                x.height=ht(x);
    		}
    	}
    }

    public boolean Delete(Dictionary e)
    {	
    	AVLTree a=getRoot(this);
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
                	AVLTree p=getDel(a);
                    setbal(p);
                    return true;
                }
            }
        }
        return false;
    }
        
    public AVLTree Find(int k, boolean exact)
    { 
        if (exact==true){
            AVLTree a=getRoot(this);
			AVLTree b=null;
            if (a==null){return null;}
            a=a.right;
            while (a!=null){
                if (k<a.key){
                    a=a.left;
                }else if (k>a.key){
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
            AVLTree a=this.getFirst();
            while (a!=null && a.key<k){
                a=a.getNext();
            }
            return a;
        }
    }

    public AVLTree getFirst()
    { 
        AVLTree a=getRoot(this);
        if (a==null || a.right==null){return null;}
        a=a.right;
        while (a.left!=null){
            a=a.left;
        }
        return a;
    }

    public AVLTree getNext()
    {
        if (this==null){return null;}
        if (this.right!=null){
            AVLTree a=this.right;
            while (a.left!=null){a=a.left;}
            return a;
        }else{
            AVLTree a=this;
            AVLTree b=this.parent;
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
    	AVLTree b=getRoot(this);
        if (b==null){return true;}
        if (b.left!=null){return false;}
        if (b.key!=-1 || b.address!=-1 || b.size!=-1){return false;}
        AVLTree a=b.getFirst();
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
            if(!htcheck(a)){return false;}
            if(a.height!=ht(a)){return false;}
            a=a.getNext();
        }
        return true;
    }
}