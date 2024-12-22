package Model;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class FileManager {
    public static BufferedImage[] getImagesFiles(String pathName) {
        System.out.println(pathName);
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

    public static BufferedImage[] reverseImageArray(BufferedImage[] images) {
        BufferedImage[] reversedImageArray = images.clone();
        for(int i = 0; i < reversedImageArray.length; i++) {
            AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
            tx.translate(-images[i].getWidth(null), 0);
            AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
            reversedImageArray[i] = op.filter(images[i], null);
        }
        return reversedImageArray;
    }

}

