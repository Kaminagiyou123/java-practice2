package com.company;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) throws IOException {
        String s1= File.separator;
       String s2= FileSystems.getDefault().getSeparator();
        System.out.println(s1+";"+s2);
        DirectoryStream.Filter<Path> filter= p->Files.isRegularFile(p);
//        DirectoryStream.Filter filter=
//                new DirectoryStream.Filter<Path>(){
//            public boolean accept (Path path) throws IOException{
//                return Files.isRegularFile(path);
//            }
//                };
        Path directory=FileSystems.getDefault().getPath("FileTree"+s1+"Dir2");
        try (DirectoryStream<Path> contents=Files.newDirectoryStream(directory,filter)){
            for (Path file:contents){
                System.out.println(file.getFileName());
            }

        }
        try {
            Path tempFile= Files.createTempFile("myapp",".apppext");
            System.out.println("temp file path is "+tempFile.toAbsolutePath());
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
//
//        Iterable<FileStore> stores=FileSystems.getDefault().getFileStores();
//        for (FileStore store:stores){
//            System.out.println(store.name());
//        }

        Iterable<Path> rootPaths=FileSystems.getDefault().getRootDirectories();
        for (Path path:rootPaths){
            System.out.println(path);
        }

        System.out.println("====walking tree for Dir2====");
        Path dir2Path=FileSystems.getDefault().getPath("FileTree"+File.separator+"Dir2");
        try {
            Files.walkFileTree(dir2Path,new PrintNames());

        } catch(IOException e){
            System.out.println(e.getMessage());
        }

        System.out.println("copy dir 2 to dir 4");
        Path copyPath=FileSystems.getDefault().getPath("FileTree"+File.separator+"Dir4"+File.separator+"Dir2Copy");
try {
    Files.walkFileTree(dir2Path, new CopyFile(dir2Path,copyPath));
}catch(IOException e){
    System.out.println(e.getMessage());
}
//        Path filePath=FileSystems.getDefault().getPath("Examples","Dir1/file1.txt");
//        long size=Files.size(filePath);
//        System.out.println("Size is "+size);
//        System.out.println("last modified is "+Files.getLastModifiedTime(filePath));
//        BasicFileAttributes attr=Files.readAttributes(filePath,BasicFileAttributes.class);
//        System.out.println("size = "+attr.size());
//



//        Path fileToCreate=FileSystems.getDefault().getPath("Examples","file2.txt");
//        Files.createFile(fileToCreate);
//        Path DirToCreate=FileSystems.getDefault().getPath("Examples","Dir4");
//        Files.createDirectories(DirToCreate);
//        Path dirToCreate= FileSystems.getDefault().getPath("Examples","Dir2/Dir3/Dir4/Dir5/Dir6");
//        Files.createDirectories(dirToCreate);



//        Path fileToDelete=FileSystems.getDefault().getPath("Examples","Dir1","file1copy.txt");
//        Files.deleteIfExists(fileToDelete);

//        Path fileToMove=FileSystems.getDefault().getPath("Examples","file1.txt");
//        Path destination=FileSystems.getDefault().getPath("Examples","file2.txt");
//        Files.move(fileToMove,destination);

//        Path sourceFile= FileSystems.getDefault().getPath("Examples","file1.txt");
//        Path copyFile= FileSystems.getDefault().getPath("Examples","file1copy.txt");
//        Files.copy(sourceFile,copyFile, StandardCopyOption.REPLACE_EXISTING);
//        sourceFile= FileSystems.getDefault().getPath("Examples","Dir1");
//        copyFile=FileSystems.getDefault().getPath("Examples","Dir4");
//        Files.copy(sourceFile,copyFile, StandardCopyOption.REPLACE_EXISTING);

//        Path path= FileSystems.getDefault().getPath("WorkingDirectoryFile.txt");
//        printFile(path);
////        Path filePath= FileSystems.getDefault().getPath("files","SubdirectoryFile.txt");
//        Path filePath= FileSystems.getDefault().getPath(".","Files","SubdirectoryFile.txt");
//        System.out.println(filePath.normalize().toAbsolutePath());
//        printFile(path);
//        filePath= Paths.get("/Documents/Java_Projects/Outthere.txt");
//        printFile(path);
//        filePath=Paths.get(".");
//        System.out.println(filePath.toAbsolutePath());
//
//        Path path2= FileSystems.getDefault().getPath(".","File","..","File","SubdirectoryFile.txt");
//        System.out.println(path2.normalize().toAbsolutePath());
//        printFile(path2.normalize());
//
//        Path path3=FileSystems.getDefault().getPath("thisfiledoesntexist.txt");
//        System.out.println(path3.toAbsolutePath());
//
//        Path path4=Paths.get(".","abcd","whatever.txt");
//        System.out.println(path4.toAbsolutePath());
//        filePath=FileSystems.getDefault().getPath(".","File");
//        System.out.println(filePath.toAbsolutePath());
//        System.out.println("Exists= "+Files.exists(filePath));
//        System.out.println("Exists= "+Files.exists(path4));





    }
////    private static void printFile(Path path){
////        try(BufferedReader fileReader= Files.newBufferedReader(path)) {
////            String line;
////            while((line= fileReader.readLine())!=null){
////                System.out.println(line);
////            }
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
//    }
}
