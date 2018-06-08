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
public class Module {
    
    String sName;
    int iSize;
    int iMaxClassSize = 20; //need to remove/replace this
    
    boolean bHasLectures;
    boolean bHasPracticals;
    boolean bHasTutorials;
    
    int iLectureDuration = 1;
    int iPracticalDuration = 1;
    int iTutorialDuration = 1;
    
    ArrayList<String> alsLectureSkills = new ArrayList<String>();
    ArrayList<String> alsLectureResources = new ArrayList<String>();
    ArrayList<String> alsPracticalSkills = new ArrayList<String>();
    ArrayList<String> alsPracticalResources = new ArrayList<String>();
    ArrayList<String> alsTutorialSkills = new ArrayList<String>();
    ArrayList<String> alsTutorialResources = new ArrayList<String>();
    
    Module( String name, int size, boolean hasLectures, boolean hasPracticals, boolean hasTutorials ) {
        
        sName = name;
        iSize = size;
        
        bHasLectures = hasLectures;
        bHasPracticals = hasPracticals;
        bHasTutorials = hasTutorials;
    }
    
    Module( String name ) {
        
        sName = name;
        iSize = 0;
        iMaxClassSize = 0;
        
        bHasLectures = true;
        bHasPracticals = true;
        bHasTutorials = true;
    }
}
