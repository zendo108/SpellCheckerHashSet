package com.inc108;

import java.io.*; 
import java.util.Set;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;
import javax.swing.JFileChooser;

class Main {
  static Set<String> dictionary = new HashSet<String>(); 
  static Set<String> wordFound = new LinkedHashSet<String>();
  public static void main(String[] args) {
    Scanner filein = null;
    Set<String> userFileWords = new HashSet<String>();
    Scanner userFilein;
    try {
        /**
         * get the words into dictionary
         */
            filein = new Scanner(new File("./words.txt"));
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
        /**
         * get the user file words into a set to analyze
         */
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

    for(String word: userFileWords) {
        //accessing each unique word of user's file
        processWord(word);//call to process word

    }

  }
  
  static void processWord(String str){
      /**
         * process the word selected
         */
      if(!dictionary.contains(str)){
          //if word is not found in dictionary
          //process
            addChar(str);
            replaceChar(str);
            removeChar(str);
            
            if(str.length()>1){
                swapFirstChar(str);
                swapLastChar(str);
            }
            
            insertSpace(str);
            System.out.println(str + ": " + wordFound);
            wordFound.clear();
      }
      
  }
  
  static void addChar(String str){
      /**
        * add a character from beginning to end
        */
    StringBuilder strBuilder = new StringBuilder(str);
    for(int i = 0; i <= str.length();i++){
      for(char ch = 'a'; ch <= 'z'; ch++){
            strBuilder.insert(i,ch);
//            System.out.println(strBuilder.toString());
            findWord(strBuilder.toString());
            strBuilder.deleteCharAt(i);
          }
    }
  }
  
  static void replaceChar(String str){
      /**
        * replace each word's char with character from beginning to end
        */
    StringBuilder strBuilder = new StringBuilder(str);
    for(int i = 0; i < str.length();i++){
      char tempChar = strBuilder.charAt(i);
      for(char ch = 'a'; ch <= 'z'; ch++){
            strBuilder.setCharAt(i,ch);
//            System.out.println(strBuilder.toString());
            findWord(strBuilder.toString());
          }
      strBuilder.setCharAt(i, tempChar);
    }
    
  }
  
  static void removeChar(String str){
      /**
        * remove each word's char with character from beginning to end at a time
        */
    StringBuilder strBuilder = new StringBuilder(str);
    
    for(int i = 0; i < str.length();i++){
        char tempChar = strBuilder.charAt(i);
        strBuilder.deleteCharAt(i);
//        System.out.println(strBuilder.toString());
        findWord(strBuilder.toString());
        strBuilder.insert(i,tempChar);
    }
    
  }
  
  static void swapFirstChar(String str){
      /**
        * swap first char with second
        */
      StringBuilder strBuilder = new StringBuilder(str);
      
      char charAtZero = str.charAt(0);
      char charAtOne = str.charAt(1);
      strBuilder.setCharAt(0,charAtOne);
      strBuilder.setCharAt(1,charAtZero);
      findWord(strBuilder.toString());
  }
  
  static void swapLastChar(String str){
      /**
        * swap last char with one before last
        */
      StringBuilder strBuilder = new StringBuilder(str);
      char charLast = strBuilder.charAt(str.length()-1);
      char charPrev = strBuilder.charAt(str.length()-2);
      strBuilder.setCharAt(str.length()-1,charPrev);
      strBuilder.setCharAt(str.length()-2,charLast);
      findWord(strBuilder.toString());
  }
  
  static void insertSpace(String str){
      /**
        * add a space from beginning to end
        * then check both words are in dictionary
        */
      StringBuilder strBuilder = new StringBuilder(str);
        for(int i = 0; i < str.length();i++){
            String firstString = str.substring(0, i);
            String secondString = str.substring(i, str.length());
            
//            System.out.println(firstString + " " + secondString);
            if(dictionary.contains(firstString)&&dictionary.contains(secondString)){
                findWord(firstString);
                findWord(secondString);
            }
            
            
        }
  }
  
  static boolean findWord(String str){
      /**
        *see if the word is in the dictionary
        */
      if(dictionary.contains(str)){
          //if the word is found insert into wordFound Set
          wordFound.add(str);
          return true;
      }else{
          return false;
      }
      
  }
  
  static void process(Set<String> set, String str) {
            /**
            * This is where external files are read into sets
            */
		set.add(str.toLowerCase());//add to dictionary
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