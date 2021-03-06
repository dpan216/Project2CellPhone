import java.util.Scanner;

public class Main1 {
	public static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		PhoneBook myPhoneBook = new PhoneBook();
		CallHistory myCallHistory = new CallHistory();
		////////////////////////////////////////////////////////////
		//TESTING PURPOSES: CONTACTS
		Contact first 	= new Contact("John", "(938)783-6748", "C00l Guy");
		Contact second 	= new Contact("Samantha", "(837)190-7834", "Chick I met");
		Contact third 	= new Contact("Pope Francis", "398-0827", "HOLLLLLYYYYYY");
		Contact fourth 	= new Contact("Commando", "256-0637", "Command me, sir");
		myPhoneBook.addContact(first);
		myPhoneBook.addContact(second);
		myPhoneBook.addContact(third);
		myPhoneBook.addContact(fourth);
		//phoneBook.contactsToString();
		//phoneBook.compareContacts();
		//myPhoneBook.contactsToString();
		/////////////////////////////////////////////
		//TESTING PURPOSES: FAVORITES
		
		Favorite top1 = new Favorite("Timmy","(763)058-9246", "Yummy",1,"C:\\Users\\Chad\\Pictures\\CECS274Proj2\\penguin.jpg", 800, 600);
		Favorite top2 = new Favorite("Hercules", "923-9036", "You're so strong ;)", 2 ,"C:\\Users\\Chad\\Pictures\\CECS274Proj2\\corgi.gif", 800, 600);
		Favorite top3 = new Favorite("Armani", "(928)293-7345", "Sameeeeeee",3,"C:\\Users\\Chad\\Pictures\\CECS274Proj2\\penguin.jpg", 800, 600);
		Favorite top4 = new Favorite("Gucci", "872-0182", "WTF CALVIN HARRIS != TAYLOR SWIFT",4,"C:\\Users\\Chad\\Pictures\\CECS274Proj2\\penguin.jpg", 800, 600);
		Favorite top5 = new Favorite("Cleo", "452-2834", "First love",5,"C:\\Users\\Chad\\Pictures\\CECS274Proj2\\penguin.jpg", 800, 600);
		
		myPhoneBook.addFavorite(top4);
		myPhoneBook.addFavorite(top2);
		myPhoneBook.addFavorite(top3);
		myPhoneBook.addFavorite(top1);
		myPhoneBook.addFavorite(top5);
		//myPhoneBook.favoritesToString();
		//myPhoneBook.compareFavorites();
		//myPhoneBook.favoritesToString();
		////////////////////////////////////////////////////
		//Menus System of the phone, with 5 options
		boolean end = false;
		while (!end){
			displayMenu();
			int response = Integer.parseInt(scan.nextLine());
			switch(response){
			///////////////////////////////////////////////
			//Make a call
			case 1: 
				System.out.println("1.Call by number\n2.Call by name\n3.Call from favorites");
				int response1 = Integer.parseInt(scan.nextLine());
				switch(response1) {
				case 1:
					callByNumber(myPhoneBook, myCallHistory);
					break;
				case 2:
					callByName(myPhoneBook, myCallHistory);
					break;
				case 3:
					callFromFavorites(myPhoneBook, myCallHistory);
					break;
				}
				break;
			//////////////////////////////////////////////////////////
			//Receive a call	
			case 2:
				receiveCall(myPhoneBook, myCallHistory);
				break;
			////////////////////////////////////////////////////////////
			//Access Top 5
			case 3:
				System.out.println("1.Add a favorite.\n2.Remove a favorite.\n3.Switch numbers of two favorites.\n4.Display a favorite.");
				int response3 = Integer.parseInt(scan.nextLine());
				switch (response3){
				case 1:
					addFavoriteContact(myPhoneBook);
					break;
				case 2:
					removeFavoriteContact(myPhoneBook);	
					break;
				case 3:
					switchFavoriteSpeedDialNumber(myPhoneBook);	
					break;
				case 4:
					displayFavorite(myPhoneBook);
					break;
				}
				break;
			////////////////////////////////////////////////////////////
			//Access Phone book
			case 4:
				System.out.println("1.Add a contact.\n2.Edit a contact.\n3.Remove a contact\n4.Display the phone book.");
				int response4 = Integer.parseInt(scan.nextLine());
				switch(response4){
					case 1:
						addContact(myPhoneBook);
						break;
					case 2:
						editContact(myPhoneBook);
						break;
					case 3:
						removeContact(myPhoneBook);
						break;
					case 4:
						displayPhoneBook(myPhoneBook, myCallHistory);
						break;
				}
				break;
			////////////////////////////////////////////////////////////
			//Access Call History
			case 5:
				myCallHistory.displayCallLog();
				boolean again7 = true;
				while (again7 == true) {
					System.out.println("Would you like to view call details? (y/n)");
					String response5 = scan.nextLine();
					if (response5.equals("y")) {
						System.out.println("Please enter caller assigned number");
						String response6 = scan.nextLine();
						myCallHistory.displayHiddenLog(Integer.parseInt(response6));
						again7 = false;
					}
					else if (response5.equals("n")) {
						again7 = false;
					}
					else {
						System.out.println("Test");
					}
				}
			////////////////////////////////////////////////////////////
			//End program
			default:
				end = true;
				break;
		}
		}	

	}
	public static void displayMenu() {
		System.out.println("Phone Menu");
		System.out.println("--------------------");
		System.out.println("1.Make a call.");
		System.out.println("2.Receive a call.");
		System.out.println("3.Access the top 5 contacts.");
		System.out.println("4.Access the phone book.");
		System.out.println("5.Access the call history.");
		System.out.println("6.End the program");
	}

	public static void callByNumber(PhoneBook myPhoneBook, CallHistory myCallHistory) {
		// CallHistory myCallHistory = new CallHistory();
		// PhoneCall myPhoneCall = new PhoneCall();
		String again1 = "";
		while (!again1.equals("done")){
			String formatNumber = "";
			PhoneCall recordedPhoneCall = new PhoneCall();
			boolean numValid = false;
			System.out.println("What is the number you would like to call");
			String number = scan.nextLine();
			while (number.length() != 10 && number.length() != 7) {
				System.out.println("Phone number is invalid, please enter a valid phone number.");
				number = scan.nextLine();
			}
			formatNumber = myPhoneBook.formatNumber(number);
		for(int a = 0; a < myPhoneBook.getContactSize(); a++){
			if (formatNumber.equals(myPhoneBook.getContactArrayList().get(a).getNumber())){
				System.out.println("You called " + myPhoneBook.getContactArrayList().get(a).getName());
				recordedPhoneCall = new PhoneCall(myPhoneBook.getContactArrayList().get(a), recordedPhoneCall.getTimestamp(), true);
				myCallHistory.addCall(recordedPhoneCall);
				numValid = true;
			}
		}
		if(!numValid){
			System.out.println("Number is not on your contacts, but call was still made.");
			Contact unknown = new Contact();
			unknown.setNumber(formatNumber);
			myPhoneBook.addUnknown(unknown);
			recordedPhoneCall = new PhoneCall(unknown, recordedPhoneCall.getTimestamp(), true);
			myCallHistory.addCall(recordedPhoneCall);
			myPhoneBook.unknownToString(); // Testing unknown arraylist
		}
		System.out.println("Do you want to call another number? (add/done)");
		again1 = scan.nextLine();
		}
	}

	public static void callByName(PhoneBook myPhoneBook, CallHistory myCallHistory) {
		String name = " ";
		String userInput = " ";
		boolean again = true;
		boolean again2 = true;
		boolean nameValid = false;
		while (again == true) {
			while(again2 == true){
				PhoneCall recordedPhoneCall = new PhoneCall();
				System.out.println("What is name of the person you would like to call?");
				name = scan.nextLine();
				for(int a = 0; a < myPhoneBook.getContactSize(); a++){
					if (name.equals(myPhoneBook.getContactArrayList().get(a).getName())){
						System.out.println("You called " + myPhoneBook.getContactArrayList().get(a).getName());
						recordedPhoneCall = new PhoneCall(myPhoneBook.getContactArrayList().get(a), recordedPhoneCall.getTimestamp(), true);
						myCallHistory.addCall(recordedPhoneCall);
						nameValid = true;
						again2 = false;
					}
				}
				if (nameValid == false){
					System.out.println("You didn't enter a correct name in your myPhoneBook.");
					System.out.println("Would you like to reenter the name or return to menu?(add/done)");
					userInput = scan.nextLine();
					if (userInput.equals("done")) {
						again2 = false;
					}
				}
			}
			
			
			System.out.println("Would you like to call another contact by name? (add/done)");
			userInput = scan.nextLine();
			if (userInput.equals("add")) {
				again2 = true;
			}
			else if (userInput.equals("done")) {
				again = false;
			}
		}
	}

	public static void callFromFavorites(PhoneBook myPhoneBook, CallHistory myCallHistory) {
		String userInput = "";
		boolean speedDialValid = false;
		boolean again = true;
		boolean again2 = true;
		
		
		while(again == true){	
			while(again2 == true){	
		PhoneCall recordedPhoneCall = new PhoneCall();
		System.out.println("Enter the speed dial number");
		int speedDial = Integer.parseInt(scan.nextLine());
		while(speedDial < 1 || speedDial > 5){
			System.out.println("Enter a valid speed dial number (1-5)");
			speedDial = Integer.parseInt(scan.nextLine());
		}
		for (int a = 0; a < myPhoneBook.getFavoriteSize(); a++){
			if(speedDial == myPhoneBook.getFavoriteArrayList().get(a).getSpeedDial()){
				System.out.println("You called " + myPhoneBook.getFavoriteArrayList().get(a).getName());
				recordedPhoneCall = new PhoneCall(myPhoneBook.getFavoriteArrayList().get(a), recordedPhoneCall.getTimestamp(), true);
				myCallHistory.addCall(recordedPhoneCall);
				speedDialValid = true;
				again2 = false;
			}
		}
		if (speedDialValid == false){
			System.out.println("You didnt enter a valid speed dial number.");
			System.out.println("Would you like to reenter the name or return to menu?(add/done)");
			userInput = scan.nextLine();
			if(userInput.equals("add")){
				again2 = true;
			}
			if(userInput.equals("done")){
				again2 = false;
				}
			}
		}
		
		System.out.println("Would you like to call another favorite by speed dial number? (add/done)");
		userInput = scan.nextLine();
		if (userInput.equals("add")) {
			again2 = true;
		}
		else if (userInput.equals("done")) {
			again = false;
			}
		}
	}

	public static void receiveCall(PhoneBook myPhoneBook, CallHistory myCallHistory) {
		String formatNumber;
		String userInput = "";
		String receiveAnother = "";
		boolean again = true;
		boolean again2 = true;
		int randomOrContact = 0;
		int favIndex;
		int contactIndex;
		//TESTING PURPOSE
		//randomOrContact = 9;
		
		
		////FAVORITE
		while(again == true){
			while (again2 == true){
				randomOrContact = (int) (Math.random()* 10);
				PhoneCall recordedPhoneCall = new PhoneCall();
			/////One of your favorites call
			if(randomOrContact < 5){
				 favIndex = (int) (Math.random() * myPhoneBook.getFavoriteSize());
				 System.out.println("You are getting called by " + myPhoneBook.getFavoriteArrayList().get(favIndex).getName());
				 System.out.println("Do you want to pick up? (y/n)");
				 userInput = scan.nextLine();
				 if(userInput.equals("y")){
					 System.out.println("You picked up and had a deligthful conversation with " + 
							 			myPhoneBook.getFavoriteArrayList().get(favIndex).getName() +
							 			".\n Your call has been recorded.");
						recordedPhoneCall = new PhoneCall(myPhoneBook.getFavoriteArrayList().get(favIndex), recordedPhoneCall.getTimestamp(), false);
						myCallHistory.addCall(recordedPhoneCall);
						again2 =false;
					 }
				 else if(userInput.equals("n")){
					 System.out.println("You rejected the call with " + myPhoneBook.getFavoriteArrayList().get(favIndex).getName()
							 + ".\n Your call has been recorded still.");
					 	recordedPhoneCall = new PhoneCall(myPhoneBook.getFavoriteArrayList().get(favIndex), recordedPhoneCall.getTimestamp(), false);
						myCallHistory.addCall(recordedPhoneCall);
						again2 =false;

				 }
			}
			
			
			// One of your contacts call
			else if(randomOrContact > 4 && randomOrContact < 8){
				contactIndex = (int) (Math.random() * myPhoneBook.getContactSize());
				System.out.println("You are getting called by " + myPhoneBook.getContactArrayList().get(contactIndex).getName());
				System.out.println("Do you want to pick up? (y/n)");
				userInput = scan.nextLine();
				 if(userInput.equals("y")){
					 System.out.println("You picked up and had a deligthful conversation with " + 
							 			myPhoneBook.getContactArrayList().get(contactIndex).getName() +
							 			".\n Your call has been recorded.");
						recordedPhoneCall = new PhoneCall(myPhoneBook.getContactArrayList().get(contactIndex), recordedPhoneCall.getTimestamp(), false);
						myCallHistory.addCall(recordedPhoneCall);
						again2 = false;
					 }
				 else if(userInput.equals("n")){
					 System.out.println("You rejected the call with " + myPhoneBook.getContactArrayList().get(contactIndex).getName()
							 + ".\n Your call has been recorded still.");
					 	recordedPhoneCall = new PhoneCall(myPhoneBook.getContactArrayList().get(contactIndex), recordedPhoneCall.getTimestamp(), false);
						myCallHistory.addCall(recordedPhoneCall);
						again2 =false;
				 }
				
			}
			// Random number calls you	
			else if(randomOrContact > 7){
				
				int randomNumber = (int) (Math.random() * 999999999);
				String areaCode = Integer.toString(randomNumber);
				areaCode = areaCode.substring(0,3);

				randomNumber = (int) (Math.random() * 999999999);
				String firstNumber = Integer.toString(randomNumber);
				firstNumber = firstNumber.substring(0,3);

				randomNumber = (int) (Math.random() * 999999999);
				String secondNumber = Integer.toString(randomNumber);
				secondNumber = secondNumber.substring(0,4);
				String entireNumber = areaCode+firstNumber+secondNumber;
				
				formatNumber = myPhoneBook.formatNumber(entireNumber);
				Contact unknown = new Contact();
				unknown.setNumber(formatNumber);
				myPhoneBook.addUnknown(unknown);
				
				System.out.println("You are getting a call from " + unknown.getNumber());
				System.out.println("Do you want to pick up? (y/n)");
				userInput = scan.nextLine();
				if(userInput.equals("y")){
					 System.out.println("You picked up and had a deligthful conversation with " + 
							 			unknown.getNumber() +
							 			".\n Your call has been recorded.");
						recordedPhoneCall = new PhoneCall(unknown,recordedPhoneCall.getTimestamp(), false);
						myCallHistory.addCall(recordedPhoneCall);
						again2 = false;
					 }
				 else if(userInput.equals("n")){
					 System.out.println("You rejected the call with " + unknown.getNumber()+ ".\n Your call has been recorded still.");
					 	recordedPhoneCall = new PhoneCall(unknown, recordedPhoneCall.getTimestamp(), false);
						myCallHistory.addCall(recordedPhoneCall);
						again2 =false;
				 }
				
				
			}
			
			System.out.println("Do you want to receive another call? (add/done)");
			receiveAnother = scan.nextLine();
			if (receiveAnother.equals("add")) {
				again2 = true;
			}
			else if (receiveAnother.equals("done")) {
				again = false;
				}
			
			}
		
		}

			
	}

	public static void addFavoriteContact(PhoneBook myPhoneBook) {
			boolean again = true;
			String userInput = "";
			String formatNumber = "";
		
		while(again == true){
				System.out.println("Enter the speed dial number of this favorite contact.");
				int favSpeedDial = Integer.parseInt(scan.nextLine());
				while(favSpeedDial < 1 || favSpeedDial > 5){
					System.out.println("Enter a valid speed dial number (1-5)");
					favSpeedDial = Integer.parseInt(scan.nextLine());
				}
				for(int a = 0; a < myPhoneBook.getFavoriteSize(); a++){
					if(favSpeedDial == myPhoneBook.getFavoriteArrayList().get(a).getSpeedDial()){
						Contact removedFavorite  = new Contact();
						removedFavorite.setName(myPhoneBook.getFavoriteArrayList().get(a).getName());
						removedFavorite.setNumber(myPhoneBook.getFavoriteArrayList().get(a).getNumber());
						removedFavorite.setNotes(myPhoneBook.getFavoriteArrayList().get(a).getNotes());
						myPhoneBook.addContact(removedFavorite);
						myPhoneBook.removeFavorite(myPhoneBook.getFavoriteArrayList().get(a));
					}
				}
				
				System.out.println("Enter the name of the favorite contact");
				String favName = scan.nextLine();
				System.out.println("Enter the number of the favorite contact.");
				String favNumber = scan.nextLine();
			while (favNumber.length() != 10 && favNumber.length() != 7) {
				System.out.println("Phone number is invalid, please enter a valid phone number.");
				favNumber = scan.nextLine();
			}
			formatNumber = myPhoneBook.formatNumber(favNumber);
			System.out.println("Enter the note for the favorite contact.");
			String favNotes = scan.nextLine();
			System.out.println("Enter the path of the picture of the favorite contact.");
			String userDirectory = scan.nextLine();
			System.out.println("Enter the width of the image.");
			int imageWidth = Integer.parseInt(scan.nextLine());
			System.out.println("Enter the height of the image.");
			int imageHeight = Integer.parseInt(scan.nextLine());
			Favorite favFavorite = new Favorite(favName, formatNumber, favNotes, favSpeedDial, userDirectory, imageWidth, imageHeight );
			myPhoneBook.addFavorite(favFavorite);
			System.out.println("Do you want to add another favorite? (add/done)");
			userInput = scan.nextLine();
			if(userInput.equals("add")){
				again = true;
			}
			else if(userInput.equals("done")){
			again = false;	
			}
		}
	}

	public static void removeFavoriteContact(PhoneBook myPhoneBook) {
		String userInput = "";
		boolean validFavorite = false;
		boolean again = true;
		boolean again2 = true;
	
	while(again == true){
		while(again2 == true){
		System.out.println("Name the favorite you want to remove?");
		userInput = scan.nextLine();
		for(int a = 0; a < myPhoneBook.getFavoriteSize(); a++){
			if(userInput.equals(myPhoneBook.getFavoriteArrayList().get(a).getName())){
				System.out.println("You have removed " + myPhoneBook.getFavoriteArrayList().get(a).getName() + " off of the favorites list." );
				myPhoneBook.removeFavorite(myPhoneBook.getFavoriteArrayList().get(a));
				validFavorite = true;
				again2 = false;
			}
		}
			if(validFavorite == false){
				System.out.println("You did not name a valid favorite name. Would you like to reenter the name? (add/done)");
				userInput = scan.nextLine();
				if(userInput.equals("done")){
					again2 = false;
					}
				else if(userInput.equals("add")){
					again2 = true;
				}
				}
		
		}
		System.out.println("Do you want to remove another favorite? (add/done)");
		userInput = scan.nextLine();
		if(userInput.equals("done")){
			again = false;
		}
		else if(userInput.equals("add")){
			again = true;
		}
		
		}
	}

	public static void switchFavoriteSpeedDialNumber(PhoneBook myPhoneBook) {
		String userInput = "";
		boolean validFav = false;
		boolean validFav2 = false;
		int speedDial = 0;
		int speedDial2 = 0;
		int favIndex = 0;
		int fav2Index = 0;
		int tempSpeedDial = 0;
		boolean again = true;
		boolean again2 = true;
		
	while(again == true){
		while(again2 == true){
		System.out.println("What is the first favorite speed dial that you would like to switch?");
		speedDial = Integer.parseInt(scan.nextLine());
		while(speedDial < 1 || speedDial > 5){
			System.out.println("Enter a valid speed dial number (1-5)");
			speedDial = Integer.parseInt(scan.nextLine());
		}
		for(int a = 0; a < myPhoneBook.getFavoriteSize(); a ++){
			if(speedDial == myPhoneBook.getFavoriteArrayList().get(a).getSpeedDial()){
				validFav = true;
				favIndex = a;
			}
		}
		System.out.println("What is the second speed dial that you would like to switch?");
		speedDial2 = Integer.parseInt(scan.nextLine());
		while(speedDial2 < 1 || speedDial2 > 5){
			System.out.println("Enter a valid speed dial number (1-5)");
			speedDial2 = Integer.parseInt(scan.nextLine());
		}
		for(int b = 0; b < myPhoneBook.getFavoriteSize(); b++){
			if(speedDial2 == myPhoneBook.getFavoriteArrayList().get(b).getSpeedDial() && speedDial != speedDial2){
				validFav2 = true;
				fav2Index = b;
			}
		}
		
		if(validFav == true && validFav2 == true){
			tempSpeedDial = myPhoneBook.getFavoriteArrayList().get(favIndex).getSpeedDial();
			myPhoneBook.getFavoriteArrayList().get(favIndex).setSpeedDial(myPhoneBook.getFavoriteArrayList().get(fav2Index).getSpeedDial());
			myPhoneBook.getFavoriteArrayList().get(fav2Index).setSpeedDial(tempSpeedDial);
			System.out.println("Favorites were switched.");
			again2 = false;
		}
		else {
			System.out.println("Those two speed dials are not valid switches.");
			System.out.println("Would you like to reenter the two speed dial numbers? (add/done)");
			userInput = scan.nextLine();
			if(userInput.equals("add")){
				again2 = true;
			}
			else if(userInput.equals("done")){
				again = false;
			}
		}
		}
		System.out.println("Do you want to switch two more favorites? (add/done)");
		userInput = scan.nextLine();
		if(userInput.equals("done")){
			again = false;
		}
		else if(userInput.equals("add")){
			again2 = true;
		}
		
	}
		
	}

	public static void displayFavorite(PhoneBook phoneBook) {
		boolean validFavorite = false;
		int speedDial = 0;
		boolean again = true;
		boolean again2 = true;
		String userInput = "";
		
	while(again == true){
		while(again2 == true){
		System.out.println("Which favorites information do you want displayed");
		speedDial = Integer.parseInt(scan.nextLine());
		for (int i = 0; i < phoneBook.getFavoriteSize(); i++) {
			if (speedDial == phoneBook.getFavoriteArrayList().get(i).getSpeedDial()) {
				phoneBook.getFavoriteArrayList().get(i).getContactFrame().displayContactImage(phoneBook.getFavoriteArrayList().get(i));
				validFavorite = true;
				again2 = false;
			}
		}
		if (validFavorite == false){
			System.out.println("You did not enter a valid speed dial to display a favorite.");
			System.out.println("Do you want to reenter the speed dial to display a favorite? (add/done)");
			userInput = scan.nextLine();
			if (userInput.equals("add")){
				again2 = true;
			}
			else if(userInput.equals("done")){
				again = false;
			}
		}
		
		}
		System.out.println("Do you want to display another favorite? (add/done)");
		userInput = scan.nextLine();
		if(userInput.equals("done")){
			again = false;
		}
		else if(userInput.equals("add")){
			again2 = true;
		}
		
	}
	}

	public static void addContact(PhoneBook myPhoneBook) {
		boolean again = true;
		String userInput = "";
		
		while(again == true){
			String unformatNumber;
			String formatNumber;
			Contact userContact = new Contact();
			System.out.println("Please enter a contact name");
			userContact.setName(scan.nextLine());
			System.out.println("Please enter the contact cellphone number");
			unformatNumber = scan.nextLine();
			//userContact.setNumber();
			while (unformatNumber.length() != 10 && unformatNumber.length() != 7) {
				System.out.println("Phone number is invalid, please enter a valid phone number.");
				unformatNumber = scan.nextLine();
			}
			formatNumber = myPhoneBook.formatNumber(unformatNumber);
			userContact.setNumber(formatNumber);
			System.out.println("Please enter any notes");
			userContact.setNotes(scan.nextLine());
			myPhoneBook.addContact(userContact);
			myPhoneBook.contactsToString();    //Checking arraylist
			System.out.println("Do you want to add another contact? (add/done)");
			userInput = scan.nextLine();
			if(userInput.equals("add")){
				again = true;
			}
			else if(userInput.equals("done")){
				again = false;
			}
			
		}
		
	}

	public static void editContact(PhoneBook myPhoneBook){
		String userInput = "";
		String number = "";
		String formatNumber = "";
		boolean validContact = false;
		int contactIndex = 0;
		boolean again = true;
		boolean again2 = true;
		
	while(again == true){
		while(again2 == true){
			validContact = false;
		System.out.println("Enter the name of the contact you want to edit.");
		userInput = scan.nextLine();
		for (int a = 0; a < myPhoneBook.getContactSize(); a++){
			if(userInput.equals(myPhoneBook.getContactArrayList().get(a).getName())){
				validContact = true;
				contactIndex = a;
			}
		}
		if (validContact == true){
			while((!userInput.equals("1") || !userInput.equals("2") || !userInput.equals("3")) && again2 == true){
			System.out.println("1.Edit contact's name.\n2.Edit contact's number.\n3.Edit contact's notes.");
			userInput = scan.nextLine();
			if(userInput.equals("1")){
				System.out.println("What is the new name you would like to give the contact?");
				myPhoneBook.getContactArrayList().get(contactIndex).setName(scan.nextLine());
				again2 = false;
			}
			else if(userInput.equals("2")){
				System.out.println("What is the new number you would like to give the contact?");
				number = scan.nextLine();
				while (number.length() != 10 && number.length() != 7) {
					System.out.println("Phone number is invalid, please enter a valid phone number.");
					number = scan.nextLine();
					again2 = false;
				}
				formatNumber = myPhoneBook.formatNumber(number);
				myPhoneBook.getContactArrayList().get(contactIndex).setNumber(formatNumber);
			}
			else if(userInput.equals("3")){
				System.out.println("What is the new note you would like to give to the contact?");
				myPhoneBook.getContactArrayList().get(contactIndex).setNotes(scan.nextLine());
				again2 = false;
			}
			else{
				System.out.println("You did not enter a valid command. Retype the option you would like.");
			}
		}	
	}
		else if(validContact == false){
			System.out.println("You did not enter a valid contact name.");
			System.out.println("Would you like to reenter the contact name? (add/done)");
			userInput = scan.nextLine();
			if(userInput.equals("add")){
				again2 = true;
			}
			else if (userInput.equals("done")){
				again = false;
			}
		}
		}
		System.out.println("Do you want to edit another contact? (add/done)");
		userInput = scan.nextLine();
		if(userInput.equals("add")){
			again2 = true;
		}
		else if(userInput.equals("done")){
			again = false;
		}
	}
	}
	
	public static void removeContact(PhoneBook myPhoneBook) {
		String userInput = "";
		boolean validContact = false;
		boolean again = true;
		boolean again2 = true;
	
		while(again == true){
			while(again2 == true){
				System.out.println("Name the contact you want to remove?");
				userInput = scan.nextLine();
				for(int a = 0; a < myPhoneBook.getContactSize(); a++){
					if(userInput.equals(myPhoneBook.getContactArrayList().get(a).getName())){
						System.out.println("You have removed " + myPhoneBook.getContactArrayList().get(a).getName() + " off of the contacts list." );
						myPhoneBook.removeContact(myPhoneBook.getContactArrayList().get(a));
						validContact = true;
						again2 = false;
					}
				}
				if(validContact == false){
					System.out.println("You did not name a valid contact name. Would you like to reenter the name? (add/done)");
					userInput = scan.nextLine();
					if(userInput.equals("done")){
						again2 = false;
						}
					else if(userInput.equals("add")){
						again2 = true;
					}
				}
			}
			System.out.println("Do you want to remove another contact? (add/done)");
			userInput = scan.nextLine();
			if(userInput.equals("done")){
				again = false;
			}
			else if(userInput.equals("add")){
				again = true;
			}
			
			}
		
	}

	public static void displayPhoneBook(PhoneBook myPhoneBook, CallHistory myCallHistory) {
		String userInput = "";
		String name = "";
		boolean nameValid = false;
		boolean again = true;
		boolean again2 = true;
		
		
		myPhoneBook.compareFavorites();
		myPhoneBook.compareContacts();
		System.out.println("Your Phone book.");
		System.out.println("-------------------------------------");
		myPhoneBook.favoritesToString();
		myPhoneBook.contactsToString();
		System.out.println("Would you like to call one of your contacts? (y/n)");
		userInput = scan.nextLine();
		if(userInput.equals("y")){
		while(again == true){
			while(again2 == true){
				nameValid = false;
			PhoneCall recordedPhoneCall = new PhoneCall();
			System.out.println("What is name of the person you would like to call?");
			name = scan.nextLine();
			for(int a = 0; a < myPhoneBook.getContactSize(); a++){
				if (name.equals(myPhoneBook.getContactArrayList().get(a).getName())){
					System.out.println("You called " + myPhoneBook.getContactArrayList().get(a).getName());
					recordedPhoneCall = new PhoneCall(myPhoneBook.getContactArrayList().get(a), recordedPhoneCall.getTimestamp(), true);
					myCallHistory.addCall(recordedPhoneCall);
					nameValid = true;
					again2 = false;
				}
			}
			for (int b = 0; b < myPhoneBook.getFavoriteSize(); b++){
				if (name.equals(myPhoneBook.getFavoriteArrayList().get(b).getName())){
					System.out.println("You called " + myPhoneBook.getFavoriteArrayList().get(b).getName());
					recordedPhoneCall = new PhoneCall(myPhoneBook.getFavoriteArrayList().get(b), recordedPhoneCall.getTimestamp(), true);
					myCallHistory.addCall(recordedPhoneCall);
					nameValid = true;
					again2 = false;
				}
			}
			if (nameValid == false){
				System.out.println("You didn't enter a correct name in your myPhoneBook.");
				System.out.println("Would you like to reenter the name or return to menu?(add/done)");
				userInput = scan.nextLine();
				if (userInput.equals("done")) {
					again2 = false;
				}
			}
			}
			System.out.println("Would you like to call another contact by name? (add/done)");
			userInput = scan.nextLine();
			if (userInput.equals("add")) {
				again2 = true;
			}
			else if (userInput.equals("done")) {
				again = false;
			}
		}
		}
		
	}
}




