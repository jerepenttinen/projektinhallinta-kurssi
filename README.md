# projektinhallinta-kurssi

github alusta:
https://github.com/jerepenttinen/projektinhallinta-kurssi

projektihallinta:
https://unimous.youtrack.cloud


## Vaatimukset - Linux ympäristö

### frontend - React (NodeJs)
#### install
    sudo apt install node
#### verify version
    node --version


### Backend - Spring boot (JAVA)

#### install
    sudo apt-get update
    sudo apt-get upgrade
    apt install openjdk-17-jdk openjdk-17-jre

#### verify install

    java -version

### DB - Postgresql
    TODO

## Ohjelmointi ympäristö
itse en vaadi teiltä minkäänlaista sitoutumista IDEn kanssa, mutta Intellij Idea on tehty Javakehitykseen ja sisältää automaattisesti maven pluginin jolla backend ympäristöön vaaditut kirjastot latautuvat maven pluginin kautta niin siksi suosittelen että käytätte kyseistä IDEä projektissa koska osaan itsekkin silloin auttaa jos tulee ongelmia.

#### Suosistus: Intellij Idea community
 Muita: Visual Studio Code

## Projektin alustus

### projektin kloonaus:
HUOM. olethan asettanut laitteesi ssh avaimen github profiiliisi! muuten alla oleva rimpsu ei toimi

    git clone git@github.com:jerepenttinen/projektinhallinta-kurssi.git
### backend alustus - Intellij Idea
1. avaa Intellij Idea IDEllä {projektikansio}. Intellij indeksoi hetken projekti kansiota kun lukee koodin jotta osaa värjätä ympäristössä olevan koodin
2. näet projektikansion avattua IDEn oikeassa reunassa "Maven" -osion. Avaa reuna palkki "Maven".
3. Maven reunapalkissa pitäisi olla Profiles ja projektitiedosto nimi "cookbook". Avaa cookbook tiedostossa oleva lifecycle kansio.
4. **lifecycles** kansio sisältää siis maven pluginin toiminnot
5. aja **clean**. odota että alaindeksissä oleva ajoikkuna on ajanut itsensä läpi.
6. aja **install**. odota että alaindeksissä oleva ajoikkuna on ajanut itsensä läpi.
7. Yläindeksissä näkyy vasara jonka vieressä pitäisi olla täppä jossa pitäisi lukea spring boot. mikäli ei lue niin avaa täppä ja paina 'edit configurations'
   1. ikkunan vasemmassa laidassa paina "+" add configurations
   2. etsi listasta spring boot
   3. aseta tiedot oikein (java ver pitäisi olla 17 ja main functio backend projektista)
8. paina vihreää play nappia jolloin projektin pitäisi käynnistyä
   1. alaindeksiin ilmestyy VM commandline jossa pyörii javaympäristö
   2. virhetilanteessa alaindeksistä näkyy error stack
   3. jos virheitä ei syntynyt niin projektille pitäisi syntyä portti lokaaliin ympäristöön


    htttp://localhost:8080
### Frontend alustus - cli
#### dependencies lataaminen
    npm install

#### testi serverin käynnistys
    npm start

## Projektihallinnan käytännöt
    TODO

## Projektihallinnan 
    TODO