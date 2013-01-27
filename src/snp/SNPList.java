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
	public void calcAllphenotable(EcoList ecolist){
		for(int i = 0; i < size(); i++){
			get(i).setphenotable(ecolist);
		}
	}
	
}
