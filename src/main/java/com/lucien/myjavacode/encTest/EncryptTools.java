package com.lucien.myjavacode.encTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EncryptTools
{
  public static String setEncrypt(String str)
  {
    String sn = "mdclsoft";
    int[] snNum = new int[str.length()];
    String result = "";
    String temp = "";
    int i = 0;
    for (int j = 0; i < str.length(); j++)
    {
      if (j == sn.length()) {
        j = 0;
      }
      snNum[i] = (str.charAt(i) ^ sn.charAt(j));
      i++;
    }
    for (int k = 0; k < str.length(); k++)
    {
      temp = Integer.toHexString(snNum[k]);
      if (temp.length() == 1) {
        temp = "0" + temp;
      }
      result = result + temp;
    }
    return result.toUpperCase();
  }
  
  public static String getEncrypt(String str)
  {
    String sn = "mdclsoft";
    char[] snNum = new char[str.length() / 2];
    String result = "";
    
    int i = 0;
    for (int j = 0; i < str.length() / 2; j++)
    {
      if (j == sn.length()) {
        j = 0;
      }
      int n = Integer.parseInt(str.substring(i * 2, i * 2 + 2), 16);
      System.out.println((char)n);
      snNum[i] = ((char)((char)n ^ sn.charAt(j)));i++;
    }
    for (int k = 0; k < str.length() / 2; k++) {
      result = result + snNum[k];
    }
    return result;
  }
  
  public static void main(String[] args)
  {
    String ss = "linzhengmao";
    System.out.println(ss.charAt(3));
    ss = setEncrypt(ss);
    System.out.println(ss);
    System.out.println(getEncrypt(ss));
    System.out.println(('a'^'b')+"");
    System.out.println((char) ('1'^'b')+"");
  }

}