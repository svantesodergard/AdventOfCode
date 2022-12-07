package december7.part1;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        Path input = Paths.get("src/december7/input");
        BufferedReader reader = Files.newBufferedReader(input);

        List<Directory> directories = new ArrayList<>();
        Directory currentDirectory = new Directory("/", null);
        directories.add(currentDirectory);

        String nextLine;
        while ((nextLine = reader.readLine()) != null) {
            if (nextLine.startsWith("$ cd")) {
                String directoryName = nextLine.substring(5);
                if (directoryName.equals("/")) {
                    //cd to root
                    currentDirectory = directories.get(0);
                } else if (directoryName.equals("..")) {
                    //cd to parent
                    currentDirectory = currentDirectory.parent;
                } else {
                    //mkdir and cd to the new dir
                    directories.add(new Directory(directoryName, currentDirectory));
                    currentDirectory = directories.get(directories.size() - 1);
                }
            } else if (!nextLine.startsWith("dir") && !nextLine.startsWith("$")) {
                //calculate file size for current dir and add said size to all its parents and itself
                int spaceIndex = nextLine.indexOf(' ');
                int fileSize = Integer.parseInt(nextLine.substring(0, spaceIndex));
                currentDirectory.addSize(fileSize);
            }
        }

        //Find Answer
        int sumOfSmallDirectoriesSize = 0;
        for (Directory directory : directories) {
            if (directory.getSize() <= 100_000) {
                sumOfSmallDirectoriesSize += directory.getSize();
            }
        }
        System.out.println("Answer: " + sumOfSmallDirectoriesSize);
    }

}

class Directory {
    String name;
    Directory parent;
    private long size = 0;

    public Directory(String name, Directory parent) {
        this.name = name;
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "Directory{" +
                "name='" + name + '\'' +
                ", parent=" + parent +
                ", size=" + size +
                '}';
    }

    public void addSize(long sizeToAdd) {
        size += sizeToAdd;
        if (!this.name.equals("/")) {
            parent.addSize(sizeToAdd);
        }
    }

    public long getSize() {
        return this.size;
    }
}
