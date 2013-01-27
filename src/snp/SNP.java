package snp;

import java.util.ArrayList;
import java.util.List;

import eco.EcoList;

/**
 * 
 * @author takashi
 *
 */
public class SNP {
	public static boolean DEBUG = true;
	private static final int PHENONUMBER = 22;// FIXME SNPの中のPHENONUMBERを格納できるように変更する
	
	/*
	 * 属する染色体番号
	 */
	private /*final*/ String chrom;
	
	/*
	 * 当該一塩基多型の位置
	 */
	private /*final*/ int pos;
	
	/*
	 * 読み込んだ対立遺伝子型列
	 */
	private char[] alleles;
	
	/*
	 * ある一塩基多型と、ある表現型の同時確率表
	 * phenotableListが必要になってくる
	 */
	
	private double[][] phenotable;
	private ArrayList<double[][]> phenotableList;
	private double[][] contigencytable;
	
	/*
	 * A, T, C, Gのどれが上で0,1に相当するかの変換表 <- 最初から4つとってもいいのでは？
	 */
	private byte[] allelenum;
	
	/*
	 * 各表現型に対するp値
	 */
	private double[] Pvalues;
	
	
	/**
	 * 
	 * @param ch
	 * @param p
	 * @param alle
	 */
	public SNP(String ch, int p, char[] alle){
		this.chrom = ch;
		this.pos = p;
		this.alleles = alle;
		
	}
	
	
	/**
	 * 
	 * @param ecolist
	 */
	public void setphenotable(EcoList ecolist){
		// 'A', 'C', 'T', 'G'の扱いの件ですが
		// どちらが多いとかいう情報は必要がなく
//		単純にSNPのp値が必要になってくるだけですので
//		0と1で適当に振り分けた上で、その割り振り情報は廃棄して構わないと思います。
//		例えばT, C, T,...というSNPであれば
//		Tを0, Cを1というように
//		最初に出てきた塩基を0, もう片方を1とします。
		
//		重いです。forの回る順番を変えればかなり早くなります。
		char zero = alleles[0];
		for(int i = 0; i < PHENONUMBER; i++){
			double[][] prentable = new double[2][2];
			for(int j = 0; j < alleles.length; j++){
				if (zero == alleles[j]){
					/*
					 * 
					 */
					if ((int) ecolist.get(j).getPhenoBinary(i) == 0){
						prentable[0][0]++;
					}
					else{
						prentable[0][1]++;
					}
				}
				else{
					if ((int) ecolist.get(j).getPhenoBinary(i) == 0){
						prentable[1][0]++;
					}
					else{
						prentable[1][1]++;
					}
					
				}
				
			}
			phenotableList.add(prentable.clone());
			
		}
		
	}
	
}
