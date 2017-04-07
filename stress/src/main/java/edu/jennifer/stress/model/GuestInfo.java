package edu.jennifer.stress.model;

import java.util.Random;

public class GuestInfo {

	private final String[] TITLES = {"Mr.","MS.","Mrs."};
	private final String[] SEED   = {"khlaid","Sami","Elma","Lucy","Messi","David","Chris","Anderson","Jessica","Chappel","Mike"};
	private Random rand;
	
	private String title;
	private String sureName;
	private String firstName;
	private String email;
	
	public GuestInfo(){
		rand = new Random();
		this.firstName = SEED[rand.nextInt(SEED.length)];
		this.sureName  = SEED[rand.nextInt(SEED.length)];
		this.email 	  =  firstName + "@" + sureName + ".com";
		this.title 	  =  TITLES[rand.nextInt(TITLES.length)];		
		
	}

	
	public String getTitle() {
		return title;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getSureName() {
		return sureName;
	}
	public String getEmail() {
		return email;
	}
}
