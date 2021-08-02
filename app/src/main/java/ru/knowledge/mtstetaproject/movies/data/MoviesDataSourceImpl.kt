package ru.knowledge.mtstetaproject.movies.data

class MoviesDataSourceImpl : MoviesDataSource {
    override fun getMovies() = listOf(
        MovieDto(
            id = 1,
            title = "Гнев человеческий",
            description = "Эйч — загадочный и холодный на вид джентльмен, но внутри него пылает жажда справедливости. Преследуя...",
            rateScore = 3,
            ageRestriction = 18,
            imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/5JP9X5tCZ6qz7DYMabLmrQirlWh.jpg",
            genre = GenreDto("Боевик", 28),
            releaseDate = "22.04.21",
            actors = listOf(
                ActorDto("Джейсон Стейтем","https://www.themoviedb.org/t/p/w600_and_h900_bestv2/lldeQ91GwIVff43JBrpdbAAeYWj.jpg"),
                ActorDto("Холт Маккэллани","https://www.themoviedb.org/t/p/w600_and_h900_bestv2/8NvOcP35qv5UHWEdpqAvQrKnQQz.jpg"),
                ActorDto("Джош Харнетт","https://www.themoviedb.org/t/p/w600_and_h900_bestv2/y3UF4biXITMMH8qutq7K7YLjvu5.jpg"))
        ),
        MovieDto(
            id = 2,
            title = "Мортал Комбат",
            description = "Боец смешанных единоборств Коул Янг не раз соглашался проиграть за деньги. Он не знает о своем наследии...",
            rateScore = 5,
            ageRestriction = 18,
            imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/pMIixvHwsD5RZxbvgsDSNkpKy0R.jpg",
            genre = GenreDto("Боевик", 28),
            releaseDate = "08.04.21",
            actors = listOf(
                ActorDto("Lewis Tan","https://www.themoviedb.org/t/p/w600_and_h900_bestv2/lkW8gh20BuwzHecXqYH1eRVuWpb.jpg"),
                ActorDto("Jessica McNamee","https://www.themoviedb.org/t/p/w600_and_h900_bestv2/aAfaMEEqD8syHv5bLi5B3sccrM2.jpg"),
                ActorDto("Josh Lawson","https://www.themoviedb.org/t/p/w600_and_h900_bestv2/Am9vM77uZd9bGODugwmWtOfzx6E.jpg"))
        ),
        MovieDto(
            id = 3,
            title = "Упс... Приплыли!",
            description = "От Великого потопа зверей спас ковчег. Но спустя полгода скитаний они готовы сбежать с него куда угодно...",
            rateScore = 5,
            ageRestriction = 6,
            imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/546RNYy9Wi5wgboQ7EtD6i0DY5D.jpg",
            genre = GenreDto("Мультик", 16),
            releaseDate = "24.09.20",
            actors = listOf(
                ActorDto("Tara Flynn","https://www.themoviedb.org/t/p/w600_and_h900_bestv2/17gBs4aux2NcnMvf3DK5UKUFttn.jpg"),
                ActorDto("Ava Connolly","https://www.themoviedb.org/t/p/w600_and_h900_bestv2/o8uE77C4wQHYHJW6En192kjxJGd.jpg"),
                ActorDto("Mary Murray","https://www.themoviedb.org/t/p/w600_and_h900_bestv2/1ZRP9IfehCSx5OeBQQDcVPvKYD0.jpg"))
        ),
        MovieDto(
            id = 4,
            title = "The Box",
            description = "Уличный музыкант знакомится с музыкальным продюсером, и они вдвоём отправляются в путешествие...",
            rateScore = 4,
            ageRestriction = 12,
            imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/fq3DSw74fAodrbLiSv0BW1Ya4Ae.jpg",
            genre = GenreDto("Драма", 18),
            releaseDate = "13.05.21",
            actors = listOf(
                ActorDto("Chanyeol","https://www.themoviedb.org/t/p/w600_and_h900_bestv2/qqvTuk4CTvS1IE47CUozhcHVahz.jpg"),
                ActorDto("Jo Dal-hwan","https://www.themoviedb.org/t/p/w600_and_h900_bestv2/jpEPPXmVC3EDMqrDQDYyXEMYlah.jpg"),
                ActorDto("Gaeko","https://www.themoviedb.org/t/p/w600_and_h900_bestv2/fDO7vJVRkZOOY1GtQMJzf4N136q.jpg"))
        ),
        MovieDto(
            id = 5,
            title = "Сага о Дэнни Эрнандесе",
            description = "Tekashi69 или Сикснайн — знаменитый бруклинский рэпер с радужными волосами — прогремел...",
            rateScore = 2,
            ageRestriction = 18,
            imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/5xXGQLVtTAExHY92DHD9ewGmKxf.jpg",
            genre = GenreDto("Музыка", 10402),
            releaseDate = "29.04.21",
            actors = listOf(
                ActorDto("6ix9ine","https://www.themoviedb.org/t/p/w600_and_h900_bestv2/xAlvyeC9zLbygGMxmmyTHymwuZP.jpg"),
                ActorDto("Jo Dal-hwan","https://www.themoviedb.org/t/p/w600_and_h900_bestv2/jpEPPXmVC3EDMqrDQDYyXEMYlah.jpg"),
                ActorDto("Gaeko","https://www.themoviedb.org/t/p/w600_and_h900_bestv2/fDO7vJVRkZOOY1GtQMJzf4N136q.jpg"))
        ),
        MovieDto(
            id = 6,
            title = "Пчелка Майя",
            description = "Когда упрямая пчелка Майя и ее лучший друг Вилли спасают принцессу-муравьишку, начинается сказочное...",
            rateScore = 4,
            ageRestriction = 0,
            imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/xltjMeLlxywym14NEizl0metO10.jpg",
            genre = GenreDto("Мультик", 16),
            releaseDate = "07.01.21",
            actors = listOf(
                ActorDto("Benson Jack Anthony","https://www.themoviedb.org/t/p/w600_and_h900_bestv2/aVfEldX1ksEMrx45yNBAf9MAIDZ.jpg"),
                ActorDto("Frances Berry","https://www.themoviedb.org/t/p/w600_and_h900_bestv2/qCp0psD5qzguABpRxWmMuC04kcl.jpg"),
                ActorDto("Christian Charisiou","https://www.themoviedb.org/t/p/w600_and_h900_bestv2/8OpoYvO1QqBYRAp1LxxUIiRdQG0.jpg"))
        ),
        MovieDto(
            id = 7,
            title = "Круэлла",
            description = "Невероятно одаренная мошенница по имени Эстелла решает сделать себе имя в мире моды.",
            rateScore = 4,
            ageRestriction = 12,
            imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/hUfyYGP9Xf6cHF9y44JXJV3NxZM.jpg",
            genre = GenreDto("Комедия", 35),
            releaseDate = "03.06.21",
            actors = listOf(
                ActorDto("Emma Stone","https://www.themoviedb.org/t/p/w600_and_h900_bestv2/2hwXbPW2ffnXUe1Um0WXHG0cTwb.jpg"),
                ActorDto("Emma Thompson","https://www.themoviedb.org/t/p/w600_and_h900_bestv2/xr8Ki3CIqweWWqS5q0kUYdiK6oQ.jpg"),
                ActorDto("Joel Fry","https://www.themoviedb.org/t/p/w600_and_h900_bestv2/4nEKEWJpaTHncCTv6zeP98V0qGI.jpg"))
        ),
        MovieDto(
            id = 8,
            title = "Чёрная вдова",
            description = "Чёрной Вдове придется вспомнить о том, что было в её жизни задолго до присоединения к команде Мстителей",
            rateScore = 3,
            ageRestriction = 16,
            imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/mbtN6V6y5kdawvAkzqN4ohi576a.jpg",
            genre = GenreDto("Боевик", 28),
            releaseDate = "08.07.21",
            actors = listOf(
                ActorDto("Scarlett Johansson","https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6NsMbJXRlDZuDzatN2akFdGuTvx.jpg"),
                ActorDto("Florence Pugh","https://www.themoviedb.org/t/p/w600_and_h900_bestv2/75PvULemW8BvheSKtPMoBBsvPLh.jpg"),
                ActorDto("Rachel Weisz","https://www.themoviedb.org/t/p/w600_and_h900_bestv2/3QbFXeiUzXUVUrJ7fdiCn7A7ReW.jpg"))
        ),
        MovieDto(
            id = 9,
            title = "Люцифер",
            description = "Заскучавший и несчастный повелитель преисподней, Люцифер Морнингстар оставил свой престол и отправился в современный Лос-Анджелес, где основал ночной клуб «Lux». Очаровательный, харизматичный и дьявольски привлекательный Люцифер наслаждается своим отдыхом — вином, женщинами и музыкой — пока красивая поп-звезда не оказывается убитой на пороге его клуба.",
            rateScore = 4,
            ageRestriction = 18,
            imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/z24OWggvuqkmB0xmtgChXbl9THw.jpg",
            genre = GenreDto("Криминал", 80),
            releaseDate = "10.09.21",
            actors = listOf(
                ActorDto("Tom Ellis","https://www.themoviedb.org/t/p/w600_and_h900_bestv2/sJkxqJfSgcwussMeywxyrnYxVX.jpg"),
                ActorDto("Lauren German","https://www.themoviedb.org/t/p/w600_and_h900_bestv2/if1TbO8dSfPiDCMfy52nH7A2D7.jpg"),
                ActorDto("Kevin Alejandro","https://www.themoviedb.org/t/p/w600_and_h900_bestv2/bh4aQqP7kJzL2Ls9tj5OmhsBlqi.jpg"))
        ),
        MovieDto(
            id = 10,
            title = "Война будущего",
            description = "В будущем идёт разрушительный конфликт с инопланетной расой. В попытке переломить ход войны учёные начинают призывать в свою армию солдат из прошлого.",
            rateScore = 4,
            ageRestriction = 13,
            imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/jdzuxuA05lW4DzedZqa43SYhaZ.jpg",
            genre = GenreDto("Боевик", 28),
            releaseDate = "01.07.21",
            actors = listOf(
                ActorDto("Chris Pratt","https://www.themoviedb.org/t/p/w600_and_h900_bestv2/gXKyT1YU5RWWPaE1je3ht58eUZr.jpg"),
                ActorDto("Yvonne Strahovski","https://www.themoviedb.org/t/p/w600_and_h900_bestv2/wio1VaQDOggDfPOTJf2vxGfooxZ.jpg"),
                ActorDto("J.K. Simmons","https://www.themoviedb.org/t/p/w600_and_h900_bestv2/7kIiPojgSVNRXb5z0hiijcD5LJ6.jpg"))
        ),
        MovieDto(
            id = 11,
            title = "Неспящие",
            description = "В результате масштабного события люди теряют способность спать. Бывшая военнослужащая с трудным прошлым пытается спасти свою семью, пока мир погружается в хаос.",
            rateScore = 3,
            ageRestriction = 18,
            imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/dNC2Tu8Hhmzl865uH9YR9hCt8Us.jpg",
            genre = GenreDto("Драма", 18),
            releaseDate = "09.06.21",
            actors = listOf(
                ActorDto("Tom Ellis","https://www.themoviedb.org/t/p/w600_and_h900_bestv2/sJkxqJfSgcwussMeywxyrnYxVX.jpg"),
                ActorDto("Lauren German","https://www.themoviedb.org/t/p/w600_and_h900_bestv2/if1TbO8dSfPiDCMfy52nH7A2D7.jpg"),
                ActorDto("Kevin Alejandro","https://www.themoviedb.org/t/p/w600_and_h900_bestv2/bh4aQqP7kJzL2Ls9tj5OmhsBlqi.jpg"))
        ),
        MovieDto(
            id = 12,
            title = "Заступник",
            description = "Джим был лучшим профессиональным снайпером, но теперь он оставил войны позади и ведёт уединённую мирную жизнь. Покою приходит конец, когда он решает вступиться за беззащитного мальчика, случайно ставшего свидетелем преступлений могущественного наркокартеля. С этого момента Джиму предстоит в одиночку противостоять киллерам, используя все свои боевые навыки.",
            rateScore = 4,
            ageRestriction = 13,
            imageUrl = "Джим был лучшим профессиональным снайпером, но теперь он оставил войны позади и ведёт уединённую мирную жизнь. Покою приходит конец, когда он решает вступиться за беззащитного мальчика, случайно ставшего свидетелем преступлений могущественного наркокартеля. С этого момента Джиму предстоит в одиночку противостоять киллерам, используя все свои боевые навыки.",
            genre = GenreDto("Боевик", 28),
            releaseDate = "04.02.21",
            actors = listOf(
                ActorDto("Chris Pratt","https://www.themoviedb.org/t/p/w600_and_h900_bestv2/gXKyT1YU5RWWPaE1je3ht58eUZr.jpg"),
                ActorDto("Yvonne Strahovski","https://www.themoviedb.org/t/p/w600_and_h900_bestv2/wio1VaQDOggDfPOTJf2vxGfooxZ.jpg"),
                ActorDto("J.K. Simmons","https://www.themoviedb.org/t/p/w600_and_h900_bestv2/7kIiPojgSVNRXb5z0hiijcD5LJ6.jpg"))
        )
    )
}