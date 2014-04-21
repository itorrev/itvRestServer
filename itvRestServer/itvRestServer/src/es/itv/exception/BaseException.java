package es.itv.exception;

public class BaseException extends Exception
{

	private static final long serialVersionUID = 1L;

	public BaseException()
	{
	}

	public BaseException(String message)
	{
		super(message);
	}

	public BaseException(Throwable t)
	{
		super(t);
	}

	public BaseException(String message, Throwable t)
	{
		super(message, t);
	}

}
