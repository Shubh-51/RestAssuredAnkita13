package com.ninza.hrm.api.genericutility;

import java.util.Random;

public class JavaUtility {
	
	/**
	 * get the Random Number ,in the range of 0-10000
	 * @return
	 */
	public int getRandomNumber() {
		Random ran=new Random();
		int ranno=ran.nextInt();
		
	return ranno;
	}
}
