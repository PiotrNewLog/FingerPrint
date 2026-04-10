package wzwi;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class WZWI {

    public static void main(String[] args) throws IOException {
        
        final int white = 255 | (255 << 8) | (255 << 16) | (255 << 24);
        final int black = (255 << 24);
        final int blue = 255 | (255 << 24);
        final int red = (255 << 16) | (255 << 24);
        
        final String path = "img/render.png";
        final String path2 = "img/output.png";

        int colNum = 0, rowNum = 0; 
        FileInputStream fIn = new FileInputStream("fingerprint.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fIn));
        String testline;
        
        String line = br.readLine();
        
        String[] attributes = line.split(",");
        colNum = attributes.length - 1; //ustawienie ilości kolumn w pliku
        
        while (line != null) { //pętla zliczająca ilość wierszy w pliku
            rowNum++;
            line = br.readLine();
        }
        
        fIn.getChannel().position(0); //ustawienie możliwości ponownego odczytu pliku
        
        int tab[][] = new int[rowNum][colNum]; //tablica przechowująca dane
        
        BufferedImage render = new BufferedImage(rowNum, colNum, BufferedImage.TYPE_INT_ARGB); //BufferedImage do tworzenia grafiki
        BufferedImage render2 = new BufferedImage(rowNum, colNum, BufferedImage.TYPE_INT_ARGB); //BufferedImage do tworzenia grafiki
        
        for (int i = 0; i < rowNum; i++) { //pętla wczytująca wartości z pliku oraz rysująca obraz
            testline = br.readLine();

            attributes = testline.split(",");
            for (int j = 0; j < attributes.length - 1; j++) {
                tab[i][j] = Integer.parseInt(attributes[j]);
                if(tab[i][j] == 0) render.setRGB(i, j, black);
                else render.setRGB(i, j, white);
            }

        }

        br.close(); //Zamknięcie pliku

// ----------- OBLICZENIA ------------------

        int suma = 0;

        for(int i = 0; i < tab.length-3; i++){

            for(int j = 0; j < tab[0].length-3; j++){

                if(tab[i+1][j+1] == 1){
                
                suma += tab[i][j] + tab[i+1][j] + tab[i+2][j];
                suma += tab[i][j+1] + tab[i+1][j+1] + tab[i+2][j+1];
                suma += tab[i][j+2] + tab[i+1][j+2] + tab[i+2][j+2];
                
                if(suma == 2 ) {
                    
                render.setRGB(i, j+1, blue);
                render.setRGB(i, j+2, blue);
                render.setRGB(i+1, j, blue);
                render.setRGB(i+1, j+2, blue);
                render.setRGB(i+2, j, blue);
                render.setRGB(i+2, j+1, blue);
                render.setRGB(i+2, j+2, blue);
                render.setRGB(i, j, blue);
                
                render2.setRGB(i, j+1, blue);
                render2.setRGB(i, j+2, blue);
                render2.setRGB(i+1, j, blue);
                render2.setRGB(i+1, j+2, blue);
                render2.setRGB(i+2, j, blue);
                render2.setRGB(i+2, j+1, blue);
                render2.setRGB(i+2, j+2, blue);
                render2.setRGB(i, j, blue);
                
                }
                else if(suma == 4)     
                {
                render.setRGB(i, j+1, red);
                render.setRGB(i, j+2, red);
                render.setRGB(i+1, j, red);
                render.setRGB(i+1, j+2, red);
                render.setRGB(i+2, j, red);
                render.setRGB(i+2, j+1, red);
                render.setRGB(i+2, j+2, red);
                render.setRGB(i, j, red);
                
                render2.setRGB(i, j+1, red);
                render2.setRGB(i, j+2, red);
                render2.setRGB(i+1, j, red);
                render2.setRGB(i+1, j+2, red);
                render2.setRGB(i+2, j, red);
                render2.setRGB(i+2, j+1, red);
                render2.setRGB(i+2, j+2, red);
                render2.setRGB(i, j, red);
                }
                }
                
                else render2.setRGB(i, j, black);
                suma = 0;
                
            }
            

        File outputfile = new File(path);
        File outputfile2 = new File(path2);
       ImageIO.write(render, "png", outputfile);
       ImageIO.write(render2, "png", outputfile2);

    }
}
}