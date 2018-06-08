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
public class Constants {
    
    public static final int iNumCourses = 10;
    public static final int iNumModules = 100;
    
    //originally 2 to allow for two lecturers to teach a practical together
    public static final int iMaxLecturersPerClass = 1;
    
    public static final int iMaxStudentGroupsPerClass = 20;
    
    public static final int iMaxClasses = 200; //For CS cluster only
    //public static final int iMaxClasses = 350; //For all CSCT
    
    public static final int iMaxDays = 5;
    public static final int iDayLength = 9;
    public static final int iDayLengthExtension = 4;
    public static final int iTotalTimeslots = iMaxDays * iDayLength;
    public static final int iMaxStudentGroups = 500;
    public static final int iMaxRooms = 100;
    public static final int iMaxLecturers = 100;
    
    public static final int iMaxGroupSize = 20;

    public static final int iWeightingStudentsInOneRoom = 1;
    public static final int iWeightingLecturerInOneRoom = 1;
    public static final int iWeightingClassInOneRoom = 1;
    public static final int iWeightingCapacityInOneRoom = 1;
    
    public static final int iPopulationSize = 25; //100
    public static final int iNumGenerations = 100; //100
            
    public static final double dProbCrossover = 1.0;
    public static final double dProbMutation = 0.4;
}
