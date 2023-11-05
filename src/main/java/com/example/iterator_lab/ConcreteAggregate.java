package com.example.iterator_lab;

import javafx.scene.image.Image;

import java.nio.file.Paths;

public class ConcreteAggregate implements
        Aggregate {
    private final String filetopic;
    private Image bi;
    @Override
    public Iterator getIterator() {
        return new ImageIterator();
    }
    public ConcreteAggregate(String filetopic)
    {
        this.filetopic = filetopic;
    }
    private class ImageIterator implements
            Iterator {
        private int current = 1;
        private Image getImage(int iterator){
            String filename =
                    Paths.get("src/main/resources/Images/"+ filetopic
                            + iterator +".jpg").toUri().toString();
            //System.out.println(filename);
            return new Image(filename);
        }
        @Override
        public boolean hasNext(int i) {
            //System.out.println(getImage(current+1).isError());
            return
                    !getImage(current+i).isError();
        }
        @Override
        public Object next() {
            if(this.hasNext(1)) {
                return getImage(++current);
            }
            current = 1;
            return getImage(1);
        }

        @Override
        public Object preview() {
            return null;
        }
    }
}