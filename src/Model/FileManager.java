package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class FileManager {
    public static BufferedImage[] getImagesFiles(String pathName) {
        File folder = new File(pathName);
        File[] listOfFiles = folder.listFiles();
        assert listOfFiles != null;
        BufferedImage[] images = new BufferedImage[listOfFiles.length];

        try{
            int i = 0;
            for (File file : listOfFiles) {
                if (file.isFile()) {

                    images[i] = ImageIO.read(file);
                    i++;

                }
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
        return images;
    }

    public static int getFolderLength(String pathName) {
        File folder = new File(pathName);
        return Objects.requireNonNull(folder.listFiles()).length;
    }

    public static int getImageWidth(String pathName) {
        BufferedImage[] images = getImagesFiles(pathName);
        return images[0].getWidth();
    }

}

