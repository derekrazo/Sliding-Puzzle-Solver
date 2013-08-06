public class NoAnswerException extends Exception {

  public NoAnswerException (String errMsg) {
  	//exception for signalling that there are no possible answers
		super (errMsg);
	}
}
