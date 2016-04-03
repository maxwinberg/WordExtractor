import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.Semaphore;

/**
 * Created by Max on 2016-04-02.
 */
public class Startup extends JFrame implements Runnable, KeyListener{

    private Thread thread;
    private Extractor extractor;
    private boolean saveCurrent;
    private Semaphore semaphore;
    private JLabel label;



    public Startup(){
        super("Tja");

        extractor = new Extractor();
        extractor.loadWords();
        semaphore = new Semaphore(0);
        label = new JLabel();
        this.add(label);
        this.setSize(new Dimension(100,100));
        this.addKeyListener(this);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        thread = new Thread(this);
        thread.start();

    }

    public static void main(String[] args){

        new Startup();

    }

    @Override
    public void run() {
        Iterator<String> it = extractor.getWords().iterator();
        String word;
        while(it.hasNext()){
            saveCurrent = false;
            word = it.next();
            label.removeAll();
            label.setText(word);
            try{
                semaphore.acquire();
            }catch(Exception e){
                System.out.println("Shit");
            }

            if(saveCurrent){
                extractor.saveWord(word);
            }
        }

    }

    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("asdasd");
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            saveCurrent = true;
        }else{
            saveCurrent = false;
        }
        semaphore.release(1);
    }
}
