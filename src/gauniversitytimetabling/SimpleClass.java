/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gauniversitytimetabling;

import java.util.ArrayList;

/**
 *
 * @author pjderrick
 */
public class SimpleClass {
    
    ArrayList<String> alsSkills = new ArrayList<String>();
    ArrayList<String> alsResources = new ArrayList<String>();
    
    String sName;
    int iLecturerIndex;
    int iSize;
    int iDuration;
    
    boolean bIsLecture = false;
    boolean bIsPractical = false;
    boolean bIsTutorial = false;
    
    public SimpleClass(String name, int size ) {
        
        sName = name;
        iSize = size;
    }
}
