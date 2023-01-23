import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class WestminsterSkinConsultationManagerGUI implements SkinConsultationManagerGUI {
    private JFrame menu = new JFrame("Menu");
    private ArrayList<Doctor> doctorsList = new ArrayList<>();
    private JPanel panel = new JPanel();// mainPanel
    private JPanel suBaPanel = new JPanel();// submit and back buttons.
    private BoxLayout boxL = new BoxLayout(panel, BoxLayout.Y_AXIS);

    private JFrame notesWindow = new JFrame("Notes");
    private JPanel notesPanel = new JPanel();
    private JPanel notesSuBaPanel = new JPanel();
    private BoxLayout boxN = new BoxLayout(notesPanel, BoxLayout.Y_AXIS);

    private JFrame imageWindow = new JFrame("Image");
    private JPanel imagePanel = new JPanel();
    private JPanel imageSuBaPanel = new JPanel();
    private BoxLayout boxI = new BoxLayout(imagePanel, BoxLayout.Y_AXIS);

    private JLabel[] createLb = {
            new JLabel("Name: "), new JLabel("Surname: "), new JLabel("DOB: "),
            new JLabel("Phone No: "),
            new JLabel("Medical License No: "), new JLabel("Specialisation: ")
    };
    private ArrayList<ArrayList<String>> schedule = new ArrayList<>();
    private String path = "";

    public byte[] encrypt(String x) throws IOException {
        return Base64.getEncoder().encode(x.getBytes(StandardCharsets.UTF_8));
    }

    public byte[] decrypt(byte[] x) {
        return Base64.getDecoder().decode(x);
    }

    public boolean duplicate(String mLN){
        for(Doctor d: doctorsList){
            if(d.getMedicalLicenseNo().equals(mLN)){
                return true;
            }
        }
        return false;
    }

    public void createDoctor(String name, String surname, LocalDate dOB, String mobileNo, String mLN,
            String specialisation, ArrayList<Doctor> doctorsList) {
        Doctor d = new Doctor(name, surname, dOB, mobileNo, mLN, specialisation);
        if(doctorsList.size() < 10 && !duplicate(mLN)) {
            doctorsList.add(d);
        } else {
            JOptionPane.showMessageDialog(null, "No more space, or duplicate doctor", "Cannot add more to list", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void defB(JPanel panel) {
        panel.setLayout(boxL);
        panel.setBorder(new EmptyBorder(new Insets(50, 80, 50, 80)));
        menu.add(panel);
        menu.pack();
        menu.setVisible(true);
        menu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void defNotes(JPanel notesPanel){
        notesPanel.setLayout(boxN);
        notesPanel.setBorder(new EmptyBorder(new Insets(50, 80, 50, 80)));
        notesWindow.pack();
        notesWindow.setVisible(true);
        notesWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    private void defImages(JPanel imagePanel){
        imagePanel.setLayout(boxI);
        imagePanel.setBorder(new EmptyBorder(new Insets(50, 80, 50, 80)));
        imageWindow.pack();
        imageWindow.setVisible(true);
        imageWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    public void deleteDoctor(ArrayList<Doctor> doctorsList, String mLN) {
        for (Doctor d : doctorsList) {
            if (d.getMedicalLicenseNo().equals(mLN))
            JOptionPane.showMessageDialog(null, "Name: "+d.getName()+" mLN: "+d.getMedicalLicenseNo()+" has been deleted.",
                                        "DELETED DOCTOR", JOptionPane.ERROR_MESSAGE);
                doctorsList.remove(d);
        }
    }

    public void printDoctors(ArrayList<Doctor> doctorsList) {

        panel.removeAll();

        JTextArea a = new JTextArea(16, 35);
        a.setEditable(false);
        JScrollPane scroll = new JScrollPane(a);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        for (Doctor d : doctorsList) {
            a.append(createLb[0].getText() + d.getName() + "\n" + createLb[1].getText() + d.getSurname() + "\n"
                    + createLb[2].getText() + d.getDOB() + "\n" + createLb[3].getText() + d.getMobileNo() + "\n"
                    + createLb[4].getText() + d.getMedicalLicenseNo() + "\n" + createLb[5].getText()
                    + d.getSpecialisation() + "\n\n");
        }
        panel.add(scroll);

        JButton back = new JButton("Back");
        suBaPanel.add(back);
        back.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                mainMenu();

            }

        });
        suBaPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(suBaPanel);
        defB(panel);
    }

    public void create() {
        panel.removeAll();
        JTextField[] createTF = {
                new JTextField(), new JTextField(), new JTextField(), new JTextField(),
                new JTextField(),
                new JTextField()
        };
        JLabel[] createLb = {
                new JLabel("Name: "), new JLabel("Surname: "), new JLabel("DOB: "),
                new JLabel("Phone No: "),
                new JLabel("Medical License No: "), new JLabel("Specialisation: ")
        };
        for (int i = 0; i < createLb.length; i++) {
            panel.add(createLb[i]);
            panel.add(createTF[i]);
            panel.add(Box.createRigidArea(new Dimension(0, 5)));
        }

        JButton submit = new JButton("Submit");
        JButton back = new JButton("Back");
        suBaPanel.add(submit);
        suBaPanel.add(back);
        submit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    createDoctor(createTF[0].getText(), createTF[1].getText(), LocalDate.parse(createTF[2].getText()),
                            createTF[3].getText(), createTF[4].getText(), createTF[5].getText(), doctorsList);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Write date YYYY-MM-DD",
                                        "INCORRECT DATE FORMAT", JOptionPane.ERROR_MESSAGE);
                }

            }

        });
        back.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                mainMenu();

            }

        });
        suBaPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(suBaPanel);

        defB(panel);
    }

    public void save(ArrayList<Doctor> doctorsList) {
        try {
            File file = new File("doctors.txt");
            if (file.createNewFile())
                System.out.println("File created: " + file.getName());
            else
                System.out.println("File already exists.");
            FileWriter writer = new FileWriter(file);
            for (Doctor d : doctorsList) {
                writer.append(createLb[0].getText() + d.getName() + "\n" + createLb[1].getText() + d.getSurname() + "\n"
                        + createLb[2].getText() + d.getDOB() + "\n" + createLb[3].getText() + d.getMobileNo() + "\n"
                        + createLb[4].getText() + d.getMedicalLicenseNo() + "\n" + createLb[5].getText()
                        + d.getSpecialisation() + "\n\n");

            }
            System.out.println("Successfully wrote to the file.");
            writer.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "WRITE ERROR", "WRITE ERROR", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

    }

    private void retrieve() {
        try {
            File file = new File("doctors.txt");
            Scanner scanner = new Scanner(file);
            int i = 0;
            String n = "";
            String sN = "";
            String date = "";
            String pN = "";
            String mLN = "";
            String s = "";
            if (file.exists())
                while (scanner.hasNext()) {
                    String t = scanner.nextLine();
                    t = t.replaceAll(".+: ", "");
                    if (t.isEmpty() || t.isBlank())
                        continue;
                    if (i == 0)
                        n = t;
                    else if (i == 1)
                        sN = t;
                    else if (i == 2)
                        date = t;
                    else if (i == 3)
                        pN = t;
                    else if (i == 4)
                        mLN = t;
                    else if (i == 5)
                        s = t;
                    i++;
                    if (i == 6) {
                        i = 0;
                        createDoctor(n, sN, LocalDate.parse(date), pN, mLN, s, doctorsList);
                    }
                }
            scanner.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "RETRIEVE ERROR.", "RETRIEVE ERROR", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

    }

    public void delete() {
        panel.removeAll();
        panel.add(new JLabel("Medical License No: "));
        JTextField mLN = new JTextField();
        panel.add(mLN);
        JButton delete = new JButton("Delete");
        JButton back = new JButton("Back");
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteDoctor(doctorsList, mLN.getText());
            }
        });
        back.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                mainMenu();

            }

        });
        suBaPanel.removeAll();
        suBaPanel.add(delete);
        suBaPanel.add(back);
        panel.add(suBaPanel);
        suBaPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        defB(panel);
    }

    private void notes() {
        notesPanel.removeAll();
        notesSuBaPanel.removeAll();  
        JLabel passwordLB = new JLabel("Enter password: ");
        JTextField password = new JTextField();
        JButton login = new JButton("Login");
        JButton back = new JButton("Back");
        JLabel image = new JLabel();
        back.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                notesPanel.removeAll();
                notesSuBaPanel.removeAll();
                notesWindow.dispose();
            }

        });
        login.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(password.getText().equals("12345")){
                    notesPanel.removeAll();
                    notesSuBaPanel.removeAll();
                    for(ArrayList a: schedule){
                        if(a.get(1) != null){
                            JButton btn = new JButton(a.get(1)+" Notes");
                            notesPanel.add(btn);
                            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
                            notesPanel.add(Box.createRigidArea(new Dimension(0, 5)));

                            btn.addActionListener(new ActionListener(){

                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    imagePanel.removeAll();
                                    if(a.get(4).toString().contains("image")){
                                        image.setIcon(new ImageIcon(new String(decrypt((byte[]) a.get(5)))));
                                        imagePanel.add(image);
                                        image.setAlignmentX(Component.CENTER_ALIGNMENT);
                                        imageWindow.add(imagePanel);
                                        if(a.get(6).toString().length() > 0){
                                        JTextField JTF = new JTextField(a.get(6).toString());
                                        JTF.setEditable(false);
                                        imagePanel.add(JTF);
                                        JTF.setAlignmentX(Component.CENTER_ALIGNMENT);
                                        imageWindow.add(imagePanel);
                                    }
                                    }else{
                                        JTextField JTF = new JTextField(a.get(4).toString());
                                        JTF.setEditable(false);
                                        imagePanel.add(JTF);
                                        JTF.setAlignmentX(Component.CENTER_ALIGNMENT);
                                        imageWindow.add(imagePanel);
                                    }
                                    defImages(imagePanel);
                                }
                                
                            });   
                        }
                    }
                    notesSuBaPanel.add(back);
                    notesPanel.add(notesSuBaPanel);
                    notesSuBaPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
                    defNotes(notesPanel);
                }
            }

        });
        notesPanel.add(passwordLB);
        passwordLB.setAlignmentX(Component.CENTER_ALIGNMENT);
        notesPanel.add(password);
        notesWindow.add(notesPanel);
        notesSuBaPanel.add(login);
        notesSuBaPanel.add(back);
        notesPanel.add(notesSuBaPanel);
        notesSuBaPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        defNotes(notesPanel);
    }

    private void consultation() {
        panel.removeAll();
        suBaPanel.removeAll();
        JLabel[] conLBs = { new JLabel("Patient Name"), new JLabel("Patient Surname"),
                new JLabel("Patient Date of Birth"), new JLabel("Patient Mobile No"),
                new JLabel("Patient Identification"), new JLabel("Date"), new JLabel("Time"),
                new JLabel("Duration enter minutes"), new JLabel("Notes"),
                new JLabel("Doctor Medical License No") };

        JTextField[] conTFs = { new JTextField(), new JTextField(), new JTextField(), new JTextField(),
                new JTextField(), new JTextField(), new JTextField(), new JTextField(),
                new JTextField(), new JTextField() };

        JButton back = new JButton("Back");
        JButton submit = new JButton("Submit");
        JButton notes = new JButton("Notes");
        JFileChooser selector = new JFileChooser();
        selector.setCurrentDirectory(new File("."));
        JButton open = new JButton("Upload Image");

        JTextArea sc = new JTextArea(16, 35);
        JScrollPane scroll = new JScrollPane(sc);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainMenu();
            }
        });
        notes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                notes();
            }
        });

        open.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int result = selector.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    path = selector.getSelectedFile().getPath();
                }
            }
        });
        for (int i = 0; i < conLBs.length; i++) {
            panel.add(conLBs[i]);
            panel.add(conTFs[i]);
            conLBs[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            conTFs[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(Box.createRigidArea(new Dimension(0, 5)));
        }
        suBaPanel.add(new JLabel("First consultation: "));
        JCheckBox first = new JCheckBox();
        suBaPanel.add(first);
        suBaPanel.add(submit);
        suBaPanel.add(back);
        suBaPanel.add(notes);
        suBaPanel.add(open);
        panel.add(suBaPanel);
        suBaPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(scroll);
        defB(panel);

        if (schedule.size() == 0) {
            int ii = 0;
            // the i here represents the total slots for all doctors.
            for (int i = 1; i < ((doctorsList.size() * 7) + 1); i++) {
                schedule.add(new ArrayList<>(
                        Arrays.asList(doctorsList.get(ii).getMedicalLicenseNo(), null, null)));
                // the i % 7 can be changed to i % x, this represents the total slots per
                // doctor.
                if (i % 7 == 0) {
                    ii++;
                }
            }
        }

        for (ArrayList s : schedule) {
            sc.append("Medical License No: " + s.get(0) + " Patient: " + s.get(1) + " Slot: " + s.get(2) + "\n");
        }

        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Long cost = (Long.parseLong(conTFs[7].getText()) / 60);
                sc.setText(null);
                LocalDate pDate = null;
                LocalDateTime cDate = null;
                try {
                    pDate = LocalDate.parse(conTFs[2].getText());
                    cDate = LocalDateTime
                            .parse(conTFs[5].getText() + "T" + conTFs[6].getText());
                    Patient p = new Patient(conTFs[0].getText(), conTFs[1].getText(), pDate, conTFs[3].getText(),
                            conTFs[4].getText());

                    if (first.isSelected()) {
                        cost *= 15;
                    } else {
                        cost *= 25;
                    }

                    Consultation c = new Consultation(cDate, cost, conTFs[8].getText());

                    for (ArrayList ar : schedule) {
                        try {
                            if (ar.get(0).equals(conTFs[9].getText()) && ar.get(1).equals(p.getPatientID())
                                    && ar.get(2).equals(c.getDandTSlot())) {
                                JOptionPane.showMessageDialog(null, "ALLOCATION EXISTS ALREADY.",
                                        "DUPLICATE ERROR", JOptionPane.ERROR_MESSAGE);
                                break;
                            }
                            if (ar.get(0).equals(conTFs[9].getText()) && !ar.get(1).equals(p.getPatientID())
                                    && ar.get(2).equals(c.getDandTSlot())) {
                                JOptionPane.showMessageDialog(null,
                                        "Allocating to another doctor, press \"submit\" again",
                                        "DUPLICATE ERROR", JOptionPane.ERROR_MESSAGE);
                                int rand = Integer
                                        .parseInt(doctorsList.get(new Random().nextInt(doctorsList.size()))
                                                .getMedicalLicenseNo());
                                if (rand == Integer.parseInt(conTFs[9].getText())) {
                                    while (rand == Integer.parseInt(conTFs[9].getText())) {
                                        rand = Integer.parseInt(
                                                doctorsList.get(new Random().nextInt(doctorsList.size()))
                                                        .getMedicalLicenseNo());
                                    }
                                    conTFs[9].setText(new String().valueOf(rand));
                                    break;
                                } else {
                                    conTFs[9].setText(new String().valueOf(rand));
                                    break;
                                }
                            }
                            if (!ar.get(0).equals(conTFs[9].getText()) && ar.get(1).equals(p.getPatientID())
                                    && ar.get(2).equals(c.getDandTSlot())) {
                                JOptionPane.showMessageDialog(null, "Patient already has a slot allocated",
                                        "DUPLICATE ERROR", JOptionPane.ERROR_MESSAGE);
                                break;
                            }
                        } catch (Exception ex) {
                            // TODO: handle exception
                        }

                        if (ar.get(0).equals(conTFs[9].getText()) && ar.get(1) == null
                                && ar.get(2) == null) {

                            ar.set(1, p.getPatientID());
                            ar.set(2, c.getDandTSlot());
                            ar.add(c.getCost());
                            System.out.println(c.getCost());
                            if (path.length() != 0) {
                                ar.add("image");
                                try {
                                    ar.add(encrypt(path));
                                } catch (IOException e1) {
                                    // TODO Auto-generated catch block
                                    e1.printStackTrace();
                                }
                                ar.add(c.getNotes());
                            } else {
                                ar.add(c.getNotes());
                            }
                            path = "";
                            break;
                        }

                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Write Date YYYY-MM-DD and Time as XX:XX",
                            "DATE and TIME ERROR", JOptionPane.ERROR_MESSAGE);
                }

                for (ArrayList s : schedule) {
                    System.out.println(schedule.indexOf(s) + ": " + s);
                    if (s.size() >= 4) {
                        sc.append("Medical License No: " + s.get(0) + " Patient: " + s.get(1)
                                + " Slot: " + s.get(2) + " Cost: \u00A3" + s.get(3) + "\n");
                    } else {
                        sc.append("Medical License No: " + s.get(0) + " Patient: " + s.get(1)
                                + " Slot: " + s.get(2) + "\n");
                    }
                }
            }
        });
    }

    public void mainMenu() {
        panel.removeAll();
        suBaPanel.removeAll();
        JButton[] mainBtns = {
                new JButton("Retrieve list of doctors from text file"),
                new JButton("Save list of doctors to text file"),
                new JButton("Create a new doctor"),
                new JButton("Print list of doctors"),
                new JButton("Delete a doctor"), new JButton("Book a Consultation"),
                new JButton("Quit")
        };
        for (JButton b : mainBtns) {
            panel.add(b);
            if (b.getText().contains("Retrieve"))
                b.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        retrieve();
                    }

                });
            else if (b.getText().contains("Save"))
                b.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            save(doctorsList);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }

                });
            else if (b.getText().contains("Create"))
                b.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        create();
                    }
                });
            else if (b.getText().contains("Print"))
                b.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Collections.sort(doctorsList, (d1, d2) -> d1.getName().compareTo(d2.getName()));
                        printDoctors(doctorsList);
                    }

                });
            else if (b.getText().contains("Delete"))
                b.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        delete();
                    }

                });
            else if (b.getText().contains("Consultation"))
                b.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        consultation();

                    }
                });
            else if (b.getText().contains("Quit"))
                b.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.exit(0);

                    }

                });
            b.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(Box.createRigidArea(new Dimension(0, 5)));
        }
        defB(panel);
    }

    public void menu() {
        mainMenu();
    }
}
/*
 * #2022-11-12: Decided to start the GUI varient from scratch to minimise the
 * footprint of program heading into a more function oriented design.
 * -COMPLETED: CREATE BTN, CREATE PAGE and QUIT BTN.
 * -NOTE: COMPLETE SUBMIT BTN FUNCTION.
 */

/*
 * #2022-11-13: Now allowing users to add new doctors (max 10) and print out a
 * list of doctors in a alphabetically sorted order.
 * -COMPLETED: SUBMIT BTN FUNCTION, PRINT FUNCTION and DELETE FUNCTION.
 */

/*
 * #2022-11-18: Created GUI for booking a consultation.
 * -NOTE: COMPLETE SCHEDULE FOR DOCTORS AND RANDOM ASSIGNMENT IF SCHEDULE FULL.
 */

/*
 * #2022-11-19: Completed implementation of Consultation and Patient Objects.
 * -NOTE: COMPLETE THE GUI AND SCHEDULE FOR BOOKING AND CONSULTATION.
 */

/*
 * #2022-11-22: Completed all the GUI functionality as specified by the specification for the program.
 */