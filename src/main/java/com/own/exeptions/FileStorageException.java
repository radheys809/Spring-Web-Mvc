package com.own.exeptions;

public class FileStorageException extends Exception {

	private final String message;

	public FileStorageException(String message, Throwable ex) {
		super(ex);
		this.message = message;
	}

	public FileStorageException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -1487920987218236560L;

}
