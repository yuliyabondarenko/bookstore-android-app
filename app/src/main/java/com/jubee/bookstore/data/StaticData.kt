package com.jubee.bookstore.data

import com.jubee.bookstore.model.BookModel

object StaticData {
    var BOOK_ITEMS: List<BookModel> = listOf(
        BookModel(1, "Юлий Алкин \"Цена познания\"", null, false, 12.3),
        BookModel(2, "Оноре де Бальзак \"Тридцатилетняя женщина\"", null, true, 10.2),
        BookModel(3, "Трейси Брайан \"Оставьте брезгливость, съешьте лягушку!\"", null, true, 17.4),
        BookModel(4, "Рей Бредбери - \"451 градус по Фаренгейту\"", null, true, 53.34)
    )
}

//INSERT INTO book (id, name, price, photo)
//VALUES (1, 'Юлий Алкин "Цена познания"', 12.3, 'http://fb2-epub.ru/Fantstic/Alkin/Tsiena_poznaniia-Iurii_Alkin.jpg');
//INSERT INTO book (id, name, price, absent, photo)
//VALUES (2, 'Оноре де Бальзак "Тридцатилетняя женщина"', 10.2, true, 'http://irecommend.ru/sites/default/files/product-images/75276/ZtWWyVa21Kvl4bYJFfvg.jpg');
//INSERT INTO book (id, name, price, absent, photo)
//VALUES (3, 'Трейси Брайан "Оставьте брезгливость, съешьте лягушку!"',  17.4, true,'https://erudyt.com.ua/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/1/0/1004187.jpg');
//INSERT INTO book (id, name, price, photo)
//VALUES (4,  'Рей Бредбери - "451 градус по Фаренгейту"', 53.34, 'http://loveread.ec/img/photo_books/2039.jpg');
//INSERT INTO book (id, name, price, photo)
//VALUES (5, 'Оскар Уайльд - "Портрет Дориана Грея"', 54.4, 'https://audioknigi.club/uploads/topics/preview/00/01/50/05/601970b1ad.jpg');
//INSERT INTO book (id, name, price, photo)
//VALUES (6, 'Антуан Франсуа Прево "История кавалера де Грие и Манон Леско"', 11.44, 'https://i.livelib.ru/boocover/1000025434/200/ea35/AntuanFransua_Prevo__Istoriya_kavalera_de_Grie_i_Manon_Lesko.jpg');
//INSERT INTO book (id, name, price, photo)
//VALUES (7, 'Владимир Набоков "Лолита"', 41.44, 'https://i.livelib.ru/boocover/1000428291/200/5fe4/Vladimir_Nabokov__Lolita.jpg');
//INSERT INTO book (id, name, price, photo)
//VALUES (8, 'Виктор Гюго "Собор Парижской Богоматери"', 11.3, 'https://bookz.ru/pics/sobor-pa_507.jpg');
//INSERT INTO book (id, name, price, photo)
//VALUES (9, 'Фарли Моуэт "Never cry wolf" (Не кричи: «Волки!»)"', 11.97, 'https://i.livelib.ru/boocover/1000569470/200/b479/Farli_Mouet__Ne_krichi_volki.jpg');
