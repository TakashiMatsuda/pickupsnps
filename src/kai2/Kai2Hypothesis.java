package kai2;

import snp.*;
import org.apache.commons.math3.special.Erf;
import pheno.PhenoList;
import eco.EcoList;


/**
 * 
 * @author takashi
 *
 */
public class Kai2Hypothesis {
	
	private double[][] n;
	private double[][] e;
	private SNPList snplist;
	private EcoList ecolist;
	private PhenoList phenolist;
	private static Kai2Hypothesis singleton;
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String argv){
		singleton = new Kai2Hypothesis();
		singleton.init();
		singleton.run(true);
		
	}
	
	
	private void init(){
		loadFiles();
	}
	
	/**
	 * 各ファイルをロードして、SNPList, EcoList, PhenoListに格納します。
	 * Input処理です。例外処理を含みます。
	 */
	private void loadFiles(){
		
	}
	
	
	/**
	 * p値の表を出力し、多重検定の結果上位のSNPsリストを出力します。
	 * @param trigger
	 */
	private void run(boolean trigger){
		
	}
	
	/**
	 * 
	 */
	private void writePValues(SNPList snplist){
		
	}
	
	
	/**
	 * 
	 * @param snplist
	 */
	private void writeHighRankSNPs(SNPList snplist){
		
	}
	
	
	/**
	 * ki二乗検定のp値の結果を返します。	
	 * 
	 * @param x
	 * @return
	 */
	private double calcKipvalue(double sobs){
		
		
		return Erf.erfc(sobs);
	}
	
	
}
