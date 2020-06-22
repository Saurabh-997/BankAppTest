package bankingApp;

public class Money implements Comparable {
	private double amount;
	private Currency currency;

	/**
	 * New Money
	 * 
	 * @param amount   The amount of money
	 * @param currency The currency of the money
	 */
	public Money(double amount, Currency currency) {
		this.amount = amount;
		this.currency = currency;
	}

	/**
	 * Return the amount of money.
	 * 
	 * @return Amount of money as an double type.
	 */
	
	  public double getAmount() { // @TODO: Fill in the code for this
		  return this.amount;
	  }
	  
	 /**
		 * Returns the currency of this Money.
		 * 
		 * @return Currency object representing the currency of this Money
		 */
	
	  public Currency getCurrency() { // @TODO: Fill in the code for this
		  return this.currency;
	  }
	  
	 /**
		 * Returns the amount of the money in the string form "(amount) (currencyname)",
		 * e.g. "10.5 CAD".
		 * 
		 * @return String representing the amount of Money.
		 */
	
	  public String toString() { // @TODO: Fill in the code for this
		  return String.valueOf(this.amount)+" "+this.currency.getName();
	  }
	  
	 /**
		 * Gets the universal value of the Money, according the rate of its Currency.
		 * 
		 * @return The value of the Money in the "universal currency". (USD)
		 */
	
	  public double getUniversalValue() { // @TODO: Fill in the code for this
		  return currency.valueInUSD(this.amount);
	  }
	  
	 /**
		 * Check to see if the value of this money is equal to the value of another
		 * Money of some other Currency.
		 * 
		 * @param other The other Money that is being compared to this Money.
		 * @return A Boolean indicating if the two monies are equal.
		 */
	
	  public boolean equals(Money other) { // @TODO: Fill in the code for this		  
		  if(this.getUniversalValue()==(other.getUniversalValue())) return true;
		  return false;
	  }
	  
	 /**
		 * Adds a Money to this Money, regardless of the Currency of the other Money.
		 * 
		 * @param other The Money that is being added to this Money.
		 * @return A new Money with the same Currency as this Money, representing the
		 *         added value of the two. (Remember to convert the other Money before
		 *         adding the amounts)
		 */
	
	  public Money add(Money other) { // @TODO: Fill in the code for this
		  if (currency.getName()==other.currency.getName()) amount=amount+other.amount;
		  else {
			  double usAmount=other.getUniversalValue();
			  double convertedAmount = usAmount/currency.getRate();
			  amount=amount+convertedAmount;
		  }
		  amount=this.currency.round(amount, 2);
		  return this;
	  }
	  
	 /**
		 * Subtracts a Money from this Money, regardless of the Currency of the other
		 * Money.
		 * 
		 * @param other The money that is being subtracted from this Money.
		 * @return A new Money with the same Currency as this Money, representing the
		 *         subtracted value. (Again, remember converting the value of the other
		 *         Money to this Currency)
		 */
	
	 public Money subtract(Money other) { // @TODO: Fill in the code for this
		if (currency.getName()==other.currency.getName()) amount=amount-other.amount;
		else {
			double usAmount=other.getUniversalValue();
			double convertedAmount = usAmount/currency.getRate();
			amount=amount-convertedAmount;
		}
		amount=this.currency.round(amount, 2);
		return this;
	 }
	  
	 /**
		 * Check to see if the amount of this Money is zero or not
		 * 
		 * @return True if the amount of this Money is equal to 0.0, False otherwise
		 */
	
	  public boolean isZero() { // @TODO: Fill in the code for this
		  if (this.amount==0.0) return true;
		  return false;
	  }
	  /**
	   	 * Negate the amount of money, i.e. if the amount is 10.0 CAD the negation
		 * returns -10.0 CAD
		 * 
		 * @return A new instance of the money class initialized with the new negated
		 *         money amount.
		 */
	
	  public Money negate() { // @TODO: Fill in the code for this
		  return new Money(-(this.amount), this.currency);
	  }
	  
	 /**
		 * Compare two Money objects. compareTo is required because the class implements
		 * the Comparable interface. (Remember the universalValue method, and that
		 * Integers already implement Comparable). Also, since compareTo must take an
		 * Object, you will have to explicitly downcast it to a Money.
		 * 
		 * @return 0 if the values of the two money are equal. A negative integer if
		 *         this Money is less valuable than the other Money. A positive integer
		 *         if this Money is more valuable than the other Money.
		 */
	  
	  public int compareTo(Object other) { // @TODO: Fill in the code for this
		  if(this.getUniversalValue()==((Money)other).getUniversalValue()) return 0;
		  else if(this.getUniversalValue()>((Money)other).getUniversalValue()) return 1;
		  else return -1;
	  }
			 
}
