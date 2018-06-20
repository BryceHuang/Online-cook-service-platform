package ofs.exception;

public class EbpException extends Exception {
	public EbpException(String msg) {		
		super(msg);
		System.out.print(msg);
	}
}
