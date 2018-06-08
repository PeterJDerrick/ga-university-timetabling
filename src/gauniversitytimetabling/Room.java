/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gauniversitytimetabling;

import java.util.ArrayList;
import static gauniversitytimetabling.Constants.*;

/**
 *
 * @author pjderrick
 */
public class Room {
    
    String sID;
    int iCapacity;
    ArrayList<String> alsResources = new ArrayList<String>();
    
    RoomTT[][] timetable = new RoomTT[iMaxDays][iDayLength];
    
    Room( String id, int capacity, ArrayList<String> resources ) {
        
        sID = id;
        iCapacity = capacity;
        alsResources = (ArrayList<String>)resources.clone();
        
        initialiseTimetableArray();
    }
    
    Room( String id ) {
        
        sID = id;
        iCapacity = 0;
        
        initialiseTimetableArray();
    }
    
    //Initialise the timetables array
    private void initialiseTimetableArray() {

        for (int nDay = 0; nDay < iMaxDays; nDay++) {
            
            for (int nTime = 0; nTime < iDayLength; nTime++) {

                timetable[nDay][nTime] = new RoomTT();
            }
        }
    }
}
