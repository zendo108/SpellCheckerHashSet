/**
 * 
 */
package com.inc108;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JFileChooser;

/**
 * @author IvanAranda
 *Recall that if set is a Set, then the following methods are defined:

	set.size() -- Returns the number of items in the set.
	set.add(item) -- Adds the item to the set, if it is not already there.
	set.contains(item) -- Check whether the set contains the item.
	set.isEmpty() -- Check whether the set is empty.
	You will also need to be able to traverse a set, using either an iterator or a for-each loop.
	https://www.callicoder.com/java-hashset/
 */
public class SpellCheckerHashSet {

	static Set<String> dictionary = new HashSet<String>();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner filein = null;
		Set<String> userFileWords = new HashSet<String>();
		Scanner userFilein;
		try {
			filein = new Scanner(new File("words.txt"));
			while (filein.hasNext()) {
			      String tk = filein.next();
//			      System.out.println(tk);
			      process(dictionary, tk); // do something with the token
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			filein.close();
		}
		
		
		try {
			File tempFile = getInputFileNameFromUser();
			userFilein = new Scanner(tempFile).useDelimiter("[^a-zA-Z]+");
			while (userFilein.hasNext()) {
			      String tk = userFilein.next();
			      process(userFileWords, tk); // do something with the token
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			filein.close();
		}
		
		for(String item: userFileWords) {
			//regular for-each
			System.out.println(item);
		}
		
		//dictionary iterator
//		Iterator<String> dictionaryIterator = dictionary.iterator();
//		while(dictionaryIterator.hasNext()) {
//			//regular for-each
//			String word = dictionaryIterator.next();
//			System.out.println(word);
//		}

	}

	public static void process(Set<String> set, String str) {
		str.toLowerCase();
		set.add(str);//add to dictionary
	}
	
	/**
     * Lets the user select an input file using a standard file
     * selection dialog box.  If the user cancels the dialog
     * without selecting a file, the return value is null.
     */
    static File getInputFileNameFromUser() {
       JFileChooser fileDialog = new JFileChooser();
       fileDialog.setDialogTitle("Select File for Input");
       int option = fileDialog.showOpenDialog(null);
       if (option != JFileChooser.APPROVE_OPTION)
          return null;
       else
          return fileDialog.getSelectedFile();
    }
}
