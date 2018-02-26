import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;

/**
 * RecordManagement
 */
public class RecordManage {
    private static String[] scoreRecords = new String[3];
    private int totalBattleTimes = 0;
    private int totalWinTimes = 0;
    private int totalLoseTimes = 0;
    private String fileName = "Records.txt";

    //�e�L�X�g�t�@�C�������т��擾
    RecordManage() {
        try {
            File file = new File(fileName);
            BufferedReader br = new BufferedReader(new FileReader(file));
            for (int i = 0; i < 3; i++) {
                scoreRecords[i] = br.readLine();
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
        totalBattleTimes = Integer.parseInt(scoreRecords[0]);
        totalWinTimes = Integer.parseInt(scoreRecords[1]);
        totalLoseTimes = Integer.parseInt(scoreRecords[2]);
    }

    //��т�ύX���āA�������݂��s��
    public void changeRecord(int winer) {
        //����:2 �s�k:1 �œ��͂��󂯂�
        totalBattleTimes++;
        if (winer == 2) {
            totalWinTimes++;
        } else {
            totalLoseTimes++;
        }
        try {
            File file = new File(fileName);
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(String.valueOf(totalBattleTimes));
            bw.newLine();
            bw.write(String.valueOf(totalWinTimes));
            bw.newLine();
            bw.write(String.valueOf(totalLoseTimes));
            bw.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    //�g�[�^���o�g���񐔂�Ԃ�
    public int getTotalBattleTimes() {
        return totalBattleTimes;
    }

    //�����񐔂�Ԃ�
    public int getTotalWinTimes() {
        return totalWinTimes;
    }

    //�s�k�񐔂�Ԃ�
    public int getTotalLoseTimes() {
        return totalLoseTimes;
    }
}