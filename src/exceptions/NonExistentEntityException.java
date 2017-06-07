package exceptions;

public class NonExistentEntityException extends Exception {

	public NonExistentEntityException() {
	}

	public NonExistentEntityException(String arg0) {
		super(arg0);
	}

	public NonExistentEntityException(Throwable arg0) {
		super(arg0);
	}

	public NonExistentEntityException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public NonExistentEntityException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
