import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Edge{

	String from;
	String to;
	int weight;

	public Edge(String from, String to, int weight){
		this.from=from;
		this.to=to;
		this.weight=weight;
	}
}

class Vertex{

	String name;
	int totwgt;
	ArrayList<Edge> edges;

	int visited;

	public Vertex(String name){
		this.name=name;
		this.totwgt=0;
		this.edges=new ArrayList<>();
		this.visited=0;
	}

	public void addEd(Edge e){
		edges.add(e);
		totwgt+=e.weight;
	}

	public int compareTo(Vertex b){
		if(this.totwgt>b.totwgt){return 1;}
		if(this.totwgt<b.totwgt){return -1;}
		else{
			int y=this.name.compareTo(b.name);
			if(y>0){return 1;}
			else if(y<0){return -1;}
			else{return 0;}
		}
	} 
}

class Graph{

	int nv;
	int ne;
	ArrayList<Vertex> vertices;
	HashMap<String,Vertex> hashvertex;

	ArrayList<ArrayList<Vertex>> ind;

	public Graph(){
		this.nv=0;
		this.ne=0;
		this.vertices=new ArrayList<>();
		this.ind=new ArrayList<ArrayList<Vertex>>();
		this.hashvertex=new HashMap<>();
	}

	public void addVertex(Vertex v){
		vertices.add(v);
		ind.add(new ArrayList<Vertex>());
		hashvertex.put(v.name,v);
		nv+=1;
	}

	public Vertex getVertex(String a){
		if(hashvertex.containsKey(a)){return hashvertex.get(a);}
		return null;
	}

	public void addEdge(String from, String to, int weight){
		Vertex fro=getVertex(from);
		Vertex t=getVertex(to);
		if(fro==null || t==null){return;}
		Edge e=new Edge(from,to,weight);
		fro.addEd(e);
		Edge e2=new Edge(to,from,weight);
		t.addEd(e2);
		ne+=1;
	}

	public void average(){
		if(nv==0){
			System.out.println(0);
			return;
		}
		System.out.println(String.format("%.2f",(ne*2.0)/nv));
	}

	public void swap(ArrayList<Vertex> vertices,int i, int j){
		Vertex temp=vertices.get(i);
		vertices.set(i,vertices.get(j));
		vertices.set(j,temp);
	}

	public int partition(ArrayList<Vertex> vertices,int low,int high){
		Vertex pivot=vertices.get(high);
		int i=low-1;
		for(int j=low;j<high;j++){
			if(vertices.get(j).compareTo(pivot)==1){
				i++;
				swap(vertices,i,j);
			}
		}
		swap(vertices,i+1,high);
		return i+1;
	}

	public int partition_r(ArrayList<Vertex> vertices,int low,int high){
		int r=(int)(low+(high-low)*Math.random());
		swap(vertices,r,high);
		return partition(vertices,low,high);
	}

	public void quickSort(ArrayList<Vertex> vertices,int low,int high){
		if(low<high){
			int p=partition_r(vertices,low,high);
			quickSort(vertices,low,p-1);
			quickSort(vertices,p+1,high);
		}
	}

	public void rank(){
		quickSort(vertices,0,nv-1);
		int count=1;
		for(Vertex i:vertices){
			System.out.print(i.name);
			if(count<nv){System.out.print(",");}
			count++;
		}
		System.out.println();
	}






	public void dfs(Vertex v, ArrayList<Vertex> a){
		a.add(v);
		v.visited=1;
		for(Edge e:v.edges){
			Vertex w=this.getVertex(e.to);
			if(w.visited==0){
				dfs(w,a);
			}
		}
	}

	public int partin(ArrayList<Vertex> vertices,int low,int high){
		Vertex pivot=vertices.get(high);
		int i=low-1;
		for(int j=low;j<high;j++){
			if(vertices.get(j).name.compareTo(pivot.name)>0){
				i++;
				swap(vertices,i,j);
			}
		}
		swap(vertices,i+1,high);
		return i+1;
	}

	public int partin_r(ArrayList<Vertex> vertices,int low,int high){
		int qq=(int)(low+(high-low)*Math.random());
		swap(vertices,qq,high);
		return partin(vertices,low,high);
	}

	public void quickin(ArrayList<Vertex> vertices,int low,int high){
		if(low<high){
			int p=partin_r(vertices,low,high);
			quickin(vertices,low,p-1);
			quickin(vertices,p+1,high);
		}
	}

	public void swaplists(ArrayList<ArrayList<Vertex>> ind,int i, int j){
		ArrayList<Vertex> temp=ind.get(i);
		ind.set(i,ind.get(j));
		ind.set(j,temp);
	}
	public int compareListTo(ArrayList<Vertex> a,ArrayList<Vertex> b){
		if(a.size()>b.size()){return 1;}
		else if(a.size()<b.size()){return -1;}
		else{
			for(int i=0;i<a.size();i++){
				int yeah=a.get(i).name.compareTo(b.get(i).name);
				if(yeah>0){return 1;}
				else if(yeah<0){return -1;}
			}
		}
		return 0;
	}
	public int partsortind(ArrayList<ArrayList<Vertex>> ind,int low,int high){
		ArrayList<Vertex> pivot=ind.get(high);
		int i=low-1;
		for(int j=low;j<high;j++){
			if(compareListTo(ind.get(j),pivot)==1){
				i++;
				swaplists(ind,i,j);
			}
		}
		swaplists(ind,i+1,high);
		return i+1;
	}

	public int partsortind_r(ArrayList<ArrayList<Vertex>> ind,int low,int high){
		int ll=(int)(low+(high-low)*Math.random());
		swaplists(ind,ll,high);
		return partsortind(ind,low,high);
	}

	public void quicksortind(ArrayList<ArrayList<Vertex>> ind,int low,int high){
		if(low<high){
			int p=partsortind_r(ind,low,high);
			quicksortind(ind,low,p-1);
			quicksortind(ind,p+1,high);
		}
	}

	public void independent_storylines_dfs(){

		for (int q=0;q<nv;q++){
			vertices.get(q).visited=0;
		}
		ind= new ArrayList<ArrayList<Vertex>>();
		for(int m=0;m<nv;m++){ind.add(new ArrayList<Vertex>());}

		int i=0;
		for(int j=0;j<nv;j++){
			if(vertices.get(j).visited==0){
				dfs(vertices.get(j),ind.get(i));
				i++;
			}
		}

		for(int k=0;k<i;k++){
			int yy=ind.get(k).size();
			quickin(ind.get(k),0,yy-1);
		}
		quicksortind(ind,0,i-1);

		for(int k=0;k<i;k++){
			int yy=ind.get(k).size();
			for(int l=0;l<yy;l++){
				System.out.print(ind.get(k).get(l).name);
				if(l<yy-1){System.out.print(",");}
			}
			if(k<i-1){System.out.println();}
		}
	}
}

public class A4_2019EE10479{

	public static ArrayList<String> splitter(String input){
		ArrayList<String> result = new ArrayList<String>();
		int start = 0;
		boolean inQuotes = false;
		for (int current = 0; current < input.length(); current++) {
    		if (input.charAt(current) == '\"') inQuotes = !inQuotes;
    		else if (input.charAt(current) == ',' && !inQuotes) {
        		result.add(input.substring(start, current));
        		start = current + 1;
    		}
		}
		result.add(input.substring(start));
		return result;
	}

	public static void main(String args[]) throws FileNotFoundException{

		String vertices=args[0];
		String edges=args[1];
		String function=args[2];

		Scanner sce=new Scanner(new File(edges));
		Scanner scv=new Scanner(new File(vertices));
		sce.nextLine();
		scv.nextLine();

		Graph g=new Graph();
		while(scv.hasNext()){
			String line=scv.nextLine();
			ArrayList<String> tokens = splitter(line);
			String name=tokens.get(1);
			name=name.replace("\"", "");
			Vertex a=new Vertex(name);
			g.addVertex(a);
		}
		while(sce.hasNext()){
			String line2=sce.nextLine();
			ArrayList<String> tokens2 = splitter(line2);
			String from=tokens2.get(0);
			from=from.replace("\"", "");
			String to=tokens2.get(1);
			to=to.replace("\"", "");
			int weight=Integer.parseInt(tokens2.get(2));
			g.addEdge(from,to,weight);
		}

		switch (function){
			case "average":
				g.average();
				break;
			case "rank":
				g.rank();
				break;
			case "independent_storylines_dfs":
				g.independent_storylines_dfs();
				break;
		}

		sce.close();
		scv.close();
	}
}
