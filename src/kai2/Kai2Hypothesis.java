package kai2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;

import snp.*;

import org.apache.commons.math3.special.Erf;
import pheno.PhenoList;
import eco.Eco;
import eco.EcoList;

/**
 * 
 * @author takashi
 * 
 */
public class Kai2Hypothesis {
	public static boolean SIGN = true;
	
	private SNPList snplist;
	private EcoList ecolist;
	private PhenoList phenolist;
	private static Kai2Hypothesis singleton;

	/**
	 * 
	 */
	Kai2Hypothesis() {
		this.snplist = new SNPList(/*216130*/);// こういうコンストラクタを作りたい
		this.ecolist = new EcoList();
		this.phenolist = new PhenoList();

	}

	/**
	 * 
	 * @param argv
	 */
	public static void main(String[] args) {
		singleton = new Kai2Hypothesis();
		singleton.init();
		singleton.run(SIGN);

	}

	private void init() {
		loadFiles();

	}

	/**
	 * 各ファイルをロードして、SNPList, EcoList, PhenoListに格納します。 Input処理です。例外処理を含みます。
	 */
	private void loadFiles() {
		loadsnpfile();
		loadecofile();
		loadphenofile();
		System.out.println("読み込みを完了しました");
	}
	
	
	/*
	 * pheno.txtを読み込んで、ecolistを作成します。(実装完了)
	 */
	private void loadecofile() {
		try{
			String line = null;
			BufferedReader br = new BufferedReader(new FileReader("pheno.txt"));
			StringBuilder a;
			double[] phenovalues = new double[22];
			String[] elements = new String[5];
			String[] prePV = new String[22];
			while((line = br.readLine()) != null){
				/*
				 * 新しいEcoインスタンスを作成し、EcoListに追加します。
				 */
				elements = line.split("	");
				
//				もし必要であればelements[0] ~ [3]も利用してください。今は捨てています。
				prePV = elements[4].split(",");
				for(int i = 0; i < prePV.length; i++){
					if (!prePV[i].equals("NA")){
						phenovalues[i] = Double.parseDouble(prePV[i]);
					}
					else{
						phenovalues[i] = -1;//FIXME この点を考慮したコードを下流実装に注入する必要がある。
					}
				}
				Eco eco = new Eco(phenovalues);
				ecolist.add(eco);
				
			}
			br.close();
		} catch (Exception e){
			e.printStackTrace();
		}
		
	}

	
	/*
	 * pheno_info.txtを読み込み、phenolistを作成します。
	 * 実は特に今は必要ない。最終的にどのphenoの情報を出力するときに必要になる。
	 * (未実装)
	 */
	private void loadphenofile() {
		try{
			String line = null;
			BufferedReader br = new BufferedReader(new FileReader("pheno_info.txt"));
			
			
			br.close();
		} catch(Exception e){
			e.printStackTrace();
		}
		
	}

	/**
	 * snps.txtを読み込み、snplistを作成します。(実装完了)
	 */
	private void loadsnpfile() {
		try {
			String line = null;
			BufferedReader br = new BufferedReader(new FileReader("snps.txt"));
//			StringBuilder chrom = new StringBuilder();
			int pos = 0;
			String[] elements = new String[3];
			char[] alleles = new char[199];
			String[] prealleles = new String[199];
			while ((line = br.readLine()) != null) {
				/*
				 * 新しいSNPインスタンスを作成し、SNPListに追加します。
				 */
				elements = line.split("	");
				pos = Integer.parseInt(elements[1]);// ちょっと重いですね
				/*
				 * elements[2]からcharを取り出す
				 */
				// 重い処理にしますが、データが多くないので気にしない
				// やろうと思えば、preallelesの生成はいらないはずです。(字数を数えていけばよい)
				prealleles = elements[2].split(",");
				for (int i = 0; i < 199; i++) {
						alleles[i] = prealleles[i].charAt(0);
				}
				SNP snp = new SNP(elements[0], pos, alleles);
				snplist.add(snp);
				

			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * p値の表を出力し、QQプロットのためのtsvファイルを出力し、多重検定の結果上位のSNPsリストを出力します。
	 * 
	 * @param trigger
	 */
	private void run(boolean trigger) {
		singleton.calcKipvalue(snplist);
		singleton.writePValues(snplist);// いらない？

		Set<double[]> pointset = singleton.calcQQ(snplist);
		singleton.writeQQplot(pointset);

		singleton.calcHighRankSNPs(snplist);
		singleton.writeHighRankSNPs(snplist);
	}
	
	
	/**
	 * 
	 * @param snplist2
	 */
	private void calcHighRankSNPs(SNPList snplist2) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	/**
	 * QQプロットの作成段階です。
	 * @param snplist2
	 */
	private Set calcQQ(SNPList snplist2) {
		// TODO 次はここから
//		各SNPについてqqプロットを作成する。
		
		
		return null;
		
	}

	/**
	 * 
	 * @param snplist2
	 */
	private void writeQQplot(Set<double[]> points) {
//		tsv出力するか、
//		Rを使うか考える。

	}

	/**
	 * 
	 */
	private void writePValues(SNPList snplist) {

	}

	/**
	 * 
	 * @param snplist
	 */
	private void writeHighRankSNPs(SNPList snplist) {
		
	}

	/**
	 * ki二乗検定のp値の結果を返します。（実装完了）
	 * 
	 * @param x
	 * @return
	 */
	private void calcKipvalue(SNPList snplist) {
		System.out.println("p値ベクトルを計算しています。");
		snplist.setAllphenotablelist(ecolist);
		snplist.setAllPValues();
		
		System.out.println("p値ベクトルの計算が終了しました。");
	}

}
