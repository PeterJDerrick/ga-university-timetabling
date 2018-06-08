/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gauniversitytimetabling;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static gauniversitytimetabling.Constants.*;

/**
 *
 * @author pjderrick
 */
public class Database {
    
    ArrayList<String> alsLecturerSkills = new ArrayList<String>();
    ArrayList<Lecturer> alLecturers = new ArrayList<Lecturer>();
    
    ArrayList<String> alsRoomResources = new ArrayList<String>();
    ArrayList<Room> alRooms = new ArrayList<Room>();
    
    ArrayList<Module> alModules = new ArrayList<Module>();
    
    //--------------------------------------------------------------------------
    
    boolean[][] a2dbLCSuitability = new boolean[iMaxLecturers][iMaxClasses];
    
    //This array is replaced with a2diPrefForRoom in GA
    //boolean[][] a2dbRCSuitability = new boolean[iMaxRooms][iMaxClasses];
    
    //int[][] a2diCourseModuleMatrix = new int[iNumCourses][iNumModules];

    int[][] a2diStudentTimeslotMatrix = new int[iMaxDays][iDayLength];
    
    //--------------------------------------------------------------------------
    
    String sResourceDir;
    
    public Database() {
        
        sResourceDir = System.getProperty("user.dir") + "\\resources\\";
        
        //Load lecturers from file
        try {
            loadLecturers();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Load lecturer skills from file
        try {
            loadLecturerSkills();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        //Load rooms from file
        try {
            loadRooms();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Load room resources from file
        try {
            loadRoomResources();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Load modules from file
        try {
            loadModules();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //-------------------------------------------------------------------
        
        preGenerateTimeslotMatrix();
        
        //-------------------------------------------------------------------
        //Generate random student sizes for Course-Module matrix
        
        //for (int nCourse = 0; nCourse < iNumCourses; nCourse++) {
            
            //for (int nModule = 0; nModule < iNumModules; nModule++) {
                
                //this.a2diCourseModuleMatrix[nCourse][nModule] = (int)(Math.random() * 200.0);   
            //}
        //}
    }
    
    //=========================================================================
    //Preload the student timeslot matrix
    
    private void preGenerateTimeslotMatrix(){
        
        for (int nDay = 0; nDay < iMaxDays; nDay++) {
            
            for (int nLength = 0; nLength < iDayLength; nLength++) {
                
                a2diStudentTimeslotMatrix[nDay][nLength] = 10;
                
                if (nLength == 0) { a2diStudentTimeslotMatrix[nDay][nLength] = 0; }
                if (nLength == iDayLength - 1) { a2diStudentTimeslotMatrix[nDay][nLength] = 5; }
                if ((nDay == 2) && (nLength >= 5)) { a2diStudentTimeslotMatrix[nDay][nLength] = -10; }
            }
        }
    }
    
    //#######################################################################
    //Load lecturers from a file
    
    ArrayList<String> lecSkills = new ArrayList<String>();
    
    private void loadLecturers() throws FileNotFoundException {
        
        String sFilename = sResourceDir + "Lecturers.csv";
        
        File flLecturers = new File(sFilename);
        Scanner scnInput = new Scanner(flLecturers);
        
        while (scnInput.hasNextLine()) {

            String sLine = scnInput.nextLine();
            String[] sChunks = sLine.split(",");
            
            //Create a new lecturer
            
            //Get lecturer name
            String sFirstName = sChunks[0];
            String sLastName = sChunks[1];
            String sName = sFirstName + " " + sLastName;
            
            System.out.println(sName);
            
            lecSkills.clear();
            for (int i = 2; i < sChunks.length; i++) {
                
                if (sChunks[i].equals("Timematrix")) { break; }
                
                lecSkills.add(sChunks[i]);
            }
            
            Lecturer lecturer = new Lecturer( sName, lecSkills );
            alLecturers.add(lecturer);
        }
        
        scnInput.close();
    }
    
    //#######################################################################
    //Load lecturer skills from a file
    
    private void loadLecturerSkills() throws FileNotFoundException {
        
        String sFilename = sResourceDir + "Lecturer Skills.csv";
        
        File flLecturerSkills = new File(sFilename);
        Scanner scnInput = new Scanner(flLecturerSkills);
        
        while (scnInput.hasNextLine()) {

            String sLine = scnInput.nextLine();
            alsLecturerSkills.add(sLine);
        }
        
        scnInput.close();
    }
    
    //#######################################################################
    //Load lecturer skills from a file
    
    ArrayList<String> resources = new ArrayList<String>();
    
    private void loadRooms() throws FileNotFoundException {
        
        String sFilename = sResourceDir + "Rooms.csv";
        
        File flRooms = new File(sFilename);
        Scanner scnInput = new Scanner(flRooms);
        
        String sID = "";
        int iCapacity = 0;
        boolean bNewRoom = true;
        
        while (scnInput.hasNextLine()) {

            String sLine = scnInput.nextLine();
            String[] sChunks = sLine.split(",");
            
            //Create a new room
            
            if (!sID.equals(sChunks[0])) {
                
                //If this is not the first room to be loaded in...
                if (bNewRoom == false) {
                    
                    Room room = new Room( sID, iCapacity, resources );
                    alRooms.add(room);
                }
                
                sID = sChunks[0];
                bNewRoom = true; 
            }
            
            
            if (iCapacity != Integer.parseInt(sChunks[1])) { iCapacity = Integer.parseInt(sChunks[1]); }
            
            System.out.println(sID);
            System.out.println(iCapacity);
            
            if (bNewRoom == true) { resources.clear(); bNewRoom = false; }
            
            resources.add(sChunks[2]);
        }
        
        //Add the final room
        Room room = new Room( sID, iCapacity, resources );
        alRooms.add(room);
        
        //Close the rooms file
        scnInput.close();
    }
    
    //#######################################################################
    //Load room resources from a file
    
    private void loadRoomResources() throws FileNotFoundException {
        
        String sFilename = sResourceDir + "Room resources.csv";
        
        File flRoomResources = new File(sFilename);
        Scanner scnInput = new Scanner(flRoomResources);
        
        while (scnInput.hasNextLine()) {

            String sLine = scnInput.nextLine();
            alsRoomResources.add(sLine);
        }
        
        scnInput.close();
    }
    
    //#######################################################################
    //Load modules from a file
    
    ArrayList<String> modules = new ArrayList<String>();
    
    ArrayList<String> lectureReqSkills = new ArrayList<String>();
    ArrayList<String> lectureReqResources = new ArrayList<String>();
    ArrayList<String> practicalReqSkills = new ArrayList<String>();
    ArrayList<String> practicalReqResources = new ArrayList<String>();
    ArrayList<String> tutorialReqSkills = new ArrayList<String>();
    ArrayList<String> tutorialReqResources = new ArrayList<String>();
    
    private void loadModules() throws FileNotFoundException {
        
        System.out.println("LOADING MODULES#################################");
        
        String sFilename = sResourceDir + "Modules final v2.csv";
        //String sFilename = sResourceDir + "Modules final.csv";
        
        String[] asChunks;
        
        //----------------------------------------
        //Determine how many modules there are in the file
        
        File flModules = new File(sFilename);
        Scanner scnInput = new Scanner(flModules);
        
        int iNumModules = 0;
        
        //Read in headings
        String sHeadings = scnInput.nextLine();
        
        String sModuleName;
        
        while (scnInput.hasNextLine()) {
            
            //Read in module name
            sModuleName = scnInput.nextLine();
            
            //Exit the loop if all the sizes, booleans and durations have been loaded
            if (sModuleName.equals("Module Lecture Skills")) {
                
                break;
            } else {
                
                iNumModules++;
            }            
        }
        
        scnInput.close();
        
        //----------------------------------------

        //----------------------------------------
        
        scnInput = new Scanner(flModules);
        
        //Read in headings
        sHeadings = scnInput.nextLine();
      
        while (scnInput.hasNextLine()) {

            //Read in module name, size, LPT booleans and durations
            asChunks = scnInput.nextLine().split(",");
            
            sModuleName = asChunks[0];
            System.out.println(sModuleName);
            
            Module module = new Module( sModuleName );
            
            //Exit the loop if all the sizes, booleans and durations have been loaded
            if (sModuleName.equals("Module Lecture Skills")) {
                break;
            }
            
            //Get module cohort size
            module.iSize = Integer.parseInt(asChunks[1]);
            
            //Get "HasLectures" boolean value
            if (asChunks[2].equals("y")) {
                module.bHasLectures = true;
            } else {
                module.bHasLectures = false;
            }
            
            //Get "HasPracticals" boolean value
            if (asChunks[3].equals("y")) {
                module.bHasPracticals = true;
            } else {
                module.bHasPracticals = false;
            }
            
            //Get "HasTutorials" boolean value
            if (asChunks[4].equals("y")) {
                module.bHasTutorials = true;
            } else {
                module.bHasTutorials = false;
            }
            
            //Get the lecture duration
            module.iLectureDuration = Integer.parseInt(asChunks[5]);
             
            //Get the practical duration
            module.iPracticalDuration = Integer.parseInt(asChunks[6]);
           
            //Get the tutorial duration
            module.iTutorialDuration = Integer.parseInt(asChunks[7]);
            
            //Add the module to the alModules ArrayList
            alModules.add(module);
        }
        
        //---------------------------------------------------------------------
        //Load in lecture skills for all modules
        
        int nModule = 0;
        
        while (scnInput.hasNextLine()) {

            //Read in module name and skills
            asChunks = scnInput.nextLine().split(",");
            
            //Exit the loop if all the lecture skills for all modules have been loaded
            if (asChunks[0].equals("Module Practical Skills")) {
                break;
            }
            
            if (asChunks.length > 1) {
            
                lectureReqSkills.clear();
                for (int i = 1; i < asChunks.length; i++) { lectureReqSkills.add(asChunks[i]); }
            }
            
            alModules.get(nModule).alsLectureSkills = (ArrayList<String>)lectureReqSkills.clone();
            
            nModule++;
        }
        
        //---------------------------------------------------------------------
        //Load in practical skills for all modules
        
        nModule = 0;
        
        while (scnInput.hasNextLine()) {

            //Read in module name and skills
            asChunks = scnInput.nextLine().split(",");
            
            //Exit the loop if all the practical skills for all modules have been loaded
            if (asChunks[0].equals("Module Tutorial Skills")) {
                break;
            }
            
            if (asChunks.length > 1) {
            
                practicalReqSkills.clear();
                for (int i = 1; i < asChunks.length; i++) { practicalReqSkills.add(asChunks[i]); }
            }
            
            alModules.get(nModule).alsPracticalSkills = (ArrayList<String>)practicalReqSkills.clone();
            
            nModule++;
        }

        //---------------------------------------------------------------------
        //Load in tutorial skills for all modules
        
        nModule = 0;
        
        while (scnInput.hasNextLine()) {

            //Read in module name and skills
            asChunks = scnInput.nextLine().split(",");
            
            //Exit the loop if all the tutorial skills for all modules have been loaded
            if (asChunks[0].equals("Module Lecture Resources")) {
                break;
            }
            
            if (asChunks.length > 1) {
            
                tutorialReqSkills.clear();
                for (int i = 1; i < asChunks.length; i++) { tutorialReqSkills.add(asChunks[i]); }
            }
            
            alModules.get(nModule).alsTutorialSkills = (ArrayList<String>)tutorialReqSkills.clone();
            
            nModule++;
        }
        
        //---------------------------------------------------------------------
        //Load in lecture resources for all modules
        
        nModule = 0;
        
        while (scnInput.hasNextLine()) {

            //Read in module name and skills
            asChunks = scnInput.nextLine().split(",");
            
            //Exit the loop if all the lecture resources for all modules have been loaded
            if (asChunks[0].equals("Module Practical Resources")) {
                break;
            }
            
            if (asChunks.length > 1) {
            
                lectureReqResources.clear();
                for (int i = 1; i < asChunks.length; i++) { lectureReqResources.add(asChunks[i]); }
            }
            
            alModules.get(nModule).alsLectureResources = (ArrayList<String>)lectureReqResources.clone();
            
            nModule++;
        }
            
        //---------------------------------------------------------------------
        //Load in practical resources for all modules
        
        nModule = 0;
        
        while (scnInput.hasNextLine()) {

            //Read in module name and skills
            asChunks = scnInput.nextLine().split(",");
            
            //Exit the loop if all the practical resources for all modules have been loaded
            if (asChunks[0].equals("Module Tutorial Resources")) {
                break;
            }
            
            if (asChunks.length > 1) {
            
                practicalReqResources.clear();
                for (int i = 1; i < asChunks.length; i++) { practicalReqResources.add(asChunks[i]); }
            }
            
            alModules.get(nModule).alsPracticalResources = (ArrayList<String>)practicalReqResources.clone();
            
            nModule++;
        }
            
        //---------------------------------------------------------------------
        //Load in tutorial resources for all modules
        
        nModule = 0;
        
        while (scnInput.hasNextLine()) {

            //Read in module name and skills
            asChunks = scnInput.nextLine().split(",");
            
            if (asChunks.length > 1) {
            
                tutorialReqResources.clear();
                for (int i = 1; i < asChunks.length; i++) { tutorialReqResources.add(asChunks[i]); }
            }
            
            alModules.get(nModule).alsTutorialResources = (ArrayList<String>)tutorialReqResources.clone();
            
            nModule++;
        }
        
        scnInput.close();
    }
}
