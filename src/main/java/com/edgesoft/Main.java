package com.edgesoft;

import java.util.LinkedList;
import java.util.Scanner;

import com.edgesoft.common.Album;
import com.edgesoft.common.PlayList;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static LinkedList<Album> albums = new LinkedList<Album>();
    private static LinkedList<PlayList> playlists = new LinkedList<PlayList>();

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean show = true;

        printMainMenu();

        while (show) {
            switch (scanner.nextLine().toLowerCase().trim()) {
                case "1" -> listEditAlbums();
                case "2" -> listEditPlayLists();
                case "q" -> show = false;
                case "quit" -> show = false;
                default -> printMainMenu();
            }
        }
    }

    private static void listEditPlayLists() {
        System.out.println("\n--> Play Lists:");

        if (playlists.size() > 0) {
            for (int i = 0; i < playlists.size(); i++) {
                System.out.println((i + 1) + ". " + playlists.get(i).getName());
            }
        } else {
            System.out.println("--> No play lists found.\n");
        }

        printActions();

        boolean show = true;

        while (show) {
            switch (scanner.nextLine().toLowerCase().trim()) {
                case "a" -> createPlayList();
                case "add" -> createPlayList();
                case "u" -> updatePlayList();
                case "update" -> updatePlayList();
                case "d" -> deletePlayList();
                case "delete" -> deletePlayList();
                case "b" -> show = false;
                case "back" -> show = false;
                default -> printActions();
            }
        }

        printMainMenu();
    }

    private static void deletePlayList() {
        //TODO: create
    }

    private static void updatePlayList() {
        //TODO: create
    }

    private static void createPlayList() {
        //TODO: create
    }

    private static void listEditAlbums() {
        System.out.println("\n--> Albums:");

        if (albums.size() > 0) {
            for (int i = 0; i < albums.size(); i++) {
                System.out.println((i + 1) + ". " + albums.get(i).getName()  + ", " + albums.get(i).getArtist());
            }
        } else {
            System.out.println("--> No albums found.\n");
        }

        printActions();

        boolean show = true;

        while (show) {
            switch (scanner.nextLine().toLowerCase().trim()) {
                case "a" -> createAlbum();
                case "add" -> createAlbum();
                case "u" -> updateAlbum();
                case "update" -> updateAlbum();
                case "d" -> deleteAlbum();
                case "delete" -> deleteAlbum();
                case "b" -> show = false;
                case "back" -> show = false;
                default -> printActions();
            }
        }

        printMainMenu();
    }

    private static boolean deleteAlbum() {
        boolean actionComplete = false;
        //TODO: create
        return actionComplete;
    }

    private static void updateAlbum() {
        //TODO: create
    }

    private static void createAlbum() {
        System.out.print("Enter album name: ");
        String albumName = scanner.nextLine();

        System.out.print("Enter artist name: ");
        String artistName = scanner.nextLine();

        if (albums.contains(albumName)) {
            System.out.println("--> Album already exists: " + albumName);
        } else {
            Album album = new Album(albumName, artistName);
            albums.add(album);
            System.out.println("--> Album created: " + albumName + " by " + artistName);
        }

        listEditAlbums();
    }

    private static void printActions() {
        String textBlock = """
                \nActions: (A)dd, (U)pdate, (D)elete, (B)ack: """;
        System.out.print(textBlock + " ");
    }

    private static void printMainMenu() {
        System.out.println("--> Main Menu: \n");

        String textBlock = """
                Available actions (select word or letter):
                                
                (1) List / Edit Albums
                (2) List / Edit Playlists
                (Q)uit                                             
                                
                Action:""";
        System.out.print(textBlock + " ");
    }
}