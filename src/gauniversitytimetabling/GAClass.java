/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gauniversitytimetabling;

import static gauniversitytimetabling.Constants.*;

/**
 *
 * @author pjderrick
 */
public class GAClass {
    
    int iNumLecturers;
    int[] aiLecturer = new int[iMaxLecturersPerClass];
    int iNumStudentGroups;
    int[] aiStudentGroup = new int[iMaxStudentGroupsPerClass];
    int iDuration; //hours

    public GAClass() {
        
    }
}
