package com.healvimaginer.watchfilm.domain.utils

import com.healvimaginer.watchfilm.data.source.local.entity.FavoriteFilmEntity
import com.healvimaginer.watchfilm.data.source.local.entity.FavoriteTvEntity
import com.healvimaginer.watchfilm.data.source.local.entity.FilmsEntity
import com.healvimaginer.watchfilm.data.source.local.entity.TvEntity
import com.healvimaginer.watchfilm.data.source.remote.response.FilmResponse
import com.healvimaginer.watchfilm.data.source.remote.response.TvResponse

object DataDummy {
    fun generateDummyFilm() : List<FilmsEntity> {
        val film = ArrayList<FilmsEntity>()
        film.add(
            FilmsEntity("f1",
                "Start Is Born",
                "Film Ini berisi tentang bagaimana kita terlahir dan bagaimana kita mendapat ha kita sebagai manusia abadi",
                "Bradley Cooper",
                "31 Agustus 2018",
                "https://image.tmdb.org/t/p/w500/7BsvSuDQuoqhWmU2fL7W2GOcZHU.jpg",
                "36 Juta Dollar",
                "436.2 Juta Dollar",
            )
        )
        film.add(
            FilmsEntity("f2",
                "Alita:Battle Angel",
                "Ketika Alita terbangun tanpa ingatan tentang siapa dia di dunia masa depan yang tidak dia kenal, dia ditangkap oleh Ido, seorang dokter yang penuh kasih yang menyadari bahwa di suatu tempat dalam cangkang cyborg yang ditinggalkan ini adalah hati dan jiwa seorang wanita muda dengan luar biasa. lalu.",
                "Robert Rodgriguez",
                "14 February 2019",
                "https://image.tmdb.org/t/p/w500/9zfwPffUXpBrEP26yp0q1ckXDcj.jpg",
                "170 Juta Dollar",
                "404.852 Juta Dollar",
            )
        )
        film.add(
            FilmsEntity("f3",
                "Aquaman(2018)",
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                "James Wan",
                "21 Desember 2018",
                "https://image.tmdb.org/t/p/w500/rcICfiL9fvwRjoWHxW8QeroLYrJ.jpg",
                "160 Juta Dollar",
                "1.148.461 Juta Dollar",
            )
        )
        film.add(
            FilmsEntity("f4",
                "Bohemian Rhasody (2018)",
                "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
                "Bryan Singer",
                "02 November 2018",
                "https://image.tmdb.org/t/p/w500/rBkptJyAMqCfoMwUGQfCpubroec.jpg",
                "52 Juta Dollar",
                "894.027 Juta Dollar",
            )
        )
        film.add(
            FilmsEntity("f5",
                "Cold Pursuit (2019)",
                "The quiet family life of Nels Coxman, a snowplow driver, is upended after his son's murder. Nels begins a vengeful hunt for Viking, the drug lord he holds responsible for the killing, eliminating Viking's associates one by one. As Nels draws closer to Viking, his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution.",
                "Hans Petter Moland",
                "08 Februari 2019",
                "https://image.tmdb.org/t/p/w500/lvWL5ZRlYFh7M7fOvYswcRqyprI.jpg",
                "60 Juta Dollar",
                "76.419 Juta Dollar",
            )
        )
        film.add(
            FilmsEntity("f6",
                "Creed II",
                "Between personal obligations and training for his next big fight against an opponent with ties to his family's past, Adonis Creed is up against the challenge of his life.",
                "Steven Caple Jr",
                "21 November 2018",
                "https://image.tmdb.org/t/p/w500/xdANQijuNrJaw1HA61rDccME4Tm.jpg",
                "50 Juta Dollar",
                "214.215 Juta Dollar",
            )
        )
        film.add(
            FilmsEntity("f7",
                "Glass",
                "In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men.",
                "M. Night Shyamalan",
                "18 january 2019",
                "https://image.tmdb.org/t/p/w500/2HVkfkgY1nvWTCRj3H1zTmlghUG.jpg",
                "20 Juta Dollar",
                "246.941 Juta Dollar",
            )
        )
        film.add(
            FilmsEntity("f8",
                "How To Train Your Dragon:The Hidden World",
                "Ketika Hiccup memenuhi mimpinya untuk menciptakan utopia naga yang damai, penemuan Toothless 'dari pasangan yang tak teruji dan sukar ditangkap membuat Night Fury menjauh. Ketika bahaya meningkat di rumah dan pemerintahan Hiccup sebagai kepala desa diuji, baik naga dan pengendara harus membuat keputusan yang mustahil untuk menyelamatkan jenis mereka.",
                "Dean DeBlois",
                "09 january 2019",
                "https://image.tmdb.org/t/p/w500/khtVL4abxYczXAWbo1wAz13CLx3.jpg",
                "129 Juta Dollar",
                "517.526 Juta Dollar",
            )
        )
        film.add(
            FilmsEntity("f9",
                "Avengers:Infinity War",
                "Karena Avengers dan sekutunya terus melindungi dunia dari ancaman yang terlalu besar untuk ditangani oleh seorang pahlawan, bahaya baru telah muncul dari bayangan kosmik: Thanos. Seorang lalim penghujatan intergalaksi, tujuannya adalah untuk mengumpulkan semua enam Batu Infinity, artefak kekuatan yang tak terbayangkan, dan menggunakannya untuk menimbulkan kehendak memutar pada semua realitas. Segala sesuatu yang telah diperjuangkan oleh Avengers telah berkembang hingga saat ini - nasib Bumi dan keberadaannya sendiri tidak pernah lebih pasti.",
                "Anthony Russo",
                "27 April 2019",
                "https://image.tmdb.org/t/p/w500/uQ538BfYLDJh3GXlzRZLo0j7PFj.jpg",
                "300 Juta Dollar",
                "2.046.239 Juta Dollar",
            )
        )
        film.add(
            FilmsEntity("f10",
                "Crimes:Grinderwald",
                "Gellert Grindelwald telah melarikan diri dari penjara dan telah mulai mengumpulkan pengikut ke tujuannya — meninggikan penyihir di atas semua makhluk non-magis. Satu-satunya yang bisa menghentikannya adalah penyihir yang pernah disebutnya sebagai sahabat terdekatnya, Albus Dumbledore. Namun, Dumbledore akan perlu mencari bantuan dari penyihir yang telah menggagalkan Grindelwald sebelumnya, mantan muridnya, Newt Scamander, yang setuju untuk membantu, tidak menyadari bahaya yang ada di depan. Garis-garis digambar saat cinta dan kesetiaan diuji, bahkan di antara teman-teman dan keluarga sejati, di dunia sihir yang semakin terbagi.",
                "David Yates",
                "16 November 2018",
                "https://image.tmdb.org/t/p/w500/5WU6uusqJrLfiBaNs3KpF4o8Lnj.jpg",
                "200 Juta Dollar",
                "653.355 Juta Dollar",
            )
        )
        return film
    }

    fun generateRemoteDummyFilm(): java.util.ArrayList<FilmResponse> {

        val film = java.util.ArrayList<FilmResponse>()

        film.add(FilmResponse("f1",
            "Start Is Born",
            "Film Ini berisi tentang bagaimana kita terlahir dan bagaimana kita mendapat ha kita sebagai manusia abadi",
            "Bradley Cooper",
            "31 Agustus 2018",
            "https://image.tmdb.org/t/p/w500/7BsvSuDQuoqhWmU2fL7W2GOcZHU.jpg",
            "36 Juta Dollar",
            "436.2 Juta Dollar",))

        return film
    }

    fun generateFavDummyFilm(): java.util.ArrayList<FavoriteFilmEntity> {

        val film = java.util.ArrayList<FavoriteFilmEntity>()

        film.add(
            FavoriteFilmEntity("f1",
            "Start Is Born",
            "Film Ini berisi tentang bagaimana kita terlahir dan bagaimana kita mendapat ha kita sebagai manusia abadi",
            "Bradley Cooper",
            "31 Agustus 2018",
            "https://image.tmdb.org/t/p/w500/7BsvSuDQuoqhWmU2fL7W2GOcZHU.jpg",
            "36 Juta Dollar",
            "436.2 Juta Dollar",)
        )

        return film
    }

    fun generateRemoteDummyTv(): java.util.ArrayList<TvResponse> {

        val tv = java.util.ArrayList<TvResponse>()

        tv.add(TvResponse("t1",
            "Marvel's Iron Fist",
            "Danny Rand resurfaces 15 years after being presumed dead. Now, with the power of the Iron Fist, he seeks to reclaim his past and fulfill his destiny.",
            "Scott Buck",
            "2017",
            "https://image.tmdb.org/t/p/w500/7BsvSuDQuoqhWmU2fL7W2GOcZHU.jpg",
            "Dibatalkan",
            "NETFLIX",
        ))

        return tv
    }

    fun generateFavDummyTv(): java.util.ArrayList<FavoriteTvEntity> {

        val tv = java.util.ArrayList<FavoriteTvEntity>()

        tv.add(FavoriteTvEntity("t1",
            "Marvel's Iron Fist",
            "Danny Rand resurfaces 15 years after being presumed dead. Now, with the power of the Iron Fist, he seeks to reclaim his past and fulfill his destiny.",
            "Scott Buck",
            "2017",
            "https://image.tmdb.org/t/p/w500/7BsvSuDQuoqhWmU2fL7W2GOcZHU.jpg",
            "Dibatalkan",
            "NETFLIX",
        ))

        return tv
    }

    fun generateDummyTv() : List<TvEntity> {
        val tv = ArrayList<TvEntity>()
        tv.add(
            TvEntity("t1",
                "Marvel's Iron Fist",
                "Danny Rand resurfaces 15 years after being presumed dead. Now, with the power of the Iron Fist, he seeks to reclaim his past and fulfill his destiny.",
                "Scott Buck",
                "2017",
                "https://image.tmdb.org/t/p/w500/7BsvSuDQuoqhWmU2fL7W2GOcZHU.jpg",
                "Dibatalkan",
                "NETFLIX",
            )
        )
        tv.add(
            TvEntity("t2",
                "Naruto Shipudden",
                "Naruto Shippūden Ultimate Ninja Storm Generations OVA Madara vs Hashirama is the tenth Naruto OVA. It is distributed as part of Naruto Shippūden: Ultimate Ninja Storm Generations.",
                "Masashi Kishimoto",
                "2002",
                "https://image.tmdb.org/t/p/w500/9zfwPffUXpBrEP26yp0q1ckXDcj.jpg",
                "Rilis",
                "NETFLIX, Bioskop",
            )
        )
        tv.add(
            TvEntity("t3",
                "NCIS 2003",
                "From murder and espionage to terrorism and stolen submarines, a team of special agents investigates any crime that has a shred of evidence connected to Navy and Marine Corps personnel, regardless of rank or position.",
                "Donald .Bellisario",
                "2003",
                "https://image.tmdb.org/t/p/w500/rcICfiL9fvwRjoWHxW8QeroLYrJ.jpg",
                "Berlanjut",
                "CBS",
            )
        )
        tv.add(
            TvEntity("t4",
                "Riverdale",
                "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.",
                "Roberto Aguirre-sacceas",
                "2017",
                "https://image.tmdb.org/t/p/w500/rBkptJyAMqCfoMwUGQfCpubroec.jpg",
                "Berlanjut",
                "The CW",
            )
        )
        tv.add(
            TvEntity("t5",
                "Riverdale",
                "Chicagoan Frank Gallagher is the proud single dad of six smart, industrious, independent kids, who without him would be... perhaps better off. When Frank's not at the bar spending what little money they have, he's passed out on the floor. But the kids have found ways to grow up in spite of him. They may not be like any family you know, but they make no apologies for being exactly who they are.",
                "John Wells",
                "2011",
                "https://image.tmdb.org/t/p/w500/lvWL5ZRlYFh7M7fOvYswcRqyprI.jpg",
                "Berakhir",
                "ShowTime",
            )
        )
        tv.add(
            TvEntity("t6",
                "Supergirl",
                "Twenty-four-year-old Kara Zor-El, who was taken in by the Danvers family when she was 13 after being sent away from Krypton, must learn to embrace her powers after previously hiding them. The Danvers teach her to be careful with her powers, until she has to reveal them during an unexpected disaster, setting her on her journey of heroism.",
                "Greg Berlanti",
                "2015",
                "https://image.tmdb.org/t/p/w500/xdANQijuNrJaw1HA61rDccME4Tm.jpg",
                "Berlanjut",
                "CBS",
            )
        )
        tv.add(
            TvEntity("t7",
                "Supernatrual",
                "Twenty-four-year-old Kara Zor-El, who was taken in by the Danvers family when she was 13 after being sent away from Krypton, must learn to embrace her powers after previously hiding them. The Danvers teach her to be careful with her powers, until she has to reveal them during an unexpected disaster, setting her on her journey of heroism.",
                "Greg Berlanti",
                "2015",
                "https://image.tmdb.org/t/p/w500/2HVkfkgY1nvWTCRj3H1zTmlghUG.jpg",
                "Berlanjut",
                "CBS",
            )
        )
        tv.add(
            TvEntity("t8",
                "Simpson",
                "Bertempat di Springfield, kota rata-rata di Amerika, pertunjukan ini berfokus pada kejenakaan dan petualangan sehari-hari keluarga Simpson; Homer, Marge, Bart, Lisa dan Maggie, serta ribuan pemain virtual. Sejak awal, serial ini telah menjadi ikon budaya pop, menarik ratusan selebriti menjadi bintang tamu. Acara ini juga menjadi terkenal karena satirnya yang tak kenal takut terhadap kehidupan politik, media, dan Amerika secara umum.",
                "Matt groening",
                "1989",
                "https://image.tmdb.org/t/p/w500/khtVL4abxYczXAWbo1wAz13CLx3.jpg",
                "Berlanjut",
                "FOX",
            )
        )
        tv.add(
            TvEntity("t9",
                "The Umbrella Academy",
                "A dysfunctional family of superheroes comes together to solve the mystery of their father's death, the threat of the apocalypse and more",
                "Steve Blackman",
                "2019",
                "https://image.tmdb.org/t/p/w500/uQ538BfYLDJh3GXlzRZLo0j7PFj.jpg",
                "Berlanjut",
                "NETFLIX",
            )
        )
        tv.add(
            TvEntity("t10",
                "The Walking Dead",
                "Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way.",
                "Frank Darabont",
                "2010",
                "https://image.tmdb.org/t/p/w500/5WU6uusqJrLfiBaNs3KpF4o8Lnj.jpg",
                "Berlanjut",
                "AMC",
            )
        )
        return tv
    }
}