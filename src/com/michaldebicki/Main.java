package com.michaldebicki;

import com.michaldebicki.model.Artist;
import com.michaldebicki.model.DataSource;
import com.michaldebicki.model.SongArtist;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        DataSource dataSource = new DataSource();
        if(!dataSource.open()) {
            System.out.println("Can not open datasource");
            return;
        }
        List<Artist> artists = dataSource.queryArtist(DataSource.ORDDER_BY_ASC);
        if(artists == null){
            System.out.println("no artist!");
            return;
        }
        for(Artist artist : artists){
            System.out.println("ID = " + artist.getId() + ", Name = " + artist.getName());
        }


        List<String> albumForArtist = dataSource.queryAlbumForArtist("Pink Floyd",dataSource.ORDER_BY_DESC);

        for(String album : albumForArtist){
            System.out.println(album);
        }

        List<SongArtist> songArtists = dataSource.queryArtistsForSong("Go Your Own Way", DataSource.ORDDER_BY_ASC);
        if(songArtists == null){
            System.out.println("Could not find the artist for the song");
            return;
        }
        for (SongArtist artist : songArtists){
            System.out.println("Artist name = " + artist.getArtistName() +
                    " Album name = " + artist.getAlbumName() +
                    " Track = " + artist.getTrack());
        }

        dataSource.querySongsMetadata();

        int count = dataSource.getCount(DataSource.TABLE_SONGS);
        System.out.println("Number of songs is " + count);

        dataSource.createViweForSongArtists();

//        Scanner scanner = new Scanner(System.in);
////        System.out.println("Enter a song title: ");
////        String title = scanner.nextLine();
////
////        songArtists = dataSource.querySongInfoView(title);
////        if(songArtists.isEmpty()) {
////            System.out.println("Could not find the artist for the song");
////            return;
////        }
////        for(SongArtist artist : songArtists) {
////            System.out.println(" FROM VIEW - Artist name = " + artist.getArtistName() +
////                    " Album name = " + artist.getAlbumName() +
////                    " Track number = " + artist.getTrack());
////        }

        dataSource.insertSong("Bird Dog", "Everly Brothers","All-Time Greatest Hits",7);
        dataSource.close();
    }
}
