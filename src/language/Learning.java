package language;

import java.util.List;
import java.util.ArrayList;

import pheno.*;

public class Learning {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String a = "aaa";
		ArrayList<String> exa = new ArrayList<String>();
		exa.add(a);
		a = null;
		System.out.println(a);
		System.out.println(exa.get(0));
		// これだと期待通りに動く。
//		総称型の理解ができていないのでは？
	}

}
