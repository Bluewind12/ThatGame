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

    //テキストファイルから戦績を取得
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

    //戦績を変更して、書き込みを行う
    public void changeRecord(int winer) {
        //勝利:2 敗北:1 で入力を受ける
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

    //トータルバトル回数を返す
    public int getTotalBattleTimes() {
        return totalBattleTimes;
    }

    //勝利回数を返す
    public int getTotalWinTimes() {
        return totalWinTimes;
    }

    //敗北回数を返す
    public int getTotalLoseTimes() {
        return totalLoseTimes;
    }
}