package br.com.adal.service;

import java.time.Clock;
import java.time.Instant;

public class Teste {

	public static void main(String[] args) {
		Instant instant2 = Instant.now(Clock.systemDefaultZone());
		System.out.println(instant2);
		
	}

}
