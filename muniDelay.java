/*Edwin Mak
A simple application in an attempt to create an interactive 
interface for the my previous job as a public information officer
at my local transportation agency
*/

import java.util.*;
import java.lang.*;
import java.io.*;
import java.util.Scanner;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

public class muniDelay
{
    private static Scanner scan;
    
	public static void main (String[] args) throws java.lang.Exception
	{
		
		scan = new Scanner(System.in);
		boolean on = true;
		List<Delay> openQ = new ArrayList <Delay>();
		while(on){
    		System.out.println("(A) ATTN or (U)UPDATE? or (1)Exit");
    		String msgType = scan.nextLine();
    		if(msgType.toUpperCase().equals("U")){
    			msgType = "UPDATE:";
    			updateMessage(openQ, msgType);
    		}else if(msgType.toUpperCase().equals("A")){
    			msgType = "ATTN:";
    			attnMessage(openQ, msgType);
    		}else if(msgType.equals("1")){
    		    break;
    		}else{
    			System.out.println("Please enter A or U or 1");
    		}
		}
	}
	
	private static String getLine(){
		String line = scan.nextLine();
		switch (line.toUpperCase()){
			case "F": line = "#FMarket";
				break;
			case "J": line = "#JChurch";
				break;
			case "N": line = "#NJudah";
				break;
			case "L": line = "#LTaraval";
				break;
			case "M": line = "#MOceanview";
				break;
			case "K": line = "#KIngleside";
			    break;
			case "T": line = "#TThird";
			    break;
			case "S": line = "#subwaysvc";
		        break;
		    default: throw new IllegalArgumentException("Please enter valid line");
		}
		return line;
	}
	
	private static String getLocation(){
		String location = scan.nextLine();
		return location;
	}
	
	private static String getReason(){
		String reason = scan.nextLine();
		return reason;
	}
	
	private static void attnMessage(List<Delay> openQ, String msgType){
	    System.out.println("Which line is the delay on?");
		String line = getLine();
		System.out.println("Which direction? (IB)(OB)(Hit enter if Both)");
		String direction = scan.nextLine();
		System.out.println("Where is the delay?");
		String location = "";
		if(line.equals("#subwaysvc")){
		    location = subwayLocation();
		}else{
		    location = scan.nextLine();
		}
		System.out.println("What is the reason for the delay?");
		String reason = scan.nextLine();
		String message = msgType + " " + direction.toUpperCase() + " " + line + " delay at " + location + " due to " + reason + "." 
		    + " Working to clear. Update shortly.";
		System.out.println(message);
		//
		Calendar rightNow = Calendar.getInstance();
		//add delay to queue of delays
		Delay d = new Delay(direction, line, location, reason, rightNow.getTime());
		openQ.add(d);
	}
	
	private static void updateMessage(List<Delay> openQ, String msgType){
	    System.out.println("Which delay do you want to update? Input number");
	    int number = 1;
	    for(Delay d : openQ){
	        System.out.println(number + ": " + d.toString());
	        number++;
	    }
	    int choice = scan.nextInt();
	    Delay updateDelay = openQ.remove(choice - 1);
	    System.out.println(msgType + " " + updateDelay.getDirection().toUpperCase() + " " + updateDelay.getLine() + " delay at " + 
	        updateDelay.getLocation() + " is clearing. Resuming regular svc.");
	}
	
	private static String subwayLocation(){
	    System.out.println(" 1-West Portal \n 2-Forest Hill \n 3-Castro \n 4-Church \n 5-Van Ness \n 6-Civic Center \n 7-Powell \n 8-Montgomery \n 9-Embarcadero");
	    String i = scan.nextLine();
	    String loc = "";
		switch (i){
			case "1": loc = "West Portal";
				break;
			case "2": loc = "Forest Hill";
				break;
			case "3": loc = "Castro";
				break;
			case "4": loc = "Church";
				break;
			case "5": loc = "Van Ness";
				break;
			case "6": loc = "Civic Center";
			    break;
			case "7": loc = "Powell";
			    break;
			case "8": loc = "Montgomery";
		        break;
		    case "9": loc = "Embarcadero";
		        break;
		    default: throw new IllegalArgumentException("Please enter valid loc");
		}
		return loc + " station";
	}
	
	public static class Delay{
        
        private String direction;
        private String line;
        private String location;
        private String reason;
        private Date date;
    
        public Delay(String direction, String line, String location, String reason, Date date){
            this.date = date;
            this.direction = direction;
            this.line = line;
            this.location = location;
            this.reason = reason;
        }
        
        public String getDirection(){
            return direction;
        }
        
        public String getLine(){
            return line;
        }
        
        public String getLocation(){
            return location;
        }
        
        public String getReason(){
            return reason;
        }
        
        public String toString(){
            String s = direction + " " + line + " " + location;
            return s;
        }
    }
	
}
