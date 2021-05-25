package com.company;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class CopyFile extends SimpleFileVisitor<Path> {
    private Path sourceRoot;
    private Path targetRoot;

    public CopyFile(Path sourceRoot, Path targetRoot) {
        this.sourceRoot = sourceRoot;
        this.targetRoot = targetRoot;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        Path relativeizedPath=sourceRoot.relativize(dir);
        System.out.println("relativizedPath = "+relativeizedPath);
        Path copyDir=targetRoot.resolve(relativeizedPath);
        System.out.println(("resolved path = "+copyDir));
        try {
            Files.copy(dir,copyDir);
        } catch(IOException e){
            System.out.println(e.getMessage());
            return FileVisitResult.SKIP_SUBTREE;
        }
        return FileVisitResult.CONTINUE;
    }


    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        Path relativeizedPath=sourceRoot.relativize(file);
        System.out.println("relativizedPath = "+relativeizedPath);
        Path copyDir=targetRoot.resolve(relativeizedPath);
        System.out.println(("resolved path = "+copyDir));
        try {
            Files.copy(file,copyDir);
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
        return FileVisitResult.CONTINUE;
    }
}
