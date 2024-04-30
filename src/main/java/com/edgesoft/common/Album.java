package com.edgesoft.common;

import java.util.ArrayList;

/**
 * Album class represents a music album.
 */
public class Album {
    private String name;
    private String artist;
    private ArrayList<Song> songs;

    /**
     * Retrieves the name of the album.
     *
     * @return The name of the album.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the artist of the album.
     *
     * @return The artist of the album.
     */
    public String getArtist() {
        return artist;
    }

    /**
     * Constructs an Album object with the specified name and artist.
     *
     * @param name   The name of the album.
     * @param artist The artist of the album.
     */
    public Album(String name, String artist) {
        this.name = name;
        this.artist = artist;

        songs = new ArrayList<>();
    }

    /**
     * Adds a song to the album.
     *
     * @param songTitle     The title of the song to add.
     * @param songDuration  The duration of the song to add.
     * @return true if the song was successfully added, false otherwise.
     */
    public boolean addSong(String songTitle, double songDuration) {
        boolean songAdded = false;

        if (findSong(songTitle) == null) {
            Song newSong = new Song(songTitle, songDuration);

            songs.add(newSong);
            songAdded = true;
        }

        return songAdded;
    }

    /**
     * Updates the song at the specified index with a new song title and duration.
     *
     * @param songIndex        The index of the song to update.
     * @param newSongTitle     The new title of the song.
     * @param newSongDuration  The new duration of the song.
     */
    public void updateSong(int songIndex, String newSongTitle, double newSongDuration) {
        songs.set(songIndex, new Song(newSongTitle, newSongDuration));
    }

    /**
     * Removes a song from the album.
     *
     * @param songTitle The title of the song to remove.
     * @return true if the song was successfully removed, false otherwise.
     */
    public boolean removeSong(String songTitle) {
        boolean songRemoved = false;

        Song song = findSong(songTitle);
        if (song != null) {
            songs.remove(song);
            songRemoved = true;
        }

        return songRemoved;
    }

    /**
     * Finds a song in the album by the given title.
     *
     * @param songTitle The title of the song to find.
     * @return The song with the given title, or null if not found.
     */
    public Song findSong(String songTitle) {
        for (Song song : songs) {
            if (song.getTitle().equals(songTitle)) {
                return song;
            }
        }

        return null;
    }

    /**
     * Finds the index of a song in the album by the given title.
     *
     * @param songTitle The title of the song to find.
     * @return The index of the song with the given title, or -1 if not found.
     */
    public int findSongIndex(String songTitle) {
        int index = -1;
        for (int i = 0; i < songs.size(); i++) {
            if (songs.get(i).getTitle().equals(songTitle)) {
                index = i;
                break;
            }
        }
        return index;
    }

    /**
     * Prints the list of songs in the album.
     */
    public void printSongs() {
        System.out.println("\n--> Currently Selected Album: " + this.getName() + ", " + this.getArtist());
        if (songs.size() > 0) {
            for (Song song : songs) {
                System.out.println(song);
            }
            System.out.println("\n");
        } else {
            System.out.println("--> There are no songs in the album\n");
        }
    }

    /**
     * Adds a new song to the album after the specified song.
     *
     * @param song              The song after which the new song will be added.
     * @param newSongTitle      The title of the new song.
     * @param newSongDuration   The duration of the new song.
     * @return true if the song was successfully added, false otherwise.
     */
    public boolean addSongAfter(Song song, String newSongTitle, double newSongDuration) {
        int index = songs.indexOf(song);
        if (index >= 0) {
            songs.add(index + 1, new Song(newSongTitle, newSongDuration));
            return true;
        } else {
            return false;
        }
    }

    /**
     * Adds a new song to the album before the specified song.
     *
     * @param song             The song before which the new song will be added.
     * @param newSongTitle     The title of the new song.
     * @param newSongDuration  The duration of the new song.
     * @return true if the song was successfully added, false otherwise.
     */
    public boolean addSongBefore(Song song, String newSongTitle, double newSongDuration) {
        int index = songs.indexOf(song);
        if (index >= 0) {
            songs.add(index, new Song(newSongTitle, newSongDuration));
            return true;
        } else {
            return false;
        }
    }
}
