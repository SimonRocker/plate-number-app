package simon.test;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.text.InputFilter;
import android.widget.Toast;


import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;


public class DBMainActivity extends AppCompatActivity {

    private boolean doubleBackToExitPressedOnce = false;
    private GoogleApiClient client;
    private DbSource dataSource;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataSource = new DbSource(this);

        dataSource.open();

        setupDB();
        dataSource.close();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        EditText text = (EditText) findViewById(R.id.Eingabe);
        text.setFilters(new InputFilter[]{new InputFilter.AllCaps()});

        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText text = (EditText) findViewById(R.id.Eingabe);
                String str = text.getText().toString();
                if (str.length() > 17 || str.length() < 1 || str.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(DBMainActivity.this);
                    builder.setTitle(R.string.nameAlert)
                            .setMessage(R.string.alert)
                            .setCancelable(false)
                            .setNegativeButton(R.string.buttonAlert, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    //AlertDialog alert = builder.create();
                    builder.show();
                } else {

                    Intent intent = new Intent(DBMainActivity.this, DBSuche.class);
                    intent.putExtra("EingabeString", str);
                    startActivity(intent);
                }
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if (id == R.id.action_settings) {
            Intent intent = new Intent(DBMainActivity.this, DBEinstellungen.class);
            startActivity(intent);
            //Intent zu einer neuen Activity, welche dann entscheidet, ob Maps eingebelendet wird
            //wenn ja, welche Zoomstufe etc....
        }
        if (id == R.id.action_history) {
            Intent intent = new Intent(DBMainActivity.this, DBVerlauf.class);
            startActivity(intent);
            //Intent zu neuer Activity, Liste, die die Suchergebnisse speichert, Löschen ist möglich
            //einzelne Einträge und ganze Liste, falls leer soll entsprechender Inhalt angezeigt werden
        }
        if (id == R.id.action_impressum) {
            Intent intent = new Intent(DBMainActivity.this, Impressum.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    public void setupDB() {

        String[][] array = new String[][]{
                {"0", "Auto der BehördenBundespräsident etc.)"},
                {"1", "Auto des BundespräsidentenKennzeichen 1-1)"},
                {"A", "Augsburg in Bayern"},
                {"AA", "Aalen in Baden-Württemberg"},
                {"AB", "Aschaffenburg in Bayern"},
                {"ABG", "Altenburg in Thüringen"},
                {"ABI", "Anhalt-Bitterfeld in Sachsen-Anhalt"},
                {"AC", "Aachen in Nordrhein-Westfalen"},
                {"AE", "Auerbach/Vogtl. in Sachsen"},
                {"AH", "Ahaus in Nordrhein-Westfalen"},
                {"AIB", "Bad Aibling in Bayern"},
                {"AIC", "Aichach in Bayern"},
                {"AK", "Altenkirchen(Westerwald) in Rheinland-Pfalz"},
                {"ALF", "Alfeld(Leine) in Niedersachsen"},
                {"ALZ", "Alzenau in Bayern"},
                {"AM", "Amberg in Bayern"},
                {"AN", "Ansbach in Bayern"},
                {"ANA", "Annaberg-Buchholz in Sachsen"},
                {"ANG", "Angermünde in Brandenburg"},
                {"ANK", "Anklam in Mecklenburg-Vorpommern"},
                {"AÖ", "Altötting in Bayern"},
                {"AP", "Apolda in Thüringen"},
                {"APD", "Apolda in Thüringen"},
                {"ARN", "Arnstadt in Thüringen"},
                {"ART", "Artern in Thüringen"},
                {"AS", "Amberg-Sulzbach in Bayern"},
                {"ASL", "Aschersleben in Sachsen-Anhalt"},

                {"ASZ",
                        "Aue-Schwarzenberg in Sachsen"},

                {"AT",
                        "Altentreptow in Mecklenburg-Vorpommern"},

                {"AUR",
                        "Aurich in Niedersachsen"},
                {"AU", "Aue in Sachsen"},

                {"AW",
                        "Ahrweiler in Rheinland-Pfalz"},

                {"AZ",
                        "Alzey in Rheinland-Pfalz"},

                {"AZE",
                        "Anhalt-Zerbst in Sachsen-Anhalt"},


                {"B",
                        "Berlin/ Diplomatenkennzeichen (wenn danach eine Nummer folgt)"},
                /**Problem bei Maps*/
                {"BA",
                        "Bamberg in Bayern"},

                {"BAD",
                        "Baden-Baden in Baden-Württemberg"},

                {"BAR",
                        "Barnim in Brandenburg"},

                {"BB",
                        "Böblingen in Baden-Württemberg"},

                {"BBG",
                        "Bernburg(Saale) in Sachsen-Anhalt"},

                {"BBL",
                        "Brandenburg/ Landesregierung und Landtag"},

                {"BC",
                        "Biberach an der Riß in Baden-Württemberg"},

                {"BCH",
                        "Buchen(Odenwald) in Baden-Württemberg"},

                {"BD",
                        "Auto des Bundes"},

                {"BE",
                        "Beckum in Nordrhein-Westfalen"},

                {"BED",
                        "Brand-Erbisdorf in Sachsen"},

                {"BER",
                        "Bernau bei Berlin in Brandenburg"},

                {"BF",
                        "Burgsteinfurt in Nordrhein-Westfalen"},

                {"BGD",
                        "Berchtesgaden in Bayern"},

                {"BGL",
                        "Berchtesgadener Land in Bayern"},

                {"BH",
                        "Bühl in Baden-Württemberg"},

                {"BI",
                        "Bielefeld in Nordrhein-Westfalen"},

                {"BID",
                        "Biedenkopf in Hessen"},

                {"BIN",
                        "Bingen in Rheinland-Pfalz"},

                {"BIR",
                        "Birkenfeld in Rheinland-Pfalz"},

                {"BIT",
                        "Bitburg in Rheinland-Pfalz"},

                {"BIW",
                        "Bischofswerda in Sachsen"},

                {"BK",
                        "Backnang in Baden-Württemberg"},

                {"BKS",
                        "Bernkastel in Rheinland-Pfalz"},

                {"BL",
                        "Balingen in Baden-Württemberg"},

                {"BLB",
                        "Berleburg in Nordrhein-Westfalen"},

                {"BLK",
                        "Burgenlandkreis in Sachsen-Anhalt"},

                {"BM",
                        "Bergheim in Nordrhein-Westfalen"},

                {"BN",
                        "Bonn in Nordrhein-Westfalen"},

                {"BNA",
                        "Borna in Sachsen"},

                {"BO",
                        "Bochum in Nordrhein-Westfalen"},

                {"BÖ",
                        "Börde in Sachsen-Anhalt"},

                {"BOH",
                        "Bocholt in Nordrhein-Westfalen"},

                {"BOR",
                        "Borken in Nordrhein-Westfalen"},

                {"BOT",
                        "Bottrop in Nordrhein-Westfalen"},

                {"BP",
                        "Bundespolizei bundesweit"},

                {"BRA",
                        "Brake in Niedersachsen"},

                {"BRB",
                        "Brandenburg in Brandenburg"},

                {"BRG",
                        "Burg in Sachsen-Anhalt"},

                {"BRK",
                        "Brückenau in Bayern"},

                {"BRL",
                        "Braunlage in Niedersachsen"},

                {"BRV",
                        "Bremervörde in Niedersachsen"},

                {"BS",
                        "Braunschweig in Niedersachsen"},

                {"BT",
                        "Bayreuth in Bayern"},

                {"BTF",
                        "Bitterfeld in Sachsen-Anhalt"},

                {"BÜD",
                        "Büdingen in Hessen"},

                {"BUL",
                        "Burglengenfeld in Bayern"},

                {"BÜR",
                        "Büren in Nordrhein-Westfalen"},

                {"BÜS",
                        "Büsingen in Baden-Württemberg"},

                {"BÜZ",
                        "Bützow in Mecklenburg-Vorpommern"},

                {"BZ",
                        "Bautzen in Sachsen"},


                {"C",
                        "Chemnitz in Sachsen"},

                {"CA",
                        "Calau in Brandenburg"},

                {"CAS",
                        "Castrop in Nordrhein-Westfalen"},

                {"CB",
                        "Cottbus in Brandenburg"},

                {"CE",
                        "Celle in Niedersachsen"},

                {"CHA",
                        "Cham in Bayern"},

                {"CLP",
                        "Cloppenburg in Niedersachsen"},

                {"CLZ",
                        "Clausthal-Zellerfeld in Niedersachsen"},

                {"CO",
                        "Coburg in Bayern"},

                {"COC",
                        "Cochem in Rheinland-Pfalz"},

                {"COE",
                        "Coesfeld in Nordrhein-Westfalen"},

                {"CR",
                        "Crailsheim in Baden-Württemberg"},

                {"CUX",
                        "Cuxhaven in Niedersachsen"},

                {"CW",
                        "Calw in Baden-Württemberg"},


                {"D",
                        "Düsseldorf in Nordrhein-Westfalen"},

                {"DA",
                        "Darmstadt in Hessen"},

                {"DAH",
                        "Dachau in Bayern"},

                {"DAN",
                        "Dannenberg in Niedersachsen"},

                {"DAU",
                        "Daun in Rheinland-Pfalz"},

                {"DBR",
                        "Doberan/Rostock in Mecklenburg-Vorpommern"},

                {"DD",
                        "Dresden(Stadt/Polizei) in Sachsen"},

                {"DE",
                        "Dessau(-Roßlau) in Sachsen-Anhalt"},

                {"DEG",
                        "Deggendorf in Bayern"},

                {"DEL",
                        "Delmenhorst in Niedersachsen"},

                {"DGF",
                        "Dingolfing in Bayern"},

                {"DH",
                        "Diepholz in Niedersachsen"},

                {"DI",
                        "Dieburg in Hessen"},

                {"DIL",
                        "Dillenburg in Hessen"},

                {"DIN",
                        "Dinslaken in Nordrhein-Westfalen"},

                {"DIZ",
                        "Diez in Rheinland-Pfalz"},

                {"DKB",
                        "Dinkelsbühl in Bayern"},

                {"DL",
                        "Döbeln in Sachsen"},

                {"DLG",
                        "Dillingen an der Donau in Bayern"},

                {"DM",
                        "Demmin in Mecklenburg-Vorpommern"},

                {"DN",
                        "Düren in Nordrhein-Westfalen"},

                {"DO",
                        "Dortmund in Nordrhein-Westfalen"},

                {"DON",
                        "Donauwörth in Bayern"},

                {"DU",
                        "Duisburg in Nordrhein-Westfalen"},

                {"DUD",
                        "Duderstadt in Niedersachsen"},

                {"DÜW",
                        "Dürkheim and der Weinstraße in Rheinland-Pfalz"},

                {"DW",
                        "Dippoldiswalde in Sachsen"},

                {"DZ",
                        "Delitzsch in Sachsen"},


                {"E",
                        "Essen in Nordrhein-Westfalen"},

                {"EA",
                        "Eisenach in Thüringen"},

                {"EB",
                        "Eilenburg in Sachsen"},

                {"EBE",
                        "Ebersberg in Bayern"},

                {"EBN",
                        "Ebern in Bayern"},

                {"EBS",
                        "Ebermannstadt in Bayern"},

                {"ECK",
                        "Eckernförde in Schleswig-Holstein"},

                {"ED",
                        "Erding in Bayern"},

                {"EE",
                        "Elbe-Elster in Brandenburg"},

                {"EF",
                        "Erfurt in Thüringen"},

                {"EG",
                        "Eggenfelden in Bayern"},

                {"EH",
                        "Eisenhüttenstadt in Brandenburg"},

                {"EI",
                        "Eichstätt in Bayern"},

                {"EIC",
                        "Eichsfeld in Thüringen"},

                {"EIL",
                        "Elsleben in Sachsen-Anhalt"},

                {"EIN",
                        "Einbeck in Niedersachsen"},

                {"EIS",
                        "Eisenberg in Thüringen"},

                {"EL",
                        "Emsland in Niedersachsen"},

                {"EM",
                        "Emmendingen in Baden-Württemberg"},

                {"EMD",
                        "Emden in Niedersachsen"},

                {"EMS",
                        "Ems in Rheinland-Pfalz"},

                {"EN",
                        "Ennepe in Nordrhein-Westfalen"},

                {"ER",
                        "Erlangen in Bayern"},

                {"ERB",
                        "Erbach in Hessen"},

                {"ERH",
                        "Erlangen-Höchstadt in Bayern"},

                {"ERK",
                        "Erkelenz in Nordrhein-Westfalen"},

                {"ERZ",
                        "Erzgebirge in Sachsen"},

                {"ES",
                        "Esslingen in Baden-Württemberg"},

                {"ESB",
                        "Eschenbach in Bayern"},

                {"ESW",
                        "Eschenwege in Hessen"},

                {"EU",
                        "Euskirchen in Nordrhein-Westfalen"},

                {"EW",
                        "Ebernwalde in Brandenburg"},


                {"F",
                        "Frankfurt am Main in Hessen"},

                {"FB",
                        "Friedberg in Hessen"},

                {"FD",
                        "Fulda in Hessen"},

                {"FDB",
                        "Friedberg in Bayern"},

                {"FDS",
                        "Freudenstadt in Baden-Württemberg"},

                {"FEU",
                        "Feuchtwangen in Bayern"},

                {"FF",
                        "Frankfurt(Oder) in Brandenburg"},

                {"FFB",
                        "Fürstenfeldbruck in Bayern"},

                {"FG",
                        "Freiberg in Sachsen"},

                {"FI",
                        "Finsterwalde in Brandenburg"},

                {"FKB",
                        "Frankenberg in Hessen"},

                {"FL",
                        "Flensburg in Schleswig-Holstein"},

                {"FLÖ",
                        "Flöha in Sachsen"},

                {"FN",
                        "Friedrichshafen in Baden-Württemberg"},

                {"FO",
                        "Forchheim in Bayern"},

                {"FOR",
                        "Forst in Brandenburg"},

                {"FR",
                        "Freiburg in Baden-Württemberg"},

                {"FRG",
                        "Freyung-Grafenau in Bayern"},

                {"FRI",
                        "Friesland in Niedersachsen"},

                {"FRW",
                        "Freienwalde in Brandenburg"},

                {"FS",
                        "Freising in Bayern"},

                {"FT",
                        "Frankenthal in Rheinland-Pfalz"},

                {"FTL",
                        "Freital in Sachsen"},

                {"FÜ",
                        "Fürth in Bayern"},

                {"FÜS",
                        "Füssen in Bayern"},

                {"FZ",
                        "Fritzlar in Hessen"},


                {"G",
                        "Gera in Thüringen"},

                {"GA",
                        "Gardelegen in Sachsen-Anhalt"},

                {"GAN",
                        "Gandersheim in Niedersachsen"},

                {"GAP",
                        "Garmisch-Partenkirchen in Bayern"},

                {"GC",
                        "Glauchau in Sachsen"},

                {"GD",
                        "Gmünd in Baden-Württemberg"},

                {"GDB",
                        "Gadebusch in Mecklenburg-Vorpommern"},

                {"GE",
                        "Gelsenkirchen in Nordrhein-Westfalen"},

                {"GEL",
                        "Geldern in Norhrhein-Westfalen"},

                {"GEO",
                        "Gerolzhofen in Bayern"},

                {"GER",
                        "Germersheim in Rheinland-Pfalz"},

                {"GF",
                        "Gifhorn in Niedersachsen"},

                {"GG",
                        "Groß-Gerau in Hessen"},

                {"GHA",
                        "Geithain in Sachsen"},

                {"GHC",
                        "Gräfenheinichen in Sachsen-Anhalt"},

                {"GI",
                        "Gießen in Hessen"},

                {"GK",
                        "Geilenkirchen in Nordrhein-Westfalen"},

                {"GL",
                        "Gladbach in Nordrhein-Westfalen"},

                {"GLA",
                        "Gladbeck in Nordrhein-Westfalen"},

                {"GM",
                        "Gummersbach in Nordrhein-Westfalen"},

                {"GMN",
                        "Grimmen in Mecklenburg-Vorpommern"},

                {"GN",
                        "Gelnhausen in Hessen"},

                {"GNT",
                        "Genthin in Sachsen-Anhalt"},

                {"GÖ",
                        "Göttingen in Niedersachsen"},

                {"GOA",
                        "Goar in Rheinland-Pfalz"},

                {"GP",
                        "Göppingen in Baden-Württemberg"},

                {"GR",
                        "Görlitz in Sachsen"},

                {"GRA",
                        "Gradfenau in Bayern"},

                {"GRH",
                        "Großenhain in Bayern"},

                {"GRI",
                        "Griesbach in Bayern"},

                {"GRM",
                        "Grimma in Sachsen"},

                {"GRZ",
                        "Greiz in Thüringen"},

                {"GS",
                        "Goslar in Niedersachsen"},

                {"GT",
                        "Gütersloh in Nordrhein-Westfalen"},

                {"GTH",
                        "Gotha in Thüringen"},

                {"GÜ",
                        "Güstrow in Mecklenburg-Vorpommern"},

                {"GUB",
                        "Guben in Brandenburg"},

                {"GUN",
                        "Gunzenhausen in Bayern"},

                {"GV",
                        "Grevenbroich in Nordrhein-Westfalen"},

                {"GVM",
                        "Grevesmühlen in Mecklenburg-Vorpommern"},

                {"GW",
                        "Greifswald in Mecklenburg-Vorpommern"},

                {"GZ",
                        "Günzburg in Bayern"},


                {"H",
                        "Hannover in Niedersachsen"},

                {"HA",
                        "Hagen in Nordrhein-Westfalen"},

                {"HAB",
                        "Hammelburg in Sachsen-Anhalt"},

                {"HAL",
                        "Halle Saale in Niedersachsen"},

                {"HAM",
                        "Hamm in Nordrhein-Westfalen"},

                {"HAS",
                        "Hassfurt in Bayern"},

                {"HB",
                        "Bremen in Bremen"},

                {"HBN",
                        "Hildburghausen in Thüringen"},

                {"HBS",
                        "Halberstadt in Sachsen-Anhalt"},

                {"HC",
                        "Hainichen in Sachsen"},

                {"HCH",
                        "Hechingen in Baden-Württemberg"},

                {"HD",
                        "Heidelberg in Baden-Württemberg"},

                {"HDH",
                        "Heidenheim in Baden-Württemberg"},

                {"HDL",
                        "Haldensleben in Sachsen-Anhalt"},

                {"HE",
                        "Helmstedt in Niedersachsen"},

                {"HEB",
                        "Hersbruck in Bayern"},

                {"HEF",
                        "Hersfeld in Hessen"},

                {"HEI",
                        "Helde in Schleswig-Holstein"},

                {"HEL",
                        "Hessischer Landtag"},

                {"HER",
                        "Herne in Nordrhein-Westfalen"},

                {"HET",
                        "Hettstedt in Sachsen-Anhalt"},

                {"HF",
                        "Herford in Nordrhein-Westfalen"},

                {"HG",
                        "Homburg in Hessen"},

                {"HGN",
                        "Hagenau in Mecklenburg-Vorpommern"},

                {"HGW",
                        "Hansestadt Greifswald in Mecklenburg-Vorpommern"},

                {"HH",
                        "Hamburg in Hamburg"},

                {"HHM",
                        "Hohenmölsen in Sachsen-Anhalt"},

                {"HI",
                        "Hildesheim in Niedersachsen"},

                {"HIG",
                        "Heiligenstadt in Thüringen"},

                {"HIP",
                        "Hilpoltstein in Bayern"},

                {"HK",
                        "Heidekreis in Niedersachsen"},

                {"HL",
                        "Hansestadt Lübeck in Schleswig-Holstein"},

                {"HM",
                        "Hammeln in Niedersachsen"},

                {"HMÜ",
                        "(Hannoversch) Münden in Niedersachsen"},

                {"HN",
                        "Heilbronn in Baden-Württemberg"},

                {"HO",
                        "Hof in Bayern"},

                {"HOG",
                        "Hofgeismar in Hessen"},

                {"HOH",
                        "Hofheim in Bayern"},

                {"HOL",
                        "Holzminden in Niedersachsen"},

                {"HOM",
                        "Homburg im Saarland"},

                {"HOR",
                        "Horb in Baden-Württemberg"},

                {"HÖS",
                        "Hochstadt in Bayern"},

                {"HOT",
                        "Hohenstein in Sachsen"},

                {"HP",
                        "Heppenheim in Hessen"},

                {"HR",
                        "Homberg in Hessen"},

                {"HRO",
                        "Hansestadt Rostock in Mecklenburg-Vorpommern"},

                {"HS",
                        "Heinsberg in Norhrhein-Westfalen"},

                {"HSK",
                        "Hochsauerlandkreis in Nordrhein-Westfalen"},

                {"HU",
                        "Hanau in Hessen"},

                {"HV",
                        "Havelberg in Sachsen-Anhalt"},

                {"HVL",
                        "Hevelland in Brandenburg"},

                {"HWI",
                        "Hansestadt Wismar in Mecklenburg-Vorpommern"},

                {"HX",
                        "Höxter in Nordrhein-Westfalen"},

                {"HY",
                        "Hoyerswerda in Sachsen"},

                {"HZ",
                        "Harz in Sachsen-Anhalt"},


                {"IGB",
                        "Ingbert im Saarland"},

                {"IK",
                        "Ilm-Kreis in Thüringen"},

                {"IL",
                        "Ilmenau in Thüringen"},

                {"ILL",
                        "Illertissen in Bayern"},

                {"IN",
                        "Ingolstadt in Bayern"},

                {"IZ",
                        "Itzehoe in Schleswig-Holstein"},

                /**Section J*/
                {"J",
                        "Jena in Thüringen"},

                {"JE",
                        "Jessen in Sachsen-Anhalt"},

                {"JL",
                        "Jerichower Land in Sachsen-Anhalt"},

                {"JÜL",
                        "Jülich in Nordrhein-Westfalen"},

                /**Section K*/
                {"K",
                        "Köln in Nordrhein-Westfalen"},

                {"KA",
                        "Karlsruhe in Baden-Württemberg"},

                {"KB",
                        "Korbach in Hessen"},

                {"KC",
                        "Kronach in Bayern"},

                {"KE",
                        "Kempten in Bayern"},

                {"KEH",
                        "Kelheim in Bayern"},

                {"KEL",
                        "Kehl in Baden-Württemberg"},

                {"KEM",
                        "Kemnath in Bayern"},

                {"KF",
                        "Kaufbeuren in Bayern"},

                {"KG",
                        "Kissingen in Bayern"},

                {"KH",
                        "Kreuznach in Rheinland-Pfalz"},

                {"KI",
                        "Kiel in Schleswig-Holstein"},

                {"KIB",
                        "Kirchheim-Bolanden in Rheinland-Pfalz"},

                {"KK",
                        "Kempten-Krefeld in Nordrhein-Westfalen"},

                {"KL",
                        "Kaiserslautern in Rheinland-Pfalz"},

                {"KLE",
                        "Kleve in Nordrhein-Westfalen"},

                {"KLZ",
                        "Klötze in Sachsen-Anhalt"},

                {"KM",
                        "Kamenz in Sachsen"},

                {"KN",
                        "Konstanz in Baden-Württemberg"},

                {"KO",
                        "Koblenz in Rheinland-Pfalz"},

                {"KÖN",
                        "Königshofen in Bayern"},

                {"KÖT",
                        "Köthen in Sachsen-Anhalt"},

                {"KÖZ",
                        "Kötzting in Bayern"},

                {"KR",
                        "Krefeld in Nordrhein-Westfalen"},

                {"KRU",
                        "Krumbach in Bayern"},

                {"KS",
                        "Kassel in Hessen"},

                {"KT",
                        "Kitzingen in Bayern"},

                {"KU",
                        "Kulmbach in Bayern"},

                {"KÜN",
                        "Künzelsau in Baden-Württemberg"},

                {"KUS",
                        "Kusel in Rheinland-Pfalz"},

                {"KW",
                        "Königs Wusterhausen in Brandenburg"},

                {"KY",
                        "Kyritz in Brandenburg"},

                {"KYF",
                        "Kyffhäuser in Thüringen"},


                {"L",
                        "Leipzig in Sachsen"},

                {"LA",
                        "Landshut in Bayern"},

                {"LAN",
                        "Landau in Bayern"},

                {"LAU",
                        "Lauf in Bayern"},

                {"LB",
                        "Ludwigsburg in Baden-Württemberg"},

                {"LBS",
                        "Lobenstein in Thüringen"},

                {"LBZ",
                        "Lübz in Mecklenburg-Vorpommern"},

                {"LC",
                        "Luckau in Brandenburg"},

                {"LD",
                        "Landau in Rheinland-Pfalz"},

                {"LDK",
                        "Lahn-Dill-Kreis in Hessen"},

                {"LDS",
                        "Landkreis Dahme-Spreewald in Brandenburg"},

                {"LEO",
                        "Leonberg in Baden-Württemberg"},

                {"LER",
                        "Leer in Niedersachsen"},

                {"LEV",
                        "Leverkusen in Nordrhein-Westfalen"},

                {"LF",
                        "Laufen in Bayern"},

                {"LG",
                        "Lüneburg in Niedersachsen"},

                {"LH",
                        "Lüdinghausen in Nordrhein-Westfalen"},

                {"LI",
                        "Lindau in Bayern"},

                {"LIB",
                        "Liebenwerda in Brandenburg"},

                {"LIF",
                        "Lichtenfels in Bayern"},

                {"LIP",
                        "Lippe in Nordrhein-Westfalen"},

                {"LL",
                        "Landsberg am Lech in Bayern"},

                {"LM",
                        "Limburg in Hessen"},

                {"LN",
                        "Lübben in Brandenburg"},

                {"LÖ",
                        "Lörrach in Baden-Württemberg"},

                {"LÖB",
                        "Löbau in Sachsen"},

                {"LOS",
                        "Landkreis Oder-Spree in Brandenburg"},

                {"LP",
                        "Lipstadt in Nordrhein-Westfalen"},

                {"LR",
                        "Lahr in Baden-Württemberg"},

                {"LRO",
                        "Landkreis Rostock in Mecklenburg-Vorpommern"},

                {"LSA",
                        "Land Sachsen-Anhalt"},

                {"LSN",
                        "Landtag Sachsen"},

                {"LSZ",
                        "Langensalza in Thüringen"},

                {"LU",
                        "Ludwigshafen am Rhein in Rheinland-Pfalz"},

                {"LÜN",
                        "Lünen in Nordrhein-Westfalen"},

                {"LUP",
                        "Ludwigslust-Parchim in Mecklenburg-Vorpommern"},

                {"LWL",
                        "Ludwigslust in Mecklenburg-Vorpommern"},


                {"M",
                        "München in Bayern"},

                {"MA",
                        "Mannheim in Baden-Württemberg"},

                {"MAB",
                        "Marienberg in Sachsen"},

                {"MAI",
                        "Mainburg in Bayern"},

                {"MAK",
                        "Marktredwitz in Bayern"},

                {"MAL",
                        "Mallersdorf in Bayern"},

                {"MB",
                        "Miesbach in Bayern"},

                {"MC",
                        "Malchin in Mecklenburg-Vorpommern"},

                {"MD",
                        "Magdeburg in Sachsen-Anhalt"},

                {"ME",
                        "Mettmann in Nordrhein-Westfalen"},

                {"MED",
                        "Meldorf in Schleswig-Holstein"},

                {"MEG",
                        "Meisungen in Hessen"},

                {"MEI",
                        "Meißen in Sachsen"},

                {"MEK",
                        "Mittlerer Erzgebirgekreis in Sachsen"},

                {"MER",
                        "Merseburg in Sachsen-Anhalt"},

                {"MET",
                        "Mellrichstadt in Bayern"},

                {"MG",
                        "Mönchengladbach in Nordrhein-Westfalen"},

                {"MGH",
                        "Mergentheim in Baden-Württemberg"},

                {"MGN",
                        "Meiningen in Thüringen"},

                {"MH",
                        "Mülheim in Nordrhein-Westfalen"},

                {"MHL",
                        "Mühlhausen in Thüringen"},

                {"MI",
                        "Minden in Nordrhein-Westfalen"},

                {"MIL",
                        "Miltenberg in Bayern"},

                {"MK",
                        "Märkischer Kreis in Nordrhein-Westfalen"},

                {"MKK",
                        "Main-Kinzig-Kreis in Hessen"},

                {"ML",
                        "Mansfelder Land in Sachsen-Anhalt"},

                {"MM",
                        "Memmingen in Bayern"},

                {"MN",
                        "Mindelheim in Bayern"},

                {"MO",
                        "Moers in Nordrheinwestfalen"},

                {"MOD",
                        "Marktoberdorf in Bayern"},

                {"MOL",
                        "Märkisch-Oderland in Brandenburg"},

                {"MON",
                        "Monschau in Nordrhein-Westfalen"},

                {"MOS",
                        "Mosbach in Baden-Württemberg"},

                {"MQ",
                        "Merseburg in Sachsen-Anhalt"},
                /**Merseburg und Querfurt in Sachsen-Anahlt*/
                {"MR",
                        "Marburg in Hessen"},

                {"MS",
                        "Münster in Nordrhein-Westfalen"},

                {"MSE",
                        "Mecklenburgische Seenplatte in Mecklenburg-Vorpommern"},

                {"MSH",
                        "(Landkreis) Mansfeld-Südharz in Sachsen-Anhalt"},

                {"MSP",
                        "(Landkreis) Main-Spessart in Bayern"},

                {"MST",
                        "Mecklenburg-Strelitz in Mecklenburg-Vorpommern"},

                {"MTK",
                        "Main-Taunus-Kreis in Hessen"},

                {"MTL",
                        "Muldental in Sachsen"},

                {"MÜ",
                        "Mühldorf in Bayern"},

                {"MÜB",
                        "Münchberg in Bayern"},

                {"MÜR",
                        "Müritz in Mecklenburg-Vorpommern"},

                {"MVL",
                        "Mecklenburg-Vorpommerscher Landtag"},

                {"MW",
                        "Mittweida in Sachsen"},

                {"MY",
                        "Mayen in Rheinland-Pfalz"},

                {"MYK",
                        "(Landkreis) Mayen-Koblenz in Rheinland-Pfalz"},

                {"MZ",
                        "Mainz in Rheinland-Pfalz"},

                {"MZG",
                        "Merzig im Saarland"},


                {"N",
                        "Nürnberg in Bayern"},

                {"NAB",
                        "Naburg in Bayern"},

                {"NAI",
                        "Naila in Bayern"},

                {"NAU",
                        "Nauen in Brandenburg"},

                {"NB",
                        "Neubrandenburg in Mecklenburg-Vorpommern"},

                {"ND",
                        "Neuburg an der Donau in Bayern"},

                {"NDH",
                        "Nordhausen in Thüringen"},

                {"NE",
                        "Neuss in Nordrhein-Westfalen"},

                {"NEA",
                        "Neustadt an der Aisch in Bayern"},

                {"NEB",
                        "Nebra in Sachsen-Anhalt"},

                {"NEC",
                        "Neunburg in Bayern"},

                {"NEN",
                        "Neuunburg in Bayern"},

                {"NES",
                        "Neustadt an der Saale in Bayern"},

                {"NEW",
                        "Neustadt an der Waldnaab in Bayern"},

                {"NF",
                        "Nordfriesland in Schleswig-Holstein"},

                {"NH",
                        "Neuhaus in Thüringen"},

                {"NI",
                        "Nienburg in Niedersachsen"},

                {"NK",
                        "Neunkirchen im Saarland"},

                {"NL",
                        "Niedersächsicher Landtag"},

                {"NM",
                        "Neumarkt in Bayern"},

                {"NMB",
                        "Naumburg in Sachsen-Anhalt"},

                {"NMS",
                        "Neumüster in Schleswig-Holstein"},

                {"NÖ",
                        "Nördlingen in Bayern"},

                {"NOH",
                        "Nordhorn in Niedersachsen"},

                {"NOL",
                        "Niederschlesische Oberlausitz in Sachsen"},
                /**Problem bei Google Maps*/
                {"NOM",
                        "Northeim in Niedersachsen"},

                {"NOR",
                        "Norden in Niedersachsen"},

                {"NP",
                        "Neuruppin in Brandenburg"},

                {"NR",
                        "Neuwied am Rhein in Rheinland-Pfalz"},

                {"NRW",
                        "Nordrhein-Westfalen(Bundesland)"},

                {"NT",
                        "Nürtingen in Baden-Württemberg"},

                {"NU",
                        "Neu-Ulm in Bayern"},

                {"NVP",
                        "Nordvorpommern in Mecklenburg-Vorpommern"},

                {"NW",
                        "Neustadt an der Weinstraße in Rheinland-Pfalz"},

                {"NWM",
                        "Nordwestmecklenburg in Mecklenburg-Vorpommern"},

                {"NY",
                        "Niesky in Sachsen"},

                {"NZ",
                        "Neustrelitz in Mecklenburg-Vorpommern"},


                {"OA",
                        "Oberallgäu in Bayern"},

                {"OAL",
                        "Ostallgäu in Bayern"},
                {"OB",
                        "Oberhausen in Nordrhein-Westfalen"},

                {"OBG",
                        "Osterbug in Sachsen-Anhalt"},

                {"OC",
                        "Oschersleben in Sachsen-Anhalt"},

                {"OCH",
                        "Ochsenfurt in Bayern"},

                {"OD",
                        "Oldesloe in Schleswig-Holstein"},

                {"OE",
                        "Olpe in Nordrhein-Westfalen"},

                {"OF",
                        "Offenbach in Hessen"},

                {"OG",
                        "Offenburg in Baden-Württemberg"},

                {"OH",
                        "Ostholstein in Schleswig-Holstein"},

                {"OHA",
                        "Osterode am Harz in Niedersachsen"},

                {"ÖHR",
                        "Öhringen in Baden-Württemberg"},

                {"OHV",
                        "Oberhavel in Brandenburg"},

                {"OHZ",
                        "Osterholz in Niedersachsen"},

                {"OK",
                        "Ohrekreis in Sachsen-Anhalt"},

                {"OL",
                        "Oldenburg in Niedersachsen"},

                {"OP",
                        "Opladen in Nordrhein-Westfalen"},

                {"OPR",
                        "Ostprignitz-Ruppin in Brandenburg"},

                {"OS",
                        "Osnabrück in Niedersachsen"},

                {"OSL",
                        "Oberspreewald-Lausitz in Brandenburg"},

                {"OVI",
                        "Oberviechtach in Bayern"},

                {"OVL",
                        "Obervogtland in Sachsen"},

                {"OZ",
                        "Oschatz in Sachsen"},


                {"P",
                        "Potsdam in Brandenburg"},

                {"PA",
                        "Passau in Bayern"},

                {"PAF",
                        "Pfaffenhofen in Bayern"},

                {"PAN",
                        "Pfarrkirchen in Bayern"},

                {"PAR",
                        "Parsberg in Bayern"},

                {"PB",
                        "Paderborn in Nordrhein-Westfalen"},

                {"PCH",
                        "Parchim in Mecklenburg-Vorpommern"},

                {"PE",
                        "Peine in Niedersachsen"},

                {"PEG",
                        "Pegnitz in Bayern"},

                {"PF",
                        "Pforzheim in Baden-Württemberg"},

                {"PI",
                        "Pinneberg in Schleswig-Holstein"},

                {"PIR",
                        "Pirna in Sachsen"},

                {"PL",
                        "Plauen in Sachsen"},

                {"PLÖ",
                        "Plön in Schleswig-Holstein"},

                {"PM",
                        "Potsdam-Mittelmark in Brandenburg"},

                {"PN",
                        "Pößneck in Thüringen"},

                {"PR",
                        "Prignitz in Brandenburg"},

                {"PRÜ",
                        "Prüm in Rheinland-Pfalz"},

                {"PS",
                        "Primasens in Rheinland-Pfalz"},

                {"PW",
                        "Pasewalk in Mecklenburg-Vorpommern"},

                {"PZ",
                        "Prenzlau in Brandenburg"},

                /**Section Q*/
                {"QFT",
                        "Querfurt in Sachsen-Anhalt"},

                {"QLB",
                        "Quedlinburg in Sachsen-Anhalt"},

                /**Section R*/
                {"R",
                        "Regensburg in Bayern"},

                {"RA",
                        "Rastatt in Baden-Württemberg"},

                {"RC",
                        "Reichenbach in Sachsen"},

                {"RD",
                        "Rendsburg in Schleswig-Holstein"},

                {"RDG",
                        "Ribnitz-Damgarten in Mecklenburg-Vorpommern"},

                {"RE",
                        "Recklinghausen in Nordrhein-Westfalen"},

                {"REG",
                        "Regen in Bayern"},

                {"REH",
                        "Rehau in Bayern"},

                {"REI",
                        "Reichenhall in Bayern"},

                {"RG",
                        "Riesa-Großenhain in Sachsen"},  /** Google Maps Problem*/

                {"RH",
                        "Roth in Bayern"},

                {"RI",
                        "Rinteln in Niedersachsen"},

                {"RID",
                        "Riedenburg in Bayern"},

                {"RIE",
                        "Riesa in Sachsen"},

                {"RL",
                        "Rochlitz in Sachsen"},

                {"RM",
                        "Röbel/Müritz in Mecklenburg-Vorpommern"},

                {"RN",
                        "Rathenow in Brandenburg"},

                {"RO",
                        "Rosenheim in Bayern"},

                {"ROD",
                        "Roding in Bayern"},

                {"ROF",
                        "Rotenburg an der Fulda in Hessen"},

                {"ROK",
                        "Rockenhausen in Rheinland-Pfalz"},

                {"ROL",
                        "Rottenburg an der Laaber in Bayern"},

                {"ROS",
                        "Rostock in Mecklenburg-Vorpommern"},

                {"ROT",
                        "Rothenburg ob der Tauber in Bayern"},

                {"ROW",
                        "Rotenburg(Wümme) in Niedersachsen"},

                {"RP",
                        "Rhein-Pfalz in Rheinland-Pfalz"},

                {"RPL",
                        "Rheinland-Pfälzischer Landtag"},

                {"RS",
                        "Remscheid in Nordrhein-Westfalen"},

                {"RSL",
                        "Rosslau in Sachsen-Anhalt"},

                {"RT",
                        "Reutlingen in Baden-Württemberg"},

                {"RU",
                        "Rudolstadt in Thüringen"},

                {"RÜD",
                        "Rüdesheim in Hessen"},

                {"RÜG",
                        "Rügen in Mecklenburg-Vorpommern"},

                {"RV",
                        "Ravensburg in Baden-Württemberg"},

                {"RW",
                        "Rottweil in Baden-Württemberg"},

                {"RZ",
                        "Ratzeburg in Schleswig-Holstein"},


                {"S",
                        "Stuttgart in Baden-Württemberg"},

                {"SAB",
                        "Saarburg in Rheinland-Pfalz"},

                {"SAD",
                        "Schwandorf in Bayern"},

                {"SAL",
                        "Saarländischer Landtag"},

                {"SAN",
                        "Stadtsteinach in Bayern"},

                {"SAW",
                        "Salzwedel in Sachsen-Anhalt"},

                {"SB",
                        "Saarbrücken in Saarbrücken"},

                {"SBG",
                        "Strasburg in Mecklenburg-Vorpommern"},

                {"SBK",
                        "Schönebeck in Sachsen-Anhalt"},

                {"SC",
                        "Schwabach in Bayern"},

                {"SCZ",
                        "Schleiz in Thüringen"},

                {"SDH",
                        "Sondershausen in Thüringen"},

                {"SDL",
                        "Stendal in Sachsen-Anhalt"},

                {"SDT",
                        "Schwendt in Brandenburg"},

                {"SE",
                        "Segeberg in Schleswig-Holstein"},

                {"SEB",
                        "Sebnitz in Sachsen"},

                {"SEE",
                        "Seelow in Brandenburg"},

                {"SEF",
                        "Scheinfeld in Bayern"},

                {"SEL",
                        "Selb in Bayern"},

                {"SFB",
                        "Senftenberg in Brandenburg"},

                {"SFT",
                        "Staßfurt in Sachsen-Anhalt"},

                {"SG",
                        "Solingen in Nordrhein-Westfalen"},

                {"SGH",
                        "Sangerhausen in Sachsen-Anhalt"},

                {"SH",
                        "Schleswig-Holstein (Bundesland)"},

                {"SHA",
                        "Schwäbisch Hall in Baden-Württemberg"},

                {"SHG",
                        "Stadthagen in Niedersachsen"},

                {"SHK",
                        "Saale-Holzland-Kreis in Thüringen"},

                {"SHL",
                        "Suhl in Thüringen"},

                {"SI",
                        "Siegen in Nordrhein-Westfalen"},

                {"SIG",
                        "Sigmaringen in Baden-Württemberg"},

                {"SIM",
                        "Simmern in Rheinland-Pfalz"},

                {"SK",
                        "Saalekreis in Sachsen-Anhalt"},

                {"SL",
                        "Schleswig in Schleswig-Holstein"},

                {"SLE",
                        "Schleiden in Nordrhein-Westfalen"},

                {"SLF",
                        "Saalfeld in Thüringen"},

                {"SLK",
                        "Salzlandkreis in Sachsen-Anhalt"},

                {"SLN",
                        "Schmölln in Thüringen"},

                {"SLS",
                        "Saarlouis im Saarland"},

                {"SLÜ",
                        "Schlüchtern in Hessen"},

                {"SLZ",
                        "Salzungen in Thüringen"},

                {"SM",
                        "Schmalkalden in Thüringen"},

                {"SMÜ",
                        "Schwabmünchen in Bayern"},

                {"SN",
                        "Schwerin in Mecklenburg-Vorpommern"},

                {"SO",
                        "Soest in Nordrhein-Westfalen"},

                {"SOB",
                        "Schrobenhausen in Bayern"},

                {"SOG",
                        "Schongau in Bayern"},

                {"SOK",
                        "Saale-Orla-Kreis in Thüringen"},

                {"SÖM",
                        "Sömmerda in Thüringen"},

                {"SON",
                        "Sonneberg in Thüringen"},

                {"SP",
                        "Speyer in Rheinland-Pfalz"},

                {"SPB",
                        "Spremberg in Brandenburg"},

                {"SPN",
                        "Spree-Neiße in Brandenburg"},

                {"SR",
                        "Straubing in Bayern"},

                {"SRB",
                        "Strausberg in Brandenburg"},

                {"SRO",
                        "Stadtroda in Thüringen"},

                {"ST",
                        "Steinfurt in Nordrhein-Westfalen"},

                {"STA",
                        "Starnberg in Bayern"},

                {"STB",
                        "Stemberg in Mecklenburg-Vorpommern"},

                {"STD",
                        "Stade in Niedersachsen"},

                {"STE",
                        "Staffelstein in Bayern"},

                {"STL",
                        "Stoilberg in Sachsen"},

                {"SU",
                        "Siegburg in Nordrhein-Westfalen"},

                {"SUL",
                        "Sulzbach in Bayern"},

                {"SÜW",
                        "Südliche Weinstraße in Rheinland-Pfalz"},

                {"SW",
                        "Schweinfurt in Bayern"},

                {"SWA",
                        "Schwalbach in Hessen"},

                {"SZ",
                        "Salzgitter in Niedersachsen"},

                {"SZB",
                        "Schwarzenberg in Sachsen"},


                {"TBB",
                        "Tauberbischofsheim in Baden-Württemberg"},

                {"TDO",
                        "Torgau, Delitzsch und Oschat in Sachsen"},  //Probleme bei Maps

                {"TE",
                        "Tecklenburg in Nordrhein-Westfalen"},

                {"TET",
                        "Teterow in Mecklenburg-Vorpommern"},

                {"TF",
                        "Teltow Fläming in Brandenburg"},

                {"TG",
                        "Torgau in Sachsen"},

                {"THL",
                        "Thüringer Landtag"},

                {"THW",
                        "Technisches Hilfswerk"},  //evtl. Probleme bei Maps

                {"TIR",
                        "Tirschenreuth in Bayern"},

                {"TO",
                        "Torgau, Oschatz in Sachsen"},  //Problem bei Maps

                {"TÖL",
                        "Tölz in Bayern"},

                {"TP",
                        "Templin in Brandenburg"},

                {"TR",
                        "Trier in Rheinland-Pfalz"},

                {"TS",
                        "Taraunstein in Bayern"},

                {"TÜ",
                        "Tübingen in Baden-Württemberg"},

                {"TUT",
                        "Tuttlingen in Baden-Württemberg"},

                /**Section U */
                {"UE",
                        "Uelzen in Niedersachsen"},

                {"UEM",
                        "Ueckermünde in Mecklenburg-Vorpommern"},

                {"UFF",
                        "Uffenheim in Bayern"},

                {"UH",
                        "Unstrut Hainich in Thüringen"},

                {"UL",
                        "Ulm in Baden-Württemberg"},

                {"UM",
                        "Uckermark in Brandenburg"},

                {"UN",
                        "Unna in Nordrhein-Westfalen"},

                {"USI",
                        "Usingen in Hessen"},


                {"V",
                        "Vogtland in Sachsen"},

                {"VAI",
                        "Vaihingen in Baden-Württemberg"},

                {"VB",
                        "Vogelsberg in Hessen"},

                {"VEC",
                        "Vechta in Niedersachsen"},

                {"VER",
                        "Verden in Niedersachsen"},

                {"VG",
                        "Vorpommern Greifswald in Mecklenburg-Vorpommern"},

                {"VIB",
                        "Vilsbiburg in Bayern"},

                {"VIE",
                        "Viersen in Nordrhein-Westfalen"},

                {"VK",
                        "Völkingen im Saarland"},

                {"VOH",
                        "Vohenstrauß in Bayern"},

                {"VR",
                        "Vorpommern Rügen in Mecklenburg-Vorpommern"},

                {"VS",
                        "Villingen-Schwenningen in Baden-Württemberg"},


                {"W",
                        "Wuppertal in Nordrhein-Westfalen"},

                {"WA",
                        "Waldeck in Hessen"},

                {"WAF",
                        "Warendorf in Nordrhein-Westfalen"},

                {"WAK",
                        "Wartburgkreis in Thüringen"},

                {"WAN",
                        "Wanne in Nordrhein-Westfalen"},

                {"WAT",
                        "Wattenscheid in Nordrhein-Westfalen"},

                {"WB",
                        "Wittenberg in Sachsen-Anhalt"},

                {"WBS",
                        "Worbis in Thüringen"},

                {"WDA",
                        "Werdau in Sachsen"},

                {"WE",
                        "Weimar in Thüringen"},

                {"WEL",
                        "Weilburg in Hessen"},

                {"WEN",
                        "Weiden in Bayern"},

                {"WER",
                        "Wertingen in Bayern"},

                {"WES",
                        "Wesel in Nordrhein-Westfalen"},

                {"WF",
                        "Wolfenbüttel in Niedersachsen"},

                {"WHV",
                        "Wilhelmshaven in Niedersachsen"},

                {"WI",
                        "Wiesbaden in Hessen"},

                {"WIL",
                        "Wittlich in Rheinland-Pfalz"},

                {"WIS",
                        "Wismar in Mecklenburg-Vorpommern"},

                {"WIT",
                        "Witten in Nordrhein-Westfalen"},

                {"WIZ",
                        "Witzenhausen in Hessen"},

                {"WK",
                        "Wittstock in Brandenburg"},

                {"WL",
                        "Winsen(Luhe) in Niedersachsen"},

                {"WLG",
                        "Wolgast in Mecklenburg-Vorpommern"},

                {"WM",
                        "Weilheim in Bayern"},

                {"WMS",
                        "Wolmirstedt in Sachsen-Anhalt"},

                {"WN",
                        "Waiblingen in Baden-Württemberg"},

                {"WND",
                        "Wendel im Saarland"},

                {"WO",
                        "Worms in Rheinland-Pfalz"},

                {"WOB",
                        "Wolfsburg in Niedersachsen"},

                {"WOH",
                        "Wolfhagen in Hessen"},

                {"WOL",
                        "Wolfach in Baden-Württemberg"},

                {"WOR",
                        "Wolfratshausen in Bayern"},

                {"WOS",
                        "Wolfstein in Bayern"},

                {"WR",
                        "Wernigerode in Sachsen-Anhalt"},

                {"WRN",
                        "Waren in Mecklenburg-Vorpommern"},

                {"WS",
                        "Wasserburg in Bayern"},

                {"WSF",
                        "Weissenfels in Sachsen-Anhalt"},

                {"WST",
                        "Westerstede in Niedersachsen"},

                {"WSW",
                        "Weisswasser in Sachsen"},

                {"WT",
                        "Waldshut in Baden-Württemberg"},

                {"WTM",
                        "Wittmund in Niedersachsen"},

                {"WÜ",
                        "Würzburg in Bayern"},

                {"WUG",
                        "Weißenburg in Bayern"},

                {"WÜM",
                        "Waldmünchen in Bayern"},

                {"WUN",
                        "Wunsiedel in Bayern"},

                {"WUR",
                        "Wurzen in Sachsen"},

                {"WW",
                        "Westerwald in Rheinland-Pfalz"},

                {"WZ",
                        "Wetzlar in Hessen"},

                {"WZL",
                        "Wanzleben in Sachsen-Anhalt"},

                /** Section X,Y,Z*/
                {"X",
                        "NATO"},

                {"Y",
                        "Bundeswehr"},

                {"Z",
                        "Zwickau in Sachsen"},

                {"ZE",
                        "Zerbst in Sachsen-Anhalt"},

                {"ZEL",
                        "Zeli Rheinland-Pfalz"},

                {"ZI",
                        "Zittau in Sachsen"},

                {"ZIG",
                        "Ziegenhain in Hessen"},

                {"ZP",
                        "Zschopau in Sachsen"},

                {"ZR",
                        "Zeulenroda in Thüringen"},

                {"ZW",
                        "Zweibrücken in Rheinland-Pfalz"},

                {"ZZ", "Zeitz in Sachsen-Anhalt"},


        };
        if(dataSource.getElement("first_start")== null) {
            Log.d("Simon", "starts setupDB");
            dataSource.createDbElement("first_start", "0");
        }
        if(dataSource.getElement("first_start").getResult().equals("0")) {
            String plate_number = "";
            String result = "";
            Log.d("Simon", "onCreate MainActivity");
            for (int i = 0; i < array.length; i++) {
                plate_number = array[i][0];
                result = array[i][1];
                dataSource.createDbElement(plate_number, result);



            }
            dataSource.createDbElement("count", "0");
            dataSource.createDbElement("history_enabled", "1");
            dataSource.createDbElement("maps_enabled", "1");
            dataSource.createDbElement("maps_zoom", "5");
            dataSource.createDbElement("input_caps", "1");

            dataSource.update(dataSource.getElement("first_start"), "1", -1);
        }

    }


    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, R.string.double_click_ends_app, Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

}
