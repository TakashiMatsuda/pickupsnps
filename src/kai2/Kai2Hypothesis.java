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
	public static boolean SIGN = true;
	
	private double[][] n;
	private double[][] e;
	private SNPList snplist;
	private EcoList ecolist;
	private PhenoList phenolist;
	private static Kai2Hypothesis singleton;
	
	
	/**
	 * 
	 */
	Kai2Hypothesis(){
		this.snplist = new SNPList();
		this.ecolist = new EcoList();
		this.phenolist = new PhenoList();
		
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String argv){
		singleton = new Kai2Hypothesis();
		singleton.init();
		singleton.run(SIGN);
		
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
	 * p値の表を出力し、QQプロットのためのtsvファイルを出力し、多重検定の結果上位のSNPsリストを出力します。
	 * @param trigger
	 */
	private void run(boolean trigger){
		singleton.calcKipvalue(snplist);
		singleton.writePValues(snplist);
		
		singleton.calcQQ(snplist);
		singleton.writeQQplot(snplist);
		
		singleton.calcKipvalue(snplist);
		singleton.writeHighRankSNPs(snplist);
	}
	
	
	/**
	 * 
	 * @param snplist2
	 */
	private void calcQQ(SNPList snplist2) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	
	/**
	 * 
	 * @param snplist2
	 */
	private void writeQQplot(SNPList snplist2) {
		// TODO 自動生成されたメソッド・スタブ
		
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
	private void calcKipvalue(SNPList snplist){
		
		
//		return Erf.erfc(sobs);
	}
	
	
}
