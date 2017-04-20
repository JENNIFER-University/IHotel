package edu.jennifer.ihotel.util;


public class HotelExceptions extends Exception{

	private static final long serialVersionUID = 1L;

	public HotelExceptions(String msg) {super(msg);}

	public HotelExceptions(Throwable t) {super(t);}

	public HotelExceptions(String msg,Throwable t) {super(msg,t);}


}
