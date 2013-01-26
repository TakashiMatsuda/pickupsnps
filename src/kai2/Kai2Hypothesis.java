package kai2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
	Kai2Hypothesis() {
		this.snplist = new SNPList();
		this.ecolist = new EcoList();
		this.phenolist = new PhenoList();

	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String argv) {
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
	}

	private void loadecofile() {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	private void loadphenofile() {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	/**
	 * 
	 */
	private void loadsnpfile() {
		try {
			String line = null;
			BufferedReader br = new BufferedReader(new FileReader("snp.txt"));
			StringBuilder chrom = new StringBuilder();
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
				// TODO alleleがNAの時の対処を考えてください。 -> 'N'を入れることにしよう
				// 重い処理にしますが、データが多くないので気にしない
				// やろうと思えば、preallelesの生成はいらないはずです。(字数を数えていけばよい)
				prealleles = elements[2].split(",");
				for (int i = 0; i < 199; i++) {
					if (prealleles[i] != "NA") {
						alleles[i] = prealleles[i].charAt(0);
					} else {
						alleles[i] = 'N';
					}
				}
				SNP snp = new SNP(elements[0], pos, alleles.clone());
				snplist.add(snp);

			}
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


	}

	/**
	 * 
	 * @param snplist2
	 */
	private void writeQQplot(SNPList snplist2) {


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
	 * ki二乗検定のp値の結果を返します。
	 * 
	 * @param x
	 * @return
	 */
	private void calcKipvalue(SNPList snplist) {
		// SNPからPhenoまでつながるしくみが必要だ。
		
		// return Erf.erfc(sobs);
	}

}
