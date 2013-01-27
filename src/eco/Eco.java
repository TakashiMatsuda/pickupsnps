package eco;

/**
 * 
 * @author takashi
 *
 */
public class Eco {
	double[] phenoarray;
	
	/**
	 * 
	 * @param array
	 */
	public Eco(double[] array){
		this.phenoarray = array;
	}
	
	
	/**
	 * int 表現型id -> double 表現型有り/無し
	 * 
	 * @param phenonum
	 * @return
	 */
	public double getPhenoBinary(int phenonum){
		return phenoarray[phenonum];
	}
	
}
