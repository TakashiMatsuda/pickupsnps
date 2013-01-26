package snp;

import java.util.List;

/**
 * 
 * @author takashi
 *
 */
public class SNP {
	public static boolean DEBUG = true;
	
	/*
	 * 読み込んだ対立遺伝子型列
	 */
	private char[] alleles;
	
	/*
	 * ある一塩基多型と、ある表現型の同時確率表
	 */
	private double[][] phenotable;
	private double[][] contigencytable;
	
	/*
	 * A, T, C, Gのどれが上で0,1に相当するかの変換表 <- 最初から4つとってもいいのでは？
	 */
	private byte[] allelenum;
	
	/*
	 * 各表現型に対するp値
	 */
	private double[] Pvalues;
	
	
	
	
	
}
