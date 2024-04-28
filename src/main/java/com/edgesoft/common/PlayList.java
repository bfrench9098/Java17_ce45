package com.edgesoft.common;

import java.util.LinkedList;

public class PlayList {
    private String name;
    private LinkedList<Song> songs;

    public String getName() {
        return name;
    }

    public PlayList(String name) {
        this.name = name;
        this.songs = new LinkedList<>();
    }

    public boolean addToPlayList(Album album, String songTitle) {
        boolean songAdded = false;

        Song song = album.findSong(songTitle);
        if (song != null) {
            songs.add(song);
            songAdded =  true;
        }

        return songAdded;
    }

    public boolean removeFromPlayList(Album album, String songTitle) {
        boolean songRemoved = false;

        Song song = album.findSong(songTitle);
        if (song != null) {
            songs.remove(song);
            songRemoved =  true;
        }

        return songRemoved;
    }

    public void printPlayList() {
        System.out.println("Playlist: " + name);
        for (Song song : songs) {
            System.out.println(song);
        }
    }
}
