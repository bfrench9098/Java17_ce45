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

        printActions(true);

        boolean show = true;

        while (show) {
            switch (scanner.nextLine().toLowerCase().trim()) {
                case "a", "add" -> createPlayList();
                case "u", "update" -> updatePlayList();
                case "d", "delete" -> deletePlayList();
                case "b", "back" -> show = false;
                default -> printActions(true);
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
            listAlbums(false);
        } else {
            System.out.println("--> No albums found.\n");
        }

        printActions(true);

        boolean show = true;

        while (show) {
            String myInput = scanner.nextLine().toLowerCase().trim();

            switch (myInput) {
                case "a", "add" -> createAlbum();
                case "u", "update" -> updateAlbum();
                case "d", "delete" -> deleteAlbum();
                case "l", "list" -> listAlbums(true);
                case "b", "back" -> show = false;
                default -> handleAlbumInput(myInput.trim());
            }
        }

        printMainMenu();
    }

    private static void listAlbums(boolean doPrint) {
        for (int i = 0; i < albums.size(); i++) {
            System.out.println((i + 1) + ". " + albums.get(i).getName()  + ", " + albums.get(i).getArtist());
        }

        if (doPrint) {
            printActions(true);
        }
    }

    private static void handleAlbumInput(String albumIndex) {
        boolean show = false;

        try {
            Album selectedAlbum = albums.get(Integer.parseInt(albumIndex) - 1);
            System.out.println("\n--> Selected Album: " + selectedAlbum.getName() + ", " + selectedAlbum.getArtist());

            selectedAlbum.printSongs();

            printActions(false);
            show = true;
        } catch (NumberFormatException e) {
            System.out.println("--> Invalid input. Please select a valid album number.");
            printActions(true);
            return;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("--> Invalid input. Please select a valid album number.");
            printActions(true);
            return;
        }

        while (show) {
            String myInput = scanner.nextLine().toLowerCase().trim();

            switch (myInput) {
                case "a", "add" -> addTrack();
                case "u", "update" -> updateTrack();
                case "d", "delete" -> deleteTrack();
                case "l", "list" -> listTracks();
                case "b", "back" -> show = false;
                default -> printActions(false);
            }
        }

        listEditAlbums();
    }

    private static void listTracks() {
        //TODO: create
    }

    private static void deleteTrack() {
        //TODO: create
    }

    private static void updateTrack() {
        //TODO: create
    }

    private static void addTrack() {
        //TODO: create
    }

    private static void deleteAlbum() {
        //TODO: create
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

    private static void printActions(boolean includeTrackNo) {
        if (includeTrackNo) {
            String textBlock = """
                    \nActions: (A)dd, (U)pdate, (D)elete, (B)ack, (L)ist, #: """;
            System.out.print(textBlock + " ");
        } else {
            String textBlock = """
                    \nActions: (A)dd, (U)pdate, (D)elete, (B)ack, (L)ist: """;
            System.out.print(textBlock + " ");
        }
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