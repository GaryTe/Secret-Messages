import java.util.Scanner;


public class SecretMessage {
	private static Scanner scanner = new Scanner(System.in);
	private static StringBuilder stringBuilder = new StringBuilder();
	private static String indicator;
	private static String text = "";
	private static int firstKey = 0;
	private static int secondKey = 26;
	private static int thirdKey = 10;
	private static String codedText = "";

	public static void main(String[] args) {
		do {
			choiceAction();
			
			switch(indicator) {
			case "e":
				encode();
				break;
			case "d":
				decode();
				break;
			};
			
			stringBuilder.delete(0, text.length());
			text = "";
			firstKey = 0;
			codedText = "";
		
			System.out.println("Enter the: 'n' for exit or the: 'y' for start the game again.");
			indicator = scanner.nextLine();
		} while (indicator.equalsIgnoreCase("y"));
		
		scanner.close();
		stringBuilder = null;
		System.out.println("You got out of the game.");
	}
	
	private static void choiceAction() {
		System.out.println("Enter the: 'e' for encode or the: 'd' for decode or: 'n' for exit:");
		indicator = scanner.nextLine();
	}
	
	private static void checkText() {
		 boolean isValue = true;
		
		while(text.length() == 0 || isValue == true) {
			System.out.println("Enter the text in Latin!.");
			text = scanner.nextLine();
			
			if(text.length() != 0) {
				checkSymbol();
				if(codedText.length() == 0) {
					isValue = false;
				}
			}
			codedText = "";
		}
		
		text = stringBuilder.append(text).reverse().toString();
	}
	
	private static void checkNumber() {
		switch(indicator) {
		case "e":
			while(firstKey <= 0 || firstKey > 25) {
				System.out.println("Enter a whole number from 1 to 25!:");
				try {
					firstKey = Integer.parseInt(scanner.nextLine());
				}catch(Exception e){
					System.out.println("You introduced a number with a floating comma. Try again!.");
				}
			}
			break;
		case "d":
			while(firstKey >= 0 || firstKey < -25) {
				System.out.println("Enter a whole number from -1 to -25!:");
				try {
					firstKey = Integer.parseInt(scanner.nextLine());
				}catch(Exception e) {
					System.out.println("You introduced a number with a floating comma. Try again!.");
				}
			}
			break;
		};
	}
	
	private static void checkSymbol() {
		text.chars().forEach(number -> {
			char symbol = (char) number;
			
			if(symbol >= 'А' && symbol <= 'Я') {
				codedText += symbol;
			}
			
			if(symbol >= 'а' && symbol <= 'я') {
				codedText += symbol;
			}
		});
	}
	
	private static void encode() {
		checkText();
		checkNumber();
		
		text.chars().forEach(number -> {
			char symbol = (char) number;
			
			symbol = getSymbol(symbol);
			
			codedText += symbol;
		});
		
		System.out.println("An encoded line: " + codedText + ". Your key to decoding:" + " -" + firstKey);
	}
	
	private static void decode() {
		checkText();
		checkNumber();
		
		text.chars().forEach(number -> {
			char symbol = (char) number;
			
			symbol = getSymbol(symbol);
			
			codedText += symbol;
		});
		
		System.out.println("An decoded line: " + codedText);
	} 
	
	private static char getSymbol(char symbol) {
		if(symbol >= 'a' && symbol <= 'z') {
			symbol += firstKey;
			
			if(symbol > 'z') {
				symbol -= secondKey;
			}
			
			if(symbol < 'a') {
				symbol += secondKey;
			}
		}
		
		if(symbol >= 'A' && symbol <= 'Z') {
			symbol += firstKey;
			
			if(symbol > 'Z') {
				symbol -= secondKey;
			}
			
			if(symbol < 'A') {
				symbol += secondKey;
			}
		}
		
		if(symbol >= '0' && symbol <= '9') {
			int correctValues;
			if(firstKey > -1) {
				correctValues = (firstKey % thirdKey != 0)? firstKey % thirdKey : 1;
			} else {
				correctValues = (firstKey % thirdKey != 0)? firstKey % thirdKey : -1;
			}
			symbol += correctValues;
			
			if(symbol > '9') {
				symbol -= thirdKey;
			}
			
			if(symbol < '0') {
				symbol += thirdKey;
			}
		}
		
		return symbol;
	}
}
