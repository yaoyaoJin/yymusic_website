package com.music.yymusic_website;

import com.music.yymusic_website.domain.ListSong;
import com.music.yymusic_website.domain.Song;
import com.music.yymusic_website.service.SongService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class YymusicWebsiteApplicationTests {

    @Test
    void contextLoads() {
        Song song=new Song();
        System.out.println(song);
        ListSong listSong=new ListSong();
        System.out.println(listSong);
    }

}
