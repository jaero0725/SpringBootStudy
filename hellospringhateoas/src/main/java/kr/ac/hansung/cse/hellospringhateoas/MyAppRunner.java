package kr.ac.hansung.cse.hellospringhateoas;

import kr.ac.hansung.cse.hellospringhateoas.entity.ActorEntity;
import kr.ac.hansung.cse.hellospringhateoas.entity.AlbumEntity;
import kr.ac.hansung.cse.hellospringhateoas.repository.ActorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyAppRunner implements ApplicationRunner {

    @Autowired
    ActorRepository actorRepository;

    private static final Logger log = LoggerFactory.getLogger(MyAppRunner.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {

        AlbumEntity album1 =new AlbumEntity( "Top Hits Vol 1", "Top hits vol 1. description", "10-03-1981");
        AlbumEntity album2 =new AlbumEntity( "Top Hits Vol 2", "Top hits vol 2. description", "10-03-1982");
        AlbumEntity album3 =new AlbumEntity( "Top Hits Vol 3", "Top hits vol 3. description", "10-03-1983");
        AlbumEntity album4 =new AlbumEntity( "Top Hits Vol 4", "Top hits vol 4. description", "10-03-1984");
        AlbumEntity album5 =new AlbumEntity( "Top Hits Vol 5", "Top hits vol 5. description", "10-03-1985");
        AlbumEntity album6 =new AlbumEntity( "Top Hits Vol 6", "Top hits vol 6. descriptiop", "10-03-1986");
        AlbumEntity album7 =new AlbumEntity( "Top Hits Vol 7", "Top hits vol 7. description", "10-03-1987");
        AlbumEntity album8 =new AlbumEntity( "Top Hits Vol 8", "Top hits vol 8. description", "10-03-1988");
        AlbumEntity album9 =new AlbumEntity( "Top Hits Vol 9", "Top hits vol 9. description", "10-03-1989");
        AlbumEntity album10 =new AlbumEntity( "Top Hits Vol 10", "Top hits vol 10. description", "10-03-1990");

        ActorEntity actor1= new ActorEntity("John", "Doe", "10-Jan-1952");
        ActorEntity actor2= new ActorEntity( "Amy", "Eugene", "05-07-1985");
        ActorEntity actor3= new ActorEntity( "Laverne", "Mann", "11-12-1988");
        ActorEntity actor4= new ActorEntity( "Janice", "Preston", "19-02-1960");
        ActorEntity actor5= new ActorEntity( "Pauline", "Rios", "29-08-1977");

        List<AlbumEntity> actor1_albums = new ArrayList<AlbumEntity>();
        actor1_albums.add(album1);
        actor1_albums.add(album2);
        actor1.setAlbums(actor1_albums);

        List<AlbumEntity> actor2_albums = new ArrayList<AlbumEntity>();
        actor2_albums.add(album3);
        actor2_albums.add(album4);
        actor2.setAlbums(actor2_albums);

        List<AlbumEntity> actor3_albums = new ArrayList<AlbumEntity>();
        actor3_albums.add(album5);
        actor3_albums.add(album6);
        actor3.setAlbums(actor3_albums);

        List<AlbumEntity> actor4_albums = new ArrayList<AlbumEntity>();
        actor4_albums.add(album7);
        actor4_albums.add(album8);
        actor4.setAlbums(actor4_albums);

        List<AlbumEntity> actor5_albums = new ArrayList<AlbumEntity>();
        actor5_albums.add(album9);
        actor5_albums.add(album10);
        actor5.setAlbums(actor5_albums);

        actorRepository.save(actor1);
        actorRepository.save(actor2);
        actorRepository.save(actor3);
        actorRepository.save(actor4);
        actorRepository.save(actor5);
    }
}
