/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gauniversitytimetabling;

/**
 *
 * @author pjderrick
 */

import java.util.ArrayList;
import static gauniversitytimetabling.Constants.*;

public class Lecturer {
    
    String sName;
    ArrayList<String> alsSkills = new ArrayList<String>();
    
    //Timeslot matrix preferences to go here
    int[][] a2diTimeslotMatrix = new int[iMaxDays][iDayLength];
    
    LecturerTT[][] timetable = new LecturerTT[iMaxDays][iDayLength];
    
    Lecturer(String name, ArrayList<String> skills ) {

        sName = name;
        alsSkills = (ArrayList<String>)skills.clone();
        
        preGenerateTimeslotMatrix();
        initialiseTimetableArray();
    }
    
    Lecturer( String name ) {
        
        sName = name;
        preGenerateTimeslotMatrix();
        initialiseTimetableArray();
    }
    
    //=========================================================================
    //Initialise the timetables array
    
    private void initialiseTimetableArray() {

        for (int nDay = 0; nDay < iMaxDays; nDay++) {
            
            for (int nTime = 0; nTime < iDayLength; nTime++) {

                timetable[nDay][nTime] = new LecturerTT();
            }
        }
    }
    
    //==========================================================================
    //Pre-generate timeslot matrix
    
    private void preGenerateTimeslotMatrix(){
        
        for (int nDay = 0; nDay < iMaxDays; nDay++) {
            
            for (int nLength = 0; nLength < iDayLength; nLength++) {
                
                a2diTimeslotMatrix[nDay][nLength] = 10;
                
                if (nLength == 0) { a2diTimeslotMatrix[nDay][nLength] = 0; }
                if (nLength == iDayLength - 1) { a2diTimeslotMatrix[nDay][nLength] = 5; }
                if ((nDay == 2) && (nLength >= 4)) { a2diTimeslotMatrix[nDay][nLength] = -10; }
            }
        }
    }
    
}
