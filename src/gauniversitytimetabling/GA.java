/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gauniversitytimetabling;

//import java.util.ArrayList;
import static gauniversitytimetabling.Constants.*;
//import preliminaryfyp.Database;

/**
 *
 * @author pjderrick
 */
public class GA {
    
    //--------------------------------------------------------------------------
    
    GAClass[] aClasses = new GAClass[iMaxClasses];
    
    //==========================================================================
    //Pre-processing matrices
    
    int[][] a2diPrefForRoom = new int[iMaxRooms][iMaxClasses]; //RC matrix
    int[][][] a3diPrefForLecturers = new int[iMaxLecturers][iMaxDays][iDayLength]; //Lecture timeslot matrices
    int[][][] a3diPrefForStudentGroups = new int[iMaxStudentGroups][iMaxDays][iDayLength]; //Student timeslot matrix
    
    //--------------------------------------------------------------------------
    
    int[] aiRoomSeatingCapacity = new int[iMaxRooms];
    int[] aiStudentGroupSize = new int[iMaxStudentGroups];
    
    //==========================================================================
    //3D arrays a,s,u holding the phenotype of the GA
    
    int[][][][] a = new int[iMaxClasses][iMaxRooms][iMaxDays][iDayLength + iDayLengthExtension];
    int[][][][] s = new int[iMaxLecturers][iMaxRooms][iMaxDays][iDayLength + iDayLengthExtension];
    int[][][][] u = new int[iMaxStudentGroups][iMaxRooms][iMaxDays][iDayLength + iDayLengthExtension];   
            
    //==========================================================================
    //Array holding the genotype (chromosome array)
    
    //is maxclasses/popsize the right way around?
    Gene[][] chromosomes = new Gene[iPopulationSize][iMaxClasses];
    int[] aiChromosomeFitnesses = new int[iPopulationSize];
    
    //==========================================================================
    //Arrays holding timetable data
    
    
    
    //--------------------------------------------------------------------------
    
    MersenneTwister mt = new MersenneTwister();
    int iCurrentGen;
    
    //##########################################################################
    
    public GA(Database database) {
        
        long lRndSeed = (long)(Math.random() * 10000.0);
        mt.setSeed(lRndSeed);
        
        //Initialise the aClasses array
        for (int nClass = 0; nClass < iMaxClasses; nClass++) {
            
            aClasses[nClass] = new GAClass();
        }
        
        //Initialise the chromosomes array
        for (int nIndividual = 0; nIndividual < iPopulationSize; nIndividual++) {
            
            for (int nClass = 0; nClass < iMaxClasses; nClass++) {

                chromosomes[nIndividual][nClass] = new Gene();
            }
        }
    }
    
    //==========================================================================
    //Intitialise the chromosomes with random values for rooms and timeslots
    
    public void initChromosomes(Database database) {
        
        for (int nIndividual = 0; nIndividual < iPopulationSize; nIndividual++) {
            
            for (int nClass = 0; nClass < aClasses.length; nClass++) {
               
                int iRndRoom = mt.nextInt(database.alRooms.size());
                int iRndTimeslot = mt.nextInt(iTotalTimeslots - 1);
                
                chromosomes[nIndividual][nClass].iRoom = iRndRoom;
                chromosomes[nIndividual][nClass].iTimeslot = iRndTimeslot;
            }
        }
    }
    
    //##########################################################################
    
    public void clearVariables() {
        
        int r;
	int t;
	int d;
	int c;
	int l;
	int g;

	for (r=0; r<iMaxRooms; r++)
	{
            for (d=0; d<iMaxDays; d++)
            {
                for (t=0; t<iDayLength; t++) //extend to iDayLength + 4h
                {
                    for (c=0; c<iMaxClasses; c++)
                    {
                            a[c][r][d][t] = 0;
                    }

                    for (l=0; l<iMaxLecturers; l++)
                    {
                            s[l][r][d][t] = 0;
                    }

                    for (g=0; g<iMaxStudentGroups; g++)
                    {
                            u[g][r][d][t] = 0;
                    }
                }
            }
	}
    }
    
    int get_day_from_timeslot(int timeslot) {
	return timeslot/9;   // 9 hours per day
    }

    int get_time_of_day_from_timeslot(int timeslot) {
	return timeslot%9;   // 9 hours per day
    }
    
    //--------------------------------------------------------------------------
    
    void load_chromosome_into_variables(int chromosome) {
        
	int r;
	int t;
	int c;
	int l;
	int g;
	int day;
	int time;
	int i;

	clearVariables();

        //int iNumClasses = aClasses.length;
        
	for (c=0; c<iMaxClasses; c++){
            
            r = chromosomes[chromosome][c].iRoom;
            t = chromosomes[chromosome][c].iTimeslot;
            day = get_day_from_timeslot(t);
            time = get_time_of_day_from_timeslot(t);

            for (t=0; t<aClasses[c].iDuration; t++)
            {
                a[c][r][day][time+t] = 1; //time+t > iDayLength (9h)

                if (aClasses[c].iNumLecturers > 0)
                {
                    for (i=0; i<aClasses[c].iNumLecturers; i++)
                    {
                        l = aClasses[c].aiLecturer[i];
                        s[l][r][day][time+t] = 1;
                    }
                }

                if (aClasses[c].iNumStudentGroups > 0)
                {
                    for (i=0; i<aClasses[c].iNumStudentGroups; i++)
                    {
                        g = aClasses[c].aiStudentGroup[i];
                        u[g][r][day][time+t] = 1;
                    }
                }
            }
	}
    }
    
    //--------------------------------------------------------------------------
    // Objective function

    private int objective_function() {
        
	int r;
	int d;
	int t;
	int c;
	int l;
	int g;
	int value;

	value = 0;

        int iNumClasses = aClasses.length;
        
	for (r=0; r<iMaxRooms; r++)
	{
            for (d=0; d<iMaxDays; d++)
            {
                for (t=0; t<iDayLength + iDayLengthExtension; t++) //extend day length by 4h -> -10 for classes after 6pm
                {
                    for (c=0; c<iNumClasses; c++)
                    {
                        value = value + a[c][r][d][t] * a2diPrefForRoom[r][c];
                    }

                    for (l=0; l<iMaxLecturers; l++)
                    {
                        if (t < iDayLength) {
                            
                            value = value + s[l][r][d][t] * a3diPrefForLecturers[l][d][t];
                        } else {
                            
                            //value = value + s[l][r][d][t] * -10;
                        }
                    }

                    for (g=0; g<iMaxStudentGroups; g++)
                    {
                        if (t < iDayLength ) {
                            
                            value = value + u[g][r][d][t] * a3diPrefForStudentGroups[g][d][t];
                        } else {
                            
                            //value = value + u[g][r][d][t] * -10;
                        }
                    }
                }
            }
	}

	return value;
    }

    //--------------------------------------------------------------------------
    
    // Constraints

    // Each group of students can only occupy one room at a time

    private int students_in_one_room() {
        
	int r;
	int d;
	int t;
	int g;
	int value;
	int broken;

	broken = 0;

	for (g=0; g<iMaxStudentGroups; g++)
	{
		for (d=0; d<iMaxDays; d++)
		{
			for (t=0; t<iDayLength; t++)
			{
				value = 0;

				for (r=0; r<iMaxRooms; r++)
				{
					value = value + u[g][r][d][t];
				}

				if (value > 1) broken++;
			}
		}
	}

	return broken;
}


// Each lecturer can only occupy one room at a time

    private int lecturer_in_one_room() {
        
	int r;
	int d;
	int t;
	int l;
	int value;
	int broken;

	broken = 0;

	for (l=0; l<iMaxLecturers; l++)
	{
		for (d=0; d<iMaxDays; d++)
		{
			for (t=0; t<iDayLength; t++)
			{
				value = 0;

				for (r=0; r<iMaxRooms; r++)
				{
					value = value + s[l][r][d][t];
				}

				if (value > 1) broken++;
			}
		}
	}

	return broken;
}


// Each class (lecture, practical, workshop, etc...) can only occupy one room at a time

private int class_in_one_room()
{
	int r;
	int d;
	int t;
	int c;
	int value;
	int broken;

	broken = 0;

        int iNumClasses = aClasses.length;
        
	for (c=0; c<iNumClasses; c++)
	{
		for (d=0; d<iMaxDays; d++)
		{
			for (t=0; t<iDayLength; t++)
			{
				value = 0;

				for (r=0; r<iMaxRooms; r++)
				{
					value = value + a[c][r][d][t];
				}

				if (value > 1) broken++;
			}
		}
	}

	return broken;
}


// Number of students must be leq to room capacity

private int room_capacity()
{
	int r;
	int d;
	int t;
	int g;
	int value;
	int broken;

	broken = 0;

	for (r=0; r<iMaxRooms; r++)
	{
		for (d=0; d<iMaxDays; d++)
		{
			for (t=0; t<iDayLength; t++)
			{
				value = 0;

				for (g=0; g<iMaxStudentGroups; g++)
				{
					value = value + u[g][r][d][t] * aiStudentGroupSize[g];
				}

				if (value > aiRoomSeatingCapacity[r]) broken++;
			}
		}
	}

	return broken;
}

//----------------------------------------------------------------------
// Fitness function (value of the objective function minus the values of the broken constraints)

    private int fitness() {
        
	int value;
        
        int iObjectiveFunction = objective_function();
        int iStudentsInOneRoom = students_in_one_room();
        int iLecturerInOneRoom = lecturer_in_one_room();
        int iClassInOneRoom = class_in_one_room();
        int iRoomCapacity = room_capacity();

	value = 0;
	value = value + iObjectiveFunction;
	value = value - iWeightingStudentsInOneRoom * iStudentsInOneRoom;
	value = value - iWeightingLecturerInOneRoom * iLecturerInOneRoom;
	value = value - iWeightingClassInOneRoom    * iClassInOneRoom;
	value = value - iWeightingCapacityInOneRoom * iRoomCapacity;

	return value;
    }
    
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //Calculate entire population once as new offspring will only need one calculation per generation
    
    public void calculateFitness() {
        
        for (int nChromosome = 0; nChromosome < iPopulationSize; nChromosome++) {
            
            System.out.println("Loading chromosome " + nChromosome + " into variables");
            
            load_chromosome_into_variables(nChromosome);
            
            System.out.println("Calculating chromosome " + nChromosome + " fitness");
            
            aiChromosomeFitnesses[nChromosome] = fitness();
        }
        
        //***debug***
        int y = 0;
    }
    
    //--------------------------------------------------------------------------
    
    int iParent1, iParent2;
    Gene[] indOffspring = new Gene[iMaxClasses];
    Gene[] indWorst = new Gene[iMaxClasses];
    int iOffspringFitness;
    
    public void calculateGeneration(int iNumRooms) {
        
        selection();
        crossover();
        mutation(iNumRooms);
            
        //Offspring's fitness is calculated in this method
        replacement();
    }
    
    public void selection() {
        
        this.iParent1 = mt.nextInt(iPopulationSize - 1);
        this.iParent2 = mt.nextInt(iPopulationSize - 1);
    }
    
    public void crossover() {
        
        int iNumGenes = aClasses.length;
        
        double dCrossover = mt.nextRndDouble();
        
        if (dCrossover <= dProbCrossover) {
            
            int iCrossoverPoint = mt.nextInt(iNumGenes - 1);

            for (int nClass = 0; nClass < iNumGenes; nClass++) {

                if (nClass <= iCrossoverPoint) {

                    this.indOffspring[nClass] = this.chromosomes[iParent1][nClass];
                } else {

                    this.indOffspring[nClass] = this.chromosomes[iParent2][nClass];
                } 
            }
        }
    }
    
    public void mutation(int iNumRooms) {
     
        double dMutate, dComponent;
        int iNumGenes = aClasses.length;
                
        for (int nClass = 0; nClass < iNumGenes; nClass++) {
            
            dMutate = mt.nextRndDouble();
            
            if (dMutate <= dProbMutation) {
                
                //Gene struct made up of two components
                dComponent = mt.nextRndDouble();
                
                if (dComponent < 0.5 ) {
                    
                    indOffspring[nClass].iRoom = mt.nextInt(iNumRooms - 1);
                } else {
                    
                    indOffspring[nClass].iTimeslot = mt.nextInt(iTotalTimeslots - 1);
                }
            }
        }
    }
    
    //Currently replaces a random individual - replace worst individual instead?
    public boolean replacement() {
        
        boolean bReplacementDone = false;
                
        //Replace a random individual in the population
        //int iIndivToReplace = mt.nextInt(iPopulationSize - 1);

        //Find worst individual
        int iWorstIndividual = -1;
        int iWorstFitness = 10000000;
        
        for (int nIndividual = 0; nIndividual < iPopulationSize; nIndividual++) {
            
            if (aiChromosomeFitnesses[nIndividual] < iWorstFitness) {
                
                iWorstFitness = aiChromosomeFitnesses[nIndividual];
                iWorstIndividual = nIndividual;
            }
        }
        
        //Store the worst individual
        for (int nClass = 0; nClass < iMaxClasses; nClass++) {
            
            indWorst[nClass] = chromosomes[iWorstIndividual][nClass];
        }
        
        //Temporarily replace worst individual with the offspring
        for (int nClass = 0; nClass < iMaxClasses; nClass++) {
            
            chromosomes[iWorstIndividual][nClass] = indOffspring[nClass];
        }
        
        //Calculate the fitness of the offspring (index of worst individual)
        load_chromosome_into_variables(iWorstIndividual);
        aiChromosomeFitnesses[iWorstIndividual] = fitness();
        
        //If the offspring fitness is worse that the worst individual
        //restore the worst individual - try again
        //Otherwise leave the offspring alone
        if (aiChromosomeFitnesses[iWorstIndividual] < iWorstFitness) {
            
            for (int nClass = 0; nClass < iMaxClasses; nClass++) {
            
                chromosomes[iWorstIndividual][nClass] = indWorst[nClass];
            }
        } else {
            
            bReplacementDone = true;
        }
        
        System.out.println("Worst individual: " + iWorstFitness);
        System.out.println("Offspring fitness: " + aiChromosomeFitnesses[iWorstIndividual]);
        
        System.out.println("Replacement done: " + bReplacementDone);
        
        return bReplacementDone;
    }
    
    //==========================================================================
    //From the best chromosome - organise and store timetable information for 
    //all room, staff and classes
    
    public void organiseDataFromBestChromosome() {
        
        //Find best chromosome
        int iBestFitness = 0;
        int iBestIndividual = -1;
        
        for (int nIndividual = 0; nIndividual < iPopulationSize; nIndividual++) {
            
            if (aiChromosomeFitnesses[nIndividual] >= iBestFitness) {
                
                iBestFitness = aiChromosomeFitnesses[nIndividual];
                iBestIndividual = nIndividual;
            }
        }
        
        load_chromosome_into_variables(iBestIndividual);
    }
    
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //Display GA progress information
    
    public void displayInfo() {
        
        //Print current generation
        System.out.println("Generation: " + iCurrentGen);
        
        //----------------------------------------------------------------------
        //Get best fitness
        
        int iBestFitness = 0;
        
        for (int nIndividual = 0; nIndividual < iPopulationSize; nIndividual++) {
            
            if (aiChromosomeFitnesses[nIndividual] >= iBestFitness) {
                
                iBestFitness = aiChromosomeFitnesses[nIndividual];
            }
        }
        
        System.out.println("Best fitness: " + iBestFitness);
        
        //----------------------------------------------------------------------
        //Get average fitness
        
        int iCumulFitness = 0;
        
        for (int nIndividual = 0; nIndividual < iPopulationSize; nIndividual++) {

            iCumulFitness += aiChromosomeFitnesses[nIndividual];
        }
        
        double dAvgFitness = (double)iCumulFitness / (double)iPopulationSize;
        
        System.out.println("Average fitness: " + dAvgFitness);
    }
}
