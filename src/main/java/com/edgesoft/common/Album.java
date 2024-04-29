package com.edgesoft.common;

import java.util.ArrayList;

public class Album {
    private String name;
    private String artist;
    private ArrayList<Song> songs;

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public Album(String name, String artist) {
        this.name = name;
        this.artist = artist;

        songs = new ArrayList<>();
    }

    public boolean addSong(String songTitle, double songDuration) {
        boolean songAdded = false;

        if (findSong(songTitle) == null) {
            Song newSong = new Song(songTitle, songDuration);

            songs.add(newSong);
            songAdded = true;
        }

        return songAdded;
    }

    public boolean removeSong(String songTitle) {
        boolean songRemoved = false;

        Song song = findSong(songTitle);
        if (song != null) {
            songs.remove(song);
            songRemoved = true;
        }

        return songRemoved;
    }

    public Song findSong(String songTitle) {
        for (Song song : songs) {
            if (song.getTitle().equals(songTitle)) {
                return song;
            }
        }

        return null;
    }

    public void printSongs() {
        if (songs.size() > 0) {
            for (Song song : songs) {
                System.out.println(song);
            }
        } else {
            System.out.println("--> There are no songs in the album");
        }
    }
}
