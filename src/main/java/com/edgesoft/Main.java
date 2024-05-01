package com.edgesoft;

import java.util.LinkedList;
import java.util.Scanner;

import com.edgesoft.common.Album;
import com.edgesoft.common.PlayList;
import com.edgesoft.common.Song;

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
        System.out.println("\n--> Album Menu:");

        if (albums.size() > 0) {
            listAlbums();
        } else {
            System.out.println("--> No albums found.\n");
        }

        boolean show = true;

        while (show) {
            printActions(true);

            String myInput = scanner.nextLine().toLowerCase().trim();

            switch (myInput) {
                case "a", "add" -> createAlbum();
                case "u", "update" -> updateAlbum();
                case "d", "delete" -> deleteAlbum();
                case "l", "list" -> listAlbums();
                case "b", "back" -> show = false;
                default -> handleAlbumInput(myInput.trim());
            }
        }

        printMainMenu();
    }

    private static void deleteAlbum() {
        System.out.print("\nEnter album name to delete: ");
        String albumName = scanner.nextLine();

        int index = -1;
        for (int i = 0; i < albums.size(); i++) {
            if (albums.get(i).getName().equals(albumName)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.println("\n--> Album not found: " + albumName);
        } else {
            Album selectedAlbum = albums.remove(index);
        }

        listAlbums();
    }

    private static void updateAlbum() {
        System.out.print("\nEnter album name to update: ");
        String albumName = scanner.nextLine();

        int index = -1;
        for (int i = 0; i < albums.size(); i++) {
            if (albums.get(i).getName().equals(albumName)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.println("\n--> Album not found: " + albumName);
        } else {
            Album selectedAlbum = albums.get(index);

            System.out.print("\nEnter new album name: ");
            String newAlbumName = scanner.nextLine();

            System.out.print("Enter new album artist: ");
            String newAlbumArtist = scanner.nextLine();

            selectedAlbum.setName(newAlbumName);
            selectedAlbum.setArtist(newAlbumArtist);
        }

        listAlbums();
    }

    private static void createAlbum() {
        System.out.print("\nEnter album name: ");
        String albumName = scanner.nextLine();

        System.out.print("Enter artist name: ");
        String artistName = scanner.nextLine();

        if (albums.contains(albumName)) {
            System.out.println("--> Album already exists: " + albumName);
        } else {
            Album album = new Album(albumName, artistName);
            albums.add(album);
            System.out.println("\n--> Album created: " + albumName + " by " + artistName);
        }

        listAlbums();
    }

    private static void listAlbums() {
        if (albums.size() > 0) {
            System.out.println("\n--> Album Menu:");

            for (int i = 0; i < albums.size(); i++) {
                System.out.println((i + 1) + ". " + albums.get(i).getName() + ", " + albums.get(i).getArtist() + "\n");
            }
        } else {
            System.out.println("\n--> Album Menu:");
            System.out.println("--> No albums found.\n");
        }
    }

    private static void handleAlbumInput(String albumIndex) {
        boolean show = false;
        Album selectedAlbum;

        System.out.println("\n--> Song Menu:");

        try {
            selectedAlbum = albums.get(Integer.parseInt(albumIndex) - 1);
            selectedAlbum.printSongs();

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
            printActions(false);

            String myInput = scanner.nextLine().toLowerCase().trim();

            switch (myInput) {
                case "a", "add" -> addSong(selectedAlbum);
                case "f", "after" -> addSongAfter(selectedAlbum);
                case "r", "before" -> addSongBefore(selectedAlbum);
                case "u", "update" -> updateSong(selectedAlbum);
                case "d", "delete" -> deleteSong(selectedAlbum);
                case "l", "list" -> printSongs(selectedAlbum);
                case "b", "back" -> show = false;
                default -> printActions(false);
            }
        }

        listAlbums();
    }

    private static void deleteSong(Album selectedAlbum) {
        System.out.println("\nEnter the song title to delete: ");
        String songTitle = scanner.nextLine();

        boolean isRemoved = selectedAlbum.removeSong(songTitle);

        if (isRemoved) {
            System.out.println("--> Song removed: " + songTitle);
        } else {
            System.out.println("--> Failed to remove song: " + songTitle);
        }

        printSongs(selectedAlbum);
    }

    private static void updateSong(Album selectedAlbum) {
        System.out.println("\nEnter the song title to update: ");
        String songTitle = scanner.nextLine();

        int songIndex = selectedAlbum.findSongIndex(songTitle);

        if (songIndex == -1) {
            System.out.println("\n--> Song not found: " + songTitle);
        } else {
            System.out.println("\nEnter new song title: ");
            String newSongTitle = scanner.nextLine();

            System.out.println("\nEnter new song duration: ");
            double newSongDuration = scanner.nextDouble();
            scanner.nextLine();

            selectedAlbum.updateSong(songIndex, newSongTitle, newSongDuration);

            printSongs(selectedAlbum);
        }

    }

    private static void addSong(Album selectedAlbum) {
        System.out.print("\nEnter song title: ");
        String songTitle = scanner.nextLine();

        System.out.print("Enter song duration: ");
        double songDuration = scanner.nextDouble();
        scanner.nextLine();

        boolean added = selectedAlbum.addSong(songTitle, songDuration);
        if (added) {
            System.out.println("--> Song added: " + songTitle + " (" + songDuration + " minutes)");
        } else {
            System.out.println("--> Failed to add song: " + songTitle);
        }

        printSongs(selectedAlbum);
    }

    private static void addSongAfter(Album selectedAlbum) {
        System.out.println("\nEnter the song title to add after: ");
        String songTitle = scanner.nextLine();

        Song song = selectedAlbum.findSong(songTitle);

        if (song == null) {
            System.out.println("\n--> Song not found: " + songTitle);
        } else {
            System.out.print("\nEnter new song title: ");
            String newSongTitle = scanner.nextLine();

            System.out.print("Enter new song duration: ");
            double newSongDuration = scanner.nextDouble();
            scanner.nextLine();

            boolean added = selectedAlbum.addSongAfter(song, newSongTitle, newSongDuration);
            if (added) {
                System.out.println("--> Song added after " + songTitle + ": " + newSongTitle + " (" + newSongDuration + " minutes)");
            } else {
                System.out.println("--> Failed to add song after " + songTitle + ": " + newSongTitle);
            }

            printSongs(selectedAlbum);
        }
    }

    private static void addSongBefore(Album selectedAlbum) {
        System.out.println("\nEnter the song title to add before: ");
        String songTitle = scanner.nextLine();

        Song song = selectedAlbum.findSong(songTitle);

        if (song == null) {
            System.out.println("\n--> Song not found: " + songTitle);
        } else {
            System.out.print("\nEnter new song title: ");
            String newSongTitle = scanner.nextLine();

            System.out.print("Enter new song duration: ");
            double newSongDuration = scanner.nextDouble();
            scanner.nextLine();

            boolean added = selectedAlbum.addSongBefore(song, newSongTitle, newSongDuration);
            if (added) {
                System.out.println("--> Song added before " + songTitle + ": " + newSongTitle + " (" + newSongDuration + " minutes)");
            } else {
                System.out.println("--> Failed to add song before " + songTitle + ": " + newSongTitle);
            }

            printSongs(selectedAlbum);
        }
    }

    private static void printSongs(Album selectedAlbum) {
        System.out.println("\n--> Song Menu:");
        selectedAlbum.printSongs();
    }

    private static void printActions(boolean includeTrackNo) {
        if (includeTrackNo) {
            String textBlock = """
                    Actions: (A)dd, A(f)ter, Befo(r)e, (U)pdate, (D)elete, (B)ack, (L)ist, #: """;
            System.out.print(textBlock + " ");
        } else {
            String textBlock = """
                    Actions: (A)dd, A(f)ter, Befo(r)e, (U)pdate, (D)elete, (B)ack, (L)ist: """;
            System.out.print(textBlock + " ");
        }
    }

    private static void printMainMenu() {
        System.out.println("--> Main Menu: \n");

        String textBlock = """
                Available actions:
                                
                (1) List / Edit Albums
                (2) List / Edit Playlists
                (Q)uit                                             
                                
                Action:""";
        System.out.print(textBlock + " ");
    }
}