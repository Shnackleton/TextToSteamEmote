import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class TextToSteamEmote
{
	//tracks all chars
	private static ArrayList<String> letterToEmoteStringArray = new ArrayList<String>();
	//tracks all steam emotes
	private static ArrayList<Character> letterToEmoteCharacterArray = new ArrayList<Character>();

	//reads txt file and adds to arrays
	private static void TextToArray() throws FileNotFoundException
	{
		//the file to read
		File emoteFile = new File("LetterToEmote.txt");
		//looks at the textfile
		Scanner fileReader = new Scanner(emoteFile);

		//if still stuff to read
		while (fileReader.hasNextLine())
		{
			//currLine in the text
			String[] letter = fileReader.nextLine().split(" ");

			//the first char
			letterToEmoteCharacterArray.add(letter[0].charAt(0));
			//the Emote of that char
			letterToEmoteStringArray.add(letter[1]);
		}
		//closes the scanner
		fileReader.close();
	}

	//changes the message into a steam Emote
	private static String MsgToEmote(String message)
	{

		String aString = "";//Translated Message

		//Loop through message
		for (int i = 0; i < message.length(); i += 1)
		{
			char currChar = message.charAt(i);
			
			if (currChar == ' ') //if currchar is space
			{
				aString += currChar +""+ currChar;
				continue;
			}
			
			//if currChar is alphabetic
			if (Character.isAlphabetic(currChar))
			{
				//if uppercase
				if (Character.isUpperCase(currChar))
				{
					//change to lowercase
					currChar = Character.toLowerCase(currChar);
				}
			}
			
			//loop through array
			for (int j = 0; j < letterToEmoteCharacterArray.size(); j += 1)
			{
				//if matches
				if (currChar == letterToEmoteCharacterArray.get(j))
				{
					//get the char version of it
					aString += letterToEmoteStringArray.get(j);
				}
			}
		}

		return aString;
	}
	
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner scnr = new Scanner(System.in);
		TextToSteamEmote.TextToArray();
		String msg = "";
		String emoteMsg = "";
		
		while (true)
		{
			if (msg.length() == 1 && msg.charAt(0) == 'q')
			{
				break;
			}
			System.out.print("Enter Message: ");
			msg = scnr.nextLine().toString();
			emoteMsg = TextToSteamEmote.MsgToEmote(msg);
			System.out.println(emoteMsg);
			System.out.println();
		}

		scnr.close();
	}
}