import java.io.File;

interface SkinConsultationManager {
    void addDoctor();
    void deleteDoctor();
    void printList();
    void write();
    void read(File file);
    void menu();
}