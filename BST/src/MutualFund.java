
/**
* MutualFund.java
* @author Tan Dung Dong
* @author Truong Phu Vu
* CIS 22C, Lab 5
*/

import java.text.DecimalFormat;
import java.util.Comparator;

public class MutualFund {
	private final String fundName;
	private final String ticker;
	private double pricePerShare;

	/** CONSTRUCTORS */

	/**
	 * One-argument constructor that assigns a fundName and "no ticker" to the
	 * ticker and -1 to pricePerShare
	 * 
	 * @param fundName the fund name
	 */
	public MutualFund(String fundName) {
		this.fundName = fundName;
		this.ticker = "no ticker";
		this.pricePerShare = -1;
	}

	/**
	 * Three-argument constructor
	 * 
	 * @param fundName      the mutual fund name
	 * @param ticker        the ticker symbol
	 * @param pricePerShare the price per share
	 */
	public MutualFund(String fundName, String ticker, double pricePerShare) {
		this.fundName = fundName;
		this.ticker = ticker;
		this.pricePerShare = pricePerShare;
	}

	/** ACCESSORS */

	/**
	 * Accesses the name of the fund
	 * 
	 * @return the fund name
	 */
	public String getFundName() {
		return fundName;
	}

	/**
	 * Accesses the ticker symbol
	 * 
	 * @return the ticker symbol
	 */
	public String getTicker() {
		return ticker;
	}

	/**
	 * Accesses the price per share
	 * 
	 * @return the price per share
	 */
	public double getPricePerShare() {
		return pricePerShare;
	}

	/** MUTATORS */

	/**
	 * Updates the share price
	 * 
	 * @param pricePerShare the new share price
	 */
	public void setPricePerShare(double pricePerShare) {
		pricePerShare = this.pricePerShare;
	}

	/** ADDITIONAL OPERATIONS */

	/**
	 * Creates a String of the mutual fund information in the format: <new line>
	 * <fundName> <ticker> Share Price: $<pricePerShare> <new line>
	 */
	@Override
	public String toString() {
		System.out.println(fundName);
		System.out.println(ticker);
		System.out.println("Share price: $" + pricePerShare);
		return "\n";
	}

}