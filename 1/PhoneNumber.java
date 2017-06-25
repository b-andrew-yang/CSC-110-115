public class PhoneNumber
{
	private String digits;
	private String label;

	// Purpose:
	// 	Initialize this instance of PhoneNumber
	// 	set the label to be "Default"
	//
	public PhoneNumber (String theDigits)
	{
        digits = theDigits;
        label = "Default";
	}

	// Purpose:
	//	Initialize this instance of PhoneNumber
	//
	public PhoneNumber (String theDigits, String theLabel)
	{
        digits = theDigits;
        label = theLabel;
	}

	// Purpose:
	// 	Change the digits associated with this PhoneNumber
	// 	to be newDigits
	//
	public void setDigits (String newDigits)
	{
        digits = newDigits;
	}

	// Purpose:
	//	Return the digits associated with this PhoneNumber
	//
	public String getDigits()
	{
		return digits;
	}

	// Purpose:
	//	Change the label associated with this PhoneNumber 
	//	to be newLabel
	//	
	public void setLabel (String newLabel)
	{
        label = newLabel;
	}

	// Purpose:
	//	Return the label associated with this PhoneNumber
	//
	public String getLabel()
	{
		return label;
	}

	// Purpose:
	//	Compare this instance of PhoneNumber to other
	//	return true if they are the same.
	//	
	//	We consider two PhoneNumbers to be equal if
	//	their digits are the same.  We do NOT consider
	//	the label.
	//
	// Pre-conditions:
	//	other is not null
	//
	// Returns:
	//	true	if this instance's digits are the same as
	//		other's digits
	//	false	otherwise
	//
	// Examples:
	//
	//	PhoneNumber p = new PhoneNumber("5551212", "Work");
	//	PhoneNumber q = new PhoneNumber("5551212", "Cell");
	//	PhoneNumber r = new PhoneNumber("3331212");
	//	
	//	p.equals(q)	- returns true
	//	p.equals(r)	- returns false
	public boolean equals (PhoneNumber other)
	{
        if(other.digits.equals(digits)){
            return true;
        }
		return false;
	}

	// Purpose:
	//	Return a String representation of this PhoneNumber
	// 
	// Returns:
	//	label:digits
	//	
	// Examples:
	// 	PhoneNumber p = new PhoneNumber("5551212", "Work");
	//	
	//	p.toString() returns	Work:5551212
	//
	public String toString()
	{
        StringBuilder sb = new StringBuilder();
        sb.append(label + ":");
        sb.append(digits);
        
		return sb.toString();
	}
}