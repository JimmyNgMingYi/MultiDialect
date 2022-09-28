package com.example.multidialect;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Database {
    public List<ListQuestions> listOfquestions;

    public List<ListQuestions> databaseNumbers()
    {
        listOfquestions = new ArrayList<>();
        listOfquestions.add(new ListQuestions("Q1: 零 (rei)",1,"0","5","3","15"));
        listOfquestions.add(new ListQuestions("Q2: いち (ichi)",2,"6","1","9","10"));
        listOfquestions.add(new ListQuestions("Q3: に (ni)",2,"55","2","66","44"));
        listOfquestions.add(new ListQuestions("Q4: さん (san)",4,"12","72","2","3"));
        listOfquestions.add(new ListQuestions("Q5: じゅう (juu)",4,"7","13","22","10"));
        listOfquestions.add(new ListQuestions("Q6: じゅうろ (Jū-roku)",2,"13","16","12","5"));
        listOfquestions.add(new ListQuestions("Q7: じゅうはち (Jū-hachi)",1,"18","11","5","3"));
        listOfquestions.add(new ListQuestions("Q8: にじゅう (Ni-jū)",4,"17","8","4","20"));
        listOfquestions.add(new ListQuestions("Q9: ひゃくいち (hyaku-ichi)",3,"44","38","101","87"));
        listOfquestions.add(new ListQuestions("Q10: ひゃくよんじゅうご (hyaku-yon-ju go)",2,"55","145","25","99"));
        listOfquestions.add(new ListQuestions("Q11: ひゃくきゅうじゅうきゅう (hyaku-kyu-ju kyu) ",1,"199","15","88","122"));
        return listOfquestions;
    }

    public List<ListQuestions> databaseGreetings()
    {
        listOfquestions = new ArrayList<>();
        listOfquestions.add(new ListQuestions("おはようございます (Ohayou-gozaimasu)",1,"Good morning","Good night","farewell","welcome back"));
        listOfquestions.add(new ListQuestions("こんにちは (Kon’nichiwa)",2,"Thank you","Hello","Good evening/hello (during evening/night)","I have come back now"));
        listOfquestions.add(new ListQuestions("ありがとうございます (Arigatou gozaimasu)",2,"please go and come back","Thank you","if it is so, farewell","Good evening/hello"));
        listOfquestions.add(new ListQuestions("行ってきます (Ittekimasu)",4,"please go and come back","if it is so, farewell","welcome back","I’ll go and come back "));
        listOfquestions.add(new ListQuestions("行ってらしゃい (itterasshai)",4,"Thank you","Hello","farewell","please go and come back"));
        listOfquestions.add(new ListQuestions("ようこそ (Yōkoso)",4,"Thank you for your hard work","if it is so, farewell","Welcome (service staff greeting)","Welcome"));
        return listOfquestions;
    }

    public List<ListQuestions> databaseJobs()
    {
        listOfquestions = new ArrayList<>();
        listOfquestions.add(new ListQuestions("かんごし (kango shi)",1,"Nurse","Scientist","Female Nurse","Artist"));
        listOfquestions.add(new ListQuestions("きょうし (kyoushi)",2,"Driver","School Teacher","Writer","Singer"));
        listOfquestions.add(new ListQuestions("うんてんしゅ (unten shu)",2,"Writer","Driver","Hairdresser","Soccer Player"));
        listOfquestions.add(new ListQuestions("さっか (sakka)",4,"Hairdresser","Dentist","Doctor","Writer"));
        listOfquestions.add(new ListQuestions("げいじゅつか (geijutsu ka)",4,"Painter","School Teacher","Dentist","Artist"));
        listOfquestions.add(new ListQuestions("せんせい (sensei)",4,"Photographer","Baseball Player","Nurse","Teacher"));
        return listOfquestions;
    }

    public List<ListQuestions> databaseCountries()
    {
        listOfquestions = new ArrayList<>();
        listOfquestions.add(new ListQuestions("えいこく (ei koku)",1,"Great Britain","Bahrain","Bahamas","Brazil"));
        listOfquestions.add(new ListQuestions("ちゅうごく (chuu goku)",2,"Canada","China","Albania","Algeria"));
        listOfquestions.add(new ListQuestions("ブラジル (bu ra ji ru)",2,"Brunei","Brazil","Bahrain","Bangladesh"));
        listOfquestions.add(new ListQuestions("オーストリア (o- su to ri a)",4,"Africa","America (USA) ","Australia","Austria"));
        listOfquestions.add(new ListQuestions("バーレーン (ba- re-n)",4,"Brunei","Bahamas","Bangladesh","Bahrain"));
        listOfquestions.add(new ListQuestions("シンガポール (shin ga po- ru)",4,"Argentina","Australia","China","Singapore"));
        return listOfquestions;
    }

    public List<ListQuestions> databaseHobbies()
    {
        listOfquestions = new ArrayList<>();
        listOfquestions.add(new ListQuestions("楽器演奏 (gakki ensō)",1,"playing an instrument","computer programming","reading","stargazing"));
        listOfquestions.add(new ListQuestions("クラシックバレエ (kurashikku barē)",2,"video games","dancing classic ballet","making accessories","making music"));
        listOfquestions.add(new ListQuestions("お菓子作り (okashi zukuri)",2,"making accessories","making candy","making music","drawing"));
        listOfquestions.add(new ListQuestions("読書 (dokusho)",4,"board games","jogging","journaling","reading"));
        listOfquestions.add(new ListQuestions("テレビゲーム (terebi gēmu)",4,"journaling","fashion","board games","video games"));
        listOfquestions.add(new ListQuestions("プログラミング (puroguramingu)",4,"stargazing","trading cards","video games","computer programming"));
        return listOfquestions;
    }

    public List<ListQuestions> databaseFamilies()
    {
        listOfquestions = new ArrayList<>();
        listOfquestions.add(new ListQuestions("そふ (sofu)",1,"Grandfather","Grandmother","Uncle","Sisters"));
        listOfquestions.add(new ListQuestions("あに (ani)",2,"Older Sister","Older Brother","Younger Sister","Younger Brother"));
        listOfquestions.add(new ListQuestions("しゅじん (shujin)",2,"Wife","Husband","Mother","Married Couple"));
        listOfquestions.add(new ListQuestions("ちち (chichi)",4,"Mother","Wife","Younger Sister","Father"));
        listOfquestions.add(new ListQuestions("きょうだい (kyoudai)",4,"Sisters","Older Sister","Married Couple","Brothers"));
        listOfquestions.add(new ListQuestions("おば (oba)",4,"Mother","Married Couple","Wife","Aunt"));
        return listOfquestions;
    }

    public List<ListQuestions> databaseFood()
    {
        listOfquestions = new ArrayList<>();
        listOfquestions.add(new ListQuestions("にほんりょうり (nihon ryouri)",1,"Japanese Cuisine","Sushi","Tempura","Snack"));
        listOfquestions.add(new ListQuestions("ごはん (gohan)",2,"Side Dish","Cooked Rice","Box Lunch","Sliced Raw Fish "));
        listOfquestions.add(new ListQuestions("ぎゅうどん  (gyuu don)",2,"Rice topped with Deep-fried Prawns & Fishes","Rice topped with Beef and Vegetables","Side Dish","Rice topped with Boiled Chicken and Eggs"));
        listOfquestions.add(new ListQuestions("おかず (okazu)?",4,"Snack","Supper","Breakfast","Side Dish"));
        listOfquestions.add(new ListQuestions("えきべん (ekiben)",4,"Lunch","Japanese Cuisine","Supper","Train Station Box Lunch"));
        listOfquestions.add(new ListQuestions("ちょうしょく (chou shoku)",4,"Dinner","Lunch","Supper","Breakfast"));
        return listOfquestions;
    }

    public List<ListQuestions> databaseGestures()
    {
        listOfquestions = new ArrayList<>();
        listOfquestions.add(new ListQuestions("わたし (Watashi)",1,"Me","Come here","Come come","please"));
        listOfquestions.add(new ListQuestions("だめ (dame)",2,"angry","not allowed","irritated","I don’t know"));
        listOfquestions.add(new ListQuestions("きてきて (kitte kitte)",2,"Come here","Come come ","Cute","Lover"));
        listOfquestions.add(new ListQuestions("会釈 (Eshaku)",4,"Deep apology","Eye Contact","long time no see","Saluting"));
        listOfquestions.add(new ListQuestions("敬礼 (Keirei)",4,"irritated","Deep apology","long time no see","Respectful saluting"));
        listOfquestions.add(new ListQuestions("目礼 (Mokurei)",4,"please","Lover","Respectful saluting","Eye Contact"));
        return listOfquestions;
    }

}
