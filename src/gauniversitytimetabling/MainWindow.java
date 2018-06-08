/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gauniversitytimetabling;

import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import static gauniversitytimetabling.Constants.*;


/**
 *
 * @author pjderrick
 */
public class MainWindow extends javax.swing.JFrame {
    
    public Database database = new Database();
    public GA ga = new GA(database);
    
    public ArrayList<SimpleClass> alSimpleClasses = new ArrayList<SimpleClass>();
    
    //------------------------------------------------------------------------
    
    private DefaultListModel<String> dlmLecturerCurrentSkills = new DefaultListModel<String>();
    private DefaultListModel<String> dlmRoomCurrentResources = new DefaultListModel<String>();
    
    //Current skills and resources
    private DefaultListModel<String> dlmModuleLecSkills = new DefaultListModel<String>();
    private DefaultListModel<String> dlmModuleLecResources = new DefaultListModel<String>();
    private DefaultListModel<String> dlmModulePracSkills = new DefaultListModel<String>();
    private DefaultListModel<String> dlmModulePracResources = new DefaultListModel<String>();
    private DefaultListModel<String> dlmModuleTutSkills = new DefaultListModel<String>();
    private DefaultListModel<String> dlmModuleTutResources = new DefaultListModel<String>();
    
    //########################################################################
    
    /**
     * Creates new form MainWindow
     */
    
    public MainWindow() {
        
        initComponents();
          
        //#####################################################################
        //The loading of the data will be done here for the time being.
        //This will have to be transferred to another part of the program
        //at a later stage
       
        //======================================================================
        //Set the timeslot matrices for the students
        
        for (int nDay = 0; nDay < iMaxDays; nDay++) {
            
            for (int nTime = 0; nTime < iDayLength; nTime++) {

                int iPreference = database.a2diStudentTimeslotMatrix[nDay][nTime];
                
                //The +1 is to take into account the day labels on the left of the table
                tblStudentMatrix.getModel().setValueAt(iPreference, nDay, nTime + 1);
            }
        }
        
        //######################################################################
        //Load all the data into the GUI widgets that require it
        
        //For all lecturers...
        DefaultListModel<String> dlmLecturers = new DefaultListModel<String>();
        for (int i = 0; i < database.alLecturers.size(); i++) {
            dlmLecturers.addElement(database.alLecturers.get(i).sName);
        }
        this.lstLecturers.setModel(dlmLecturers);
        this.lstTTStaff.setModel(dlmLecturers);
        
        //For all lecturer available skills...
        DefaultListModel<String> dlmLecturerSkills = new DefaultListModel<String>();
        for (int i = 0; i < database.alsLecturerSkills.size(); i++) {
            dlmLecturerSkills.addElement(database.alsLecturerSkills.get(i));
        }
        this.lstAvailableLecturerSkills.setModel(dlmLecturerSkills);
        this.lstLecturerSkills.setModel(dlmLecturerSkills);
        
        //The same for the appropriate lists in the Module dialog box
        this.lstModuleAvailableLTS.setModel(dlmLecturerSkills);
        this.lstModuleAvailablePTS.setModel(dlmLecturerSkills);
        this.lstModuleAvailableTTS.setModel(dlmLecturerSkills);
        
        //For all rooms...
        DefaultListModel<String> dlmRooms = new DefaultListModel<String>();
        for (int i = 0; i < database.alRooms.size(); i++) {
            dlmRooms.addElement(database.alRooms.get(i).sID);
        }
        this.lstRooms.setModel(dlmRooms);
        this.lstTTRooms.setModel(dlmRooms);
        
        //For all room resources...
        DefaultListModel<String> dlmRoomResources = new DefaultListModel<String>();
        for (int i = 0; i < database.alsRoomResources.size(); i++) {
            dlmRoomResources.addElement(database.alsRoomResources.get(i));
        }
        this.lstAvailableRoomResources.setModel(dlmRoomResources);
        this.lstRoomResources.setModel(dlmRoomResources);
        
        //The same for the appropriate lists in the Module dialog box
        this.lstModuleAvailableLRR.setModel(dlmRoomResources);
        this.lstModuleAvailablePRR.setModel(dlmRoomResources);
        this.lstModuleAvailableTRR.setModel(dlmRoomResources);
        
        //---------------------------------------------------------------------
        
        //Ensure that the modules can be seen in the Modules list
        DefaultListModel<String> dlmModules = new DefaultListModel<String>();
        for (int i = 0; i < database.alModules.size(); i++) {
            dlmModules.addElement(database.alModules.get(i).sName);
        }
        this.lstModules.setModel(dlmModules);
        
        //#####################################################################
        //Monitoring for changes in the timetable tabs
        
        ChangeListener changeListener = new ChangeListener() {
            
            public void stateChanged(ChangeEvent changeEvent) {
                
                JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
                int index = sourceTabbedPane.getSelectedIndex();
                
                updateTimetableDisplay(sourceTabbedPane.getTitleAt(index), false);
            }
        };
        tpTimetable.addChangeListener(changeListener);
        }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dlgLecturer = new javax.swing.JDialog();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstLecturers = new javax.swing.JList<>();
        pnlLecturerSkills = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTimeslotMatrix = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        lstAvailableLecturerSkills = new javax.swing.JList<>();
        jScrollPane6 = new javax.swing.JScrollPane();
        lstCurrentLecturerSkills = new javax.swing.JList<>();
        lblAvailableLecturerSkills = new javax.swing.JLabel();
        lblCurrentSkills = new javax.swing.JLabel();
        btnAddLecturerSkillToList = new javax.swing.JButton();
        btnRemoveLecturerSkillFromList = new javax.swing.JButton();
        btnAddLecturer = new javax.swing.JButton();
        btnDeleteLecturer = new javax.swing.JButton();
        btnClearLecturerDetails = new javax.swing.JButton();
        btnLecturerOK = new javax.swing.JButton();
        btnUpdateTimeslotMatrix = new javax.swing.JButton();
        dlgRoom = new javax.swing.JDialog();
        btnAddNewRoom = new javax.swing.JButton();
        btnDeleteRoom = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        lstRooms = new javax.swing.JList<>();
        btnRoomOK = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        lstAvailableRoomResources = new javax.swing.JList<>();
        jLabel4 = new javax.swing.JLabel();
        btnAddResource = new javax.swing.JButton();
        btnRemoveResource = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        lstCurrentRoomResources = new javax.swing.JList<>();
        jLabel5 = new javax.swing.JLabel();
        spnRoomCapacity = new javax.swing.JSpinner();
        jLabel24 = new javax.swing.JLabel();
        btnClearRoomDetails = new javax.swing.JButton();
        dlgModule = new javax.swing.JDialog();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        lstModules = new javax.swing.JList<>();
        spnModuleSize = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();
        btnAddNewModule = new javax.swing.JButton();
        btnDeleteModule = new javax.swing.JButton();
        btnClearModuleDetails = new javax.swing.JButton();
        btnModuleOK = new javax.swing.JButton();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        pnlLectures = new javax.swing.JPanel();
        ckbModuleHasLectures = new javax.swing.JCheckBox();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        lstModuleAvailableLTS = new javax.swing.JList<>();
        jScrollPane10 = new javax.swing.JScrollPane();
        lstModuleAvailableLRR = new javax.swing.JList<>();
        btnModuleAddLTS = new javax.swing.JButton();
        btnModuleRemoveLTS = new javax.swing.JButton();
        btnModuleAddLRR = new javax.swing.JButton();
        btnModuleRemoveLRR = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        lstModuleCurrentLTS = new javax.swing.JList<>();
        jScrollPane12 = new javax.swing.JScrollPane();
        lstModuleCurrentLRR = new javax.swing.JList<>();
        jLabel13 = new javax.swing.JLabel();
        cmbLecturePreferredRoom1 = new javax.swing.JComboBox<>();
        cmbLecturePreferredRoom2 = new javax.swing.JComboBox<>();
        cmbLecturePreferredRoom3 = new javax.swing.JComboBox<>();
        cmbLecturePreferredRoom4 = new javax.swing.JComboBox<>();
        jLabel27 = new javax.swing.JLabel();
        spnLectureDuration = new javax.swing.JSpinner();
        pnlPracticals = new javax.swing.JPanel();
        ckbModuleHasPracticals = new javax.swing.JCheckBox();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane13 = new javax.swing.JScrollPane();
        lstModuleAvailablePTS = new javax.swing.JList<>();
        jScrollPane14 = new javax.swing.JScrollPane();
        lstModuleAvailablePRR = new javax.swing.JList<>();
        btnModuleAddPTS = new javax.swing.JButton();
        btnModuleRemovePTS = new javax.swing.JButton();
        btnModuleAddPRR = new javax.swing.JButton();
        btnModuleRemovePRR = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane15 = new javax.swing.JScrollPane();
        lstModuleCurrentPTS = new javax.swing.JList<>();
        jScrollPane16 = new javax.swing.JScrollPane();
        lstModuleCurrentPRR = new javax.swing.JList<>();
        jLabel18 = new javax.swing.JLabel();
        cmbPracticalPreferredRoom1 = new javax.swing.JComboBox<>();
        cmbPracticalPreferredRoom2 = new javax.swing.JComboBox<>();
        cmbPracticalPreferredRoom3 = new javax.swing.JComboBox<>();
        cmbPracticalPreferredRoom4 = new javax.swing.JComboBox<>();
        spnPracticalDuration = new javax.swing.JSpinner();
        jLabel28 = new javax.swing.JLabel();
        pnlTutorials = new javax.swing.JPanel();
        ckbModuleHasTutorials = new javax.swing.JCheckBox();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane17 = new javax.swing.JScrollPane();
        lstModuleAvailableTTS = new javax.swing.JList<>();
        jScrollPane18 = new javax.swing.JScrollPane();
        lstModuleAvailableTRR = new javax.swing.JList<>();
        btnModuleAddTTS = new javax.swing.JButton();
        btnModuleRemoveTTS = new javax.swing.JButton();
        btnModuleAddTRR = new javax.swing.JButton();
        btnModuleRemoveTRR = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jScrollPane19 = new javax.swing.JScrollPane();
        lstModuleCurrentTTS = new javax.swing.JList<>();
        jScrollPane20 = new javax.swing.JScrollPane();
        lstModuleCurrentTRR = new javax.swing.JList<>();
        jLabel23 = new javax.swing.JLabel();
        cmbTutorialPreferredRoom1 = new javax.swing.JComboBox<>();
        cmbTutorialPreferredRoom2 = new javax.swing.JComboBox<>();
        cmbTutorialPreferredRoom3 = new javax.swing.JComboBox<>();
        cmbTutorialPreferredRoom4 = new javax.swing.JComboBox<>();
        jLabel29 = new javax.swing.JLabel();
        spnTutorialDuration = new javax.swing.JSpinner();
        dlgSkills = new javax.swing.JDialog();
        jScrollPane21 = new javax.swing.JScrollPane();
        lstLecturerSkills = new javax.swing.JList<>();
        jLabel25 = new javax.swing.JLabel();
        btnAddLecSkill = new javax.swing.JButton();
        btnDeleteLecSkill = new javax.swing.JButton();
        btnLecSkillsOK = new javax.swing.JButton();
        dlgResources = new javax.swing.JDialog();
        jScrollPane22 = new javax.swing.JScrollPane();
        lstRoomResources = new javax.swing.JList<>();
        jLabel26 = new javax.swing.JLabel();
        btnAddRoomRes = new javax.swing.JButton();
        btnDeleteRoomRes = new javax.swing.JButton();
        btnRoomResOK = new javax.swing.JButton();
        dlgStudentTSMatrix = new javax.swing.JDialog();
        jScrollPane23 = new javax.swing.JScrollPane();
        tblStudentMatrix = new javax.swing.JTable();
        btnStudentMatrixOK = new javax.swing.JButton();
        dlgTimetables = new javax.swing.JDialog();
        jScrollPane25 = new javax.swing.JScrollPane();
        txtTimetable = new javax.swing.JTextArea();
        tpTimetable = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane26 = new javax.swing.JScrollPane();
        lstTTStudents = new javax.swing.JList<>();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane27 = new javax.swing.JScrollPane();
        lstTTStaff = new javax.swing.JList<>();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane28 = new javax.swing.JScrollPane();
        lstTTRooms = new javax.swing.JList<>();
        btnTTOK = new javax.swing.JButton();
        dlgPreprocessing = new javax.swing.JDialog();
        jScrollPane24 = new javax.swing.JScrollPane();
        txtPPList = new javax.swing.JTextArea();
        btnPPOK = new javax.swing.JButton();
        jScrollPane29 = new javax.swing.JScrollPane();
        txtPPListRooms = new javax.swing.JTextArea();
        mainMenuBar = new javax.swing.JMenuBar();
        file = new javax.swing.JMenu();
        mnuRunPreprocessing = new javax.swing.JMenuItem();
        mnuRunGA = new javax.swing.JMenuItem();
        mnuQuit = new javax.swing.JMenuItem();
        edit = new javax.swing.JMenu();
        mnuLecturers = new javax.swing.JMenuItem();
        mnuRooms = new javax.swing.JMenuItem();
        mnuModules = new javax.swing.JMenuItem();
        mnuLecturerSkills = new javax.swing.JMenuItem();
        mnuResources = new javax.swing.JMenuItem();
        mnuStudentMatrix = new javax.swing.JMenuItem();
        view = new javax.swing.JMenu();
        mnuTimetables = new javax.swing.JMenuItem();
        mnuViewPPData = new javax.swing.JMenuItem();

        dlgLecturer.setTitle("Lecturers");
        dlgLecturer.setBounds(new java.awt.Rectangle(0, 0, 650, 550));
        dlgLecturer.setResizable(false);

        jLabel1.setText("Lecturers");
        jLabel1.setToolTipText("");

        lstLecturers.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstLecturersValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(lstLecturers);

        pnlLecturerSkills.setBorder(javax.swing.BorderFactory.createTitledBorder("Lecturer Skills"));

        tblTimeslotMatrix.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Mon", null, null, null, null, null, null, null, null, null},
                {"Tue", null, null, null, null, null, null, null, null, null},
                {"Wed", null, null, null, null, null, null, null, null, null},
                {"Thur", null, null, null, null, null, null, null, null, null},
                {"Fri", null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Day", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblTimeslotMatrix);

        jLabel2.setText("Timeslot Preferences");

        jScrollPane3.setViewportView(lstAvailableLecturerSkills);

        jScrollPane6.setViewportView(lstCurrentLecturerSkills);

        lblAvailableLecturerSkills.setText("Available skills");

        lblCurrentSkills.setText("Current skills");

        btnAddLecturerSkillToList.setText("Add");
        btnAddLecturerSkillToList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddLecturerSkillToListActionPerformed(evt);
            }
        });

        btnRemoveLecturerSkillFromList.setText("Remove");
        btnRemoveLecturerSkillFromList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveLecturerSkillFromListActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlLecturerSkillsLayout = new javax.swing.GroupLayout(pnlLecturerSkills);
        pnlLecturerSkills.setLayout(pnlLecturerSkillsLayout);
        pnlLecturerSkillsLayout.setHorizontalGroup(
            pnlLecturerSkillsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLecturerSkillsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlLecturerSkillsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addGroup(pnlLecturerSkillsLayout.createSequentialGroup()
                        .addGroup(pnlLecturerSkillsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlLecturerSkillsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnAddLecturerSkillToList)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblAvailableLecturerSkills))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlLecturerSkillsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCurrentSkills)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRemoveLecturerSkillFromList)))))
        );
        pnlLecturerSkillsLayout.setVerticalGroup(
            pnlLecturerSkillsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLecturerSkillsLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(pnlLecturerSkillsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAvailableLecturerSkills)
                    .addComponent(lblCurrentSkills))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlLecturerSkillsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlLecturerSkillsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAddLecturerSkillToList)
                    .addComponent(btnRemoveLecturerSkillFromList))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(9, 9, 9)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnAddLecturer.setText("Add Lecturer...");
        btnAddLecturer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddLecturerActionPerformed(evt);
            }
        });

        btnDeleteLecturer.setText("Delete Lecturer");
        btnDeleteLecturer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDeleteLecturerMouseClicked(evt);
            }
        });
        btnDeleteLecturer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteLecturerActionPerformed(evt);
            }
        });

        btnClearLecturerDetails.setText("Clear Lecturer Details");
        btnClearLecturerDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearLecturerDetailsActionPerformed(evt);
            }
        });

        btnLecturerOK.setText("OK");
        btnLecturerOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLecturerOKActionPerformed(evt);
            }
        });

        btnUpdateTimeslotMatrix.setText("Save Timeslot Matrix");
        btnUpdateTimeslotMatrix.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateTimeslotMatrixActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dlgLecturerLayout = new javax.swing.GroupLayout(dlgLecturer.getContentPane());
        dlgLecturer.getContentPane().setLayout(dlgLecturerLayout);
        dlgLecturerLayout.setHorizontalGroup(
            dlgLecturerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlgLecturerLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(dlgLecturerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dlgLecturerLayout.createSequentialGroup()
                        .addGroup(dlgLecturerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(dlgLecturerLayout.createSequentialGroup()
                                .addGroup(dlgLecturerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pnlLecturerSkills, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(dlgLecturerLayout.createSequentialGroup()
                                .addComponent(btnLecturerOK)
                                .addGap(285, 285, 285)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(dlgLecturerLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAddLecturer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDeleteLecturer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnClearLecturerDetails)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdateTimeslotMatrix)
                        .addGap(27, 27, 27))))
        );
        dlgLecturerLayout.setVerticalGroup(
            dlgLecturerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlgLecturerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(dlgLecturerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dlgLecturerLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlLecturerSkills, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(dlgLecturerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAddLecturer)
                    .addGroup(dlgLecturerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnClearLecturerDetails)
                        .addComponent(btnDeleteLecturer)
                        .addComponent(btnUpdateTimeslotMatrix)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLecturerOK))
        );

        dlgRoom.setTitle("Rooms");
        dlgRoom.setBounds(new java.awt.Rectangle(0, 0, 555, 420));
        dlgRoom.setResizable(false);

        btnAddNewRoom.setText("Add new room...");
        btnAddNewRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddNewRoomActionPerformed(evt);
            }
        });

        btnDeleteRoom.setText("Delete room");
        btnDeleteRoom.setToolTipText("");
        btnDeleteRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteRoomActionPerformed(evt);
            }
        });

        lstRooms.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstRoomsValueChanged(evt);
            }
        });
        jScrollPane4.setViewportView(lstRooms);

        btnRoomOK.setText("OK");
        btnRoomOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRoomOKActionPerformed(evt);
            }
        });

        jLabel3.setText("Rooms");
        jLabel3.setToolTipText("");

        jScrollPane5.setViewportView(lstAvailableRoomResources);

        jLabel4.setText("Available Room Resources");

        btnAddResource.setToolTipText("");
        btnAddResource.setLabel("Add resource");
        btnAddResource.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddResourceActionPerformed(evt);
            }
        });

        btnRemoveResource.setToolTipText("");
        btnRemoveResource.setLabel("Remove resource");
        btnRemoveResource.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveResourceActionPerformed(evt);
            }
        });

        jScrollPane7.setViewportView(lstCurrentRoomResources);

        jLabel5.setText("Current Room Resources");

        spnRoomCapacity.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnRoomCapacityStateChanged(evt);
            }
        });
        spnRoomCapacity.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                spnRoomCapacityPropertyChange(evt);
            }
        });

        jLabel24.setText("Room capacity:");

        btnClearRoomDetails.setText("Clear Room Details");
        btnClearRoomDetails.setToolTipText("");
        btnClearRoomDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearRoomDetailsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dlgRoomLayout = new javax.swing.GroupLayout(dlgRoom.getContentPane());
        dlgRoom.getContentPane().setLayout(dlgRoomLayout);
        dlgRoomLayout.setHorizontalGroup(
            dlgRoomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlgRoomLayout.createSequentialGroup()
                .addGroup(dlgRoomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dlgRoomLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(dlgRoomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(dlgRoomLayout.createSequentialGroup()
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(dlgRoomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(dlgRoomLayout.createSequentialGroup()
                                        .addGap(37, 37, 37)
                                        .addComponent(btnAddResource)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnRemoveResource))
                                    .addGroup(dlgRoomLayout.createSequentialGroup()
                                        .addGroup(dlgRoomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dlgRoomLayout.createSequentialGroup()
                                                .addGap(4, 4, 4)
                                                .addGroup(dlgRoomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel5)
                                                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(dlgRoomLayout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(dlgRoomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel4)
                                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(dlgRoomLayout.createSequentialGroup()
                                                        .addGap(101, 101, 101)
                                                        .addComponent(btnClearRoomDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(dlgRoomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(spnRoomCapacity, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                            .addComponent(jLabel3)
                            .addGroup(dlgRoomLayout.createSequentialGroup()
                                .addComponent(btnAddNewRoom)
                                .addGap(8, 8, 8)
                                .addComponent(btnDeleteRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(dlgRoomLayout.createSequentialGroup()
                        .addGap(211, 211, 211)
                        .addComponent(btnRoomOK)))
                .addGap(0, 28, Short.MAX_VALUE))
        );
        dlgRoomLayout.setVerticalGroup(
            dlgRoomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlgRoomLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dlgRoomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dlgRoomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dlgRoomLayout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(dlgRoomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAddResource)
                            .addComponent(btnRemoveResource))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(dlgRoomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(dlgRoomLayout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spnRoomCapacity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dlgRoomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddNewRoom)
                    .addComponent(btnDeleteRoom)
                    .addComponent(btnClearRoomDetails))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRoomOK)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        dlgModule.setTitle("Modules");
        dlgModule.setBounds(new java.awt.Rectangle(0, 0, 769, 660));

        jLabel6.setText("Modules:");

        lstModules.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstModulesValueChanged(evt);
            }
        });
        jScrollPane8.setViewportView(lstModules);

        spnModuleSize.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spnModuleSize.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                spnModuleSizeAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        spnModuleSize.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnModuleSizeStateChanged(evt);
            }
        });

        jLabel7.setText("Module size:");

        btnAddNewModule.setText("Add new module");
        btnAddNewModule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddNewModuleActionPerformed(evt);
            }
        });

        btnDeleteModule.setText("Delete module");

        btnClearModuleDetails.setText("Clear module details");
        btnClearModuleDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearModuleDetailsActionPerformed(evt);
            }
        });

        btnModuleOK.setText("OK");
        btnModuleOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModuleOKActionPerformed(evt);
            }
        });

        jTabbedPane4.setName(""); // NOI18N

        ckbModuleHasLectures.setText("Module has lectures");
        ckbModuleHasLectures.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckbModuleHasLecturesActionPerformed(evt);
            }
        });

        jLabel9.setText("Available teaching skills");

        jScrollPane9.setViewportView(lstModuleAvailableLTS);

        lstModuleAvailableLRR.setToolTipText("");
        jScrollPane10.setViewportView(lstModuleAvailableLRR);

        btnModuleAddLTS.setText("Add");
        btnModuleAddLTS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModuleAddLTSActionPerformed(evt);
            }
        });

        btnModuleRemoveLTS.setText("Remove");
        btnModuleRemoveLTS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModuleRemoveLTSActionPerformed(evt);
            }
        });

        btnModuleAddLRR.setText("Add");
        btnModuleAddLRR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModuleAddLRRActionPerformed(evt);
            }
        });

        btnModuleRemoveLRR.setText("Remove");
        btnModuleRemoveLRR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModuleRemoveLRRActionPerformed(evt);
            }
        });

        jLabel10.setText("Available room resources");

        jLabel11.setText("Current teaching skills");

        jLabel12.setText("Current room resources");

        jScrollPane11.setViewportView(lstModuleCurrentLTS);

        jScrollPane12.setViewportView(lstModuleCurrentLRR);

        jLabel13.setText("Preferred rooms");

        cmbLecturePreferredRoom1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select..." }));

        cmbLecturePreferredRoom2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select..." }));

        cmbLecturePreferredRoom3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select..." }));

        cmbLecturePreferredRoom4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select..." }));

        jLabel27.setText("Duration:");

        spnLectureDuration.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spnLectureDuration.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnLectureDurationStateChanged(evt);
            }
        });

        javax.swing.GroupLayout pnlLecturesLayout = new javax.swing.GroupLayout(pnlLectures);
        pnlLectures.setLayout(pnlLecturesLayout);
        pnlLecturesLayout.setHorizontalGroup(
            pnlLecturesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLecturesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlLecturesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ckbModuleHasLectures)
                    .addGroup(pnlLecturesLayout.createSequentialGroup()
                        .addGroup(pnlLecturesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlLecturesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlLecturesLayout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(57, 57, 57)
                                .addComponent(jLabel13))
                            .addGroup(pnlLecturesLayout.createSequentialGroup()
                                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pnlLecturesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmbLecturePreferredRoom1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbLecturePreferredRoom2, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbLecturePreferredRoom3, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbLecturePreferredRoom4, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(pnlLecturesLayout.createSequentialGroup()
                        .addGroup(pnlLecturesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlLecturesLayout.createSequentialGroup()
                                .addComponent(btnModuleAddLTS)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnModuleRemoveLTS))
                            .addComponent(jLabel11))
                        .addGap(39, 39, 39)
                        .addGroup(pnlLecturesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addGroup(pnlLecturesLayout.createSequentialGroup()
                                .addComponent(btnModuleAddLRR)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnModuleRemoveLRR))))
                    .addGroup(pnlLecturesLayout.createSequentialGroup()
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlLecturesLayout.createSequentialGroup()
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spnLectureDuration, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        pnlLecturesLayout.setVerticalGroup(
            pnlLecturesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLecturesLayout.createSequentialGroup()
                .addGroup(pnlLecturesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlLecturesLayout.createSequentialGroup()
                        .addComponent(ckbModuleHasLectures)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlLecturesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlLecturesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlLecturesLayout.createSequentialGroup()
                                .addGroup(pnlLecturesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnlLecturesLayout.createSequentialGroup()
                                        .addComponent(cmbLecturePreferredRoom1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmbLecturePreferredRoom2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmbLecturePreferredRoom3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmbLecturePreferredRoom4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlLecturesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnModuleAddLTS)
                                    .addComponent(btnModuleRemoveLTS)
                                    .addComponent(btnModuleAddLRR)
                                    .addComponent(btnModuleRemoveLRR)))
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlLecturesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlLecturesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(spnLectureDuration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 68, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("Lectures", pnlLectures);

        ckbModuleHasPracticals.setText("Module has practicals");
        ckbModuleHasPracticals.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckbModuleHasPracticalsActionPerformed(evt);
            }
        });

        jLabel14.setText("Available teaching skills");

        jScrollPane13.setViewportView(lstModuleAvailablePTS);

        lstModuleAvailablePRR.setToolTipText("");
        jScrollPane14.setViewportView(lstModuleAvailablePRR);

        btnModuleAddPTS.setText("Add");
        btnModuleAddPTS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModuleAddPTSActionPerformed(evt);
            }
        });

        btnModuleRemovePTS.setText("Remove");
        btnModuleRemovePTS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModuleRemovePTSActionPerformed(evt);
            }
        });

        btnModuleAddPRR.setText("Add");
        btnModuleAddPRR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModuleAddPRRActionPerformed(evt);
            }
        });

        btnModuleRemovePRR.setText("Remove");
        btnModuleRemovePRR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModuleRemovePRRActionPerformed(evt);
            }
        });

        jLabel15.setText("Available room resources");

        jLabel16.setText("Current teaching skills");

        jLabel17.setText("Current room resources");

        jScrollPane15.setViewportView(lstModuleCurrentPTS);

        jScrollPane16.setViewportView(lstModuleCurrentPRR);

        jLabel18.setText("Preferred rooms");

        cmbPracticalPreferredRoom1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select..." }));

        cmbPracticalPreferredRoom2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select..." }));

        cmbPracticalPreferredRoom3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select..." }));

        cmbPracticalPreferredRoom4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select..." }));

        spnPracticalDuration.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spnPracticalDuration.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnPracticalDurationStateChanged(evt);
            }
        });

        jLabel28.setText("Duration:");

        javax.swing.GroupLayout pnlPracticalsLayout = new javax.swing.GroupLayout(pnlPracticals);
        pnlPracticals.setLayout(pnlPracticalsLayout);
        pnlPracticalsLayout.setHorizontalGroup(
            pnlPracticalsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPracticalsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPracticalsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ckbModuleHasPracticals)
                    .addGroup(pnlPracticalsLayout.createSequentialGroup()
                        .addGroup(pnlPracticalsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlPracticalsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlPracticalsLayout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(57, 57, 57)
                                .addComponent(jLabel18))
                            .addGroup(pnlPracticalsLayout.createSequentialGroup()
                                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pnlPracticalsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmbPracticalPreferredRoom1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbPracticalPreferredRoom2, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbPracticalPreferredRoom3, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbPracticalPreferredRoom4, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(pnlPracticalsLayout.createSequentialGroup()
                        .addGroup(pnlPracticalsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlPracticalsLayout.createSequentialGroup()
                                .addComponent(btnModuleAddPTS)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnModuleRemovePTS))
                            .addComponent(jLabel16))
                        .addGap(39, 39, 39)
                        .addGroup(pnlPracticalsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addGroup(pnlPracticalsLayout.createSequentialGroup()
                                .addComponent(btnModuleAddPRR)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnModuleRemovePRR))))
                    .addGroup(pnlPracticalsLayout.createSequentialGroup()
                        .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlPracticalsLayout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spnPracticalDuration, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        pnlPracticalsLayout.setVerticalGroup(
            pnlPracticalsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPracticalsLayout.createSequentialGroup()
                .addGroup(pnlPracticalsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlPracticalsLayout.createSequentialGroup()
                        .addComponent(ckbModuleHasPracticals)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlPracticalsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15)
                            .addComponent(jLabel18))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlPracticalsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlPracticalsLayout.createSequentialGroup()
                                .addGroup(pnlPracticalsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnlPracticalsLayout.createSequentialGroup()
                                        .addComponent(cmbPracticalPreferredRoom1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmbPracticalPreferredRoom2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmbPracticalPreferredRoom3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmbPracticalPreferredRoom4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlPracticalsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnModuleAddPTS)
                                    .addComponent(btnModuleRemovePTS)
                                    .addComponent(btnModuleAddPRR)
                                    .addComponent(btnModuleRemovePRR)))
                            .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlPracticalsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlPracticalsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(spnPracticalDuration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 68, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("Practicals", pnlPracticals);

        ckbModuleHasTutorials.setText("Module has tutorials");
        ckbModuleHasTutorials.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckbModuleHasTutorialsActionPerformed(evt);
            }
        });

        jLabel19.setText("Available teaching skills");

        jScrollPane17.setViewportView(lstModuleAvailableTTS);

        lstModuleAvailableTRR.setToolTipText("");
        jScrollPane18.setViewportView(lstModuleAvailableTRR);

        btnModuleAddTTS.setText("Add");
        btnModuleAddTTS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModuleAddTTSActionPerformed(evt);
            }
        });

        btnModuleRemoveTTS.setText("Remove");
        btnModuleRemoveTTS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModuleRemoveTTSActionPerformed(evt);
            }
        });

        btnModuleAddTRR.setText("Add");
        btnModuleAddTRR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModuleAddTRRActionPerformed(evt);
            }
        });

        btnModuleRemoveTRR.setText("Remove");
        btnModuleRemoveTRR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModuleRemoveTRRActionPerformed(evt);
            }
        });

        jLabel20.setText("Available room resources");

        jLabel21.setText("Current teaching skills");

        jLabel22.setText("Current room resources");

        jScrollPane19.setViewportView(lstModuleCurrentTTS);

        jScrollPane20.setViewportView(lstModuleCurrentTRR);

        jLabel23.setText("Preferred rooms");

        cmbTutorialPreferredRoom1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select..." }));

        cmbTutorialPreferredRoom2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select..." }));

        cmbTutorialPreferredRoom3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select..." }));
        cmbTutorialPreferredRoom3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTutorialPreferredRoom3ActionPerformed(evt);
            }
        });

        cmbTutorialPreferredRoom4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select..." }));

        jLabel29.setText("Duration:");

        spnTutorialDuration.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spnTutorialDuration.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnTutorialDurationStateChanged(evt);
            }
        });

        javax.swing.GroupLayout pnlTutorialsLayout = new javax.swing.GroupLayout(pnlTutorials);
        pnlTutorials.setLayout(pnlTutorialsLayout);
        pnlTutorialsLayout.setHorizontalGroup(
            pnlTutorialsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTutorialsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTutorialsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ckbModuleHasTutorials)
                    .addGroup(pnlTutorialsLayout.createSequentialGroup()
                        .addGroup(pnlTutorialsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlTutorialsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlTutorialsLayout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addGap(57, 57, 57)
                                .addComponent(jLabel23))
                            .addGroup(pnlTutorialsLayout.createSequentialGroup()
                                .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pnlTutorialsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmbTutorialPreferredRoom1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbTutorialPreferredRoom2, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbTutorialPreferredRoom3, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbTutorialPreferredRoom4, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(pnlTutorialsLayout.createSequentialGroup()
                        .addGroup(pnlTutorialsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlTutorialsLayout.createSequentialGroup()
                                .addComponent(btnModuleAddTTS)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnModuleRemoveTTS))
                            .addComponent(jLabel21))
                        .addGap(39, 39, 39)
                        .addGroup(pnlTutorialsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addGroup(pnlTutorialsLayout.createSequentialGroup()
                                .addComponent(btnModuleAddTRR)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnModuleRemoveTRR))))
                    .addGroup(pnlTutorialsLayout.createSequentialGroup()
                        .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlTutorialsLayout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spnTutorialDuration, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        pnlTutorialsLayout.setVerticalGroup(
            pnlTutorialsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTutorialsLayout.createSequentialGroup()
                .addGroup(pnlTutorialsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlTutorialsLayout.createSequentialGroup()
                        .addComponent(ckbModuleHasTutorials)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlTutorialsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(jLabel20)
                            .addComponent(jLabel23))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlTutorialsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlTutorialsLayout.createSequentialGroup()
                                .addGroup(pnlTutorialsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnlTutorialsLayout.createSequentialGroup()
                                        .addComponent(cmbTutorialPreferredRoom1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmbTutorialPreferredRoom2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmbTutorialPreferredRoom3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmbTutorialPreferredRoom4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlTutorialsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnModuleAddTTS)
                                    .addComponent(btnModuleRemoveTTS)
                                    .addComponent(btnModuleAddTRR)
                                    .addComponent(btnModuleRemoveTRR)))
                            .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlTutorialsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21)
                            .addComponent(jLabel22))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTutorialsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(spnTutorialDuration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 68, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("Tutorials", pnlTutorials);

        javax.swing.GroupLayout dlgModuleLayout = new javax.swing.GroupLayout(dlgModule.getContentPane());
        dlgModule.getContentPane().setLayout(dlgModuleLayout);
        dlgModuleLayout.setHorizontalGroup(
            dlgModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlgModuleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dlgModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dlgModuleLayout.createSequentialGroup()
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTabbedPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 573, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(dlgModuleLayout.createSequentialGroup()
                        .addGroup(dlgModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(dlgModuleLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spnModuleSize, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(dlgModuleLayout.createSequentialGroup()
                                .addComponent(btnAddNewModule)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDeleteModule)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnClearModuleDetails)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnModuleOK)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        dlgModuleLayout.setVerticalGroup(
            dlgModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlgModuleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dlgModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane4)
                    .addComponent(jScrollPane8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dlgModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spnModuleSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(8, 8, 8)
                .addGroup(dlgModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddNewModule)
                    .addComponent(btnDeleteModule)
                    .addComponent(btnClearModuleDetails)
                    .addComponent(btnModuleOK))
                .addGap(43, 43, 43))
        );

        jTabbedPane4.getAccessibleContext().setAccessibleDescription("");

        dlgSkills.setTitle("Lecturer Skills");
        dlgSkills.setBounds(new java.awt.Rectangle(0, 0, 250, 300));

        jScrollPane21.setViewportView(lstLecturerSkills);

        jLabel25.setText("List of skills");

        btnAddLecSkill.setText("Add Skill...");
        btnAddLecSkill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddLecSkillActionPerformed(evt);
            }
        });

        btnDeleteLecSkill.setText("Delete Skill");
        btnDeleteLecSkill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteLecSkillActionPerformed(evt);
            }
        });

        btnLecSkillsOK.setText("OK");
        btnLecSkillsOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLecSkillsOKActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dlgSkillsLayout = new javax.swing.GroupLayout(dlgSkills.getContentPane());
        dlgSkills.getContentPane().setLayout(dlgSkillsLayout);
        dlgSkillsLayout.setHorizontalGroup(
            dlgSkillsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlgSkillsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dlgSkillsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25)
                    .addGroup(dlgSkillsLayout.createSequentialGroup()
                        .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(dlgSkillsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAddLecSkill)
                            .addComponent(btnDeleteLecSkill)
                            .addComponent(btnLecSkillsOK))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dlgSkillsLayout.setVerticalGroup(
            dlgSkillsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dlgSkillsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dlgSkillsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane21, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                    .addGroup(dlgSkillsLayout.createSequentialGroup()
                        .addComponent(btnAddLecSkill)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDeleteLecSkill)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLecSkillsOK)))
                .addContainerGap())
        );

        dlgResources.setTitle("Room Resources");
        dlgResources.setBounds(new java.awt.Rectangle(0, 0, 600, 300));

        jScrollPane22.setViewportView(lstRoomResources);

        jLabel26.setText("List of resources");

        btnAddRoomRes.setText("Add Resource...");
        btnAddRoomRes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddRoomResActionPerformed(evt);
            }
        });

        btnDeleteRoomRes.setText("Delete Resource");
        btnDeleteRoomRes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteRoomResActionPerformed(evt);
            }
        });

        btnRoomResOK.setText("OK");
        btnRoomResOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRoomResOKActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dlgResourcesLayout = new javax.swing.GroupLayout(dlgResources.getContentPane());
        dlgResources.getContentPane().setLayout(dlgResourcesLayout);
        dlgResourcesLayout.setHorizontalGroup(
            dlgResourcesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlgResourcesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dlgResourcesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26)
                    .addGroup(dlgResourcesLayout.createSequentialGroup()
                        .addComponent(jScrollPane22, javax.swing.GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(dlgResourcesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAddRoomRes, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnRoomResOK)
                            .addComponent(btnDeleteRoomRes))))
                .addGap(20, 20, 20))
        );
        dlgResourcesLayout.setVerticalGroup(
            dlgResourcesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dlgResourcesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dlgResourcesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane22, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                    .addGroup(dlgResourcesLayout.createSequentialGroup()
                        .addComponent(btnAddRoomRes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDeleteRoomRes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRoomResOK)))
                .addContainerGap())
        );

        dlgStudentTSMatrix.setTitle("Student Timeslot Preferences");
        dlgStudentTSMatrix.setBounds(new java.awt.Rectangle(0, 0, 490, 200));

        tblStudentMatrix.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Mon", null, null, null, null, null, null, null, null, null},
                {"Tue", null, null, null, null, null, null, null, null, null},
                {"Wed", null, null, null, null, null, null, null, null, null},
                {"Thur", null, null, null, null, null, null, null, null, null},
                {"Fri", null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Day", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane23.setViewportView(tblStudentMatrix);

        btnStudentMatrixOK.setText("OK");
        btnStudentMatrixOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStudentMatrixOKActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dlgStudentTSMatrixLayout = new javax.swing.GroupLayout(dlgStudentTSMatrix.getContentPane());
        dlgStudentTSMatrix.getContentPane().setLayout(dlgStudentTSMatrixLayout);
        dlgStudentTSMatrixLayout.setHorizontalGroup(
            dlgStudentTSMatrixLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlgStudentTSMatrixLayout.createSequentialGroup()
                .addGroup(dlgStudentTSMatrixLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dlgStudentTSMatrixLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(dlgStudentTSMatrixLayout.createSequentialGroup()
                        .addGap(195, 195, 195)
                        .addComponent(btnStudentMatrixOK)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dlgStudentTSMatrixLayout.setVerticalGroup(
            dlgStudentTSMatrixLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlgStudentTSMatrixLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane23, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnStudentMatrixOK)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        dlgTimetables.setTitle("Timetables");
        dlgTimetables.setBounds(new java.awt.Rectangle(0, 0, 500, 350));

        txtTimetable.setEditable(false);
        txtTimetable.setColumns(20);
        txtTimetable.setRows(5);
        jScrollPane25.setViewportView(txtTimetable);

        tpTimetable.setName(""); // NOI18N
        tpTimetable.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tpTimetableStateChanged(evt);
            }
        });

        lstTTStudents.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstTTStudentsValueChanged(evt);
            }
        });
        jScrollPane26.setViewportView(lstTTStudents);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane26, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane26, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                .addContainerGap())
        );

        tpTimetable.addTab("Classes", jPanel1);

        lstTTStaff.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstTTStaffValueChanged(evt);
            }
        });
        jScrollPane27.setViewportView(lstTTStaff);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane27, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane27, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                .addContainerGap())
        );

        tpTimetable.addTab("Staff", jPanel2);

        lstTTRooms.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstTTRoomsValueChanged(evt);
            }
        });
        jScrollPane28.setViewportView(lstTTRooms);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane28, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane28, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                .addContainerGap())
        );

        tpTimetable.addTab("Rooms", jPanel3);

        btnTTOK.setText("OK");
        btnTTOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTTOKActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dlgTimetablesLayout = new javax.swing.GroupLayout(dlgTimetables.getContentPane());
        dlgTimetables.getContentPane().setLayout(dlgTimetablesLayout);
        dlgTimetablesLayout.setHorizontalGroup(
            dlgTimetablesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlgTimetablesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tpTimetable, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane25, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(dlgTimetablesLayout.createSequentialGroup()
                .addGap(202, 202, 202)
                .addComponent(btnTTOK)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dlgTimetablesLayout.setVerticalGroup(
            dlgTimetablesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dlgTimetablesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dlgTimetablesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tpTimetable)
                    .addComponent(jScrollPane25))
                .addGap(18, 18, 18)
                .addComponent(btnTTOK)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        dlgPreprocessing.setBounds(new java.awt.Rectangle(0, 0, 710, 550));

        txtPPList.setEditable(false);
        txtPPList.setColumns(20);
        txtPPList.setRows(5);
        jScrollPane24.setViewportView(txtPPList);

        btnPPOK.setText("OK");
        btnPPOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPPOKActionPerformed(evt);
            }
        });

        txtPPListRooms.setEditable(false);
        txtPPListRooms.setColumns(20);
        txtPPListRooms.setRows(5);
        jScrollPane29.setViewportView(txtPPListRooms);

        javax.swing.GroupLayout dlgPreprocessingLayout = new javax.swing.GroupLayout(dlgPreprocessing.getContentPane());
        dlgPreprocessing.getContentPane().setLayout(dlgPreprocessingLayout);
        dlgPreprocessingLayout.setHorizontalGroup(
            dlgPreprocessingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlgPreprocessingLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnPPOK)
                .addGap(327, 327, 327))
            .addGroup(dlgPreprocessingLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane24, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane29, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(75, Short.MAX_VALUE))
        );
        dlgPreprocessingLayout.setVerticalGroup(
            dlgPreprocessingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlgPreprocessingLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dlgPreprocessingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane24, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane29, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                .addComponent(btnPPOK)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Final Year Project - University Timetabling");

        file.setText("File");

        mnuRunPreprocessing.setText("Run Pre-processing");
        mnuRunPreprocessing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuRunPreprocessingActionPerformed(evt);
            }
        });
        file.add(mnuRunPreprocessing);

        mnuRunGA.setText("Run GA");
        mnuRunGA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuRunGAActionPerformed(evt);
            }
        });
        file.add(mnuRunGA);

        mnuQuit.setText("Quit");
        mnuQuit.setToolTipText("");
        mnuQuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuQuitActionPerformed(evt);
            }
        });
        file.add(mnuQuit);

        mainMenuBar.add(file);

        edit.setText("Edit");

        mnuLecturers.setText("Lecturers...");
        mnuLecturers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuLecturersActionPerformed(evt);
            }
        });
        edit.add(mnuLecturers);

        mnuRooms.setText("Rooms...");
        mnuRooms.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuRoomsActionPerformed(evt);
            }
        });
        edit.add(mnuRooms);

        mnuModules.setText("Modules...");
        mnuModules.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuModulesActionPerformed(evt);
            }
        });
        edit.add(mnuModules);

        mnuLecturerSkills.setText("Lecturer Skills...");
        mnuLecturerSkills.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuLecturerSkillsActionPerformed(evt);
            }
        });
        edit.add(mnuLecturerSkills);

        mnuResources.setText("Room Resources...");
        mnuResources.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuResourcesActionPerformed(evt);
            }
        });
        edit.add(mnuResources);

        mnuStudentMatrix.setText("Student Timeslot Matrix...");
        mnuStudentMatrix.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuStudentMatrixActionPerformed(evt);
            }
        });
        edit.add(mnuStudentMatrix);

        mainMenuBar.add(edit);

        view.setText("View");

        mnuTimetables.setText("View All Timetables...");
        mnuTimetables.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuTimetablesActionPerformed(evt);
            }
        });
        view.add(mnuTimetables);

        mnuViewPPData.setText("View Pre-processing data...");
        mnuViewPPData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuViewPPDataActionPerformed(evt);
            }
        });
        view.add(mnuViewPPData);

        mainMenuBar.add(view);

        setJMenuBar(mainMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 283, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    //########################################################################
    //Running the GA
    
    private void runGA() {
        
        int iNumRooms = database.alRooms.size();
        int iNumLecturers = database.alLecturers.size();
        ga.iCurrentGen = 0;
       
        //Initialise chromosomes to random values for rooms and timeslots
        ga.initChromosomes(database);
        ga.calculateFitness();
        
        //Run through iNumGenerations
        for (int i = 0; i < iNumGenerations; i++) {
            
            ga.calculateGeneration( iNumRooms );
            ga.displayInfo();
            
            ga.iCurrentGen++;
        }
        
        //Generate the information needed for the timetables for rooms/staff/classes
        ga.organiseDataFromBestChromosome();
        
        int iNumClasses = alSimpleClasses.size();
        
        //----------------------------------------------------------------------
        //Clear all previous data
        
        //Clear all room timetabling data
        for (int nRoom = 0; nRoom < iNumRooms; nRoom++) {
            
            for (int nDay = 0; nDay < iMaxDays; nDay++) {

                for (int nTime = 0; nTime < iDayLength; nTime++) {
                    
                    database.alRooms.get(nRoom).timetable[nDay][nTime].iDuration = 0;
                    database.alRooms.get(nRoom).timetable[nDay][nTime].sClass = "";

                    database.alRooms.get(nRoom).timetable[nDay][nTime].sLecturer = "";

                    database.alRooms.get(nRoom).timetable[nDay][nTime].bIsLecture = false;
                    database.alRooms.get(nRoom).timetable[nDay][nTime].bIsPractical = false;
                    database.alRooms.get(nRoom).timetable[nDay][nTime].bIsTutorial = false;
                }   
            }
        }
        
        //Clear all lecturer timetabling data
        for (int nLecturer = 0; nLecturer < iNumLecturers; nLecturer++) {
            
            for (int nDay = 0; nDay < iMaxDays; nDay++) {

                for (int nTime = 0; nTime < iDayLength; nTime++) {
                    
                    database.alLecturers.get(nLecturer).timetable[nDay][nTime].iDuration = 0;
                    database.alLecturers.get(nLecturer).timetable[nDay][nTime].sClass = "";

                    database.alLecturers.get(nLecturer).timetable[nDay][nTime].sRoom = "";
                    database.alLecturers.get(nLecturer).timetable[nDay][nTime].bIsLecturerPresent = false;

                    database.alLecturers.get(nLecturer).timetable[nDay][nTime].bIsLecture = false;
                    database.alLecturers.get(nLecturer).timetable[nDay][nTime].bIsPractical = false;
                    database.alLecturers.get(nLecturer).timetable[nDay][nTime].bIsTutorial = false;
                }   
            }
        }
        
        //----------------------------------------------------------------------
        //Generate timetables for all rooms
        
        //a = new int[iMaxClasses][iMaxRooms][iMaxDays][iDayLength]
        
        for (int nRoom = 0; nRoom < iNumRooms; nRoom++) {
            
            for (int nClass = 0; nClass < iNumClasses; nClass++) {

                for (int nDay = 0; nDay < iMaxDays; nDay++) {

                    for (int nTime = 0; nTime < iDayLength; nTime++) {

                        int iClassPresentInRoom = ga.a[nClass][nRoom][nDay][nTime];
                        
                        if (iClassPresentInRoom == 1) {
                            
                            database.alRooms.get(nRoom).timetable[nDay][nTime].iDuration = alSimpleClasses.get(nClass).iDuration;
                            database.alRooms.get(nRoom).timetable[nDay][nTime].sClass = alSimpleClasses.get(nClass).sName;
                            
                            int iLecturerIndex = ga.aClasses[nClass].aiLecturer[0];
                            database.alRooms.get(nRoom).timetable[nDay][nTime].sLecturer = database.alLecturers.get(iLecturerIndex).sName;
                            
                            database.alRooms.get(nRoom).timetable[nDay][nTime].bIsLecture = alSimpleClasses.get(nClass).bIsLecture;
                            database.alRooms.get(nRoom).timetable[nDay][nTime].bIsPractical = alSimpleClasses.get(nClass).bIsPractical;
                            database.alRooms.get(nRoom).timetable[nDay][nTime].bIsTutorial = alSimpleClasses.get(nClass).bIsTutorial;
                        }
                    }     
                }
            }
        }
        
        //----------------------------------------------------------------------
        //Generate timetables for all lecturers
        
        //s[iMaxLecturers][iMaxRooms][iMaxDays][iDayLength]
        
        for (int nLecturer = 0; nLecturer < iNumLecturers; nLecturer++) {
            
            for (int nRoom = 0; nRoom < iNumRooms; nRoom++) {

                for (int nDay = 0; nDay < iMaxDays; nDay++) {

                    for (int nTime = 0; nTime < iDayLength; nTime++) {

                        int iLecturerPresentInRoom = ga.s[nLecturer][nRoom][nDay][nTime];
                        
                        if (iLecturerPresentInRoom == 1) {
                            
                            database.alLecturers.get(nLecturer).timetable[nDay][nTime].bIsLecturerPresent = true;
                            database.alLecturers.get(nLecturer).timetable[nDay][nTime].sRoom = database.alRooms.get(nRoom).sID;
                            
                            for (int nClass = 0; nClass < alSimpleClasses.size(); nClass++) {
                                
                                int iClassPresentInRoom = ga.a[nClass][nRoom][nDay][nTime];
                                
                                if (iClassPresentInRoom == 1) {
                                
                                    int iClassLecturer = ga.aClasses[nClass].aiLecturer[0];

                                    if (iClassLecturer == nLecturer) {

                                        database.alLecturers.get(nLecturer).timetable[nDay][nTime].bIsLecture = alSimpleClasses.get(nClass).bIsLecture;
                                        database.alLecturers.get(nLecturer).timetable[nDay][nTime].bIsPractical = alSimpleClasses.get(nClass).bIsPractical;
                                        database.alLecturers.get(nLecturer).timetable[nDay][nTime].bIsTutorial = alSimpleClasses.get(nClass).bIsTutorial;

                                        database.alLecturers.get(nLecturer).timetable[nDay][nTime].iDuration = alSimpleClasses.get(nClass).iDuration;
                                        database.alLecturers.get(nLecturer).timetable[nDay][nTime].sClass = alSimpleClasses.get(nClass).sName;

                                        break;
                                    }
                                }
                            }
                        }
                    }     
                }
            }
        }
    }
    
    private void updateTimetableDisplay(String sTabDisplayed, boolean bDisplayStudentsTab) {
        
        String sTimetable = "";
        
        int iTabDisplayed = tpTimetable.getSelectedIndex();
        
        //--------------------------------------------------------------------
        //Class timetables
        
        if (iTabDisplayed == 0) {
            
            int iClass = lstTTStudents.getSelectedIndex();
            
            if (iClass != -1) {
                
                int iDuration = alSimpleClasses.get(iClass).iDuration;
                
                int iLecturerIndex = ga.aClasses[iClass].aiLecturer[0];
                String sLecturer = database.alLecturers.get(iLecturerIndex).sName;

                for (int nDay = 0; nDay < iMaxDays; nDay++) {
                    
                    //Get the name of the day
                    String sDay = "";
                    switch (nDay) {

                        case 0: sTimetable = sTimetable + "Monday" + "\n"; break;
                        case 1: sTimetable = sTimetable + "Tuesday" + "\n"; break;
                        case 2: sTimetable = sTimetable + "Wednesday" + "\n"; break;
                        case 3: sTimetable = sTimetable + "Thursday" + "\n"; break;
                        case 4: sTimetable = sTimetable + "Friday" + "\n"; break;
                    }
                    
                    sTimetable = sTimetable + sDay + "\n";
                    
                    for (int nTime = 0; nTime < iDayLength; nTime++) {
                        
                        //Get the time string
                        String sTime = "";
                        switch (nTime) {

                            case 0: sTime = "09:00" + "\n"; break;
                            case 1: sTime = "10:00" + "\n"; break;
                            case 2: sTime = "11:00" + "\n"; break;
                            case 3: sTime = "12:00" + "\n"; break;
                            case 4: sTime = "13:00" + "\n"; break;
                            case 5: sTime = "14:00" + "\n"; break;
                            case 6: sTime = "15:00" + "\n"; break;
                            case 7: sTime = "16:00" + "\n"; break;
                            case 8: sTime = "17:00" + "\n"; break;
                        }
                        sTimetable = sTimetable + sTime;
                        
                        for (int nRoom = 0; nRoom < database.alRooms.size(); nRoom++) {

                            int iClassPresentInRoom = ga.a[iClass][nRoom][nDay][nTime];
                            
                            if (iClassPresentInRoom == 1) {
                                
                                String sRoomName = database.alRooms.get(nRoom).sID;
 
                                //Update the timetable string
                                sTimetable = sTimetable + sRoomName + "\n";
                                sTimetable = sTimetable + sLecturer + "\n";
                                sTimetable = sTimetable + "Duration: " + String.valueOf(iDuration) + "h\n";

                                break;
                            }
                        }
                    }
                }
            }
        }
        
        //--------------------------------------------------------------------
        //Staff timetables
        
        if (iTabDisplayed == 1) {
            
            int iStaff = lstTTStaff.getSelectedIndex();
            
            if (iStaff != -1) {

                for (int nDay = 0; nDay < iMaxDays; nDay++) {

                    switch (nDay) {

                        case 0: sTimetable = sTimetable + "Monday" + "\n"; break;
                        case 1: sTimetable = sTimetable + "Tuesday" + "\n"; break;
                        case 2: sTimetable = sTimetable + "Wednesday" + "\n"; break;
                        case 3: sTimetable = sTimetable + "Thursday" + "\n"; break;
                        case 4: sTimetable = sTimetable + "Friday" + "\n"; break;
                    }

                    String sTime = "";            

                    for (int nTime = 0; nTime < iDayLength; nTime++) {

                        switch (nTime) {

                            case 0: sTime = "09:00" + "\n"; break;
                            case 1: sTime = "10:00" + "\n"; break;
                            case 2: sTime = "11:00" + "\n"; break;
                            case 3: sTime = "12:00" + "\n"; break;
                            case 4: sTime = "13:00" + "\n"; break;
                            case 5: sTime = "14:00" + "\n"; break;
                            case 6: sTime = "15:00" + "\n"; break;
                            case 7: sTime = "16:00" + "\n"; break;
                            case 8: sTime = "17:00" + "\n"; break;
                        }

                        //int iDuration = database.alLecturers.get(iStaff).timetable[nDay][nTime].iDuration;
                        String sRoom = database.alLecturers.get(iStaff).timetable[nDay][nTime].sRoom;
                        
                        boolean bIsLecturerPresent = database.alLecturers.get(iStaff).timetable[nDay][nTime].bIsLecturerPresent;
                        
                        //If there is a class present at that daya and time...
                        if ( bIsLecturerPresent ) {
                            
                            int iDuration = database.alLecturers.get(iStaff).timetable[nDay][nTime].iDuration;
                            String sDuration = String.valueOf(iDuration);
                            String sClass = database.alLecturers.get(iStaff).timetable[nDay][nTime].sClass;

                            boolean bIsLecture = database.alLecturers.get(iStaff).timetable[nDay][nTime].bIsLecture;
                            boolean bIsPractical = database.alLecturers.get(iStaff).timetable[nDay][nTime].bIsPractical;
                            boolean bIsTutorial = database.alLecturers.get(iStaff).timetable[nDay][nTime].bIsTutorial;
                            
                            String sActivityType = "";
                            if (bIsLecture) { sActivityType = "Lecture - "; }
                            if (bIsPractical) { sActivityType = "Practical - "; }
                            if (bIsTutorial) { sActivityType = "Tutorial - "; }
                            
                            sTimetable = sTimetable + sTime + sRoom + "\n" + sActivityType + sClass + "\n" + "Duration: " + sDuration + "\n";
                            //sTimetable = sTimetable + sTime + sRoom + "\n";
                        }
                    }
                }
            }
        }
        
        //--------------------------------------------------------------------
        //Room timetables
        
        if (iTabDisplayed == 2) {
            
            int iRoom = lstTTRooms.getSelectedIndex();
            
            if (iRoom != -1) {

                for (int nDay = 0; nDay < iMaxDays; nDay++) {

                    switch (nDay) {

                        case 0: sTimetable = sTimetable + "Monday" + "\n"; break;
                        case 1: sTimetable = sTimetable + "Tuesday" + "\n"; break;
                        case 2: sTimetable = sTimetable + "Wednesday" + "\n"; break;
                        case 3: sTimetable = sTimetable + "Thursday" + "\n"; break;
                        case 4: sTimetable = sTimetable + "Friday" + "\n"; break;
                    }

                    String sTime = "";            

                    for (int nTime = 0; nTime < iDayLength; nTime++) {

                        switch (nTime) {

                            case 0: sTime = "09:00" + "\n"; break;
                            case 1: sTime = "10:00" + "\n"; break;
                            case 2: sTime = "11:00" + "\n"; break;
                            case 3: sTime = "12:00" + "\n"; break;
                            case 4: sTime = "13:00" + "\n"; break;
                            case 5: sTime = "14:00" + "\n"; break;
                            case 6: sTime = "15:00" + "\n"; break;
                            case 7: sTime = "16:00" + "\n"; break;
                            case 8: sTime = "17:00" + "\n"; break;
                        }

                        int iDuration = database.alRooms.get(iRoom).timetable[nDay][nTime].iDuration;
                        
                        //If there is a class present at that daya and time...
                        if (iDuration > 0) {
                            
                            String sDuration = String.valueOf(iDuration);
                            String sClass = database.alRooms.get(iRoom).timetable[nDay][nTime].sClass;
                            String sLecturer = database.alRooms.get(iRoom).timetable[nDay][nTime].sLecturer;
                            
                            boolean bIsLecture = database.alRooms.get(iRoom).timetable[nDay][nTime].bIsLecture;
                            boolean bIsPractical = database.alRooms.get(iRoom).timetable[nDay][nTime].bIsPractical;
                            boolean bIsTutorial = database.alRooms.get(iRoom).timetable[nDay][nTime].bIsTutorial;
                            
                            String sActivityType = "";
                            if (bIsLecture) { sActivityType = "Lecture - "; }
                            if (bIsPractical) { sActivityType = "Practical - "; }
                            if (bIsTutorial) { sActivityType = "Tutorial - "; }
                            
                            sTimetable = sTimetable + sTime + sActivityType + sClass + "\n" + sLecturer + "\n" + "Duration: " + sDuration + "\n";
                        }
                    }
                }
            }
        }
        
        txtTimetable.setText(sTimetable);
        txtTimetable.setCaretPosition(0);
    }
    
    //########################################################################
    
    private void mnuLecturersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuLecturersActionPerformed
        
        dlgLecturer.setVisible(true);
    }//GEN-LAST:event_mnuLecturersActionPerformed

    private void mnuQuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuQuitActionPerformed
        
        System.exit(0);
    }//GEN-LAST:event_mnuQuitActionPerformed

    private void cmbTutorialPreferredRoom3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTutorialPreferredRoom3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTutorialPreferredRoom3ActionPerformed
    
    //Add a new skill to a lecturer
    private void btnAddLecturerSkillToListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddLecturerSkillToListActionPerformed
        
        int iLecturerSelected = lstLecturers.getSelectedIndex();
        
        int iSelectedSkill = lstAvailableLecturerSkills.getSelectedIndex();
        
        if ((iSelectedSkill != -1) && (iLecturerSelected != -1)) {
        
            DefaultListModel<String> dlmAvailableLecturerSkills = (DefaultListModel<String>)lstAvailableLecturerSkills.getModel();

            String sNewSkill = dlmAvailableLecturerSkills.get(iSelectedSkill);

            dlmLecturerCurrentSkills.addElement(sNewSkill);

            //Add this code to room resources and the modules.
            //This code removes the need for a user to save any changes made to a 
            //lecturer's skills in the template before moving on
            int iLecturer = this.lstLecturers.getSelectedIndex();
            database.alLecturers.get(iLecturer).alsSkills.add(sNewSkill);

            this.lstCurrentLecturerSkills.setModel(dlmLecturerCurrentSkills);
        }
    }//GEN-LAST:event_btnAddLecturerSkillToListActionPerformed

    private void lstLecturersValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstLecturersValueChanged
        
        //Strange that this gets called twice when an item in the list gets selected
        //System.out.println("Value changed");
        
        int iLecturer = lstLecturers.getSelectedIndex();
        
        dlmLecturerCurrentSkills.clear();
        
        for (int i = 0; i < database.alLecturers.get(iLecturer).alsSkills.size(); i++) {
            
            dlmLecturerCurrentSkills.addElement(database.alLecturers.get(iLecturer).alsSkills.get(i));
        }
        this.lstCurrentLecturerSkills.setModel(dlmLecturerCurrentSkills);
        
        //----------------------------------------------------------------------
        //Set the timeslot matrix table for the lecturer
        
        for (int nDay = 0; nDay < iMaxDays; nDay++) {
            
            for (int nTime = 0; nTime < iDayLength; nTime++) {

                int iPreference = database.alLecturers.get(iLecturer).a2diTimeslotMatrix[nDay][nTime];
                
                //The +1 is to take into account the day labels on the left of the table
                tblTimeslotMatrix.getModel().setValueAt(iPreference, nDay, nTime + 1);
            }
        }
    }//GEN-LAST:event_lstLecturersValueChanged

    private void mnuRoomsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuRoomsActionPerformed
        
        dlgRoom.setVisible(true);
    }//GEN-LAST:event_mnuRoomsActionPerformed

    private void lstRoomsValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstRoomsValueChanged
        
        int iRoom = lstRooms.getSelectedIndex();
        
        dlmRoomCurrentResources.clear();
        
        for (int i = 0; i < database.alRooms.get(iRoom).alsResources.size(); i++) {
            
            dlmRoomCurrentResources.addElement(database.alRooms.get(iRoom).alsResources.get(i));
        }
        this.lstCurrentRoomResources.setModel(dlmRoomCurrentResources);
        
        //Update the spinner box containing the room capacity
        this.spnRoomCapacity.setValue(database.alRooms.get(iRoom).iCapacity);
    }//GEN-LAST:event_lstRoomsValueChanged

    private void mnuModulesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuModulesActionPerformed
       
        dlgModule.setVisible(true);
    }//GEN-LAST:event_mnuModulesActionPerformed

    private void lstModulesValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstModulesValueChanged
        
        //Some duplication going on here - need to check this out
        dlmModuleLecSkills.clear();
        dlmModuleLecResources.clear();
        dlmModulePracSkills.clear();
        dlmModulePracResources.clear();
        dlmModuleTutSkills.clear();
        dlmModuleTutResources.clear();

        //======================================================================
        
        int iModule = lstModules.getSelectedIndex();
        
        //---------------------------------------------------------------------
        
        //For lecture skills
        for (int i = 0; i < database.alModules.get(iModule).alsLectureSkills.size(); i++) {
            
            dlmModuleLecSkills.addElement(database.alModules.get(iModule).alsLectureSkills.get(i));
        }
        this.lstModuleCurrentLTS.setModel(dlmModuleLecSkills);
        
        //For practical skills
        for (int i = 0; i < database.alModules.get(iModule).alsPracticalSkills.size(); i++) {
            
            dlmModulePracSkills.addElement(database.alModules.get(iModule).alsPracticalSkills.get(i));
        }
        this.lstModuleCurrentPTS.setModel(dlmModulePracSkills);
        
        //For tutorial skills
        for (int i = 0; i < database.alModules.get(iModule).alsTutorialSkills.size(); i++) {
            
            dlmModuleTutSkills.addElement(database.alModules.get(iModule).alsTutorialSkills.get(i));
        }
        this.lstModuleCurrentTTS.setModel(dlmModuleTutSkills);
        
        //---------------------------------------------------------------------
        
        //For lecture room resources
        for (int i = 0; i < database.alModules.get(iModule).alsLectureResources.size(); i++) {
            
            dlmModuleLecResources.addElement(database.alModules.get(iModule).alsLectureResources.get(i));
        }
        this.lstModuleCurrentLRR.setModel(dlmModuleLecResources);
        
        //For practical room resources
        for (int i = 0; i < database.alModules.get(iModule).alsPracticalResources.size(); i++) {
            
            dlmModulePracResources.addElement(database.alModules.get(iModule).alsPracticalResources.get(i));
        }
        this.lstModuleCurrentPRR.setModel(dlmModulePracResources);
        
        //For tutorial room resources
        for (int i = 0; i < database.alModules.get(iModule).alsTutorialResources.size(); i++) {
            
            dlmModuleTutResources.addElement(database.alModules.get(iModule).alsTutorialResources.get(i));
        }
        this.lstModuleCurrentTRR.setModel(dlmModuleTutResources);

        //=====================================================================
        //Set sizes and boolean flags in GUI
        
        spnModuleSize.setValue(database.alModules.get(iModule).iSize);
        
        spnLectureDuration.setValue(database.alModules.get(iModule).iLectureDuration);
        spnPracticalDuration.setValue(database.alModules.get(iModule).iPracticalDuration);
        spnTutorialDuration.setValue(database.alModules.get(iModule).iTutorialDuration);
        
        ckbModuleHasLectures.setSelected(database.alModules.get(iModule).bHasLectures);
        ckbModuleHasPracticals.setSelected(database.alModules.get(iModule).bHasPracticals);
        ckbModuleHasTutorials.setSelected(database.alModules.get(iModule).bHasTutorials);
    }//GEN-LAST:event_lstModulesValueChanged

    private void mnuRunGAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuRunGAActionPerformed
        runGA();
    }//GEN-LAST:event_mnuRunGAActionPerformed

    private void btnAddResourceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddResourceActionPerformed
        
        int iSelectedRoom = this.lstRooms.getSelectedIndex();
        int iSelectedResource = this.lstAvailableRoomResources.getSelectedIndex();
        
        if ((iSelectedResource != -1) && (iSelectedRoom != -1)) {
        
            DefaultListModel<String> dlmAvailableRoomResources = (DefaultListModel<String>)lstAvailableRoomResources.getModel();

            String sNewResource = dlmAvailableRoomResources.get(iSelectedResource);
            dlmRoomCurrentResources.addElement(sNewResource);

            int iRoom = this.lstRooms.getSelectedIndex();
            database.alRooms.get(iRoom).alsResources.add(sNewResource);

            this.lstCurrentRoomResources.setModel(dlmRoomCurrentResources);
        }
    }//GEN-LAST:event_btnAddResourceActionPerformed

    private void btnModuleAddLTSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModuleAddLTSActionPerformed
        
        int iModuleSelected = lstModules.getSelectedIndex();
        int iSelectedSkill = lstModuleAvailableLTS.getSelectedIndex();
        
        if ((iSelectedSkill != -1) && (iModuleSelected != -1)) {
        
            DefaultListModel<String> dlmAvailableModuleLecSkills = (DefaultListModel<String>)lstModuleAvailableLTS.getModel();

            String sNewSkill = dlmAvailableModuleLecSkills.get(iSelectedSkill);
            dlmModuleLecSkills.addElement(sNewSkill);

            int iSelectedModule = lstModules.getSelectedIndex();
            database.alModules.get(iSelectedModule).alsLectureSkills.add(sNewSkill);

            this.lstModuleCurrentLTS.setModel(dlmModuleLecSkills);
        }
    }//GEN-LAST:event_btnModuleAddLTSActionPerformed

    private void btnModuleAddLRRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModuleAddLRRActionPerformed
        
        int iModuleSelected = lstModules.getSelectedIndex();
        int iSelectedResource = lstModuleAvailableLRR.getSelectedIndex();
        
        if ((iSelectedResource != -1) && (iModuleSelected != -1)) {

            DefaultListModel<String> dlmAvailableModuleLecResources = (DefaultListModel<String>)lstModuleAvailableLRR.getModel();

            String sNewResource = dlmAvailableModuleLecResources.get(iSelectedResource);
            dlmModuleLecResources.addElement(sNewResource);

            int iSelectedModule = lstModules.getSelectedIndex();
            database.alModules.get(iSelectedModule).alsLectureResources.add(sNewResource);

            this.lstModuleCurrentLRR.setModel(dlmModuleLecResources);
        }
    }//GEN-LAST:event_btnModuleAddLRRActionPerformed

    private void btnModuleAddPTSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModuleAddPTSActionPerformed

        int iModuleSelected = lstModules.getSelectedIndex();
        int iSelectedSkill = lstModuleAvailablePTS.getSelectedIndex();
        
        if ((iSelectedSkill != -1) && (iModuleSelected != -1)) {
        
            DefaultListModel<String> dlmAvailablePracticalLecSkills = (DefaultListModel<String>)lstModuleAvailablePTS.getModel();

            String sNewSkill = dlmAvailablePracticalLecSkills.get(iSelectedSkill);
            dlmModulePracSkills.addElement(sNewSkill);

            int iSelectedModule = lstModules.getSelectedIndex();
            database.alModules.get(iSelectedModule).alsPracticalSkills.add(sNewSkill);

            this.lstModuleCurrentPTS.setModel(dlmModulePracSkills);
        }
    }//GEN-LAST:event_btnModuleAddPTSActionPerformed

    private void btnModuleAddPRRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModuleAddPRRActionPerformed

        int iModuleSelected = lstModules.getSelectedIndex();
        int iSelectedResource = lstModuleAvailablePRR.getSelectedIndex();
        
        if ((iSelectedResource != -1) && (iModuleSelected != -1)) {
        
            DefaultListModel<String> dlmAvailableModulePracticalResources = (DefaultListModel<String>)lstModuleAvailablePRR.getModel();

            String sNewResource = dlmAvailableModulePracticalResources.get(iSelectedResource);
            dlmModulePracResources.addElement(sNewResource);

            int iSelectedModule = lstModules.getSelectedIndex();
            database.alModules.get(iSelectedModule).alsPracticalResources.add(sNewResource);

            this.lstModuleCurrentPRR.setModel(dlmModulePracResources);
        }
    }//GEN-LAST:event_btnModuleAddPRRActionPerformed

    private void btnModuleAddTTSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModuleAddTTSActionPerformed

        int iModuleSelected = lstModules.getSelectedIndex();
        int iSelectedSkill = lstModuleAvailableTTS.getSelectedIndex();
        
        if ((iSelectedSkill != -1) && (iModuleSelected != -1)) {
               
            DefaultListModel<String> dlmAvailableTutorialLecSkills = (DefaultListModel<String>)lstModuleAvailableTTS.getModel();

            String sNewSkill = dlmAvailableTutorialLecSkills.get(iSelectedSkill);
            dlmModuleTutSkills.addElement(sNewSkill);

            int iSelectedModule = lstModules.getSelectedIndex();
            database.alModules.get(iSelectedModule).alsTutorialSkills.add(sNewSkill);

            this.lstModuleCurrentTTS.setModel(dlmModuleTutSkills);
        }
    }//GEN-LAST:event_btnModuleAddTTSActionPerformed

    private void btnModuleAddTRRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModuleAddTRRActionPerformed
        
        int iModuleSelected = lstModules.getSelectedIndex();
        int iSelectedResource = lstModuleAvailableTRR.getSelectedIndex();

        if ((iSelectedResource != -1) && (iModuleSelected != -1)) {
        
            DefaultListModel<String> dlmAvailableModuleTutResources = (DefaultListModel<String>)lstModuleAvailableTRR.getModel();

            String sNewResource = dlmAvailableModuleTutResources.get(iSelectedResource);
            dlmModuleTutResources.addElement(sNewResource);

            int iSelectedModule = lstModules.getSelectedIndex();
            database.alModules.get(iSelectedModule).alsTutorialResources.add(sNewResource);

            this.lstModuleCurrentTRR.setModel(dlmModuleTutResources);
        }
    }//GEN-LAST:event_btnModuleAddTRRActionPerformed

    private void btnModuleRemoveLTSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModuleRemoveLTSActionPerformed
        
        int iSelectedSkill = lstModuleCurrentLTS.getSelectedIndex();

        if (iSelectedSkill != -1) {
        
            int iModule = this.lstModules.getSelectedIndex();
            database.alModules.get(iModule).alsLectureSkills.remove(iSelectedSkill);

            dlmModuleLecSkills.remove(iSelectedSkill);
            this.lstModuleCurrentLTS.setModel(dlmModuleLecSkills);
        }
    }//GEN-LAST:event_btnModuleRemoveLTSActionPerformed

    private void btnModuleRemoveLRRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModuleRemoveLRRActionPerformed
        
        int iSelectedResource = lstModuleCurrentLRR.getSelectedIndex();
        
        if (iSelectedResource != -1) {
        
            int iModule = this.lstModules.getSelectedIndex();
            database.alModules.get(iModule).alsLectureResources.remove(iSelectedResource);

            dlmModuleLecResources.remove(iSelectedResource);
            this.lstModuleCurrentLRR.setModel(dlmModuleLecResources);
        }
    }//GEN-LAST:event_btnModuleRemoveLRRActionPerformed

    private void btnModuleRemovePTSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModuleRemovePTSActionPerformed
        
        int iSelectedSkill = lstModuleCurrentPTS.getSelectedIndex();
        
        if (iSelectedSkill != -1) {
        
            int iModule = this.lstModules.getSelectedIndex();
            database.alModules.get(iModule).alsPracticalSkills.remove(iSelectedSkill);

            dlmModulePracSkills.remove(iSelectedSkill);
            this.lstModuleCurrentPTS.setModel(dlmModulePracSkills);
        }
    }//GEN-LAST:event_btnModuleRemovePTSActionPerformed

    private void btnModuleRemovePRRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModuleRemovePRRActionPerformed
        
        int iSelectedResource = lstModuleCurrentPRR.getSelectedIndex();
        
        if (iSelectedResource != -1) {
        
            int iModule = this.lstModules.getSelectedIndex();
            database.alModules.get(iModule).alsPracticalResources.remove(iSelectedResource);

            dlmModulePracResources.remove(iSelectedResource);
            this.lstModuleCurrentPRR.setModel(dlmModulePracResources);
        }
    }//GEN-LAST:event_btnModuleRemovePRRActionPerformed

    private void btnModuleRemoveTTSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModuleRemoveTTSActionPerformed
        
        int iSelectedSkill = lstModuleCurrentTTS.getSelectedIndex();

        if (iSelectedSkill != -1) {
        
            int iModule = this.lstModules.getSelectedIndex();
            database.alModules.get(iModule).alsTutorialSkills.remove(iSelectedSkill);

            dlmModuleTutSkills.remove(iSelectedSkill);
            this.lstModuleCurrentTTS.setModel(dlmModuleTutSkills);
        }
    }//GEN-LAST:event_btnModuleRemoveTTSActionPerformed

    private void btnModuleRemoveTRRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModuleRemoveTRRActionPerformed
        
        int iSelectedResource = lstModuleCurrentTRR.getSelectedIndex();

        if (iSelectedResource != -1) {
        
            int iModule = this.lstModules.getSelectedIndex();
            database.alModules.get(iModule).alsTutorialResources.remove(iSelectedResource);

            dlmModuleTutResources.remove(iSelectedResource);
            this.lstModuleCurrentTRR.setModel(dlmModuleTutResources);
        }
    }//GEN-LAST:event_btnModuleRemoveTRRActionPerformed

    private void btnRemoveLecturerSkillFromListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveLecturerSkillFromListActionPerformed
        
        int iSelectedSkill = lstCurrentLecturerSkills.getSelectedIndex();

        if (iSelectedSkill != -1) {
        
            int iLecturer = this.lstLecturers.getSelectedIndex();
            database.alLecturers.get(iLecturer).alsSkills.remove(iSelectedSkill);

            dlmLecturerCurrentSkills.remove(iSelectedSkill);
            this.lstCurrentLecturerSkills.setModel(dlmLecturerCurrentSkills);
        }
    }//GEN-LAST:event_btnRemoveLecturerSkillFromListActionPerformed

    private void btnRemoveResourceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveResourceActionPerformed
        
        int iSelectedResource = lstCurrentRoomResources.getSelectedIndex();

        if (iSelectedResource != -1) {
        
            int iRoom = this.lstRooms.getSelectedIndex();
            database.alRooms.get(iRoom).alsResources.remove(iSelectedResource);        

            dlmRoomCurrentResources.remove(iSelectedResource);
            this.lstCurrentRoomResources.setModel(dlmRoomCurrentResources);
        }
    }//GEN-LAST:event_btnRemoveResourceActionPerformed

    private void mnuLecturerSkillsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuLecturerSkillsActionPerformed
        dlgSkills.setVisible(true);
    }//GEN-LAST:event_mnuLecturerSkillsActionPerformed

    private void mnuResourcesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuResourcesActionPerformed
        dlgResources.setVisible(true);
    }//GEN-LAST:event_mnuResourcesActionPerformed

    private void mnuStudentMatrixActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuStudentMatrixActionPerformed
        dlgStudentTSMatrix.setVisible(true);
    }//GEN-LAST:event_mnuStudentMatrixActionPerformed

    private void mnuTimetablesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuTimetablesActionPerformed
        
        boolean bDisplayStudentsTab = true;
        updateTimetableDisplay("Students", bDisplayStudentsTab);
        
        dlgTimetables.setVisible(true);
    }//GEN-LAST:event_mnuTimetablesActionPerformed

    private void btnLecturerOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLecturerOKActionPerformed
        dlgLecturer.setVisible(false);
    }//GEN-LAST:event_btnLecturerOKActionPerformed

    private void btnRoomOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRoomOKActionPerformed
        dlgRoom.setVisible(false);
    }//GEN-LAST:event_btnRoomOKActionPerformed

    private void btnModuleOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModuleOKActionPerformed
        dlgModule.setVisible(false);
    }//GEN-LAST:event_btnModuleOKActionPerformed

    private void btnLecSkillsOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLecSkillsOKActionPerformed
        dlgSkills.setVisible(false);
    }//GEN-LAST:event_btnLecSkillsOKActionPerformed

    private void btnRoomResOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRoomResOKActionPerformed
        dlgResources.setVisible(false);
    }//GEN-LAST:event_btnRoomResOKActionPerformed

    private void btnStudentMatrixOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStudentMatrixOKActionPerformed
        
        //----------------------------------------------------------------------
        //Update the Student Timeslot Matrix database
        
        DefaultTableModel dtmTimeslotMatrix = (DefaultTableModel)tblStudentMatrix.getModel();
        
        for (int nDay = 0; nDay < iMaxDays; nDay++) {
            
            for (int nTime = 0; nTime < iDayLength; nTime++) {

                //The +1 is to take into account the day labels on the left of the table
                int iPreference = (int)dtmTimeslotMatrix.getValueAt(nDay, nTime + 1);
                
                database.a2diStudentTimeslotMatrix[nDay][nTime] = iPreference;
            }
        }
        
        //----------------------------------------------------------------------
        
        dlgStudentTSMatrix.setVisible(false);
    }//GEN-LAST:event_btnStudentMatrixOKActionPerformed

    private void btnTTOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTTOKActionPerformed
        dlgTimetables.setVisible(false);
    }//GEN-LAST:event_btnTTOKActionPerformed

    private void btnClearLecturerDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearLecturerDetailsActionPerformed
        
        dlmLecturerCurrentSkills.clear();
        lstCurrentLecturerSkills.setModel(dlmLecturerCurrentSkills);
        
        int iLecturer = this.lstLecturers.getSelectedIndex();
        database.alLecturers.get(iLecturer).alsSkills.clear();
    }//GEN-LAST:event_btnClearLecturerDetailsActionPerformed

    private void ckbModuleHasLecturesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckbModuleHasLecturesActionPerformed
        
        int iModule = this.lstModules.getSelectedIndex();
        
        database.alModules.get(iModule).bHasLectures = ckbModuleHasLectures.isSelected();
    }//GEN-LAST:event_ckbModuleHasLecturesActionPerformed

    private void ckbModuleHasPracticalsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckbModuleHasPracticalsActionPerformed
        
        int iModule = this.lstModules.getSelectedIndex();
        
        database.alModules.get(iModule).bHasPracticals = ckbModuleHasPracticals.isSelected();
    }//GEN-LAST:event_ckbModuleHasPracticalsActionPerformed

    private void ckbModuleHasTutorialsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckbModuleHasTutorialsActionPerformed
        
        int iModule = this.lstModules.getSelectedIndex();
        
        database.alModules.get(iModule).bHasTutorials = ckbModuleHasTutorials.isSelected();
    }//GEN-LAST:event_ckbModuleHasTutorialsActionPerformed

    private void btnClearModuleDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearModuleDetailsActionPerformed
        
        this.dlmModuleLecSkills.clear();
        this.dlmModuleLecResources.clear();
        this.dlmModulePracSkills.clear();
        this.dlmModulePracResources.clear();
        this.dlmModuleTutSkills.clear();
        this.dlmModuleTutResources.clear();
        
        lstModuleCurrentLTS.setModel(dlmModuleLecSkills);
        lstModuleCurrentLRR.setModel(dlmModuleLecResources);
        lstModuleCurrentPTS.setModel(dlmModulePracSkills);
        lstModuleCurrentPRR.setModel(dlmModulePracResources);
        lstModuleCurrentTTS.setModel(dlmModuleTutSkills);
        lstModuleCurrentTRR.setModel(dlmModuleTutResources);
        
        int iModule = this.lstModules.getSelectedIndex();
        
        database.alModules.get(iModule).alsLectureSkills.clear();
        database.alModules.get(iModule).alsLectureResources.clear();
        database.alModules.get(iModule).alsPracticalSkills.clear();
        database.alModules.get(iModule).alsPracticalResources.clear();
        database.alModules.get(iModule).alsTutorialSkills.clear();
        database.alModules.get(iModule).alsTutorialResources.clear();
    }//GEN-LAST:event_btnClearModuleDetailsActionPerformed

    private void btnClearRoomDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearRoomDetailsActionPerformed
        
        this.dlmRoomCurrentResources.clear();
        lstCurrentRoomResources.setModel(dlmRoomCurrentResources);
        
        int iRoom = this.lstRooms.getSelectedIndex();
        database.alRooms.get(iRoom).alsResources.clear();
    }//GEN-LAST:event_btnClearRoomDetailsActionPerformed

    private void btnDeleteRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteRoomActionPerformed
        
        int iSelectedRoom = lstRooms.getSelectedIndex();

        lstRooms.remove(iSelectedRoom);
        lstTTRooms.remove(iSelectedRoom);
        database.alRooms.remove(iSelectedRoom);
    }//GEN-LAST:event_btnDeleteRoomActionPerformed

    private void btnDeleteLecturerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteLecturerActionPerformed
        
        int iSelectedLecturer = lstLecturers.getSelectedIndex();

        lstLecturers.remove(iSelectedLecturer);
        lstTTStaff.remove(iSelectedLecturer);
        database.alLecturers.remove(iSelectedLecturer);
    }//GEN-LAST:event_btnDeleteLecturerActionPerformed

    private void btnDeleteLecturerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteLecturerMouseClicked
        //NULL
    }//GEN-LAST:event_btnDeleteLecturerMouseClicked

    private void btnAddNewModuleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNewModuleActionPerformed
        
        String sNewModule = JOptionPane.showInputDialog(null,"Enter new module name", "New Module", JOptionPane.QUESTION_MESSAGE);
               
        DefaultListModel<String> dlmModules = (DefaultListModel<String>)lstModules.getModel();       
        dlmModules.addElement(sNewModule);
        
        Module module = new Module( sNewModule );
        database.alModules.add(module);
        
        this.lstModules.setModel(dlmModules);
    }//GEN-LAST:event_btnAddNewModuleActionPerformed

    private void btnAddNewRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNewRoomActionPerformed
        
        String sNewRoom = JOptionPane.showInputDialog(null,"Enter new room name", "New Room", JOptionPane.QUESTION_MESSAGE);
               
        DefaultListModel<String> dlmRooms = (DefaultListModel<String>)lstRooms.getModel();       
        dlmRooms.addElement(sNewRoom);
        
        Room room = new Room( sNewRoom );
        database.alRooms.add(room);
        
        this.lstRooms.setModel(dlmRooms);
        this.lstTTRooms.setModel(dlmRooms);
    }//GEN-LAST:event_btnAddNewRoomActionPerformed

    private void btnAddLecturerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddLecturerActionPerformed
        
        String sNewLecturer = JOptionPane.showInputDialog(null,"Enter new lecturer name", "New Lecturer", JOptionPane.QUESTION_MESSAGE);
               
        DefaultListModel<String> dlmLecturers = (DefaultListModel<String>)lstLecturers.getModel();       
        dlmLecturers.addElement(sNewLecturer);
        
        Lecturer lecturer = new Lecturer( sNewLecturer );
        database.alLecturers.add(lecturer);
        
        this.lstLecturers.setModel(dlmLecturers);
        this.lstTTStaff.setModel(dlmLecturers);
    }//GEN-LAST:event_btnAddLecturerActionPerformed

    private void btnAddLecSkillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddLecSkillActionPerformed
        
        String sNewSkill = JOptionPane.showInputDialog(null,"Enter new lecturer skill", "New Skill", JOptionPane.QUESTION_MESSAGE);
        
        DefaultListModel<String> dlmSkills = (DefaultListModel<String>)lstLecturerSkills.getModel();       
        dlmSkills.addElement(sNewSkill);

        database.alsLecturerSkills.add(sNewSkill);
        
        this.lstLecturerSkills.setModel(dlmSkills);
    }//GEN-LAST:event_btnAddLecSkillActionPerformed

    private void btnDeleteLecSkillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteLecSkillActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeleteLecSkillActionPerformed

    private void btnAddRoomResActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddRoomResActionPerformed
        
        String sNewResource = JOptionPane.showInputDialog(null,"Enter new room resource", "New Resource", JOptionPane.QUESTION_MESSAGE);
               
        DefaultListModel<String> dlmResources = (DefaultListModel<String>)lstRoomResources.getModel();       
        dlmResources.addElement(sNewResource);
        
        database.alsRoomResources.add(sNewResource);
        
        this.lstRoomResources.setModel(dlmResources);
    }//GEN-LAST:event_btnAddRoomResActionPerformed

    private void btnDeleteRoomResActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteRoomResActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeleteRoomResActionPerformed

    private void mnuRunPreprocessingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuRunPreprocessingActionPerformed

        
        int iNumLecturers = database.alLecturers.size();
        //***debug***
        //iNumLecturers = 25;
        //iNumLecturers = 100;
        
        //Calculate number of classes and gather their skills and properties
        alSimpleClasses.clear();
        generateSimpleClassData();
        
        //Create the LC matrix
        int iNumClasses = alSimpleClasses.size();
        generateLCMatrix(alSimpleClasses, iNumLecturers, iNumClasses);
        
        //Create the RC matrix - ga.a2diPrefForRoom
        int iNumRooms = database.alRooms.size();
        generateRCMatrix(alSimpleClasses, iNumRooms, iNumClasses);
        
        //Update the Pre-processing form text box to show lecturer suitabilities
        String sSuitabilities = generateLecturePPWindowData(iNumLecturers, iNumClasses);
        
        //Update the Pre-processing form text box to show room suitabilities
        String sRoomSuitabilities = generateRoomPPWindowData(iNumRooms, iNumClasses);
        
        //======================================================================
        //Fill in a3diPrefForLecturers and a3diPrefForStudentGroups
        
        //Lecturer's timeslot matrices
        for (int nLecturer = 0; nLecturer < database.alLecturers.size(); nLecturer++) {
            
            for (int nDay = 0; nDay < iMaxDays; nDay++) {
                
                for (int nTime = 0; nTime < iDayLength; nTime++) {
                    
                    ga.a3diPrefForLecturers[nLecturer][nDay][nTime] = database.alLecturers.get(nLecturer).a2diTimeslotMatrix[nDay][nTime];
                }
            }
        }
        
        //Student timeslot matrix (all groups have the same matrix)
        for (int nStudentGroup = 0; nStudentGroup < database.alLecturers.size(); nStudentGroup++) {
            
            for (int nDay = 0; nDay < iMaxDays; nDay++) {
                
                for (int nTime = 0; nTime < iDayLength; nTime++) {
                    
                    ga.a3diPrefForLecturers[nStudentGroup][nDay][nTime] = database.a2diStudentTimeslotMatrix[nDay][nTime];
                }
            }
        } 
        
        //----------------------------------------------------------------------
        //Store the room seating capacities
        
        for (int nRoom = 0; nRoom < database.alRooms.size(); nRoom++) {
            
            ga.aiRoomSeatingCapacity[nRoom] = database.alRooms.get(nRoom).iCapacity;
        }
        
        //----------------------------------------------------------------------
        //Select suitable lecturers from the LC matrix at random
        //and store class details for the GA
        
        MersenneTwister mt = new MersenneTwister();
        
        for (int nClass = 0; nClass < iNumClasses; nClass++) {
            
            //Make a list of lecturer indexes who can teach the class
            ArrayList<Integer> aliRndLecturers = new ArrayList<Integer>();
            
            for (int nLecturer = 0; nLecturer < iNumLecturers; nLecturer++) {
                   
                if (database.a2dbLCSuitability[nLecturer][nClass] == true) {
                    
                    aliRndLecturers.add(nLecturer);
                }
            } 
            
            int iRndLecturer = mt.nextInt(aliRndLecturers.size());
            
            ga.aClasses[nClass].aiLecturer[0] = aliRndLecturers.get(iRndLecturer);
            ga.aClasses[nClass].iNumLecturers = 1;
            ga.aClasses[nClass].iDuration = alSimpleClasses.get(nClass).iDuration;
        }
        
        //------------------------------------------------------------------
        //Set up and assign student groups to classes
        
        int iStudentGroupIndex = 0;
        
        for (int nClass = 0; nClass < iNumClasses; nClass++) {
        
            boolean bIsLecture = alSimpleClasses.get(nClass).bIsLecture;
            boolean bIsPractical = alSimpleClasses.get(nClass).bIsPractical;
            boolean bIsTutorial = alSimpleClasses.get(nClass).bIsTutorial;
            
            int iCohortSize = alSimpleClasses.get(nClass).iSize;
            
            if ((bIsLecture == true) && (iCohortSize > 0)) {                
                
                iStudentGroupIndex = assignGroupsToClass(iCohortSize, iStudentGroupIndex, nClass);
            }
            if ((bIsPractical == true) && (iCohortSize > 0)) {
                
                iStudentGroupIndex = assignGroupsToClass(iCohortSize, iStudentGroupIndex, nClass);
            }
            if ((bIsTutorial == true) && (iCohortSize > 0)) {
                
                iStudentGroupIndex = assignGroupsToClass(iCohortSize, iStudentGroupIndex, nClass);
            }
        }  
        
        //======================================================================
        //Set the classes list in the timetables viewer with the correct data
        
        //For all classes...
        DefaultListModel<String> dlmClasses = new DefaultListModel<String>();
        for (int i = 0; i < alSimpleClasses.size(); i++) {
            
            String sClassName = alSimpleClasses.get(i).sName;
            
            boolean bIsLecture = alSimpleClasses.get(i).bIsLecture;
            boolean bIsPractical = alSimpleClasses.get(i).bIsPractical;
            boolean bIsTutorial = alSimpleClasses.get(i).bIsTutorial;

            String sActivityType = "";
            if (bIsLecture) { sActivityType = "Lecture - "; }
            if (bIsPractical) { sActivityType = "Practical - "; }
            if (bIsTutorial) { sActivityType = "Tutorial - "; }
            
            dlmClasses.addElement(sActivityType + sClassName);
        }
        this.lstTTStudents.setModel(dlmClasses);
        
        //======================================================================
        //Last actions for pre-processing
        
        this.txtPPList.setText(sSuitabilities);
        this.txtPPList.setCaretPosition(0);
        
        this.txtPPListRooms.setText(sRoomSuitabilities);
        this.txtPPListRooms.setCaretPosition(0);
        
        this.dlgPreprocessing.setVisible(true);
    }//GEN-LAST:event_mnuRunPreprocessingActionPerformed

    //**************************************************************************
    //Generate lecturer pre-processing data for the Pre-processing window
    
    private String generateLecturePPWindowData(int iNumLecturers, int iNumClasses) {
        
        String sSuitabilities = "";
        
        //Generate the data to be displayed in the pre-processing window
        for (int nLecturer = 0; nLecturer < iNumLecturers; nLecturer++) {
        
            System.out.println("nLecturer:" + nLecturer);
            
            String sLecturer = database.alLecturers.get(nLecturer).sName;
            sSuitabilities = sSuitabilities + sLecturer + "\n";
            
            for (int nClass = 0; nClass < iNumClasses; nClass++) {
                
                //Display the line only if the lecturer can take the class
                if (database.a2dbLCSuitability[nLecturer][nClass]) {
                
                    boolean bIsLecture = alSimpleClasses.get(nClass).bIsLecture;
                    boolean bIsPractical = alSimpleClasses.get(nClass).bIsPractical;
                    boolean bIsTutorial = alSimpleClasses.get(nClass).bIsTutorial;

                    if (bIsLecture) { sSuitabilities = sSuitabilities + "Lecture - "; }
                    if (bIsPractical) { sSuitabilities = sSuitabilities + "Practical - "; }
                    if (bIsTutorial) { sSuitabilities = sSuitabilities + "Tutorial - "; }
                    
                    String sClassName = alSimpleClasses.get(nClass).sName;
                    sSuitabilities = sSuitabilities + sClassName + "\n";
                }
            }
        }
        
        return sSuitabilities;
    }
    
    //--------------------------------------------------------------------------
    //Generate room pre-processing data for the Pre-processing window
    
    private String generateRoomPPWindowData(int iNumRooms, int iNumClasses) {
        
        String sRoomSuitabilities = "";
        
        //Generate the data to be displayed in the pre-processing window
        for (int nRoom = 0; nRoom < iNumRooms; nRoom++) {
        
            System.out.println("nRoom:" + nRoom);
            
            String sRoom = database.alRooms.get(nRoom).sID;
            sRoomSuitabilities = sRoomSuitabilities + sRoom + "\n";
            
            for (int nClass = 0; nClass < iNumClasses; nClass++) {
                
                //Display the line only if the room can take the class
                if (ga.a2diPrefForRoom[nRoom][nClass] == 1) {
                
                    boolean bIsLecture = alSimpleClasses.get(nClass).bIsLecture;
                    boolean bIsPractical = alSimpleClasses.get(nClass).bIsPractical;
                    boolean bIsTutorial = alSimpleClasses.get(nClass).bIsTutorial;

                    if (bIsLecture) { sRoomSuitabilities = sRoomSuitabilities + "Lecture - "; }
                    if (bIsPractical) { sRoomSuitabilities = sRoomSuitabilities + "Practical - "; }
                    if (bIsTutorial) { sRoomSuitabilities = sRoomSuitabilities + "Tutorial - "; }

                    String sClassName = alSimpleClasses.get(nClass).sName;
                    sRoomSuitabilities = sRoomSuitabilities + sClassName + "\n";
                }
            }
        }
        
        return sRoomSuitabilities;
    }
    
    //==========================================================================
    //Assign student groups to a class
    
    private int assignGroupsToClass( int iCohortSize, int iStudentGroupIndex , int nClass) {
        
        if (iCohortSize > iMaxGroupSize) {

            int iNumStudentGroups = (int)Math.ceil((double)iCohortSize / (double)iMaxGroupSize);
            ga.aClasses[nClass].iNumStudentGroups = iNumStudentGroups;

            for (int nGroup = 0; nGroup < iNumStudentGroups; nGroup++) {

                ga.aClasses[nClass].aiStudentGroup[nGroup] = iStudentGroupIndex;

                if ((nGroup < iNumStudentGroups - 1) && (iNumStudentGroups > 1)) {

                    ga.aiStudentGroupSize[nGroup] = iMaxGroupSize;
                } else {

                    ga.aiStudentGroupSize[nGroup] = iCohortSize % iMaxGroupSize;
                }

                iStudentGroupIndex++;
            }

        } else {

            ga.aClasses[nClass].aiStudentGroup[0] = iStudentGroupIndex;
            ga.aClasses[nClass].iNumStudentGroups = 1;
            ga.aiStudentGroupSize[iStudentGroupIndex] = iCohortSize % iMaxGroupSize;

            iStudentGroupIndex++;
        }
        
        return iStudentGroupIndex;
    }
    
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //Generate the RC matrix - ga.a2diPrefForRoom
    
    private void generateRCMatrix( ArrayList<SimpleClass> alSimpleClasses, int iNumRooms, int iNumClasses) {
            
        for (int nRoom = 0; nRoom < iNumRooms; nRoom++) {
        
            int iNumRoomResources = database.alRooms.get(nRoom).alsResources.size();
            
            for (int nClass = 0; nClass < iNumClasses; nClass++) {
                
                boolean bHasAllResources = true;
                
                int iNumClassResources = alSimpleClasses.get(nClass).alsResources.size();
                
                int iClassSize = alSimpleClasses.get(nClass).iSize;
                int iRoomCapacity = database.alRooms.get(nRoom).iCapacity;
                
                //If the room has >= num skills and the class can fit in the room, start the checking
                if ((iClassSize < iRoomCapacity) && (iNumRoomResources >= iNumClassResources)) {
                    
                    int iNumClassResourcesMatched = 0;
                    
                    for (int nRoomResource = 0; nRoomResource < iNumRoomResources; nRoomResource++) {

                        String sRoomResource = database.alRooms.get(nRoom).alsResources.get(nRoomResource);

                        if (iNumClassResourcesMatched < iNumClassResources) {

                            for (int nClassResource = 0; nClassResource < iNumClassResources; nClassResource++) {

                                String sClassResource = alSimpleClasses.get(nClass).alsResources.get(nClassResource);

                                //if the room has one of the skills to teach the class - break
                                if (sRoomResource.equals(sClassResource)) {

                                    iNumClassResourcesMatched++;
                                    break;
                                }
                            }
                        //If the room has matched the available class skills
                        } else {
                            
                            bHasAllResources = true;
                            break;
                        }
                    }
                    
                    if (iNumClassResourcesMatched < iNumClassResources) {
                        bHasAllResources = false;
                    }
                
                //If the room has < num skills required, then the room is unsuitable
                } else {
                    
                    bHasAllResources = false;
                }
                
                //System.out.println("nRoom:" + nRoom);
                //System.out.println("nClass:" + nClass);
                    
                //Set the appropriate integer in the RC matrix accordingly
                if (bHasAllResources) {
                    
                    ga.a2diPrefForRoom[nRoom][nClass] = 1;
                    
                } else {
                    
                    ga.a2diPrefForRoom[nRoom][nClass] = 0;
                }
            }
        }
    }
    
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //Generate the LC matrix - a2dbLCSuitability[L][C]
    
    private void generateLCMatrix( ArrayList<SimpleClass> alSimpleClasses, int iNumLecturers, int iNumClasses ) {
        
        for (int nLecturer = 0; nLecturer < iNumLecturers; nLecturer++) {
        
            int iNumLecturerSkills = database.alLecturers.get(nLecturer).alsSkills.size();
            
            for (int nClass = 0; nClass < iNumClasses; nClass++) {
                
                boolean bHasAllSkills = true;
                
                int iNumClassSkills = alSimpleClasses.get(nClass).alsSkills.size();
                
                //If the lecturer has >= num skills, start the checking
                if (iNumLecturerSkills >= iNumClassSkills) {
                    
                    int iNumClassSkillsMatched = 0;
                    
                    for (int nLecturerSkill = 0; nLecturerSkill < iNumLecturerSkills; nLecturerSkill++) {

                        String sLecturerSkill = database.alLecturers.get(nLecturer).alsSkills.get(nLecturerSkill);

                        if (iNumClassSkillsMatched < iNumClassSkills) {

                            for (int nClassSkill = 0; nClassSkill < iNumClassSkills; nClassSkill++) {

                                String sClassSkill = alSimpleClasses.get(nClass).alsSkills.get(nClassSkill);

                                //if the lecturer has one of the skills to teach the class - break
                                if (sLecturerSkill.equals(sClassSkill)) {

                                    iNumClassSkillsMatched++;
                                    break;
                                }
                            }
                        //If the lecturer has matched the available class skills
                        } else {
                            
                            bHasAllSkills = true;
                            break;
                        }
                    }
                    
                    if (iNumClassSkillsMatched < iNumClassSkills) {
                        bHasAllSkills = false;
                    }
                
                //If the lecturer has < num skills required, then the lecturer is unsuitable
                } else {
                    
                    bHasAllSkills = false;
                }
                
                //System.out.println("nLecturer:" + nLecturer);
                //System.out.println("nClass:" + nClass);
                    
                //Set the appropriate boolean in the LC matrix accordingly
                if (bHasAllSkills) {
                    
                    database.a2dbLCSuitability[nLecturer][nClass] = true;
                    
                } else {
                    
                    database.a2dbLCSuitability[nLecturer][nClass] = false;
                }
            }
        }
    }
    
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //Gather data together for the LC ansd RC matrices
    
    private void generateSimpleClassData() {
        
        int iNumModules = database.alModules.size();
        
        //For all modules...
        for (int nModule = 0; nModule < iNumModules; nModule++) {
            
            String sModuleName = database.alModules.get(nModule).sName;
            int iModuleSize = database.alModules.get(nModule).iSize;
            
            boolean bHasLectures = database.alModules.get(nModule).bHasLectures;
            boolean bHasPracticals = database.alModules.get(nModule).bHasPracticals;
            boolean bHasTutorials = database.alModules.get(nModule).bHasTutorials;
            
            //Determine the number of groups of 20 students
            int iNumGroups = (int)(Math.ceil((double)iModuleSize / (double)iMaxGroupSize));
            int iGroupSizeRemainder = iModuleSize % iMaxGroupSize;

            //Lectures
            if (bHasLectures) {
                
                SimpleClass scLecture = new SimpleClass(sModuleName, iModuleSize);
                scLecture.bIsLecture = true;
                scLecture.alsSkills = (ArrayList<String>)database.alModules.get(nModule).alsLectureSkills.clone();
                scLecture.alsResources = (ArrayList<String>)database.alModules.get(nModule).alsLectureResources.clone();
                scLecture.iDuration = database.alModules.get(nModule).iLectureDuration;
                alSimpleClasses.add(scLecture);
            }
            
            //Practicals
            if (bHasPracticals) {
                
                int iPracticalSize;
                
                for (int nPractical = 0; nPractical < iNumGroups; nPractical++) {

                    if (nPractical < iNumGroups - 1) {
                        
                        iPracticalSize = iMaxGroupSize;
                    } else {
                        
                        iPracticalSize = iGroupSizeRemainder;
                    }
                    SimpleClass scPractical = new SimpleClass(sModuleName, iPracticalSize);
                    scPractical.bIsPractical = true;
                    scPractical.alsSkills = (ArrayList<String>)database.alModules.get(nModule).alsPracticalSkills.clone();
                    scPractical.alsResources = (ArrayList<String>)database.alModules.get(nModule).alsPracticalResources.clone();
                    scPractical.iDuration = database.alModules.get(nModule).iPracticalDuration;
                    alSimpleClasses.add(scPractical);
                }
            }
            
            //Tutorials
            if (bHasTutorials) {
                
                for (int nTutorial = 0; nTutorial < iNumGroups; nTutorial++) {

                    int iTutorialSize;
                    
                    if (nTutorial < iNumGroups - 1) {
                        
                        iTutorialSize = iMaxGroupSize;
                    } else {
                        
                        iTutorialSize = iGroupSizeRemainder;
                    }
                    SimpleClass scTutorial = new SimpleClass(sModuleName, iTutorialSize);
                    scTutorial.bIsTutorial = true;
                    scTutorial.alsSkills = (ArrayList<String>)database.alModules.get(nModule).alsTutorialSkills.clone();
                    scTutorial.alsResources = (ArrayList<String>)database.alModules.get(nModule).alsTutorialResources.clone();
                    scTutorial.iDuration = database.alModules.get(nModule).iTutorialDuration;
                    alSimpleClasses.add(scTutorial);
                }
            }
        }
    }
    
    private void btnPPOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPPOKActionPerformed
        
        this.dlgPreprocessing.setVisible(false);
    }//GEN-LAST:event_btnPPOKActionPerformed

    private void mnuViewPPDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuViewPPDataActionPerformed
        
        this.dlgPreprocessing.setVisible(true);
    }//GEN-LAST:event_mnuViewPPDataActionPerformed

    private void spnRoomCapacityPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_spnRoomCapacityPropertyChange
        //Null
    }//GEN-LAST:event_spnRoomCapacityPropertyChange

    private void spnRoomCapacityStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnRoomCapacityStateChanged
        
        int iRoom = this.lstRooms.getSelectedIndex();
        database.alRooms.get(iRoom).iCapacity = (int)spnRoomCapacity.getValue();
    }//GEN-LAST:event_spnRoomCapacityStateChanged

    private void spnModuleSizeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnModuleSizeStateChanged
        
        int iModule = this.lstModules.getSelectedIndex();
        database.alModules.get(iModule).iSize = (int)spnModuleSize.getValue();
    }//GEN-LAST:event_spnModuleSizeStateChanged

    private void spnLectureDurationStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnLectureDurationStateChanged
        
        int iModule = this.lstModules.getSelectedIndex();
        database.alModules.get(iModule).iLectureDuration = (int)spnLectureDuration.getValue();
    }//GEN-LAST:event_spnLectureDurationStateChanged

    private void spnPracticalDurationStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnPracticalDurationStateChanged
        
        int iModule = this.lstModules.getSelectedIndex();
        database.alModules.get(iModule).iPracticalDuration = (int)spnPracticalDuration.getValue();
    }//GEN-LAST:event_spnPracticalDurationStateChanged

    private void spnTutorialDurationStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnTutorialDurationStateChanged
        
        int iModule = this.lstModules.getSelectedIndex();
        database.alModules.get(iModule).iTutorialDuration = (int)spnTutorialDuration.getValue();
    }//GEN-LAST:event_spnTutorialDurationStateChanged

    //----------------------------------------------------------------------
    //Save the timeslot matrix table for the lecturer
    
    private void btnUpdateTimeslotMatrixActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateTimeslotMatrixActionPerformed
        
        int iLecturer = lstLecturers.getSelectedIndex();
        
        DefaultTableModel dtmTimeslotMatrix = (DefaultTableModel)tblTimeslotMatrix.getModel();
        
        for (int nDay = 0; nDay < iMaxDays; nDay++) {
            
            for (int nTime = 0; nTime < iDayLength; nTime++) {

                //The +1 is to take into account the day labels on the left of the table
                int iPreference = (int)dtmTimeslotMatrix.getValueAt(nDay, nTime + 1);
                
                database.alLecturers.get(iLecturer).a2diTimeslotMatrix[nDay][nTime] = iPreference;
            }
        }
    }//GEN-LAST:event_btnUpdateTimeslotMatrixActionPerformed

    private void tpTimetableStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tpTimetableStateChanged
        
        //Null
    }//GEN-LAST:event_tpTimetableStateChanged

    private void lstTTRoomsValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstTTRoomsValueChanged
        
        updateTimetableDisplay( "Rooms" , false);
    }//GEN-LAST:event_lstTTRoomsValueChanged

    private void lstTTStaffValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstTTStaffValueChanged
        
        updateTimetableDisplay( "Staff" , false);
    }//GEN-LAST:event_lstTTStaffValueChanged

    private void lstTTStudentsValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstTTStudentsValueChanged
        
        updateTimetableDisplay( "Students" , false);
    }//GEN-LAST:event_lstTTStudentsValueChanged

    private void spnModuleSizeAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_spnModuleSizeAncestorAdded
        // Null
    }//GEN-LAST:event_spnModuleSizeAncestorAdded

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddLecSkill;
    private javax.swing.JButton btnAddLecturer;
    private javax.swing.JButton btnAddLecturerSkillToList;
    private javax.swing.JButton btnAddNewModule;
    private javax.swing.JButton btnAddNewRoom;
    private javax.swing.JButton btnAddResource;
    private javax.swing.JButton btnAddRoomRes;
    private javax.swing.JButton btnClearLecturerDetails;
    private javax.swing.JButton btnClearModuleDetails;
    private javax.swing.JButton btnClearRoomDetails;
    private javax.swing.JButton btnDeleteLecSkill;
    private javax.swing.JButton btnDeleteLecturer;
    private javax.swing.JButton btnDeleteModule;
    private javax.swing.JButton btnDeleteRoom;
    private javax.swing.JButton btnDeleteRoomRes;
    private javax.swing.JButton btnLecSkillsOK;
    private javax.swing.JButton btnLecturerOK;
    private javax.swing.JButton btnModuleAddLRR;
    private javax.swing.JButton btnModuleAddLTS;
    private javax.swing.JButton btnModuleAddPRR;
    private javax.swing.JButton btnModuleAddPTS;
    private javax.swing.JButton btnModuleAddTRR;
    private javax.swing.JButton btnModuleAddTTS;
    private javax.swing.JButton btnModuleOK;
    private javax.swing.JButton btnModuleRemoveLRR;
    private javax.swing.JButton btnModuleRemoveLTS;
    private javax.swing.JButton btnModuleRemovePRR;
    private javax.swing.JButton btnModuleRemovePTS;
    private javax.swing.JButton btnModuleRemoveTRR;
    private javax.swing.JButton btnModuleRemoveTTS;
    private javax.swing.JButton btnPPOK;
    private javax.swing.JButton btnRemoveLecturerSkillFromList;
    private javax.swing.JButton btnRemoveResource;
    private javax.swing.JButton btnRoomOK;
    private javax.swing.JButton btnRoomResOK;
    private javax.swing.JButton btnStudentMatrixOK;
    private javax.swing.JButton btnTTOK;
    private javax.swing.JButton btnUpdateTimeslotMatrix;
    private javax.swing.JCheckBox ckbModuleHasLectures;
    private javax.swing.JCheckBox ckbModuleHasPracticals;
    private javax.swing.JCheckBox ckbModuleHasTutorials;
    private javax.swing.JComboBox<String> cmbLecturePreferredRoom1;
    private javax.swing.JComboBox<String> cmbLecturePreferredRoom2;
    private javax.swing.JComboBox<String> cmbLecturePreferredRoom3;
    private javax.swing.JComboBox<String> cmbLecturePreferredRoom4;
    private javax.swing.JComboBox<String> cmbPracticalPreferredRoom1;
    private javax.swing.JComboBox<String> cmbPracticalPreferredRoom2;
    private javax.swing.JComboBox<String> cmbPracticalPreferredRoom3;
    private javax.swing.JComboBox<String> cmbPracticalPreferredRoom4;
    private javax.swing.JComboBox<String> cmbTutorialPreferredRoom1;
    private javax.swing.JComboBox<String> cmbTutorialPreferredRoom2;
    private javax.swing.JComboBox<String> cmbTutorialPreferredRoom3;
    private javax.swing.JComboBox<String> cmbTutorialPreferredRoom4;
    private javax.swing.JDialog dlgLecturer;
    private javax.swing.JDialog dlgModule;
    private javax.swing.JDialog dlgPreprocessing;
    private javax.swing.JDialog dlgResources;
    private javax.swing.JDialog dlgRoom;
    private javax.swing.JDialog dlgSkills;
    private javax.swing.JDialog dlgStudentTSMatrix;
    private javax.swing.JDialog dlgTimetables;
    private javax.swing.JMenu edit;
    private javax.swing.JMenu file;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane24;
    private javax.swing.JScrollPane jScrollPane25;
    private javax.swing.JScrollPane jScrollPane26;
    private javax.swing.JScrollPane jScrollPane27;
    private javax.swing.JScrollPane jScrollPane28;
    private javax.swing.JScrollPane jScrollPane29;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JLabel lblAvailableLecturerSkills;
    private javax.swing.JLabel lblCurrentSkills;
    private javax.swing.JList<String> lstAvailableLecturerSkills;
    private javax.swing.JList<String> lstAvailableRoomResources;
    private javax.swing.JList<String> lstCurrentLecturerSkills;
    private javax.swing.JList<String> lstCurrentRoomResources;
    private javax.swing.JList<String> lstLecturerSkills;
    private javax.swing.JList<String> lstLecturers;
    private javax.swing.JList<String> lstModuleAvailableLRR;
    private javax.swing.JList<String> lstModuleAvailableLTS;
    private javax.swing.JList<String> lstModuleAvailablePRR;
    private javax.swing.JList<String> lstModuleAvailablePTS;
    private javax.swing.JList<String> lstModuleAvailableTRR;
    private javax.swing.JList<String> lstModuleAvailableTTS;
    private javax.swing.JList<String> lstModuleCurrentLRR;
    private javax.swing.JList<String> lstModuleCurrentLTS;
    private javax.swing.JList<String> lstModuleCurrentPRR;
    private javax.swing.JList<String> lstModuleCurrentPTS;
    private javax.swing.JList<String> lstModuleCurrentTRR;
    private javax.swing.JList<String> lstModuleCurrentTTS;
    private javax.swing.JList<String> lstModules;
    private javax.swing.JList<String> lstRoomResources;
    private javax.swing.JList<String> lstRooms;
    private javax.swing.JList<String> lstTTRooms;
    private javax.swing.JList<String> lstTTStaff;
    private javax.swing.JList<String> lstTTStudents;
    private javax.swing.JMenuBar mainMenuBar;
    private javax.swing.JMenuItem mnuLecturerSkills;
    private javax.swing.JMenuItem mnuLecturers;
    private javax.swing.JMenuItem mnuModules;
    private javax.swing.JMenuItem mnuQuit;
    private javax.swing.JMenuItem mnuResources;
    private javax.swing.JMenuItem mnuRooms;
    private javax.swing.JMenuItem mnuRunGA;
    private javax.swing.JMenuItem mnuRunPreprocessing;
    private javax.swing.JMenuItem mnuStudentMatrix;
    private javax.swing.JMenuItem mnuTimetables;
    private javax.swing.JMenuItem mnuViewPPData;
    private javax.swing.JPanel pnlLecturerSkills;
    private javax.swing.JPanel pnlLectures;
    private javax.swing.JPanel pnlPracticals;
    private javax.swing.JPanel pnlTutorials;
    private javax.swing.JSpinner spnLectureDuration;
    private javax.swing.JSpinner spnModuleSize;
    private javax.swing.JSpinner spnPracticalDuration;
    private javax.swing.JSpinner spnRoomCapacity;
    private javax.swing.JSpinner spnTutorialDuration;
    private javax.swing.JTable tblStudentMatrix;
    private javax.swing.JTable tblTimeslotMatrix;
    private javax.swing.JTabbedPane tpTimetable;
    private javax.swing.JTextArea txtPPList;
    private javax.swing.JTextArea txtPPListRooms;
    private javax.swing.JTextArea txtTimetable;
    private javax.swing.JMenu view;
    // End of variables declaration//GEN-END:variables
}
