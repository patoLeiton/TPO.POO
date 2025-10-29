package Utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageInspector {
    public static void main(String[] args) throws Exception {
        File dir = new File("image");
        if (!dir.exists() || !dir.isDirectory()) {
            System.out.println("image/ directory not found: " + dir.getAbsolutePath());
            return;
        }
        File[] files = dir.listFiles((d, name) -> name.toLowerCase().endsWith(".png") || name.toLowerCase().endsWith(".jpg") || name.toLowerCase().endsWith(".jpeg"));
        if (files == null || files.length == 0) {
            System.out.println("No image files found in " + dir.getAbsolutePath());
            return;
        }
        for (File f : files) {
            try {
                BufferedImage img = ImageIO.read(f);
                if (img == null) {
                    System.out.println(f.getName() + ": could not read (null)");
                    continue;
                }
                int w = img.getWidth();
                int h = img.getHeight();
                boolean hasAlpha = img.getColorModel().hasAlpha();
                long nonTransparent = 0;
                long total = (long) w * h;
                // sample pixels if very large
                int stepX = Math.max(1, w / 200);
                int stepY = Math.max(1, h / 200);
                for (int y = 0; y < h; y += stepY) {
                    for (int x = 0; x < w; x += stepX) {
                        int argb = img.getRGB(x, y);
                        int alpha = (argb >> 24) & 0xff;
                        if (alpha > 16) nonTransparent++; // > ~6% visible
                    }
                }
                double sampleCount = ((double)((w + stepX -1)/stepX)) * ((double)((h + stepY -1)/stepY));
                double nonTransPct = (nonTransparent / sampleCount) * 100.0;
                System.out.printf("%s: %dx%d, alpha=%s, nonTransparent(sample)=%d (%.1f%%)\n",
                        f.getName(), w, h, hasAlpha, nonTransparent, nonTransPct);
            } catch (Exception e) {
                System.out.println(f.getName() + ": error reading: " + e.getMessage());
            }
        }
    }
}
