package snp;

import java.util.ArrayList;

import eco.EcoList;

/**
 * 
 * @author takashi
 *
 */
public class SNPList extends ArrayList<SNP>{
	
	
	/**
	 * 
	 * @param ecolist
	 */
	public void setAllphenotablelist(EcoList ecolist){
		for(int i = 0; i < size(); i++){
			get(i).setphenotablelist(ecolist);
		}
	}
	
	/**
	 * 
	 */
	public void setAllPValues(){
		for(int i = 0; i < this.size(); i++){
			this.get(i).setPvalueArray();
		}
	}
}
