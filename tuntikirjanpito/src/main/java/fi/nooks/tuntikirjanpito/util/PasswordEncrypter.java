package fi.nooks.tuntikirjanpito.util;

import java.util.Scanner;

import org.springframework.security.crypto.password.StandardPasswordEncoder;

public class PasswordEncrypter {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		StandardPasswordEncoder encoder = new StandardPasswordEncoder();
		System.out.print("Password: ");
		String password = scanner.nextLine();
		String crypted = encoder.encode(password);
		System.out.println("Your encrypted password is (with random salt): " + crypted);
	}
}