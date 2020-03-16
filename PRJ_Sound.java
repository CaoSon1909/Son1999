
package prj_sound;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;
import javazoom.jl.player.Player;

public class PRJ_Sound {

static Player player=null;
    public static void main(String[] args) {
        try {
            Scanner s=new Scanner(System.in);
            String filename;
            System.out.println("enter music file path:");
            filename=s.nextLine().trim();
            
            FileInputStream fis=null;
            BufferedInputStream bis=null;
            File f=new File(filename);
            fis=new FileInputStream(f);
            bis=new BufferedInputStream(fis);
            player =new Player(bis);
            
            //run in new thread to play background
            Thread playingThread =new Thread(){//Anonymus class
                public void run(){
                    try {
                        player.play();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            };
            playingThread.start();
            System.out.println("Music is playing...");
            System.out.println("Press 'S' to stop!");
            s=new Scanner(System.in);
            String command=s.nextLine();
            if(command.equalsIgnoreCase("S"))
            {
                if(player!=null){
                    player.close();
                    System.out.println("Player is stopped!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
}
