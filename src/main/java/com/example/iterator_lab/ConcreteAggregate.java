package com.example.iterator_lab;
import javafx.scene.image.Image;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ConcreteAggregate implements
        Aggregate {
    private Image bi;
    @Override
    public Iterator getIterator() {
        return new ImageIterator();
    }
    private class ImageIterator implements
            Iterator {
        private int current = 1;
        public List<File> images;
        public  ImageIterator()
        {
            images = new ArrayList<File>();

        }
        private Image getImage(int iterator){
            File dir = new File("src/main/resources/Images");
            for(File file : dir.listFiles())
            {
                if(file.isFile())
                    if(file.getPath().endsWith(".jpg") ||
                            file.getPath().endsWith(".jpeg") ||
                            file.getPath().endsWith(".png"))
                        images.add(file);
            }
            return new Image(images.get(iterator).toURI().toString());
        }
        @Override
        public boolean hasNext() {
            return
                    !getImage(current+1).isError();
        }
        @Override
        public boolean hasPreview() {
            return
                    !getImage(current-1).isError();
        }
        @Override
        public Object next() {
            if(this.hasNext()) {
                return getImage(++current);
            }
            current = 1;
            return getImage(1);
        }

        @Override
        public Object preview() {
            if(this.hasPreview()) {
                return getImage(--current);
            }
            current = 8;
            return getImage(8);
        }
    }
}