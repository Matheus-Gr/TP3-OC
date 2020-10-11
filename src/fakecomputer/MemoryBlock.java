package fakecomputer;

public class MemoryBlock {

	private int[] words;
	private int addBlock;
	private boolean refreshed;
	private int cost;
	private int cacheHit;
	private int cont;
	
	public MemoryBlock(){
		addBlock = -1;
		refreshed = false;
		cost = 0;
		cacheHit = 0;
		cont = 0;
	}
	
	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost += cost;
	}
	
	public void reCost(int cost) {
		this.cost = cost;
	}
	
	public int[] getWords() {
		return words;
	}

	public void setWords(int[] words) {
		this.words = words;
	}

	public int getAddBlock() {
		return addBlock;
	}

	public void setAddBlock(int addBlock) {
		this.addBlock = addBlock;
	}

	public boolean isRefreshed() {
		return refreshed;
	}

	public void setRefreshed() {
		this.refreshed = true;
	}
	
	public void unsetRefreshed() {
		this.refreshed = false;
	}
	
	public void readRefreshed(boolean bool) {
		this.refreshed = bool;
	}
	
	public int getCacheHit() {
		return cacheHit;
	}

	public void setCacheHit(int cacheHit) {
		this.cacheHit = cacheHit;
	}
	
	public void incCont(int n) {
		this.cont += n;
	}
	public int getCont() {
		return cont;
	}
	public void setCont(int cont) {
		this.cont = cont;
	}
}
