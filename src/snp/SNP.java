package snp;

import java.util.ArrayList;

import org.apache.commons.math3.special.Erf;

import eco.EcoList;

/**
 * 
 * @author takashi
 * 
 */
public class SNP {
	public static boolean DEBUG = true;
	private static final int PHENONUMBER = 22;// FIXME
	// SNPの中のPHENONUMBERを格納できるように変更する

	/*
	 * 属する染色体番号
	 */
	private/* final */String chrom;

	/*
	 * 当該一塩基多型の位置
	 */
	private/* final */int pos;

	/*
	 * 読み込んだ対立遺伝子型列
	 */
	private char[] alleles;

	/*
	 * ある一塩基多型と、ある表現型の同時確率表 phenotableListが必要になってくる
	 */
	// 必要であればリファクタリングする
	private ArrayList<int[][]> phenotableList;
	private ArrayList<double[][]> contingencytablelist;

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
	public SNP(String ch, int p, char[] alle) {
		this.chrom = ch;
		this.pos = p;
		this.alleles = alle;

	}

	/**
	 * phenotablelistとcontingencytablelistをset!します。（実装完了）
	 * 
	 * @param ecolist
	 */
	public void setphenotablelist(EcoList ecolist) {
		// 'A', 'C', 'T', 'G'の扱いの件ですが
		// どちらが多いとかいう情報は必要がなく
		// 単純にSNPのp値が必要になってくるだけですので
		// 0と1で適当に振り分けた上で、その割り振り情報は廃棄して構わないと思います。
		// 例えばT, C, T,...というSNPであれば
		// Tを0, Cを1というように
		// 最初に出てきた塩基を0, もう片方を1とします。

		// 重いです。forの回る順番を変えればかなり早くなります。
		char zero = alleles[0];
		for (int i = 0; i < PHENONUMBER; i++) {
			int[][] prentable = new int[2][2];
			for (int j = 0; j < alleles.length; j++) {
				if (zero == alleles[j]) {
					/*
					 * 
					 */
					// FIXME タテヨコ逆でしたーーーー！！！
					if ((int) ecolist.get(j).getPhenoBinary(i) == 0) {
						prentable[0][0]++;
					} else {
						prentable[0][1]++;
					}
				} else {
					if ((int) ecolist.get(j).getPhenoBinary(i) == 0) {
						prentable[1][0]++;
					} else {
						prentable[1][1]++;
					}

				}

			}
			double[][] preETable = createContingencyTable(prentable);
			phenotableList.add(prentable.clone());
			contingencytablelist.add(preETable.clone());

		}

	}

	/**
	 * phenotableに対して対応するcontingencytableを返します。（実装完了）
	 * 
	 * @param phenotable
	 * @return
	 */
	private double[][] createContingencyTable(int[][] phenotable) {
		/*
		 * 帰無仮説で与えられる各出現回数を計算するための各出現回数の小計の値
		 */
		int[] nbase = new int[2];
		nbase[0] = phenotable[0][0] + phenotable[0][1];
		nbase[1] = phenotable[1][0] + phenotable[1][1];
		int[] npheno = new int[2];
		npheno[0] = phenotable[0][0] + phenotable[1][0];
		npheno[1] = phenotable[0][1] + phenotable[1][1];
		int n = nbase[0] + nbase[1];

		double[][] contingencytable = new double[2][2];
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				contingencytable[i][j] = nbase[i] * npheno[j] + n;// タテヨコ逆だが、問題ないとおもったのでそのまま実装をつづけました。
			}
		}

		return contingencytable;
	}

	/**
	 * pheno表とcontingency表からP値を導出します。（実装完了）
	 * @param phenotable
	 * @param contingencytable
	 * @return
	 */
	private double calcPValues(int[][] phenotable, double[][] contingencytable) {
		double sobs = 0;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				sobs += Math.pow(((double)phenotable[i][j]) - contingencytable[i][j], 2)
						/ contingencytable[i][j];
			}
		}
		/*
		 * p-valueの導出をしています。
		 */
		return Erf.erfc(Math.sqrt(sobs / 2.0));

	}
	
	/**
	 * PValuesをset!します。（実装完了）
	 */
	public void setPvalueArray(){
		this.Pvalues = new double[PHENONUMBER];
		for(int i = 0; i < PHENONUMBER; i++){
			this.Pvalues[i] = calcPValues(phenotableList.get(i), contingencytablelist.get(i));
		}
		
		System.out.println("P値ベクトルの計算が終了しました。");
	}

}
